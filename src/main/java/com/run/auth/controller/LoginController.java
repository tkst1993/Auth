package com.run.auth.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.run.auth.common.Whether;
import com.run.auth.context.LoginUserCache;
import com.run.auth.context.NativeCache;
import com.run.auth.context.UserContext;
import com.run.auth.dto.Accordion;
import com.run.auth.entity.Functions;
import com.run.auth.entity.Role;
import com.run.auth.entity.RoleFunction;
import com.run.auth.entity.User;
import com.run.auth.entity.UserRole;
import com.run.auth.service.RoleService;
import com.run.auth.service.UserService;

@Controller
public class LoginController {
	
	@Autowired private UserService userService;
	
	@Autowired private NativeCache nativeCache;
	
	@Autowired private RoleService roleService;
	
	@RequestMapping("/auth")
	@ResponseBody
	public String index(){
		if(null != UserContext.getCurrent() && null !=UserContext.getCurrent().getUser()){
			return "detail";
		}
		return "login";
	}
	
	@RequestMapping("/login_t")
	public String login(Model model , String name , String pwd){
		//非空检验
		User user = userService.getUser(name, pwd);
		if(null == user){
			return "/security/login";
		}
		try{
			//LoginUserCache.put(user, 30*60);
			
			if("admin".equals( user.getName() )){
				//左侧显示的内容
				model.addAttribute("accordins",getAccordion(true,null));
			}else{
				List<UserRole> userRoles = userService.getUserRolesByUserId(user.getId());
				if(null == userRoles || userRoles.size() == 0){
					return "login";
				}
				List<Long> roleIds = new ArrayList<Long>();
				for(UserRole userRole : userRoles){
					roleIds.add(userRole.getRoleId());
				}
				List<Role> roles = roleService.getRoles(roleIds);
				nativeCache.setRoles(user.getId(), roles);
				
				LoginUserCache.put(user);
				List<Accordion> accordions = getAccordion(false,user.getId());
				model.addAttribute("accordins",accordions);
				LoginUserCache.setAccorions(user.getName(), accordions);
			}
			return "detail";
			
		}catch(Exception e){
			e.printStackTrace();
			LoginUserCache.remove(user.getName());
			return "login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(){
		if(null != UserContext.getCurrent() && null !=UserContext.getCurrent().getUser()){
			LoginUserCache.remove(UserContext.getCurrent().getUser().getName());
		}
		
		return "/security/login";
		
	}
	
	private List<Accordion> getAccordion(boolean isAdmin , Long userId)
	{
		Set<String>permissionUrls = new HashSet<String>();
		Set<Long>rootFunctionIdSet = new HashSet<Long>();
		if(!isAdmin){
			for(Role role:nativeCache.getRoles(userId)){
				List<RoleFunction>roleFunction = roleService.getRoleFunctions(role.getId());
				for(RoleFunction rf : roleFunction){
					Functions func = nativeCache.getFunction(rf.getFunctionId());
					if(Whether.YES.getValue() ==func.getAccordion() ){
						rootFunctionIdSet.add(func.getId());
					}else{
						permissionUrls.add(func.getUrl());
					}
				}
			}
		}
		
		Map<Long,Accordion> accordionMap = new HashMap<Long,Accordion>();
		List<Accordion> permissionAccordionSet = new LinkedList<Accordion>();
		
		List<Functions> functions = nativeCache.getFunctions();
		
		List<Accordion> rootAccordionSet = new LinkedList<Accordion>();
		
		for(Functions function:functions){
			Accordion accordion = new Accordion(function.getId(),function.getParentId(),function.getName(),function.getUrl(),function.getserialNum());
			accordionMap.put(function.getId(), accordion);
			if(!isAdmin){
				if(permissionUrls.contains(function.getUrl())){
					permissionAccordionSet.add(accordion);
				}
				if(rootFunctionIdSet.contains(function.getUrl())){
					rootAccordionSet.add(accordion);
				}
			}else{
				permissionAccordionSet.add(accordion);
				if(Whether.YES.getValue() == function.getAccordion()){
					rootAccordionSet.add(accordion);
				}
			}
		}
		
		Collections.sort(rootAccordionSet);
		Collections.sort(permissionAccordionSet);
		
		for(Accordion accordion : rootAccordionSet){
			completeAccordion(permissionAccordionSet,accordion);
		}
		return rootAccordionSet;
	}

	private void completeAccordion(List<Accordion> permissionAccordionSet,
			Accordion rootAccordion) {
		for(Accordion accordion : permissionAccordionSet){
			if(accordion.getParentId() == rootAccordion.getId()){
				rootAccordion.getChildren().add(accordion);
			}
		}
		
	}

}

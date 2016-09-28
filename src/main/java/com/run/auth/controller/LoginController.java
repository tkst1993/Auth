package com.run.auth.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.run.auth.common.AjaxResult;
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

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired private UserService userService;
	
	@Autowired private NativeCache nativeCache;
	
	@Autowired private RoleService roleService;
	
	@RequestMapping("/index")
	public String index(){
		if(null != UserContext.getCurrent() && null !=UserContext.getCurrent().getUser()){
			return "detail_t";
		}
		return "login";
	}
	
	@RequestMapping("/login_t")
	public ModelAndView login(ModelAndView mav, String username , String pwd , HttpServletRequest request,
						HttpServletResponse response){
		//非空检验
		User user = userService.getUser(username, pwd);
		JSONObject jsonResult = new JSONObject();
		if(null == user){
			//jsonResult.put("resMsg", AjaxResult.error());
			mav.addObject("resMsg", AjaxResult.error());
			mav.setViewName("login");
			return mav;
		}else{
			try{
				//LoginUserCache.put(user, 30*60);
				//jsonResult.put("username", user.getName());
				//mav.addObject("usrname",user.getName());
				//session中设置用户名
				request.getSession().setAttribute("usrname",user.getName());
				if("admin".equals( user.getName() )){
					//左侧显示的内容
					List<Accordion> accordions = getAccordion(true,null);
					//jsonResult.put("accordins",accordins);
					mav.addObject("accordions",accordions);
					//request.setAttribute("accordions",accordions);
				}else{
					List<UserRole> userRoles = userService.getUserRolesByUserId(user.getId());
					if(null == userRoles || userRoles.size() == 0){
						mav.addObject("resMsg", AjaxResult.error());
						//jsonResult.put("resMsg", AjaxResult.error());
					}
					List<Long> roleIds = new ArrayList<Long>();
					for(UserRole userRole : userRoles){
						roleIds.add(userRole.getRoleId());
					}
					List<Role> roles = roleService.getRoles(roleIds);
					nativeCache.setRoles(user.getId(), roles);
					
					LoginUserCache.put(user);
					List<Accordion> accordions = getAccordion(false,user.getId());
					//jsonResult.put("accordins",accordions);
					mav.addObject("accordions",accordions);
					//request.setAttribute("accordions",accordions);
					LoginUserCache.setAccorions(user.getName(), accordions);
				}
				//jsonResult.put("resMsg", AjaxResult.success());
				mav.addObject("resMsg", AjaxResult.success());

				//输出数据
				/*try{
					PrintWriter pw = response.getWriter();
					pw.println(jsonResult.toString());
				}catch(Exception e){
					e.printStackTrace();
				}*/
				mav.setViewName("detail");
				return mav;
			}catch(Exception e){
				e.printStackTrace();
				LoginUserCache.remove(user.getName());
				//jsonResult.put("resMsg", AjaxResult.error());
				mav.addObject("resMsg", AjaxResult.error());
				mav.setViewName("login");
				return mav;
			}
		}


	}
	
	@RequestMapping("/logout")
	public String logout(){
		if(null != UserContext.getCurrent() && null !=UserContext.getCurrent().getUser()){
			LoginUserCache.remove(UserContext.getCurrent().getUser().getName());
		}
		return "redirect:/login_t";
		
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

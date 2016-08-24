package com.run.auth.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run.auth.common.Whether;
import com.run.auth.dto.Accordion;
import com.run.auth.entity.Functions;
import com.run.auth.entity.Role;
import com.run.auth.entity.RoleFunction;
import com.run.auth.entity.User;
import com.run.auth.entity.UserRole;
import com.run.auth.service.RoleService;
import com.run.auth.service.UserService;
import com.sun.org.apache.xalan.internal.utils.Objects;

@Component
public class LoginUserHelper {
	@Autowired private UserService userService;
	
	@Autowired private RoleService roleService;
	
	@Autowired private NativeCache nativeCache;
	
	public void executeLogin(String username,String pwd){
		User user = userService.getUser(username, pwd);
		List<UserRole>userRoles = userService.getUserRolesByUserId(user.getId());
		if(null == user || 0==userRoles.size()){
			return;
		}
		List<Long> roleIds = new ArrayList<Long>();
		for(UserRole userRole : userRoles){
			roleIds.add(userRole.getRoleId());
		}
		List<Role> roles = roleService.getRoles(roleIds);
		nativeCache.setRoles(user.getId(), roles);
		
		LoginUserCache.put(user);
		List<Accordion> accordions = getAccordion(false,user.getId());
		LoginUserCache.setAccorions(user.getName(), accordions);
		
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
					if(Objects.equals(func.getAccordion(), Whether.YES.getValue())){
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
			if(Objects.equals(accordion.getParentId(), rootAccordion.getId())){
				rootAccordion.getChildren().add(accordion);
			}
		}
		
	}
	

}

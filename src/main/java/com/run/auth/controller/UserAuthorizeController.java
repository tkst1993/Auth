package com.run.auth.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.run.auth.common.AjaxResult;
import com.run.auth.common.BaseEntity;
import com.run.auth.dto.Authorize;
import com.run.auth.entity.Role;
import com.run.auth.entity.User;
import com.run.auth.entity.UserRole;
import com.run.auth.service.RoleService;
import com.run.auth.service.UserService;


@RequestMapping("/authorize")
@Controller
public class UserAuthorizeController {
	
	@Autowired private UserService userService;
	
	@Autowired private RoleService roleService;
	
	@RequestMapping("/index")
	public String index(){
		return "/security/authorize/zuthorize_list";
	}
	
	@RequestMapping("/userRole")
	public String authorizeIndex(){
		return "/security/authorize/user_role_list";
	}
	
	@RequestMapping("/getAuthorizes")
	public List<Authorize> getAuthorizes(Integer page,Integer rows)
	{
		List<UserRole> userRoles = userService.getUserRoles(page, rows);
		Collection<Long> userIds = new HashSet<Long>();
		Collection<Long> roleIds = new HashSet<Long>();
		for(UserRole ur : userRoles){
			userIds.add(ur.getUserId());
			roleIds.add(ur.getRoleId());
		}
		
		Collection<User> users =  userService.getUsers(userIds);
		List<Role> roles = roleService.getRoles(roleIds);
		
		Map<Long,User> userMap = BaseEntity.idEntityMap(users);
		Map<Long,Role> roleMap = BaseEntity.idEntityMap(roles);
		
		List<Authorize> authorizes = new LinkedList<Authorize>();
		for(UserRole ur : userRoles){
			Authorize authorize = new Authorize();
			authorize.setRoleId(ur.getRoleId());
			authorize.setUserId(ur.getUserId());
			authorize.setUserName(userMap.get(ur.getUserId()).getName());
			authorize.setUserRoleId(ur.getId());
			authorize.setRoleName(roleMap.get(ur.getRoleId()).getName());
			authorizes.add(authorize);
		}
		
		return authorizes;
		
	}
	
	/**
	 * 根据用户id查询用户和角色的 对应关系
	 * @param userId
	 * @return
	 */
	public List<UserRole> getUserRoleBuUserId(Long userId){
		return userService.getUserRolesByUserId(userId);
	}
	
	/**
	 * 设置权限
	 * @param user
	 * @param roleIds
	 * @return
	 */
	public AjaxResult setAuthorize(User user,String roleIds)
	{
		String[] temp = roleIds.split(",");
		Long[] roleIdArray  = new Long[temp.length];
		for(int i=0;i<roleIdArray.length;i++){
			roleIdArray[i] = Long.valueOf(temp[i]);
		}
		userService.addUserRole(user.getId(), roleIdArray);
		return AjaxResult.success();
	}
	

}

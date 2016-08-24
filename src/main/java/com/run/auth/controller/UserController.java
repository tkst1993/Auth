package com.run.auth.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.run.auth.common.AjaxResult;
import com.run.auth.entity.User;
import com.run.auth.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index")
	public String userList(){
		return "/security/user/user_list";
	}
	
	/**
	 * 新建、修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/addEditUser")
	@ResponseBody
	public AjaxResult addEditUser(User user){
		if(null == user.getId()){
			userService.addUser(user);
		}else{
			userService.updateUser(user);
		}
		return AjaxResult.success();
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public AjaxResult deleteUser(Long id){
		userService.deleteUserById(id);
		return AjaxResult.success();
	}
	
	/**
	 * 分页查询用户信息
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getUsers")
	@ResponseBody
	public Collection<User> getUsers(Integer page,Integer size){
		return userService.getUsers(page, size);
	}

}

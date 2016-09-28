package com.run.auth.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.run.auth.dto.Accordion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.run.auth.common.AjaxResult;
import com.run.auth.entity.User;
import com.run.auth.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index")
	public ModelAndView userList(ModelAndView mav, HttpServletRequest request , HttpServletResponse response){
		System.out.println("/security/user/user_list");
		List<Accordion> accordions = (List<Accordion>) request.getAttribute("accordions");
		mav.addObject("accordions",accordions);
		mav.setViewName("userManage");
		return mav;
		//return "/security/user/user_list";
	}
	
	/**
	 *
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
	 *
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
	 *
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

package com.run.auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.run.auth.common.AjaxResult;
import com.run.auth.entity.Role;
import com.run.auth.entity.RoleFunction;
import com.run.auth.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	@ResponseBody
	public void index(){
		//return "security/role/role_list";
	}
	
	@RequestMapping(value = "/getRoles",method = RequestMethod.POST)
	@ResponseBody
	public List<Role> getRoles(Integer page,Integer size){
		return roleService.getRoles(page, size);
	}
	
	@RequestMapping("/addEditRole")
	@ResponseBody
	public AjaxResult addEditRole(Role role){
		String functionIds = role.getFunctionIds();
		//得到角色的functionid
		String[] idArray = functionIds.split(",");
		List<RoleFunction> rfs = new ArrayList<RoleFunction>();
		for(int i=0;i<idArray.length;i++){
			RoleFunction rf = new RoleFunction();
			rf.setFunctionId(Long.valueOf(idArray[i]));
			//rf.setRoleId(role.getId());
			rf.setStatus(1);
			rfs.add(rf);
		}
		if(null == role.getId()){
			roleService.addRole(role, rfs);
		}else{
			roleService.editRole(role, rfs);
		}
		
		return AjaxResult.success();
	}
	
	@RequestMapping("/deleteRole")
	@ResponseBody
	public AjaxResult deleteRole(Long id){
		 roleService.deleteRole(id);
		 return AjaxResult.success();
	}

}

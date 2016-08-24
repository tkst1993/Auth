package com.run.auth.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.run.auth.entity.Functions;
import com.run.auth.entity.Role;
import com.run.auth.service.FunctionService;
/**
 * 适用范围：1、单节点应用 2、不涉及修改
 * @author tangke
 *
 */
@Service
public class NativeCache {
	//<functionid,functions>
	private Map<Long,Functions> functionMap = new HashMap<Long,Functions>();
	
	//<userId,Roles>
	private Map<Long,List<Role>> userRoleMap = new HashMap<Long,List<Role>>();
	
	@Autowired
	private FunctionService functionService;
	
	@PostConstruct
	public void init(){
		List<Functions> functionList = functionService.getAllFunctions();
		for(Functions function:functionList){
			functionMap.put(function.getId(), function);
		}
	}
	
	public List<Functions> getFunctions(){
		if(functionMap.isEmpty()){
			init();
		}
		return new ArrayList<Functions>(functionMap.values());
	}
	
	public void addFunction(Functions function){
		functionMap.put(function.getId(), function);
	}
	
	public void replaceFunction(Functions function){
		if(functionMap.containsKey(function.getId())){
			functionMap.remove(function.getId());
			functionMap.put(function.getId(), function);
		}
	}
	public void removeFunction(Long functionId){
		if(functionMap.containsKey(functionId)){
			functionMap.remove(functionId);
		}
	}
	
	public void setRoles(Long userId , List<Role>Roles){
		userRoleMap.put(userId, Roles);
	}
	
	public List<Role> getRoles(Long userId){
		return userRoleMap.get(userId);
	}
	
	public Functions getFunction(Long id){
		return functionMap.get(id);
	}
	

}

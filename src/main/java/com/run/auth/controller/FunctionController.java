package com.run.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.run.auth.common.AjaxResult;
import com.run.auth.common.tree.Node;
import com.run.auth.common.tree.Tree;
import com.run.auth.context.NativeCache;
import com.run.auth.entity.Functions;
import com.run.auth.service.FunctionService;

@Controller
@RequestMapping("/function")
public class FunctionController {
	
	@Autowired
	private FunctionService functionService;
	
	@Autowired NativeCache nativeCache;
	/**
	 * 菜单首页
	 * @return
	 */
	@RequestMapping(value="/index")
	public String userList(){
		return "/security/function/function_list";
	}
	/**
	 * 增加功能（菜单）
	 * @param function
	 */
	@RequestMapping("/addFunction")
	@ResponseBody
	public AjaxResult addFunction(Functions function){
		if(null == function.getId()){
			function.setserialNum(nativeCache.getFunctions().size());
			functionService.addFunction(function);
			nativeCache.addFunction(function);
		}else{
			functionService.updateUrl(function.getId(), function.getUrl());
			nativeCache.replaceFunction(function);
		}
		return AjaxResult.success();
		
	}
	
	/**
	 * 查询全部功能
	 */
	@RequestMapping("/getAllFunctions")
	@ResponseBody
	public void getAllFunctions(){
		functionService.getAllFunctions();
	}
	
	/**
	 * 根据id删除功能
	 * @param id
	 */
	@RequestMapping("/deleteFunctionById")
	@ResponseBody
	public AjaxResult deleteFunctionById(Long id){
		functionService.deleteFunctionById(id);
		nativeCache.removeFunction(id);
		return AjaxResult.success();
	}
	/**
	 * 得到子菜单功能集合
	 * @param page
	 * @param size
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/getSubFunctions")
	@ResponseBody
	public List<Functions> getSubFunctions(int page,int size,Long parentId){
		if(null == parentId){
			parentId = 0L;
		}
		return functionService.getFunctions(page, size, parentId);
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/buildMenuTreeForEdit")
	@ResponseBody
	public List<Node> buildMenuTreeForEdit(){
		List<Functions>functionList = nativeCache.getFunctions();
		Tree tree = new Tree(functionList);
		return tree.build();
		
	}
	
	

}

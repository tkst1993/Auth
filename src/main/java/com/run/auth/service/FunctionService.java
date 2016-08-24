package com.run.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.run.auth.common.AjaxResult;
import com.run.auth.dao.FunctionDao;
import com.run.auth.entity.Functions;


@Service
public class FunctionService {
	@Autowired
	private FunctionDao functionDao;
	
	/**
	 * 增加功能（菜单）
	 * @param funtion
	 */
	public void addFunction(Functions funtion){
		functionDao.saveFunctions(funtion);
	}
	
	/**
	 * 根据功能id更新其url信息
	 * @param id
	 * @param url
	 */
	public void updateUrl(Long id , String url){
		functionDao.updateUrl(id, url);
	}
	
	/**
	 * 分页查询指定父节点的子节点
	 * @param page
	 * @param size
	 * @param parentId
	 * @return 功能集合
	 */
	public List<Functions>getFunctions(int page,int size,Long parentId){
		return functionDao.findFunctions(page, size, parentId);
	}
	
	/**
	 * 根据id删除功能
	 * @param id
	 */
	public AjaxResult deleteFunctionById(Long id){
		functionDao.deleteById(id);
		return AjaxResult.success();
	}
	/**
	 * 查询全部功能
	 * @return
	 */
	public List<Functions> getAllFunctions(){
		return functionDao.findALlFunctions();
	}
	
	

}

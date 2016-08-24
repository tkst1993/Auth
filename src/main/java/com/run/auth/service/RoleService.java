package com.run.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.run.auth.dao.RoleDao;
import com.run.auth.dao.RoleFunctionDao;
import com.run.auth.entity.Role;
import com.run.auth.entity.RoleFunction;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleFunctionDao roleFunctionDao;
	
	/**
	 * 保存角色信息，同时保存角色对应的功能
	 * @param role 角色
	 * @param roleFunctions 角色功能的关联关系
	 */
	public void addRole(Role role,List<RoleFunction> roleFunctions){
		roleDao.saveRole(role);
		for( int i=0;i<roleFunctions.size();i++){
			roleFunctions.get(i).setRoleId(role.getId());
		}
		
		roleFunctionDao.saveRoleFunction(roleFunctions);
	}
	/**
	 * 修改角色信息，同时修改角色对应得功能
	 * @param role
	 * @param roleFunctions
	 */
	public void editRole(Role role,List<RoleFunction> roleFunctions){
		roleDao.updateRole(role);
		roleFunctionDao.deleteByRoleId(role.getId());
		for( int i=0;i<roleFunctions.size();i++){
			roleFunctions.get(i).setRoleId(role.getId());
		}
		roleFunctionDao.saveRoleFunction(roleFunctions);
	}
	
	
	/**
	 * 分页查询角色信息
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Role> getRoles(int page,int size)
	{
		List<Role>roles = roleDao.findRolesByPages(page, size);
		for(Role role : roles){
			List<RoleFunction> roleFunctions = roleFunctionDao.findRoleFunctionsByRoleId(role.getId());
			StringBuilder functionIds = new StringBuilder();
			for(RoleFunction rf : roleFunctions)
			{
				functionIds.append(rf.getFunctionId()).append(",");
			}
			if(functionIds.length() >  1){
				role.setFunctionIds(functionIds.deleteCharAt(functionIds.length() - 1).toString());
			
			}
		}
		return roles;
	}
	
	/**
	 * 根据id删除角色
	 * @param roleId
	 */
	public void deleteRole(Long roleId){
		roleDao.deleteRoleById(roleId);
		roleFunctionDao.deleteByRoleId(roleId);
	}
	
	/**
	 * 根据id集合查询角色信息
	 * @param ids
	 * @return
	 */
	public List<Role> getRoles(Collection<Long> ids)
	{
		return roleDao.findRoleByIds(ids);
	}
	
	/**
	 * 根据用户id查询用户功能对应关系
	 * @param roleId
	 * @return
	 */
	public List<RoleFunction> getRoleFunctions(Long roleId){
		return roleFunctionDao.findRoleFunctionsByRoleId(roleId);
	}
	
	

}

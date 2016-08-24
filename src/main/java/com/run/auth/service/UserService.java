package com.run.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.run.auth.dao.UserDao;
import com.run.auth.dao.UserRoleDao;
import com.run.auth.entity.User;
import com.run.auth.entity.UserRole;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void addUser(User user){
		userDao.save(user);
	}
	
	public void updateUser(User user){
		userDao.update(user);
	}
	
	public void deleteUserById(Long id){
		userDao.deleteById(id);
	}
	
	public User getUser(String name,String pwd)
	{
		return userDao.getUser(name, pwd);
	}
	
	public Collection<User> getUsers(int page ,int size)
	{
		return userDao.findPage(page, size);
	}
	
	public Collection<User> getUsers(Collection<Long> ids)
	{
		return userDao.findByIds(ids);
	}
	
	/**
	 *分页查询用户角色对应关系 
	 * @param page
	 * @param size
	 * @return 用户角色对应关系集合
	 */
	public List<UserRole> getUserRoles(Integer page , Integer size)
	{
		return userRoleDao.findUserRoles(page, size);
	}
	
	/**
	 * 根据用户id查询角色对应关系
	 * @param userId 用户id
	 * @return 用户角色对应关系集合
	 */
	public List<UserRole> getUserRolesByUserId(Long userId)
	{
		return userRoleDao.findUserRoleByUserId(userId);
	}
	/**
	 * 保存用户角色对应关系
	 * @param userid
	 * @param roleIds
	 */
	public void addUserRole(Long userid,Long[]roleIds){
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for(int i=0;i<roleIds.length;i++){
			UserRole userRole = new UserRole();
			userRole.setUserId(userid);
			userRole.setRoleId(roleIds[i]);
			userRoles.add(userRole);
		}
		userRoleDao.saveUserRoles(userRoles);
		
	}
}

package com.run.auth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.run.auth.common.BaseDao;
import com.run.auth.entity.User;

public class UserDao extends BaseDao{
	
	private class UserMapper implements RowMapper<User>{

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setPwd(rs.getString("pwd"));
			return user;
		} 
		
	}
	/**
	 * 根据用户名密码查询用户，用于登录
	 * @param name
	 * @param pwd
	 * @return
	 */
	public User getUser(String name , String pwd){
		String sql = "select * from auth_user where name = ? and pwd = ?";
		try{
			return jdbcTemplate.queryForObject(sql, new Object[]{name,pwd},new UserMapper());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void save(User user){
		String sql = "insert into auth_user(id,name,pwd) values(?,?,?)";
		jdbcTemplate.update(sql, user.getId(),user.getName(),user.getPwd());
		
	}
	public void deleteById(Long id){
		String sql = "delete from auth_user where id = ?";
		jdbcTemplate.update(sql, id);
		
	}
	public void update(User user){
		String sql = "update auth_user set name=?,pwd=? where id = ?";
		jdbcTemplate.update(sql, user.getName(),user.getPwd(),user.getId());
		
	}
	public User fingById(Long id){
		String sql = "select * from auth_user where id = ?";
		try{
			return jdbcTemplate.queryForObject(sql, new Object[]{id},new UserMapper());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> findByIds(List<Long> ids){
		StringBuilder sqlStr = new StringBuilder("select * from auth_user where id in (");
		for(Long id : ids){
			sqlStr.append(id+",");
		}
		sqlStr.append(")");
		String sql = sqlStr.deleteCharAt(sqlStr.length()-2).toString();
		System.out.println(sql);
		try{
			return jdbcTemplate.query(sql,new UserMapper());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/*public static void main(String[] args) {
		List<Long>ids = new ArrayList<Long>();
		ids.add(12L);
		ids.add(34L);
		ids.add(56L);
		ids.add(78L);
		findByIds(ids);
		
		
	}*/
	

}

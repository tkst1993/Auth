package com.run.auth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.run.auth.common.BaseDao;
import com.run.auth.entity.UserRole;
@Repository
public class UserRoleDao extends BaseDao {
	private Logger logger= LoggerFactory.getLogger(UserRoleDao.class);

    /**
     * UserRole关系实体类RowMapper映射类
     */
    private class UserRoleRowMapper implements RowMapper<UserRole>{

        public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {
            UserRole userRole=new UserRole();
            userRole.setRoleId(resultSet.getLong("role_id"));
            userRole.setUserId(resultSet.getLong("user_id"));
            userRole.setId(resultSet.getLong("id"));
            return userRole;
        }
    }

    /**
     * 根据userRole 来更新
     * @param userRole 用户角色
     */
    public void updateUserRole(UserRole userRole){
        String sql="update auth_user_role set role_id =?,user_id =? where id= ?";
        jdbcTemplate.update(sql,userRole.getRoleId(),userRole.getUserId(),userRole.getId());
    }

    /**
     * 保存UserRole对象
     * @param userRole
     */
    public void saveUserRole(UserRole userRole){
        String sql="insert into auth_user_role(role_id,user_id) values(?,?)";
        jdbcTemplate.update(sql,userRole.getRoleId(),userRole.getUserId());
    }

    /**
     * 根据id查找UserRole对象
     * @param id
     * @return
     */
    public UserRole findUserRoleById(Long id){
        String sql="select * from auth_user_role where id =?";
        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{id},new UserRoleRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    /**
     * 分页查询用户角色关系
     * @param page
     * @param size
     * @return
     */
    public List<UserRole> findUserRoles(int page,int size){
        String sql="select * from auth_user_role limit ?,?";
        try {
            return jdbcTemplate.query(sql,new Object[]{(page-1)*size,size},new UserRoleRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据userId查询用户角色
     * @param userId
     * @return
     */
    public List<UserRole> findUserRoleByUserId(Long userId){
        String sql="select * from auth_user_role where user_id =?";
        try {
            return jdbcTemplate.query(sql,new Object[]{userId},new UserRoleRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 批量保存用户角色对应关系
     * @param userRoles 用户角色对应关系集合
     */
    public void saveUserRoles(Collection<UserRole> userRoles){
        String sql="insert into auth_user_role(role_id,user_id) values(?,?)";
        List<Object[]> batchuArgs=new ArrayList<Object[]>();
        for(UserRole ur: userRoles){
        	Object[] obj=new Object[2];
            obj[0]=ur.getRoleId();
            obj[1]=ur.getUserId();
            batchuArgs.add(obj);
        }
        jdbcTemplate.batchUpdate(sql,batchuArgs);
    }

}

package com.run.auth.dao;

import com.run.auth.common.BaseDao;
import com.run.auth.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.List;
@Repository
public class RoleDao extends BaseDao{
	
	private Logger logger= LoggerFactory.getLogger(RoleDao.class);

    /**
     * Role的RowMapper映射类
     */
    private class RoleRowMapper implements RowMapper<Role>{

        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role=new Role();
            role.setId(resultSet.getLong("id"));
            role.setName(resultSet.getString("name"));
            return role;
        }
    }

    /**
     * 根据id查找用户角色
     * @param id id
     * @return
     */
    public Role findRoleById(Long id){
        String sql="select * from auth_role where id =?";
        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{id},new RoleRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  保存角色 目的可以将主键带出来。
     * @param role role角色
     */
    public void saveRole(final Role role){
        final String sql="insert into auth_role(name) values(?)";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,role.getName());
                return ps;
            }
        },keyHolder);
        //获取id
        role.setId(keyHolder.getKey().longValue());
    }

    /**
     * 根据id删除角色
     * @param roleid    角色的id
     */
    public void deleteRoleById(Long roleid){
        String sql="delete from auth_role where id=?";
        jdbcTemplate.update(sql,roleid);
    }

    /**
     * 根据role对象来更新Role
     * @param role role对象
     */
    public void updateRole(Role role){
        String sql="update auth_role set name=? where id=?";
        jdbcTemplate.update(sql,role.getName(),role.getId());
    }

    /**
     *  根据ID集合批量查询
     */
    public List<Role> findRoleByIds(Collection<Long> ids){
        StringBuilder sb=new StringBuilder("select * from auth_role where id in (");
        //ids.forEach((id) -> sb.append(id).append(","));
        for(Long id : ids){
        	sb.append(id).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        logger.info("findByIds sql"+sb);
        try {
            return jdbcTemplate.query(sb.toString(),new RoleRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据分页查询Roles
     * @param page 页码
     * @param size 每页的大小
     * @return 返回Role的链表
     */
    public List<Role> findRolesByPages(int page,int size){
        String sql="select * from auth_role limit ?,?";
        try {
            return jdbcTemplate.query(sql,new Object[]{(page-1)*size,size},new RoleRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}

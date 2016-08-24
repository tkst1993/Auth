package com.run.auth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.run.auth.common.BaseDao;
import com.run.auth.entity.RoleFunction;

public class RoleFunctionDao extends BaseDao {
	
	private class RoleFunctionMapper implements RowMapper<RoleFunction>{

		public RoleFunction mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			RoleFunction roleFunction = new RoleFunction();
			roleFunction.setId(rs.getLong("id"));
			roleFunction.setRoleId(rs.getLong("role_id"));
			roleFunction.setStatus(rs.getInt("status"));
			return roleFunction;
		}
	}
	
	/**
     * 根据id查找RoleFunctions
     * @param id id
     * @return 返回值
     */
	public RoleFunction findRoleFunctionById(Long id){
		String sql = "select * from auth_role_function where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id} , new RoleFunctionMapper());
	}
	
	/**
     *  批量更新RoleFunctons对象
     * @param roleFunctions roleFunctions集合类
     */
	public void saveRoleFunction(Collection<RoleFunction> roleFunctions){
		String sql = "insert into auth_role_function(role_id,function_id,status)values(?,?,?)";
		List<Object[]>batchArgs = new ArrayList<Object[]>();
		for(RoleFunction rf : roleFunctions){
			Object[] objs = new Object[3];
			objs[0] = rf.getRoleId();
			objs[1] = rf.getFunctionId();
			objs[2] = rf.getStatus();
			
			batchArgs.add(objs);
		}
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	/**
     * 根据RoleId查找RoleFunctions实体类
     * @param roleId    角色id
     */
    public List<RoleFunction> findRoleFunctionsByRoleId(Long roleId){
        String sql="select * from auth_role_function where role_id = ?";
        try {
            return jdbcTemplate.query(sql,new Object[]{roleId},new RoleFunctionMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据角色roleid删除auth_role_function记录
     * @param roleid 角色id
     */
    public void deleteByRoleId(Long roleid){
        String sql="delete from auth_role_function where role_id=?";
        jdbcTemplate.update(sql,roleid);
    }

}

package com.run.auth.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseEntity {
	private Long id;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static <T extends BaseEntity> Map<Long,T> idEntityMap(Collection<T> list)
	{
		Map<Long,T>map = new HashMap<Long,T>();
		
		if(null == list||0==list.size()){
			//集合最好不要返回null值，避免空指针异常
			return map;
		}
		for(T entity : list){
			map.put(entity.getId(), entity);
		}
		return map;
	}

}

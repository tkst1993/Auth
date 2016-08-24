package com.run.auth.context;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.run.auth.entity.User;

public class LoginUserCache {
	
	private static Map<Long,User> cache = new HashMap<Long,User>();
	
	/**
	 * 
	 * @param user
	 * @param expire 单位秒
	 */
	public static void put(User user,long expire){
		long expireTime = Calendar.getInstance().getTime().getTime() + expire * 1000;
		LoginUser loginUser = new LoginUser();
		loginUser.setExpire(expireTime);
		loginUser.setUser(user);
		cache.put(user.getId(), user);
	}
	
	public static void remove(){
		cache.clear();
	}
	
	private static class LoginUser{
		private long expire;
		private User user;
		public long getExpire() {
			return expire;
		}
		public void setExpire(long expire) {
			this.expire = expire;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		
	}

}

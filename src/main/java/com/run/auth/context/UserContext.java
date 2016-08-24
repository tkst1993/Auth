package com.run.auth.context;

import com.run.auth.entity.User;

public class UserContext {
	private static ThreadLocal<UserContext> tl = new ThreadLocal<UserContext>();
	
	private User user;
	
	private UserContext(User user){
		this.user = user;
	}
	
	public static void setCurrent(User user){
		tl.set(new UserContext(user));
	}
	
	public static UserContext getCurrent(){
		return tl.get();
	}
	
	public User getUser(){
		return user;
	}

}

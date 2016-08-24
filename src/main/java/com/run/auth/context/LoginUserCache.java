package com.run.auth.context;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import sun.misc.BASE64Encoder;

import com.run.auth.dto.Accordion;
import com.run.auth.entity.User;

public class LoginUserCache {
	
	private static Map<String,User> cache = new HashMap<String,User>();
	
	private static Map<String,List<Accordion>> userAccordionMap = new HashMap<String,List<Accordion>>();
	
	public static void put(User user){
		cache.put(user.getName(), user);
		UserContext.setCurrent(user);
	}
	
	public static User get(String username){
		return cache.get(username);
	}
	
	public static void setCookie(User user){
		int expire = 1800;//秒
		String source = user.getName() + "$" +user.getPwd();
		String res = getBase64(source);
		Cookie cookie = new Cookie("auth",res);
		cookie.setMaxAge(expire);
		ResponseContext.getCurrent().addCookie(cookie);
	}
	
	public static void remove(String username){
		cache.remove(username);
		Cookie cookie = new Cookie("suth" , null);
		ResponseContext.getCurrent().addCookie(cookie);
		UserContext.setCurrent(null);
	}
	
	public static void setAccorions(String username ,List<Accordion> accordions){
		userAccordionMap.put(username, accordions);
	}
	
	public static List<Accordion> getAccorions(String username ){
		return userAccordionMap.get(username);
	}
	
	public static String getBase64(String str){
		byte[] b = null;
		String s = null;
		try{
			b = str.getBytes("utf-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		if(b != null){
			s = new BASE64Encoder().encode(b);
		}
		return s;
		
	}

}

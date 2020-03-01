package com.forum.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.dao.LoginTicketDao;
import com.forum.dao.UserDao;
import com.forum.entity.LoginTicket;
import com.forum.entity.User;
import com.forum.utils.ForumUtils;


@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	LoginTicketDao loginTicketDao;
	
	public Map<String, Object> register(String tel, String password) {
		Map<String, Object> map=new HashMap<String,Object>();
		if(tel==null || "".equals(tel)) {
			map.put("tel","手机号不能为空");
			return map;
		}
		
		if(tel.length()!=11) {
			map.put("tel","手机号不正确");
			return map;
		}
		
		if(password==null || "".equals(tel)) {
			map.put("password","手机号不能为空");
			return map;
		}
		
		if(password.length()<6) {
			map.put("password","密码不能少于6位");
			return map;
		}
		
		User user=userDao.getUserBytel(tel);
		if(user!=null) {
			map.put("tel", "手机号已经被注册");
			return map;
		}
		user=new User();
		String salt=ForumUtils.getRandomUUID().substring(0,6);
		user.setSalt(salt);
		user.setTel(tel);
		user.setPassword(ForumUtils.Md5(password+salt));
		userDao.addUser(user);
		String ticket=addTicket(tel);
		map.put("ticket", ticket);
		return map;
	}

	
	private String addTicket(String tel) {
		String ticket = ForumUtils.getRandomUUID();
		long expired = new Date().getTime()+1000*3600*24*10;
		LoginTicket loginTicke=new LoginTicket();
		loginTicke.setTel(tel);
		loginTicke.setTicket(ticket);
		loginTicke.setExpired(new Date(expired));
		
		loginTicketDao.loginTicketDao(loginTicke);
		return ticket;
	}
}

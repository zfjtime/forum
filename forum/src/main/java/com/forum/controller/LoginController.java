package com.forum.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forum.service.UserService;
import com.forum.utils.ForumUtils;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping(path="/register/",method=RequestMethod.POST)
	@ResponseBody
	public String register(@RequestParam("tel") String tel,@RequestParam("password") String password ,HttpServletResponse response) {
		try {
			Map<String,Object> map=userService.register(tel,password);
			if(map.containsKey("ticket")) {
				Cookie cookie=new Cookie("ticket", map.get("ticket").toString());
				cookie.setMaxAge(60*24*12);
				cookie.setPath("/");
				response.addCookie(cookie);
				return ForumUtils.toJSONString(200,map);
			}else {
				return ForumUtils.toJSONString(500,map);
			}
			
			
		} catch (Exception e) {
			return ForumUtils.toJSONString(500,"注册失败");
		}
		
	}
}

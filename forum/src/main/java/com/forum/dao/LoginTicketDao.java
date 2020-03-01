package com.forum.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.forum.entity.LoginTicket;
import com.forum.entity.User;

@Mapper
public interface LoginTicketDao {

	String TABLE_NAME="loginTicket";
	String INSERT_FILDS=" tel, ticket, status, expired ";
	String SELECT_FILDS="id,"+INSERT_FILDS;
	
	@Select({"select", SELECT_FILDS, "from ",TABLE_NAME, "where tel=#{tel}"})
	User getUserBytel(String tel);

	@Insert({"isnert into ",TABLE_NAME,"(",INSERT_FILDS,") values (#{tel},#{ticket},#{status},#{expired}) "})
	void loginTicketDao(LoginTicket loginTicket);
}

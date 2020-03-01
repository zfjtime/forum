package com.forum.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.forum.entity.User;

@Mapper
public interface UserDao {

	String TABLE_NAME="user";
	String INSERT_FILDS=" tel, password, salt, name, headLink ";
	String SELECT_FILDS="id,"+INSERT_FILDS;
	
	@Select({"select", SELECT_FILDS, "from ",TABLE_NAME, "where tel=#{tel}"})
	User getUserBytel(String tel);

	@Insert({"isnert into ",TABLE_NAME,"(",INSERT_FILDS,") values (#{tel},#{password},#{salt},#{name},#{headLink}"})
	void addUser(User user);
}

package cn.me.dao;

import java.util.List;

import cn.me.model.SUser;

public interface SUserDao {
	// 为了实现查询所有，要有一条查询语句
	List<SUser> getAllData();
	
	boolean isExist(String uname, String pwd);
}

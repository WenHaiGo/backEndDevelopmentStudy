package cn.me.service;
//要实现增删改查

import java.util.*;

public interface SUserService {
	//先实现获取所有
	List getAllData();
	
	boolean isExist(String uname,String pwd);
	
}

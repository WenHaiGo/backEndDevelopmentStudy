package cn.me.service.impl;

import java.util.*;

import cn.me.dao.impl.SUserDaoImpl;
import cn.me.model.SUser;
import cn.me.service.SUserService;

public class SUserServiceImpl implements SUserService{



	@Override
	public List<SUser> getAllData() {
		// TODO Auto-generated method stub
		SUserDaoImpl eImpl = new SUserDaoImpl();
		return eImpl.getAllData();
	}

	@Override
	public boolean isExist(String uanme,String pwd) {
		
		return new SUserDaoImpl().isExist(uanme,pwd);
	}

}

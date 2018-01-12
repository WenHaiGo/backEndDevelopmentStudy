package cn.me.dao.impl;

import java.util.*;

import java.sql.PreparedStatement;

import java.sql.Statement;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.me.dao.SUserDao;
import cn.me.dbutil.DBUtil;
import cn.me.model.SUser;
import cn.me.model.SUser;

public class SUserDaoImpl implements SUserDao {
	Connection conn = null;
	Statement stm = null;
	ResultSet rs = null;
	PreparedStatement pstm = null;

	@Override
	public List<SUser> getAllData() {

		String sql = "select * from shop_user";
		conn = DBUtil.getConn();
		List<SUser> list = new ArrayList<SUser>();
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				SUser e = new SUser();
				e.setUname(rs.getString("uname"));
				e.setAge(rs.getInt("age"));
				e.setSex(rs.getInt("sex"));
				list.add(e);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				DBUtil.DBclose(conn, stm, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * 0表示登陆失败 1表示登陆成功
	 */
	@Override
	public boolean isExist(String uname, String pwd) {

		conn = DBUtil.getConn();
		boolean flag = false;

		String sql = "select * from shop_user where uname=? and password=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uname);
			pstm.setString(2, pwd);
			//execute不好用？？？
			rs = pstm.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				DBUtil.DBclose(conn, pstm,rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

}

package cn.me.dao.impl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.me.dao.BookDao;
import cn.me.dbutil.DBUtil;
import cn.me.model.Book;

public class BookDaoImpl implements BookDao {
	Connection conn = null;
	Statement stm = null;
	ResultSet rs = null;
	PreparedStatement pstm;

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub

		String sql = "select * from book";
		List<Book> list = new ArrayList<>();

		try {
			conn = DBUtil.getConn();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setbName(rs.getString("BNAME"));
				book.setbImg(rs.getString("BIMG"));
				book.setbPrice(rs.getInt("BPRICE"));
				book.setBid(rs.getInt("bid"));
				list.add(book);
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

	@Override
	public int delBookById(int bid) {
		Connection conn = DBUtil.getConn();
		String sql = "delete from book where bid =?";
		int flag = 0;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bid);
			flag = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBUtil.DBclose(conn, pstm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public Book findById(int bid) {
		// TODO Auto-generated method stub

		String sql = "select * from book where bid =" + bid;
		conn = DBUtil.getConn();
		Book book = new Book();
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				book.setbImg(rs.getString("bimg"));
				book.setbName(rs.getString("bname"));
				book.setbPrice(rs.getInt("bprice"));
				book.setBid(rs.getInt("bid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				DBUtil.DBclose(conn, stm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return book;
	}

	@Override
	public int update(Book book) {
		String sql = " update book set bname = ?,bprice = ? ,bimg=?where bid = ?";
		conn = DBUtil.getConn();
		int flag = 0;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getbName());
			pstm.setInt(2, book.getbPrice());
			pstm.setString(3, book.getbImg());
			pstm.setInt(4, book.getBid());
			flag = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				DBUtil.DBclose(conn, pstm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public int add(Book book) {

		String sql = "insert into book(bname,bprice,bimg,bid) values(?,?,?,?)";
		conn = DBUtil.getConn();
		int flag = 0;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getbName());
			pstm.setInt(2, book.getbPrice());
			pstm.setString(3, book.getbImg());
			pstm.setInt(4, book.getBid());

			flag = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}

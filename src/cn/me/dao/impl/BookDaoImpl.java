package cn.me.dao.impl;

import java.sql.Statement;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.me.dao.BookDao;
import cn.me.dbutil.DBUtil;
import cn.me.model.Address;
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

	// 获取数据库一共有多少条数据 注意这里如果不传入参数的话是不知道将去获取哪一个表的数据
	@Override
	public int getTotalNum() {

		conn = DBUtil.getConn();
		String sql = "select count(*) from book";

		int sum = 0;
		try {

			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				sum = rs.getInt(1);
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
		return sum;
	}

	// 得到具体某一页显示多少条数据
	// 到底如何理解泛型？？？
	@Override
	public <T> List<T> getPageList(int pageNo, int pageSize) {

		String sql = "select * from book limit ?,?";
		List<T> blist = new ArrayList<>();
		conn = DBUtil.getConn();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNo - 1) * pageSize);
			pstm.setInt(2, pageSize);

			rs = pstm.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setbImg(rs.getString("bimg"));
				book.setbName(rs.getString("bname"));
				book.setbPrice(rs.getInt("bprice"));
				blist.add((T) book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				DBUtil.DBclose(conn, pstm, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return blist;
	}

	public int getFindBookSize(String selBook) {
		String sql = "select count(*) from book where bname like ?";
		conn = DBUtil.getConn();
		List<Book> bList = new ArrayList<>();
		int sum = 0;
		try {
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, "%" + selBook + "%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				sum = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBUtil.DBclose(conn, pstm, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return sum;
	}

	@Override
	public List<Book> getSelcBook(String selBook, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * from book where bname like ? limit ?,? ";
		conn = DBUtil.getConn();
		List<Book> bList = new ArrayList<>();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + selBook + "%");
			pstm.setInt(2, (pageNo - 1) * pageSize);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setbImg(rs.getString("bimg"));
				book.setbName(rs.getString("bname"));
				book.setbPrice(rs.getInt("bprice"));
				bList.add(book);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBUtil.DBclose(conn, pstm, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bList;
	}

	@Override
	public List<Address> getAddressByPid(int pid) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConn();
		String sql = "select * from region where pid = ?";
		List<Address> aList = new ArrayList<>();
		try {

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pid);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Address a = new Address();
				a.setId(rs.getInt("id"));
				a.setPid(rs.getInt("pid"));
				a.setName(rs.getString("name"));
				aList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBUtil.DBclose(conn, pstm, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return aList;
	}

}

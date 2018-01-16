package cn.me.dao;

import java.util.List;

import cn.me.model.Book;

public interface BookDao {
	/**
	 * 得到所有书籍
	 * 
	 * @return
	 */
	List<Book> getAllBook();

	int delBookById(int bid);

	/**
	 * 通过id查找图书信息
	 * 
	 * @param bid
	 * @return
	 */
	Book findById(int bid);

	/**
	 * 修改图书信息
	 * 
	 * @param book
	 * @return
	 */
	int update(Book book);

	/**
	 * 添加图书
	 * 
	 * @param book
	 * @return
	 */
	int add(Book book);

	/**
	 * 
	 * 得到总的数据条数 但是请注意如何判断是得到用户的总条数还是图书的总条数？？
	 * 
	 * @return
	 */
	int getTotalNum();
	
	<T> List<T> getPageList(int pageStart,int pageSize);
	
	
	//查询书籍  通过书名来查找
	List getSelcBook(String SelBook,int pageNo,int pageSize);
	
	//增加了级联菜单的实现：
	List getAddressByPid(int pid);
	

}

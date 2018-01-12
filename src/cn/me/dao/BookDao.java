package cn.me.dao;

import java.util.List;

import cn.me.model.Book;

public interface BookDao {
	List<Book> getAllBook();
	int delBookById(int bid);
	Book findById( int bid);
	int update(Book book);
	int add(Book book);
}

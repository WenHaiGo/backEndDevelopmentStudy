package cn.me.service;

import java.util.List;

import cn.me.model.Book;

public interface BookService {
	List<Book> getAllBook();

	int delBookById(int bid);

	Book findById(int bid);

	int update(Book book);
	
	int add(Book book);
}

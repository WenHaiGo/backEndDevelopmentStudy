package cn.me.service.impl;

import java.util.List;

import cn.me.dao.impl.BookDaoImpl;
import cn.me.model.Book;
import cn.me.service.BookService;

public class BookServiceImpl implements BookService {

	@Override
	public List<Book> getAllBook() {

		return new BookDaoImpl().getAllBook();
	}

	@Override
	public int delBookById(int bid) {
		// TODO Auto-generated method stub
		return new BookDaoImpl().delBookById(bid);
	}

	@Override
	public Book findById(int bid) {
		// TODO Auto-generated method stub
		return new BookDaoImpl().findById(bid);
	}

	@Override
	public int update(Book book) {
		// TODO Auto-generated method stub
		return new BookDaoImpl().update(book);
	}

	@Override
	public int add(Book book) {
		// TODO Auto-generated method stub
		return new BookDaoImpl().add(book);
	}
	
	

}

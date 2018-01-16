package cn.me.service;

import java.util.List;

import cn.me.dbutil.PageUtil;
import cn.me.model.Address;
import cn.me.model.Book;

public interface BookService {
	List<Book> getAllBook();

	int delBookById(int bid);

	Book findById(int bid);

	int update(Book book);

	int add(Book book);

	<T> PageUtil<T> getPage(int pageNo, int pageSize);

	PageUtil<Book> getFindBookByName(String selBook, int pageNo, int pageSize);
	List<Address> getAddressByPid(int pid);
}

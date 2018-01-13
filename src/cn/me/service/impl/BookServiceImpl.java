package cn.me.service.impl;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;

import cn.me.dao.impl.BookDaoImpl;
import cn.me.dbutil.PageUtil;
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

	@Override
	public <T> PageUtil<T> getPage(int pageNo, int pageSize) {

		// 现在有一个疑惑就是在哪里定义应该分多少页 每一页应该分多少数据？？
		PageUtil<T> pageUtil = new PageUtil<>();
		int totalNumData = new BookDaoImpl().getTotalNum();
		int totalPage = totalNumData%pageSize==0?totalNumData/pageSize:totalNumData/pageSize+1;
		pageUtil.setTotalPage(totalPage);
		if (pageNo <= 1) {
			pageNo = 1;
		}
		System.out.println(pageNo);
		if (pageNo >= totalPage) {
			pageNo = totalPage;
		}
		pageUtil.setList(new BookDaoImpl().getPageList(pageNo, pageSize));

		pageUtil.setPageNo(pageNo);
		pageUtil.setTotalPage(totalPage);
		System.out.println(totalPage);
		pageUtil.setPageSize(pageSize);
		return pageUtil;
	}

	@Override
	public int totalPage(int pageSize) {

		int totalPage = (int) Math.ceil(new BookDaoImpl().getTotalNum() / pageSize);
		System.out.println(totalPage);
		return totalPage;
	}

}

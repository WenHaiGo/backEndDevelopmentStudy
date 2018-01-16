package cn.me.service.impl;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;

import cn.me.dao.impl.BookDaoImpl;
import cn.me.dbutil.PageUtil;
import cn.me.model.Address;
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

	// 从servlet里面调用这个方法，servlet传入第几页和每一页显示多少条数据，
	@Override
	public <T> PageUtil<T> getPage(int pageNo, int pageSize) {

		// 现在有一个疑惑就是在哪里定义应该分多少页 每一页应该分多少数据？？
		PageUtil<T> pageUtil = new PageUtil<>();
		int totalNumData = new BookDaoImpl().getTotalNum();
		// 这里不要使用math.ceil这个方法，这个方法处理分数会出错。
		int totalPage = totalNumData % pageSize == 0 ? totalNumData / pageSize : totalNumData / pageSize + 1;
		pageUtil.setTotalPage(totalPage);
		if (pageNo <= 1) {
			pageNo = 1;
		}
		if (pageNo >= totalPage) {
			pageNo = totalPage;
		}

		// 这里体现了分页节省流量的特性
		//因为这里是调用的getpage方法传入的是起始页和每一页数据量，导致无法对于一个新的查询结果来分页，
		//到底应该怎么
		pageUtil.setList(new BookDaoImpl().getPageList(pageNo, pageSize));
		pageUtil.setPageNo(pageNo);
		pageUtil.setTotalPage(totalPage);
		pageUtil.setPageSize(pageSize);
		//传回一个完全可以使用的分页类 类里面包含了一切可以用到的数据。体现了整洁性
		return pageUtil;
	}

	
	//这样的话效率也非常低啊，每次都需要重新重数据库里面查询一下所有的数据再次分页还是？？这就关系到了数据库分页的原理，分页查询到底是
	//对于服务器好还是只是优化了用户体验，为用户节省了流量？？
	//这是由于
	@Override
	public PageUtil<Book> getFindBookByName(String selBook,int pageNo,int pageSize) {
		System.out.println(pageNo+"nihap");
		PageUtil<Book> pageUtil = new PageUtil<>();
		int totalNumData = new BookDaoImpl().getFindBookSize(selBook);
		// 这里不要使用math.ceil这个方法，这个方法处理分数会出错。
		int totalPage = totalNumData % pageSize == 0 ? totalNumData / pageSize : totalNumData / pageSize + 1;
		pageUtil.setTotalPage(totalPage);
		if (pageNo <= 1) {
			pageNo = 1;
		}
		if (pageNo >= totalPage) {
			pageNo =totalPage;
		}

		// 这里体现了分页节省流量的特性
		//因为这里是调用的getpage方法传入的是起始页和每一页数据量，导致无法对于一个新的查询结果来分页，
		//到底应该怎么
		System.out.println("pagenisadsadd"+pageNo);
		pageUtil.setList(new BookDaoImpl().getSelcBook(selBook,pageNo,pageSize));
		pageUtil.setPageNo(pageNo);
		pageUtil.setTotalPage(totalPage);
		pageUtil.setPageSize(pageSize);
		//传回一个完全可以使用的分页类 类里面包含了一切可以用到的数据。体现了整洁性
		return pageUtil;
	}
	
	@Override
	public List<Address> getAddressByPid(int pid)
	{
		
		return new BookDaoImpl().getAddressByPid(pid);
		
	}

}

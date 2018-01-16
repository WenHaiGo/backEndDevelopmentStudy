package cn.me.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.me.dbutil.PageUtil;
import cn.me.model.Book;
import cn.me.service.impl.BookServiceImpl;
import cn.me.service.impl.SUserServiceImpl;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("param") != null && request.getParameter("param").equals("main")) {
			mainPage(request, response);
		}
		// 因为点击添加到购物车会有一个带有参数的超链接指向这里
		else if (request.getParameter("param") != null && request.getParameter("param").equals("cart")) {
			System.out.println("进入购物车界面");
			// 判断session里面是否存在登陆信息
			cart(request, response);

		}
		// 执行删除操作，同时处理俩个输入参数
		else if (request.getParameter("param") != null && request.getParameter("param").equals("del")) {
			delBook(request, response);
		}

		else if (request.getParameter("param") != null && request.getParameter("param").equals("edit")) {
			editBook(request, response);
		}

		// 修改保存好的数据
		// 如何只是在service调用一个方法就可以？这里肯定是向后台传输一个book实例
		// 如何修改来着？？需要修改原来的值，而不是添加新的值 那就直接使用修改语句那条件是什么
		else if (request.getParameter("param") != null && request.getParameter("param").equals("update")) {
			updateBook(request, response);
		}

		else if (request.getParameter("param") != null && request.getParameter("param").equals("add")) {
			addBook(request, response);
		}

		else if (request.getParameter("param") != null && request.getParameter("param").equals("dividePage")) {
			dividePage(request, response);
		}

		else if (request.getParameter("param") != null && request.getParameter("param").equals("findBook")) {
			findBookByName(request, response);

		}

	}

	private void findBookByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		// 第一次进来pageNo还没有值呢 是不是首先要判断是否为空
		if (request.getParameter("pageNo") != null) {
			// 得到传进来的搜索参数  俩次一次是从dividepage里面传进来的，一次是从findbook 传进来的参数
			String findBookByName = request.getParameter("findBookByName");
			String pageNoStr = request.getParameter("pageNo");
			pageNo = Integer.parseInt(pageNoStr);
			// 分页还是得到一个list，然后将这个转发到showbook页面
			// 我明白了 是由servlet来处理具体分多少页，每页分多少数据
			PageUtil<Book> pageUtil = new BookServiceImpl().getFindBookByName(findBookByName, pageNo, 3);
			// 问题是pageutil里面的当前页数如何更新？？ 从哪里更新？？
			request.setAttribute("pageUtil", pageUtil);
			request.setAttribute("findBookByName", findBookByName);
			// 我觉得这里应该有一个改变工具类里面当前页数语句：
			request.getRequestDispatcher("/showFindBookPage.jsp").forward(request, response);
		} else {
			// 得到传进来的搜索参数
			String findBookByName = request.getParameter("findBookByName");
			PageUtil<Book> pageUtil = new BookServiceImpl().getFindBookByName(findBookByName, pageNo, 3);
			// 问题是pageutil里面的当前页数如何更新？？ 从哪里更新？？
			request.setAttribute("pageUtil", pageUtil);
			request.setAttribute("findBookByName", findBookByName);
			// 我觉得这里应该有一个改变工具类里面当前页数语句：
			System.out.println(pageUtil.getList().size());
			request.getRequestDispatcher("/showFindBookPage.jsp").forward(request, response);
		}
	}

	private void dividePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("进入分页");

		int pageNo = 1;
		// 第一次进来epageNo还没有值呢 是不是首先要判断是否为空
		// 这就是servlet的坏处，由于request必须先有请求才会有值
		if (request.getParameter("pageNo") != null) {
			String pageNoStr = request.getParameter("pageNo");
			pageNo = Integer.parseInt(pageNoStr);
			// 分页还是得到一个list，然后将这个转发到showbook页面
			// 我明白了 是由servlet来处理具体分多少页，每页分多少数据
			PageUtil<Book> pageUtil = new BookServiceImpl().getPage(pageNo, 3);
			// 问题是pageutil里面的当前页数如何更新？？ 从哪里更新？？
			request.setAttribute("pageUtil", pageUtil);
			// 我觉得这里应该有一个改变工具类里面当前页数语句：
			request.getRequestDispatcher("/divideBook.jsp").forward(request, response);
		} else {
			PageUtil<Book> pageUtil = new BookServiceImpl().getPage(pageNo, 3);
			// 问题是pageutil里面的当前页数如何更新？？ 从哪里更新？？
			request.setAttribute("pageUtil", pageUtil);
			// 我觉得这里应该有一个改变工具类里面当前页数语句：
			request.getRequestDispatcher("/divideBook.jsp").forward(request, response);
		}
	}

	private void mainPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入图书主界面");
		BookServiceImpl bsi = new BookServiceImpl();
		List<Book> list = new ArrayList<>();
		// 从service得到所有的书籍
		list = bsi.getAllBook();
		request.setAttribute("blist", list);
		// 利用转发向jsp页面将书籍列表传输过去
		request.getRequestDispatcher("/showBook.jsp").forward(request, response);
	}

	private void cart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object object = request.getSession().getAttribute("login");// 获取session
		if (object != null) {
			// 如果session里面有登录信息就转到主界面
			response.sendRedirect("BookServlet?param=dividePage");
			System.out.println(object);
		} else {
			// request.getRequestDispatcher("/login.jsp").forward(request, response);
			response.sendRedirect("login.jsp");
		}
	}

	private void editBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("editParam") != null) {

			int bid = Integer.parseInt(request.getParameter("editParam"));
			// 得到书籍id之后开始展示该书籍的信息这时候应该调到哪一个
			// 此时有数据了
			// 此时的dao里面应该有一个根据id获取所有值返回一个该id下的book实体然后将这个book实体通过request添加属性后
			// 传输到jsp页面上然后在提交到一个servlet的save里面
			Book book = new BookServiceImpl().findById(bid);
			// 如何判断这个request里面是否已经有了该名字的值？？
			// 如何判断session里面？
			// 如何判断application里面是否有的？？

			request.setAttribute("book", book);
			System.out.println(book.getbName());
			request.getRequestDispatcher("/editBook.jsp").forward(request, response);
		}
	}

	private void delBook(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("delParam") != null) {
			int bid = Integer.parseInt(request.getParameter("delParam"));
			new BookServiceImpl().delBookById(bid);
			try {
				response.sendRedirect("BookServlet?param=main");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("王文海");
		Book book = new Book();
		book.setBid(Integer.parseInt(request.getParameter("bId")));
		book.setbImg((request.getParameter("bImg")));
		book.setbName(request.getParameter("bName"));
		book.setbPrice(Integer.parseInt(request.getParameter("bPrice")));

		int flag = new BookServiceImpl().update(book);
		if (flag > 0) {
			try {
				response.sendRedirect("BookServlet?param=main");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("修改失败");
		}
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) {
		Book book = new Book();
		book.setBid(Integer.parseInt(request.getParameter("bId")));
		book.setbImg((request.getParameter("bImg")));
		book.setbName(request.getParameter("bName"));
		book.setbPrice(Integer.parseInt(request.getParameter("bPrice")));
		int flag;
		flag = new BookServiceImpl().add(book);
		if (flag > 0) {
			try {
				response.sendRedirect("BookServlet?param=main");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("添加失败");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

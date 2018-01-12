package cn.me.servlet;

import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.me.model.SUser;
import cn.me.service.SUserService;
import cn.me.service.impl.SUserServiceImpl;

/**
 * Servlet implementation class MySerVlet
 */
@WebServlet("/SUserServlet")
public class SUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 由于不能直接传递参数 只好判断user是否输入值了
		if (request.getParameter("user") != null) {
			String uname = request.getParameter("user");
			String pwd = request.getParameter("pwd");
			// 登陆成功返回true
			boolean flag = new SUserServiceImpl().isExist(uname, pwd);
			// 若登陆成功在session里面添加数据 并且返回到商品页面
			if (flag == true) {
				System.out.println("登陆成功");
				HttpSession session = request.getSession();// 获取session
				//session里面存放的就是用户SUser这是为了方便以后动态改变登陆注册时候好动态获取当前用户姓名
				//是不是只需要存放用户名就可以了
				SUser suser = new SUser();
				suser.setUname(uname);
				session.setAttribute("login", suser);
				// request.getRequestDispatcher("/ShowBook.jsp");// 转发的路径是怎么来着？
				//这里如果直接进入展示页面必然会引起空指针，因为showbook页面需要bookservlet提供list
				
				
				response.sendRedirect("BookServlet?param=main");
			}
			// 登陆失败重新登陆
			else {
				System.out.println("登陆失败");
				// 这里就不可以使用转发必须重定向为什么
				// request.getRequestDispatcher("/login.jsp");
				// 重新登陆，但是重新登陆出现问题，难道是自己不能重定向到自己吗
				response.sendRedirect("login.jsp");
			}
		}

		if (request.getParameter("param") != null && request.getParameter("param").equals("main")) {

			response.setContentType("text/html;charset=utf-8");
			SUserServiceImpl UImpl = new SUserServiceImpl();
			List<SUser> list = new ArrayList<>();
			list = UImpl.getAllData();
			request.setAttribute("list", list);
			// response.sendRedirect("/servletTest/show.jsp");
			request.getRequestDispatcher("/ShowUser.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

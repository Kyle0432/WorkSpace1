package com.kyle.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;














import com.kyle.bookstore.dao.impl.ShoppingCartImpl;
import com.kyle.bookstore.db.JDBCUtils;
import com.kyle.bookstore.domain.Book;
import com.kyle.bookstore.domain.ShoppingCartItem;
import com.kyle.bookstore.domain.User;
import com.kyle.bookstore.service.BookService;
import com.kyle.bookstore.service.UserService;
import com.kyle.bookstore.web.CriteriaBook;
import com.kyle.bookstore.web.LikeQueryBook;
import com.kyle.bookstore.web.Page;

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //实例化操作
	private BookService bookService = new BookService();
	private UserService userService = new UserService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用doPost方法
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //获取从jsp页面传来的参数值
		String str = request.getParameter("method");
		
		try {
			//表示可以调用你声明的方法的方法名
			Method method = getClass().getDeclaredMethod(str, HttpServletRequest.class,HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//1.分页操作
	protected void getBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参数值
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");

		/*如果minPrice和maxPrice没有传值过来,那就直接默认为0和Integer.MAX_VALUE
		所以也就是说从0到最大那显示的就是全部的信息·(相当于没有设置这样的范围)*/
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		//不允许只用一对try  catch 因为这样要是出了异常下面的就无法赋值了
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {}
		
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {}
		//进行初始操作
		CriteriaBook criteriaBook = new CriteriaBook(pageNo, minPrice, maxPrice);
		//在Service中获取Page对象
		Page<Book> page = bookService.getPage(criteriaBook);
		//保存page
		request.setAttribute("bookPage", page);
		//转发到book.jsp页面
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
		
	}
	//2.登录验证
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参数值
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String sql = "SELECT user_id,user_name,password,account_id FROM user WHERE user_name = ? AND password=?";
		
    	Connection connection = null;
		PreparedStatement  ps = null;
		ResultSet  rs = null;
		try{
			connection = JDBCUtils.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, passWord);
		    rs = ps.executeQuery();
			// 如果有下一条记录就执行登录成功 
			  if(rs.next()){
				  //*[注意]要用session保存对象
			       request.getSession().setAttribute("userName", userName);
			       getBooks(request, response);
		          // response.sendRedirect(request.getContextPath()+"/WEB-INF/pages/book.jsp");
			         request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
			  }else{
				  response.sendRedirect(request.getContextPath()+"/error-2.jsp");
			  }		
		 }catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
			JDBCUtils.release(rs, ps);
		}
	}
	/*[注意]
	 * List<Book>:表示到最后获取的是该集合里字段的所有值   
	 * 也就是所有的记录  到最后用到 forEach会把所有的记录遍历出来
	 * 
	 * Book对象:表示到最后获取的是Book对象按照条件所查询的字段的值
	 * 只有一条记录 到最后面只需 ${book.xxx}就可以获取这个字段的值了
	 */
	//3.模糊查询操作
	protected void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取参数值
		String remark = request.getParameter("remark");
		System.out.println(remark);
		//把请求参数封装为一个 LikeQueryBook 对象
		LikeQueryBook likeQueryBook = new LikeQueryBook(remark);
		
		//获取book对象
		Book book = bookService.getBook(likeQueryBook);
		
		//保存对象
		request.setAttribute("book", book);
		
		//进行转发操作
		request.getRequestDispatcher("/WEB-INF/pages/detailed.jsp").forward(request, response);
		
	}

	//4.登录权限控制
	protected void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//获取用户的登录信息
		String userName =  (String) session.getAttribute("userName");
		System.out.println(userName);
		if(userName != null){//表示成功登入后
			String book_id = request.getParameter("book_id");
			int id = -1;
			try {
				id = Integer.parseInt(book_id);
			} catch (Exception e) {}
		 //response.sendRedirect(request.getContextPath()+"/WEB-INF/pages/cart.jsp");
         request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
		}else{
		 response.sendRedirect(request.getContextPath()+"/pleaseLogin.jsp");
		}
		
	}
	//5,退出登入操作
	protected void secede(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//flase代表:不创建session对象,只是从request中获取
		HttpSession session = request.getSession(false);
		if(session == null){
			return;
		}
		session.removeAttribute("userName");
		//获取下分页的内容不获取会丢失分页的内容
		getBooks(request, response);

		//request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);;
		response.sendRedirect(request.getContextPath()+"/WEB-INF/pages/book.jsp");
	}
	//6，用户注册操作
	protected void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参数值
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String account_id = request.getParameter("account_id");
		//转型 
		int user_Id = 0;
		int account_Id = 0;
		try {
			user_Id = Integer.parseInt(user_id);
		} catch (NumberFormatException e) {}
		
	    try {
	    	account_Id = Integer.parseInt(account_id);
		} catch (NumberFormatException e) {}
		//进行初始操作
		User user = new User(user_Id,user_name,password,account_Id);
        if(user != null){
    		//插入操作
    		userService.register(user);
    		//注册成功
    		response.sendRedirect(request.getContextPath()+"/success.jsp");
        }
	}
}

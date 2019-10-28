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
    //ʵ��������
	private BookService bookService = new BookService();
	private UserService userService = new UserService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����doPost����
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //��ȡ��jspҳ�洫���Ĳ���ֵ
		String str = request.getParameter("method");
		
		try {
			//��ʾ���Ե����������ķ����ķ�����
			Method method = getClass().getDeclaredMethod(str, HttpServletRequest.class,HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//1.��ҳ����
	protected void getBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ����ֵ
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");

		/*���minPrice��maxPriceû�д�ֵ����,�Ǿ�ֱ��Ĭ��Ϊ0��Integer.MAX_VALUE
		����Ҳ����˵��0���������ʾ�ľ���ȫ������Ϣ��(�൱��û�����������ķ�Χ)*/
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		//������ֻ��һ��try  catch ��Ϊ����Ҫ�ǳ����쳣����ľ��޷���ֵ��
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {}
		
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {}
		//���г�ʼ����
		CriteriaBook criteriaBook = new CriteriaBook(pageNo, minPrice, maxPrice);
		//��Service�л�ȡPage����
		Page<Book> page = bookService.getPage(criteriaBook);
		//����page
		request.setAttribute("bookPage", page);
		//ת����book.jspҳ��
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
		
	}
	//2.��¼��֤
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ����ֵ
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
			// �������һ����¼��ִ�е�¼�ɹ� 
			  if(rs.next()){
				  //*[ע��]Ҫ��session�������
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
	/*[ע��]
	 * List<Book>:��ʾ������ȡ���Ǹü������ֶε�����ֵ   
	 * Ҳ�������еļ�¼  ������õ� forEach������еļ�¼��������
	 * 
	 * Book����:��ʾ������ȡ����Book��������������ѯ���ֶε�ֵ
	 * ֻ��һ����¼ �������ֻ�� ${book.xxx}�Ϳ��Ի�ȡ����ֶε�ֵ��
	 */
	//3.ģ����ѯ����
	protected void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡ����ֵ
		String remark = request.getParameter("remark");
		System.out.println(remark);
		//�����������װΪһ�� LikeQueryBook ����
		LikeQueryBook likeQueryBook = new LikeQueryBook(remark);
		
		//��ȡbook����
		Book book = bookService.getBook(likeQueryBook);
		
		//�������
		request.setAttribute("book", book);
		
		//����ת������
		request.getRequestDispatcher("/WEB-INF/pages/detailed.jsp").forward(request, response);
		
	}

	//4.��¼Ȩ�޿���
	protected void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//��ȡ�û��ĵ�¼��Ϣ
		String userName =  (String) session.getAttribute("userName");
		System.out.println(userName);
		if(userName != null){//��ʾ�ɹ������
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
	//5,�˳��������
	protected void secede(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//flase����:������session����,ֻ�Ǵ�request�л�ȡ
		HttpSession session = request.getSession(false);
		if(session == null){
			return;
		}
		session.removeAttribute("userName");
		//��ȡ�·�ҳ�����ݲ���ȡ�ᶪʧ��ҳ������
		getBooks(request, response);

		//request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);;
		response.sendRedirect(request.getContextPath()+"/WEB-INF/pages/book.jsp");
	}
	//6���û�ע�����
	protected void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ����ֵ
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String account_id = request.getParameter("account_id");
		//ת�� 
		int user_Id = 0;
		int account_Id = 0;
		try {
			user_Id = Integer.parseInt(user_id);
		} catch (NumberFormatException e) {}
		
	    try {
	    	account_Id = Integer.parseInt(account_id);
		} catch (NumberFormatException e) {}
		//���г�ʼ����
		User user = new User(user_Id,user_name,password,account_Id);
        if(user != null){
    		//�������
    		userService.register(user);
    		//ע��ɹ�
    		response.sendRedirect(request.getContextPath()+"/success.jsp");
        }
	}
}

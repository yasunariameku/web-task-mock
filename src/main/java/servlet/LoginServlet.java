package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginAA")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
			
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		
		//入力値を取得
		String login_id = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		
		//System.out.println(login_id);
		
		List<User> result = null;
		
		String name = null;
		String idMatch = null;
		String passMatch = null;
	
		
		HttpSession session = request.getSession(true);
	
		
		
		if (login_id == "") {
			request.setAttribute("id", "IDは必須です");
		}
		
		if (pass == "") {
			request.setAttribute("pass", "PASSは必須です");
		}
		
		
		
		if (login_id.equals("") || pass.equals("")) {
	        request.getRequestDispatcher("/index.jsp").forward(request, response);
	        
		}else {
			result = (List<User>) new LoginService().findByUser(login_id, pass);
			
			//System.out.println(result.get(0).getName());
			
			//System.out.println(result.size());
			
			if (result.size() == 0) {
				request.setAttribute("msg", "idまたはpassが間違っています");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}else {
				//System.out.println(result.get(0));
				
				name = result.get(0).getName();
				session.setAttribute("name", name);
				request.getRequestDispatcher("menu.jsp").forward(request, response);
			}
		
	     
	        request.getRequestDispatcher("menu.jsp").forward(request, response);

		}
		
	}

}

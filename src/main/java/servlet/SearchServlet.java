package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.SearchService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		String searchWord = request.getParameter("search");
		
		HttpSession session = request.getSession(true);
		session.getAttribute("searchResult");
		session.getAttribute("result");
		
		List<Product> result = null;
		
		if (searchWord.equals("")) {
			//空文字の時は、全レコードの取得。
			//System.out.println("空文字です");
			
			result = new SearchService().findAll();
			
			request.setAttribute("result", result);
			
			session.setAttribute("result", result);
			
			session.setAttribute("searchResult", result.size());
			//System.out.println(session.getAttribute("searchResult"));
			
			request.getRequestDispatcher("menu.jsp").forward(request, response);
			
		}else if (!(searchWord.equals(""))) {
			//何かしら入力された値があるなら、商品名、カテゴリー名に部分一致したレコードを取得する。
			//System.out.println(searchStr);
			
			result = new SearchService().find(searchWord);
			
			session.setAttribute("result", result);
			
			request.setAttribute("result", result);
			
			session.setAttribute("searchResult", result.size());
			
			request.getRequestDispatcher("menu.jsp").forward(request, response);
		}

			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}

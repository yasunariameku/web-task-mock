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

/**
 * Servlet implementation class SortServlet
 */
@WebServlet("/SortServlet")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sortKey = request.getParameter("sort");
		
		HttpSession session = request.getSession(true);
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) session.getAttribute("result");
		
		
		if ("id".equals(sortKey)) {
			list.sort((p1, p2) -> p1.getId() >= p2.getId() ? 1 : -1);
		}else if ("category".equals(sortKey)) {
			list.sort((p1, p2) -> p1.getCategory().compareTo(p2.getCategory()));
		}else if ("price_asc".equals(sortKey)) {
			list.sort((p1, p2) -> p1.getPrice() >= p2.getPrice() ? -1 : 1);
		}else if ("price_desc".equals(sortKey)) {
			list.sort((p1, p2) -> p1.getPrice() >= p2.getPrice() ? 1 : -1);
		}

		session.setAttribute("result", list);
		
		request.getRequestDispatcher("menu.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}

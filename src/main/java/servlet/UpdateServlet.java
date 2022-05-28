package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.UpdateService;
import util.ParamUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String result_idStr = request.getParameter("id");
		int result_id = Integer.parseInt(result_idStr);
		
		//System.out.println(result_id);
		
		HttpSession session = request.getSession();
		
		Product productDetail = null;

		productDetail = (Product) session.getAttribute("result");
		
		request.setAttribute("productDetail",productDetail);
		

		
		request.getRequestDispatcher("updateInput.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
	
		
		Product productDetail = null;
		
		productDetail = (Product) session.getAttribute("result");
		
		System.out.println("ここはもともと入っている入力値が出力されるはず");
		System.out.println(productDetail.getId());
		System.out.println(productDetail.getProduct_id());
		System.out.println(productDetail.getName());
		System.out.println(productDetail.getCategory());
		System.out.println(productDetail.getPrice());
		System.out.println("ーーーーーーーーーーーーーーーーーーーー");
		
		String productIdStr = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String priceStr = request.getParameter("price");
		String categoryStr = request.getParameter("category");
		String description = request.getParameter("description");
		
		System.out.println("ここはもともとの値がはいるのか？それともnullが入るのか？");
		//productIdStr、productName、priceStr、descriptionは空文字が入っている。
		System.out.println(productIdStr);
		System.out.println(productName);
		System.out.println(priceStr);
		System.out.println(categoryStr);
		System.out.println(description);
		System.out.println("ーーーーーーーーーーーーーーーーーーーー");
		
		Integer productId = null;
		Integer price = null;
		Integer category_id = null;
		
		String result = null;
		
		if(productIdStr.equals("")) {
			request.setAttribute("id_msg", "商品IDは必須です");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);

		}else {
			productId = Integer.parseInt(productIdStr);
			System.out.println(productId);
			
			if (productId == productDetail.getProduct_id()) {
				request.setAttribute("id_msg", "商品IDが一致していません");
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);

			}
		}
		
		if(priceStr.equals("")) {
			request.setAttribute("price_msg", "単価は必須です");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);

		}else {
			price = Integer.parseInt(priceStr);
		}
		
		if(productName.equals("")) {
			request.setAttribute("name_msg", "商品名は必須です");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);

		}
		
		if(!(ParamUtil.isNullOrEmpty(categoryStr)) == true ) {
			category_id = Integer.parseInt(categoryStr);
		}
		
		
		
		Product p = new Product(productId, category_id, productName, price, description);
		
		result = new UpdateService().update(p);
		
		request.setAttribute("msg",result);
		
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}

}

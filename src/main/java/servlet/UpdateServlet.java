package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		Integer id = null;
		
		//更新前の値
		productDetail = (Product) session.getAttribute("result");
		id = productDetail.getId();
		
		
		//更新したい情報の入力値
		String productIdStr = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String priceStr = request.getParameter("price");
		String categoryStr = request.getParameter("category");
		String description = request.getParameter("description");
		
		//入力値を数値型に変換するための変数
		Integer productId = null;
		Integer price = null;
		Integer category_id = null;
		
		//更新処理をした時の結果を入れるための変数
		String result = null;
		
		//商品IDが未入力だったとき
		if(productIdStr.equals("")) {
			request.setAttribute("id_msg", "商品IDは必須です");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);

		}else {
			productId = Integer.parseInt(productIdStr);
		}
		
		//単価が未入力だったとき
		if(priceStr.equals("")) {
			request.setAttribute("price_msg", "単価は必須です");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);

		}else {
			price = Integer.parseInt(priceStr);
		}
		
		//商品名が未入力だったとき
		if(productName.equals("")) {
			request.setAttribute("name_msg", "商品名は必須です");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
		}
		
		if(!(ParamUtil.isNullOrEmpty(categoryStr)) == true ) {
			category_id = Integer.parseInt(categoryStr);
		}
		
		//System.out.println(productId);
		
		//Productのインスタンスを生成
		Product p = new Product(productDetail.getId(),productId, productName, price, category_id,  description);
		
		//idが違う商品で商品IDが重複していないかどうかをチェックする
		List<Product> list = new ArrayList<>();
		
		list = new UpdateService().check(id, productId);
		
		
		if(!(list.size() == 0)) {
			request.setAttribute("msg", "他の商品IDと重複しています");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			

			productDetail = (Product) session.getAttribute("result");
			
			request.setAttribute("productDetail",productDetail);
		}
		
		//更新の処理
		new UpdateService().update(p, id);
		
		productDetail = new UpdateService().update(p, id);
		
		session.setAttribute("result", productDetail);
		
		request.setAttribute("result",productDetail);
		
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}

}

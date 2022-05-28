package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.RegisterService;
import util.ParamUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		
		// 入力情報を取得
		String productIdStr = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String priceStr = request.getParameter("price");
		String category_idStr = request.getParameter("roleId");
		String description = request.getParameter("description");
		
		
		String result = null;
		
		Integer productId = null;
		Integer price = null;
		Integer category_id = null;
		
		//入力情報の判定
		if(ParamUtil.isNullOrEmpty(productIdStr) == true ) {
			request.setAttribute("id_msg", "IDは必須です");
		} else {
			productId = ParamUtil.checkAndParseInt(productIdStr);
		}
		
		if(ParamUtil.isNullOrEmpty(productName) == true ) {
			request.setAttribute("name_msg", "商品名は必須です");
		} 
		
		if(ParamUtil.isNullOrEmpty(priceStr) == true ) {
			request.setAttribute("price_msg", "単価は必須です");
		}else {
			price = ParamUtil.checkAndParseInt(priceStr);
		}
		
		if(!(ParamUtil.isNullOrEmpty(category_idStr) == true)) {
			category_id = ParamUtil.checkAndParseInt(category_idStr);
		}
		
		
		if(ParamUtil.isNullOrEmpty(productIdStr) == true || ParamUtil.isNullOrEmpty(productName) == true || ParamUtil.isNullOrEmpty(priceStr) == true) {
			request.setAttribute("msg", "");
		}
		
		//入力された商品IDがすでにある商品IDだった場合
		List<Product> checkProductId = null; 
		checkProductId  = new RegisterService().checkProductId(productId);
		
		if (!(checkProductId == null)) {
			request.setAttribute("msg", "商品IDが重複しています");
			
		}else {
			//入力された情報をもとにproductのインスタンスを生成。
			Product p = new Product(productId, category_id, productName, price, description);
			
			//ここでproductIdは取ってこれている。
			//System.out.println(p.getCategory_id());
			
			//ProductServiceのインスタンスを生成して、registメソッド()呼び出す
			result = new RegisterService().regist(p);
			
			//System.out.println(result);
			
			request.setAttribute("msg", result);
		}
		
		request.getRequestDispatcher("insert.jsp").forward(request, response);
		
	}

}

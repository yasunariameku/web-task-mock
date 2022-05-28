package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class UpdateService {
	//引数なしコンストラクタ
		public UpdateService() {
		}
		
		List<Product> list = new ArrayList<>();
		
		Product productOne = new Product();
		
		public List<Product>  check(Integer id, Integer product_id){
			try (Connection conn = DbUtil.getConnection()) {
	        	
	        	//UserDaoのインスタンスを生成
	        	ProductDao productDao = new ProductDao(conn);
	        	//ProductDaoのインスタンスメソッドのfindAllメソッドを呼び出す
	        	list = productDao.check(id, product_id);
	        	
	            return list;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
		
		}
		
		String result = null;
		
		public Product update(Product product, Integer id) {
			try (Connection conn = DbUtil.getConnection()) {
	        	
	        	//UserDaoのインスタンスを生成
	        	ProductDao productDao = new ProductDao(conn);
	        	//ProductDaoのインスタンスメソッドのfindAllメソッドを呼び出す
	        	productOne =  productDao.update(product, id);
	        	
	            return productOne;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
		}
}

package service;

import java.sql.Connection;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class RegisterService {
	
	//引数なしコンストラクタ
	public RegisterService() {
	}
	
	String result = null;
	
	public String regist(Product product) {
		try (Connection conn = DbUtil.getConnection()) {
        	
            //System.out.println(login_id);
        	//↑で引数の値を受け取れている。

        	
        	//Product p = new Product();
        	//UserDaoのインスタンスを生成
        	ProductDao productDao = new ProductDao(conn);
        	//ProductDaoのインスタンスメソッドのfindAllメソッドを呼び出す
        	result =  productDao.regist(product);
        	
        	//System.out.println(user.get(0).getName());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}

}

package service;

import java.sql.Connection;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class SearchService {
	
	//引数なしコンストラクタ
	public SearchService() {
	}
	
	List<Product> product = null;
	
	public List<Product> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
        	
            //System.out.println(login_id);
        	//↑で引数の値を受け取れている。

        	
        	Product p = new Product();
        	//UserDaoのインスタンスを生成
        	ProductDao productDao = new ProductDao(conn);
        	//ProductDaoのインスタンスメソッドのfindByIdメソッドを呼び出す
        	product = productDao.findAll(p);
        	
        	//System.out.println(user.get(0).getName());

            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
	
	
	public List<Product> find() {
        try (Connection conn = DbUtil.getConnection()) {
        	
            //System.out.println(login_id);
        	//↑で引数の値を受け取れている。

        	
        	Product p = new Product();
        	//UserDaoのインスタンスを生成
        	ProductDao productDao = new ProductDao(conn);
        	//ProductDaoのインスタンスメソッドのfindByIdメソッドを呼び出す
        	product = productDao.findAll(p);
        	
        	//System.out.println(user.get(0).getName());

            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

}

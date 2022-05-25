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
	
	
	
	//全件取得
	public List<Product> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
        	
        	//ProductDaoのインスタンスを生成
        	ProductDao productDao = new ProductDao(conn);
        	//ProductDaoのインスタンスメソッドのfindAllメソッドを呼び出す
        	product = productDao.findAll();
        	
            return product;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
	
	//条件を指定しての取得
	public List<Product> find(String word) {
        try (Connection conn = DbUtil.getConnection()) {
        	
        	//ProductDaoのインスタンスを生成
        	ProductDao productDao = new ProductDao(conn);
        	//ProductDaoのインスタンスメソッドのfindByIdメソッドを呼び出す
        	product = productDao.find(word);
        	
            return product;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
	}
	
	
	//商品詳細の取得
	public List<Product> findById(Integer id) {
        try (Connection conn = DbUtil.getConnection()) {
        	
        	//ProductDaoのインスタンスを生成
        	ProductDao productDao = new ProductDao(conn);
        	//ProductDaoのインスタンスメソッドのfindByIdメソッドを呼び出す
        	product = productDao.findById(id);
        	
            return product;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
	}
	
	//

}

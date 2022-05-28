package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class DeleteService {
	
	public DeleteService() {
	}
	
	List<Product> result = new ArrayList<>();
	
	public List<Product> delete(Integer id) {
		try (Connection conn = DbUtil.getConnection()) {
        	
        	//ProductDaoのインスタンスを生成
        	ProductDao productDao = new ProductDao(conn);
        	//ProductDaoのインスタンスメソッドのdeleteメソッドを呼び出す
        	result =  productDao.delete(id);
        	
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}
}

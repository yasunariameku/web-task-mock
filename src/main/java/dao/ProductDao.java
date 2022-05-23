package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {
	
	private Connection connection;
		
	public ProductDao(Connection connection) {
	    this.connection = connection;
	}
	
	
	private static final String SQL_SELECT_ALL = "SELECT * FROM users";
	
	List<Product> list = new ArrayList<>();
	
	
	public List<Product> findAll(Product p) {
		
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)){
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),rs.getInt("product_id"), rs.getString("name"),rs.getInt("price"), rs.getString("description")));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    	
        return list;
    }
	
	// 入力したidをもとに、レコードを取得する。

		private static final String SQL_SELECT_BY_PRODUCT_ID = "SELECT * FROM products WHERE product_id = ? ORDER BY product_id;";


	    public Product findByProductId(Integer product_id) {
	    	
	    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_PRODUCT_ID)) {
	            stmt.setInt(1, product_id);
	            
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	            	list.add(new Product(rs.getInt("id"),rs.getInt("product_id"), rs.getString("name"),rs.getInt("price"), rs.getString("description")));
	            }
	            
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	    	
	    	return null;
	        
	    }

	
	

}






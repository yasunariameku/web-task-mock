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
	
	//DBから取得した値を入れるためのリスト（箱）
	List<Product> list = new ArrayList<>();
	
	//全件取得
	private static final String SQL_SELECT_ALL = "SELECT p.id,p.product_id, p.name, p.price, c.name AS category, p.description FROM products p INNER JOIN categories c ON p.category_id = c.id ORDER BY p.product_id";
	
	
	public List<Product> findAll() {
		
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)){
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"), rs.getString("description")));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    	
        return list;
    }
	
	// 入力した文字列をもとに、レコードを取得する。
	
	static String  searchWord = null;

	
	private static final String SQL_SELECT = "SELECT p.id, p.product_id, p.name, p.price, c.name AS category, p.description FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE ";
	
	private static final String ORDER = "ORDER BY p.product_id;";

    public List<Product> find(String word) {
    	
    	searchWord = word;
    	
    	String WHERE = ("(p.name LIKE '%" + searchWord + "%') OR (c.name LIKE '%" + searchWord + "%') ");
    	//WHERE p.name LIKE  '%品%' OR c.name LIKE '%品%' ORDER BY p.product_id;
    	
    	String SQL_FIND = SQL_SELECT + WHERE + ORDER;
    	
    	System.out.println(SQL_FIND);
    	
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_FIND)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            	list.add(new Product(rs.getInt("id"), rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"), rs.getString("description")));
            }
            
            //System.out.println(list.size());
            
            return list;
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    	
    	return null;
        
    }
    
    private static final String SQL_SELECT_BY_PRODUCT_ID = "SELECT p.id, p.product_id, p.name, p.price, c.name AS category, p.description FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE p.id = ?";


    public List<Product> findById(Integer id) {
    	
    	
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_PRODUCT_ID)) {
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"), rs.getString("description")));
            }
            
            return list;
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    	
    	return null;
        
    }
    
    //新規登録
    
    private static final String SQL_REGIST = "INSERT INTO products (product_id, category_id, name, price, description) VALUES (?, ?, ?, ?, ?);";
    
    public String regist(Product product ) {
    	
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_REGIST)) {
    		//System.out.println(product.getProduct_id());
    		stmt.setInt(1, product.getProduct_id());
    		stmt.setInt(2, product.getCategory_id());
    		stmt.setString(3, product.getName());
    		stmt.setInt(4, product.getPrice());
            stmt.setString(5, product.getDescription());
    		
            stmt.executeUpdate();
            
            return "成功しました。";
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    	
    	
    	return null;
    }
    
    //登録情報の更新
    private static final String SQL_UPDATE = "UPDATE products SET product_name = ?, price = ? WHERE product_id = ?";
    
    
	public void update(Product product) {
		try(PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
			stmt.setString(1, product.getProduct_name());
			stmt.setInt(2, product.getPrice());
			stmt.setInt(3, product.getProduct_id());
			
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
    
    
    //登録情報の削除
	private static final String SQL_DELETE = "DELETE FROM products WHERE product_name = ?";
    
    
	public void delete(String product_name) {
		try(PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)){
			stmt.setString(1, product_name);
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

    
}






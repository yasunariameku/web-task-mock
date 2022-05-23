package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao {
	
	
	private Connection connection;
	
	public UserDao(Connection connection) {
	    this.connection = connection;
	}
	
	private static final String SQL_SELECT_WHERE_USER_ID = "SELECT * FROM users WHERE login_id = ? AND password = ?";
	
	
	public List<User> findByUser(User p) {
		
			List<User> list = new ArrayList<>();
			
			//System.out.println(product_id);
	    	
	    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_USER_ID)) {
	            stmt.setString(1, p.getLogin_id());
	            stmt.setString(2, p.getPassword());

	            ResultSet rs = stmt.executeQuery();
	            
	            //System.out.println(p.getLogin_id());
	            //System.out.println(p.getPassword());

	
	            while (rs.next()) {
	            	//System.out.println(p.getLogin_id());
	            	//System.out.println(p.getPassword());
	                list.add(new User(rs.getInt("id"),rs.getString("login_id"), rs.getString("password"),rs.getString("name"), rs.getInt("role")));
	                //System.out.println(list.get(0).getName());
	                
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    	
	        return list;
	    }
	
}

package service;

import java.sql.Connection;
import java.util.List;

import dao.UserDao;
import entity.User;
import util.DbUtil;

public class LoginService {
	
	//引数なしコンストラクタ
	public LoginService() {
	}
	
	List<User> user = null;
	
	
	public List<User> findByUser(String login_id, String pass) {
        try (Connection conn = DbUtil.getConnection()) {
        	
            //System.out.println(login_id);
        	//↑で引数の値を受け取れている。

        	
        	User u = new User(null,login_id, pass, null,null);
        	//UserDaoのインスタンスを生成
        	UserDao userDao = new UserDao(conn);
        	//ProductDaoのインスタンスメソッドのfindByIdメソッドを呼び出す
        	user= userDao.findByUser(u);
        	
        	//System.out.println(user.get(0).getName());

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}

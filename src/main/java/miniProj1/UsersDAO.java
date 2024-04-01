package miniProj1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miniProj1.UsersVO;
import miniProj1.UsersDAO;

public class UsersDAO {

	 private static Connection conn = null;
	    private static PreparedStatement userListPstmt = null;
	    private static PreparedStatement userInsertPstmt = null;
	    private static PreparedStatement userDeletePstmt = null;
	    private static PreparedStatement userDetailPstmt = null;

	    private static PreparedStatement userUpdatePstmt = null;
	    private static PreparedStatement userDeleteAllPstmt = null;

	    private static PreparedStatement userValidationIdPstmt = null;
	    private static PreparedStatement userValidationPasswordPstmt = null;
	    
	    static {

	        try {
	            // 1. JDBC 드라이버 로딩
	            Class.forName("org.mariadb.jdbc.Driver");
	            // 2. DB 연결
	            conn = DriverManager.getConnection(
	                    "jdbc:mariadb://localhost:3306/miniproj_db",
	                    "bituser", //계정이름
	                    "1004" //계정비밀번호
	            );
	            
	            System.out.println("연결 성공");
	            conn.setAutoCommit(false);
	            
	            userListPstmt = conn.prepareStatement("select * from tb_users");
	            userInsertPstmt = conn.prepareStatement("insert into vusers (userid, name, password, gender, email,phone) values (?, ?, ?, ?,?,?)");
	            userDetailPstmt = conn.prepareStatement("select * from tb_users where userid=?");
	            userValidationIdPstmt = conn.prepareStatement("select userid from users where userid=?  ");
	            userValidationPasswordPstmt  = conn.prepareStatement("select password from tb_users whrere password=? ");
	            //delete 가 되지 않았던 이유: ? 개수에 맞춰서 setString() 을 해주어야 한다.
	            userDeletePstmt = conn.prepareStatement("delete from tb_users where userid=?");
	            userDeleteAllPstmt = conn.prepareStatement("delete from tb_users");
	            userUpdatePstmt = conn.prepareStatement("update tb_users set name=?, password=?,gender=?, email=?, phone=? where userid=?");
	          
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();

	        }
	    }
	    
	    public List<UsersVO> list() {
	        List<UsersVO> list = new ArrayList<>();
	        try {
	            ResultSet rs = UsersDAO.userListPstmt.executeQuery();
	            while (rs.next()) {
	                UsersVO tb_users = new UsersVO(rs.getString("userid")
	                        , rs.getString("password")
	                        , rs.getString("name")
	                        , rs.getString("gender")
	                        , rs.getString("email")
	                        , rs.getString("phone"));
	                
	                list.add(tb_users);
	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	    
	    public int insert(UsersVO usersVO){
	        int updated = 0;
	        try{
	            userInsertPstmt.setString(1, usersVO.getUserid());
	            userInsertPstmt.setString(2, usersVO.getName());
	            userInsertPstmt.setString(3, usersVO.getPassword());
	            userInsertPstmt.setString(4, usersVO.getGender());
	            userInsertPstmt.setString(5, usersVO.getEmail());
	            userInsertPstmt.setString(6, usersVO.getPhone());
	            updated = userInsertPstmt.executeUpdate();
	            conn.commit();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return updated;
	    }
	    
	    public UsersVO read(String userid) {

	        UsersVO tb_users = null;
	        try {
	            userDetailPstmt.setString(1, userid);

	            ResultSet rs = userDetailPstmt.executeQuery();
	            if (rs.next()) {
	            	tb_users = new UsersVO(rs.getString("userid")
	                        , rs.getString("password")
	                        , rs.getString("name")
	                        , rs.getString("gender")
	                        , rs.getString("email")
	                        , rs.getString("phone"));
	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return tb_users;
	    }
	    
	    public int update(UsersVO usersVO) {
	        int updated = 0;
	        try {
	            userUpdatePstmt.setString(1, usersVO.getName());
	            userUpdatePstmt.setString(2, usersVO.getPassword());
	            userUpdatePstmt.setString(3, usersVO.getGender());
	            userUpdatePstmt.setString(4, usersVO.getEmail());
	            userUpdatePstmt.setString(5, usersVO.getPhone());
	            userUpdatePstmt.setString(6, usersVO.getUserid());
	            updated = userUpdatePstmt.executeUpdate();
	            conn.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return updated;
	        
	        
	
}
	    public int delete(UsersVO usersVO) {
	        int updated = 0;

	        try {
	            userDeletePstmt.setString(1, usersVO.getUserid());
	            updated = userDeletePstmt.executeUpdate();
	            conn.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return updated;
	    }

	    public int clear() {
	        int updated = 0;
	        try {
	            updated = userDeleteAllPstmt.executeUpdate();
	            conn.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return updated;
	    }

	    public boolean validationId(String userid){
	        boolean result = false;
	        try {
	            userValidationIdPstmt.setString(1, userid);
	            ResultSet rs = userValidationIdPstmt.executeQuery();
	            if (rs.next()) {
	                result = true;
	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	    }
	        return result;
	    }
	    public boolean  validationPassword(String userpassword){
	        boolean result = false;
	        try {
	            userValidationPasswordPstmt.setString(1, userpassword);
	            ResultSet rs = userValidationPasswordPstmt.executeQuery();
	            if (rs.next()) {
	                result = true;
	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

		public int delete(String userid) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

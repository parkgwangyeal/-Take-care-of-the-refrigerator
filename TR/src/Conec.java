
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//-----------------------------------------------------------------------------------
/**
@class Conec.java
@brief 디비에 접속히거 접속을 끈고 하기위한 클래스

*///----------------------------------------------------------------------------------



public class Conec {
	public Connection conn;
	public PreparedStatement pstmt;
	public ResultSet rs;
	public Statement stmt = null;
	//-----------------------------------------------------------------------------------
	/**
	@brief connection클래스의  함수- 디비에 연결하게해준다
	@param DB_Name 접속기위한 DB테이블이름을 받아온다.
	*///----------------------------------------------------------------------------------

	public void connection(String DB_Name) {
    	try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+DB_Name+".db");
			conn.setAutoCommit(false);//뭐지
		}catch(ClassNotFoundException e){
			
		}catch(SQLException e){
			
		}
    }
   
    // db 해제
	//-----------------------------------------------------------------------------------
	/**
	@brief connection클래스의  함수- 디비와의 연결을 끈어준다
	*///----------------------------------------------------------------------------------

    public void disconnection() {
    	try 
    	{
    		if(pstmt != null) pstmt.close();
                      
    		if(rs != null) rs.close();
                      
    		if(conn != null) conn.close();
             
    	} catch (SQLException e) {
    		
    	}
    }
}

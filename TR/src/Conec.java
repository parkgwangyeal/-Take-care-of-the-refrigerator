
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//-----------------------------------------------------------------------------------
/**
@class Conec.java
@brief ��� �������� ������ ���� �ϱ����� Ŭ����

*///----------------------------------------------------------------------------------



public class Conec {
	public Connection conn;
	public PreparedStatement pstmt;
	public ResultSet rs;
	public Statement stmt = null;
	//-----------------------------------------------------------------------------------
	/**
	@brief connectionŬ������  �Լ�- ��� �����ϰ����ش�
	@param DB_Name ���ӱ����� DB���̺��̸��� �޾ƿ´�.
	*///----------------------------------------------------------------------------------

	public void connection(String DB_Name) {
    	try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+DB_Name+".db");
			conn.setAutoCommit(false);//����
		}catch(ClassNotFoundException e){
			
		}catch(SQLException e){
			
		}
    }
   
    // db ����
	//-----------------------------------------------------------------------------------
	/**
	@brief connectionŬ������  �Լ�- ������ ������ �����ش�
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

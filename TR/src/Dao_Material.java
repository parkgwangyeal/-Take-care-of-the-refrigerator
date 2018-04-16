
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

//-----------------------------------------------------------------------------------
/**
@class Dao_Material.java
@brief Coon클래스를 상속받아서 디비를 사용하고 재료DBtable에 관련된 쿼리문을 가지고 있는 클래스

*///----------------------------------------------------------------------------------


public class Dao_Material extends Conec{
	
	private Pop p =new Pop();
	//-----------------------------------------------------------------------------------
	/**
	@brief Add_MFood클래스의 함수 - 재료를 추가하게 해주는 함수
	@param txt_Name 재료이름
	@param txt_Life 유통기한
	*///----------------------------------------------------------------------------------

	void mmeterial_add(String txt_Name, String txt_Life) {

		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO Material (name,expire) " + "VALUES ('" + txt_Name + "'," + txt_Life + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
			p.Message("등록 되었습니다.");
		} catch (SQLException e) {
			p.Message("이미 등록된 재료입니다");
		}

	}
	
	//-----------------------------------------------------------------------------------
		/**
		@brief Add_MFood클래스의 함수 - 재료를 제거하게 해주는 함수
		@param x 재료이름
		*///----------------------------------------------------------------------------------


	void mmeterial_del(String x) {

		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM material  " + "WHERE name='" + x + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
			p.Message("삭제 되었습니다.");

		} catch (SQLException e) {
			
		}

	}

}
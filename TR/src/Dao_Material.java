
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
@brief CoonŬ������ ��ӹ޾Ƽ� ��� ����ϰ� ���DBtable�� ���õ� �������� ������ �ִ� Ŭ����

*///----------------------------------------------------------------------------------


public class Dao_Material extends Conec{
	
	private Pop p =new Pop();
	//-----------------------------------------------------------------------------------
	/**
	@brief Add_MFoodŬ������ �Լ� - ��Ḧ �߰��ϰ� ���ִ� �Լ�
	@param txt_Name ����̸�
	@param txt_Life �������
	*///----------------------------------------------------------------------------------

	void mmeterial_add(String txt_Name, String txt_Life) {

		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO Material (name,expire) " + "VALUES ('" + txt_Name + "'," + txt_Life + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
			p.Message("��� �Ǿ����ϴ�.");
		} catch (SQLException e) {
			p.Message("�̹� ��ϵ� ����Դϴ�");
		}

	}
	
	//-----------------------------------------------------------------------------------
		/**
		@brief Add_MFoodŬ������ �Լ� - ��Ḧ �����ϰ� ���ִ� �Լ�
		@param x ����̸�
		*///----------------------------------------------------------------------------------


	void mmeterial_del(String x) {

		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM material  " + "WHERE name='" + x + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
			p.Message("���� �Ǿ����ϴ�.");

		} catch (SQLException e) {
			
		}

	}

}
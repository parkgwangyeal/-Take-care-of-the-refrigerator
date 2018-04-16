

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSpinnerUI;

//-----------------------------------------------------------------------------------
/**
@class Dao_user.java
@brief CoonŬ������ ��ӹ޾Ƽ� ��� ����ϰ�  Usertable�� ���õ� �������� ��Ƶ� Ŭ����

*///----------------------------------------------------------------------------------

public class Dao_user extends Conec {

	public int z;
	public int count = 0;
	public int less = 0;
	public Pop p = new Pop();
	public int ss;
	//-----------------------------------------------------------------------------------
		/**
		@brief Dao_userŬ������ �Լ� - ������ ��Ḧ ���ϴ� ������ŭ ���� �� �ֵ��� ���ִ�
		@param object �޺��ڽ����� ���õ� ��� �̸�
		@param y ����
		*///----------------------------------------------------------------------------------
	
	public void del(Object object, String y) {
		try {
			do {
				stmt = conn.createStatement();
				String sql = "select min(expire),num from Usertable where name = '" + object + "';";
				stmt.executeUpdate(sql);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				stmt = conn.createStatement();
				String sql1 = "select DISTINCT min(num),expire from Usertable where name = '" + object + "'and expire ="
						+ rs.getInt("min(expire)") + ";";
				stmt.executeUpdate(sql1);
				pstmt = conn.prepareStatement(sql1);
				rs = pstmt.executeQuery();
				if (count == 0) {
					z = rs.getInt("min(num)") - Integer.parseInt(y);
					ss = rs.getInt("min(num)");
					if(ss==0){
						p.Message("����� ���� ��� �Դϴ�");
						break;
					}														
				} else {
					z = rs.getInt("min(num)") - z;
				}
			
				stmt = conn.createStatement();
				String sql2 = "update Usertable set  num = " + z + " where name ='" + object + "' and expire ="
						+ rs.getInt("expire") + " and num =" + rs.getInt("min(num)") + " ;";
				stmt.executeUpdate(sql2);
				stmt = conn.createStatement();
				String sql3 = "delete from usertable where num <= 0;";
				stmt.executeUpdate(sql3);
				stmt.close();

				z = -z;
				count++;
				conn.commit();
			} while (z > 0);
			if(count>0) p.Message("���� �Ǿ����ϴ�.");
			
			count = 0;
			
		} catch (SQLException e) {}
	}

	//-----------------------------------------------------------------------------------
			/**
			@brief Dao_userŬ������ �Լ� -��Ḧ �丮�� �ʿ��� ��ŭ ���� �� �ֵ��� ���ִ�
			@param x �޺��ڽ����� ���õ� ��� �̸� 
			@param y ����
			*///----------------------------------------------------------------------------------
		
	public void del_item(String x, String y) {
		try {
			do {

				stmt = conn.createStatement();
				String sql5 = "delete from usertable where num == 0;";
				stmt.executeUpdate(sql5);

				conn.commit();

				stmt = conn.createStatement();
				String sql = "select min(expire),num from Usertable where name = '" + x + "';";
				stmt.executeUpdate(sql);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				stmt = conn.createStatement();
				String sql1 = "select min(num),expire from Usertable where name = '" + x + "'and expire ="
						+ rs.getInt("min(expire)") + ";";
				stmt.executeUpdate(sql1);
				pstmt = conn.prepareStatement(sql1);
				rs = pstmt.executeQuery();

				if (rs.getInt("min(num)") == 0 && less == 0) {
					p.Message(x + "�� " + y + "�� �����ϴ�.");
					break;
				}

				else if (rs.getInt("min(num)") == 0 && less != 0) {
					p.Message(x + "�� �����ϴ�." + less + "��ŭ �����ϴ�.");
					break;
				}
				if (count == 0) {
					z = rs.getInt("min(num)") - Integer.parseInt(y);

				} else {
					z = rs.getInt("min(num)") - z;
				}

				stmt = conn.createStatement();
				String sql2 = "update Usertable set num = " + z + " where name ='" + x + "' and expire ="
						+ rs.getInt("expire") + " and num =" + rs.getInt("min(num)") + ";";
				stmt.executeUpdate(sql2);

				stmt = conn.createStatement();

				String sql3 = "delete from usertable where num <= 0;";
				stmt.executeUpdate(sql3);
				stmt.close();
				
				z = -z;

				if (z > 0)
					less = z;
				count++;
				conn.commit();

			} while (z > 0);

		} catch (SQLException e) {}
	}

	//-----------------------------------------------------------------------------------
	/**
	@brief Dao_userŬ������ �Լ� - �������̺� ����ڰ� ��Ḧ �����ϴ� �Լ��̴�.
	@param x �̸� 
	@param num ����
	@param z �������
	@param o �����
	@param d �Է��� ��¥
	*///----------------------------------------------------------------------------------

	public void InsertStorage(String x, int num, String z, String o, String d) {
		try {
			if(z.equals(""))
    		{
    			Dao dao = new Dao();
    			dao.connection("Material");
    			z=String.valueOf(dao.searchItem(x));
    			dao.disconnection();
    		}
	
			stmt = conn.createStatement();
			String sql = "INSERT INTO Usertable (name,num,expire,storage,date) VALUES('" + x + "','" + num + "','" + z
					+ "','" + o + "','" + d + "')";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.commit();

		} catch (SQLException e) {}
	}
	
	//-----------------------------------------------------------------------------------
		/**
		@brief Dao_userŬ������ �Լ� - ����Ұ� �Ű������� �������� ���̺� �������̺��� ��Ḧ �־��ֱ����� �Լ� Ŭ���� Dto�������� Arraylist�� ���� ��ȯ���ִ� �Լ��̴�
		@param storage ����� 
		*///----------------------------------------------------------------------------------

	// ī�װ��� �˻�
	public ArrayList<Dto> searchStorage(String storage) {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {

			String query = "select name,num,life from Usertable where storage = \'" + storage + "\'";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Dto t_code = new Dto();
				t_code.setName(rs.getString("name"));
				t_code.setNum(rs.getInt("num"));
				t_code.setLife(rs.getInt("life"));
				type_list.add(t_code);

			}
		} catch (SQLException e) {
		}

		return type_list;
	}

	
	//-----------------------------------------------------------------------------------
			/**
			@brief Dao_userŬ������ �Լ� - �������̺��� ����� �̸��� ������ ���������  �Լ� Ŭ���� Dto�������� Arraylist�� ���� ��ȯ���ִ� �Լ��̴�
			*///----------------------------------------------------------------------------------

	// �� �ƾ��� ����Ʈ ��̿� ��� �Լ�
	public ArrayList<Dto> searchStorage() {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {
			
			String query = "select name,num,life from Usertable";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Dto t_code = new Dto();
				t_code.setName(rs.getString("name"));
				t_code.setNum(rs.getInt("num"));
				t_code.setLife(rs.getInt("life"));
				type_list.add(t_code);
			}
		} catch (SQLException e) {
		}

		return type_list;
	}

	//-----------------------------------------------------------------------------------
	/**
	@brief Dao_userŬ������ �Լ� - ������ �����̵Ǵ� ��������� �޾Ƽ� �װͺ��� ��������� ���� �������̺� �ִ� ����� �̸��� ������ ���������  �Լ� Ŭ���� Dto�������� Arraylist�� ���� ��ȯ���ִ� �Լ��̴�
	*///----------------------------------------------------------------------------------

	public ArrayList<Dto> searchStorage(int expire) {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {

			String query = "select name,num,Life from Usertable where expire <= \'" + expire + "\'";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Dto t_code = new Dto();
				t_code.setName(rs.getString("name"));
				t_code.setNum(rs.getInt("num"));
				t_code.setLife(rs.getInt("Life"));
				type_list.add(t_code);

			}
		} catch (SQLException e) {
		}

		return type_list;
	}
	

	//-----------------------------------------------------------------------------------
			/**
			@brief Dao_userŬ������ �Լ� - �������̺��� ����� �̸���  �Լ� Ŭ���� Dto�������� Arraylist�� ���� ��ȯ���ִ� �Լ��̴�
			*///----------------------------------------------------------------------------------

	public ArrayList<Dto> searchName() {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {
			String query = "select name from Usertable";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Dto t_code = new Dto();
				t_code.setName(rs.getString("name"));

				type_list.add(t_code);
			}
		} catch (SQLException e) {
		}

		return type_list;
	}

	//-----------------------------------------------------------------------------------
	/**
	@brief Dao_userŬ������ �Լ� - �������̺��� ����� ��¥��  �Լ� Ŭ���� Dto�������� Arraylist�� ���� ��ȯ���ִ� �Լ��̴�
	*///----------------------------------------------------------------------------------

	// �԰�¥ �ҷ�����
	public ArrayList<Dto> searchDate() {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {
			String query = "select date from Usertable";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Dto t_code = new Dto();
				t_code.setDate(rs.getString("date"));
				type_list.add(t_code);
			}
		} catch (SQLException e) {
		}

		return type_list;
	}
	
	//-----------------------------------------------------------------------------------
	/**
	@brief Dao_userŬ������ �Լ� - �������̺��� ����� ���������  �Լ� Ŭ���� Dto�������� Arraylist�� ���� ��ȯ���ִ� �Լ��̴�
	*///----------------------------------------------------------------------------------

	public ArrayList<Dto> searchExpire() {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {
			String query = "select expire from Usertable";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Dto t_code = new Dto();
				t_code.setExpire(rs.getInt("expire"));
				type_list.add(t_code);
			}
		} catch (SQLException e) {
		}

		return type_list;
	}

	//-----------------------------------------------------------------------------------
		/**
		@brief Dao_userŬ������ �Լ� - ���� ����� ������Ѱ� x���� �������� ��������� ������Ʈ ��Ű�� �Լ� 
		*///----------------------------------------------------------------------------------
	
	public void UpdateStorage(int life, String name, int expire) {
		try {

			stmt = conn.createStatement();
			String sql = "Update Usertable set life='" + life + "'where name='" + name + "'and expire=" + expire + ";";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.commit();

		} catch (SQLException e) {
			
		}
	}

}

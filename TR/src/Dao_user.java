

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
@brief Coon클래스를 상속받아서 디비를 사용하고  Usertable에 관련된 쿼리문을 모아둔 클래스

*///----------------------------------------------------------------------------------

public class Dao_user extends Conec {

	public int z;
	public int count = 0;
	public int less = 0;
	public Pop p = new Pop();
	public int ss;
	//-----------------------------------------------------------------------------------
		/**
		@brief Dao_user클래스의 함수 - 유저가 재료를 원하는 수량만큼 지울 수 있도록 해주다
		@param object 콤보박스에서 선택된 재료 이름
		@param y 수량
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
						p.Message("냉장고에 없는 재료 입니다");
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
			if(count>0) p.Message("삭제 되었습니다.");
			
			count = 0;
			
		} catch (SQLException e) {}
	}

	//-----------------------------------------------------------------------------------
			/**
			@brief Dao_user클래스의 함수 -재료를 요리에 필요한 만큼 지울 수 있도록 해주다
			@param x 콤보박스에서 선택된 재료 이름 
			@param y 수량
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
					p.Message(x + "가 " + y + "개 없습니다.");
					break;
				}

				else if (rs.getInt("min(num)") == 0 && less != 0) {
					p.Message(x + "가 없습니다." + less + "만큼 없습니다.");
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
	@brief Dao_user클래스의 함수 - 유저테이블에 사용자가 재료를 저장하는 함수이다.
	@param x 이름 
	@param num 수량
	@param z 유통기한
	@param o 저장소
	@param d 입력한 날짜
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
		@brief Dao_user클래스의 함수 - 저장소가 매개변수로 들어왔을뗴에 테이블에 유저테이블의 재료를 넣어주기위해 함수 클래스 Dto형식으로 Arraylist를 만들어서 반환해주는 함수이다
		@param storage 저장소 
		*///----------------------------------------------------------------------------------

	// 카테고리별 검색
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
			@brief Dao_user클래스의 함수 - 유저테이블의 재료의 이름과 갯수와 유통기한을  함수 클래스 Dto형식으로 Arraylist를 만들어서 반환해주는 함수이다
			*///----------------------------------------------------------------------------------

	// 총 아아템 리스트 어레이에 담는 함수
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
	@brief Dao_user클래스의 함수 - 마감의 기준이되는 유통기한을 받아서 그것보다 유통기한이 적은 유저테이블에 있는 재료의 이름과 갯수와 유통기한을  함수 클래스 Dto형식으로 Arraylist를 만들어서 반환해주는 함수이다
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
			@brief Dao_user클래스의 함수 - 유저테이블의 재료의 이름을  함수 클래스 Dto형식으로 Arraylist를 만들어서 반환해주는 함수이다
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
	@brief Dao_user클래스의 함수 - 유저테이블의 재료의 날짜를  함수 클래스 Dto형식으로 Arraylist를 만들어서 반환해주는 함수이다
	*///----------------------------------------------------------------------------------

	// 입고날짜 불러오기
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
	@brief Dao_user클래스의 함수 - 유저테이블의 재료의 유통기한을  함수 클래스 Dto형식으로 Arraylist를 만들어서 반환해주는 함수이다
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
		@brief Dao_user클래스의 함수 - 원래 저장된 유통기한과 x일이 지난후의 유통기한을 업데이트 시키는 함수 
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


import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Dao extends Conec {
	public String ss;
	private Pop p= new Pop();
	public String get_item(Object object) {
		try{
		String query = "select item from Recipe where rname='" + object + "' ";
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		ss=rs.getString("item");
		}
		catch(SQLException e){
		}
		return ss;
	}
	
	public Integer searchItem(String name) {
		int expire = 0;
		try {

			String query = "select expire from Material where name = \'" + name + "\'";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			expire = rs.getInt("expire");

		} catch (SQLException e) {
			
		}

		return expire;
	}

	public ArrayList<Dto> search_List(String item) {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {
			String query = "select rname from Recipe where item like  '%" + item + "%' ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Dto t_code = new Dto();
				t_code.setName(rs.getString("rname"));
				type_list.add(t_code);
			}
		} catch (SQLException e) {
		}

		return type_list;
	}

	// 카테고리별 음식리스트 검색
	public ArrayList<Dto> search_List(String item, String type) {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {

			String query = "select rname from Recipe where type = '" + type + "' and item like  '%" + item + "%'";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs != null) {
				
			}
			while (rs.next()) {
				Dto t_code = new Dto();
				t_code.setName(rs.getString("rname"));
				type_list.add(t_code);
			}
		} catch (SQLException e) {
		}

		return type_list;
	}

	// 저장소 검색
	public ArrayList<Dto> searchType(String type) {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {
			String query = "select name,num,expire from Material";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Dto t_code = new Dto();
				t_code.setType(rs.getString("type"));
				type_list.add(t_code);
			}
		} catch (SQLException e) {
		}

		return type_list;
	}

	public ArrayList<Dto> searchName() {
		ArrayList<Dto> type_list = new ArrayList<Dto>();
		try {
			String query = "select name from Material";
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

	public String ViewRecipe(String search, Object selectedItem, JTextArea textArea) {
		String item=null;
		try {
			String query = "select " + search + ",image,item from Recipe where rname = '" + selectedItem + "';";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery();

			textArea.setText(rset.getString(search));

			item = rset.getString("item");

		} catch (SQLException e) {
	
			p.Message("재료를 선택해 주세요");
		}
		return item;
	}
	//일단
	public ImageIcon ViewImage(String search, Object selectedItem, ImageIcon icon, JLabel label_Image, JPanel background) {
		String item=null;
		ImageIcon icon_c=null;
		try {
			String query = "select image from Recipe where rname = '" + selectedItem + "';";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery();

			icon = new ImageIcon("이미지/" + rset.getString("image"));
			Image image = icon.getImage(); // ImageIcon을 Image로 변환
			Image image_c = image.getScaledInstance(386, 292, Image.SCALE_SMOOTH);
			icon_c = new ImageIcon(image_c);// Image로 ImageIcon생성
			label_Image.setVisible(false);

			//label_Image = new JLabel(icon_c, JLabel.CENTER);
			//label_Image.setBounds(12, 21, 386, 292);

			//background.add(label_Image);
		} catch (SQLException e) {
			
		}
		return icon_c;
	}
}

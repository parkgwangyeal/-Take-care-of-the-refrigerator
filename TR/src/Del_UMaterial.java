
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

//-----------------------------------------------------------------------------------
/**
 * @class Del_UMaterial.java
 * @brief 관리자가 사용자 냉장고테이블에 음식을 추가하기위한 클래스
 */// ----------------------------------------------------------------------------------

public class Del_UMaterial extends Material {
	private Pop p = new Pop();
	private int a;
	
	// ------------ ----------------------------------------------------------------------
	/**
	 * @brief Del_UMaterial클래스의 생성자 - Dao_user클래스를 사용해서 유저가 재료의 이름과 개수를 입력받아 ㅏ
	 *        재료를 삭제한다.
	 */// ----------------------------------------------------------------------------------

	public Del_UMaterial() {

		Btn_Finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dao_user DU = new Dao_user();
				DU.connection("UserTable");
				if (Txt_Amount.getText().equals("")) {
					p.Message("개수를 입력하세요");
					return;
				} 
				  a = Integer.parseInt(Txt_Amount.getText());
				
				if(a<0){
					p.Message("잘못 입력하셨습니다(에러:-)");
					return;
				}
				else {
					DU.del(Combo_Name.getSelectedItem(), Txt_Amount.getText());
					DU.disconnection();
					Main_User MU = new Main_User();
					MU.setBounds(0, 0, 1180, 740);
					MU.setVisible(true);
					setVisible(false);
				}
				DU.disconnection();
			}
		});
	}
}

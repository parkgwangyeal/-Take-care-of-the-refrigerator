
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
 * @brief �����ڰ� ����� ��������̺� ������ �߰��ϱ����� Ŭ����
 */// ----------------------------------------------------------------------------------

public class Del_UMaterial extends Material {
	private Pop p = new Pop();
	private int a;
	
	// ------------ ----------------------------------------------------------------------
	/**
	 * @brief Del_UMaterialŬ������ ������ - Dao_userŬ������ ����ؼ� ������ ����� �̸��� ������ �Է¹޾� ��
	 *        ��Ḧ �����Ѵ�.
	 */// ----------------------------------------------------------------------------------

	public Del_UMaterial() {

		Btn_Finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dao_user DU = new Dao_user();
				DU.connection("UserTable");
				if (Txt_Amount.getText().equals("")) {
					p.Message("������ �Է��ϼ���");
					return;
				} 
				  a = Integer.parseInt(Txt_Amount.getText());
				
				if(a<0){
					p.Message("�߸� �Է��ϼ̽��ϴ�(����:-)");
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

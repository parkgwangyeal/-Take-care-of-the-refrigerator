
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//-----------------------------------------------------------------------------------
/**
 * @class Expire_list.java
 * @brief 관리자가 냉장고 재료중 유통기한 임박한 재료들을 이용해 가능한 요리 리스트를 보여준다.
 */// ----------------------------------------------------------------------------------

public class Expire_list extends View_Recipe {
	// -----------------------------------------------------------------------------------
	/**
	 * @brief Expire_list클래스의 생성자 - Dao_user클래스를 이용하여 유통기한임박 재료리스트를 받은후 선택한 재료의
	 *        가능요리 리스트를보여준다
	 */// ----------------------------------------------------------------------------------
	public ArrayList<Dto> addressList2;
	Nutrient nut;
	public Expire_list() {
		nut.before_View=2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1272, 720);
		comboBox.setBounds(284, 323, 164, 45);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(14, 323, 132, 48);
		//comboBox_1.setBounds(14, 323, 387, 47);
		getContentPane().add(comboBox_1);

		JButton btnNewButton = new JButton("재료선택");
		btnNewButton.setBackground(new Color(204, 255, 204));
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object temp = comboBox_1.getSelectedItem();

				String text = temp.toString();

				Dao controller2 = new Dao();
				controller2.connection("Material");

				addressList2 = controller2.search_List(text);
				controller2.disconnection();
				Object[] arrAdd2 = new Object[addressList2.size()];
				for (int j = 0; j < addressList2.size(); j++) {
					try {
						Dto t_code = addressList2.get(j);
						arrAdd2[j] = t_code.getName();
						comboBox.addItem(arrAdd2[j]);
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}

			}
		});
		btnNewButton.setBounds(160, 323, 105, 47);
		getContentPane().add(btnNewButton);

		//btnNewButton_1.setBounds(415, 411, 105, 47);
		btnNewButton_3.removeActionListener(a3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nut = new Nutrient(addressList2);
				nut.setVisible(true);
				setVisible(false);

			}
		});
		
		Dao_user controller = new Dao_user();
		controller.connection("UserTable");
		ArrayList<Dto> addressList = controller.searchStorage(5);
		Object[] arrAdd = new Object[addressList.size()];
		for (int i = 0; i < addressList.size(); i++) {
			Dto t_code = addressList.get(i);
			arrAdd[i] = t_code.getName();
			comboBox_1.addItem(arrAdd[i]);
		}
		controller.disconnection();
	}
}


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @class Soup_list.java
 * @brief View_RecipeŬ������ ��ӹ޴� Ŭ�����̴�. UserTabled.db�� �о� ���� ���� ����� �ľ���
 *        Material.db���� ���� ���� ���� ������ �� ���õ� ����Ʈ�� �˷��ش�.
 */
public class Soup_list extends View_Recipe {

	// public JButton btnNewButton_2;//����Һ���
	public JComboBox comboBox_1;
	public JButton btn_item;// ��ἱ��
	public ArrayList<Dto> addressList2;
	public int count = 0;
	Nutrient nut;
	/**
	 * @brief Soup_list Ŭ���� �������̴�. comboBox_1���� ��ἱ���� "��ἱ��"��ư�� ������ comboBox�� �ش�
	 *        ���ɷ����� ����Ʈ���� ��ϵȴ�.
	 *///
	public Soup_list() {
		nut.before_View = 4;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1272, 720);
		super.comboBox.setBounds(14, 409, 386, 45);

		this.comboBox_1 = new JComboBox();
		this.comboBox_1.setBounds(14, 323, 123, 47);
		getContentPane().add(this.comboBox_1);

		this.btn_item = new JButton("��ἱ��");
		this.btn_item.setBackground(new Color(204, 255, 204));
		this.btn_item.setFont(new Font("���� ���", Font.PLAIN, 15));
		this.btn_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (count == 0) {
					Object temp = comboBox_1.getSelectedItem();

					String text = temp.toString();

					Dao controller2 = new Dao();
					controller2.connection("Material");

					addressList2 = controller2.search_List(text, "��");
					controller2.disconnection();
					Object[] arrAdd2 = new Object[addressList2.size()];
					for (int j = 0; j < addressList2.size(); j++) {
						try {
							Dto t_code = addressList2.get(j);
							arrAdd2[j] = t_code.getName();
							comboBox.addItem(arrAdd2[j]);
						} catch (Exception e1) {
							
						}
					}
					count++;
				} else {
					comboBox.removeAllItems();
					Object temp = comboBox_1.getSelectedItem();

					String text = temp.toString();

					Dao controller2 = new Dao();
					controller2.connection("Material");

					addressList2 = controller2.search_List(text, "��");
					controller2.disconnection();
					Object[] arrAdd2 = new Object[addressList2.size()];
					for (int j = 0; j < addressList2.size(); j++) {
						try {
							Dto t_code = addressList2.get(j);
							arrAdd2[j] = t_code.getName();
							comboBox.addItem(arrAdd2[j]);
						} catch (Exception e1) {
							
						}
					}
				}
			}
		});
		this.btn_item.setBounds(160, 323, 105, 47);
		getContentPane().add(this.btn_item);

		super.btnNewButton_1.setBounds(444, 323, 105, 45);
		super.comboBox.setBounds(284, 323, 128, 45);
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
		ArrayList<Dto> addressList = controller.searchName();
		Object[] arrAdd = new Object[addressList.size()];
		for (int i = 0; i < addressList.size(); i++) {
			Dto t_code = addressList.get(i);
			arrAdd[i] = t_code.getName();
			comboBox_1.addItem(arrAdd[i]);
		}
		controller.disconnection();
	}

}
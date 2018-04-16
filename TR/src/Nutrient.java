
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Font;

/**
 * @class Nutrient.java
 * @brief Rice_listŬ������ ��ӹ޴� Ŭ�����̴�. �丮����Ʈ�� �ش�丮 ����ҵ��� �����ִ� Ŭ����
 */
public class Nutrient extends Rice_list {
	// public JTextPane textPane;
	public static int before_View=0;
	public static Object ob;
	public Object[] arrAdd;
	public JButton btnNewButton_4;
	private Pop p = new Pop();
	private String ss;
	private ArrayList<Dto> addressList;
	//-----------------------------------------------------------------------------------
	/**
	@brief NutrientŬ������ ������ - ����Ҹ� �����δ� ��Ȱ�� �ϴ� ������
	*///----------------------------------------------------------------------------------
	public Nutrient() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1272, 720);

		this.getContentPane().remove(comboBox_1);
		this.getContentPane().remove(btn_item);
		this.getContentPane().remove(btnNewButton_3);

		this.arrAdd = new Object[addressList2.size()];
		for (int j = 0; j < addressList2.size(); j++) {
			try {
				Dto t_code = addressList2.get(j);
				this.arrAdd[j] = t_code.getName();
				super.comboBox.addItem(this.arrAdd[j]);
			} catch (Exception e1) {
				System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			}

		}

		this.btnNewButton_1 = new JButton();
		this.btnNewButton_1.addActionListener(new ActionListener() {
			int count = 0;

			public void actionPerformed(ActionEvent arg0) {
				try {

					if (count == 0) {
						Label_Image = new JLabel();
						Label_Image.setBounds(12, 21, 386, 292);
						background.add(Label_Image);
						// Label_Image.setVisible(false);
						count++;
					}

					Dao controller = new Dao();
					controller.connection("Material");
					item = controller.ViewRecipe("nutrient", comboBox.getSelectedItem(), textArea_1);
					controller.ViewRecipe("recipe", comboBox.getSelectedItem(), textArea_1);
					ImageIcon image = controller.ViewImage("recipe", comboBox.getSelectedItem(), icon, Label_Image,
							background);

					Label_Image = new JLabel(image, JLabel.CENTER);
					Label_Image.setBounds(12, 21, 386, 292);
					background.add(Label_Image);

					controller.disconnection();
				} catch (Exception e) {

				}
			}
		});

		this.btnNewButton_4 = new JButton("����ȭ��");
		this.btnNewButton_4.setFont(new Font("���� ���", Font.PLAIN, 15));
		this.btnNewButton_4.setBackground(new Color(204, 255, 204));
		this.btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (before_View == 1) {
					View_Recipe VR = new View_Recipe(ob);
					VR.setVisible(true);
					setVisible(false);
				} else if (before_View == 2) {
					Expire_list El = new Expire_list();
					El.setVisible(true);
					setVisible(false);
				}  else if (before_View == 4){
					Soup_list soup = new Soup_list();
					soup.setVisible(true);
					setVisible(false);
				} else if (before_View == 5){
					Side_list side = new Side_list();
					side.setVisible(true);
					setVisible(false);
				} else if(before_View == 6){
					Special_list special = new Special_list();
					special.setVisible(true);
					setVisible(false);
				} else if(before_View == 7){
					Season_list season = new Season_list();
					season.setVisible(true);
					setVisible(false);
				} else{
					Rice_list rice = new Rice_list();
					rice.setVisible(true);
					setVisible(false);
				}
			}
		});
		this.btnNewButton_4.setBounds(639, 574, 128, 46);
		getContentPane().add(this.btnNewButton_4);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(183, 542, 97, 53);
		getContentPane().add(textArea);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 525, 97, 58);
		getContentPane().add(scrollPane);

		JTextArea textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);

	}

	/**
	 * @brief Rice_listŬ���� �������̴�. ī�װ��� �丮���⿡�� �Ѿ�� addressList2 �丮����Ʈ�� ����ҵ���
	 *        �����ش�.
	 * @param addressList2
	 *///
	public Nutrient(ArrayList<Dto> addressList2) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1272, 720);

		this.getContentPane().remove(comboBox_1);
		this.getContentPane().remove(btn_item);
		this.getContentPane().remove(btnNewButton_3);

		this.arrAdd = new Object[addressList2.size()];
		for (int j = 0; j < addressList2.size(); j++) {
			try {
				Dto t_code = addressList2.get(j);
				this.arrAdd[j] = t_code.getName();
				super.comboBox.addItem(this.arrAdd[j]);
			} catch (Exception e1) {
				System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			}

		}
		btnNewButton_1.removeActionListener(al);
		btnNewButton_1.addActionListener(new ActionListener() {
			int count = 0;

			public void actionPerformed(ActionEvent arg0) {

				try {
					if (count == 0) {
						Label_Image = new JLabel();
						Label_Image.setBounds(12, 21, 386, 292);
						background.add(Label_Image);
						// Label_Image.setVisible(false);
						count++;
					}

					Dao controller = new Dao();
					controller.connection("Material");
					item = controller.ViewRecipe("nutrient", comboBox.getSelectedItem(), textArea_1);
					ImageIcon image = controller.ViewImage("recipe", comboBox.getSelectedItem(), icon, Label_Image,
							background);
					ss = controller.get_item(comboBox.getSelectedItem());
					Label_Image = new JLabel(image, JLabel.CENTER);
					Label_Image.setBounds(12, 21, 386, 292);
					background.add(Label_Image);
					controller.disconnection();
				} catch (Exception e) {

				}
			}
		});

		btnNewButton_2.removeActionListener(a2);
		this.btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Dao_user DU = new Dao_user();
				DU.connection("UserTable");
				StringTokenizer tokens = new StringTokenizer(ss, ",");
				for (int x = 1; tokens.hasMoreElements(); x++) {

					String i1 = null;
					String i2 = null;
					i1 = tokens.nextToken();
					i2 = tokens.nextToken();
					DU.del_item(i1, i2);

				}
				DU.disconnection();
				p.Message("�Ϸ� �Ͽ����ϴ�.");

				Main_User MU = new Main_User();
				setVisible(false);
				MU.setVisible(true);
				MU.setBounds(0, 0, 1180, 740);
			}
		});
		Dto s = addressList2.get(0);
		this.btnNewButton_4 = new JButton("����ȭ��");
		this.btnNewButton_4.setFont(new Font("���� ���", Font.PLAIN, 15));
		this.btnNewButton_4.setBackground(new Color(204, 255, 204));
		this.btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (before_View == 1) {
					View_Recipe VR = new View_Recipe(ob);
					VR.setBounds(0,0,1272, 720);
					VR.setVisible(true);
					setVisible(false);
				} else if (before_View == 2) {
					Expire_list El = new Expire_list();
					El.setBounds(0,0,1272, 720);
					El.setVisible(true);
					setVisible(false);
				} else if (before_View == 4){
					Soup_list soup = new Soup_list();
					soup.setBounds(0,0,1272, 720);
					soup.setVisible(true);
					setVisible(false);
				} else if (before_View == 5){
					Side_list side = new Side_list();
					side.setBounds(0,0,1272, 720);
					side.setVisible(true);
					setVisible(false);
				} else if(before_View == 6){
					Special_list special = new Special_list();
					special.setBounds(0,0,1272, 720);
					special.setVisible(true);
					setVisible(false);
				} else if(before_View == 7){
					Season_list season = new Season_list();
					season.setBounds(0,0,1272, 720);
					season.setVisible(true);
					setVisible(false);
				} else{
					Rice_list rice = new Rice_list();
					rice.setBounds(0,0,1272, 720);
					rice.setVisible(true);
					setVisible(false);
				}
			}
		});
		this.btnNewButton_4.setBounds(639, 574, 128, 46);
		getContentPane().add(this.btnNewButton_4);
	}

}
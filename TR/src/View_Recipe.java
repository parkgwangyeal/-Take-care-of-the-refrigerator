
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

//-----------------------------------------------------------------------------------
/**
 * @class View_Recipe.java
 * @brief 테이블에서 선택한 재료를 가지고 할 수 있는 요리리스트를 보여주는 클래스
 * 
 * 
 */// ----------------------------------------------------------------------------------

public class View_Recipe extends JFrame {
	public JPanel background;
	public JComboBox comboBox;
	public ImageIcon icon;
	public JLabel Label_Image;
	public JButton btnNewButton_1;// 요리선택
	public String item;
	public JButton btnNewButton_2;// 요리완료
	public ImageIcon back;
	public JButton btnNewButton;// 돌아가기
	public JScrollPane scrollPane;
	public JTextArea textArea_1;
	public JButton btnNewButton_3;// 영양소보기

	private Pop p = new Pop();
	private String ss;
	public ActionListener al;
	public ActionListener a2;
	public ActionListener a3;
	Nutrient nut;

	// -----------------------------------------------------------------------------------
	/**
	 * @brief View_Recipe클래스의 생성자 - 내가 원하는 재료를 유저창에서 선택하면 그것에 맞는 요리들을 보여주고 요리를
	 *        선택하면 사진과 레시피를 보여준다
	 */// ----------------------------------------------------------------------------------
	public View_Recipe() { // 스윙으로 확인하기위해서 GUI들만 끌어왔습니다
		ImageIcon back = new ImageIcon("이미지/View_Recipe.png"); // 배경사진
		background = new JPanel() { // 배경코드
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(back.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1272, 720);

		setContentPane(background);
		this.background.setLayout(null);

		this.comboBox = new JComboBox();

		this.comboBox.setBounds(284, 323, 164, 45); // 요리리스트 위치
		this.background.add(comboBox);

		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(598, 47, 609, 501);

		background.add(scrollPane);
		// 쿼리입력
		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		scrollPane.setViewportView(textArea_1);

		// 쿼리입력끝
		// 요리선택 누를시에 오른쪽 텍스트파일에 레시피 뜸
		this.btnNewButton_1 = new JButton("요리선택");
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton_1.setBackground(new Color(204, 255, 204));
		this.btnNewButton_1.addActionListener(al = new ActionListener() {
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
					item = controller.ViewRecipe("recipe", comboBox.getSelectedItem(), textArea_1);
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
		// 레시피 띄우기 끝
		this.btnNewButton_1.setBounds(462, 323, 104, 45);
		this.background.add(this.btnNewButton_1);

		this.btnNewButton_2 = new JButton("요리완료");
		btnNewButton_2.setBackground(new Color(204, 255, 204));
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		try {
			this.btnNewButton_2.addActionListener(a2 = new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					Dao_user DU = new Dao_user();
					DU.connection("UserTable");
					try {
						StringTokenizer tokens = new StringTokenizer(ss, ",");
						for (int x = 1; tokens.hasMoreElements(); x++) {

							String i1 = null;
							String i2 = null;
							i1 = tokens.nextToken();
							i2 = tokens.nextToken();
							DU.del_item(i1, i2);

						}
						p.Message("완료 하였습니다.");
					} catch (NullPointerException e) {
						p.Message("요리를 선택해주세요");
					}
					DU.disconnection();
					Main_User MU = new Main_User();
					setVisible(false);
					MU.setVisible(true);
					MU.setBounds(100, 100, 1180, 740);

				}

			});
		} catch (NullPointerException e) {
			p.Message("요리를 선택해주세요");
		}
		this.btnNewButton_2.setBounds(802, 576, 113, 45); // 요리선택버튼 위치
		this.background.add(this.btnNewButton_2);

		btnNewButton = new JButton("돌아가기");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_User user = new Main_User();
				user.setBounds(0,0,1180, 740);
				user.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(948, 576, 126, 45);
		background.add(btnNewButton);

		btnNewButton_3 = new JButton("영양소 보기");
		btnNewButton_3.setBackground(new Color(204, 255, 204));
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton_3.addActionListener(a3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nut = new Nutrient();
				nut.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(639, 576, 126, 45);
		background.add(btnNewButton_3);

	}
	// -----------------------------------------------------------------------------------
		/**
		 * @brief View_Recipe클래스의 생성자 - 내가 원하는 재료를 유저창에서 선택하면 그것에 맞는 요리들을 보여주고 요리를
		 *        선택하면 사진과 레시피를 보여준다
		 * @param s
		 *            내가원하는 재료
		 */// ----------------------------------------------------------------------------------

	public View_Recipe(Object s) {
		nut.before_View = 1;
		Nutrient.ob = s;
		this.back = new ImageIcon("이미지/View_Recipe.png"); // 배경사진
		this.background = new JPanel() { // 배경코드
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(back.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1272, 720);
		setContentPane(background);
		this.background.setLayout(null);

		String text = s.toString();
		this.comboBox = new JComboBox();

		Dao controller = new Dao();
		controller.connection("Material");
		ArrayList<Dto> addressList = controller.search_List(text);
		Object[] arrAdd = new Object[addressList.size()];
		for (int i = 0; i < addressList.size(); i++) {
			Dto t_code = addressList.get(i);
			arrAdd[i] = t_code.getName();
			this.comboBox.addItem(arrAdd[i]);
		}
		controller.disconnection();
		this.comboBox.setBounds(284, 323, 164, 45); // 요리리스트 위치
		this.background.add(this.comboBox);

		// 쿼리입력

		/// 실험용
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(598, 47, 609, 501);
		background.add(scrollPane);
		// 쿼리입력
		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		scrollPane.setViewportView(textArea_1);

		// 쿼리입력끝
		// 요리선택 누를시에 오른쪽 텍스트파일에 레시피 뜸
		this.btnNewButton_1 = new JButton("요리선택");
		btnNewButton_1.setBackground(new Color(204, 255, 204));
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			int count = 0;;
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
					item = controller.ViewRecipe("recipe", comboBox.getSelectedItem(), textArea_1);
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
		// 레시피 띄우기 끝
		this.btnNewButton_1.setBounds(462, 323, 104, 45);
		this.background.add(this.btnNewButton_1);

		this.btnNewButton_2 = new JButton("요리완료");
		this.btnNewButton_2.setBackground(new Color(204, 255, 204));
		this.btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		this.btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Dao_user DU = new Dao_user();
				DU.connection("UserTable");
				try {
					StringTokenizer tokens = new StringTokenizer(ss, ",");
					for (int x = 1; tokens.hasMoreElements(); x++) {

						String i1 = null;
						String i2 = null;
						i1 = tokens.nextToken();
						i2 = tokens.nextToken();
						DU.del_item(i1, i2);

					}
					p.Message("완료 하였습니다.");
					setVisible(false);
				} catch (NullPointerException e) {
					p.Message("요리를 선택해주세요");
				}
				DU.disconnection();
				Main_User MU = new Main_User();
				setVisible(false);
				MU.setVisible(true);
				MU.setBounds(100, 100, 1180, 740);
			}
		});

		this.btnNewButton_2.setBounds(802, 576, 113, 45); // 요리선택버튼 위치
		this.background.add(this.btnNewButton_2);

		btnNewButton_3 = new JButton("영양소 보기");
		btnNewButton_3.setBackground(new Color(204, 255, 204));
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton_3.addActionListener(a3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nut = new Nutrient(addressList);
				nut.setBounds(0, 0, 1272, 720);
				nut.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(639, 576, 126, 45);
		background.add(btnNewButton_3);

		this.btnNewButton = new JButton("돌아가기");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_User user = new Main_User();
				user.setBounds(0,0,1180, 740);
				user.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(948, 576, 126, 45);
		this.background.add(this.btnNewButton);
	}
	// -----------------------------------------------------------------------------------
		/**
		 * @brief View_Recipe클래스의 생성자 - 내가 원하는 재료를 유저창에서 선택하면 그것에 맞는 요리들을 보여주고 요리를
		 *        선택하면 사진과 레시피를 보여준다
		 * @param ArrayList<Dto> addressList2
		 *            내가원하는 재료
		 */// ----------------------------------------------------------------------------------
	public View_Recipe(ArrayList<Dto> addressList2) { // 스윙으로 확인하기위해서 GUI들만
														// 끌어왔습니다
		ImageIcon back = new ImageIcon("이미지/View_Recipe.png"); // 배경사진
		background = new JPanel() { // 배경코드
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(back.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1272, 720);

		setContentPane(background);
		this.background.setLayout(null);

		this.comboBox = new JComboBox();

		this.comboBox.setBounds(284, 323, 164, 45); // 요리리스트 위치
		this.background.add(comboBox);

		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(598, 47, 609, 501);

		background.add(scrollPane);
		// 쿼리입력
		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		scrollPane.setViewportView(textArea_1);

		// 쿼리입력끝
		// 요리선택 누를시에 오른쪽 텍스트파일에 레시피 뜸
		this.btnNewButton_1 = new JButton("요리선택");
		this.btnNewButton_1.setBackground(new Color(204, 255, 204));
		this.btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		this.btnNewButton_1.addActionListener(al = new ActionListener() {
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
					item = controller.ViewRecipe("recipe", comboBox.getSelectedItem(), textArea_1);
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
		// 레시피 띄우기 끝
		this.btnNewButton_1.setBounds(462, 323, 104, 45);
		this.background.add(this.btnNewButton_1);

		this.btnNewButton_2 = new JButton("요리완료");
		this.btnNewButton_2.setBackground(new Color(204, 255, 204));
		this.btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		try {
			this.btnNewButton_2.addActionListener(a2 = new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					Dao_user DU = new Dao_user();
					DU.connection("UserTable");
					try {
						StringTokenizer tokens = new StringTokenizer(ss, ",");
						for (int x = 1; tokens.hasMoreElements(); x++) {

							String i1 = null;
							String i2 = null;
							i1 = tokens.nextToken();
							i2 = tokens.nextToken();
							DU.del_item(i1, i2);

						}
						p.Message("완료 하였습니다.");
						setVisible(false);
					} catch (NullPointerException e) {
						p.Message("요리를 선택해주세요");
					}
					DU.disconnection();
					Main_User MU = new Main_User();
					MU.setVisible(true);
					MU.setBounds(100, 100, 1180, 740);
				}

			});
		} catch (NullPointerException e) {
			p.Message("요리를 선택해주세요");
		}
		this.btnNewButton_2.setBounds(802, 576, 113, 45); // 요리선택버튼 위치
		this.background.add(this.btnNewButton_2);

		btnNewButton = new JButton("돌아가기");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_User user = new Main_User();
				user.setBounds(0, 0, 1272, 720);
				user.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(1062, 576, 126, 45);
		background.add(btnNewButton);

		btnNewButton_3 = new JButton("영양소 보기");
		btnNewButton_3.setBackground(new Color(204, 255, 204));
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton_3.addActionListener(a3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nut = new Nutrient();
				nut.setBounds(0, 0, 1272, 720);
				nut.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(639, 576, 126, 45);
		background.add(btnNewButton_3);

	}

}
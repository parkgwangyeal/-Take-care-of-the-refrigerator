
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

//-----------------------------------------------------------------------------------
/**
 * @class Category.java
 * @brief 카테고리별 요리선택창을 보여주는 클래스
 * 
 */// ----------------------------------------------------------------------------------

public class Category extends JFrame {
	private JScrollPane scrollPane;
	private ImageIcon icon;
	private JPanel background;
	public JLabel label;
	public JButton btnNewButton;
	public JButton btnNewButton_1;
	public JButton btnNewButton_2;
	public JButton btnNewButton_3;
	public JButton btnNewButton_4;
	public JButton btnNewButton_5;

	// -----------------------------------------------------------------------------------
	/**
	 * @brief Category클래스의 생성자 - 카테고리별로 버튼을 생성되어 있다.
	 */// ----------------------------------------------------------------------------------

	public Category() {
		this.icon = new ImageIcon("이미지/Category.png");
		this.background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ㅇㅇ
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		this.background.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 685);

		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(background);
		background.setLayout(null);

		this.label = new JLabel("카테고리별 요리리스트");
		this.label.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		this.label.setBounds(14, 12, 340, 55);
		this.background.add(label);

		this.btnNewButton = new JButton("밥");
		this.btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		this.btnNewButton.setBackground(new Color(188, 143, 143));
		this.btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rice_list rice = new Rice_list();
				rice.setBounds(0, 0, 1272, 720);
				rice.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(new Color(153, 102, 102));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(new Color(188, 143, 143));
			}
		});
		this.btnNewButton.setBounds(129, 220, 80, 61);
		background.add(btnNewButton);

		this.btnNewButton_1 = new JButton("국");
		this.btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		this.btnNewButton_1.setBackground(new Color(188, 143, 143));
		this.btnNewButton_1.setBounds(445, 220, 80, 61);
		this.btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Soup_list soup = new Soup_list();
				soup.setBounds(0, 0, 1272, 720);
				soup.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setBackground(new Color(153, 102, 102));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setBackground(new Color(188, 143, 143));
			}
		});
		this.background.add(btnNewButton_1);

		this.btnNewButton_2 = new JButton("반 찬");
		this.btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		this.btnNewButton_2.setBackground(new Color(188, 143, 143));
		this.btnNewButton_2.setBounds(763, 220, 80, 61);
		this.btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Side_list side = new Side_list();
				side.setBounds(0, 0, 1272, 720);
				side.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setBackground(new Color(153, 102, 102));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setBackground(new Color(188, 143, 143));
			}
		});
		this.background.add(btnNewButton_2);

		this.btnNewButton_3 = new JButton("특 식");
		this.btnNewButton_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		this.btnNewButton_3.setBackground(new Color(188, 143, 143));
		this.btnNewButton_3.setBounds(289, 426, 80, 61);
		this.btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Special_list special = new Special_list();
				special.setBounds(0, 0, 1272, 720);
				special.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_3.setBackground(new Color(153, 102, 102));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_3.setBackground(new Color(188, 143, 143));
			}
		});
		this.background.add(btnNewButton_3);

		this.btnNewButton_4 = new JButton("계 절");
		this.btnNewButton_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		this.btnNewButton_4.setBackground(new Color(188, 143, 143));
		this.btnNewButton_4.setBounds(605, 426, 80, 61);
		this.btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Season_list season = new Season_list();
				season.setBounds(0, 0, 1272, 720);
				season.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_4.setBackground(new Color(153, 102, 102));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_4.setBackground(new Color(188, 143, 143));
			}
		});
		this.background.add(btnNewButton_4);

		this.btnNewButton_5 = new JButton("돌아가기");
		this.btnNewButton_5.setBackground(SystemColor.control);
		this.btnNewButton_5.setBounds(869, 545, 123, 40);
		this.btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main_User user = new Main_User();
				user.setBounds(0, 0, 1272, 720);
				user.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_5.setBackground(new Color(192, 192, 192));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_5.setBackground(SystemColor.controlHighlight);
			}
		});
		this.background.add(btnNewButton_5);
		
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
	}
}

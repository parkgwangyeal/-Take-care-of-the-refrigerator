

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main_Login extends JFrame {
	private JPanel background;
	private ImageIcon icon;
	private Dimension d;
	private JButton Btn_Manager;
	private JButton Btn_User;
	private JButton Btn_Cancel;
	private JScrollPane scrollPane;

	//-----------------------------------------------------------------------------------
	/**
	@brief Main_Login클래스의 생성자 - 로그인창의 전체적인 클릭이벤트를 사용하게 해준다
	*///----------------------------------------------------------------------------------
	public Main_Login() {
		icon = new ImageIcon("이미지/Login.png");
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ㅇㅇ

				// Approach 2: Scale image to size of component
				// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				// Approach 3: Fix the image position in the scroll pane
				// Point p = scrollPane.getViewport().getViewPosition();
				// g.drawImage(icon.getImage(), p.x, p.y, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		Btn_Manager = new JButton("관리");
		Btn_Manager.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Btn_Manager.setBackground(new Color(255, 255, 153));
		Btn_Manager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main_Manage MM = new Main_Manage();
				MM.setBounds(0, 0, 1000, 650);
				MM.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_Manager.setBackground(new Color(255, 255, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Btn_Manager.setBackground(new Color(255, 255, 153));
			}
		});
		background.setLayout(null);
		Btn_Manager.setBounds(617, 400, 80, 80);
		background.add(Btn_Manager);

		Btn_User = new JButton("냉장고");
		Btn_User.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Btn_User.setBackground(new Color(255, 255, 153));
		Btn_User.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main_User MU = new Main_User();
				MU.setBounds(0, 0, 1180, 740);
				MU.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_User.setBackground(new Color(255, 255, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Btn_User.setBackground(new Color(255, 255, 153));
			}
		});
		Btn_User.setBounds(480, 400, 80, 80);
		background.add(Btn_User);

		Btn_Cancel = new JButton("나가기");
		Btn_Cancel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Btn_Cancel.setBackground(new Color(255, 255, 153));
		Btn_Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_Cancel.setBackground(new Color(255, 255, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Btn_Cancel.setBackground(new Color(255, 255, 153));
			}
		});
		Btn_Cancel.setBounds(750, 400, 80, 80);
		background.add(Btn_Cancel);

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
	}
}
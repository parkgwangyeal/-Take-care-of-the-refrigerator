

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
	@brief Main_LoginŬ������ ������ - �α���â�� ��ü���� Ŭ���̺�Ʈ�� ����ϰ� ���ش�
	*///----------------------------------------------------------------------------------
	public Main_Login() {
		icon = new ImageIcon("�̹���/Login.png");
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ����

				// Approach 2: Scale image to size of component
				// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				// Approach 3: Fix the image position in the scroll pane
				// Point p = scrollPane.getViewport().getViewPosition();
				// g.drawImage(icon.getImage(), p.x, p.y, null);
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};

		Btn_Manager = new JButton("����");
		Btn_Manager.setFont(new Font("���� ���", Font.PLAIN, 15));
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

		Btn_User = new JButton("�����");
		Btn_User.setFont(new Font("���� ���", Font.PLAIN, 15));
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

		Btn_Cancel = new JButton("������");
		Btn_Cancel.setFont(new Font("���� ���", Font.PLAIN, 15));
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;



//-----------------------------------------------------------------------------------
/**
@class Del_MMaterial.java
@brief 관리자가 재료를 제거하기위한  클래스
*///----------------------------------------------------------------------------------


public class Del_MMaterial extends JFrame {
	private JScrollPane scrollPane;
	private ImageIcon icon;
	private JPanel background;
	private JLabel lblNewLabel;
	private JLabel Label_Name;
	private JButton Btn_Cancel;
	private JButton Btn_Finish;

	private JTextField Txt_Name;
	Pop p = new Pop();
	//-----------------------------------------------------------------------------------
	/**
	@brief Del_MMaterial클래스의 생성자 - 재료의이름을 입력받아 Material.db에서 재료를 삭제해준다
	*///----------------------------------------------------------------------------------
	public Del_MMaterial() {
		icon = new ImageIcon("이미지/littel_Del.png");

		background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ㅇㅇ
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		background.setLayout(null);

		lblNewLabel = new JLabel("식료품 삭제");
		lblNewLabel.setBounds(14, 12, 84, 18);
		background.add(lblNewLabel);

		Label_Name = new JLabel("재료 이름");
		Label_Name.setBounds(25, 89, 66, 18);
		background.add(Label_Name);

		Txt_Name = new JTextField();
		Txt_Name.setBounds(97, 87, 133, 24);
		background.add(Txt_Name);
		Txt_Name.setColumns(10);

		Btn_Cancel = new JButton("취소");
		Btn_Cancel.setBackground(new Color(240, 255, 255));
		Btn_Cancel.setBounds(150, 147, 66, 48);
		Btn_Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				p.Message("취소 되었습니다.");
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_Cancel.setBackground(new Color(175, 253, 253));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Btn_Cancel.setBackground(new Color(240, 255, 255));
			}
		});
		background.add(Btn_Cancel);

		Btn_Finish = new JButton("완료");
		Btn_Finish.setBackground(new Color(240, 255, 255));
		Btn_Finish.setBounds(62, 147, 66, 48);
		Btn_Finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dao_Material DM = new Dao_Material();
				DM.connection("Material");
				if (Txt_Name.getText().equals("")) {
					p.Message("삭제할 재료를 입력하세요.");
					return;
				} else {
					DM.mmeterial_del(Txt_Name.getText());
					DM.disconnection();
					setVisible(false);
				}
				DM.disconnection();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_Finish.setBackground(new Color(175, 253, 253));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Btn_Finish.setBackground(new Color(240, 255, 255));
			}
		});
		background.add(Btn_Finish);

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
	}
}
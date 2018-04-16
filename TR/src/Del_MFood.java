
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

//-----------------------------------------------------------------------------------
/**
@class Del_MFood.java
@brief 관리자가 음식을 제거하는  클래스

*///----------------------------------------------------------------------------------


public class Del_MFood extends JFrame {
	JScrollPane scrollPane;
	ImageIcon icon;
	JPanel background;
	JLabel Label_Name;
	JButton Btn_Finish;
	JButton Btn_Cancel;

	private JTextField Txt_Name;
	Pop p = new Pop();
	//-----------------------------------------------------------------------------------
		/**
		@brief Del_MFood클래스의 생성자 - 관리자가 음식의 이름을 적고 클릭이벤트를 통해 Material.db에서 지울수 있게 해준다
		*///----------------------------------------------------------------------------------
	public Del_MFood() {
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

		Label_Name = new JLabel("음식 이름");
		Label_Name.setBounds(46, 99, 57, 15);
		background.add(Label_Name);

		Txt_Name = new JTextField();
		Txt_Name.setBounds(115, 96, 116, 21);
		background.add(Txt_Name);
		Txt_Name.setColumns(10);

		Btn_Finish = new JButton("완료");
		Btn_Finish.setBackground(new Color(240, 255, 255));
		Btn_Finish.setBounds(62, 145, 65, 59);
		Btn_Finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dao_recipe Dr = new Dao_recipe();
				Dr.connection("Material");
				if (Txt_Name.getText().equals("")) {
					p.Message("삭제할 요리를 입력하세요.");
					return;
				} else {
					Dr.recipe_del(Txt_Name.getText());
					Dr.disconnection();
					p.Message("삭제 되었습니다.");
					setVisible(false);
				}
				Dr.disconnection();
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

		Btn_Cancel = new JButton("취소");
		Btn_Cancel.setBackground(new Color(240, 255, 255));
		Btn_Cancel.setBounds(139, 145, 65, 59);
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

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
	}
}

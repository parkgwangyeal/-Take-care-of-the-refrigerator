
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
@brief �����ڰ� ������ �����ϴ�  Ŭ����

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
		@brief Del_MFoodŬ������ ������ - �����ڰ� ������ �̸��� ���� Ŭ���̺�Ʈ�� ���� Material.db���� ����� �ְ� ���ش�
		*///----------------------------------------------------------------------------------
	public Del_MFood() {
		icon = new ImageIcon("�̹���/littel_Del.png");

		background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ����
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};

		background.setLayout(null);

		Label_Name = new JLabel("���� �̸�");
		Label_Name.setBounds(46, 99, 57, 15);
		background.add(Label_Name);

		Txt_Name = new JTextField();
		Txt_Name.setBounds(115, 96, 116, 21);
		background.add(Txt_Name);
		Txt_Name.setColumns(10);

		Btn_Finish = new JButton("�Ϸ�");
		Btn_Finish.setBackground(new Color(240, 255, 255));
		Btn_Finish.setBounds(62, 145, 65, 59);
		Btn_Finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dao_recipe Dr = new Dao_recipe();
				Dr.connection("Material");
				if (Txt_Name.getText().equals("")) {
					p.Message("������ �丮�� �Է��ϼ���.");
					return;
				} else {
					Dr.recipe_del(Txt_Name.getText());
					Dr.disconnection();
					p.Message("���� �Ǿ����ϴ�.");
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

		Btn_Cancel = new JButton("���");
		Btn_Cancel.setBackground(new Color(240, 255, 255));
		Btn_Cancel.setBounds(139, 145, 65, 59);
		Btn_Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				p.Message("��� �Ǿ����ϴ�.");
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

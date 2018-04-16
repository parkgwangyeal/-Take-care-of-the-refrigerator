
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

//-----------------------------------------------------------------------------------
/**
@class Add_MMaterial.java
@brief �����ڰ� ��Ḧ �߰��ϱ�����  Ŭ����

*///----------------------------------------------------------------------------------

public class Add_MMaterial extends JFrame {
	private JScrollPane scrollPane;
	private ImageIcon icon;
	private JPanel background;
	private JLabel Label_Name;
	private JLabel Label_Life;
	private JButton Btn_Finish;
	private JButton Btn_Cancel;
	private JTextField Txt_Name;
	private JTextField Txt_Life;
	Pop p = new Pop();


	//-----------------------------------------------------------------------------------
	/**
	@brief Add_MMaterialŬ������ ������ - �����ڰ� ��Ḧ �������� �ؽ�Ʈ�� ���������� �̿��ϸ� Ŭ���̺�Ʈ�� ����Ͽ� �ְ� ���ش�
	*///----------------------------------------------------------------------------------
	
	public Add_MMaterial() {
		
		icon = new ImageIcon("�̹���/littel_Add.png");

		background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ����
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		background.setLayout(null);

		Label_Name = new JLabel("��� �̸�");
		Label_Name.setBounds(44, 71, 57, 15);
		background.add(Label_Name);

		Label_Life = new JLabel("�������");
		Label_Life.setBounds(44, 122, 57, 15);
		background.add(Label_Life);

		Txt_Name = new JTextField();
		Txt_Name.setBounds(113, 68, 116, 21);
		background.add(Txt_Name);
		Txt_Name.setColumns(10);

		Txt_Life = new JTextField();
		Txt_Life.setColumns(10);
		Txt_Life.setBounds(113, 119, 116, 21);
		background.add(Txt_Life);

		Btn_Finish = new JButton("���");
		Btn_Finish.setBackground(new Color(240, 255, 255));
		Btn_Finish.setBounds(65, 173, 66, 48);
		Btn_Finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dao_Material AM = new Dao_Material();
				AM.connection("Material");
				
				
				if(Txt_Name.getText().equals("")){
					p.Message("�̸��� �Է����� �����̽��ϴ�.");
					return;
				}
				
				else if(Txt_Life.getText().equals("")){
					p.Message("��������� �Է����� �����̽��ϴ�.");
					return;
				}
			
				else
				{
				AM.mmeterial_add(Txt_Name.getText(), Txt_Life.getText());
				AM.disconnection();
				}
				setVisible(false);
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
		Btn_Cancel.setBounds(143, 173, 66, 48);
		Btn_Cancel.setBackground(new Color(240, 255, 255));
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
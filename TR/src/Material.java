
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//-----------------------------------------------------------------------------------
/**
@class Material.java
@brief ����,�߰��� ����ȱ���� �����ϴ� Ŭ����

*///----------------------------------------------------------------------------------


public class Material extends JFrame {
	private JScrollPane scrollPane;
	private ImageIcon icon;
	private Dimension d;
	private JLabel Label_Name;
	private JLabel Label_Amount;
	private JButton Btn_Cancel;
	private JScrollPane Scroll_Ref;
	private JScrollPane Scroll_Cold;
	private JScrollPane Scroll_Ordinary;
	protected JPanel contentPane;
	protected JTextField Txt_Amount;
	protected JTable table;
	protected JTable table_1;
	protected JTable table_2;
	protected JComboBox<Object> Combo_Name;
	protected JButton Btn_Finish;
	Pop p = new Pop();
	Search s = new Search();


	//-----------------------------------------------------------------------------------
	/**
	@brief MaterialŬ������ ������ - ��ῡ ���õ� UI�� ǥ���� �Ǵ� ������
	*///----------------------------------------------------------------------------------

	
	public Material() {
		icon = new ImageIcon("�̹���/U_Add.png");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 764);
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ����
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
		contentPane.setLayout(null);

		Label_Name = new JLabel("�̸�");
		Label_Name.setFont(new Font("���� ���", Font.PLAIN, 16));
		Label_Name.setBounds(74, 179, 107, 51);
		contentPane.add(Label_Name);

		Label_Amount = new JLabel("����");
		Label_Amount.setFont(new Font("���� ���", Font.PLAIN, 16));
		Label_Amount.setBounds(74, 272, 107, 39);
		contentPane.add(Label_Amount);

		Txt_Amount = new JTextField();
		Txt_Amount.setBounds(195, 274, 116, 39);
		contentPane.add(Txt_Amount);
		Txt_Amount.setColumns(10);

		Btn_Cancel = new JButton("���");
		Btn_Cancel.setFont(new Font("���� ���", Font.PLAIN, 16));
		Btn_Cancel.setBounds(405, 508, 86, 59);
		Btn_Cancel.setBackground(new Color(245, 245, 245));
		Btn_Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main_User user = new Main_User();
				p.Message("��� �Ǿ����ϴ�.");
				user.setVisible(true);
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_Cancel.setBackground(new Color(220, 220, 220));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Btn_Cancel.setBackground(new Color(245, 245, 245));
			}
		});
		contentPane.add(Btn_Cancel);

		Btn_Finish = new JButton("�Ϸ�");
		Btn_Finish.setBounds(405, 408, 86, 59);
		Btn_Finish.setBackground(new Color(245, 245, 245));
		Btn_Finish.setFont(new Font("���� ���", Font.PLAIN, 16));
		Btn_Finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_Finish.setBackground(new Color(220, 220, 220));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Btn_Finish.setBackground(new Color(245, 245, 245));
			}
		});
		contentPane.add(Btn_Finish);

		Scroll_Ref = new JScrollPane();
		Scroll_Ref.setBounds(603, 89, 334, 153);
		contentPane.add(Scroll_Ref);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] { { " ", " ", " " }, }, new String[] { "�̸�", "����", "��ȿ�Ⱓ" }) {
					boolean[] columnEditables = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		s.searchT_list("�õ�", table);//
		Scroll_Ref.setViewportView(table);

		Scroll_Cold = new JScrollPane();
		Scroll_Cold.setBounds(607, 299, 330, 153);
		contentPane.add(Scroll_Cold);

		table_1 = new JTable();
		table_1.setModel(
				new DefaultTableModel(new Object[][] { { " ", " ", " " }, }, new String[] { "�̸�", "����", "��ȿ�Ⱓ" }) {
					boolean[] columnEditables = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		s.searchT_list("����", table_1);
		Scroll_Cold.setViewportView(table_1);

		Scroll_Ordinary = new JScrollPane();
		Scroll_Ordinary.setBounds(609, 497, 328, 158);
		contentPane.add(Scroll_Ordinary);

		table_2 = new JTable();
		table_2.setModel(
				new DefaultTableModel(new Object[][] { { " ", " ", " " }, }, new String[] { "�̸�", "����", "��ȿ�Ⱓ" }) {
					boolean[] columnEditables = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		s.searchT_list("���", table_2);
		Scroll_Ordinary.setViewportView(table_2);

		Combo_Name = new JComboBox();

		s.searchT_list(Combo_Name);

		Combo_Name.setBounds(195, 182, 116, 48);
		contentPane.add(Combo_Name);

		scrollPane = new JScrollPane(contentPane);
		setContentPane(scrollPane);
	}
}



import java.awt.Color;
import java.awt.Dimension;

//import Dao_user;
//import Dto;
//import Table_model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;


//-----------------------------------------------------------------------------------
/**
@class Main_User.java
@brief ������ ���α׷����â�� �����ִ� Ŭ����
*///---------------------------------------------------------------------------------
public class Main_User extends JFrame {
	private JScrollPane scrollPane;
	private ImageIcon icon;
	private JPanel background;
	private JButton Btn_Add;
	private JButton Btn_Del;
	private JButton Btn_view;
	private JButton Btn_Back;
	private JButton Btn_Category;
	private JButton Btn_Life;
	private JScrollPane ScrollPane_List;
	private JScrollPane ScrollPane_Life;
	private JTable m_table;// ���� ���� ��� ����Ʈ
	private JTable expire_table; // ������� �� ��ȿ�Ⱓ �ӹ� ����Ʈ
	private String select_item;
	private int row;
	private int cou;
	private Object ss;
	Pop p = new Pop();
	private JLabel label_1;
	//-----------------------------------------------------------------------------------
	/**
	@brief Main_UserŬ������ ������
	@details ����â�� �����ֱ����� ��ư��� ���̺�����ִ�.
	
	*///----------------------------------------------------------------------------------

	
	public Main_User() {
		this.icon = new ImageIcon("�̹���/User.jpg");
		this.background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ����
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		this.background.setLayout(null);
		
		////////////////////////////////////////////////////// �������

		try {
			Dao_user controller = new Dao_user();
			controller.connection("UserTable");
			Expire Ex = new Expire();
			String Current = Ex.dTime; // ���糯¥
			ArrayList<Dto> StockList = controller.searchDate(); // �԰�¥
			ArrayList<Dto> ExpireList = controller.searchExpire();// �⺻�������
			ArrayList<Dto> NameList = controller.searchName();

			for (int i = 0; i < StockList.size(); i++) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
				Date beginDate = formatter.parse(Current);
				Date endDate = formatter.parse(StockList.get(i).getDate());
				long diff = beginDate.getTime() - endDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				int temp = (int) (ExpireList.get(i).getExpire() - diffDays);
				String name = NameList.get(i).getName();
				int expire = ExpireList.get(i).getExpire();
				controller.UpdateStorage(temp, name, expire);
			}
			controller.disconnection();
		} catch (ParseException e) {
			
		}
		try {
			setBounds(0, 0, 1180, 740);
			// �߰���ư********************************************************************
			this.Btn_Add = new JButton("�߰�");
			this.Btn_Add.setFont(new Font("���� ���", Font.PLAIN, 18));
			this.Btn_Add.setBackground(new Color(255, 222, 173));
			this.Btn_Add.setBounds(705, 314, 123, 50);
			this.Btn_Add.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Add_UMaterial fa = new Add_UMaterial();
					fa.setBounds(0, 0, 1023, 764);
					fa.setVisible(true);
					setVisible(false);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Btn_Add.setBackground(new Color(255, 160, 122));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					Btn_Add.setBackground(new Color(255, 222, 173));
				}
			});
			background.add(Btn_Add);
			// �߰���ư
			// ��*****************************************************************
			// ������ư*******************************************************************
			Btn_Del = new JButton("����");
			Btn_Del.setBackground(new Color(255, 222, 173));
			Btn_Del.setFont(new Font("���� ���", Font.PLAIN, 18));
			Btn_Del.setBounds(893, 314, 123, 50);
			Btn_Del.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Del_UMaterial fd = new Del_UMaterial();
					fd.setBounds(0, 0, 1023, 764);
					fd.setVisible(true);
					setVisible(false);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Btn_Del.setBackground(new Color(255, 160, 122));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					Btn_Del.setBackground(new Color(255, 222, 173));
				}
			});
			background.add(Btn_Del);
			// ������ư
			// ��****************************************************************
			// �丮���� ��ư*********************************************************
			Btn_view = new JButton("�޴�����");
			Btn_view.setBackground(new Color(255, 222, 173));
			Btn_view.setFont(new Font("���� ���", Font.PLAIN, 18));
			Btn_view.setBounds(621, 402, 123, 50);
			Btn_view.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(ss!=null){
					View_Recipe view = new View_Recipe(ss);
					view.setBounds(0, 0, 1280, 740);
					view.setVisible(true);
					setVisible(false);
					}else{
						p.Message("��Ḧ �����ϼ���");
						return;
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Btn_view.setBackground(new Color(255, 160, 122));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					Btn_view.setBackground(new Color(255, 222, 173));
				}
			});
			background.add(Btn_view);
			// �丮���� ��ư
			// ��*********************************************************
			// �ڷΰ���
			// ��ư***********************************************************
			Btn_Back = new JButton("���ư���");
			Btn_Back.setBackground(SystemColor.controlHighlight);
			Btn_Back.setBounds(1015, 610, 95, 35);
			Btn_Back.setFont(new Font("���� ���", Font.PLAIN, 14));
			Btn_Back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Main_Login ML = new Main_Login();
					ML.setBounds(0, 0, 1000, 650);
					ML.setVisible(true);
					setVisible(false);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Btn_Back.setBackground(new Color(192, 192, 192));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					Btn_Back.setBackground(SystemColor.controlHighlight);
				}
			});
			background.add(Btn_Back);
			
			//�ڷΰ��� ��ư ��****************************************************
			//ī�װ� ��ư******************************************************
			
			Btn_Category = new JButton("ī�װ�");
			Btn_Category.setBackground(new Color(255, 222, 173));
			Btn_Category.setFont(new Font("���� ���", Font.PLAIN, 18));
			Btn_Category.setBounds(791, 402, 123, 50);
			Btn_Category.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Category ca = new Category();
					ca.setBounds(0,0,1070,685);
					ca.setVisible(true);
					setVisible(false);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Btn_Category.setBackground(new Color(255, 160, 122));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					Btn_Category.setBackground(new Color(255, 222, 173));
				}
			});
			background.add(Btn_Category);
			//ī�װ� ��ư ��****************************************************
			//������� ��ư******************************************************
			Btn_Life = new JButton("��ȿ�Ⱓ");
			Btn_Life.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			Btn_Life.setBackground(new Color(255, 222, 173));
			Btn_Life.setFont(new Font("���� ���", Font.PLAIN, 18));
			Btn_Life.setBounds(959, 402, 123, 50);
			Btn_Life.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Expire_list expire = new Expire_list();
					expire.setBounds(0,0,1272, 720);
					expire.setVisible(true);
					setVisible(false);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Btn_Life.setBackground(new Color(255, 160, 122));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					Btn_Life.setBackground(new Color(255, 222, 173));
				}
			});
			background.add(Btn_Life);
			//������� ��ư ��****************************************************
			scrollPane = new JScrollPane(background);
			setContentPane(scrollPane);

			Search search = new Search();
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			ScrollPane_List = new JScrollPane();
			ScrollPane_List.setBounds(39, 68, 309, 561);
			background.add(ScrollPane_List);

			m_table = new JTable();

			m_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			m_table.setModel(
					new DefaultTableModel(new Object[][] { { " ", " ", " " }, }, new String[] { "�̸�", "����", "��ȿ�Ⱓ" }) {
						boolean[] columnEditables = new boolean[] { false, false, false };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});

			search.searchT_list(m_table);// db�ҷ����� �� ����Ʈ�� �ִ� �Լ�

			ScrollPane_List.setViewportView(m_table);
			m_table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					row = m_table.getSelectedRow();
					ss = m_table.getValueAt(row,0);
					
				}
			});

			System.out.print(select_item);
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			ScrollPane_Life = new JScrollPane();
			ScrollPane_Life.setBounds(611, 68, 499, 225);
			background.add(ScrollPane_Life);

			expire_table = new JTable();
			ScrollPane_Life.setViewportView(expire_table);

			expire_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			expire_table.setModel(
					new DefaultTableModel(new Object[][] { { " ", " ", " " }, }, new String[] { "�̸�", "����", "��ȿ�Ⱓ" }) {
						boolean[] columnEditables = new boolean[] { false, false, false };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});

			search.searchT_list(5, expire_table);// db�ҷ����� �� ����Ʈ�� �ִ� �Լ�

			ScrollPane_List.setViewportView(m_table);
			
			JLabel label = new JLabel("\uC7AC\uB8CC\uB9AC\uC2A4\uD2B8");
			label.setFont(new Font("���� ���", Font.PLAIN, 12));
			label.setBounds(39, 43, 107, 29);
			background.add(label);
			
			label_1 = new JLabel("��ȿ�Ⱓ �ӹ� ����Ʈ");
			label_1.setFont(new Font("���� ���", Font.PLAIN, 12));
			label_1.setBounds(611, 43, 123, 29);
			background.add(label_1);

		} catch (Exception e) {
			
		}

	}
}

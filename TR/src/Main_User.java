

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
@brief 유저의 프로그램사용창을 보여주는 클래스
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
	private JTable m_table;// 유저 보유 재료 리스트
	private JTable expire_table; // 보유재료 중 유효기간 임박 리스트
	private String select_item;
	private int row;
	private int cou;
	private Object ss;
	Pop p = new Pop();
	private JLabel label_1;
	//-----------------------------------------------------------------------------------
	/**
	@brief Main_User클래스의 생성자
	@details 유저창을 보여주기위한 버튼들과 테이블들이있다.
	
	*///----------------------------------------------------------------------------------

	
	public Main_User() {
		this.icon = new ImageIcon("이미지/User.jpg");
		this.background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ㅇㅇ
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		this.background.setLayout(null);
		
		////////////////////////////////////////////////////// 유통기한

		try {
			Dao_user controller = new Dao_user();
			controller.connection("UserTable");
			Expire Ex = new Expire();
			String Current = Ex.dTime; // 현재날짜
			ArrayList<Dto> StockList = controller.searchDate(); // 입고날짜
			ArrayList<Dto> ExpireList = controller.searchExpire();// 기본유통기한
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
			// 추가버튼********************************************************************
			this.Btn_Add = new JButton("추가");
			this.Btn_Add.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
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
			// 추가버튼
			// 끝*****************************************************************
			// 삭제버튼*******************************************************************
			Btn_Del = new JButton("삭제");
			Btn_Del.setBackground(new Color(255, 222, 173));
			Btn_Del.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
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
			// 삭제버튼
			// 끝****************************************************************
			// 요리보기 버튼*********************************************************
			Btn_view = new JButton("메뉴보기");
			Btn_view.setBackground(new Color(255, 222, 173));
			Btn_view.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
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
						p.Message("재료를 선택하세요");
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
			// 요리보기 버튼
			// 끝*********************************************************
			// 뒤로가기
			// 버튼***********************************************************
			Btn_Back = new JButton("돌아가기");
			Btn_Back.setBackground(SystemColor.controlHighlight);
			Btn_Back.setBounds(1015, 610, 95, 35);
			Btn_Back.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
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
			
			//뒤로가기 버튼 끝****************************************************
			//카테고리 버튼******************************************************
			
			Btn_Category = new JButton("카테고리");
			Btn_Category.setBackground(new Color(255, 222, 173));
			Btn_Category.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
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
			//카테고리 버튼 끝****************************************************
			//유통기한 버튼******************************************************
			Btn_Life = new JButton("유효기간");
			Btn_Life.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			Btn_Life.setBackground(new Color(255, 222, 173));
			Btn_Life.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
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
			//유통기한 버튼 끝****************************************************
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
					new DefaultTableModel(new Object[][] { { " ", " ", " " }, }, new String[] { "이름", "개수", "유효기간" }) {
						boolean[] columnEditables = new boolean[] { false, false, false };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});

			search.searchT_list(m_table);// db불러오고 총 리스트에 넣는 함수

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
					new DefaultTableModel(new Object[][] { { " ", " ", " " }, }, new String[] { "이름", "개수", "유효기간" }) {
						boolean[] columnEditables = new boolean[] { false, false, false };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});

			search.searchT_list(5, expire_table);// db불러오고 총 리스트에 넣는 함수

			ScrollPane_List.setViewportView(m_table);
			
			JLabel label = new JLabel("\uC7AC\uB8CC\uB9AC\uC2A4\uD2B8");
			label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			label.setBounds(39, 43, 107, 29);
			background.add(label);
			
			label_1 = new JLabel("유효기간 임박 리스트");
			label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			label_1.setBounds(611, 43, 123, 29);
			background.add(label_1);

		} catch (Exception e) {
			
		}

	}
}



import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main_Manage extends JFrame {

   private JScrollPane scrollPane;
   private ImageIcon icon;
   private JPanel background;
   private JButton Btn_AddMaterial;
   private JButton Btn_DelMaterial;
   private JButton Btn_AddFood;
   private JButton Btn_DelFood;
   private JButton Btn_Back;
   
 //-----------------------------------------------------------------------------------
 	/**
 	@brief Main_ManagerŬ���� ������ - ������â�� ������ �� �ֶǷ�  ��ư�� �ǳڵ� �������� swing�� ���Ǿ���.
 	*///----------------------------------------------------------------------------------
   
   public Main_Manage() {

     this.icon = new ImageIcon("�̹���/Manager.png");

     this.background = new JPanel() {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
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

      // ��� ��� ��ư*********************************************
      this.Btn_AddMaterial = new JButton("��� ���");
      Btn_AddMaterial.setFont(new Font("���� ���", Font.PLAIN, 15));
      Btn_AddMaterial.setBackground(new Color(255, 228, 225));
      Btn_AddMaterial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Add_MMaterial AM = new Add_MMaterial();
				AM.setBounds(100, 100, 300, 300);
				AM.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_AddMaterial.setBackground(new Color(255, 204, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Btn_AddMaterial.setBackground(new Color(255, 228, 225));
			}
		});
      Btn_AddMaterial.setBounds(144, 96, 99, 34);
      background.add(Btn_AddMaterial);
      // ��� ��� ��ư ��********************************************
      // ��� ��� ���� ��ư******************************************
      this.Btn_DelMaterial = new JButton("��� ��� ����");
      Btn_DelMaterial.setFont(new Font("���� ���", Font.PLAIN, 15));
      Btn_DelMaterial.setBackground(new Color(255, 228, 225));
      Btn_DelMaterial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Del_MMaterial DM = new Del_MMaterial();
				DM.setBounds(100, 100, 300, 300);
				DM.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_DelMaterial.setBackground(new Color(255, 204, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Btn_DelMaterial.setBackground(new Color(255, 228, 225));
			}
		});
      Btn_DelMaterial.setBounds(381, 96, 141, 34);
      background.add(Btn_DelMaterial);
      // ��� ��� ���� ��ư ��*****************************************
      // ���� ��� ��ư*********************************************
      this.Btn_AddFood = new JButton("�丮 ���");
      Btn_AddFood.setFont(new Font("���� ���", Font.PLAIN, 15));
      Btn_AddFood.setBackground(new Color(255, 228, 225));
      Btn_AddFood.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Add_MFood AM = new Add_MFood();
				AM.setBounds(100, 100, 580, 560);
				AM.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_AddFood.setBackground(new Color(255, 204, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Btn_AddFood.setBackground(new Color(255, 228, 225));
			}
		});
      Btn_AddFood.setBounds(144, 185, 99, 34);
      background.add(Btn_AddFood);
      // ���� ��� ��ư ��**********************************************
      // ��� �丮 ����*********************************************
      this.Btn_DelFood = new JButton("��� �丮 ����");
      Btn_DelFood.setFont(new Font("���� ���", Font.PLAIN, 15));
      Btn_DelFood.setBackground(new Color(255, 228, 225));
      Btn_DelFood.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Del_MFood DM = new Del_MFood();
				DM.setBounds(100, 100, 300, 300);
				DM.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Btn_DelFood.setBackground(new Color(255, 204, 204));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Btn_DelFood.setBackground(new Color(255, 228, 225));
			}
		});
      Btn_DelFood.setBounds(381, 185, 141, 34);
      background.add(Btn_DelFood);
      // ��� �丮 ���� ��********************************************
      // ���ư��� ��ư*********************************************
      this.Btn_Back = new JButton("���ư���");
      Btn_Back.setFont(new Font("���� ���", Font.PLAIN, 15));
      Btn_Back.setBackground(SystemColor.control);
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
      Btn_Back.setBounds(842, 540, 99, 34);
      background.setLayout(null);
      background.add(Btn_Back);
      //���ư��� ��ư ��*************************************
      
      this.scrollPane = new JScrollPane(background);
      setContentPane(scrollPane);

   }
}
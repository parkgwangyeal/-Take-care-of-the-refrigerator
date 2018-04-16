import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

//-----------------------------------------------------------------------------------
/**
@class Add_MFood.java
@brief 관리자가 음식을 추가하기위한  클래스

*///----------------------------------------------------------------------------------

public class Add_MFood extends JFrame{
   private JScrollPane scrollPane;
   private ImageIcon icon;
   private JTextField Text_;
   private JTextField textField_1;
   private String a;
   private JComboBox comboBox;
   public JLabel lblNewLabel;
   public JTextArea textArea;
   public JTextArea textArea_1;
   Pop p = new Pop();  
	//-----------------------------------------------------------------------------------
	/**
	@brief Add_MFood클래스의 생성자 - 요리이름,주재료,레시피,카테고리,이미지를 입력받아 Material.db에 요리를 등록한다.
	*///----------------------------------------------------------------------------------
   public Add_MFood() {
      icon = new ImageIcon("이미지/littel_Add.png");

      JPanel background = new JPanel() {
         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ㅇㅇ
            setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
            super.paintComponent(g);
         }
      };
      
      background.setLayout(null);
      
      JLabel Label_AddFood = new JLabel("요리 이름");
      Label_AddFood.setBounds(51, 35, 57, 15);
      background.add(Label_AddFood);
      
      JLabel Label_MainMaterial = new JLabel("주재료");
      Label_MainMaterial.setBounds(51, 63, 57, 15);
      background.add(Label_MainMaterial);
      
      JLabel Label_SubMaterial = new JLabel("레시피");
      Label_SubMaterial.setBounds(51, 182, 57, 15);
      background.add(Label_SubMaterial);
      
      JLabel Label_Recipe = new JLabel("카테고리");
      Label_Recipe.setBounds(51, 347, 57, 15);
      background.add(Label_Recipe);
      
      JLabel Label_Image = new JLabel("이미지");
      Label_Image.setBounds(51, 377, 57, 15);
      background.add(Label_Image);
      
      Text_ = new JTextField();
      Text_.setBounds(120, 32, 116, 21);
      background.add(Text_);
      Text_.setColumns(10);
      
      textField_1 = new JTextField();
      textField_1.setColumns(10);
      textField_1.setBounds(120, 60, 244, 107);
      background.add(textField_1);
      
      JButton Btn_Image = new JButton("이미지 선택");
      Btn_Image.setBackground(new Color(240, 255, 255));
      Btn_Image.setBounds(120, 373, 116, 23);
      Btn_Image.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Choose file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    a= fileopen.getSelectedFile().toString();
                }
         }
         @Override
         public void mouseEntered(MouseEvent e) {
            Btn_Image.setBackground(new Color(175, 253, 253));            
         }
         @Override
         public void mouseExited(MouseEvent e) {
            Btn_Image.setBackground(new Color(240, 255, 255));
         }
      });
      background.add(Btn_Image);
      
      JButton Btn_Finish = new JButton("등록");
      Btn_Finish.setBackground(new Color(240, 255, 255));
      Btn_Finish.setBounds(353, 489, 70, 44);
      Btn_Finish.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
        	 Dao_recipe Dr = new Dao_recipe();
        	 Dr.connection("Material");
        	 if (Text_.getText().equals("")) {
					p.Message("요리 이름을 입력하세요.");
					return;
				} else if (textField_1.getText().equals("")) {
					p.Message("재료를 입력하세요.");
					return;
				} else if (textArea.getText().equals("")) {
					p.Message("레시피를 입력하세요.");
					return;
				} else if (a==null) {
					p.Message("이미지를 불러오세요");
					return;
				} else if(textArea_1.getText().equals("")){
					p.Message("영양소를 입력하세요");
					return;
				}
				else{
					
					try {
						Copy cp = new Copy();
						cp.Copy_image(a);
						Dr.recipe_add(Text_.getText(), textField_1.getText(), textArea.getText(), comboBox.getSelectedItem(),a,textArea_1.getText());

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
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
      
      JButton Btn_Cancel = new JButton("취소");
      Btn_Cancel.setBackground(new Color(240, 255, 255));
      Btn_Cancel.setBounds(446, 489, 70, 44);
      Btn_Cancel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            setVisible(false);
            p.Message("취소 되었습니다.");
         }
         @Override
         public void mouseEntered(MouseEvent e) {
            Btn_Cancel.setBackground(new Color(175, 253, 253));            
         }
         @Override
         public void mouseExited(MouseEvent e) {}
      });
      background.add(Btn_Cancel);
      
      scrollPane = new JScrollPane(background);
      
      comboBox = new JComboBox();
      comboBox.addItem("밥");
      comboBox.addItem("국");
      comboBox.addItem("반찬");
      comboBox.addItem("특식");
      comboBox.addItem("계절음식");
      comboBox.setBounds(122, 342, 116, 20);
      background.add(comboBox);
      
      lblNewLabel = new JLabel("영양소");
      lblNewLabel.setBounds(51, 418, 42, 28);
      background.add(lblNewLabel);
      
      
      JScrollPane scrollPane_1 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane_1.setBounds(125, 192, 239, 132);
      background.add(scrollPane_1);
      
      textArea = new JTextArea();
      textArea.setLineWrap(true);
      scrollPane_1.setViewportView(textArea);
      
      JScrollPane scrollPane_2 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane_2.setBounds(120, 408, 244, 69);
      background.add(scrollPane_2);
      
      textArea_1 = new JTextArea();
      textArea_1.setLineWrap(true);
      scrollPane_2.setViewportView(textArea_1);
      
      JLabel label = new JLabel("\uC591\uC2DD : \uC7AC\uB8CC , \uAC1C\uC218 , \uC7AC\uB8CC , \uAC1C\uC218...");
      label.setBounds(208, 170, 156, 15);
      background.add(label);
      setContentPane(scrollPane);
   }
}
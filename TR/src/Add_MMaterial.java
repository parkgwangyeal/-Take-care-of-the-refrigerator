
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
@brief 관리자가 재료를 추가하기위한  클래스

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
	@brief Add_MMaterial클래스의 생성자 - 관리자가 재료를 여러가지 텍스트와 파일추저를 이용하며 클릭이벤트를 사용하여 주가 해준다
	*///----------------------------------------------------------------------------------
	
	public Add_MMaterial() {
		
		icon = new ImageIcon("이미지/littel_Add.png");

		background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);// ㅇㅇ
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		background.setLayout(null);

		Label_Name = new JLabel("재료 이름");
		Label_Name.setBounds(44, 71, 57, 15);
		background.add(Label_Name);

		Label_Life = new JLabel("유통기한");
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

		Btn_Finish = new JButton("등록");
		Btn_Finish.setBackground(new Color(240, 255, 255));
		Btn_Finish.setBounds(65, 173, 66, 48);
		Btn_Finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dao_Material AM = new Dao_Material();
				AM.connection("Material");
				
				
				if(Txt_Name.getText().equals("")){
					p.Message("이름를 입력하지 않으셨습니다.");
					return;
				}
				
				else if(Txt_Life.getText().equals("")){
					p.Message("유통기한을 입력하지 않으셨습니다.");
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

		Btn_Cancel = new JButton("취소");
		Btn_Cancel.setBounds(143, 173, 66, 48);
		Btn_Cancel.setBackground(new Color(240, 255, 255));
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
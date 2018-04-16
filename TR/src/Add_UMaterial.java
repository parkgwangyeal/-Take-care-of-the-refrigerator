import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//-----------------------------------------------------------------------------------
/**
 * @class Add_UMaterial.java
 * @brief MaterialŬ�������� ��ӹ޾� ������ ����Ʈ�� ��Ḧ �߰� �ϴ� Ŭ����
 * 
 */// ----------------------------------------------------------------------------------
public class Add_UMaterial extends Material {

	private JTextField Text_Expire;
	private JLabel label;
	private JLabel Label_Storage;
	private JLabel Label_Expire;
	private JRadioButton Radio_Ref;
	private JRadioButton Radio_Cold;
	private JRadioButton Radio_Ordinary;
	private ButtonGroup bg;
	private Pop p = new Pop();

	// -----------------------------------------------------------------------------------
	/**
	 * @brief Add_UMaterial Ŭ������ ������ - �ؽ�Ʈ���ϰ� ��ư�� �̿��Ͽ� ����Ʈ�� ������ �߰��Ѵ�.
	 */// ----------------------------------------------------------------------------------

	public Add_UMaterial() {
		Btn_Finish.setLocation(405, 408);

		label = new JLabel("����߰�");
		label.setFont(new Font("���� ���", Font.PLAIN, 16));
		label.setBounds(170, 101, 72, 39);
		contentPane.add(label);

		Label_Expire = new JLabel("��ȿ�Ⱓ");
		Label_Expire.setFont(new Font("���� ���", Font.PLAIN, 16));
		Label_Expire.setBounds(74, 338, 107, 51);
		contentPane.add(Label_Expire);

		Text_Expire = new JTextField();
		Text_Expire.setBounds(195, 351, 116, 38);
		contentPane.add(Text_Expire);
		Text_Expire.setColumns(10);

		Label_Storage = new JLabel("����� ��ġ");
		Label_Storage.setFont(new Font("���� ���", Font.PLAIN, 16));
		Label_Storage.setBounds(74, 453, 86, 44);
		contentPane.add(Label_Storage);

		Radio_Ref = new JRadioButton("�õ�");
		Radio_Ref.setFont(new Font("���� ���", Font.PLAIN, 16));
		Radio_Ref.setBounds(204, 462, 57, 27);
		contentPane.add(Radio_Ref);

		Radio_Cold = new JRadioButton("����");
		Radio_Cold.setFont(new Font("���� ���", Font.PLAIN, 16));
		Radio_Cold.setBounds(204, 514, 57, 27);
		contentPane.add(Radio_Cold);

		Radio_Ordinary = new JRadioButton("���");
		Radio_Ordinary.setFont(new Font("���� ���", Font.PLAIN, 16));
		Radio_Ordinary.setBounds(204, 571, 57, 27);
		contentPane.add(Radio_Ordinary);

		bg = new ButtonGroup();
		bg.add(Radio_Ref);
		bg.add(Radio_Cold);
		bg.add(Radio_Ordinary);

		Btn_Finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object temp = Combo_Name.getSelectedItem();
				String temp1 = temp.toString();
				String x = temp1;
				String y = Txt_Amount.getText();
				String z = Text_Expire.getText();
				String o;
				String d;
				String str = y;
				String str2 = z;
				
				if (Radio_Ref.isSelected()) {
					o = Radio_Ref.getText();
				}

				else if (Radio_Cold.isSelected()) {
					o = Radio_Cold.getText();
				} 
				else if (Radio_Ordinary.isSelected()) {
					o = Radio_Ordinary.getText();
				}
				else
					o = null;

				Expire date = new Expire();
				d = date.dTime;
				Dao_user controller = new Dao_user();
				controller.connection("UserTable");
				
				if (x.equals("")) {
					p.Message("�̸��� �Է����� �����̽��ϴ�.");
					return;
				}

				else if (y.equals("")) {
					p.Message("������ �Է����� �����̽��ϴ�.");
					return;
				}

				else if (o == null) {
					p.Message("����Ҹ� �Է����� �����̽��ϴ�.");
					return;
				}
				
				int num = Integer.parseInt(str);
				
				if (100 < num) {
					p.Message("100�� ���� �����մϴ�.");
					return;
				}

				else if (num < 0) {
					p.Message("�߸� �Է��ϼ̽��ϴ�(����:-)");
					return;
				}

				else{
					controller.InsertStorage(x, num, z, o, d);
					p.Message("�߰� �Ǿ����ϴ�.");
					Main_User MU = new Main_User();
					MU.setVisible(true);
					MU.setBounds(0, 0, 1180, 740);
					setVisible(false);
				}
			}
		});

	}
}

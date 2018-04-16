
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
@class Search.java
@brief UserTabled.db�� Material.db�� �о� ���ϴ� �����͸���Ʈ�� �˻��� �ִ� Ŭ�����̴�. 
*/
public class Search {
   public Dao controller;
   public Dao_user ucontroller;
   public ArrayList<Dto> addressList;
   public Object[] arrAdd;
   public Object[][] arrAdd2;
   
   /**
    @brief searchT_list�Լ� - Combo_Name�޺��ڽ��� ������ ��Ḯ��Ʈ �˻��ؼ� �־��ش�.
    @param Combo_Name
    */
   //�޺��ڽ��� ������ ��Ḯ��Ʈ �˻��ؼ� �־��ִ� �Լ� 
   public void searchT_list(JComboBox<Object> Combo_Name) {

      this.controller = new Dao();
      controller.connection("Material");

      this.addressList = controller.searchName();

      this.arrAdd = new Object[this.addressList.size()];
      for (int i = 0; i < this.addressList.size(); i++) {
         Dto t_code = this.addressList.get(i);
         this.arrAdd[i] = t_code.getName();
         Combo_Name.addItem(this.arrAdd[i]);
      }
      this.controller.disconnection();
   }
   /**
    @brief searchT_list�Լ� - t���̺� ����� ����� �� ��Ḯ��Ʈ �˻��ؼ� �־��ش�.
    @param t
    */
   //����� �� ��� ����Ʈ �˻��ؼ� ���̺� ���
   public void searchT_list(JTable t) {

      this.ucontroller = new Dao_user();
      this.ucontroller.connection("UserTable");
      this.addressList = this.ucontroller.searchStorage();
      
      this.arrAdd2 = new Object[addressList.size()][3];
      for (int i = 0; i < addressList.size(); i++) {
         Dto t_code = addressList.get(i);
         this.arrAdd2[i][0] = t_code.getName();
         this.arrAdd2[i][1] = t_code.getNum();
         this.arrAdd2[i][2] = t_code.getLife();
         t.setModel(new Table_model(this.arrAdd2));
      }
      this.ucontroller.disconnection();
   }
   
   /**
    @brief searchT_list�Լ� -����Һ� ����Ʈ �˻��ؼ� ���̺� ���
    @param storage,t
    */
   // ����Һ� ����Ʈ �˻��ؼ� ���̺� ���
   public void searchT_list(String storage,JTable t) {

      this.ucontroller = new Dao_user();
      this.ucontroller.connection("UserTable");
      this.addressList = this.ucontroller.searchStorage(storage);
      
      this.arrAdd2 = new Object[this.addressList.size()][3];

      for (int i = 0; i < this.addressList.size(); i++) {
         Dto t_code = this.addressList.get(i);
         this.arrAdd2[i][0] = t_code.getName();
         this.arrAdd2[i][1] = t_code.getNum();
         this.arrAdd2[i][2] = t_code.getLife();

         t.setModel(new Table_model(this.arrAdd2));
      }
      this.ucontroller.disconnection();
   }
   /**
    @brief searchT_list�Լ� - //��ȿ�Ⱓ �ӹ� ����Ʈ �˻��ؼ� ���̺� ���
    @param t,expire
    */
   
   public void searchT_list(int expire,JTable t) {

      this.ucontroller = new Dao_user();
      this.ucontroller.connection("UserTable");
      this.addressList = this.ucontroller.searchStorage(expire);

      this.arrAdd2 = new Object[this.addressList.size()][3];
      for (int i = 0; i < this.addressList.size(); i++) {
         Dto t_code = this.addressList.get(i);
         this.arrAdd2[i][0] = t_code.getName();
         this.arrAdd2[i][1] = t_code.getNum();
         this.arrAdd2[i][2] = t_code.getLife();
         t.setModel(new Table_model(this.arrAdd2));
      }
      ucontroller.disconnection();
   }
}
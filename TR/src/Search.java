
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
@class Search.java
@brief UserTabled.db과 Material.db를 읽어 원하는 데이터리스트를 검색해 주는 클래스이다. 
*/
public class Search {
   public Dao controller;
   public Dao_user ucontroller;
   public ArrayList<Dto> addressList;
   public Object[] arrAdd;
   public Object[][] arrAdd2;
   
   /**
    @brief searchT_list함수 - Combo_Name콤보박스에 관리자 재료리스트 검색해서 넣어준다.
    @param Combo_Name
    */
   //콤보박스에 관리자 재료리스트 검색해서 넣어주는 함수 
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
    @brief searchT_list함수 - t테이블에 사용자 냉장고 총 재료리스트 검색해서 넣어준다.
    @param t
    */
   //냉장고 총 재료 리스트 검색해서 테이블에 등록
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
    @brief searchT_list함수 -저장소별 리스트 검색해서 테이블에 등록
    @param storage,t
    */
   // 저장소별 리스트 검색해서 테이블에 등록
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
    @brief searchT_list함수 - //유효기간 임박 리스트 검색해서 테이블에 등록
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
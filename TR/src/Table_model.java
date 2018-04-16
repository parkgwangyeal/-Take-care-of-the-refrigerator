
import javax.swing.table.AbstractTableModel;


//-----------------------------------------------------------------------------------
/**
* @class Table_model.java
* @brief 테이블 가져오는것들에 대한 값을 모아주는 함수 클래스
*/// ----------------------------------------------------------------------------------

public class Table_model extends AbstractTableModel {

	String[] columNames =
		{"이름","개수","유효기간"};
	
	Object[][] data = {{" "," "," "}};
       
 
    // 실험중
    public Table_model(Object[][] data) {
           this.data = data;
    }
    
    @Override
    public int getColumnCount() {
    	return columNames.length;
    }
 
    @Override
    public int getRowCount() {
    	return data.length;           
    }
 
    @Override
    public Object getValueAt(int arg0,int arg1) {
        return data[arg0][arg1];
    }
    
    public Object getValueAt(int arg0) {
        return data[arg0][0];
    }
 
    @Override
    public String getColumnName(int arg0) {
    	return columNames[arg0];
    }

}

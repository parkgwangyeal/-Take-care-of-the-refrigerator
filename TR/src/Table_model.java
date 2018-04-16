
import javax.swing.table.AbstractTableModel;


//-----------------------------------------------------------------------------------
/**
* @class Table_model.java
* @brief ���̺� �������°͵鿡 ���� ���� ����ִ� �Լ� Ŭ����
*/// ----------------------------------------------------------------------------------

public class Table_model extends AbstractTableModel {

	String[] columNames =
		{"�̸�","����","��ȿ�Ⱓ"};
	
	Object[][] data = {{" "," "," "}};
       
 
    // ������
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

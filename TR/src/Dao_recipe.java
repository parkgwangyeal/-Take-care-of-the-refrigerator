
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//-----------------------------------------------------------------------------------
/**
@class Dao_recipe.java
@brief CoonŬ������ ��ӹ޾Ƽ� ��� ����ϰ� ������ DBtable�� ���õ� �������� ������ �ִ� Ŭ����
*///----------------------------------------------------------------------------------

public class Dao_recipe extends Conec {

private Pop p = new Pop();
	//-----------------------------------------------------------------------------------
		/**
		@brief Dao_recipeŬ������ �Լ� - �丮�� �ؽ�Ʈ�ʵ�� ���������� �̿��ؼ� �߰���ư���� ������ DB�� �־��ش�.
		@param string �̸�
		@param string2 ���
		@param string3 ������
		@param string4 Ÿ��
		@param file ��������
		*///----------------------------------------------------------------------------------
	
	void recipe_add(String string, String string2, String string3, Object string4, String file,String string5) {
		try {
			String fileName = file.substring(file.lastIndexOf('\\') + 1, file.length());
			String desFile = null;
			try {
				
				desFile = new File(".").getCanonicalPath() + "\\�̹���\\" + fileName;

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FileInputStream fis = new FileInputStream(file);
			if(fileName.substring(fileName.length()-3, fileName.length()).equals("png") || fileName.substring(fileName.length()-3, fileName.length()).equals("jpg"))
			{
				
			String sql = "INSERT INTO Recipe (rname,item,recipe,type,image,nutrient) " + "VALUES ('" + string + "','" + string2
					+ "','" + string3 + "','" + string4 + "','" + fileName + "','"+string5+"');";
			p.Message("��� �Ǿ����ϴ�.");
			stmt.executeUpdate(sql);
			// Reset Text Fields
			stmt.close();
			conn.commit();
			conn.close();
			
			}
			else p.Message("�̹��� ������ �ƴմϴ�.");
		} catch (Exception e1) {
			
			
		}
	}

	//-----------------------------------------------------------------------------------
	/**
	@brief Dao_recipeŬ������ �Լ� - �丮�� �ؽ�Ʈ�ʵ�� ��ư�� �̿��ؼ� ������ DB�� ������Ų��.
	@param a �丮�̸�
	*///----------------------------------------------------------------------------------

	void recipe_del(String a) {

		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM recipe  " + "WHERE rname='" + a + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();

		} catch (Exception e) {
			
		}

	}

}

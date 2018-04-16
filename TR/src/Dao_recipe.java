
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//-----------------------------------------------------------------------------------
/**
@class Dao_recipe.java
@brief Coon클래스를 상속받아서 디비를 사용하고 레시피 DBtable에 관련된 쿼리문을 가지고 있는 클래스
*///----------------------------------------------------------------------------------

public class Dao_recipe extends Conec {

private Pop p = new Pop();
	//-----------------------------------------------------------------------------------
		/**
		@brief Dao_recipe클래스의 함수 - 요리를 텍스트필드와 파일추저를 이용해서 추가버튼으로 레시피 DB에 넣어준다.
		@param string 이름
		@param string2 재료
		@param string3 레시피
		@param string4 타입
		@param file 파일추저
		*///----------------------------------------------------------------------------------
	
	void recipe_add(String string, String string2, String string3, Object string4, String file,String string5) {
		try {
			String fileName = file.substring(file.lastIndexOf('\\') + 1, file.length());
			String desFile = null;
			try {
				
				desFile = new File(".").getCanonicalPath() + "\\이미지\\" + fileName;

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FileInputStream fis = new FileInputStream(file);
			if(fileName.substring(fileName.length()-3, fileName.length()).equals("png") || fileName.substring(fileName.length()-3, fileName.length()).equals("jpg"))
			{
				
			String sql = "INSERT INTO Recipe (rname,item,recipe,type,image,nutrient) " + "VALUES ('" + string + "','" + string2
					+ "','" + string3 + "','" + string4 + "','" + fileName + "','"+string5+"');";
			p.Message("등록 되었습니다.");
			stmt.executeUpdate(sql);
			// Reset Text Fields
			stmt.close();
			conn.commit();
			conn.close();
			
			}
			else p.Message("이미지 파일이 아닙니다.");
		} catch (Exception e1) {
			
			
		}
	}

	//-----------------------------------------------------------------------------------
	/**
	@brief Dao_recipe클래스의 함수 - 요리를 텍스트필드와 버튼를 이용해서 레시피 DB에 삭제시킨다.
	@param a 요리이름
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

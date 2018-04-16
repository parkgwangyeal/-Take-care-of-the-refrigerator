

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//-----------------------------------------------------------------------------------
/**
@class Copy.java
@brief Coon클래스를 상속받아서 디비를 사용하고 재료DBtable에 관련된 쿼리문을 가지고 있는 클래스

*///----------------------------------------------------------------------------------

public class Copy  {
	private int input=0, count=0;
	private byte[] data = new byte[1024];
	//-----------------------------------------------------------------------------------
		/**
		@brief Copy_image클래스의 함수 - 이미지파일로 파일을 카피해주는 역활을한다.
		@param cp 파일이름
		*///----------------------------------------------------------------------------------


void Copy_image(String cp) throws IOException {

  File file1 = new File(cp);
  String fileName = cp.substring(cp.lastIndexOf('\\') + 1, cp.length());
  File file2 = new File("이미지//"+fileName);
  
  long fsize1 = file1.length(); // 원본 파일 크기 변환
  
  FileInputStream fis = new FileInputStream(file1);
  FileOutputStream fos = new FileOutputStream(file2);
  
 
  System.out.print(cp);
  while((input=fis.read(data))!=-1){
   fos.write(data, 0, input);
  count+=input;
   float per = ((float)count/fsize1) * 100;
   
  }

 } 
}


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//-----------------------------------------------------------------------------------
/**
@class Copy.java
@brief CoonŬ������ ��ӹ޾Ƽ� ��� ����ϰ� ���DBtable�� ���õ� �������� ������ �ִ� Ŭ����

*///----------------------------------------------------------------------------------

public class Copy  {
	private int input=0, count=0;
	private byte[] data = new byte[1024];
	//-----------------------------------------------------------------------------------
		/**
		@brief Copy_imageŬ������ �Լ� - �̹������Ϸ� ������ ī�����ִ� ��Ȱ���Ѵ�.
		@param cp �����̸�
		*///----------------------------------------------------------------------------------


void Copy_image(String cp) throws IOException {

  File file1 = new File(cp);
  String fileName = cp.substring(cp.lastIndexOf('\\') + 1, cp.length());
  File file2 = new File("�̹���//"+fileName);
  
  long fsize1 = file1.length(); // ���� ���� ũ�� ��ȯ
  
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
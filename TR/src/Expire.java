

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//-----------------------------------------------------------------------------------
/**
@class Expire.java
@brief ��������� ����ϱ����� Ŭ����

*///----------------------------------------------------------------------------------

public class Expire {

	public String dTime;
	private SimpleDateFormat formatter;
	private Date currentTime;
	//-----------------------------------------------------------------------------------
		/**
		@brief ExpireŬ������ ������ - �����ð��� �޾ƿͼ� ������ִ� �Լ�
		*///----------------------------------------------------------------------------------
	public Expire(){
		this.formatter = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
		this.currentTime = new Date ( );
		dTime = formatter.format ( currentTime );
	}
	public String printDate(){
		return dTime;
	}
}
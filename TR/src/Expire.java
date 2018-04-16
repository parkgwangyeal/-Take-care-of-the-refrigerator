

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//-----------------------------------------------------------------------------------
/**
@class Expire.java
@brief 유통기한을 계산하기위한 클래스

*///----------------------------------------------------------------------------------

public class Expire {

	public String dTime;
	private SimpleDateFormat formatter;
	private Date currentTime;
	//-----------------------------------------------------------------------------------
		/**
		@brief Expire클래스의 생성자 - 현제시간을 받아와서 계산해주는 함수
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


//-----------------------------------------------------------------------------------
/**
 * @class Dto.java
 * @brief 데이터를 전달하는 객체 클래스
 */// ----------------------------------------------------------------------------------

public class Dto {
	//재료
    private String name;
    private Integer num;
    private Integer expire;
    private String storage;
    private String date;
    private int life;
   
    //레시피
    private String item;//재료들
    private String recipe;
    private String type;//밥인지 국인지 하는 의미의 타입
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getExpire() {
		return expire;
	}
	public void setExpire(Integer expire) {
		this.expire = expire;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date=date;
	}
	public int getLife(){
		return life;
	}
	public void setLife(int life){
		this.life = life;
	}
}
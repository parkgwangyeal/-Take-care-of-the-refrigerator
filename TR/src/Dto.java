

//-----------------------------------------------------------------------------------
/**
 * @class Dto.java
 * @brief �����͸� �����ϴ� ��ü Ŭ����
 */// ----------------------------------------------------------------------------------

public class Dto {
	//���
    private String name;
    private Integer num;
    private Integer expire;
    private String storage;
    private String date;
    private int life;
   
    //������
    private String item;//����
    private String recipe;
    private String type;//������ ������ �ϴ� �ǹ��� Ÿ��
    
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
package truc;

public class UserVO {


	private String id;
	private String pw;
	private String name;
	private String num1;
	private String num2;
	private String num3;
	private String email1;
	private String email2;
	private String add1;
	private String add2;
	
	
	public UserVO() {

	}


	public UserVO(String id, String pw, String name, String num1, String num2, String num3, String email1,
			String email2, String add1, String add2) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.email1 = email1;
		this.email2 = email2;
		this.add1 = add1;
		this.add2 = add2;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNum1() {
		return num1;
	}


	public void setNum1(String num1) {
		this.num1 = num1;
	}


	public String getNum2() {
		return num2;
	}


	public void setNum2(String num2) {
		this.num2 = num2;
	}


	public String getNum3() {
		return num3;
	}


	public void setNum3(String num3) {
		this.num3 = num3;
	}


	public String getEmail1() {
		return email1;
	}


	public void setEmail1(String email1) {
		this.email1 = email1;
	}


	public String getEmail2() {
		return email2;
	}


	public void setEmail2(String email2) {
		this.email2 = email2;
	}


	public String getAdd1() {
		return add1;
	}


	public void setAdd1(String add1) {
		this.add1 = add1;
	}


	public String getAdd2() {
		return add2;
	}


	public void setAdd2(String add2) {
		this.add2 = add2;
	}


	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", name=" + name + ", num1=" + num1 + ", num2=" + num2 + ", num3="
				+ num3 + ", email1=" + email1 + ", email2=" + email2 + ", add1=" + add1 + ", add2=" + add2 + "]";
	}


	

	
	
	
}

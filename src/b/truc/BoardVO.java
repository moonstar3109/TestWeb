package b.truc;

import java.sql.Timestamp;

public class BoardVO {
	
	//테이블명과 동일하게 선언
	private int bno;
	private String name;
	private String title;
	private String content;
	private Timestamp regdate;

	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}


	public BoardVO(int bno, String name, String title, String content, Timestamp regdate) {
		super();
		this.bno = bno;
		this.name = name;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}


	public int getBno() {
		return bno;
	}


	public void setBno(int bno) {
		this.bno = bno;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}


	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", name=" + name + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + "]";
	}

	
	
	
	
}

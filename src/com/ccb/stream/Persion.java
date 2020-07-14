package com.ccb.stream;
// 人的实体类
public class Persion {
	private String name;
	public Persion() {
		// TODO Auto-generated constructor stub
	}
	public Persion(String name) {
		super();
		this.name = name;
	}
	static Persion build(String name) {
		Persion persion = new Persion();
		persion.setName(name);
		return persion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Persion [name=" + name + "]";
	}
	
	

}

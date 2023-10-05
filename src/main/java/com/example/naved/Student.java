package com.example.naved;

public class Student {
	private String date;
	private String name;
	private String descc;
	private long amt;
	private String category;
	private long bal;
	
	public Student(String date,String name,String descc,long amt,String category,long bal) {
		this.date=date;
		this.name=name;
		this.descc=descc;
		this.amt=amt;
		this.category=category;
		this.bal=bal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescc() {
		return descc;
	}

	public void setDescc(String descc) {
		this.descc = descc;
	}

	public long getAmt() {
		return amt;
	}

	public void setAmt(long amt) {
		this.amt = amt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getBal() {
		return bal;
	}

	public void setBal(long bal) {
		this.bal = bal;
	}
	
	
}

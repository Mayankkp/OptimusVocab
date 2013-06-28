package com.optimus.vocab;

import android.R.integer;

public class TestStatus {

	
	private int id ;
	private int isActive;
	private int isCompleted;
	private String dummyName;
	public TestStatus(int id, int isActive, int isCompleted , String dummyName) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.isCompleted = isCompleted;
		this.dummyName = dummyName;
	}
	public int getId() {
		return id;
	}
	public String  getDummyName() {
		return this.dummyName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(int isCompleted) {
		this.isCompleted = isCompleted;
	}
}

package com.me.coin.framework.dao;

public enum Op {
	
	And("and"),
	
	Or("or");
	
	private Op(String type) {
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}

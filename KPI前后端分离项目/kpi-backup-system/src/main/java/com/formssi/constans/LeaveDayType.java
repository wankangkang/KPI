package com.formssi.constans;

public enum LeaveDayType {

	L1("L1","年假"),
	L2("L2","事假"),
	L4("L4","陪产假"),
	;
	
	private String engDescription;
	private String chiDescription;
	
	private LeaveDayType(String engDescription, String chiDescription) {
		this.engDescription = engDescription;
		this.chiDescription = chiDescription;
	}
	
	public String getEngDescription() {
		return engDescription;
	}
	
	public void setEngDescription(String programSerialNum) {
		this.engDescription = programSerialNum;
	}
	
	public String getChiDescriptione() {
		return chiDescription;
	}
	
	public void setChiDescription(String programName) {
		this.chiDescription = programName;
	}
}

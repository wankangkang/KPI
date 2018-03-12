package com.formssi.constans;

public enum Rank {

	SA("SA","高级"),
	AP("AP","中级"),
	PR("PR","初级"),
	T1("T1","实训"),
	;
	
	private String engDescription;
	private String chiDescription;
	
	private Rank(String engDescription, String chiDescription) {
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

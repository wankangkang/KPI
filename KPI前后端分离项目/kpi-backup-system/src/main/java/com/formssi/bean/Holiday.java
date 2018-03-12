package com.formssi.bean;

public class Holiday {

	private Long holidayId;
	private String holidayType;
	private String holidayDate;
	private int year;
	private int month;
	private String title;
	private int vacation;

	public Long getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Long holidayId) {
		this.holidayId = holidayId;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getVacation() {
		return vacation;
	}

	public void setVacation(int vacation) {
		this.vacation = vacation;
	}

	@Override
	public String toString() {
		return "Holiday [holidayId=" + holidayId + ", holidayType=" + holidayType + ", holidayDate=" + holidayDate
				+ ", year=" + year + ", month=" + month + ", title=" + title + ", vacation=" + vacation + "]";
	}

}

package home.model.vo;

import java.sql.Date;

public class Notice {
	
	private int notNo;
	private String notTitle;
	private String notCont;
	private Date notDate;
	private String notAdmin;
	
	public Notice() {}

	public int getNotNo() {
		return notNo;
	}

	public void setNotNo(int notNo) {
		this.notNo = notNo;
	}

	public String getNotTitle() {
		return notTitle;
	}

	public void setNotTitle(String notTitle) {
		this.notTitle = notTitle;
	}

	public String getNotCont() {
		return notCont;
	}

	public void setNotCont(String notCont) {
		this.notCont = notCont;
	}

	public Date getNotDate() {
		return notDate;
	}

	public void setNotDate(Date notDate) {
		this.notDate = notDate;
	}

	public String getNotAdmin() {
		return notAdmin;
	}

	public void setNotAdmin(String notAdmin) {
		this.notAdmin = notAdmin;
	}

	@Override
	public String toString() {
		return "Notice [notNo=" + notNo + ", notTitle=" + notTitle + ", notCont=" + notCont + ", notDate=" + notDate
				+ ", notAdmin=" + notAdmin + "]";
	}
	
	
	
	
}

package home.model.vo;

import java.sql.Date;

public class CheerBoard {
	
	private int cbNo;
	private String cbCont;
	private Date cbDate;
	private String userId;
	
	public CheerBoard() {}

	public int getCbNo() {
		return cbNo;
	}

	public void setCbNo(int cbNo) {
		this.cbNo = cbNo;
	}

	public String getCbCont() {
		return cbCont;
	}

	public void setCbCont(String cbCont) {
		this.cbCont = cbCont;
	}

	public Date getCbDate() {
		return cbDate;
	}

	public void setCbDate(Date cbDate) {
		this.cbDate = cbDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CheerBoard [cbNo=" + cbNo + ", cbCont=" + cbCont + ", cbDate=" + cbDate + ", userId=" + userId + "]";
	}

}

package community.donBoard.model.vo;

import java.sql.Date;

public class DonateBoard {

	public DonateBoard() {}
	
	private int dbNo;
	private String dbTitle;
	private String dbCont;
	private Date dbDate;
	private String userId;
	
	public int getDbNo() {
		return dbNo;
	}
	public void setDbNo(int dbNo) {
		this.dbNo = dbNo;
	}
	public String getDbTitle() {
		return dbTitle;
	}
	public void setDbTitle(String dbTitle) {
		this.dbTitle = dbTitle;
	}
	public String getDbCont() {
		return dbCont;
	}
	public void setDbCont(String dbCont) {
		this.dbCont = dbCont;
	}
	public Date getDbDate() {
		return dbDate;
	}
	public void setDbDate(Date dbDate) {
		this.dbDate = dbDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}

package board.memBoard.model.vo;

import java.sql.Date;

public class MemberBoard {

	public MemberBoard() {}
	private int mbNo;
	private String mbTitle;
	private String mbCont;
	private Date mbDate;
	private String userId;
	public int getMbNo() {
		return mbNo;
	}
	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}
	public String getMbTitle() {
		return mbTitle;
	}
	public void setMbTitle(String mbTitle) {
		this.mbTitle = mbTitle;
	}
	public String getMbCont() {
		return mbCont;
	}
	public void setMbCont(String mbCont) {
		this.mbCont = mbCont;
	}
	public Date getMbDate() {
		return mbDate;
	}
	public void setMbDate(Date mbDate) {
		this.mbDate = mbDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}

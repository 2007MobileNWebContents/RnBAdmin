package community.donBoard.model.vo;

import java.sql.Date;

public class DonBoardComment {

	public DonBoardComment() {};
	private int donNo;
	private String commentContent;
	private String userId;
	private Date regDate;
	public int getDonNo() {
		return donNo;
	}
	public void setDonNo(int donNo) {
		this.donNo = donNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}

package board.notice.model.vo;

import java.sql.Date;

public class NoticeComment {

	public NoticeComment() {}
	
	private int comNo;
	private int notieceNo;
	private String commentContent;
	private String userId;
	private Date regDate;
	public int getComNo() {
		return comNo;
	}
	public void setComNo(int comNo) {
		this.comNo = comNo;
	}
	public int getNotieceNo() {
		return notieceNo;
	}
	public void setNotieceNo(int notieceNo) {
		this.notieceNo = notieceNo;
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
	@Override
	public String toString() {
		return "NoticeCommentDAO [comNo=" + comNo + ", notieceNo=" + notieceNo + ", commentContent=" + commentContent
				+ ", userId=" + userId + ", regDate=" + regDate + "]";
	}


}
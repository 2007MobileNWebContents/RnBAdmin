package resResult.model.vo;

import java.sql.Date;

public class ResPriv {

	private int rpNo;
	private String userId; 
	private Date rpDate;
	private String rpPlace; 
	private String rpTime; 
	private String rpKind; 
	private String rpState; 
	private String rpOn; 
	
	public ResPriv() {}
	
	public ResPriv(int rpNo, String userId, Date rpDate, String rpPlace, String rpTime, String rpKind, String rpState, String rpOn) {
		super();
		this.rpNo = rpNo;
		this.userId = userId;
		this.rpDate = rpDate;
		this.rpPlace = rpPlace;
		this.rpTime = rpTime;
		this.rpKind = rpKind;
		this.rpState = rpState;
		this.rpOn = rpOn;
	}
	
	

	public int getRpNo() {
		return rpNo;
	}

	public void setRpNo(int rpNo) {
		this.rpNo = rpNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getRpDate() {
		return rpDate;
	}

	public void setRpDate(Date rpDate) {
		this.rpDate = rpDate;
	}

	public String getRpPlace() {
		return rpPlace;
	}

	public void setRpPlace(String rpPlace) {
		this.rpPlace = rpPlace;
	}

	public String getRpTime() {
		return rpTime;
	}

	public void setRpTime(String rpTime) {
		this.rpTime = rpTime;
	}

	public String getRpKind() {
		return rpKind;
	}

	public void setRpKind(String rpKind) {
		this.rpKind = rpKind;
	}

	public String getRpState() {
		return rpState;
	}

	public void setRpState(String rpState) {
		this.rpState = rpState;
	}

	public String getRpOn() {
		return rpOn;
	}

	public void setRpOn(String rpOn) {
		this.rpOn = rpOn;
	}

	@Override
	public String toString() {
		return "ResPriv [rpNo=" + rpNo + ", userId=" + userId + ", rpDate=" + rpDate + ", rpPlace=" + rpPlace
				+ ", rpTime=" + rpTime + ", rpKind=" + rpKind + ", rpState=" + rpState + ", rpOn=" + rpOn + "]";
	}

	
	
}

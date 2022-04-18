package reservation.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class PersonalReservation {
	
	private int rpNo;
	private String rpDate; // 예약날짜
	private String rpPlace; // 예약장소
	private String rpTime; // 예약시간
	private String rpKind; // 헌혈종류
	private String rpState; // 예약승인상태
	private String rpOn; // 온라인/오프라인
	private String userId; // 사용자아이디
	
	public PersonalReservation() {
		
	}

	public int getRpNo() {
		return rpNo;
	}

	public void setRpNo(int rpNo) {
		this.rpNo = rpNo;
	}

	public String getRpDate() {
		return rpDate;
	}

	public void setRpDate(String rpDate) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PersonalReservation [rpNo=" + rpNo + ", rpDate=" + rpDate + ", rpPlace=" + rpPlace + ", rpTime="
				+ rpTime + ", rpKind=" + rpKind + ", rpState=" + rpState + ", rpOn=" + rpOn + ", userId=" + userId + "]";
	}
	
}

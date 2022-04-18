package memInfo.model.vo;

import java.sql.Date;

public class MemberInfo {

	private String userId;
	private String userName;
	private String userBD;
	private String userABO;
	private String userPhone;
	private String userEmail;
	private String userAddrRoad;
	private String userAddrDetail;
	
	public MemberInfo() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserBD() {
		return userBD;
	}

	public void setUserBD(String userBD) {
		this.userBD = userBD;
	}

	public String getUserABO() {
		return userABO;
	}

	public void setUserABO(String userABO) {
		this.userABO = userABO;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddrRoad() {
		return userAddrRoad;
	}

	public void setUserAddrRoad(String userAddrRoad) {
		this.userAddrRoad = userAddrRoad;
	}

	public String getUserAddrDetail() {
		return userAddrDetail;
	}

	public void setUserAddrDetail(String userAddrDetail) {
		this.userAddrDetail = userAddrDetail;
	}

	@Override
	public String toString() {
		return "MemberInfo [userName=" + userName + ", userId=" + userId + ", userBD=" + userBD + ", userABO=" + userABO
				+ ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", userAddrRoad=" + userAddrRoad
				+ ", userAddrDetail=" + userAddrDetail + "]";
	}


	
	
	
}

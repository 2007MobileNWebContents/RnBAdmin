package member.model.vo;


public class Member {

	private String userId;
	private String userPwd;
	private String userName;
	private String userBD;
	private String userGender;
	private String userPhone;
	private String userAddrPost;
	private String userAddrRoad;
	private String userAddrDetail;
	private String userEmail;
	private String userABO;

	public Member() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserBD() {
		return userBD;
	}

	public void setUserBD(String userBD) {
		this.userBD = userBD;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddrPost() {
		return userAddrPost;
	}

	public void setUserAddrPost(String userAddrPost) {
		this.userAddrPost = userAddrPost;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserABO() {
		return userABO;
	}

	public void setUserABO(String userABO) {
		this.userABO = userABO;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", userBD=" + userBD
				+ ", userGender=" + userGender + ", userPhone=" + userPhone + ", userAddrPost=" + userAddrPost
				+ ", userAddrRoad=" + userAddrRoad + ", userAddrDetail=" + userAddrDetail + ", userEmail=" + userEmail
				+ ", userABO=" + userABO + "]";
	}

	
	
	

}



package reservation.model.vo;

public class GroupReservation {

	private int rgNo;
	private String rgDate;
	private String rgPlace;
	private String rgTime;
	private String rgState;
	private String rgPeople;
	private String userId;
	
	public GroupReservation() {
		
	}

	public int getRgNo() {
		return rgNo;
	}

	public void setRgNo(int rgNo) {
		this.rgNo = rgNo;
	}

	public String getRgDate() {
		return rgDate;
	}

	public void setRgDate(String rgDate) {
		this.rgDate = rgDate;
	}

	public String getRgPlace() {
		return rgPlace;
	}

	public void setRgPlace(String rgPlace) {
		this.rgPlace = rgPlace;
	}

	public String getRgTime() {
		return rgTime;
	}

	public void setRgTime(String rgTime) {
		this.rgTime = rgTime;
	}

	public String getRgState() {
		return rgState;
	}

	public void setRgState(String rgState) {
		this.rgState = rgState;
	}

	public String getRgPeople() {
		return rgPeople;
	}

	public void setRgPeople(String rgPeople) {
		this.rgPeople = rgPeople;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "GroupReservation [rgNo=" + rgNo + ", rgDate=" + rgDate + ", rgPlace=" + rgPlace + ", rgTime=" + rgTime
				+ ", rgState=" + rgState + ", rgPeople=" + rgPeople + ", userId=" + userId + "]";
	}
	
	
}

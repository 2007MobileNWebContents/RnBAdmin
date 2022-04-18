package reservation.model.vo;

import java.util.ArrayList;

public class PageData {

	private ArrayList<GroupReservation> groupAckPageList;
	private ArrayList<GroupReservation> groupConfirmPageList;
	private ArrayList<PersonalReservation> personalPageList;
	private ArrayList<PersonalReservation> personalAckPageList;
	private String groupAckPageNavi;
	private String groupConfirmPageNavi;
	private String personalPageNavi;
	private String personalAckPageNavi;
	

	public PageData() {
		
	}

	public String getPersonalAckPageNavi() {
		return personalAckPageNavi;
	}


	public void setPersonalAckPageNavi(String personalAckPageNavi) {
		this.personalAckPageNavi = personalAckPageNavi;
	}

	public ArrayList<PersonalReservation> getPersonalAckPageList() {
		return personalAckPageList;
	}



	public void setPersonalAckPageList(ArrayList<PersonalReservation> personalAckPageList) {
		this.personalAckPageList = personalAckPageList;
	}

	public ArrayList<GroupReservation> getGroupAckPageList() {
		return groupAckPageList;
	}



	public void setGroupAckPageList(ArrayList<GroupReservation> groupAckPageList) {
		this.groupAckPageList = groupAckPageList;
	}



	public ArrayList<GroupReservation> getGroupConfirmPageList() {
		return groupConfirmPageList;
	}



	public void setGroupConfirmPageList(ArrayList<GroupReservation> groupConfirmPageList) {
		this.groupConfirmPageList = groupConfirmPageList;
	}



	public ArrayList<PersonalReservation> getPersonalPageList() {
		return personalPageList;
	}

	public void setPersonalPageList(ArrayList<PersonalReservation> personalPageList) {
		this.personalPageList = personalPageList;
	}

	

	public String getGroupAckPageNavi() {
		return groupAckPageNavi;
	}

	public void setGroupAckPageNavi(String groupAckPageNavi) {
		this.groupAckPageNavi = groupAckPageNavi;
	}

	public String getGroupConfirmPageNavi() {
		return groupConfirmPageNavi;
	}

	public void setGroupConfirmPageNavi(String groupConfirmPageNavi) {
		this.groupConfirmPageNavi = groupConfirmPageNavi;
	}

	public String getPersonalPageNavi() {
		return personalPageNavi;
	}

	public void setPersonalPageNavi(String personalPageNavi) {
		this.personalPageNavi = personalPageNavi;
	}

	@Override
	public String toString() {
		return "PageData [groupAckPageList=" + groupAckPageList + ", groupConfirmPageList=" + groupConfirmPageList
				+ ", personalPageList=" + personalPageList + ", personalAckPageList=" + personalAckPageList
				+ ", groupAckPageNavi=" + groupAckPageNavi + ", groupConfirmPageNavi=" + groupConfirmPageNavi
				+ ", personalPageNavi=" + personalPageNavi + ", personalAckPageNavi=" + personalAckPageNavi + "]";
	}

	
	
}

package result.model.vo;

import java.sql.Date;

public class Hospital {
	
	//DB에서 불러온 데이터 저장
	private int hpNo;
	private String hpName;
	private String hpNumber;
	private String hpPhone;
	private String hpAddr;
	private Date hpDate;
	private String hpDis;
	private String hpCheck;
	private String hpMedi;

	//getter/setter
	public int getHpNo() {
		return hpNo;
	}
	public void setHpNo(int hpNo) {
		this.hpNo = hpNo;
	}
	public String getHpName() {
		return hpName;
	}
	public void setHpName(String hpName) {
		this.hpName = hpName;
	}
	public String getHpNumber() {
		return hpNumber;
	}
	public void setHpNumber(String hpNumber) {
		this.hpNumber = hpNumber;
	}
	public String getHpPhone() {
		return hpPhone;
	}
	public void setHpPhone(String hpPhone) {
		this.hpPhone = hpPhone;
	}
	public String getHpAddr() {
		return hpAddr;
	}
	public void setHpAddr(String hpAddr) {
		this.hpAddr = hpAddr;
	}
	public Date getHpDate() {
		return hpDate;
	}
	public void setHpDate(Date hpDate) {
		this.hpDate = hpDate;
	}
	public String getHpDis() {
		return hpDis;
	}
	public void setHpDis(String hpDis) {
		this.hpDis = hpDis;
	}
	public String getHpCheck() {
		return hpCheck;
	}
	public void setHpCheck(String hpCheck) {
		this.hpCheck = hpCheck;
	}
	public String getHpMedi() {
		return hpMedi;
	}
	public void setHpMedi(String hpMedi) {
		this.hpMedi = hpMedi;
	}
	
	
	
	
	
	
	

}

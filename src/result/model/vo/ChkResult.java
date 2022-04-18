package result.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class ChkResult {
	
	private int crCode;
	private String crNumber;
	private String crBanDis;
	private String crBanCheck;
	private String crBanDrg;
	private String crBody;
	private String crIn;
	private String crOut;
	private String crDrg;
	private String crKor;
	private String crOver;
	private String crFamily;
	private String crRes;
	private Date crDate;
	private String userId;
	private ArrayList<Hospital> hospital;
	
	public int getCrCode() {
		return crCode;
	}
	public void setCrCode(int crCode) {
		this.crCode = crCode;
	}
	public String getCrNumber() {
		return crNumber;
	}
	public void setCrNumber(String crNumber) {
		this.crNumber = crNumber;
	}
	public String getCrBanDis() {
		return crBanDis;
	}
	public void setCrBanDis(String crBanDis) {
		this.crBanDis = crBanDis;
	}
	public String getCrBanCheck() {
		return crBanCheck;
	}
	public void setCrBanCheck(String crBanCheck) {
		this.crBanCheck = crBanCheck;
	}
	public String getCrBanDrg() {
		return crBanDrg;
	}
	public void setCrBanDrg(String crBanDrg) {
		this.crBanDrg = crBanDrg;
	}
	public String getCrBody() {
		return crBody;
	}
	public void setCrBody(String crBody) {
		this.crBody = crBody;
	}
	public String getCrIn() {
		return crIn;
	}
	public void setCrIn(String crIn) {
		this.crIn = crIn;
	}
	public String getCrOut() {
		return crOut;
	}
	public void setCrOut(String crOut) {
		this.crOut = crOut;
	}
	public String getCrDrg() {
		return crDrg;
	}
	public void setCrDrg(String crDrg) {
		this.crDrg = crDrg;
	}
	public String getCrKor() {
		return crKor;
	}
	public void setCrKor(String crKor) {
		this.crKor = crKor;
	}
	public String getCrOver() {
		return crOver;
	}
	public void setCrOver(String crOver) {
		this.crOver = crOver;
	}
	public String getCrFamily() {
		return crFamily;
	}
	public void setCrFamily(String crFamily) {
		this.crFamily = crFamily;
	}
	public String getCrRes() {
		return crRes;
	}
	public void setCrRes(String crRes) {
		this.crRes = crRes;
	}
	public Date getCrDate() {
		return crDate;
	}
	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ArrayList<Hospital> getHospital() {
		return hospital;
	}
	public void setHospital(ArrayList<Hospital> hospital) {
		this.hospital = hospital;
	}
	


}

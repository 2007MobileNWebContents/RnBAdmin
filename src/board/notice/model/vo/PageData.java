package board.notice.model.vo;

import java.util.ArrayList;

import board.memBoard.model.vo.MemBoardComment;
import board.memBoard.model.vo.MemberBoard;
import community.donBoard.model.vo.DonBoardComment;
import community.donBoard.model.vo.DonateBoard;

public class PageData {

	public PageData () {}

	private String pageNavi;
	private ArrayList<Notice> pageList;
	private ArrayList<NoticeComment> cPageList;
	private ArrayList<MemberBoard> mPageList;
	private ArrayList<DonateBoard> dPageList;
	private ArrayList<MemBoardComment> mcPageList;
	private ArrayList<DonBoardComment> dcPageList;


	public ArrayList<DonBoardComment> getDcPageList() { return dcPageList; }
	public void setDcPageList(ArrayList<DonBoardComment> dcPageList) {
		this.dcPageList = dcPageList; }

	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public ArrayList<Notice> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<Notice> pageList) {
		this.pageList = pageList;
	}
	public ArrayList<NoticeComment> getcPageList() {
		return cPageList;
	}
	public void setcPageList(ArrayList<NoticeComment> cPageList) {
		this.cPageList = cPageList;
	}
	public ArrayList<MemberBoard> getmPageList() {
		return mPageList;
	}
	public void setmPageList(ArrayList<MemberBoard> mPageList) {
		this.mPageList = mPageList;
	}


	public ArrayList<DonateBoard> getdPageList() { return dPageList; } public
	void setdPageList(ArrayList<DonateBoard> dPageList) { this.dPageList =
	dPageList; }

	public ArrayList<MemBoardComment> getMcPageList() {
		return mcPageList;
	}
	public void setMcPageList(ArrayList<MemBoardComment> mcPageList) {
		this.mcPageList = mcPageList;
	}



}

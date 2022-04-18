package community.cheer.vo;

import java.util.ArrayList;

import home.model.vo.CheerBoard;

public class PageData {
	
	private ArrayList<CheerBoard> pageList;
	private String pageNavi;

	public PageData() {}

	public ArrayList<CheerBoard> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<CheerBoard> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}

package memInfo.model.vo;

import java.util.ArrayList;

public class PageData {

	private ArrayList<MemberInfo> pageList;
	private String pageNavi;
	
	public PageData() {}

	public ArrayList<MemberInfo> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<MemberInfo> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}

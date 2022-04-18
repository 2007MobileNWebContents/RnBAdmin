package faq.model.vo;

import java.util.ArrayList;

public class PageData {

	 private ArrayList<FAQ> pageList;
	 private String pageNavi;
	 
	 public ArrayList<FAQ> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<FAQ> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	public PageData() {}
}

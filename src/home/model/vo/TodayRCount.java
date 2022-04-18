package home.model.vo;

public class TodayRCount {
	
	private String userABO;
	private int rCount;
	
	public TodayRCount() {}

	public String getUserABO() {
		return userABO;
	}

	public void setUserABO(String userABO) {
		this.userABO = userABO;
	}

	public int getrCount() {
		return rCount;
	}

	public void setrCount(int rCount) {
		this.rCount = rCount;
	}

	@Override
	public String toString() {
		return "TodayRCount [userABO=" + userABO + ", rCount=" + rCount + "]";
	}
	
	
}

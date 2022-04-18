package resResult.model.vo;

public class ResResult {
	
	private int rpNo;
	private String resId;
	private String resB;
	private String resC;
	private int resAlt;
	private int resPro;
	private int resAst;
	private int resCol;
	
	public ResResult() {
		
	}
	
	public ResResult(int rpNo, String resId, String resB, String resC, int resAlt, int resPro, int resAst, int resCol) {
		this.rpNo = rpNo;
		this.resId = resId;
		this.resB = resB;
		this.resC = resC;
		this.resAlt = resAlt;
		this.resPro = resPro;
		this.resAst = resAst;
		this.resCol = resCol;
	}
	
	public ResResult(String resB, String resC, int resAlt, int resPro, int resAst, int resCol) {
		this.resB = resB;
		this.resC = resC;
		this.resAlt = resAlt;
		this.resPro = resPro;
		this.resAst = resAst;
		this.resCol = resCol;
	}

	public int getRpNo() {
		return rpNo;
	}

	public void setRpNo(int rpNo) {
		this.rpNo = rpNo;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResB() {
		return resB;
	}

	public void setResB(String resB) {
		this.resB = resB;
	}

	public String getResC() {
		return resC;
	}

	public void setResC(String resC) {
		this.resC = resC;
	}

	public int getResAlt() {
		return resAlt;
	}

	public void setResAlt(int resAlt) {
		this.resAlt = resAlt;
	}

	public int getResPro() {
		return resPro;
	}

	public void setResPro(int resPro) {
		this.resPro = resPro;
	}

	public int getResAst() {
		return resAst;
	}

	public void setResAst(int resAst) {
		this.resAst = resAst;
	}



	public int getResCol() {
		return resCol;
	}

	public void setResCol(int resCol) {
		this.resCol = resCol;
	}

	@Override
	public String toString() {
		return "ResResult [rpNo=" + rpNo + ", resId=" + resId + ", resB=" + resB + ", resC=" + resC + ", resAlt="
				+ resAlt + ", resPro=" + resPro + ", resAst=" + resAst + ", resCol=" + resCol + "]";
	}

	
	
}

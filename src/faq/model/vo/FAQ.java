package faq.model.vo;

public class FAQ {

	private int faqNo;
	private String faqQ;
	private String faqA;
	
	public FAQ(){}
	
	public int getFaqNo() {
		return faqNo;
	}
	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}
	public String getFaqQ() {
		return faqQ;
	}
	public void setFaqQ(String faqQ) {
		this.faqQ = faqQ;
	}
	public String getFaqA() {
		return faqA;
	}
	public void setFaqA(String faqA) {
		this.faqA = faqA;
	}
}

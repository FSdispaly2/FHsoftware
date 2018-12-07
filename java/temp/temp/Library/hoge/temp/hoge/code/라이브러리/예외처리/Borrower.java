/**
 * 
 * 도서관 관리 시스템에 등록되어 있는 가상의 이용자
 * (학번 이름)2017315022 주인선, 2017315024 임윤지, 
 *          2017315049 다카타 이쿠오, 2017315055 에다 카츠토시
 * 2018/12/07
 * 
 */

public class Borrower {

	private String name;
	private int count;

	public Borrower(String name) {
		this.name = name;
		this.count = 0;
	}

	public String getName() {
		return this.name;

	}
    public void upCount() {
		this.count++;
	}
    public void disCount() {
		this.count--;
	}
	public int getCount() {
		return this.count;
	}
}

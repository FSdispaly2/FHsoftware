
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


import java.util.Vector;

public class Book {

	private String name;
	private String author;
	private String cNumber;
	private Borrower connection = null;
	private Vector<String> bookData = new Vector<String>();
	
	public Book(String name, String author, String cNumber) {
		this.name = name;
		this.author = author;
		this.bookData.add(name);
		this.bookData.add(author);
		this.cNumber = cNumber;
	}
	
	public void connect(Borrower borrower) {
		this.connection = borrower;
	}
	public void disconnect() {
		this.connection = null;
	}
	public Vector<String> search(){
		return this.bookData;
	}
	public String getCNumber() {
		return this.cNumber;
	}
	public boolean loanStatus() {
		if(this.connection == null) {
			return true;
		}else{
			return false;
		}
	}
}

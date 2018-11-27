import java.util.Vector;

public class Borrower {

	private String name;
	
	public Borrower(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public void borrowBook(Book book) {
		book.connect(this);
	}
	public boolean checkLoanStatus(Library library, Book book) {
		return checkLoanStatus(library, book);
	}
	public void returnBook(Library library, Book book) {
		returnBook(library, book);
	}
}

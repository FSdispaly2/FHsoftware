import java.util.Vector;

public class Borrower {

	private String name;
	
	public Borrower(String name){
		this.name = name;
	}
	
	public Book search(Library library, Librarian librarian, String bookName) {
		return librarian.search(library, bookName);
	}
	public void borrowBook(Library library, Librarian librarian, Book book) {
		librarian.borrowBook(this, library, book);
	}
	public boolean checkLoanStatus(Library library, Librarian librarian, Book book) {
		return librarian.checkLoanStatus(library, book);
	}
	public void returnBook(Library library, Librarian librarian, Book book) {
		librarian.returnBook(library, book);
	}
}

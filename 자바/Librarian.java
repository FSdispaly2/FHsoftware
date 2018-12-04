import java.util.Vector;

public class Librarian {

	private String name;
	
	public Librarian(String name) {
		this.name = name;
	}

	public void signUpBorrower(Library library, String borrowerName) {
		library.signUpBorrower(borrowerName);
	}
	public void signUpBook(Library library, Book book) {
		library.signUpBook(book);
	}
	public Book search(Library library, String bookName) {
		return library.search(bookName);
	}
	public void borrowBook(Borrower borrower, Library library, Book book) {
		library.borrowBook(borrower, book);
	}
	public boolean checkLoanStatus(Library library, Book book) {
		return library.checkLoanStatus(book);
	}
	public void returnBook(Library library, Book book) {
		library.returnBook(book);
	}

}


import java.util.Vector;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Library {

	private String name;
	private ArrayList<Book> books = new ArrayList<Book>();
	private Vector<String> borrowers = new Vector<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library library = new Library("SunMoonLibrary");
		Librarian librarian = new Librarian("Katsu");
		System.out.println("어서오세요.");
		Scanner input = new Scanner(System.in);
		System.out.println("등록하는 책의 제목과 저자를 입력하세요.");
		Book b = new Book(input.nextLine(), input.nextLine());
		librarian.signUpBook(library, b);
		System.out.println("お客様のお名前を入力してください。");
		String borrowerName = input.nextLine();
		librarian.signUpBorrower(library, borrowerName);
		Borrower borrower = new Borrower(borrowerName);
		System.out.println("책 제목을 입력하세요.");
		String bookName = input.nextLine();
		Book book = borrower.search(library, librarian, bookName);
		if(book != null && borrower.checkLoanStatus(library, librarian, book)) {
			//Book클레스 말고 cNumber로?
			borrower.borrowBook(library, librarian, book);
			borrower.returnBook(library, librarian, book);
		}
		input.close();
	}
	
	public Library(String name) {
		this.name = name;
	}
	public void signUpBook(Book book) {
		this.books.add(book);
	}
	public void signUpBorrower(String borrowerName) {
		boolean check = false;
		for(int i = 0; i < borrowers.size(); i++) {
			if(books.get(i).equals(borrowerName)){
				check = true;
			}
		}
		if(check) {
			System.out.println("이미 등록된 이름입니다.");
			return;
		}
		this.borrowers.add(borrowerName);
	}
	public Book search(String bookName) {
		for(int i = 0; i < this.books.size(); i++) {
			if(this.books.get(i).search().get(0).equals(bookName)){
				System.out.println("책 이름 : " + this.books.get(i).search().get(0));
				System.out.println("책 저자 : " + this.books.get(i).search().get(1));
				return this.books.get(i);
			}
		}
		System.out.println("입력하신 제목 책이 없습니다.");
		return null;
	}
	public void loanStatus(Book book) {
		if(book.loanStatus()) {
			System.out.println("지금 책은 빌릴 수 있습니다.");
		}else{
			System.out.println("지금 책은 빌릴 수 없습니다.");
		}
	}
	public boolean checkLoanStatus(Book book) {
		return book.loanStatus();
	}
	public void borrowBook(Borrower borrower, Book book) {
		book.connect(borrower);
		System.out.println("처리가 완료 되었습니다.");
	}
	public void returnBook(Book book) {
		book.disconnect();
		System.out.println("처리가 완료 되었습니다.");
	}

}

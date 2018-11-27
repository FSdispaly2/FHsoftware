
import java.util.*;

public class Library {

	private String name;
//	private ArrayList<Book> books = new ArrayList<Book>();
	private ArrayList<ArrayList<Book>> books = new ArrayList<ArrayList<Book>>();
	private ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
	
	public Library(String name) {
		this.name = name;
		for(int i = 0; i < 9; i++) {
			this.books.add(new ArrayList<Book>());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library library = new Library("SunMoonLibrary");
		System.out.println("어서오세요.");
		library.signUpBook();
		library.signUpBorrower();
		library.searchBook();
//		Book book = borrower.search(library, librarian, bookName);
//		if(book != null && borrower.checkLoanStatus(library, librarian, book)) {
//			//Book클레스 말고 cNumber로?
//			borrower.borrowBook(library, librarian, book);
//			borrower.returnBook(library, librarian, book);
//		}
	}

	public void signUpBook() {
		Scanner input = new Scanner(System.in);
		System.out.println("登録する本のジャンルと名前、著者を入力してください。");
		System.out.println("0.総記 1.哲学 2.歴史 3.社会科学 4.自然科学 5.技術 6.産業 7.芸術 8.言語 9.文学");
		System.out.println("종류 입력(수자)");
		String cNumber = input.nextLine();
		System.out.println("책 제목 입력");
		String bookName = input.nextLine();
		System.out.println("책 저자 입력");
		String author = input.nextLine();
		cNumber = this.makeCNumber(bookName, author, cNumber);
		System.out.println(cNumber);
		Book book = new Book(bookName, author, cNumber);
		this.books.get(Integer.parseInt(book.getCNumber().substring(0, 1))).add(book);
		input.close();
	}
	public void signUpBorrower() {
		Scanner input = new Scanner(System.in);
		System.out.println("お客様のお名前を入力してください。");
		String borrowerName = input.nextLine();
		boolean check = false;
		for(Borrower x: this.borrowers) {
			if(x.getName().equals(borrowerName)) {
				check = true;
				break;
			}
		}
		if(check) {
			System.out.println("이미 등록된 이름입니다.");
		} else {
			Borrower b = new Borrower(borrowerName);
			this.borrowers.add(b);
		}
		input.close();
	}
	public String checkBooks(String bookName, String author, String cNumber) {
		String No = "00";
		ArrayList<Book> temp = this.books.get(Integer.parseInt(cNumber.substring(0, 1)));
		int count = 0;
		for(Book x: temp) {
			if(cNumber.equals(x.getCNumber().substring(0, cNumber.length()))) {
				count++;
			}
		}
		if(count == 0) {
			return No;
		} else if(count / 10 == 0) {
			No = "0" + count;
		} else {
			No = "" + count;
		}
		return No;
	}
	public String makeCNumber(String bookName, String author, String cNumber) {
		String temp = bookName + author;
		char[] ctemp = temp.toCharArray();
		for(char x: ctemp) {
			cNumber += (int)(x);
		}
		cNumber += this.checkBooks(bookName, author, cNumber);
		return cNumber;
	}
	public void searchBook() {
		Scanner input = new Scanner(System.in);
		System.out.println("検索する本のジャンルと名前、著者を入力してください。");
		System.out.println("0.総記 1.哲学 2.歴史 3.社会科学 4.自然科学 5.技術 6.産業 7.芸術 8.言語 9.文学");
		System.out.println("종류 입력(수자)");
		String cNumber = input.nextLine();
		System.out.println("책 제목 입력");
		String bookName = input.nextLine();
		System.out.println("책 저자 입력");
		String author = input.nextLine();
		if(cNumber.equals("")) {
			for(ArrayList<Book> x: this.books) {
				for(Book y: x) {
					
				}
			}
		}else {
			for(Book b: this.books.get(Integer.parseInt(cNumber))) {
				
			}
		}
		input.close();
	}
}

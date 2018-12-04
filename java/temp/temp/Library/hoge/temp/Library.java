
import java.util.*;

public class Library {

	private String name;
	private TreeSet<Book> books = new TreeSet<Book>(new MyComparator ());
	private HashSet<Borrower> borrowers = new HashSet<Borrower>();
	private HashSet<String> librarians = new HashSet<String>();
	private Iterator<Book> iBook;
	private Iterator<Borrower> iBorrower;
	private Iterator<String> iLibrarian;
	
	public Library(String name) {
		this.name = name; 
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Library library = new Library("SunMoonLibrary");
		library.librarians.add("unknow");
		while(true) {
			System.out.println("환영합니다,  " + library.getName() + "입니다.");
			int ID = library.login(input);
			int choices;
			int cNumber;
			boolean exit;
			boolean systemCall = false;
			Book book = null;
			switch(ID) {
			case 0:
				while(true) {
					exit = false;
					choices = input.nextInt();
					input.nextLine();
					switch(choices) {
					case 0:
						library.signUpBook(input);
						break;
					case 1:
						library.signUpBorrower(input);
						break;
					case 2:
						exit = true;
						break;
					}
					if(exit) {
						break;
					}
				}
				break;
			case 1:
				while(true) {
					exit = false;
					choices = input.nextInt();
					input.nextLine();
					switch(choices) {
					case 0:
						library.borrowBook(input, book);
						break;
					case 1:
						library.searchBook(input);
						break;
					case 2:
						cNumber = input.nextInt();
						input.nextLine();
						book = library.choiceBook(cNumber);
						break;
					case 3:
						exit = true;
						break;
					}
					if(exit) {
						break;
					}
				}
				break;
			case 2:
				systemCall = true;
				break;
			case 3:
				break;
			}
			if(systemCall) {
				break;
			}
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public void signUpBook(Scanner input) {
		System.out.println("등록하시는 책의 분류번호,제목과 저자를 입력해주세요.");
		System.out.println("0.기타 1.문학 2.역사 3.사회과학 4.자연과학 5.기술 6.산업 7.예술 8.언어 9.문학");
		System.out.println("책의 종류를 입력해주세요.(숫자)");
		int cNumber = input.nextInt();
		input.nextLine();
		System.out.println("책의 제목을 입력해주세요.");
		String bookName = input.nextLine();
		System.out.println("책의 저자를 입력해주세요.");
		String author = input.nextLine();
		cNumber = this.makeCNumber(bookName, author, cNumber);
		System.out.println(cNumber);
		Book book = new Book(bookName, author, cNumber);
		this.books.add(book);
	}
	public void signUpBorrower(Scanner input) {
		System.out.println("등록하시려는 이용자의 성함을 입력해주세요.");
		String borrowerName = input.nextLine();
		this.iBorrower = this.borrowers.iterator();
		boolean check = false;
		while(this.iBorrower.hasNext()) {
			Borrower x = (Borrower)this.iBorrower.next();
			if(x.getName().equals(borrowerName)) {
				check = true;
				break;
			}
		}
		if(check) {
			System.out.println("이미 등록되어 있는 이름입니다.");
		} else {
			System.out.println("성공적으로 등록되었습니다.");
			Borrower b = new Borrower(borrowerName);
			this.borrowers.add(b);
		}
	}
	public int checkBooks(int cNumber) {
		this.iBook = this.books.iterator();
		int count = 0;
		while(this.iBook.hasNext()) {
			Book b = (Book)this.iBook.next();
			if(b.getCNumber() == cNumber) {
				count++;
			}
		}
		return count;
	}
	public int makeCNumber(String bookName, String author, int cNumber) {
		String temp = bookName + author;
		cNumber = cNumber * 10000000;
		cNumber += temp.replaceAll(" ", "").length() * 100;
		cNumber += (int)bookName.charAt(0) * 10000;
		cNumber += this.checkBooks(cNumber);
		return cNumber;
	}
	public void searchBook(Scanner input) {
		Book book;
		System.out.println("찾으시려는 책의 카테고리 번호, 제목, 저자 중에 하나를 입력해주세요.");
		String temp = input.nextLine();
		this.iBook = books.iterator();
		if(temp.length() == 1 && (int)temp.charAt(0) > 58){
			while(this.iBook.hasNext()) {
				book = this.iBook.next();
				if(book.getCNumber()/10000000 == (int)temp.charAt(0) - 48) {
					System.out.println(book.search() + "\n" + book.getCNumber());
				}else if(book.search().split("\n")[0].equals(temp)) {
					System.out.println(book.search() + "\n" + book.getCNumber());
				}else if(book.search().split("\n")[1].equals(temp)) {
					System.out.println(book.search() + "\n" + book.getCNumber());
				}
			}
			System.out.println("결과가 없습니다.");
		}
	}
	public Book choiceBook(int cNumber) {
		Book book;
		this.iBook = books.iterator();
		while(this.iBook.hasNext()) {
			book = this.iBook.next();
			if(book.getCNumber() == cNumber) {
				return book;
			}
		}
		book = null;
		return book;
	}
	public int login(Scanner input) {
		System.out.println("ID를 입력해주세요.");
		String ID = input.nextLine();
		this.iLibrarian = librarians.iterator();
		while(this.iLibrarian.hasNext()) {
			String x = (String)this.iLibrarian.next();
			if(x.equals(ID)) {
				System.out.println(ID + "(관리자)님, 환영합니다.");
				return 0;
			}
		}
		if(ID.equals("guest")) {
			System.out.println(ID + "(이용자)님, 환영합니다.");
			return 1;
		}else if(ID.equals("exit")){
			System.out.println("시스템을 종료합니다.");
			return 2;
		}else {
			System.out.println("올바른 ID를 입력해주세요.");
			return 3;
		}
	}
	public void borrowBook(Scanner input, Book book) {
		System.out.println("대출하시고자 하는 이용자의 이름을 입력해주세요.");
		String borrowerName = input.nextLine();
		boolean check = true;
		this.iBorrower = this.borrowers.iterator();
		while(this.iBorrower.hasNext()) {
			Borrower x = (Borrower)this.iBorrower.next();
			if(x.getName().equals(borrowerName) && book.loanStatus()) {
				book.connect(x);
				System.out.println("대출이 성공석으로 완료되었습니다.");
				check = false;
				break;
			}
		}
		if(check) {
			System.out.println("대출에 실패하였습니다. 다시 시도해주세요.");
		}
	}
	public void returnBook(Book book) {
		book.disconnect();
		System.out.println("반납이 완료되었습니다.");
	}
}

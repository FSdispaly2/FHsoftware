
import java.util.*;

public class Library {

	private String name;
//	private ArrayList<Book> books = new ArrayList<Book>();
	private ArrayList<ArrayList<Book>> books = new ArrayList<ArrayList<Book>>();
	private ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
	private Vector<String> librarians = new Vector<String>();
	
	public Library(String name) {
		this.name = name;
		for(int i = 0; i < 9; i++) {
			this.books.add(new ArrayList<Book>());
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Library library = new Library("SunMoonLibrary");
		library.login(input);
		System.out.println("어서오세요 " + library.getName() + "입니다.");
		library.signUpBook(input);
		library.signUpBorrower(input);
//		Book book = library.searchBook(input);
		Book book = library.books.get(8).get(0);
		library.borrowBook(input, book);
		library.returnBook(book);
	}
	
	public String getName(){
		return this.name;
	}
	
	public void signUpBook(Scanner input) {
		System.out.println("등록하시는 책의 분류번호,제목과 저자를 입력해주세요.");
		System.out.println("0.기타 1.문학 2.역사 3.사회과학 4.자연과학 5.기술 6.산업 7.예술 8.언어 9.문학");
		System.out.println("책의 종류를 입력해주세요.(숫자)");
		String cNumber = input.nextLine();
		System.out.println("책의 제목을 입력해주세요.");
		String bookName = input.nextLine();
		System.out.println("책의 저자 입력.");
		String author = input.nextLine();
		cNumber = this.makeCNumber(bookName, author, cNumber);
		System.out.println(cNumber);
		Book book = new Book(bookName, author, cNumber);
		this.books.get(Integer.parseInt(book.getCNumber().substring(0, 1))).add(book);
	}
	public void signUpBorrower(Scanner input) {
		System.out.println("대출자의 이름을 입력해주세요.");
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
		char[] ctemp = temp.replaceAll(" ", "").toCharArray();
		for(char x: ctemp) {
			cNumber += (int)(x);
		}
		cNumber += this.checkBooks(bookName, author, cNumber);
		return cNumber;
	}
//		Book book = null;
//		System.out.println("濾쒐뇨�걲�굥�쑍�겗�궦�깵�꺍�꺂�겏�릫�뎺�곮몭�끹굮�뀯�뒟�걮�겍�걦�걽�걬�걚��");
//		System.out.println("0.渶뤺쮼 1.�벒耶� 2.閭닷뤁 3.鹽얌폏燁묈�� 4.�눎�꽫燁묈�� 5.��烏� 6.�뵣璵� 7.�듃烏� 8.鼇�沃� 9.�뻼耶�");
//		System.out.println("醫낅쪟 �엯�젰(�닔�옄)");
//		String cNumber = input.nextLine();
//		System.out.println("梨� �젣紐� �엯�젰");
//		String bookName = input.nextLine();
//		System.out.println("梨� ���옄 �엯�젰");
//		String author = input.nextLine();
//		if(cNumber.equals("")) {
//			for(ArrayList<Book> x: this.books) {
//				for(Book y: x) {
//					
//				}
//			}
//		}else {
//			for(Book b: this.books.get(Integer.parseInt(cNumber))) {
//				
//			}
//		}
//		return book;
//	}
	public boolean login(Scanner input) {
		System.out.println("ID를 입력해주세요.");
		String ID = input.nextLine();
		for(String x: this.librarians) {
			if(x.equals(ID)) {
				return true;
			}
		}
		return false;
	}
	public void borrowBook(Scanner input, Book book) {
		System.out.println("대출자의 이름을  입력해주세요 히하");
		String signIn = input.nextLine();
		boolean check = true;
		for(Borrower x: this.borrowers) {
			if(signIn.equals(x.getName()) && book.loanStatus()) {
				book.connect(x);
				System.out.println("처리가 완료되었습니다.");
				check = false;
				break;
			}
		}
		if(check) {
			System.out.println("error");
		}
	}
	public void returnBook(Book book) {
		book.disconnect();
		System.out.println("처리가 완룍되었습니다.");
	}
}

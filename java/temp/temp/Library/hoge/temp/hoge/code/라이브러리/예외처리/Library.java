/**
 * 
 * 도서관 관리 시스템
 * (학번 이름)2017315022 주인선, 2017315024 임윤지, 
 *          2017315049 다카타 이쿠오, 2017315055 에다 카츠토시
 * 2018/12/07
 * 
 */

import java.util.*;

public class Library {

	private String name;
	private TreeSet<Book> books = new TreeSet<Book>(new Book());
	private HashSet<Borrower> borrowers = new HashSet<Borrower>();
	private HashSet<String> librarians = new HashSet<String>();
	private Iterator<Book> iBook;
	private Iterator<Borrower> iBorrower;
	private Iterator<String> iLibrarian;

	public Library(String name) {
		this.name = name;
		String bookName;
		String author;
		Book book;
		int cNumber;
		for (int i = 0; i < 3; i++) {
			bookName = "English";
			author = "Rowling";
			cNumber = this.makeCNumber(bookName, author, 8);
			book = new Book(bookName, author, cNumber);
			this.books.add(book);
			bookName = "日本語";
			author = "翔";
			cNumber = this.makeCNumber(bookName, author, 8);
			book = new Book(bookName, author, cNumber);
			this.books.add(book);
			bookName = "한국어";
			author = "박완서";
			cNumber = this.makeCNumber(bookName, author, 8);
			book = new Book(bookName, author, cNumber);
			this.books.add(book);
		}
		Borrower borrower = new Borrower("김철수");
		this.borrowers.add(borrower);
		this.librarians.add("김영희");
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Library library = new Library("SunMoonLibrary");
		while (true) {
			System.out.println("환영합니다,  " + library.getName() + "입니다.");
			int ID = library.login(input);
			int choices;
			boolean exit;
			boolean systemCall = false;
			switch (ID) {
			case 0:
				while (true) {
					choices = 10;
					exit = false;
					System.out.println("원하시는 서비스를 선택해주세요.");
					System.out.println(
							"0: 책 등록   1: 이용자 등록   2: 책 대출   3: 책 반납   4: 대출 중인 책 전시   5: 대출 가능한 책 전시  6: 로그아웃");
					try {
						choices = input.nextInt();
						input.nextLine();

					} catch (InputMismatchException e) {
						System.out.println("숫자로 써주세요.");
						input.nextLine();
					}

					switch (choices) {
					case 0: // 책 등록
						library.signUpBook(input);
						break;
					case 1: // 이용자 등록
						library.signUpBorrower(input);
						break;
					case 2: // 대출
						library.borrowBook(input);
						break;
					case 3: // 반납
						library.returnBook(input);
						break;
					case 4: // 대출 중 전시
						library.displayBookOnLone();
						break;
					case 5: // 대출 가능 전시
						library.displayBookForLone();
						break;
					case 6: // 로그아웃
						exit = true;
						System.out.println("서비스를 이용해 주셔서 감사합니다.");
						break;
					default:
						System.out.println("올바른 숫자를 입력해주세요.");
						break;
					}

					if (exit) {
						break;
					}
				}
				break;
			case 1:
				while (true) {
					library.searchBook(input);
					System.out.println("계속 검색하시겠습니까? (Yes/No : 대소문자를 구분합니다.)");
					String continueSearch = input.nextLine();
					if (continueSearch.equals("No")) {
						System.out.println("서비스를 이용해주셔서 감사합니다.");
						break;
					} else if (continueSearch.equals("Yes")) {
						continue;

					} else {
						System.out.println("Yes/No로 입력해주세요.");
						System.out.println("로그인 화면으로 돌아갑니다.");
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
			if (systemCall) {
				break;
			}
		}

	}

	public String getName() {
		return this.name;
	}

	public void signUpBook(Scanner input) {
		System.out.println("등록하시는 책의 분류번호,제목과 저자를 입력해주세요.");
		System.out.println("0.기타 1.철학 2.역사 3.사회과학 4.자연과학 5.기술 6.산업 7.예술 8.언어 9.문학");
		System.out.println("책의 종류를 입력해주세요.(숫자)");
		int cNumber = 10;
		try {
			cNumber = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("숫자로 써주세요.");
			input.nextLine();

		}
		if (cNumber > 9) {
			System.out.println("올바른 숫자를 입력해주세요.");
			this.signUpBook(input);
			return;
		}
		System.out.println("책의 제목을 입력해주세요.");
		String bookName = input.nextLine();
		System.out.println("책의 저자를 입력해주세요.");
		String author = input.nextLine();
		cNumber = this.makeCNumber(bookName, author, cNumber);
		Book book = new Book(bookName, author, cNumber);
		this.books.add(book);
		System.out.println("성공적으로 책이 등록 되었습니다.");
	}

	public void signUpBorrower(Scanner input) {
		System.out.println("등록하시려는 이용자의 성함을 입력해주세요.");
		String borrowerName = input.nextLine();
		this.iBorrower = this.borrowers.iterator();
		boolean check = false;
		while (this.iBorrower.hasNext()) {
			Borrower x = this.iBorrower.next();
			if (x.getName().equals(borrowerName)) {
				check = true;
				break;
			}
		}
		if (check) {
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
		while (this.iBook.hasNext()) {
			Book b = this.iBook.next();
			if (b.getCNumber() / 100 == cNumber / 100) {
				count++;
			}
		}
		return count;
	}

	public int makeCNumber(String bookName, String author, int cNumber) {
		String temp = bookName + author;
		cNumber = cNumber * 10000000;
		cNumber += temp.replaceAll(" ", "").length() * 100;
		cNumber += (int) bookName.charAt(0) % 1000 * 10000;
		cNumber += this.checkBooks(cNumber);
		return cNumber;
	}

	public void searchBook(Scanner input) {
		Book book;
		System.out.println("(분류번호)");
		System.out.println("0.기타 1.철학 2.역사 3.사회과학 4.자연과학 5.기술 6.산업 7.예술 8.언어 9.문학");
		System.out.println("찾으시려는 책의 분류 번호, 제목, 저자 중에 하나를 입력해주세요.");
		String temp = input.nextLine();
		this.iBook = books.iterator();
		boolean check = true;
		while (this.iBook.hasNext()) {
			book = this.iBook.next();
			if (temp.length() == 1 && book.getCNumber() / 10000000 == (int) temp.charAt(0) - 48) {
				System.out.println("\n" + book.search() + "\n" + book.getCNumber());
				check = false;
			} else if (book.search().split("\n")[0].equals(temp)) {
				System.out.println("\n" + book.search() + "\n" + book.getCNumber());
				check = false;
			} else if (book.search().split("\n")[1].equals(temp)) {
				System.out.println("\n" + book.search() + "\n" + book.getCNumber());
				check = false;
			}
		}
		if (check) {
			System.out.println("결과가 없습니다.");
		}

	}

	public int login(Scanner input) {
		System.out.println("ID를 입력해주세요.만약 guest이시면 검색이외에 서비스는 이용하실수 없습니다.");
		System.out.println("exit을 입력하면 종료합니다.");
		String ID = input.nextLine();
		this.iLibrarian = librarians.iterator();
		while (this.iLibrarian.hasNext()) {
			String x = this.iLibrarian.next();
			if (x.equals(ID)) {
				System.out.println(ID + "(관리자)님, 환영합니다.");
				return 0;
			}
		}
		if (ID.equals("guest")) {
			System.out.println(ID + "(이용자)님, 환영합니다.");
			return 1;
		} else if (ID.equals("exit")) {
			System.out.println("시스템을 종료합니다. 이용해 주셔서 감사합니다.");
			return 2;
		} else {
			System.out.println("올바른 ID를 입력해주세요.");
			return 3;
		}
	}

	public void borrowBook(Scanner input) {
		System.out.println("대출하시고자 하는 이용자의 이름을 입력해주세요.");
		boolean check = true;
		String borrowerName = input.nextLine();
		Book book;
		this.iBorrower = this.borrowers.iterator();
		while (this.iBorrower.hasNext()) {
			Borrower x = this.iBorrower.next();
			if (x.getName().equals(borrowerName)) {
				System.out.println(x.getName() + "님은 책을 " + (3 - x.getCount()) + "권을 대출 하실 수 있습니다.");
				System.out.println("대출할 책의 고유번호를 입력해주세요.");
				int cNumber = 0;
				try {
					cNumber = input.nextInt();
					input.nextLine();

				} catch (InputMismatchException e) {
					System.out.println("숫자로 써주세요.");
					input.nextLine();
				}

				this.iBook = books.iterator();
				while (this.iBook.hasNext()) {
					book = this.iBook.next();
					if (x.getCount() >= 3) {
						System.out.println("이미 대출 한도를 초과하셨습니다.");
						check = false;
						break;
					}
					if (!book.loanStatus() && book.getCNumber() == cNumber) {
						System.out.println("이미 대출 중인 책입니다");
						check = false;
						break;

					}
					if (book.getCNumber() == cNumber) {
						book.connect(x);
						x.upCount();
						check = false;
						System.out.println("대출이 성공적으로 완료되었습니다.");
						break;
					}
				}
			}
		}
		if (check) {
			System.out.println("대출에 실패하셨습니다. 다시 시도해주세요.");
		}
	}

	public void returnBook(Scanner input) {
		Book book = null;
		boolean check = true;
		System.out.println("반납할 책의 고유번호를 입력해주세요.");
		int cNumber = 0;
		try {
			cNumber = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("숫자로 써주세요.");
			input.nextLine();
		}

		this.iBook = books.iterator();
		while (this.iBook.hasNext()) {
			book = this.iBook.next();
			if (book.getCNumber() == cNumber) {
				book.getBorrower().disCount();
				book.disconnect();
				System.out.println("반납이 완료되었습니다.");
				check = false;
				break;
			}
		}
		if (check) {
			System.out.println("반납에 실패하였습니다. 다시 시도해주세요.");
		}
	}

	public void displayBookOnLone() { // 대출 중인 책 전시
		Book book;
		boolean check = true;
		this.iBook = books.iterator();
		while (this.iBook.hasNext()) {
			book = this.iBook.next();
			if (!book.loanStatus()) {
				System.out.println("\n" + book.search() + "\n" + book.getCNumber());
				check = false;
			}

		}
		if (check) {
			System.out.println("대출중인 책이 없습니다.");
		}
	}

	public void displayBookForLone() { // 대출 가능한 책 전시 貸出できる本の展示
		Book book;
		this.iBook = books.iterator();
		while (this.iBook.hasNext()) {
			book = this.iBook.next();
			if (book.loanStatus()) {
				System.out.println("\n" + book.search() + "\n" + book.getCNumber());
			}
		}
	}
}

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
		for(int i = 0; i < 3; i++) {
			bookName = "English";
			author = "Tom";
			cNumber = this.makeCNumber(bookName, author, 8);
			book = new Book(bookName, author, cNumber);
			this.books.add(book);
			bookName = "Japanese";
			author = "翔";
			cNumber = this.makeCNumber(bookName, author, 8);
			book = new Book(bookName, author, cNumber);
			this.books.add(book);
			bookName = "Korean";
			author = "Yu Gwan-sun";
			cNumber = this.makeCNumber(bookName, author, 8);
			book = new Book(bookName, author, cNumber);
			this.books.add(book);
		}
<<<<<<< HEAD
		Borrower borrower = new Borrower("HongGilDong");
=======
		Borrower borrower = new Borrower("홍길동");
>>>>>>> d2817acb738ae406a642112c3a0d77a6b74a3bb0
		this.borrowers.add(borrower);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Library library = new Library("SunMoon");
		library.librarians.add("KimYoMi");
		while (true) {
			System.out.println("Welcome, to the library of " + library.getName());
			int ID = library.login(input);
			int choices;
			boolean exit;
			boolean systemCall = false;
			switch (ID) {
			case 0:
				while (true) {
					exit = false;
					System.out.println("Select the service you want.");
					System.out.println("0: Register a book   1: Register a borrower   2: Book loan   3: Return book   4: Display book for lone   5: Display book on lone  6: Logout");
					choices = input.nextInt();
					input.nextLine();
					switch (choices) {
					case 0: // Register a book  
						library.signUpBook(input);
						break;
					case 1: // Register a borrower
						library.signUpBorrower(input);
						break;
					case 2: // Book loan
						library.borrowBook(input);
						break;
					case 3: // Return book
						library.returnBook(input);
						break;
					case 4: // Display book for lone
						library.displayBookOnLone();
						break;
					case 5: // Display book on lone
						library.displayBookForLone();
						break;
					case 6: // Logout
						exit = true;
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
					System.out.println("Do you want to continue searching? (Yes/No : Case sensitive)");
					String continueSearch = input.nextLine();
					if (continueSearch.equals("No")) {
						break;
					} else if (continueSearch.equals("Yes")) {
						continue;
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
		System.out.println("Please enter the category number, title or author of the book you are registering.");
		System.out.println("0.Other 1.Philosophy 2. History 3. Social Sciences 4. Natural Science 5.Technology 6. Industry 7.Art 8.Language 9.Literature");
		System.out.println("Please enter the type of book.(Number)");
		int cNumber = input.nextInt();
		input.nextLine();
		System.out.println("Please enter the title of the book.");
		String bookName = input.nextLine();
		System.out.println("Please enter the author of the book.");
		String author = input.nextLine();
		cNumber = this.makeCNumber(bookName, author, cNumber);
		Book book = new Book(bookName, author, cNumber);
		this.books.add(book);
	}

	public void signUpBorrower(Scanner input) {
		System.out.println("Please enter the borrower's name to register.");
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
			System.out.println("Sorry, name already registered.");
		} else {
			System.out.println("Successfully registered.");
			Borrower b = new Borrower(borrowerName);
			this.borrowers.add(b);
		}
	}

	public int checkBooks(int cNumber) {
		this.iBook = this.books.iterator();
		int count = 0;
		while (this.iBook.hasNext()) {
			Book b = this.iBook.next();
			if (b.getCNumber() == cNumber) {
				count++;
			}
		}
		return count;
	}

	public int makeCNumber(String bookName, String author, int cNumber) {
		String temp = bookName + author;
		cNumber = cNumber * 10000000;
		cNumber += temp.replaceAll(" ", "").length() * 100;
		cNumber += (int) bookName.charAt(0) % 100 * 10000;
		cNumber += this.checkBooks(cNumber);
		return cNumber;
	}

	public void searchBook(Scanner input) {
		Book book;
		System.out.println("Please enter the category number, title, or author of the book you are looking for.");
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
		if(check) {
			System.out.println("No results.");
		}

	}

	public int login(Scanner input) {
		System.out.println("Please enter your ID.If you are a guest, you cannot use the service except for search.");
		String ID = input.nextLine();
		this.iLibrarian = librarians.iterator();
		while (this.iLibrarian.hasNext()) {
			String x = this.iLibrarian.next();
			if (x.equals(ID)) {
				System.out.println(ID + "Ms. "+ ID +"\n" + " Welcome, sir.");
				return 0;
			}
		}
		if (ID.equals("guest")) {
			System.out.println(ID + "Guest. "+ ID +"\n" + " Welcome, sir.");
			return 1;
		} else if (ID.equals("exit")) {
			System.out.println("Shut down the system.");
			return 2;
		} else {
			System.out.println("Please enter a valid ID.");
			return 3;
		}
	}

	public void borrowBook(Scanner input) {
<<<<<<< HEAD
		System.out.println("Please enter the name of the borrower you wish to borrow.");
=======
		System.out.println("대출하시고자 하는 이용자의 이름을 입력해주세요.");
		boolean check = true;
>>>>>>> d2817acb738ae406a642112c3a0d77a6b74a3bb0
		String borrowerName = input.nextLine();
		Book book;
		this.iBorrower = this.borrowers.iterator();
		while (this.iBorrower.hasNext()) {
			Borrower x = this.iBorrower.next();
			if (x.getName().equals(borrowerName)) {
				System.out.println("Please enter the category number of the book you want to check out.");
				int cNumber = input.nextInt();
				input.nextLine();
				this.iBook = books.iterator();
				while (this.iBook.hasNext()) {
					book = this.iBook.next();
					if (book.getCNumber() == cNumber) {
						book.connect(x);
<<<<<<< HEAD
						System.out.println("The loan is successful.");
=======
						check = false;
						System.out.println("대출이 성공석으로 완료되었습니다.");
>>>>>>> d2817acb738ae406a642112c3a0d77a6b74a3bb0
						break;
					}
				}
			}
		}
<<<<<<< HEAD
		System.out.println("Failed to check out. Please try again");
=======
		if(check) {
			System.out.println("대출에 실패하였습니다. 다시 시도해주세요.");
		}
>>>>>>> d2817acb738ae406a642112c3a0d77a6b74a3bb0
	}

	public void returnBook(Scanner input) {
		Book book = null;
		boolean check = true;
<<<<<<< HEAD
		System.out.println("Please enter the category number of the book to return.");
=======
		System.out.println("반납할 책의 고유번호를 입력해주세요.");
>>>>>>> d2817acb738ae406a642112c3a0d77a6b74a3bb0
		int cNumber = input.nextInt();
		input.nextLine();
		this.iBook = books.iterator();
		while (this.iBook.hasNext()) {
			book = this.iBook.next();
			if (book.getCNumber() == cNumber) {
				book.disconnect();
<<<<<<< HEAD
				System.out.println("Return completed.");
=======
				System.out.println("반납이 완료되었습니다.");
>>>>>>> d2817acb738ae406a642112c3a0d77a6b74a3bb0
				check = false;
				break;
			}
		}
		if(check) {
<<<<<<< HEAD
			System.out.println("Failed to return. Please try again.");
=======
			System.out.println("반납에 실패하였습니다. 다시 시도해주세요.");
>>>>>>> d2817acb738ae406a642112c3a0d77a6b74a3bb0
		}
	}


	public void displayBookOnLone() { // 대출 중인 책 전시
		Book book;
		this.iBook = books.iterator();
		while (this.iBook.hasNext()) {
			book = this.iBook.next();
			if (!book.loanStatus()) {
				System.out.println("\n" + book.search() + "\n" + book.getCNumber());
			}
		}
	}

	public void displayBookForLone() {  // 대출 가능한 책 전시 貸出できる本の展示
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


import java.util.*;

public class Library {

	private String name;
	private TreeSet<Book> books = new TreeSet<Book>(new MyComparator ());
	private HashSet<Borrower> borrowers = new HashSet<Borrower>();
	private HashSet<String> librarians = new HashSet<String>();
	private Iterator<Book> iBook = books.iterator();
	private Iterator<Borrower> iBorrower = borrowers.iterator();
	private Iterator<String> iLibrarian = librarians.iterator();
	
	public Library(String name) {
		this.name = name; 
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Library library = new Library("SunMoonLibrary");
		while(true) {
			System.out.println((int)input.nextLine().charAt(0));
			System.out.println("�뼱�꽌�삤�꽭�슂 " + library.getName() + "�엯�땲�떎.");
			library.librarians.add("unknow");
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
				System.out.println("System...");
				systemCall = true;
				break;
			case 3:
				System.out.println("error");
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
		System.out.println("�벑濡앺븯�떆�뒗 梨낆쓽 遺꾨쪟踰덊샇,�젣紐⑷낵 ���옄瑜� �엯�젰�빐二쇱꽭�슂.");
		System.out.println("0.湲고� 1.臾명븰 2.�뿭�궗 3.�궗�쉶怨쇳븰 4.�옄�뿰怨쇳븰 5.湲곗닠 6.�궛�뾽 7.�삁�닠 8.�뼵�뼱 9.臾명븰");
		System.out.println("梨낆쓽 醫낅쪟瑜� �엯�젰�빐二쇱꽭�슂.(�닽�옄)");
		int cNumber = input.nextInt();
		input.nextLine();
		System.out.println("梨낆쓽 �젣紐⑹쓣 �엯�젰�빐二쇱꽭�슂.");
		String bookName = input.nextLine();
		System.out.println("梨낆쓽 ���옄 �엯�젰.");
		String author = input.nextLine();
		cNumber = this.makeCNumber(bookName, author, cNumber);
		System.out.println(cNumber);
		Book book = new Book(bookName, author, cNumber);
		this.books.add(book);
	}
	public void signUpBorrower(Scanner input) {
		System.out.println("��異쒖옄�쓽 �씠由꾩쓣 �엯�젰�빐二쇱꽭�슂.");
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
			System.out.println("�씠誘� �벑濡앸맂 �씠由꾩엯�땲�떎.");
		} else {
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
		System.out.println(bookName.charAt(0));
		System.out.println((int)bookName.charAt(0));
		cNumber += this.checkBooks(cNumber);
		return cNumber;
	}
	public void searchBook(Scanner input) {
		Book book;
		System.out.println("type the number tytle or author");
		String temp = input.nextLine();
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
			System.out.println("error");
		}
	}
	public Book choiceBook(int cNumber) {
		Book book;
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
		System.out.println("ID瑜� �엯�젰�빐二쇱꽭�슂.");
		String ID = input.nextLine();
		while(this.iLibrarian.hasNext()) {
			String x = (String)this.iLibrarian.next();
			if(x.equals(ID)) {
				System.out.println("message");
				return 0;
			}
		}
		if(ID.equals("gest")) {
			return 1;
		}else if(ID.equals("exit")){
			return 2;
		}else {
			return 3;
		}
	}
	public void borrowBook(Scanner input, Book book) {
		System.out.println("��異쒖옄�쓽 �씠由꾩쓣  �엯�젰�빐二쇱꽭�슂 �엳�븯");
		String borrowerName = input.nextLine();
		boolean check = true;
		while(this.iBorrower.hasNext()) {
			Borrower x = (Borrower)this.iBorrower.next();
			if(x.getName().equals(borrowerName) && book.loanStatus()) {
				book.connect(x);
				System.out.println("泥섎━媛� �셿猷뚮릺�뿀�뒿�땲�떎.");
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
		System.out.println("泥섎━媛� �셿猷띾릺�뿀�뒿�땲�떎.");
	}
}

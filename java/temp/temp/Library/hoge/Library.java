
import java.util.*;

public class Library {

	private String name;
	private HashSet<Book> books = new HashSet<Book>();
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
		while(true) {
			System.out.println("�뼱�꽌�삤�꽭�슂 " + library.getName() + "�엯�땲�떎.");
			int ID = library.login(input);
			boolean systemCall = false;
			switch(ID) {
			case 0:
//				while(true) {
//					int 
				library.signUpBook(input);
				library.signUpBook(input);
				library.signUpBook(input);
				library.signUpBorrower(input);
				library.signUpBorrower(input);
				library.signUpBorrower(input);
//				}
				break;
			case 1:
//				Book book = library.searchBook(input);
//				library.borrowBook(input, book);
//				library.returnBook(book);
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
		String cNumber = input.nextLine();
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
	public String checkBooks(String cNumber) {
		String No = "00";
		this.iBook = this.books.iterator();
		int count = 0;
		while(this.iBook.hasNext()) {
			Book b = (Book)this.iBook.next();
			if(cNumber.equals(b.getCNumber().substring(0, cNumber.length()))) {
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
		cNumber += this.checkBooks(cNumber);
		return cNumber;
	}
//		Book book = null;
//		System.out.println("癲꾩뮁�눊占쎄굉占쎄데占쎌몟占쎄쿁占쎄땋占쎄뭇占쎄틡占쎄틓占쎄쾹占쎈┼占쎈렭占쎄낟紐�占쎈겧援�占쎈��占쎈뮓占쎄괼占쎄쾷占쎄괜占쎄굵占쎄괵占쎄콢占쏙옙");
//		System.out.println("0.歷띕ㅊ怡� 1.占쎈쾼�띰옙 2.癲녿떣琉� 3.埇쎌뼂�룒�뇖臾덌옙占� 4.占쎈닅占쎄쉥�뇖臾덌옙占� 5.占쏙옙�깗占� 6.占쎈덫�뮫占� 7.占쎈뱜�깗占� 8.庸뉛옙亦껓옙 9.占쎈뻤�띰옙");
//		System.out.println("�넫�굝履� 占쎌뿯占쎌젾(占쎈땾占쎌쁽)");
//		String cNumber = input.nextLine();
//		System.out.println("筌�占� 占쎌젫筌륅옙 占쎌뿯占쎌젾");
//		String bookName = input.nextLine();
//		System.out.println("筌�占� 占쏙옙占쎌쁽 占쎌뿯占쎌젾");
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
	public int login(Scanner input) {
		System.out.println("ID瑜� �엯�젰�빐二쇱꽭�슂.");
		String ID = input.nextLine();
		this.iLibrarian = this.librarians.iterator();
		while(this.iLibrarian.hasNext()) {
			String x = (String)this.iLibrarian.next();
			if(x.equals(ID)) {
				System.out.println("message");
				return 0;
			}
		}
		if(ID == "gest") {
			return 1;
		}else if(ID == "exit"){
			return 2;
		}else {
			return 3;
		}
	}
	public void borrowBook(Scanner input, Book book) {
		System.out.println("��異쒖옄�쓽 �씠由꾩쓣  �엯�젰�빐二쇱꽭�슂 �엳�븯");
		String borrowerName = input.nextLine();
		this.iBorrower = this.borrowers.iterator();
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

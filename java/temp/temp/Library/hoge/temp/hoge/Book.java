import java.util.Comparator;

public class Book implements Comparator<Book>{

	private String name;
	private String author;
	private int cNumber;
	private Borrower connection = null;
	
	public Book() {
	}
	public Book(String name, String author, int cNumber){
		this.name = name;
		this.author = author;
		this.cNumber = cNumber;
	}
	
	public void connect(Borrower borrower) {
		this.connection = borrower;
	}
	public void disconnect() {
		this.connection = null;
	}
	public String search(){
		return this.name + "" + "\n" + this.author;
	}
	public int getCNumber() {
		return this.cNumber;
	}
	public boolean loanStatus() {
		if(this.connection == null) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int compare(Book b1, Book b2) {
		
		if(b1.getCNumber() < b2.getCNumber()) {
			return 1;
		}else if(b1.getCNumber() > b2.getCNumber()) {
			return -1;
		}else {
			return 0;
		}
	}
}

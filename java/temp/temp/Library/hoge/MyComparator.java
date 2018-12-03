import java.util.Comparator;

public class MyComparator implements Comparator<Book>{

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

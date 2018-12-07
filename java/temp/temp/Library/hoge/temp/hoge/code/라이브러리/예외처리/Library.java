/**
 * 
 * ������ ���� �ý���
 * (�й� �̸�)2017315022 ���μ�, 2017315024 ������, 
 *          2017315049 ��īŸ �����, 2017315055 ���� ī�����
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
			bookName = "������";
			author = "��";
			cNumber = this.makeCNumber(bookName, author, 8);
			book = new Book(bookName, author, cNumber);
			this.books.add(book);
			bookName = "�ѱ���";
			author = "�ڿϼ�";
			cNumber = this.makeCNumber(bookName, author, 8);
			book = new Book(bookName, author, cNumber);
			this.books.add(book);
		}
		Borrower borrower = new Borrower("��ö��");
		this.borrowers.add(borrower);
		this.librarians.add("�迵��");
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Library library = new Library("SunMoonLibrary");
		while (true) {
			System.out.println("ȯ���մϴ�,  " + library.getName() + "�Դϴ�.");
			int ID = library.login(input);
			int choices;
			boolean exit;
			boolean systemCall = false;
			switch (ID) {
			case 0:
				while (true) {
					choices = 10;
					exit = false;
					System.out.println("���Ͻô� ���񽺸� �������ּ���.");
					System.out.println(
							"0: å ���   1: �̿��� ���   2: å ����   3: å �ݳ�   4: ���� ���� å ����   5: ���� ������ å ����  6: �α׾ƿ�");
					try {
						choices = input.nextInt();
						input.nextLine();

					} catch (InputMismatchException e) {
						System.out.println("���ڷ� ���ּ���.");
						input.nextLine();
					}

					switch (choices) {
					case 0: // å ���
						library.signUpBook(input);
						break;
					case 1: // �̿��� ���
						library.signUpBorrower(input);
						break;
					case 2: // ����
						library.borrowBook(input);
						break;
					case 3: // �ݳ�
						library.returnBook(input);
						break;
					case 4: // ���� �� ����
						library.displayBookOnLone();
						break;
					case 5: // ���� ���� ����
						library.displayBookForLone();
						break;
					case 6: // �α׾ƿ�
						exit = true;
						System.out.println("���񽺸� �̿��� �ּż� �����մϴ�.");
						break;
					default:
						System.out.println("�ùٸ� ���ڸ� �Է����ּ���.");
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
					System.out.println("��� �˻��Ͻðڽ��ϱ�? (Yes/No : ��ҹ��ڸ� �����մϴ�.)");
					String continueSearch = input.nextLine();
					if (continueSearch.equals("No")) {
						System.out.println("���񽺸� �̿����ּż� �����մϴ�.");
						break;
					} else if (continueSearch.equals("Yes")) {
						continue;

					} else {
						System.out.println("Yes/No�� �Է����ּ���.");
						System.out.println("�α��� ȭ������ ���ư��ϴ�.");
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
		System.out.println("����Ͻô� å�� �з���ȣ,����� ���ڸ� �Է����ּ���.");
		System.out.println("0.��Ÿ 1.ö�� 2.���� 3.��ȸ���� 4.�ڿ����� 5.��� 6.��� 7.���� 8.��� 9.����");
		System.out.println("å�� ������ �Է����ּ���.(����)");
		int cNumber = 10;
		try {
			cNumber = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("���ڷ� ���ּ���.");
			input.nextLine();

		}
		if (cNumber > 9) {
			System.out.println("�ùٸ� ���ڸ� �Է����ּ���.");
			this.signUpBook(input);
			return;
		}
		System.out.println("å�� ������ �Է����ּ���.");
		String bookName = input.nextLine();
		System.out.println("å�� ���ڸ� �Է����ּ���.");
		String author = input.nextLine();
		cNumber = this.makeCNumber(bookName, author, cNumber);
		Book book = new Book(bookName, author, cNumber);
		this.books.add(book);
		System.out.println("���������� å�� ��� �Ǿ����ϴ�.");
	}

	public void signUpBorrower(Scanner input) {
		System.out.println("����Ͻ÷��� �̿����� ������ �Է����ּ���.");
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
			System.out.println("�̹� ��ϵǾ� �ִ� �̸��Դϴ�.");
		} else {
			System.out.println("���������� ��ϵǾ����ϴ�.");
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
		System.out.println("(�з���ȣ)");
		System.out.println("0.��Ÿ 1.ö�� 2.���� 3.��ȸ���� 4.�ڿ����� 5.��� 6.��� 7.���� 8.��� 9.����");
		System.out.println("ã���÷��� å�� �з� ��ȣ, ����, ���� �߿� �ϳ��� �Է����ּ���.");
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
			System.out.println("����� �����ϴ�.");
		}

	}

	public int login(Scanner input) {
		System.out.println("ID�� �Է����ּ���.���� guest�̽ø� �˻��̿ܿ� ���񽺴� �̿��ϽǼ� �����ϴ�.");
		System.out.println("exit�� �Է��ϸ� �����մϴ�.");
		String ID = input.nextLine();
		this.iLibrarian = librarians.iterator();
		while (this.iLibrarian.hasNext()) {
			String x = this.iLibrarian.next();
			if (x.equals(ID)) {
				System.out.println(ID + "(������)��, ȯ���մϴ�.");
				return 0;
			}
		}
		if (ID.equals("guest")) {
			System.out.println(ID + "(�̿���)��, ȯ���մϴ�.");
			return 1;
		} else if (ID.equals("exit")) {
			System.out.println("�ý����� �����մϴ�. �̿��� �ּż� �����մϴ�.");
			return 2;
		} else {
			System.out.println("�ùٸ� ID�� �Է����ּ���.");
			return 3;
		}
	}

	public void borrowBook(Scanner input) {
		System.out.println("�����Ͻð��� �ϴ� �̿����� �̸��� �Է����ּ���.");
		boolean check = true;
		String borrowerName = input.nextLine();
		Book book;
		this.iBorrower = this.borrowers.iterator();
		while (this.iBorrower.hasNext()) {
			Borrower x = this.iBorrower.next();
			if (x.getName().equals(borrowerName)) {
				System.out.println(x.getName() + "���� å�� " + (3 - x.getCount()) + "���� ���� �Ͻ� �� �ֽ��ϴ�.");
				System.out.println("������ å�� ������ȣ�� �Է����ּ���.");
				int cNumber = 0;
				try {
					cNumber = input.nextInt();
					input.nextLine();

				} catch (InputMismatchException e) {
					System.out.println("���ڷ� ���ּ���.");
					input.nextLine();
				}

				this.iBook = books.iterator();
				while (this.iBook.hasNext()) {
					book = this.iBook.next();
					if (x.getCount() >= 3) {
						System.out.println("�̹� ���� �ѵ��� �ʰ��ϼ̽��ϴ�.");
						check = false;
						break;
					}
					if (!book.loanStatus() && book.getCNumber() == cNumber) {
						System.out.println("�̹� ���� ���� å�Դϴ�");
						check = false;
						break;

					}
					if (book.getCNumber() == cNumber) {
						book.connect(x);
						x.upCount();
						check = false;
						System.out.println("������ ���������� �Ϸ�Ǿ����ϴ�.");
						break;
					}
				}
			}
		}
		if (check) {
			System.out.println("���⿡ �����ϼ̽��ϴ�. �ٽ� �õ����ּ���.");
		}
	}

	public void returnBook(Scanner input) {
		Book book = null;
		boolean check = true;
		System.out.println("�ݳ��� å�� ������ȣ�� �Է����ּ���.");
		int cNumber = 0;
		try {
			cNumber = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("���ڷ� ���ּ���.");
			input.nextLine();
		}

		this.iBook = books.iterator();
		while (this.iBook.hasNext()) {
			book = this.iBook.next();
			if (book.getCNumber() == cNumber) {
				book.getBorrower().disCount();
				book.disconnect();
				System.out.println("�ݳ��� �Ϸ�Ǿ����ϴ�.");
				check = false;
				break;
			}
		}
		if (check) {
			System.out.println("�ݳ��� �����Ͽ����ϴ�. �ٽ� �õ����ּ���.");
		}
	}

	public void displayBookOnLone() { // ���� ���� å ����
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
			System.out.println("�������� å�� �����ϴ�.");
		}
	}

	public void displayBookForLone() { // ���� ������ å ���� ����Ǫ����������
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
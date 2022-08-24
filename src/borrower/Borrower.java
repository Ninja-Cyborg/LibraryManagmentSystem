package borrower; 

public class Borrower {
	
	private String borrowId;
	private String scholarId;
	private String bookId;
	private String returnDate;
	private String borrowDate;
	private String fine;
	
	Borrower(String borrowId, String bookId,
			String borrowDate,String returnDate, String fine){
		this.borrowId = borrowId;
		this.bookId = bookId;
		this.returnDate = returnDate;
		this.borrowDate = borrowDate;
		this.fine = fine;
	}

	public String getReturnDate() {
		return returnDate;
	}
	public String getFine() {
		return fine;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public String getBookId() {
		return bookId;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
}

package book;

import java.sql.Connection;

import application.dbConnection;

public class Book {

	private String bookTitle;
	private String bookId;
	private String bookAuthor;
	private String bookPub;
	private String bookAvail;
	private String bookRequest;
	private String borrowId;

	public Book(String bookTitle, String bookId, String bookAuthor, String bookPub, String bookAvail,
			String bookRequest, String borrowId) {
		super();
		this.bookTitle = bookTitle;
		this.bookId = bookId;
		this.bookAuthor = bookAuthor;
		this.bookPub = bookPub;
		this.bookAvail = bookAvail;
		this.bookRequest = bookRequest;
		this.borrowId = borrowId;
	}


	Book(String bookTitle, String bookId, String bookAuthor, 
			String bookPub, String bookAvail){
		this.bookTitle = bookTitle;
		this.bookId = bookId;
		this.bookAuthor = bookAuthor;
		this.bookPub = bookPub;
		this.bookAvail = bookAvail;
	}

	public Book(String bookId, String bookAvail, String bookRequest, String borrowId) {
		this.bookId = bookId;
		this.bookAvail = bookAvail;
		this.bookRequest = bookRequest;
		this.borrowId = borrowId;
	}

	public String getBookRequest() {
		return bookRequest;
	}

	public String getBorrowId() {
		return borrowId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public String getBookId() {
		return bookId;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public String getBookPub() {
		return bookPub;
	}
	public String getBookAvail() {
		return bookAvail;
	}
}

package book;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BookController implements Initializable{

	@FXML
	private TextField bookIdTxt;
	@FXML
	private TextField bookNameTxt;
	@FXML
	private TextField authorTxt;
	@FXML
	private TextField publisherTxt;
	@FXML
	private TextField yearTxt;
	@FXML
	private Label addBooktxt;
	@FXML
	private Button quit;
	@FXML
	private Button addBook;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void quitOnAction(ActionEvent event) {
		Stage stage = (Stage) quit.getScene().getWindow();
		stage.close();
	}

	public void addBookOnAction(ActionEvent event) {

		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
		
		String bookId = bookIdTxt.getText();
		String bookTitle = bookNameTxt.getText();
		String bookAuthor = authorTxt.getText();
		String bookPub = publisherTxt.getText();
		String bookAvail = "yes";
		String bookRequest = "no";
		
		String insertBook = "insert into book(bookTitle, bookId, bookAuthor, bookPub, bookAvail, bookRequest) values ('";
		String insertValues = bookTitle+ "','" + bookId + "','" + bookAuthor + "','"+ bookPub + "','" + bookAvail + "','"+ bookRequest +"');";
		String insertToAdd = insertBook + insertValues;
		try {
			Statement st = connectdb.createStatement();
			st.executeUpdate(insertToAdd);
			addBooktxt.setText("Book Added to Database");
	
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
}

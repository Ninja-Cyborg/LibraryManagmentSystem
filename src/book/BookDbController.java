package book;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;
import java.util.ResourceBundle;

import application.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

// show books in database

public class BookDbController implements Initializable {

	@FXML
	private TableView<Book> bookTable;
	@FXML
	private TableColumn<Book, String> bookTitle;
	@FXML
	private TableColumn<Book, String> bookId;
	@FXML
	private TableColumn<Book, String> bookAuthor;
	@FXML
	private TableColumn<Book, String> bookPub;
	@FXML
	private TableColumn<Book, String> bookAvail;
	@FXML
	private Button requestBook;
	@FXML
	private Button quit;
	@FXML
	private TextField requestStatus;
	@FXML
	private TextField bookIdtxt;
	
	ObservableList<Book> oList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		
		bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		bookAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
		bookPub.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
		bookAvail.setCellValueFactory(new PropertyValueFactory<>("bookAvail"));
		showDb();
		bookTable.setItems(oList);
		}
	
	public void showDb() {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
		
		String data = "SELECT * FROM world.book";
		try {
			Statement st = connectdb.createStatement();
			ResultSet rs = st.executeQuery(data);
			
			while(rs.next()) {
				oList.add(new Book(rs.getString("bookTitle"), 
							rs.getString("bookId"), 
							rs.getString("bookAuthor"), 
							rs.getString("bookPub"), 
							rs.getString("bookAvail")));
			}
		}catch(Exception e){	
		}
	}
	
	public void requestBookbtnOnAction(ActionEvent event) {
		validateRequest();
	}
	
	public void validateRequest() {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();

		String verifyAvail = "SELECT count(1) FROM book WHERE bookId ='" + bookIdtxt.getText()  +
							"' And bookAvail = 'yes' AND bookRequest = 'no'; ";
		try {
			Statement st = connectdb.createStatement();
			ResultSet queryResult = st.executeQuery(verifyAvail);
			while(queryResult.next()) {
				if(queryResult.getInt(1) == 1) {
					String reqBook = "UPDATE book set bookRequest= 'yes' where bookId = '"+ bookIdtxt.getText() +"';";		
					try {
						Statement is = connectdb.createStatement();
						is.executeUpdate(reqBook);		
					}catch(Exception e) {
						e.printStackTrace();
						e.getCause();
					}
					//issueStatuslbl.setText("Book Issued");
					requestStatus.setText("Book Requested");
				}
				else {
					requestStatus.setText("Not Available");
				}
			}
		}catch(Exception e) {	
		}	
	}
	
	public void quitOnAction(ActionEvent event) {
		Stage stage = (Stage) quit.getScene().getWindow();
		stage.close();
	}
}

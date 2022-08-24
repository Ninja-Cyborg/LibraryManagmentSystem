package book;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class IssueBookController implements Initializable {

	@FXML
	private TableView<Book> requestTable;
	@FXML
	private TableColumn<Book, String> bookId;
	@FXML
	private TableColumn<Book, String> bookAvail;
	@FXML
	private TableColumn<Book, String> bookRequest;
	@FXML
	private TableColumn<Book, String> borrowId;
	@FXML
	private Button issueBookbtn;
	@FXML
	private Button quit;
	@FXML
	private TextField bookIdTxt;
	@FXML
	private TextField issueStatuslbl;
	
	ObservableList<Book> reqList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle res) {
		
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		bookAvail.setCellValueFactory(new PropertyValueFactory<>("bookAvail"));
		bookRequest.setCellValueFactory(new PropertyValueFactory<>("bookRequest"));
		borrowId.setCellValueFactory(new PropertyValueFactory<>("borrowId"));
		showDb();
		requestTable.setItems(reqList);
		}
	
	public void showDb() {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
		
		String data = "SELECT * FROM world.book";
		try {
			Statement st = connectdb.createStatement();
			ResultSet rs = st.executeQuery(data);
			
			while(rs.next()) {
				reqList.add(new Book(rs.getString("bookId"), 
							rs.getString("bookAvail"),
							rs.getString("bookRequest"),
							rs.getString("borrowId")));
			}
		}catch(Exception e){	
		}
	}
	
	public void issueBookbtnOnAction(ActionEvent event) {
		checkavailability();
	}
	
	public void checkavailability() {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();

		String verifyAvail = "SELECT count(1) FROM book WHERE bookId ='" + bookIdTxt.getText()  +
							"' And bookAvail = 'yes' AND bookRequest = 'yes'; ";
		try {
			Statement st = connectdb.createStatement();
			ResultSet queryResult = st.executeQuery(verifyAvail);
			while(queryResult.next()) {
				if(queryResult.getInt(1) == 1) {
					String issueBookDb = "UPDATE book set bookAvail = 'no', bookRequest= 'no' where bookId = '"+ bookIdTxt.getText() +"';";		
					try {
						Statement is = connectdb.createStatement();
						is.executeUpdate(issueBookDb);		
					}catch(Exception e) {
						e.printStackTrace();
						e.getCause();
					}
					issueStatuslbl.setText("Book Issued");
				}
				else {
					issueStatuslbl.setText("Book Issuing failed: Book Unavailable");
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

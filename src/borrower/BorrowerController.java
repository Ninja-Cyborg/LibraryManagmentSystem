package borrower;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.dbConnection;
import book.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BorrowerController implements Initializable{

	@FXML
	private TableView<Borrower> historyTable;
	@FXML
	private TableColumn<Borrower, String> borrowId;
	@FXML
	private TableColumn<Borrower, String> bookId;
	@FXML
	private TableColumn<Borrower, String> borrowDate;
	@FXML
	private TableColumn<Borrower, String> returnDate;
	@FXML
	private TableColumn<Borrower, String> fine;
	@FXML
	private Button requestBookbtn;
	@FXML
	private TextField bookIdTxt;
	@FXML
	private Label requestStatusTxt;
	String scholarId;
	@FXML
	private Button quit;
	
	public String getScholarId() {
		return scholarId;
	}

	public void setScholarId(String scholarId) {
		this.scholarId = scholarId;
	}

	ObservableList<Borrower> bList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		borrowId.setCellValueFactory(new PropertyValueFactory<>("borrowId"));
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		borrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
		returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		fine.setCellValueFactory(new PropertyValueFactory<>("fine"));
		
		connectDb();
		historyTable.setItems(bList);
	}
	
	public void connectDb() {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
		
		String Id = "29957890";
		String data = "SELECT * FROM world.borrower where scholarId ='"+ Id +"';";
		try {
			Statement st = connectdb.createStatement();
			ResultSet rs = st.executeQuery(data);
			
			while(rs.next()) {
				bList.add(new Borrower(rs.getString("borrowID"),
						rs.getString("bookId"),
						rs.getString("borrowDate"),
						rs.getString("returnDate"),
						rs.getString("fine")));
			}
		}catch(Exception e){	
		}
	}
	public void quitbtnOnAction(ActionEvent event) {
		Stage stage = (Stage) quit.getScene().getWindow();
		stage.close();
	}
}

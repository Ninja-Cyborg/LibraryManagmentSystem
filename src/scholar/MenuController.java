package scholar;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.dbConnection;
import borrower.BorrowerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuController implements Initializable{

	@FXML
	private Button requestBook;
	@FXML 
	private Button accountInfoBtn;
	@FXML
	private Button borrowHistory;
	@FXML
	private Button logoutbtn;
	@FXML
	TextField userTxt;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void bookDbbtnOnAction(ActionEvent event) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Book/BookDb.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		Stage stage = (Stage) requestBook.getScene().getWindow();
		stage.setTitle("Book Database");
		stage.setScene(scene);
		stage.show();
	}
	
	public void accountInfobtnOnAction(ActionEvent action) throws IOException{
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/scholar/ScholarInfo.fxml"));
		Parent root = loader.load();
		
		String username = userTxt.getText(); 
		ScholarInfoController infoCon = loader.getController();
		infoCon.setUsername(username);
		infoCon.usernameTxt.setText(username);
		
		String contact = "SELECT contact FROM world.scholar where username ='"+ getUsername()  +"';";
		try{
		Statement st = connectdb.createStatement();
		ResultSet rs = st.executeQuery(contact);
		
		infoCon.contactTxt.setText(rs.getString("contact"));
		}catch(Exception e) {
		}
		
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		Stage stage = (Stage) accountInfoBtn.getScene().getWindow();
		stage.setTitle("Account Details");
		stage.setScene(scene);
		stage.show();
	}
	public void borrowHistorybtnOnAction(ActionEvent action) throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Borrower/BorrowHistory.fxml"));
		Parent root = loader.load();
		
		BorrowerController bCon = new BorrowerController();
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();

		String getScholarId = "SELECT scholarId FROM scholar WHERE username ='" + username  +"'; ";
		try {
			Statement st = connectdb.createStatement();
			ResultSet rs = st.executeQuery(getScholarId);
			while(rs.next()) {
				bCon.setScholarId(rs.getString("scholarId"));
				bCon.setScholarId(rs.getString(0));
			}
		}catch(Exception e) {	
		}	
		
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		Stage stage = (Stage) borrowHistory.getScene().getWindow();
		stage.setTitle("Borrow History");
		stage.setScene(scene);
		stage.show();
	}
	public void logoutbtnOnAction(ActionEvent action) {
		Stage stage = (Stage) logoutbtn.getScene().getWindow();
		stage.close();
	}
}

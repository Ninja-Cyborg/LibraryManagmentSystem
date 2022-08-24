package librarian;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

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
	private Button issueBook;
	@FXML 
	Button addBookbtn;
	@FXML
	private Button ScholarDbbtn;
	@FXML
	private Button MenuLogoutbtn;
	@FXML 
	TextField txtuse;
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		
	}

	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public void issueBookbtnOnAction(ActionEvent event) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Book/IssueBook.fxml"));
		Parent root = loader.load();
		
		Scene scene2 = new Scene(root, 600, 400);
		scene2.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		Stage stage2 = (Stage) issueBook.getScene().getWindow();
		stage2.setTitle("Issue Book");
		stage2.setScene(scene2);
		stage2.show();
	}
	
	public void scholarDbbtnOnAction(ActionEvent event) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scholar/ScholarDb.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		Stage stage = (Stage) ScholarDbbtn.getScene().getWindow();
		//stage.close();
		stage.setTitle("Scholar List");
		stage.setScene(scene);
		stage.show();
	}
 
	public void MenuAddBookOnAction(ActionEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/book/AddBook.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		Stage stage = (Stage) addBookbtn.getScene().getWindow();
		stage.setTitle("Add Book Information");
		stage.setScene(scene);
		stage.show();
	}
	
	public void logoutbtnOnAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) MenuLogoutbtn.getScene().getWindow();
		stage.close();	
	}
}

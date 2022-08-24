package scholar;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ScholarInfoController implements Initializable {

	@FXML
	private TextField IdTxt;
	@FXML
	private TextField nameTxt;
	@FXML
	public TextField contactTxt;
	@FXML
	private TextField emailTxt;
	@FXML
	TextField usernameTxt;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private TextField genderTxt;
	@FXML
	private Button quit;
	private String username;
	
	ObservableList<Scholar> userList = FXCollections.observableArrayList();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
		
		String contact = "SELECT contact FROM world.scholar where username='" + getUsername() + "';";
		try {
			Statement st = connectdb.createStatement();
			ResultSet rs = st.executeQuery(contact);
			emailTxt.setText(rs.getString(0));
		}catch(Exception e){	
		}
		nameTxt.setText("iside");
	}
	
	public void quitOnAction(ActionEvent event) {
		Stage stage = (Stage) quit.getScene().getWindow();
		stage.close();
	}
	public void setIdTxt(TextField idTxt) {
		IdTxt = idTxt;
	}
	public void setNameTxt(TextField nameTxt) {
		this.nameTxt = nameTxt;
	}
	public void setContactTxt(TextField contactTxt) {
		this.contactTxt = contactTxt;
	}
	public void setEmailTxt(TextField emailTxt) {
		this.emailTxt = emailTxt;
	}
	public void setPasswordTxt(PasswordField passwordTxt) {
		this.passwordTxt = passwordTxt;
	}
	public void setGenderTxt(TextField genderTxt) {
		this.genderTxt = genderTxt;
	}
	public void setQuit(Button quit) {
		this.quit = quit;
	}
}

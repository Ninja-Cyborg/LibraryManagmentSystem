package scholar;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddScholarController implements Initializable {

	@FXML
	private TextField nameTxt;
	@FXML
	private TextField contactTxt;
	@FXML
	private TextField emailTxt;
	@FXML
	private TextField usernameTxt;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private PasswordField passConfirmTxt;
	@FXML
	private Button AddScholarbtn;
	@FXML
	private Label prompt;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void AddScholarBtnOnAction(ActionEvent event) {
		if(passwordTxt.getText().equals(passConfirmTxt.getText())) {
			registerUser();
			prompt.setText("User is registered");
		}
		else {
			prompt.setText("Passwords does not match!");
		}
	}
	
	public void registerUser() {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
	
		String name = nameTxt.getText();
		String contact = contactTxt.getText();
		String username = usernameTxt.getText();
		String password = passwordTxt.getText();
		String email = emailTxt.getText();
		
		double genrateId = 1+ (Math.random()*999999);
		String id = Double.toString(genrateId);
		
		String insertData = "INSERT INTO world.scholar(scholarid, name, contact, email,  username, password) VALUES ('";
		String insertValues = id+ "','" + name + "','" + contact + "','"+ email + "'" + username + "','" + password + "');";
		String insertToRegister = insertData + insertValues;
		
		try {
			Statement st = connectdb.createStatement();
			st.executeUpdate(insertToRegister);					
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}	
	}
}

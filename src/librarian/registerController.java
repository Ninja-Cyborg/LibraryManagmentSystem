package librarian;

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

public class registerController implements Initializable {


	@FXML
	private TextField fNametxt;
	@FXML
	private TextField lNametxt;
	@FXML
	private TextField idtxt;
	@FXML
	private TextField usernametxtr;
	@FXML
	private PasswordField passwordtxtr;
	@FXML
	private PasswordField passConfirmtxtr;
	@FXML
	private Button AddScholarbtn;
	@FXML
	private Label prompt;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}
	
	public void signUpbtnOnAction(ActionEvent event) {
		if(passwordtxtr.getText().equals(passConfirmtxtr.getText())) {
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
		String fName = fNametxt.getText();
		String lName = lNametxt.getText();
		String username = usernametxtr.getText();
		String password = passwordtxtr.getText();
		String id = idtxt.getText();
		
		String insertData = "INSERT INTO world.testData(id, first_name, last_name, username, password) VALUES ('";
		String insertValues = id+ "','" + fName + "','" + lName + "','"+ username + "','" + password + "');";
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

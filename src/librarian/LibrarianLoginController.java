package librarian;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LibrarianLoginController implements Initializable {
	
	@FXML
	private Button exitbtn;
	@FXML
	private Label logintxt;
	@FXML
	public TextField usernameTxt;
	@FXML
	public PasswordField passwordTxt;
	@FXML
	private Button loginbtn;

	@Override
	public void initialize(URL url, ResourceBundle res) {
		
	}
	
	public void logInbtnOnAction(ActionEvent event) throws IOException {
		
		if(usernameTxt.getText().isBlank()== false && passwordTxt.getText().isBlank() == false) {
				validateLogin();
			}
		else {
			logintxt.setText("Enter Both Credentials");
		}
	}
	
	public void exitbtnOnAction(ActionEvent event) {
		Stage stage = (Stage) exitbtn.getScene().getWindow();
		stage.close();
		
	}
	
	public void validateLogin() {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
		
		String verifylogin = "SELECT count(1) FROM librarian WHERE username ='" +usernameTxt.getText()+ "' AND password = '" + passwordTxt.getText()+ "' ";
		try {
			Statement st = connectdb.createStatement();
			ResultSet queryResult = st.executeQuery(verifylogin);
			
			while(queryResult.next()) {
				if(queryResult.getInt(1) == 1) {
					logintxt.setText("Valid");
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/librarian/MenuWindow.fxml"));
					Parent root = loader.load();
					String username = usernameTxt.getText();
					MenuController menuController = loader.getController();
					menuController.txtuse.setText(username);
					menuController.setUsername(username);
					
					Scene scene = new Scene(root, 600 ,400);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					Stage stage = (Stage) loginbtn.getScene().getWindow();
					stage.setTitle("Main Menu");
					stage.setScene(scene);
					stage.show();
				}
				else {
					logintxt.setText("Invalid");
				}
			}
		}catch(Exception e) {	
		}
	}
}


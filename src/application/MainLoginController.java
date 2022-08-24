package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// add button actions
public class MainLoginController implements Initializable {

	@FXML 
	private Button btnlgn1;
	@FXML
	private Button btnlgn2;
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		// TODO Auto-generated method stub
	}
	
	public void btnlgn1SetOnAction(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scholar/ScholarLoginWindow.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage) btnlgn1.getScene().getWindow();
		//stage.close();
		stage.setTitle("Scholar Login");
		stage.setScene(scene);
		stage.show();
	}
	
	public void btnlgn2SetOnAction(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/librarian/LibrarianloginWindow.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage) btnlgn2.getScene().getWindow();
		//stage.close();
		stage.setTitle("Librarian Login");
		stage.setScene(scene);
		stage.show();
	}
}

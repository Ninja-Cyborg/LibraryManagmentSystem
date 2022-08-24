package scholar;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ScholarDbController implements Initializable {

	@FXML
	private TableView<Scholar> scholarTable;
	@FXML
	private TableColumn<Scholar, String> scholarId;
	@FXML
	private TableColumn<Scholar, String> name;
	@FXML
	private TableColumn<Scholar, String> contact;
	@FXML
	private TableColumn<Scholar, String> email;
	@FXML
	private TableColumn<Scholar, String> username;
	@FXML
	private TableColumn<Scholar, String> password;
	@FXML
	private Button quit;
	
	ObservableList<Scholar> oList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		scholarId.setCellValueFactory(new PropertyValueFactory<>("scholarId"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		username.setCellValueFactory(new PropertyValueFactory<>("username"));
		password.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		showDb();
		scholarTable.setItems(oList);
	}
	
	public void showDb() {
		dbConnection connectNow = new dbConnection();
		Connection connectdb = connectNow.getConnection();
		
		String data = "SELECT * FROM world.scholar";
		try {
			Statement st = connectdb.createStatement();
			ResultSet rs = st.executeQuery(data);
			
			while(rs.next()) {
				oList.add(new Scholar(rs.getString("scholarId"),
						rs.getString("Name"),
						rs.getString("contact"),
						rs.getString("email"),
						rs.getString("username"),
						rs.getString("password")));
			}
		}catch(Exception e){	
		}
	}
	public void quitOnAction(ActionEvent event) {
		Stage stage = (Stage) quit.getScene().getWindow();
		stage.close();
	}	
}

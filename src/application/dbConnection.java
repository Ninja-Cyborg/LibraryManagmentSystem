package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {

	public Connection dbLink;
	public Connection getConnection() {
		
		String dbuser = "root";                  //mysql user name
		String dbpass = "root@unforget7";        //mysql password
		String url = "jdbc:mysql://localhost/world?autoReconnect=true&useSSL=false";		
		try {
			dbLink = DriverManager.getConnection(url, dbuser, dbpass);
		}
		catch(Exception e){
			e.printStackTrace();
			e.getCause();
		}
		return dbLink;
	}
}

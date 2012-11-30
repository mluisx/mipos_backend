package maurix.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {

	public static Connection openDatabase() throws ClassNotFoundException,IOException  {	
		Connection dbCon = null;
		try {	
		    Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://127.0.0.1/mi_pos?user=root&password=glendora";
			dbCon = DriverManager.getConnection(dbUrl);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dbCon;
	}
	
	public static void closeDatabase(Connection dbCon) {
		try {
			dbCon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}

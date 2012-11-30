package maurix.dao.impl;

import java.io.IOException;
import java.sql.*;

import maurix.dao.UsersDAO;

public class DefaultUserDAO implements UsersDAO {

	@Override
	public int getUserIdByUserNameAndPassword(String user, String pass) throws ClassNotFoundException, IOException,
	SQLException {
		String query = "select id from users where user = \"" + user + "\" and password = \"" + pass + "\"";
		Statement stmt;
		int id = 0;
		try {
			Connection dbCon = DatabaseDriver.openDatabase();
			stmt = dbCon.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				 id = rs.getInt(1);
			}
			DatabaseDriver.closeDatabase(dbCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
}

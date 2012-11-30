package maurix.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import maurix.Cash;
import maurix.dao.CashDAO;

public class DefaultCashDAO implements CashDAO {

	@Override
	public boolean postNewCash(Cash cash) throws ClassNotFoundException,
			IOException, SQLException {
		Connection dbCon = null;
		String query = "insert into cashes values (null,now()," + cash.getAmount() + ",DEFAULT)";
		Statement stmt;
		try {
			dbCon = DatabaseDriver.openDatabase();
			stmt = dbCon.createStatement();	
			int result = stmt.executeUpdate(query);
			DatabaseDriver.closeDatabase(dbCon);
			if (result==1) {				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}

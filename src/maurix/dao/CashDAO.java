package maurix.dao;

import java.io.IOException;
import java.sql.SQLException;

import maurix.Cash;

public interface CashDAO {

	boolean postNewCash(Cash cash) throws ClassNotFoundException, IOException, SQLException;
	
}

package maurix.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface UsersDAO {
	
	int getUserIdByUserNameAndPassword(String user, String pass) throws ClassNotFoundException, IOException, SQLException;
	
}

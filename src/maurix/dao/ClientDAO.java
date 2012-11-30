package maurix.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import maurix.Cash;
import maurix.Client;
import maurix.ClientHistory;

public interface ClientDAO {

	ArrayList<Client> searchForClients() throws ClassNotFoundException, IOException, SQLException;
	
	ArrayList<ClientHistory> searchForClientHistory(int clientId) throws ClassNotFoundException, IOException, SQLException;
	
}


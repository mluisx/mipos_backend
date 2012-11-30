package maurix.dao.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import maurix.Client;
import maurix.ClientHistory;
import maurix.dao.ClientDAO;

public class DefaultClientDAO implements ClientDAO {

	ArrayList<Client> clientsList = new ArrayList<Client>();
	ArrayList<ClientHistory> clientHistoryList = new ArrayList<ClientHistory>();
	
	@Override
	public ArrayList<Client> searchForClients() throws ClassNotFoundException,
			IOException, SQLException {
		String query = "select * from clients";
		Statement stmt;
		try {
			Connection dbCon = DatabaseDriver.openDatabase();
			stmt = dbCon.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setBalance(new BigDecimal(rs.getDouble("balance")));
				clientsList.add(client);
			}
			DatabaseDriver.closeDatabase(dbCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientsList;
	}
	
	@Override
	public ArrayList<ClientHistory> searchForClientHistory(int clientId) throws ClassNotFoundException,
			IOException, SQLException {
		String query = "select * from client_history where client_id = " + clientId + " order by id desc";
		Statement stmt;
		try {
			Connection dbCon = DatabaseDriver.openDatabase();
			stmt = dbCon.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ClientHistory clientHistory = new ClientHistory();
				clientHistory.setClientId(clientId);
				clientHistory.setSaleId(rs.getInt("sale_id"));
				clientHistoryList.add(clientHistory);
			}
			DatabaseDriver.closeDatabase(dbCon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientHistoryList;
	}
}

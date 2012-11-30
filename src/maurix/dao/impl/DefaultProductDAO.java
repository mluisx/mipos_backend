package maurix.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import maurix.StockNewProduct;
import maurix.dao.NewProductDAO;

public class DefaultProductDAO implements NewProductDAO {

	@Override
	public boolean postNewProduct(StockNewProduct newProduct) throws ClassNotFoundException, IOException, SQLException {
		InputStream stream = null;
		Connection dbCon = null;
		String query = "insert into products values (null,\"" + newProduct.getCode() + "\",\"" + newProduct.getDescription()
				+ "\"," + newProduct.getPrice() + "," + newProduct.getQuantity() + ",\"" + newProduct.getCategory() +
				"\",?)";
//		Statement stmt;
		if (newProduct.getPictureData()!=null) {
			stream = new ByteArrayInputStream(newProduct.getPictureData());
		}
		
		try {
			dbCon = DatabaseDriver.openDatabase();
			PreparedStatement stmt = dbCon.prepareStatement(query);
//			stmt.setBlob(1, stream);
			stmt.setBinaryStream(1, stream, (int) newProduct.getPictureData().length);
//			System.out.println("Stream: " + stream.toString());
//			stmt.setBinaryStream(1, stream);
//			stmt = dbCon.createStatement();	
			stmt.execute();
			stream.close();
			stmt.close();
//			int result = stmt.executeUpdate(query);
//			if (result==1) {
//				DatabaseDriver.closeDatabase(dbCon);
//				return true;
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseDriver.closeDatabase(dbCon);
		return true;
	}

}

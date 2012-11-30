package maurix.dao;

import java.io.IOException;
import java.sql.SQLException;

import maurix.*;

public interface NewProductDAO {

	boolean postNewProduct(StockNewProduct newProduct) throws ClassNotFoundException, IOException, SQLException;
	
}

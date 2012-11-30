package maurix;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import maurix.dao.impl.DefaultCashDAO;
import maurix.dao.impl.DefaultClientDAO;
import maurix.dao.impl.DefaultProductDAO;
import maurix.dao.impl.DefaultUserDAO;

// POJO, no interface no extends

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {
  
  private static final String USER_ID = "userId";
  private static final String PASSWORD = "password";
  private static final String CLIENT_ID = "clientId";

  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello(@QueryParam(USER_ID) String user, @QueryParam(PASSWORD) String pass) {
	DefaultUserDAO  userDao = new DefaultUserDAO();
	int id = 0;
	try {
		id = userDao.getUserIdByUserNameAndPassword(user, pass);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return "User Id: " + id;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public LogIn sayJsonHello(@QueryParam(USER_ID) String user, @QueryParam(PASSWORD) String pass) {
	DefaultUserDAO  userDao = new DefaultUserDAO();
	int id = 0;
	try {
		id = userDao.getUserIdByUserNameAndPassword(user, pass);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	LogIn logInJsonObj = new LogIn();
	logInJsonObj.setUser(user);
	logInJsonObj.setId(id);
	if (id!=0) {
		logInJsonObj.setSuccessful(true);
	} else {
		logInJsonObj.setSuccessful(false);
	}
	return logInJsonObj;
  }

  // This method is called if XML is request
  @GET
  @Produces(MediaType.TEXT_XML)
  public String sayXMLHello() {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
  }

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }

  @POST
  @Path("/post")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createNewProduct(StockNewProduct newProduct) {
	DefaultProductDAO  productDao = new DefaultProductDAO();
	String result = null;
	try {
		result = "Track saved : " + newProduct.getCode() + newProduct.getDescription();
		if (productDao.postNewProduct(newProduct)) {
			return Response.status(201).entity(result).build();
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Response.status(500).entity(result).build();
	}
  
  @POST
  @Path("/cash-open")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response openNewCash(Cash cash) {
	DefaultCashDAO cashDao = new DefaultCashDAO();
	String result = null;
	try {
		result = "Cash Created";
		if (cashDao.postNewCash(cash)) {
			return Response.status(201).entity(result).build();
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Response.status(500).entity(result).build();
  }

  // This method is called if TEXT_PLAIN is request
  @GET
  @Path("/clients")
  @Produces(MediaType.APPLICATION_JSON)
  public ClientsList gettingClientsList() {
	  DefaultClientDAO clientDao = new DefaultClientDAO();
	  ArrayList<Client> clientsList = null;
	  try {
		  clientsList = clientDao.searchForClients();
	  } catch (ClassNotFoundException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  } catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  } catch (SQLException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  ClientsList clientsListJsonObj = new ClientsList();
	  clientsListJsonObj.setClientsList(clientsList);
	  return clientsListJsonObj;
  }
  
  // This method is called if TEXT_PLAIN is request
  @GET
  @Path("/client-history")
  @Produces(MediaType.APPLICATION_JSON)
  public ClientHistoryList gettingClientsList(@QueryParam(CLIENT_ID) int clientId) {
	  DefaultClientDAO clientDao = new DefaultClientDAO();
	  ArrayList<ClientHistory> clientHistoryList = null;
	  try {
		  clientHistoryList = clientDao.searchForClientHistory(clientId);
	  } catch (ClassNotFoundException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  } catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  } catch (SQLException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  ClientHistoryList clientHistoryListJsonObj = new ClientHistoryList();
	  clientHistoryListJsonObj.setClientHistoryList(clientHistoryList);
	  return clientHistoryListJsonObj;
  }
}

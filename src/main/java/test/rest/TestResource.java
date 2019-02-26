package test.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/test")
public class TestResource {

	@GET
	@Path("/prueba1.rest")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> prueba1(){
		HashMap<String, String> res = new HashMap<String,String>();
		
		String dbUrl = "jdbc:postgresql://";
		dbUrl += System.getenv("POSTGRESQL_SERVICE_HOST");
		dbUrl += "/"+System.getenv("POSTGRESQL_DATABASE");
		
		String username = System.getenv("POSTGRESQL_USER");
		String password = System.getenv("POSTGRESQL_PASSWORD");
		
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;
		
		try {
			connection = DriverManager.getConnection(dbUrl,username,password);
			if(connection != null ) {
				s = connection.createStatement();
				r = s.executeQuery("SELECT string FROM prueba1");
				
				while(r.next()) {
					String v = r.getString("string");
					
					res.put(v, v);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			res.put("ERROR", e.getMessage());
		}finally {
			if(r != null) {
				try {
					r.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return res;
	}
}

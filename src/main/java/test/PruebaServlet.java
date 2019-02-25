package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PruebaServlet
 */
@WebServlet("/db")
public class PruebaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
				r = s.executeQuery("SELECT serial,string FROM prueba1");
				
				while(r.next()) {
					String v = r.getString("string");
					
					response.getWriter().append(" <br \\>" + v);
				}
			}
			
		} catch (SQLException e) {
			response.getWriter().append(e.getMessage());
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

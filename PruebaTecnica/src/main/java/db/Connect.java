package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class Connect {
	
	
	
	private String driver = "com.mysql.cj.jdbc.Driver";
    private String host = "localhost";
    private int port = 3306;
    private String dbname = "prueba_tecnica";
    private String username = "root";
    private String password = "The_best_pass123";
    private String url = "jdbc:mysql://"+host+":"+port+"/"+dbname;
    
    private Connection con;
    
    public Connect() throws ClassNotFoundException, SQLException {
    	Class.forName(driver);
    	con = DriverManager.getConnection(url+"?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false", username, password);
    }
    
    public Connection getConnection() {
    	return con;
    }
    
    public void close() {
    	con = null;
    }

	public static void main(String[] args) {
		
		try {
			Connect connection = new Connect();
			PreparedStatement stmt = connection.getConnection().prepareStatement("select * from user");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("email"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}

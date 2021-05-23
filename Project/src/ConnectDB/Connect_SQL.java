package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect_SQL{
	
	static Connection conn = null; 
	//static String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Social_Network";
	static String dbURL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    static String user = "sa";
    static String pass = "Password123@jkl#";
    
    private static Connection getConnection(String dbURL, String user, String pass) {
    	try {
    		
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            conn = DriverManager.getConnection(dbURL);
    		
    	} catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	return conn; 
    }
    public static Connection getConnection() {
    	if (conn != null) 
    		return conn; 
    	return getConnection(dbURL, user, pass); 
    }
    
	public boolean connectDB() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		
    		conn.close();
    		return true;
    		
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
}
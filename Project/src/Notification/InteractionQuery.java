package Notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import AccountManagement.Account;
import Blog.Blogs;
import Status.BlogStatus;

public class InteractionQuery {
	ArrayList<Interaction> notice = new ArrayList<Interaction>();
	
	public InteractionQuery() {}
	
	public ArrayList<Interaction> getNotice() {
		return notice;
	}

	public void setNotice(ArrayList<Interaction> notice) {
		this.notice = notice;
	}

	public void getAllNoticeById(int id) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
			
//			String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Social_Network";
//			String user = "sa";
//			String pass = "Password123@jkl#";
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//	        Connection conn = DriverManager.getConnection(dbURL, user, pass);
			
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From Interaction Where blogOwnerId = \'" + id + "\'";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
					Interaction n = new Interaction(r.getInt("InteractionId"), r.getInt("visitorID"), r.getString("visitorUsername"),
							r.getInt("blogOwnerId"), r.getString("blogOwnerUsername"),r.getInt("blogId"),
							r.getString("dateInteract"),r.getString("notice"));
					this.notice.add(n);
			}
			r.close();
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
}

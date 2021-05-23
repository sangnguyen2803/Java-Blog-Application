package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import HashPw.BCrypt;

public class UserManager {
	private ArrayList<Users> users = new ArrayList<Users>();
	
	
	public boolean check_exist(String username) {
		this.GetAllUser();
		for(int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).getUsername().equals(username)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void GetAllUser() {
		this.users.clear();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From User_Social";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				String firstname = r.getString("First_Name");
		    	String lastname = r.getString("Last_Name");
		   		String username = r.getString("Username");
		   		String password = r.getString("Passwork");
		   		String date_of_birth = r.getString("Date_of_birth");
		   		String adress = r.getString("Adress");
	    		String email = r.getString("Email");
	    		String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
	    		Users u = new Users(username, hashpw, firstname, lastname, date_of_birth, adress, email, r.getInt("UserID"));
	    		this.users.add(u);
	    		
			}
			r.close();
    		
    		
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}
	
	public boolean Insert(Users u){
		if(!this.check_exist(u.getUsername())) {
			System.out.print("TTTT");
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
	    		Connection conn = DriverManager.getConnection(DB_URL);
	    		Statement stmt = conn.createStatement();
	    	
	    		String insert = "Insert Into User_Social Values('" + u.getFirst_Name() + "','"
	    				+ u.getLast_Name() + "','" + u.getUsername() + "','" + u.getPassword() + "','" + u.getDate_Of_Birth() + "','" + 
	    				u.getAdress() + "','" + u.getEmail() + "')";
	    		stmt.executeUpdate(insert);
	    		conn.commit();	
			}catch(ClassNotFoundException ex)
			{
				System.out.println("Error: unable to load driver class.");
	    		System.exit(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return false;
	}
	
	public boolean Update(Users u){
		if(this.check_exist(u.getUsername())) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
	    		Connection conn = DriverManager.getConnection(DB_URL);
	    		Statement stmt = conn.createStatement();
	    	
	    		String update = "Update User_Social Set First_Name = '" + u.First_Name + "', Last_Name = '" + u.Last_Name + "', Date_of_birth = '" + u.Date_Of_Birth 
	    				+ "', Adress = '" + u.Adress + "', Email = '" + u.Email + "', Passwork = '" + u.Password + "' Where Username = '" + u.Username +"'";
	    		stmt.executeUpdate(update);
	    		conn.commit();	
			}catch(ClassNotFoundException ex)
			{
				System.out.println("Error: unable to load driver class.");
	    		System.exit(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return false;
	}
	
	public boolean Delete(Users u){
		if(this.check_exist(u.getUsername())) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
	    		Connection conn = DriverManager.getConnection(DB_URL);
	    		Statement stmt = conn.createStatement();
	    	
	    		String delete = "Delete From User_Social Where Username = '" + u.Username +"'";
	    		stmt.executeUpdate(delete);
	    		conn.commit();	
			}catch(ClassNotFoundException ex)
			{
				System.out.println("Error: unable to load driver class.");
	    		System.exit(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return false;
	}

}

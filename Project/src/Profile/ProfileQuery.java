package Profile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import AccountManagement.Account; // store user information
import ConnectDB.Connect_SQL;

public class ProfileQuery {
	ArrayList<Account> account = new ArrayList<Account>();
	
	public ProfileQuery() {}
	
	public ArrayList<Account> getAccountList() {
		return account;
	}

	public void setAccountList(ArrayList<Account> account) {
		this.account = account;
	}
	public Account getAllAccount(String id) {
		this.account.clear();
		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
//			Connection conn = DriverManager.getConnection(DB_URL);
			
			String dbURL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
			String user = "sa";
			String pass = "Password123@jkl#";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
	        Connection conn = DriverManager.getConnection(dbURL, user, pass);
			
			Statement stmt = conn.createStatement();
			String getAllAccount = "Select * From User_Social Where UserId = " + id;
			ResultSet result = stmt.executeQuery(getAllAccount);
			while(result.next()) {
				Account a = new Account(result.getInt("UserID"),result.getString("Username"), result.getString("Passwork"), result.getString("First_Name"), result.getString("Last_Name"), result.getString("Date_of_birth"), result.getString("Adress"), result.getString("Email"));
				return a;
			}
			result.close();
		}catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class.");
			System.exit(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	public boolean updateAccount(Account accountDto) {
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			Statement stmt = conn.createStatement();
			String password = accountDto.getPassword();
			System.out.println(accountDto.getId());
			String queryStatement = "Update User_Social "
								+ "Set  First_Name = '" + accountDto.getFirstName() + "', "
										+ "Last_Name = '" + accountDto.getLastName() + "', "
										+ "Username = '" + accountDto.getUsername() + "', "
										+ "Date_of_birth = '" + accountDto.getDateOfBirth() + "', "
										+ "Email = '" + accountDto.getEmail() + "', "
										+ "Adress = '" + accountDto.getAddress() + "' "
								+ "Where UserID = '" + accountDto.getId() + "'";
			int rowChange = stmt.executeUpdate(queryStatement);
			System.out.println(rowChange);
			if (rowChange != 1)
				return false;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}
}
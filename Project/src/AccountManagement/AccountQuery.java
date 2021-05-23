package AccountManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.Connect_SQL;



public class AccountQuery{
	ArrayList<Account> account = new ArrayList<Account>();
	
	public AccountQuery() {}
	
	public ArrayList<Account> getAccountList() {
		return account;
	}

	public void setAccountList(ArrayList<Account> account) {
		this.account = account;
	}
	public void getAllAccount() {
		this.account.clear();
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			Statement stmt = conn.createStatement();
			String getAllAccount = "Select * From User_Social";
			ResultSet result = stmt.executeQuery(getAllAccount);
			while(result.next()) {
				Account a = new Account(result.getInt("UserID"),result.getString("Username"), result.getString("Passwork"), result.getString("First_Name"), result.getString("Last_Name"), result.getString("Date_of_birth"), result.getString("Adress"), result.getString("Email"));
				this.account.add(a);
			}
			result.close();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public Account getAllAccount(String id) {
		this.account.clear();
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			Statement stmt = conn.createStatement();
			String getAllAccount = "Select * From User_Social Where UserId = " + id;
			ResultSet result = stmt.executeQuery(getAllAccount);
			while(result.next()) {
				Account a = new Account(result.getInt("UserID"),result.getString("Username"), result.getString("Passwork"), result.getString("First_Name"), result.getString("Last_Name"), result.getString("Date_of_birth"), result.getString("Adress"), result.getString("Email"));
				return a;
			}
			result.close();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	

	public void findAccountByIdAndUsername(String id, String username) {
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			Statement stmt = conn.createStatement();
			String queryStatement = "";
			if (username.equals("") && !id.equals("")) {
				queryStatement = "Select * From User_Social Where UserId = " + id;
			}
			else if (id.equals("") && !username.equals("")) {
				queryStatement = "Select * From User_Social Where Username = \'" + username + "\'" ;
			}
			else {
				queryStatement = "Select * From User_Social Where UserId = " + id + " AND Username = \'" + username + "\'";
			}
			ResultSet result = stmt.executeQuery(queryStatement);
			while(result.next()) {
				Account a = new Account(result.getInt("UserID"),result.getString("Username"), result.getString("Passwork"), result.getString("First_Name"), result.getString("Last_Name"), result.getString("Date_of_birth"), result.getString("Adress"), result.getString("Email"));
				this.account.add(a);
			}
			result.close();
		}catch (SQLException e1) {
			System.out.println("SQL Problems");
			e1.printStackTrace();
		}
	}

	public boolean addAccount(Account accountDto) {
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			Statement stmt = conn.createStatement();
			String password = accountDto.getPassword();
			String queryStatement = "Insert into User_Social (First_Name, Last_Name, Username, Passwork, Date_of_birth, Adress, Email) "
					+ "				values ('" + accountDto.getFirstName()
									+ "', '" + accountDto.getLastName() 
									+ "', '" + accountDto.getUsername() 
									+ "', '" + password + "', '" 
									+ accountDto.getDateOfBirth() 
									+ "', '" + accountDto.getAddress() 
									+ "', '" + accountDto.getEmail() + "');";
			int rowChange = stmt.executeUpdate(queryStatement);
			if (rowChange != 1)
				return false;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;	
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
										+ "Passwork = '" + accountDto.getPassword() + "', "
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

	public int removeById(String removeId) {
		int rows = 0;
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			PreparedStatement stmt = null;
			String queryStatement = "DELETE FROM User_Social WHERE UserId = " + removeId;
			stmt = conn.prepareStatement(queryStatement);
			rows = stmt.executeUpdate();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("8");
		return rows;
		
	}
	
	public void removeInteractionByUserId(String id) {
		
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			PreparedStatement stmt = null;
			String queryStatement = "DELETE FROM Interaction WHERE visitorId = " + id;
			stmt = conn.prepareStatement(queryStatement);
			stmt.executeUpdate();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("1");
	}

	public void removeLikeByUserId(String removeId) {
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			PreparedStatement stmt = null;
			String queryStatement = "DELETE FROM LikeBlog WHERE UsernameID = " + removeId;
			stmt = conn.prepareStatement(queryStatement);
			stmt.executeUpdate();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("2");
	}
	
	public void removeCommentByUserId(String removeId) {
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			PreparedStatement stmt = null;
			String queryStatement = "DELETE FROM Comment WHERE UsernameIDCmt = " + removeId;
			stmt = conn.prepareStatement(queryStatement);
			stmt.executeUpdate();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("3");
	}
	
	public ArrayList<String> getAllBlogId(String removeId) {
		ArrayList<String> blogList = new ArrayList<String>();
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			Statement stmt = conn.createStatement();
			String queryStatement = "SELECT * FROM Blog WHERE UsernameId = " + removeId;
	        ResultSet rs = stmt.executeQuery(queryStatement);
	        while(rs.next()){
	        	blogList.add(rs.getString("BlogID"));
	        }
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("4");
		return blogList;
	}
	
	public void removeLikeByBlogId(String blogId) {
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			PreparedStatement stmt = null;
			String queryStatement = "DELETE FROM LikeBlog WHERE BlogID = " + blogId;
			stmt = conn.prepareStatement(queryStatement);
			stmt.executeUpdate();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("5");
	}
	
	public void removeCommentByBlogId(String blogId) {
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			PreparedStatement stmt = null;
			String queryStatement = "DELETE FROM Comment WHERE BlogID = " + blogId;
			stmt = conn.prepareStatement(queryStatement);
			stmt.executeUpdate();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("6");
	}
	
	public void removeBlogByUserId(String removeId) {
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			PreparedStatement stmt = null;
			String queryStatement = "DELETE FROM Blog WHERE UsernameID = " + removeId;
			stmt = conn.prepareStatement(queryStatement);
			stmt.executeUpdate();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("7");
	}

}
package User;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.LocalDate;

import Blog.*;
import Interactive.*;
import LoginDesign.FrameLogin;
import Notification.Interaction;
import Query.QuerySQL;
import HashPw.BCrypt;
import Status.*;
import ConnectDB.Connect_SQL;

public class Users{

    protected String Username;
    protected String Password;
	protected String First_Name;
    protected String Last_Name;
    protected String Date_Of_Birth;
    protected String Adress;
    protected String Email;
    ArrayList<Blogs> blog = new ArrayList<>();
    protected ArrayList<Interactives> cmt = new ArrayList<Interactives>();
    protected int UserID;
    
    String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Social_Network";
    String user = "sa";
    String pass = "Password123@jkl#";
    
    public Users() {
    	
    }
    
    public Users(String username, String password, String first_Name, String last_Name, String date_Of_Birth,
			String adress, String email) {
		super();
		Username = username;
		Password = password;
		First_Name = first_Name;
		Last_Name = last_Name;
		Date_Of_Birth = date_Of_Birth;
		Adress = adress;
		Email = email;
	}



	public String getUsername() {
		return Username;
	}



	public void setUsername(String username) {
		Username = username;
	}



	public String getPassword() {
		return Password;
	}



	public void setPassword(String password) {
		Password = password;
	}



	public String getFirst_Name() {
		return First_Name;
	}



	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}



	public String getLast_Name() {
		return Last_Name;
	}



	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}



	public String getDate_Of_Birth() {
		return Date_Of_Birth;
	}



	public void setDate_Of_Birth(String date_Of_Birth) {
		Date_Of_Birth = date_Of_Birth;
	}



	public String getAdress() {
		return Adress;
	}



	public void setAdress(String adress) {
		Adress = adress;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}

	

    public ArrayList<Blogs> getBlog() {
		return blog;
	}

	public void setBlog(ArrayList<Blogs> blog) {
		this.blog = blog;
	}

	public Users(Users a){
        this.Username = a.Username;
        this.Password = a.Password;
        this.First_Name = a.First_Name;
        this.Last_Name = a.Last_Name;
        this.Date_Of_Birth = a.Date_Of_Birth;
        this.Adress = a.Adress;
        this.Email = a.Email;
        this.UserID = a.UserID;
    }



    // Method 

    public boolean Login(String username, String password){
    	try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
//    		Connection conn = DriverManager.getConnection(DB_URL);
 		
//    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//            Connection conn = DriverManager.getConnection(dbURL, user, pass);
    		
    		Connection conn = Connect_SQL.getConnection(); 
    		
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From User_Social";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getString("Username").equals(username) && BCrypt.checkpw(password, r.getString("Passwork"))) {
					this.UserID = r.getInt("UserID");
					this.Username = r.getString("Username");
					this.Password = r.getString("Passwork");
					this.First_Name = r.getString("First_Name");
					this.Last_Name = r.getString("Last_Name");
					this.Date_Of_Birth = r.getString("Date_of_birth");
					this.Adress = r.getString("Adress");
					this.Email = r.getString("Email");
					return true;
				}
			}
			r.close();
    		
    		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return false;
    }

    public void Logout(){
    	this.UserID = 0;
    	this.Username = "";
    	this.Password = "";
    	this.First_Name = "";
    	this.Last_Name = "";
    	this.Date_Of_Birth = "";
    	this.Adress ="";
    	this.Email = "";
    	this.blog.clear();
    	this.cmt.clear();
    }

    public void SignUp(){
    	try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    		String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Social_Network;integratedSecurity=true;";
//    		Connection conn = DriverManager.getConnection(DB_URL);
    		
//    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//            Connection conn = DriverManager.getConnection(dbURL, user, pass);
    		
    		Connection conn = Connect_SQL.getConnection(); 
    		
    		Statement stmt = conn.createStatement();
    	
    		String insert = "Insert Into User_Social Values('" + this.getFirst_Name() + "','"
    				+ this.getLast_Name() + "','" + this.getUsername() + "','" + this.getPassword() + "','" + this.getDate_Of_Birth() + "','" + 
    				this.getAdress() + "','" + this.getEmail() + "')";
    		stmt.executeUpdate(insert);
    		conn.commit();
 
    		
    		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    

    public Users(String username, String password, String first_Name, String last_Name, String date_Of_Birth,
			String adress, String email, int userID) {
		super();
		Username = username;
		Password = password;
		First_Name = first_Name;
		Last_Name = last_Name;
		Date_Of_Birth = date_Of_Birth;
		Adress = adress;
		Email = email;
		UserID = userID;
	}

	public boolean Validation(){
        return true;
    }

    public void getAllBlog() {
    	this.blog.clear();
    	try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";    		
//    		Connection conn = DriverManager.getConnection(DB_URL);
    		
//    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//            Connection conn = DriverManager.getConnection(dbURL, user, pass);
    		
    		Connection conn = Connect_SQL.getConnection(); 
//    		
    		String sql = "SELECT * FROM Blog WHERE DeleteBlog = 0"; 
    		PreparedStatement ps = conn.prepareStatement(sql); 
			ResultSet r = ps.executeQuery(); 
			while(r.next()) {
				/*if(r.getString("Username").equals(this.Username))
				{
					BlogStatus stt = new BlogStatus();
					stt.set(r.getBoolean("Edit"));
					Blogs b = new Blogs(r.getString("Title"),r.getInt("BlogID"), r.getString("Username"),
							r.getString("Body"), r.getBoolean("CommentEnabled"),r.getBoolean("DeleteBlog"),
							r.getString("Date_of_blog"),r.getBoolean("Edit"), stt);
					b.getAllCmnt();
					this.blog.add(b);
				} */ 
				
				BlogStatus stt = new BlogStatus();
				stt.set(r.getBoolean("Edit"));
				Blogs b = new Blogs(r.getString("Title"),r.getInt("BlogID"), r.getString("Username"),
						r.getString("Body"), r.getBoolean("CommentEnabled"),r.getBoolean("DeleteBlog"),
						r.getString("Date_of_blog"),r.getBoolean("Edit"), stt);
				// b.getAllCmnt();
				this.blog.add(b);
			}
			
			// sort by date
			sortBlogByDate(this.blog); 
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void sortBlogByDate(ArrayList<Blogs> listBlog) {
    	// ArrayList<Blogs> sorted = new ArrayList<>(); 
    	for (int i = 0; i < listBlog.size() - 1; i++) {
    		for (int j = i; j < listBlog.size(); j++) {
    			LocalDate date1 = LocalDate.parse(listBlog.get(i).get_Date()); 
    			LocalDate date2 = LocalDate.parse(listBlog.get(j).get_Date());
    			if (date1.compareTo(date2) < 0) { // less than
    				Blogs temp = listBlog.get(i); 
    				listBlog.set(i, listBlog.get(j)); 
    				listBlog.set(j, temp); 
    			}
    		}
    	}
    	
    	
    	// return sorted; 
    }
    
    public void getAllCmt() {
    	this.cmt.clear();
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From Comment";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getString("Username").equals(this.Username))
				{
					Interactives inter = new Interactives(r.getString("Username"), r.getInt("BlogID"), r.getInt("CommentID"),
							r.getString("Body"),r.getBoolean("Deletecmt"), r.getBoolean("Edit"), r.getString("Date_of_Comment"),
							r.getString("UsernameIDBlog"));
					this.cmt.add(inter);
				}
				
				
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
    
    
    
    public boolean PostBlog(Blogs b){
    	try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
//    		Connection conn = DriverManager.getConnection(DB_URL);
    		
//    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//            Connection conn = DriverManager.getConnection(dbURL, user, pass);
    		
    		Connection conn = Connect_SQL.getConnection(); 
    		
    		Statement stmt = conn.createStatement();
    		int Hitcmt = 0;
    		int HitLike = 0;
    		System.out.print(this.UserID + " "  + b.get_Username());
    		String insert = "Insert Into Blog Values('" + this.UserID + "','" + b.get_Username() + "','" + b.get_Title() + "','" + 
    				b.get_Body() + "','" + b.get_CommentEnabled() + "','" + b.get_DeleteBlog() + "','" + b.get_Edit()
    				+ "','" + b.get_Date() + "','" + Hitcmt + "','" + HitLike + "')";
    		stmt.executeUpdate(insert);
    		conn.commit();
    		
    		String select = "Select * From Blog Where Username = '" + b.get_Username() +"'";
    		ResultSet r = stmt.executeQuery(select);
    		int blogID = 0;
    		boolean stt = true;
			while(r.next()) {
				
				if(r.getInt("BlogID") > blogID) {
					blogID = r.getInt("BlogID");
					stt = r.getBoolean("Edit");
				}
				
			}
			System.out.print(blogID);
			
			int status = 1;
			if(!stt) {
				status = 0;
			}
			
			String insertstatus = "Insert Into BlogStatus Values('" + b.get_Username() + "'," + blogID
    				+ ","  + status + ")";
			stmt.executeUpdate(insertstatus);
    		conn.commit();
			
    		return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	return false;
    }

    public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public void ViewsBlog(){
    	
    }

    public boolean PostCmt(Interactives inter, Interaction notice){
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		int delete = 1;
    		int edit = 1;
    		if(!inter.get_DeleteCmt()) {
    			delete = 0;
    		}
    		if(!inter.get_EditCmt()) {
    			edit = 0;
    		}
    		
    		if(inter.get_EditCmt()) {
    			String insert = "Insert Into Comment Values(" + this.UserID + ",'" + inter.get_Username()+ "','" +
    		    		inter.getUsernameBlog() + "'," + inter.get_BlogID() + ",'" + inter.get_Body() + "'," + delete
    		    		 + "," + edit + ",'" + inter.get_Date() + "','" + "" + "')";
    			String noti = notice.getVisitor() + " has comment on blog of " + notice.getUsername() ;
    			notice.setNotice(noti);
    			String insert_notice = "Insert Into Interaction Values(" + notice.getVisitorId() + ",'" +
    			notice.getVisitor() + "'," + notice.getUsernameId() + ",'" + notice.getUsername() + "'," + notice.getBlogId()
    			+ ",'"+notice.getDate()+"','" + notice.getNotice() + "')";
    		    stmt.executeUpdate(insert);
    		    stmt.executeUpdate(insert_notice);
    		    conn.commit();
    		    return true;
    		}
    		
    		
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
    
    public void Comment(Blogs blog, String body, String string, Interaction notice) {
    	try {
//    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//            Connection conn = DriverManager.getConnection(dbURL, user, pass);
    		Connection conn = Connect_SQL.getConnection(); 
            Statement stmt = conn.createStatement();
            
            int deleteCmt = 0; 
            int editCmt = 0; 
            
            String sql = "Insert into Comment Values(" + this.UserID + ", '" + this.Username + "', '" + blog.get_Username() + 
            		"', " + blog.get_BlogID() + ", '" + body + "', " + deleteCmt + ", " + editCmt + ", '" + String.valueOf(string) + 
            		"', '" + blog.get_Title() + "')"; 
            
            String noti = notice.getVisitor() + " has comment on blog of " + notice.getUsername() ;
			notice.setNotice(noti);
			String insert_notice = "Insert Into Interaction Values(" + notice.getVisitorId() + ",'" +
			notice.getVisitor() + "'," + notice.getUsernameId() + ",'" + notice.getUsername() + "'," + notice.getBlogId()
			+ ",'"+notice.getDate()+"','" + notice.getNotice() + "')";
			
            stmt.executeUpdate(sql);
            stmt.executeUpdate(insert_notice);
    		conn.commit();
            
    	} catch(SQLException e) {
    		// System.out.println("SQL error"); 
    		e.printStackTrace();
    	}
    }

    public void EditComment(){

    }

    public void DeleteComment(){

    }

    public void LikeBlog(int blogID, String usernameBlog, Interaction notice){
    	try {    		
    		Connection conn = Connect_SQL.getConnection(); 
    		Statement stmt = conn.createStatement();
    		
    		String select = "Select * From LikeBlog";
    		ResultSet r = stmt.executeQuery(select);
    		boolean check = false;
    		boolean stt = false;
    		while(r.next()) {
				
				if(r.getInt("UsernameID") == this.UserID && r.getInt("BlogID") == blogID) {
					check = true;
					stt = r.getBoolean("StatusLike");
					break;
				}
				
			}
    		
    		if(check) {
    			if(stt) {
    				String update = "Update LikeBlog Set StatusLike = " + 0 + " Where UsernameID = " + this.UserID;
    				stmt.executeUpdate(update);
    				
    				String noti = notice.getVisitor() + " has not like the blog of " + notice.getUsername() ;
        			notice.setNotice(noti);
        			String insert_notice = "Insert Into Interaction Values(" + notice.getVisitorId() + ",'" +
        			notice.getVisitor() + "'," + notice.getUsernameId() + ",'" + notice.getUsername() + "'," + notice.getBlogId()
        			+ ",'"+notice.getDate()+"','" + notice.getNotice() + "')";
        			stmt.executeUpdate(insert_notice);
    			}
    			else {
    				String update = "Update LikeBlog Set StatusLike =" + 1 + " Where UsernameID = " + this.UserID;
    				stmt.executeUpdate(update);
    				String noti = notice.getVisitor() + " has like the blog of " + notice.getUsername() ;
        			notice.setNotice(noti);
        			String insert_notice = "Insert Into Interaction Values(" + notice.getVisitorId() + ",'" +
        			notice.getVisitor() + "'," + notice.getUsernameId() + ",'" + notice.getUsername() + "'," + notice.getBlogId()
        			+ ",'"+notice.getDate()+"','" + notice.getNotice() + "')";
        			stmt.executeUpdate(insert_notice);
    			}
    		}
    		else {
    			String insert = "Insert Into LikeBlog Values(" + this.UserID + ",'" + this.Username + "',"
    					+ 1 + ",'" + usernameBlog + "'," + blogID + ")";
    			stmt.executeUpdate(insert);
    			String noti = notice.getVisitor() + "has like the blog of " + notice.getUsername() ;
    			notice.setNotice(noti);
    			String insert_notice = "Insert Into Interaction Values(" + notice.getVisitorId() + ",'" +
    			notice.getVisitor() + "'," + notice.getUsernameId() + ",'" + notice.getUsername() + "'," + notice.getBlogId()
    			+ ",'"+notice.getDate()+"','" + notice.getNotice() + "')";
    			stmt.executeUpdate(insert_notice);
    		}
    		
    		conn.commit();
    
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   
    }	

    public boolean DeleteBlog(int BlogID){
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Blog";
    		ResultSet r = stmt.executeQuery(select);
    		boolean Delete = true;
    		while(r.next()) {
				
				if(r.getInt("BlogID") ==  BlogID && this.Username.equals(r.getString("Username"))) {
					Delete = r.getBoolean("DeleteBlog");
				}
				
			}
    		
    		if(Delete){
    			QuerySQL qr = new QuerySQL();
    			if(qr.ClearCmtAndLikeOfBlog(BlogID)) {
    				String delete = "Delete From Blog Where BlogID = " + BlogID;
        			stmt.executeUpdate(delete);
        			conn.commit();
        			return true;
    			}
    			else {
    				return false;
    			}
    			
    		}
    		
    		
    		
    	
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
    
    public boolean DeleteCmt(int CmtID){
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Comment";
    		ResultSet r = stmt.executeQuery(select);
    		boolean Delete = true;
    		while(r.next()) {
				
				if(r.getInt("CommentID") ==  CmtID && this.Username.equals(r.getString("Username"))) {
					Delete = r.getBoolean("DeleteCmt");
				}
				
			}
    		
    		if(Delete){
    			String delete = "Delete From Comment Where CommentID = " + CmtID;
    			stmt.executeUpdate(delete);
    			conn.commit();
    			return true;
    		}
    		
    		
    		
    	
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

    public boolean EditBlog(int BlogID, String title, String body){
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Blog";
    		ResultSet r = stmt.executeQuery(select);
    		boolean Edit = true;
    		while(r.next()) {
				
				if(r.getInt("BlogID") ==  BlogID && this.Username.equals(r.getString("Username"))) {
					Edit = r.getBoolean("Edit");
				}
				
			}
    		r.close();
    		if(Edit){
    			String update = "Update Blog Set Title = '" + title + "', Body = '" + body + "' Where BlogID = " + BlogID;
    			stmt.executeUpdate(update);
    			conn.commit();
    			return true;
    		}
    		
    		
    		
    	
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
    
    
    public boolean EditCmt(int CmtID, String body)
    {
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Comment";
    		ResultSet r = stmt.executeQuery(select);
    		boolean Update = true;
    		while(r.next()) {
				
				if(r.getInt("CommentID") ==  CmtID && this.Username.equals(r.getString("Username"))) {
					Update = r.getBoolean("Edit");
				}
				
			}
    		
    		r.close();
    		if(Update){
    			String delete = "Update Comment Set Body = '" + body + "' Where CommentID = " + CmtID;
    			stmt.executeUpdate(delete);
    			conn.commit();
    			return true;
    		}
    		
    		
    		
    	
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
    

    public void SetPrivacy(){
        
    }
}
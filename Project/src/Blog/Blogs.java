package Blog;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Interactive.Interactives;
import Interactive.LikeBlog;
import Status.BlogStatus;
import User.Users;
import ConnectDB.Connect_SQL;

public class Blogs{

    protected String Title;
    protected int BlogID;
    protected String Username;
    // protected int UserID; 
    protected String Body;
    protected boolean CommentEnabled;
    protected boolean DeleteBlog;
    protected String Date;
    protected boolean Edit;
    protected BlogStatus status;
    public ArrayList<Interactives> cmt = new ArrayList<Interactives>();
    // public ArrayList<LikeBlog> like = new ArrayList<>(); 
    public int likes; 
    public int isLiked; 
    
    String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Social_Network";
    String user = "sa";
    String pass = "Password123@jkl#";

    public Blogs(){}

    public Blogs(String title, int blogid, String username, String body, boolean cmtEnabled, boolean delete, String
    date, boolean edit, BlogStatus statuss){
        this.Title = title;
        this.BlogID = blogid;
        this.Username = username;
        this.Body = body;
        this.CommentEnabled = cmtEnabled;
        this.DeleteBlog = delete;
        this.Date = date;
        this.Edit = edit;
        this.status = statuss;
    }
    
    public Blogs(String title, String username, String body, boolean cmtEnabled, boolean delete, String
    	    date, boolean edit, BlogStatus statuss){
    	        this.Title = title;
    	        this.Username = username;
    	        this.Body = body;
    	        this.CommentEnabled = cmtEnabled;
    	        this.DeleteBlog = delete;
    	        this.Date = date;
    	        this.Edit = edit;
    	        this.status = statuss;
    	    }

    public Blogs(Blogs b){
        this.Title = b.Title;
        this.BlogID = b.BlogID;
        this.Username = b.Username;
        this.Body = b.Body;
        this.CommentEnabled = b.CommentEnabled;
        this.DeleteBlog = b.DeleteBlog;
        this.Date = b.Date;
        this.Edit = b.Edit;
        this.status = b.status;
        // this.getAllCmnt();
    }

    /// Method get

    public String get_Title(){
        return this.Title;
    }

    public int get_BlogID(){
        return this.BlogID;
    }

    public String get_Username(){
        return this.Username;
    }

    public String get_Body(){
        return this.Body;
    }

    public boolean get_CommentEnabled(){
        return this.CommentEnabled;
    }

    
    public boolean get_DeleteBlog(){
        return this.DeleteBlog;
    }

    public String get_Date(){
        return this.Date;
    }

    public boolean get_Edit(){
        return this.Edit;
    }

    // Method set

    public void set_Title(String title){
        this.Title = title;
    }

    public void set_BlogID(int blogid){
        this.BlogID = blogid;
    }

    public void set_Username(String username){
        this.Username = username;
    }

    public void set_Body(String body){
        this.Body = body;
    }

    public void set_CommentEnabled(boolean cmtEnabled){
        this.CommentEnabled = cmtEnabled;
    }

    
    public void set_DeleteBlog(boolean delete){
        this.DeleteBlog = delete;
    }

    public void set_Date(String date){
        this.Date = date;
    }

    public void set_Edit(boolean edit){
        this.Edit = edit;
    }
    
    public void getComments() {
    	try {
    		
//    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//            Connection conn = DriverManager.getConnection(dbURL, user, pass);
    		this.cmt.clear();
    		Connection conn = Connect_SQL.getConnection(); 
            Statement stmt = conn.createStatement();
            
            String sql = "Select * from Comment Where BlogID = " + this.BlogID; 
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
            	Interactives comment = new Interactives(rs.getString("Username"), rs.getInt("BlogID"), rs.getString("Body"), 
            			rs.getBoolean("DeleteCmt"), rs.getBoolean("Edit"), rs.getString("Date_of_Comment"), rs.getString("UsernameIDBlog"));
            	this.cmt.add(comment); 
            }
            rs.close();
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public int getLikes() {
    	try {
    		try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);;
    		Statement stmt = conn.createStatement();
            
            String sql = "SELECT count(*) AS Likes FROM LikeBlog WHERE BlogID = " + this.BlogID + "AND StatusLike = 1"; 
            ResultSet rs = stmt.executeQuery(sql); 
            
            while (rs.next()) {
            	this.likes = rs.getInt("Likes"); 
            }
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return this.likes; 
    }
    
    public int isLiked(String username) {
    	try {
    		try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);;
    		Statement stmt = conn.createStatement();
    		
            String sql = "SELECT StatusLike FROM LikeBlog WHERE BlogID = " + this.BlogID + " AND Username = '" + username + "'";  
            ResultSet rs = stmt.executeQuery(sql); 
            
            while (rs.next()) {
            	this.isLiked = rs.getInt("StatusLike");  
            }
            rs.close();
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return this.isLiked; 
    }
    
    public int getUserIDWithUsername(String username) {
    	int UserID = 0; 
    	try {
//    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//            Connection conn = DriverManager.getConnection(dbURL, user, pass);
    		Connection conn = Connect_SQL.getConnection(); 
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT UserID from User_Social WHERE Username = '" + username + "'"; 
            ResultSet rs = stmt.executeQuery(sql); 
            
            while (rs.next()) {
            	UserID = rs.getInt("UserID"); 
            }
            rs.close();
            
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return UserID; 
    }

    public void getAllCmnt() { // dont need, a blog just contains its own comments
    	this.cmt.clear();
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From Comment";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getString("BlogID").equals(this.BlogID))
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

    /// Method


    public void PostBlog(){

    }

    public void ViewBlog(){

    }

    public void LikeBlog(){

    }

    public void DeleteBlog(){

    }

    public void EditBlog(){

    }

    public void CommentEnable(){

    }

    public void SetPrivacy(){
        
    }

}
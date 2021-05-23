package Query;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blog.*;
import Interactive.*;
import Status.BlogStatus;
import ConnectDB.Connect_SQL;

public class QuerySQL{
    ArrayList<Blogs> blog = new ArrayList<Blogs>();
    ArrayList<Interactives> cmt = new ArrayList<Interactives>();
    ArrayList<LikeBlog> like = new ArrayList<LikeBlog>();
    
    public QuerySQL() {}
    
    public void getAllBlog() {
    	this.blog.clear();
    	try {
    		Connection conn = Connect_SQL.getConnection(); 
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From Blog";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
					BlogStatus stt = new BlogStatus();
					stt.set(r.getBoolean("Edit"));
					Blogs b = new Blogs(r.getString("Title"),r.getInt("BlogID"), r.getString("Username"),
							r.getString("Body"), r.getBoolean("CommentEnabled"),r.getBoolean("DeleteBlog"),
							r.getString("Date_of_blog"),r.getBoolean("Edit"), stt);
					// b.getAllCmnt();
					this.blog.add(b);
			}
			r.close();
    		
    		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void getAllBlog(String username) {
    	this.blog.clear();
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From Blog";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getString("Username").equals(username))
				{
					BlogStatus stt = new BlogStatus();
					stt.set(r.getBoolean("Edit"));
					Blogs b = new Blogs(r.getString("Title"),r.getInt("BlogID"), r.getString("Username"),
							r.getString("Body"), r.getBoolean("CommentEnabled"),r.getBoolean("DeleteBlog"),
							r.getString("Date_of_blog"),r.getBoolean("Edit"), stt);
					b.getAllCmnt();
					System.out.println(r.getString("Body"));
					this.blog.add(b);
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
    
    
    public void getAllCmt(int blogid) {
    	this.cmt.clear();
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From Comment";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getInt("BlogID") == (blogid))
				{
					Interactives inter = new Interactives(r.getString("Username"), r.getInt("BlogID"), r.getInt("CommentID"),
							r.getString("Body"),r.getBoolean("Deletecmt"), r.getBoolean("Edit"), r.getString("Date_of_Comment"),
							r.getString("UsernameIDBlog"));
					
					this.cmt.add(inter);
				}
				System.out.println(r.getString("Body"));
				
			}
			String Update = "Update Blog Set HitComment =" + this.cmt.size() + " Where BlogID = " + blogid;
			stmt.executeUpdate(Update);
			conn.commit();
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
    
    public int getAllLike(int blogid) {
    	this.like.clear();
    	int nb = 0;
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From LikeBlog";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getInt("BlogID") == (blogid))
				{
					if(r.getBoolean("StatusLike") == true) {
						nb = nb + 1;
					}
					
					LikeBlog likes = new LikeBlog(r.getInt("LikeID"), r.getInt("UsernameID"), r.getString("Username"),
							r.getBoolean("StatusLike"), r.getString("UsernameBlog"), r.getInt("BlogID"));
					this.like.add(likes);
				}	
			}
			String Update = "Update Blog Set HitLike =" + nb + " Where BlogID = " + blogid;
			stmt.executeUpdate(Update);
			conn.commit();
			r.close();
			
    		
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	return nb;
    }

	public ArrayList<Blogs> getBlog() {
		return blog;
	}

	public void setBlog(ArrayList<Blogs> blog) {
		this.blog = blog;
	}

	public ArrayList<Interactives> getCmt() {
		return cmt;
	}

	public void setCmt(ArrayList<Interactives> cmt) {
		this.cmt = cmt;
	}
	
	public boolean ClearCmtAndLikeOfBlog(int BlogID) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String deleteLike  = "Delete From LikeBlog Where BlogID = " + BlogID;
    		String deleteCmt  = "Delete From Comment Where BlogID = " + BlogID;
    		stmt.executeUpdate(deleteCmt);
    		stmt.executeUpdate(deleteLike);
    		conn.commit();
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
    
	
	public void SetCmtEnabled(int BlogID) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Blog Where BlogID = " + BlogID;
    		ResultSet r = stmt.executeQuery(select);
    		String update = "";
    
			while(r.next()) {
				System.out.print("Hello");
				if(r.getBoolean("CommentEnabled")) {
					update = "Update Blog Set CommentEnabled = " + 0 + " Where BlogID = " + BlogID;
					
					
				}
				else {
					 update = "Update Blog Set CommentEnabled = " + 1 + " Where BlogID = " + BlogID;					
					
				}
			}
			r.close();
			if(update.length() > 0) {
				stmt.executeUpdate(update);
				conn.commit();
			}
		
			
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
	}
	
	
	public void SetDeleteBlog(int BlogID) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Blog Where BlogID = " + BlogID;
    		ResultSet r = stmt.executeQuery(select);
    		String update = "";
    
			while(r.next()) {
				
				if(r.getBoolean("DeleteBlog")) {
					update = "Update Blog Set DeleteBlog = " + 0 + " Where BlogID = " + BlogID;
					
					
				}
				else {
					 update = "Update Blog Set DeleteBlog = " + 1 + " Where BlogID = " + BlogID;					
					
				}
			}
			r.close();
			if(update.length() > 0) {
				stmt.executeUpdate(update);
				conn.commit();
			}
		
			
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
	}
	
	public void SetEditBlog(int BlogID) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Blog Where BlogID = " + BlogID;
    		ResultSet r = stmt.executeQuery(select);
    		String update = "";
    
			while(r.next()) {
				
				if(r.getBoolean("Edit")) {
					update = "Update Blog Set Edit = " + 0 + " Where BlogID = " + BlogID;
					
					
				}
				else {
					 update = "Update Blog Set Edit = " + 1 + " Where BlogID = " + BlogID;					
					
				}
			}
			r.close();
			if(update.length() > 0) {
				stmt.executeUpdate(update);
				conn.commit();
			}
		
			
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
	}
	
	public void SetDeleteCmt(int CmtID) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Comment Where CommentID = " + CmtID;
    		ResultSet r = stmt.executeQuery(select);
    		String update = "";
			while(r.next()) {
				if(r.getBoolean("DeleteCmt")) {
					update = "Update Comment Set DeleteCmt = " + 0 + " Where CommentID = " + CmtID;
					
				}
				else {
					update = "Update Comment Set DeleteCmt = " + 1 + " Where CommentID = " + CmtID;
					
				}
			}
			r.close();
			if(update.length() > 0) {
				stmt.executeUpdate(update);
				conn.commit();
			}
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
	}
	
	public void SetEditCmt(int CmtID) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);
    		Statement stmt = conn.createStatement();
    		String select = "Select * From Comment Where CommentID = " + CmtID;
    		ResultSet r = stmt.executeQuery(select);
    		String update = "";
			while(r.next()) {
				if(r.getBoolean("Edit")) {
					 update = "Update Comment Set Edit = " + 0 + " Where CommentID = " + CmtID;
					
				}
				else {
					 update = "Update Comment Set Edit = " + 1 + " Where CommentID = " + CmtID;
					
				}
			}
			r.close();
			if(update.length() > 0) {
				stmt.executeUpdate(update);
				conn.commit();
			}
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
	}
    
    
}
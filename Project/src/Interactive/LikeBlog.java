package Interactive;

public class LikeBlog {
	private int LikeID;
	private int UserID;
	private String Username;
	private boolean StatusLike;
	private String UsernameBlog;
	private int BlogID;
	
	
	public LikeBlog(int userID, String username, boolean statusLike, String usernameBlog, int blogID) {
		super();
		UserID = userID;
		Username = username;
		StatusLike = statusLike;
		UsernameBlog = usernameBlog;
		BlogID = blogID;
	}
	
	public LikeBlog(int likeID, int userID, String username, boolean statusLike, String usernameBlog, int blogID) {
		super();
		LikeID = likeID;
		UserID = userID;
		Username = username;
		StatusLike = statusLike;
		UsernameBlog = usernameBlog;
		BlogID = blogID;
	}
	public int getLikeID() {
		return LikeID;
	}
	public void setLikeID(int likeID) {
		LikeID = likeID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public boolean isStatusLike() {
		return StatusLike;
	}
	public void setStatusLike(boolean statusLike) {
		StatusLike = statusLike;
	}
	public String getUsernameBlog() {
		return UsernameBlog;
	}
	public void setUsernameBlog(String usernameBlog) {
		UsernameBlog = usernameBlog;
	}
	public int getBlogID() {
		return BlogID;
	}
	public void setBlogID(int blogID) {
		BlogID = blogID;
	}

}

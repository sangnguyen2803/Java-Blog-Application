package Notification;

public class Interaction {
	private int id;
	private String username;
	private int usernameId;
	private String visitor;
	private int visitorId;
	private int blogId;
	private String date;
	private String notice;
	
	public Interaction(int id, int usernameId, String username, int visitorId, String visitor, int blogId,
			String date, String notice) {
		this.id = id;
		this.username = username;
		this.usernameId = usernameId;
		this.visitor = visitor;
		this.visitorId = visitorId;
		this.blogId = blogId;
		this.date = date;
		this.notice = notice;
	}
	public Interaction(int usernameId, String username, int visitorId, String visitor, int blogId,
			String date, String notice) {
		
		this.username = username;
		this.usernameId = usernameId;
		this.visitor = visitor;
		this.visitorId = visitorId;
		this.blogId = blogId;
		this.date = date;
		this.notice = notice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUsernameId() {
		return usernameId;
	}

	public void setUsernameId(int usernameId) {
		this.usernameId = usernameId;
	}

	public String getVisitor() {
		return visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
}

package Blog;

import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import User.Users;
import Blog.Blogs;

public class listBlog extends JFrame {
	JPanel panel = new JPanel(); 
	JPanel subPanel = new JPanel(); 
	JPanel bodyPanel = new JPanel(); 
	JPanel buttonPanel = new JPanel(); 
	JLabel titleLabel; 
	JLabel usernameLabel; 
	JLabel datePosted; 
	JButton likeButton = new JButton("Like"); 
	JButton commentButton = new JButton("Comment"); 
	static Users user; 
	ArrayList<Blogs> blogs = new ArrayList<>(); 
	
	public listBlog(Users u) {
		user = u; 
		user.getAllBlog();
		blogs = user.getBlog(); 
		
		for (Blogs blog : blogs) {
			
		}
		
	}
}

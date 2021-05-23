package Blog;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;

import User.*;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import Status.BlogStatus;
import Interactive.Interactives;
import Notification.Interaction;
import PageHome.PageHome;

public class BlogFrame extends JFrame {

	/**
	 * 
	 */
	JPanel contentPane = new JPanel();
	JLabel titleBlog; 
	JLabel usernameBlog; 
	JLabel datePosted; 
	JLabel bodyBlog; 
	JButton likeButton = new JButton(); 
	JButton commentButton = new JButton(); 
	
	
	static Users user; 
	static Blogs blog; 
	// int UserID = 0;
	
	public String getDate() {
		String date = "";
	
		date = java.time.LocalDate.now().toString() + " " + java.time.LocalTime.now().getHour() + ":"
				+ java.time.LocalTime.now().getMinute() + ":" + java.time.LocalTime.now().getSecond();
		return date;
	}
	public BlogFrame(Blogs b, Users u) {
		blog = b; 
		user = u; 
		// setSize(600, 120); 
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Blog of " + user.getUsername()); 
		setBounds(500, 300, 600, 400);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		contentPane.setBackground(new Color(0,138,138));
		contentPane.setBorder(BorderFactory.createLineBorder(new Color(0,138,138)));
		
		titleBlog = new JLabel(blog.get_Title()); 
		titleBlog.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernameBlog = new JLabel(blog.get_Username()); 
		datePosted = new JLabel(" at " + blog.get_Date());  
		datePosted.setForeground(Color.gray);
		bodyBlog = new JLabel(blog.get_Body()); 
		
		JPanel subPanel = new JPanel(); 
		subPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		subPanel.setBackground(Color.white);
		subPanel.add(titleBlog); 
		subPanel.add(usernameBlog); 
		subPanel.add(datePosted); 
		
		JPanel bodyPanel = new JPanel(); 
		bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bodyPanel.setBackground(Color.white);
		
		if (blog.get_Body().length() >= 85) {
			String text = "<html>"; 
			for (int i = 0; i < blog.get_Body().length(); i++) {
				text += blog.get_Body().charAt(i); 
				if (text.length() % 85 == 0) {
					text += "<br>"; 
				}
			}
			text += "</html>"; 
			bodyBlog = new JLabel(text); 
			bodyPanel.add(bodyBlog); 
		} 
		else { // just show it otherwise
			bodyBlog = new JLabel(blog.get_Body()); 
			bodyPanel.add(bodyBlog); 
		}
		
		JPanel buttonPanel = new JPanel(); 
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.setBackground(Color.white);
		
		blog.getComments();
		int isLiked = blog.isLiked(user.getUsername()); 
		JButton likeButton = new JButton(); 
		if (blog.isLiked(user.getUsername()) == 0) {
			likeButton.setText("Like (" + blog.getLikes() + ")");
			likeButton.setForeground(Color.black);
		}
		else {
			likeButton.setText("Like (" + blog.getLikes() + ")");
			likeButton.setForeground(Color.blue);
		}
		
		JButton commentButton = new JButton("Comment (" + blog.cmt.size() + ")"); 
		
		likeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				String date = String.valueOf(LocalDate.now()); 
				int userIdBlog = blog.getUserIDWithUsername(blog.get_Username()); 
				Interaction notice = new Interaction(userIdBlog, blog.get_Username(), user.getUserID(), user.getUsername(), blog.get_BlogID(), date, "");
				user.LikeBlog(blog.get_BlogID(), blog.get_Username(), notice);
				if (blog.isLiked == 0) { // like button
					blog.likes += 1; 
					likeButton.setForeground(Color.blue);
					likeButton.setText("Like (" + blog.likes + ")");
				}
				else { // unlike button
					blog.likes -= 1;
					likeButton.setForeground(Color.black);
					likeButton.setText("Like (" + blog.likes + ")");
				}
				blog.isLiked = Math.abs(blog.isLiked - 1); 
			}
		});
		
		commentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JDialog dialog = new JDialog(new PageHome(user), "Comment"); 
				// dialog.setSize(400,200);
				dialog.setBounds(600, 300, 600, 200);
				dialog.setLayout(new FlowLayout());
				JPanel panel = new JPanel(); 
				panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				JTextArea commentText = new JTextArea(3, 20); 
				commentText.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						JTextArea textArea = (JTextArea) e.getSource(); 
						String text = textArea.getText(); 
						if (text.length() % 30 == 0 && text.length() != 0) {
							text += "\n"; 
						}
						
						textArea.setText(text);
					}
				});
				
				panel.add(new JLabel("Your comment: ")); 
				panel.add(commentText); 
				
				JPanel buttonPanel = new JPanel(); 
				buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				JButton addCommentButton = new JButton("OK"); 
				addCommentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(commentText.getText());
						int userIdBlog = blog.getUserIDWithUsername(blog.get_Username()); 
						Interaction notice = new Interaction(userIdBlog, blog.get_Username(), user.getUserID(), user.getUsername(), blog.get_BlogID(), getDate(), "");
						user.Comment(blog, commentText.getText(), getDate(), notice);
					}
				});
				buttonPanel.add(addCommentButton); 
				
				dialog.add(panel); 
				dialog.add(buttonPanel); 
				dialog.setVisible(true);
			}
		});
		
		buttonPanel.add(likeButton);
		buttonPanel.add(commentButton); 
		
		contentPane.add(subPanel); 
		contentPane.add(bodyPanel); 
		contentPane.add(buttonPanel); 
		
		// show comment here
		blog.getComments();
		contentPane.add(Box.createRigidArea(new Dimension(0,10))); 
		ArrayList<JPanel> cmtPanel = showComment(blog.cmt); 
		for (JPanel panel : cmtPanel) {
			contentPane.add(panel); 
			contentPane.add(Box.createRigidArea(new Dimension(0,10))); 
		}
		
		JScrollPane scrollPane = new JScrollPane(contentPane); 
		
		
		
		add(scrollPane);
		pack();
		
		//setUndecorated(true);
	}
	
	public ArrayList<JPanel> showComment(ArrayList<Interactives> cmtList) {
		ArrayList<JPanel> cmtPanel = new ArrayList<>(); 
		for (Interactives cmt : cmtList) {
			JPanel panel = new JPanel(); 
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.setBackground(Color.white);
			panel.setBorder(BorderFactory.createLineBorder(new Color(0, 138, 138)));
			
			JPanel subPanel = new JPanel(); 
			subPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			subPanel.setBackground(Color.white);
			JLabel userCmt = new JLabel(cmt.get_Username()); 
			userCmt.setFont(new Font("Tahoma", Font.BOLD, 15));
			JLabel datePosted = new JLabel(" at " + cmt.get_Date()); 
			datePosted.setForeground(Color.gray);
			datePosted.setForeground(Color.gray);
			subPanel.add(userCmt); 
			subPanel.add(datePosted); 
			
			JPanel bodyPanel = new JPanel();
			bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			bodyPanel.setBackground(Color.white);
			
			if (cmt.get_Body().length() >= 70) {
				String text = "<html>"; 
				for (int i = 0; i < cmt.get_Body().length(); i++) {
					text += cmt.get_Body().charAt(i); 
					if (text.length() % 70 == 0) {
						text += "<br>"; 
					}
				}
				text += "</html>"; 
				JLabel bodyPost = new JLabel(text); 
				bodyPanel.add(bodyPost); 
			}
			else {
				JLabel bodyPost = new JLabel(cmt.get_Body()); 
				bodyPanel.add(bodyPost); 
			}
			
			panel.add(subPanel); 
			panel.add(bodyPanel); 
			
			cmtPanel.add(panel); 
		}
		
		return cmtPanel; 
	}
	
	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BlogFrame frame = new BlogFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
}

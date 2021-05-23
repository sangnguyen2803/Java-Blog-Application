package PageHome;

import java.time.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import User.Users;
import Blog.Blogs;
import Blog.postBlogFrame;
import LoginDesign.FrameLogin;
import Blog.BlogFrame;
import Status.BlogStatus;
import Notification.Interaction;
import Notification.NotificationManagement;
import Profile.ProfileBlogPublic;
import Query.QuerySQL;
import Setting.Setting_Blog;

import java.util.*;

public class PageHome extends JFrame implements ActionListener {

	JPanel contentPane;
	JLabel logoLabel; 
	JTextField searchBar; 
	JButton postBlogBtn; 
	JButton logoutBtn; 
	JLabel helloUser; 
	static Users user; 
	ArrayList<Blogs> blogList = new ArrayList<>();
	

	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PageHome frame = new PageHome(user);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public String getDate() {
		String date = "";
	
		date = java.time.LocalDate.now().toString() + " " + java.time.LocalTime.now().getHour() + ":"
				+ java.time.LocalTime.now().getMinute() + ":" + java.time.LocalTime.now().getSecond();
		return date;
	}
	public PageHome(Users u) {
		
		user = new Users(u);
		helloUser = new JLabel("Bonjour " + user.getUsername() + " !"); 
		helloUser.setBounds(800, 13, 200, 30);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1000, 800);
		setSize(1000, 800); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(new Color(0, 139, 139));
		
		// set up all panel: 
		JPanel navbarPanel = new JPanel(); // navbar
		JPanel container = new JPanel();
		JPanel scrollPanel = new JPanel(); // contains all blogs with a scrollpane
		JPanel sidebarPanel = new JPanel(); // a sidebar which contains all blog's titles
		
		// set up navbar: 
		navbarPanel.setLayout(null);
		navbarPanel.setBackground(Color.white);
		navbarPanel.setPreferredSize(new Dimension(contentPane.getWidth(), 55));
		logoLabel = new JLabel("VNS Blog");
		logoLabel.setForeground(new Color(225,127,80));
		logoLabel.setBounds(50,12,100,30); 
		
		searchBar = new JTextField("   Search for blog", 20); 
		searchBar.setBounds(200, 12, 350, 30); 
		searchBar.setBackground(new Color(238,238,238));
		searchBar.setForeground(Color.GRAY);
		searchBar.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Text field is pressed: " + searchBar.getText().toLowerCase()); 
				String text = searchBar.getText().toLowerCase(); 
				QuerySQL query = new QuerySQL(); 
				query.getAllBlog();
				ArrayList<Blogs> blogs = query.getBlog(); 
				for (Blogs blog: blogs) {
					if (blog.get_Title().toLowerCase().contains(text)) {
						BlogFrame blogFrame = new BlogFrame(blog, user); 
						blogFrame.setVisible(true);
					}
				}
				searchBar.setText("");
			}
		});
		
		postBlogBtn = new JButton("Create blog"); 
		postBlogBtn.setBounds(600, 13, 150, 30); 
		
		logoutBtn = new JButton("Logout"); 
		logoutBtn.setBounds(800, 13, 100, 30);
		
		navbarPanel.add(logoLabel); 
		navbarPanel.add(searchBar); 
		navbarPanel.add(postBlogBtn); 
		// navbarPanel.add(logoutBtn); 
		navbarPanel.add(helloUser); 
		
		// set up container: 
		container.setPreferredSize(new Dimension(contentPane.getWidth(), contentPane.getHeight() - navbarPanel.getSize().height));
		container.setBackground(contentPane.getBackground());
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL; 
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.insets = new Insets(8,8,8,8); 
		
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
		scrollPanel.setBackground(contentPane.getBackground());
		// scrollPanel.setPreferredSize(new Dimension(600, 700));
		JScrollPane scrollPane = new JScrollPane(scrollPanel); 
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(600, 700));
		// scrollPane.getViewport().setPreferredSize(new Dimension(600, 700));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		user.getAllBlog();
		blogList = user.getBlog(); 
		
		ArrayList<JPanel> blogPanel = showPost(blogList);
		for (JPanel panel : blogPanel) {
			scrollPanel.add(panel); 
			scrollPanel.add(Box.createRigidArea(new Dimension(0,10))); 
		}
		
		container.add(scrollPane, gbc); 
		
		sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.PAGE_AXIS));
	//	sidebarPanel.setBackground(Color.white);
		sidebarPanel.setBackground(new Color(50, 100, 120));
		sidebarPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 138, 138)));
		sidebarPanel.setBorder(new EmptyBorder(10,10,10,10));
		JScrollPane sidebarScroll = new JScrollPane(sidebarPanel); 
		sidebarScroll.setBorder(null);
		sidebarScroll.setPreferredSize(new Dimension(300, 700));
		sidebarScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); 
 		
		sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
		JLabel text = new JLabel("MOST RECENT BLOG"); 
		text.setForeground(Color.white);
		text.setFont(new Font("Tahoma", Font.BOLD, 15));
		sidebarPanel.add(text);
		sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));  
		
		ArrayList<JLabel> titleBlog = showBlogTitle(blogList); 
		for (JLabel title : titleBlog) {
			JPanel panel = new JPanel(); 
			panel.setLayout(new BorderLayout());
			panel.setBackground(Color.white);
			panel.setBorder(BorderFactory.createLineBorder(new Color(0,138,138)));
			panel.setBorder(new EmptyBorder(5,5,5,5));
			title.setFont(new Font("Tahoma", Font.BOLD, 12));
			title.setForeground(new Color(0,100,120));
			panel.add(title, BorderLayout.WEST); 
			JButton viewButton = new JButton("View"); 
			viewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// System.out.println(title.getText()); 
					for (Blogs blog: blogList) {
						if (title.getText().equals(blog.get_Title())) {
							BlogFrame blogFrame = new BlogFrame(blog, user); 
							blogFrame.setVisible(true);
						}
 					}
				}
			});
			panel.add(viewButton, BorderLayout.EAST);
			
			sidebarPanel.add(panel); 
			sidebarPanel.add(Box.createRigidArea(new Dimension(0,10)));
		}
		
		gbc.gridx = 1; 
		gbc.gridy = 0; 
		container.add(sidebarScroll, gbc); 
		
		// contentPane adding all their child components
		contentPane.add(navbarPanel); 
		contentPane.add(container); 
		
		
		
		
		// add action listener
		postBlogBtn.addActionListener(this);
		helloUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				ProfileBlogPublic frame = new ProfileBlogPublic(user);
				frame.setVisible(true);
				frame.showInfo();
				frame.showBlog();
			}
			
		});
		
		logoLabel.addMouseListener(new MouseAdapter() { // reload and update the home page
			public void mouseClicked(MouseEvent e) {
				dispose(); 
				PageHome newPageHome = new PageHome(user); 
				newPageHome.setVisible(true);
			}
		});
		
		setContentPane(contentPane);
	}
	
	public ArrayList<JPanel> showPost(ArrayList<Blogs> list) {
		ArrayList<JPanel> blogPanel = new ArrayList<>(); 
		for (Blogs blog : list) {
			JPanel panel = new JPanel(); 
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			// panel.setPreferredSize(new Dimension(590, 100));
			panel.setBackground(Color.white);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			JPanel subPanel = new JPanel(); 
			subPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			subPanel.setBackground(Color.white);
			JLabel titleLabel = new JLabel(blog.get_Title()); 
			titleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			JLabel usernameLabel = new JLabel("• Posted by " + blog.get_Username()); 
			JLabel datePosted = new JLabel(" at " + blog.get_Date()); 
			datePosted.setForeground(Color.gray);
			subPanel.add(titleLabel); 
			subPanel.add(usernameLabel); 
			subPanel.add(datePosted); 
			
			
			JPanel bodyPanel = new JPanel();
			bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			bodyPanel.setBackground(Color.white);
			
			// if the body is too long, break the lines
			if (blog.get_Body().length() >= 80) {
				String text = "<html>"; 
				for (int i = 0; i < blog.get_Body().length(); i++) {
					text += blog.get_Body().charAt(i); 
					if (text.length() % 80 == 0) {
						text += "<br>"; 
					}
				}
				text += "</html>"; 
				JLabel bodyPost = new JLabel(text); 
				bodyPanel.add(bodyPost); 
			} 
			else { // just show it otherwise
				JLabel bodyPost = new JLabel(blog.get_Body()); 
				bodyPanel.add(bodyPost); 
			}
			
			JPanel buttonPanel = new JPanel(); 
			buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			buttonPanel.setBackground(panel.getBackground());
			
			blog.getComments();
			int isLiked = blog.isLiked(user.getUsername()); 
			JButton likeButton = new JButton(); 
			if (blog.isLiked(user.getUsername()) == 0) {
//				if (blog.getLikes() > 0) {
//					likeButton.setText("Like (" + blog.likes + ")");
//					likeButton.setForeground(Color.black);
//				}
//				else if (blog.getLikes() == 0) {
//					// likeButton.setText("Like");
//					likeButton.setForeground(Color.black);
//				}
				likeButton.setText("Like (" + blog.getLikes() + ")");
				likeButton.setForeground(Color.black);
			}
			else {
				// likeButton.setText("Like");
				// likeButton.setBorderPainted(false);
				likeButton.setText("Like (" + blog.getLikes() + ")");
				likeButton.setForeground(Color.blue);
			}
			
			JButton commentButton = new JButton("Comment (" + blog.cmt.size() + ")"); 
			
			
			buttonPanel.add(likeButton); 
			buttonPanel.add(commentButton); 
			
			if (blog.get_Username().equals(user.getUsername())) {
				JButton editButton = new JButton("Setting"); 
				buttonPanel.add(editButton);
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						dispose();
						Setting_Blog st = new Setting_Blog(user);
						st.setVisible(true);
					}
				});
			}
			
			// add action listener
			likeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {					
					String date = getDate();
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
					JDialog dialog = new JDialog(PageHome.this, "Comment"); 
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
							dialog.setVisible(false);
						}
					});
					buttonPanel.add(addCommentButton); 
					
					dialog.add(panel); 
					dialog.add(buttonPanel); 
					dialog.pack();
					dialog.setVisible(true);
				}
			});
			
			
			panel.add(subPanel); 
			panel.add(bodyPanel); 
			panel.add(buttonPanel); 
			
			
			blogPanel.add(panel); 
			
			// add mouse listener for this whole panel
			panel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					BlogFrame blogFrame = new BlogFrame(blog, user); 
					blogFrame.setVisible(true);
				}
			});
		}
		
		// add action listener
		
		
		return blogPanel; 
	}
	
	public ArrayList<JLabel> showBlogTitle(ArrayList<Blogs> list) {
		ArrayList<JLabel> titleLabel = new ArrayList<>();
		for (Blogs blog : list) {
			JLabel label = new JLabel(blog.get_Title()); 
			titleLabel.add(label); 
		}
		
		return titleLabel; 
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand(); 
		if (command.equals("Create blog")) {			
			postBlogFrame pbf = new postBlogFrame(user); 
			pbf.setVisible(true);
		}
	}
}

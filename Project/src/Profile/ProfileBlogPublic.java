package Profile;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.border.LineBorder;

import AccountManagement.Account;
import Blog.Blogs;
import LoginDesign.FrameLogin;
import Notification.NotificationManagement;
import PageHome.PageHome;
import User.Users;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.TextArea;

public class ProfileBlogPublic extends JFrame {
	static Users user; 
	private ArrayList<Blogs> blog;
	private String blogId;
	private JPanel contentPane;
	private ImageIcon edit = new ImageIcon("src/FrameImages/edit.jpg");
	private JTextField Tfield_title;
	private JTextField textField;
	private JTextField textField2;
	private JTextField textField1;
	private JList list;
	private JTextArea textArea;
	private JTextField title;
	private JTextPane date;
	private JTextPane username;
	private JButton showBlogs;
	private JButton btnPublish;
	private JButton btnEdit;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileBlogPublic frame = new ProfileBlogPublic(user);
					frame.setVisible(true);
					frame.showInfo();
					frame.showBlog();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showBlog() {
		BlogQuery blogQuery = new BlogQuery();
		this.blog = new ArrayList<Blogs>();
		blogQuery.getBlogByUsername(user.getUsername());
		blog = blogQuery.getBlog();
		String[] values = new String[blog.size()];
		for (int i = 0; i < blog.size(); i++) {
			values[i] = String.valueOf(blog.get(i).get_BlogID()) + " - " + blog.get(i).get_Title();
		}
		this.list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		showBlogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEdit.setEnabled(true);
				btnPublish.setEnabled(false);
				String str = (String) list.getSelectedValue();
				String[] id = new String[2];
				id = str.split(" - ");
				ProfileBlogPublic.this.blogId = id[0];
				for(Blogs item : blog) {
					if (String.valueOf(item.get_BlogID()).equals(id[0])) {
						ProfileBlogPublic.this.date.setText("has published a blog on " + item.get_Date());
						ProfileBlogPublic.this.username.setText(item.get_Username());
						ProfileBlogPublic.this.title.setText(item.get_Title());
						ProfileBlogPublic.this.textArea.setText(item.get_Body());
					}
				}
			}
		});
		
	}
	public void showInfo() {
		JTextPane usernameProfile = new JTextPane();
		usernameProfile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		usernameProfile.setBackground(new Color(135, 206, 235));
		usernameProfile.setBounds(155, 5, 350, 35);
		
		JTextPane subInfo = new JTextPane();
		subInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subInfo.setBackground(new Color(135, 206, 235));
		subInfo.setBounds(150, 35, 500, 60);
		
		ProfileQuery profile = new ProfileQuery();
		Account profileInfo = profile.getAllAccount(String.valueOf(user.getUserID()));
		System.out.println(profileInfo.getUsername());
		usernameProfile.setText(profileInfo.getFirstName() + " " + profileInfo.getLastName() + " ( " + profileInfo.getUsername() + " )");
		usernameProfile.setEditable(false);
		String subInfoProfile = " Date of birth: " + profileInfo.getDateOfBirth()
					+ "\n Address: " + profileInfo.getAddress()
					+ "\n Email: " + profileInfo.getEmail();
		subInfo.setText(subInfoProfile);
		getContentPane().add(usernameProfile);
		getContentPane().add(subInfo);
	}
	private String String(long time) {
		// TODO Auto-generated method stub
		return null;
	}
	public ProfileBlogPublic(Users u) {
		user = new Users(u);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setForeground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(145, 9, 11, 84);
		contentPane.add(separator);
		
		JPanel body = new JPanel();
		body.setBackground(new Color(245, 245, 245));
		body.setBounds(0, 103, 586, 260);
		contentPane.add(body);
		body.setLayout(null);
		
		JButton btnCreateBlog = new JButton("CREATE A BLOG");
		btnCreateBlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateBlog frame = new CreateBlog(user);
				frame.showInfo();
				frame.createBlog();
				frame.setVisible(true);
				ProfileBlogPublic.this.dispose();
			}
		});
		btnCreateBlog.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateBlog.setBounds(211, 10, 153, 22);
		body.add(btnCreateBlog);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 41, 566, 2);
		body.add(separator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setBounds(10, 73, 153, 132);
		body.add(scrollPane_1);
		
		list = new JList();
		list.setBorder(new LineBorder(new Color(192, 192, 192)));
		scrollPane_1.setViewportView(list);
		list.setBackground(new Color(245, 245, 245));
		
		JLabel lblNewLabel = new JLabel("Blog List");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(55, 50, 60, 22);
		body.add(lblNewLabel);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(174, 52, 5, 198);
		body.add(separator_2);
		
		username = new JTextPane();
		username.setFont(new Font("Tahoma", Font.BOLD, 15));
		username.setBackground(new Color(245, 245, 245));
		username.setBounds(189, 49, 118, 25);
		username.setEditable(false);
		body.add(username);
		
		date = new JTextPane();
		date.setFont(new Font("Tahoma", Font.ITALIC, 14));
		date.setEditable(false);
		date.setBackground(new Color(245, 245, 245));
		date.setBounds(311, 49, 265, 25);
		body.add(date);
		
		title = new JTextField();
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setEditable(false);
		title.setBounds(189, 82, 387, 19);
		body.add(title);
		title.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 111, 388, 94);
		body.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		showBlogs = new JButton("Check");
		showBlogs.setBounds(44, 215, 85, 21);
		body.add(showBlogs);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				LocalDateTime now = LocalDateTime.now(); 
				date.setText("has published a blog on " + dtf.format(now));
				textArea.setEditable(true);
				title.setEditable(true);
				btnPublish.setEnabled(true);
				btnEdit.setEnabled(false);
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEdit.setBounds(391, 215, 85, 21);
		body.add(btnEdit);
		
		btnPublish = new JButton("Publish");
		btnPublish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				System.out.println(dtf.format(now));
				String date = dtf.format(now);
				String titleBlog = title.getText();
				String contentBlog = textArea.getText();
				String id = ProfileBlogPublic.this.blogId;
				textArea.setEditable(false);
				title.setEditable(false);
				btnPublish.setEnabled(false);
				btnEdit.setEnabled(true);
				BlogQuery query = new BlogQuery();
				if (query.updateBlog(id, titleBlog, contentBlog, date)) {
					System.out.println("Update successfully");
				}
				else {
					System.out.println("Fail to update");
				}
			}
		});
		btnPublish.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPublish.setBounds(491, 215, 85, 21);
		body.add(btnPublish);
		btnEdit.setEnabled(false);
		btnPublish.setEnabled(false);
		
		JButton btnHome = new JButton("Homepage");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PageHome pageHome = new PageHome(user); 
				pageHome.setVisible(true);
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHome.setBounds(10, 9, 125, 21);
		contentPane.add(btnHome);
		
		JButton btnActivities = new JButton("Notification");
		btnActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileBlogPublic.this.dispose();
				NotificationManagement frame = new NotificationManagement(user);
				frame.showInfo(user);
				frame.showNotification();
				frame.setVisible(true);
				
			}
		});
		btnActivities.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActivities.setBounds(10, 40, 125, 21);
		contentPane.add(btnActivities);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileBlogPublic.this.dispose();
				FrameLogin lg = new FrameLogin();
				lg.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogOut.setBounds(10, 72, 125, 21);
		contentPane.add(btnLogOut);
		
		JButton btnEditImg = new JButton(edit);
		btnEditImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProfileForm frameEdit = new EditProfileForm(u);
				frameEdit.setVisible(true);
				ProfileBlogPublic.this.dispose();
			}
		});
		edit.setDescription("Edit profile");
		btnEditImg.setBounds(498, 28, 65, 65);
		contentPane.add(btnEditImg);		
	}
}

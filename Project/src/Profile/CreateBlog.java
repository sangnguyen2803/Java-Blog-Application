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
import AccountManagement.AccountQuery;
import AccountManagement.UpdateForm;
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
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateBlog extends JFrame {
	static Users user;
	private JPanel contentPane;
	private ImageIcon edit = new ImageIcon("src/FrameImages/edit.jpg");
	private JTextField Tfield_title;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBlog frame = new CreateBlog(user);
					frame.setVisible(true);
					frame.showInfo();
					frame.createBlog();
					
				} catch (Exception e) {
					e.printStackTrace();
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
		Account profileInfo = profile.getAllAccount(String.valueOf(this.user.getUserID()));
		System.out.println(profileInfo.getUsername());
		usernameProfile.setText(profileInfo.getFirstName() + " " + profileInfo.getLastName() + " ( " + profileInfo.getUsername() + " )");
		usernameProfile.setEditable(false);
		String subInfoProfile = " Date of birth: " + profileInfo.getDateOfBirth()
					+ "\n Address: " + profileInfo.getAddress()
					+ "\n Email: " + profileInfo.getEmail();
		subInfo.setText(subInfoProfile);
		subInfo.setEditable(false);
		getContentPane().add(usernameProfile);
		getContentPane().add(subInfo);
	}
	public void createBlog() {
		JPanel body = new JPanel();
		body.setBackground(new Color(245, 245, 245));
		body.setBounds(0, 103, 586, 260);
		contentPane.add(body);
		body.setLayout(null);
		AccountQuery query = new AccountQuery();
		Account account = query.getAllAccount(String.valueOf(this.user.getUserID()));
		
		JTextPane postOwnerInfo = new JTextPane();
		postOwnerInfo.setBackground(new Color(245, 245, 245));
		postOwnerInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		postOwnerInfo.setBounds(20, 45, 271, 24);
		postOwnerInfo.setEditable(false);
		
		JTextPane Tfield_date = new JTextPane();
		Tfield_date.setBackground(new Color(245, 245, 245));
		Tfield_date.setFont(new Font("Tahoma", Font.ITALIC, 14));
		Tfield_date.setBounds(400, 45, 150, 24); // 300 -> 400, 145 -> 50
		Tfield_date.setEditable(false);
		
		
		postOwnerInfo.setText("Blog's owner: " + account.getUsername());
		Tfield_date.setText("2021-04-15");
		body.add(postOwnerInfo);
		body.add(Tfield_date);
		
		JLabel lblNewLabel = new JLabel("Title: ");
		lblNewLabel.setBounds(23, 75, 50, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		body.add(lblNewLabel);
		
		JTextField titleTxt = new JTextField();
		titleTxt.setBounds(80, 78, 480, 20);
		body.add(titleTxt);
		
		JLabel lblNewLabel_1 = new JLabel("WRITE BLOG");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(240, 15, 116, 13);
		body.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(30, 144, 255)));
		scrollPane.setBounds(20, 105, 540, 101);
		body.add(scrollPane);
		
		JTextArea contentTxtArea = new JTextArea();
		contentTxtArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(contentTxtArea);
		
		JCheckBox commentCheck = new JCheckBox("Anyone can comment on this blog");
		commentCheck.setBackground(new Color(245, 245, 245));
		commentCheck.setFont(new Font("Tahoma", Font.PLAIN, 13));
		commentCheck.setBounds(19, 210, 219, 21);
		body.add(commentCheck);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileBlogPublic profileBlog = new ProfileBlogPublic(user);
				profileBlog.setVisible(true);
				profileBlog.showInfo();
				profileBlog.showBlog();
				CreateBlog.this.dispose();
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(472, 10, 85, 21);
		body.add(btnBack);
		
		JButton BtnPublic = new JButton("Publish");
		BtnPublic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountQuery query = new AccountQuery();
				Account account = query.getAllAccount(String.valueOf(CreateBlog.this.user.getUserID()));
				String userId = String.valueOf(CreateBlog.this.user.getUserID());
				String content = contentTxtArea.getText();
				String title = titleTxt.getText();
				String username = account.getUsername();
				String date = Tfield_date.getText();
				boolean commentEnable = commentCheck.isSelected();
				BlogQuery blogQuery = new BlogQuery();
				if (blogQuery.insertBlog(account, title, content, commentEnable, date)) {
					System.out.println("Add successfully!");
				}
				else {
					System.out.println("Fail to add");
				}
				
			}
		});
		BtnPublic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BtnPublic.setBounds(447, 210, 113, 24);
		body.add(BtnPublic);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 40, 566, 2);
		body.add(separator_1);
	}
	public CreateBlog(Users u) {
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
		
		JButton btnHome = new JButton("Homepage");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHome.setBounds(10, 9, 125, 21);
		contentPane.add(btnHome);
		
		JButton btnView = new JButton("Notification");
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnView.setBounds(10, 40, 125, 21);
		contentPane.add(btnView);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogOut.setBounds(10, 72, 125, 21);
		contentPane.add(btnLogOut);
		
		JButton btnEditImg = new JButton(edit);
		btnEditImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProfileForm frameEdit = new EditProfileForm(user);
				frameEdit.setVisible(true);
				CreateBlog.this.dispose();
			}
		});
		edit.setDescription("Edit profile");
		btnEditImg.setBounds(483, 30, 65, 65);
		contentPane.add(btnEditImg);		
		

		
	
	}
}

package LoginDesign;

import java.awt.BorderLayout;

// import file from other package
import Blog.*;
import java.awt.EventQueue;
import User.Users;
import HashPw.*;
import Interactive.Interactives;
import Interactive.LikeBlog;
import Notification.Interaction;
import PageHome.PageHome;
import Profile.ProfileBlogPublic;
import Status.BlogStatus;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FrameLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private Image img_logo = new ImageIcon(FrameLogin.class.getResource("../imglogin/login.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_username = new ImageIcon(FrameLogin.class.getResource("../imglogin/username.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_password = new ImageIcon(FrameLogin.class.getResource("../imglogin/password.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_login = new ImageIcon(FrameLogin.class.getResource("../imglogin/login_icon.jpg")).getImage().getScaledInstance(45, 35, Image.SCALE_SMOOTH);
	private JLabel loginMessager = new JLabel("");
	private Users user = new Users();
	private int UserID = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin frame = new FrameLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getDate() {
		String date = "";
	
		date = java.time.LocalDate.now().toString() + " " + java.time.LocalTime.now().getHour() + ":"
				+ java.time.LocalTime.now().getMinute() + ":" + java.time.LocalTime.now().getSecond();
		return date;
	}
	
	public static boolean check_existe_user(String username, String password) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);

//			
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From User_Social";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getString("Username").equals(username) && BCrypt.checkpw(password, r.getString("Passwork"))) {
					return true;
				}
				else if (r.getString("Username").equals(username) == false || BCrypt.checkpw(password, r.getString("Passwork")) == false) {
					return false;
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
		
		
		return false;
	}
	
	/**
	 * Create the frame.
	 */
	public FrameLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(171, 160, 250, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea txtUsername = new JTextArea();
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtUsername.getText().equals("Username")) {
					txtUsername.setText("");
				}
				else {
					txtUsername.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsername.getText().equals(""))
				{
					txtUsername.setText("Username");
				}
			}
		});
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUsername.setText("Username");
		txtUsername.setBounds(10, 10, 176, 20);
		panel.add(txtUsername);
		
		JLabel IconUsername = new JLabel("");
		IconUsername.setHorizontalAlignment(SwingConstants.CENTER);
		IconUsername.setBounds(196, 5, 54, 28);
		panel.add(IconUsername);
		IconUsername.setIcon(new ImageIcon(img_username));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(171, 210, 250, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPassword.getText().equals("Password"))
				{
					txtPassword.setEchoChar('*');
					txtPassword.setText("");
				}
				else {
					txtPassword.selectAll();
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("Password");
					txtPassword.setEchoChar((char)0);
				}
			}
		});
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPassword.setEchoChar((char)0); 
		txtPassword.setText("Password");
		txtPassword.setToolTipText("");
		txtPassword.setBounds(10, 10, 179, 20);
		panel_1.add(txtPassword);
		
		JLabel IconPassword = new JLabel("");
		IconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		IconPassword.setBounds(194, 2, 56, 38);
		panel_1.add(IconPassword);
		IconPassword.setIcon(new ImageIcon(img_password));
		
		JPanel btnLogin = new JPanel();
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!txtUsername.getText().equals("") && !txtPassword.getText().equals("")) {
					loginMessager.setText("");
					if(FrameLogin.this.user.Login(txtUsername.getText(),txtPassword.getText()))
					{
						JOptionPane.showMessageDialog(null,"Login successfully");
						loginMessager.setText("");
						System.out.println(getDate());
						
						PageHome pageHome = new PageHome(FrameLogin.this.user); 
						FrameLogin.this.dispose();
				
						pageHome.setVisible(true);
						
					}
					else {
						loginMessager.setText("Username or password not correct!");
					}
					
				}
				else {
					if(txtUsername.getText().equals("") || txtPassword.getText().equals("")|| txtUsername.getText().equals("Username") || txtPassword.getText().equals("Password")) {
						loginMessager.setText("Please input all requirement");
					}
					else {
						loginMessager.setText("Username and password didn't math!");
					}
				}
					
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(30,60,60));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(47,79,79));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				btnLogin.setBackground(new Color(60,80,80));
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				btnLogin.setBackground(new Color(30,60,60));
			}
		});
		btnLogin.setBackground(new Color(47, 79, 79));
		btnLogin.setBounds(171, 282, 250, 47);
		contentPane.add(btnLogin);
		btnLogin.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(104, 10, 106, 27);
		btnLogin.add(lblNewLabel);
		
		JLabel IconLogin = new JLabel("");
		IconLogin.setHorizontalAlignment(SwingConstants.CENTER);
		IconLogin.setBounds(37, 0, 57, 47);
		btnLogin.add(IconLogin);
		IconLogin.setIcon(new ImageIcon(img_login));
		
		JLabel IconClose = new JLabel("X");
		IconClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to close this application ?", "Confirmation",JOptionPane.YES_NO_OPTION) == 0){
					FrameLogin.this.dispose();
				}
					
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				IconClose.setForeground(Color.red);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				IconClose.setForeground(Color.white);
			}
			
		});
		IconClose.setForeground(Color.WHITE);
		IconClose.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		IconClose.setHorizontalAlignment(SwingConstants.CENTER);
		IconClose.setBounds(570, 10, 20, 20);
		contentPane.add(IconClose);
		
		JLabel FrLogin = new JLabel("");
		FrLogin.setHorizontalAlignment(SwingConstants.CENTER);
		FrLogin.setBounds(171, 60, 250, 80);
		contentPane.add(FrLogin);
		FrLogin.setIcon(new ImageIcon(img_logo));
		
		JLabel label = new JLabel("New label");
		label.setBounds(171, 249, -17, 1);
		contentPane.add(label);
		
		loginMessager.setForeground(new Color(255, 255, 0));
		loginMessager.setFont(new Font("Arial", Font.PLAIN, 12));
		loginMessager.setBounds(171, 260, 250, 20);
		contentPane.add(loginMessager);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameLogin.this.dispose();
				FrameSignUp obj = new FrameSignUp();
				obj.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(250, 250, 210));
		btnNewButton.setForeground(new Color(30, 144, 255));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(323, 334, 98, 21);
		contentPane.add(btnNewButton);
		
//		setUndecorated(true);
		setLocationRelativeTo(null);
		
	}
}

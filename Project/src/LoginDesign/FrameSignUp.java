package LoginDesign;

import java.awt.BorderLayout;
import User.Users;
import HashPw.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class FrameSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField txtfirstname;
	private JTextField txtlastname;
	private JTextField txtadress;
	private JTextField txtusername;
	private JTextField txtemail;
	private JPasswordField txtpassword;
	private JTextField txtDate;
	private JLabel check_date = new JLabel("");
	private JLabel check_adress = new JLabel("");
	private JLabel check_password = new JLabel("");
	private JLabel check_username = new JLabel("");
	private JLabel check_firstname = new JLabel("");
	private JLabel check_lastname = new JLabel("");
	private JLabel check_email = new JLabel("");
	private final Action action = new SwingAction();
	private JRadioButton FemaleRadioButton = new JRadioButton("Female");
	private JRadioButton MaleRadioButton = new JRadioButton("Male");
	private JLabel check_sex = new JLabel("");
	
	static String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Social_Network";
    static String user = "sa";
    static String pass = "Password123@jkl#";

	
	// Check date of birth
	public static boolean check_date(String d) {
		
		String[] tmp = d.split("-");
		if(tmp.length != 3) {
			return false;
		}
		else {
			for(int i = 0; i < tmp.length; i++) {
				try{
	                Integer.parseInt(tmp[i]);
	            } catch (Exception e) {
	            	return false;
	            }
			}
		}
		
		return true;
	}
	
	public static boolean check_existe_user(String username) {
		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
//    		Connection conn = DriverManager.getConnection(DB_URL);
			
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            Connection conn = DriverManager.getConnection(dbURL, user, pass);
			
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From User_Social";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getString("Username").equals(username)) {
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
		
		
		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSignUp frame = new FrameSignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameSignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New User Register");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(234, 48, 310, 43);
		contentPane.add(lblNewLabel);
		
		JPanel txtFirstName = new JPanel();
		txtFirstName.setBounds(146, 119, 183, 43);
		contentPane.add(txtFirstName);
		txtFirstName.setLayout(null);
		
		txtfirstname = new JTextField();
		txtfirstname.setBorder(null);
		txtfirstname.setBounds(10, 10, 163, 30);
		txtFirstName.add(txtfirstname);
		txtfirstname.setColumns(10);
		
		JLabel txtFN = new JLabel("First Name");
		txtFN.setForeground(Color.WHITE);
		txtFN.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFN.setHorizontalAlignment(SwingConstants.CENTER);
		txtFN.setBounds(64, 119, 72, 43);
		contentPane.add(txtFN);
		
		JLabel txtLN = new JLabel("Last Name");
		txtLN.setHorizontalAlignment(SwingConstants.CENTER);
		txtLN.setForeground(Color.WHITE);
		txtLN.setFont(new Font("Arial", Font.PLAIN, 14));
		txtLN.setBounds(64, 200, 72, 43);
		contentPane.add(txtLN);
		
		JPanel txtLastName = new JPanel();
		txtLastName.setBounds(146, 200, 183, 43);
		contentPane.add(txtLastName);
		txtLastName.setLayout(null);
		
		txtlastname = new JTextField();
		txtlastname.setBorder(null);
		txtlastname.setColumns(10);
		txtlastname.setBounds(10, 10, 163, 30);
		txtLastName.add(txtlastname);
		
		JLabel txtAdr = new JLabel("Adress");
		txtAdr.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdr.setForeground(Color.WHITE);
		txtAdr.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAdr.setBounds(64, 283, 72, 43);
		contentPane.add(txtAdr);
		
		JPanel txtAdress = new JPanel();
		txtAdress.setBounds(146, 283, 183, 43);
		contentPane.add(txtAdress);
		txtAdress.setLayout(null);
		
		txtadress = new JTextField();
		txtadress.setBorder(null);
		txtadress.setColumns(10);
		txtadress.setBounds(10, 10, 163, 30);
		txtAdress.add(txtadress);
		
		JLabel txtUs = new JLabel("Username");
		txtUs.setHorizontalAlignment(SwingConstants.CENTER);
		txtUs.setForeground(Color.WHITE);
		txtUs.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUs.setBounds(403, 119, 72, 43);
		contentPane.add(txtUs);
		
		JPanel txtUsername = new JPanel();
		txtUsername.setBounds(485, 119, 183, 43);
		contentPane.add(txtUsername);
		txtUsername.setLayout(null);
		
		txtusername = new JTextField();
		txtusername.setBorder(null);
		txtusername.setColumns(10);
		txtusername.setBounds(10, 10, 163, 30);
		txtUsername.add(txtusername);
		
		JLabel txtPw = new JLabel("Password");
		txtPw.setHorizontalAlignment(SwingConstants.CENTER);
		txtPw.setForeground(Color.WHITE);
		txtPw.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPw.setBounds(403, 200, 72, 43);
		contentPane.add(txtPw);
		
		JLabel txtEm = new JLabel("Email");
		txtEm.setHorizontalAlignment(SwingConstants.CENTER);
		txtEm.setForeground(Color.WHITE);
		txtEm.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEm.setBounds(403, 283, 72, 43);
		contentPane.add(txtEm);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(485, 283, 183, 43);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		txtemail = new JTextField();
		txtemail.setBorder(null);
		txtemail.setColumns(10);
		txtemail.setBounds(10, 10, 163, 30);
		panel_5.add(txtemail);
		
		JPanel txtUsername_1 = new JPanel();
		txtUsername_1.setBounds(485, 200, 183, 43);
		contentPane.add(txtUsername_1);
		txtUsername_1.setLayout(null);
		
		txtpassword = new JPasswordField();
		txtpassword.setBorder(null);
		txtpassword.setBounds(10, 10, 163, 30);
		txtUsername_1.add(txtpassword);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int check = 0;
				
				if(txtfirstname.getText().equals("")) {
					check_firstname.setText("First name not empty");
				}
				else {
					check_firstname.setText("");
					check += 1;
				}
				
				if(txtlastname.getText().equals("")) {
					check_lastname.setText("Last name not empty");
				}
				else {
					check_lastname.setText("");
					check += 1;
				}
				
				if(txtadress.getText().equals("")) {
					check_adress.setText("Adress not empty");
				}
				else {
					check_adress.setText("");
					check += 1;
				}
				
				if(txtDate.getText().equals("")) {
					check_date.setText("Date of birth not empty");
				}
				else {
					if(check_date(txtDate.getText()))
					{
						check_date.setText("");
						check += 1;
					}
					else {
						check_date.setText("Follow the format YYYY-MM-DD");
					}
					
				}
				
				if(txtusername.getText().equals("")) {
					check_username.setText("Username not empty");
				}
				else {
					if(check_existe_user(txtusername.getText())) {
						check_username.setText("");
						check += 1;
					}
					else {
						check_username.setText("Username already exists");
					}
					
				}
				
				if(txtpassword.getText().equals("")) {
					check_password.setText("Password not empty");
				}
				else {
					check_password.setText("");
					check += 1;
				}
				
				if(txtemail.getText().equals("")) {
					check_email.setText("Email not empty");
				}
				else {
					check_email.setText("");
					check += 1;
				}
				
				
				
				
				
				if(!MaleRadioButton.isSelected() && !FemaleRadioButton.isSelected()) {
					check_sex.setText("Please select your sex");
				}
				else {
					check_sex.setText("");
				}
				if(check == 7) {
				
			    		
			    	String firstname = txtfirstname.getText();
			    	String lastname = txtlastname.getText();
			   		String username = txtusername.getText();
			   		String password = txtpassword.getText();
			   		String date_of_birth = txtDate.getText();
			   		String adress = txtadress.getText();
		    		String email = txtemail.getText();
		    		String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
		    		Users u = new Users(username, hashpw, firstname, lastname, date_of_birth, adress, email);
			    	u.SignUp();	
			    	
			    	
		    		
					txtfirstname.setText("");
		    		txtlastname.setText("");
		    		txtusername.setText("");
		    		txtpassword.setText("");
		    		txtDate.setText("");
		    		txtadress.setText("");
		    		txtemail.setText("");
		    		FrameSignUp.this.dispose();
		    		FrameLogin login = new FrameLogin();
		    		login.setVisible(true);
				}
				
			}
			
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(335, 490, 163, 49);
		contentPane.add(btnNewButton);
		
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateOfBirth.setForeground(Color.WHITE);
		lblDateOfBirth.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(38, 367, 103, 43);
		contentPane.add(lblDateOfBirth);
		
		JPanel txtAdress_1 = new JPanel();
		txtAdress_1.setLayout(null);
		txtAdress_1.setBounds(146, 367, 183, 43);
		contentPane.add(txtAdress_1);
		
		txtDate = new JTextField();
		txtDate.setBorder(null);
		txtDate.setColumns(10);
		txtDate.setBounds(10, 10, 163, 30);
		txtAdress_1.add(txtDate);
		
		
		check_lastname.setForeground(new Color(255, 215, 0));
		check_lastname.setFont(new Font("Arial", Font.PLAIN, 12));
		check_lastname.setBounds(146, 253, 183, 20);
		contentPane.add(check_lastname);
		
		
		check_firstname.setForeground(new Color(255, 215, 0));
		check_firstname.setFont(new Font("Arial", Font.PLAIN, 12));
		check_firstname.setBounds(146, 170, 183, 20);
		contentPane.add(check_firstname);
		
		
		check_username.setForeground(new Color(255, 215, 0));
		check_username.setFont(new Font("Arial", Font.PLAIN, 12));
		check_username.setBounds(485, 172, 183, 20);
		contentPane.add(check_username);
		
		
		check_password.setForeground(new Color(255, 215, 0));
		check_password.setFont(new Font("Arial", Font.PLAIN, 12));
		check_password.setBounds(485, 253, 183, 20);
		contentPane.add(check_password);
		
		
		check_adress.setForeground(new Color(255, 215, 0));
		check_adress.setFont(new Font("Arial", Font.PLAIN, 12));
		check_adress.setBounds(146, 337, 183, 20);
		contentPane.add(check_adress);
		
		
		check_date.setForeground(new Color(255, 215, 0));
		check_date.setFont(new Font("Arial", Font.PLAIN, 12));
		check_date.setBounds(146, 420, 183, 43);
		contentPane.add(check_date);
		
		
		check_email.setForeground(new Color(255, 215, 0));
		check_email.setFont(new Font("Arial", Font.PLAIN, 12));
		check_email.setBounds(485, 337, 183, 20);
		contentPane.add(check_email);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setHorizontalAlignment(SwingConstants.CENTER);
		lblSex.setForeground(Color.WHITE);
		lblSex.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSex.setBounds(403, 367, 72, 43);
		contentPane.add(lblSex);
		FemaleRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FemaleRadioButton.isSelected()) {
					MaleRadioButton.setSelected(false);
				}
			}
		});
		
		
		FemaleRadioButton.setFont(new Font("Arial", Font.PLAIN, 12));
		FemaleRadioButton.setBounds(596, 367, 72, 33);
		contentPane.add(FemaleRadioButton);
		
		
		MaleRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MaleRadioButton.isSelected()) {
					FemaleRadioButton.setSelected(false);
				}
			}
		});
		MaleRadioButton.setFont(new Font("Arial", Font.PLAIN, 12));
		MaleRadioButton.setBounds(484, 367, 72, 33);
		contentPane.add(MaleRadioButton);
		
		
		check_sex.setForeground(new Color(255, 215, 0));
		check_sex.setFont(new Font("Arial", Font.PLAIN, 12));
		check_sex.setBounds(485, 420, 183, 20);
		contentPane.add(check_sex);
		
		JButton btnNewButton_1 = new JButton("X");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to close this application ?", "Confirmation",JOptionPane.YES_NO_OPTION) == 0){
					FrameSignUp.this.dispose();
					
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setBackground(new Color(0, 139, 139));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(750, 0, 50, 21);
		contentPane.add(btnNewButton_1);
		setUndecorated(true);
		
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}

package AccountManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ConnectDB.Connect_SQL;
import HashPw.BCrypt;
import User.Users;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AccountManagement extends JFrame {
	static Users user; 
	private String removeId; //this id is used for removing an account
	private JPanel contentPane;
	private JTable table;
//	private JTable table2_user;
	private JTable table1_admin;
	DefaultTableModel model1_admin;
	private JScrollPane scrollPane1_admin;
//	private JScrollPane scrollPane2_user;
	private JLabel lblNewLabel1_username;
	private JLabel lblNewLabel2_username;
	private JTextField textField1_username;
	private JTextField textField2_id;
	private JLabel lblNewLabel2_password;
	private JPasswordField passwordField_2;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel2_id;
	private JTextField textField2_username;
	private JTextField textField1_id;
	private JLabel lblNewLabel1_id;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private ImageIcon frame_bg = new ImageIcon("src/FrameImages/frame_bg.jpg");
	private JTextField txtBoxRemove;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountManagement frame = new AccountManagement();
					frame.setVisible(true);
					frame.showUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getUsername(String userid) {
		String username = "";
	
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);

//			
    		Statement stmt = conn.createStatement();
    		String getAllUser = "Select * From User_Social";
    		ResultSet r = stmt.executeQuery(getAllUser);
			while(r.next()) {
				if(r.getInt("UserID") == Integer.parseInt(userid)) {
				username = r.getString("Username");
				r.close();
				break;
			}
			
    		
			}	
		}catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class.");
    		System.exit(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return username;
	}
	
	
	
	public void removeInteractionByBlogID(String userid) {
		String userOwer =  getUsername(userid);
		try {
			Connect_SQL connect = new Connect_SQL();
			Connection conn = connect.getConnection();
			PreparedStatement stmt = null;
			String queryStatement = "DELETE FROM Interaction WHERE blogOwnerUsername = N'" + userOwer + "'";
			stmt = conn.prepareStatement(queryStatement);
			stmt.executeUpdate();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("1");
	
	}
	
	public void remove_Status(String userid) {
		String username = getUsername(userid);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
    		Connection conn = DriverManager.getConnection(DB_URL);					
    		Statement stmt = conn.createStatement();
    		String delete = "Delete from BlogStatus Where UsernameBlog = N'" + username + "'";
    		stmt.executeUpdate(delete);
    		
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	protected void displayResultFromFinding(ArrayList<Account> list) {
	
		model1_admin = (DefaultTableModel)table1_admin.getModel();
		model1_admin.setRowCount(0);
		Object[] column_admin = {"ID", "Username", "Password", "FirstName", "LastName", "Date Of Birth", "Address", "Email"};
		final Object[] row_user = new Object[8];
		for (int i = 0; i < list.size(); i++) {
			row_user[0] = list.get(i).getId();
			row_user[1] = list.get(i).getUsername();
			row_user[2] = list.get(i).getPassword();
			row_user[3] = list.get(i).getFirstName();
			row_user[4] = list.get(i).getLastName();
			row_user[5] = list.get(i).getDateOfBirth();
			row_user[6] = list.get(i).getAddress();
			row_user[7] = list.get(i).getEmail();
			model1_admin.addRow(row_user);
		}
		model1_admin.setColumnIdentifiers(column_admin);
		
	}
	protected void showUser() {
		model1_admin = (DefaultTableModel)table1_admin.getModel();
		AccountQuery accQuery = new AccountQuery();
		accQuery.getAllAccount();
		ArrayList<Account> list = accQuery.getAccountList();
		model1_admin.setRowCount(0);
		Object[] column_admin = {"ID", "Username", "Password", "FirstName", "LastName", "Date Of Birth", "Address", "Email"};
		final Object[] row_user = new Object[8];
		for (int i = 0; i < list.size(); i++) {
			row_user[0] = list.get(i).getId();
			row_user[1] = list.get(i).getUsername();
			row_user[2] = list.get(i).getPassword();
			row_user[3] = list.get(i).getFirstName();
			row_user[4] = list.get(i).getLastName();
			row_user[5] = list.get(i).getDateOfBirth();
			row_user[6] = list.get(i).getAddress();
			row_user[7] = list.get(i).getEmail();
			model1_admin.addRow(row_user);
		}
		model1_admin.setColumnIdentifiers(column_admin);
	}
	public AccountManagement() {
		user = new Users();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBounds(10, 57, 566, 296);
		contentPane.add(tabbedPane);
		/*
		JPanel panel2_user = new JPanel();
		tabbedPane.addTab("Users", null, panel2_user, null);
		panel2_user.setLayout(null);
		
		scrollPane2_user = new JScrollPane();
		scrollPane2_user.setBounds(10, 33, 541, 140);
		panel2_user.add(scrollPane2_user);
		table2_user = new JTable();
		model2_user = new DefaultTableModel();
		scrollPane2_user.setViewportView(table2_user);
		table2_user.setBorder(new LineBorder(new Color(72, 209, 204), 1, true));
		table2_user.setModel(model2_user);
		scrollPane2_user.setViewportView(table2_user);
		
		
		lblNewLabel2_id = new JLabel("ID:");
		lblNewLabel2_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel2_id.setBounds(10, 184, 30, 14);
		panel2_user.add(lblNewLabel2_id);
		
		textField2_id = new JTextField();
		textField2_id.setBounds(31, 182, 118, 19);
		panel2_user.add(textField2_id);
		textField2_id.setColumns(10);
		
		lblNewLabel2_username = new JLabel("Username:");
		lblNewLabel2_username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel2_username.setBounds(163, 184, 70, 14);
		panel2_user.add(lblNewLabel2_username);
		
		textField2_username = new JTextField();
		textField2_username.setColumns(10);
		textField2_username.setBounds(232, 184, 118, 19);
		panel2_user.add(textField2_username);
		
		lblNewLabel2_password = new JLabel("Password:");
		lblNewLabel2_password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel2_password.setBounds(360, 184, 70, 14);
		panel2_user.add(lblNewLabel2_password);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(426, 184, 125, 19);
		panel2_user.add(passwordField_2);
		
		btnNewButton_3 = new JButton("Add");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(466, 223, 85, 21);
		panel2_user.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Update");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBounds(360, 223, 85, 21);
		panel2_user.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Remove");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_5.setBounds(252, 223, 85, 21);
		panel2_user.add(btnNewButton_5);
		
		*/
		JPanel panel1_admin = new JPanel();
		tabbedPane.addTab("     Account Management    ", null, panel1_admin, null);
		panel1_admin.setLayout(null);
		
		model1_admin = new DefaultTableModel();
		Object[] column_admin = {"ID", "Username", "Password", "FirstName", "LastName", "Date Of Birth", "Address", "Email"};
		final Object[] row_admin = new Object[8];
		model1_admin.setColumnIdentifiers(column_admin);
		
		scrollPane1_admin = new JScrollPane();
		scrollPane1_admin.setBounds(10, 54, 541, 142);
		panel1_admin.add(scrollPane1_admin);
		
		table1_admin = new JTable();
		scrollPane1_admin.setViewportView(table1_admin);
		table1_admin.setBorder(new LineBorder(new Color(72, 209, 204), 1, true));
		table1_admin.setModel(model1_admin);
		scrollPane1_admin.setViewportView(table1_admin);
		
		lblNewLabel1_id = new JLabel("ID:");
		lblNewLabel1_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_id.setBounds(63, 30, 30, 14);
		panel1_admin.add(lblNewLabel1_id);
		
		textField1_id = new JTextField();
		textField1_id.setColumns(10);
		textField1_id.setBounds(92, 25, 70, 19);
		panel1_admin.add(textField1_id);
		
		lblNewLabel1_username = new JLabel("Username:");
		lblNewLabel1_username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_username.setBounds(192, 30, 70, 14);
		panel1_admin.add(lblNewLabel1_username);
		
		textField1_username = new JTextField();
		textField1_username.setBounds(267, 25, 118, 19);
		panel1_admin.add(textField1_username);
		textField1_username.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddForm addForm = new AddForm();
				addForm.setVisible(true);
				/*
				String id = textField1_id.getText();
				String username = textField1_username.getText();
				String password = passwordField_1.getText();
				if(!(id.equals("") && username.equals("") && password.equals(""))) {
					ArrayList<Account> accountList = new ArrayList<Account>();
					AccountQuery accQuery = new AccountQuery();
					accQuery.addAccount(id, username, password);
					accountList = accQuery.getAccountList();
					displayResultFromFinding(accountList);
					showUser();
				}
				*/
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(466, 223, 85, 21);
		panel1_admin.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateForm updateForm = new UpdateForm();
				updateForm.setVisible(true);
				return;
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(360, 223, 85, 21);
		panel1_admin.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Remove");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeId = txtBoxRemove.getText();
				if(removeId.length() > 0) {
					
					AccountQuery accQuery = new AccountQuery();
				
					accQuery.removeInteractionByUserId(removeId);
					
					accQuery.removeLikeByUserId(removeId);
					accQuery.removeCommentByUserId(removeId);
					
					ArrayList<String> list = new ArrayList<String>();
					list = accQuery.getAllBlogId(removeId);
					
					for (String item : list) {
						accQuery.removeLikeByBlogId(item);
						accQuery.removeCommentByBlogId(item);
					}
					
					remove_Status(removeId);
					removeInteractionByBlogID(removeId);
					accQuery.removeBlogByUserId(removeId);
					
					if (accQuery.removeById(removeId) == 1) {
						JOptionPane.showMessageDialog(null, "Successfully removed an account from database", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Fail to remove an account from database", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
					}
					
					showUser();
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(254, 223, 85, 21);
		panel1_admin.add(btnNewButton_2);
		
		btnNewButton_6 = new JButton("Find");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField1_id.getText();
				String username = textField1_username.getText();
				if(!(id.equals("") && username.equals(""))) {
					ArrayList<Account> accountList = new ArrayList<Account>();
					AccountQuery accQuery = new AccountQuery();
					accQuery.findAccountByIdAndUsername(id, username);
					accountList = accQuery.getAccountList();
					displayResultFromFinding(accountList);
				}
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_6.setBounds(416, 23, 85, 21);
		panel1_admin.add(btnNewButton_6);
		
		JButton btnNewButton_2_1 = new JButton("Clear");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1_admin.setRowCount(0);
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2_1.setBounds(10, 223, 85, 21);
		panel1_admin.add(btnNewButton_2_1);
		
		txtBoxRemove = new JTextField();
		txtBoxRemove.setBounds(179, 223, 70, 21);
		panel1_admin.add(txtBoxRemove);
		txtBoxRemove.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ACCOUNT MANAGEMENT");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(157, 10, 287, 37);
		contentPane.add(lblNewLabel);
		JLabel lblNewLabel2 = new JLabel(frame_bg);
		lblNewLabel2.setBounds(0, 0, 586, 363);
		contentPane.add(lblNewLabel2);
		
	}
}

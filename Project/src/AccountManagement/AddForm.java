package AccountManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import HashPw.BCrypt;
import User.Users;

import javax.swing.JMenuBar;

public class AddForm extends JFrame {
	static Users user; 
	private JPanel contentPane;
	private JTextField tfield_username;
	private JPasswordField pfield_password;
	private JTextField tfield_firstname;
	private JTextField tfield_email;
	private JTextField tfield_lastname;
	private JTextField tfield_address;
	private ImageIcon frame_bg = new ImageIcon("src/FrameImages/frame_bg.jpg");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddForm frame = new AddForm();
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
	public AddForm() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1_admin = new JPanel();
		panel1_admin.setBorder(new LineBorder(new Color(0, 128, 128), 1, true));
		panel1_admin.setLayout(null);
		panel1_admin.setBounds(10, 77, 561, 265);
		contentPane.add(panel1_admin);
		
		JLabel lblNewLabel1_username = new JLabel("Username:");
		lblNewLabel1_username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel1_username.setBounds(32, 85, 106, 14);
		panel1_admin.add(lblNewLabel1_username);
		
		tfield_username = new JTextField();
		tfield_username.setColumns(10);
		tfield_username.setBounds(139, 85, 129, 19);
		panel1_admin.add(tfield_username);
		
		JLabel lblNewLabel1_password = new JLabel("Password:");
		lblNewLabel1_password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel1_password.setBounds(32, 117, 106, 14);
		panel1_admin.add(lblNewLabel1_password);
		
		pfield_password = new JPasswordField();
		pfield_password.setBounds(139, 114, 129, 19);
		panel1_admin.add(pfield_password);
		
		JLabel lblNewLabel1_password_1 = new JLabel("Date Of Birth:");
		lblNewLabel1_password_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel1_password_1.setBounds(32, 148, 106, 14);
		panel1_admin.add(lblNewLabel1_password_1);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 0);
		JDateChooser dateChooser = new JDateChooser(c.getTime());
		dateChooser.setBounds(139, 143, 129, 19);
		panel1_admin.add(dateChooser);
			
		JLabel lblNewLabel1_password_1_1 = new JLabel("First name:");
		lblNewLabel1_password_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel1_password_1_1.setBounds(298, 85, 106, 14);
		panel1_admin.add(lblNewLabel1_password_1_1);
		
		tfield_firstname = new JTextField();
		tfield_firstname.setColumns(10);
		tfield_firstname.setBounds(387, 85, 141, 19);
		panel1_admin.add(tfield_firstname);
		
		JLabel lblNewLabel1_password_2 = new JLabel("Lastname:");
		lblNewLabel1_password_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel1_password_2.setBounds(298, 117, 73, 14);
		panel1_admin.add(lblNewLabel1_password_2);
		
		JLabel lblNewLabel1_password_1_2 = new JLabel("Email:");
		lblNewLabel1_password_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel1_password_1_2.setBounds(32, 177, 106, 14);
		panel1_admin.add(lblNewLabel1_password_1_2);
		
		tfield_email = new JTextField();
		tfield_email.setColumns(10);
		tfield_email.setBounds(139, 172, 129, 19);
		panel1_admin.add(tfield_email);
		
		tfield_lastname = new JTextField();
		tfield_lastname.setColumns(10);
		tfield_lastname.setBounds(387, 112, 141, 19);
		panel1_admin.add(tfield_lastname);
		
		JLabel lblNewLabel1_password_2_1 = new JLabel("Address:");
		lblNewLabel1_password_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel1_password_2_1.setBounds(298, 148, 73, 14);
		panel1_admin.add(lblNewLabel1_password_2_1);
		
		tfield_address = new JTextField();
		tfield_address.setColumns(10);
		tfield_address.setBounds(387, 143, 141, 19);
		panel1_admin.add(tfield_address);
		JButton btnAddAccount = new JButton("Add account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfield_username.getText();
				String firstname = tfield_firstname.getText();
				String lastname = tfield_lastname.getText();
				String convert_password = pfield_password.getText();
				String password = BCrypt.hashpw(convert_password, BCrypt.gensalt(12));
				String address = tfield_address.getText();
				String email = tfield_email.getText();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date = df.format(dateChooser.getDate());
				Account account = new Account(username, password, firstname, lastname, date, address, email);
				if (!(username.equals("") || password.equals("") || email.equals(""))) {
					AccountQuery accQuery = new AccountQuery();
					boolean result = accQuery.addAccount(account);
					if (result == false) {
						JOptionPane.showMessageDialog(null, "Fail to add a new account.");
						AccountManagement accountManagementFrame = new AccountManagement();
						accountManagementFrame.setVisible(true);
						accountManagementFrame.showUser();
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "A new account was successfully added to database.");
						AccountManagement accountManagementFrame = new AccountManagement();
						accountManagementFrame.setVisible(true);
						accountManagementFrame.showUser();
						dispose();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Information must not be empty!");
				}
			}
		});
		btnAddAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddAccount.setBounds(388, 221, 136, 21);
		panel1_admin.add(btnAddAccount);
		
		JLabel lblNewLabel_1 = new JLabel("ACCOUNT INFORMATION:");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(32, 20, 214, 40);
		panel1_admin.add(lblNewLabel_1);
		
		JButton btnReturn = new JButton("Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go back to previous page
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturn.setBounds(43, 221, 86, 21);
		panel1_admin.add(btnReturn);
		
		JLabel lblAddAccount = new JLabel("ADD ACCOUNT");
		lblAddAccount.setForeground(new Color(220, 20, 60));
		lblAddAccount.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAddAccount.setBounds(209, 10, 172, 37);
		contentPane.add(lblAddAccount);
		
		JLabel lblNewLabel = new JLabel(frame_bg);
		lblNewLabel.setBounds(0, 0, 586, 363);
		contentPane.add(lblNewLabel);
		
	}
}

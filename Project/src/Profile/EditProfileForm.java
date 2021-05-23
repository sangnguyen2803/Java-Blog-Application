package Profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
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

import AccountManagement.Account;
import AccountManagement.AccountManagement;
import AccountManagement.AccountQuery;
import HashPw.BCrypt;
import User.Users;

import javax.swing.JMenuBar;

public class EditProfileForm extends JFrame {
	static Users user;
	private JPanel contentPane;
	private JTextField tfield_username;
	private JPasswordField pfield_password;
	private JTextField tfield_firstname;
	private JTextField tfield_email;
	private JTextField tfield_lastname;
	private JTextField tfield_address;
	private ImageIcon frame_bg = new ImageIcon("src/FrameImages/frame_bg.jpg");
	private JTextField tfield_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfileForm frame = new EditProfileForm(user);
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
	
	public void showProfileInfo(String id) {
		Account account = new Account();
		AccountQuery accQuery = new AccountQuery();
		account = accQuery.getAllAccount(id);
		tfield_username.setText(account.getUsername());
		pfield_password.setText(account.getPassword());
		tfield_firstname.setText(account.getFirstName());
		tfield_lastname.setText(account.getLastName());
		tfield_email.setText(account.getEmail());
		tfield_address.setText(account.getAddress());
		tfield_username.setEditable(false);
		tfield_firstname.setEditable(true);
		tfield_lastname.setEditable(true);
		tfield_email.setEditable(true);
		tfield_address.setEditable(true);
		pfield_password.setEditable(true);
		
	}
	public EditProfileForm(Users u) {
		user = new Users(u);
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
		lblNewLabel1_username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_username.setBounds(28, 52, 106, 14);
		panel1_admin.add(lblNewLabel1_username);

		tfield_username = new JTextField();
		tfield_username.setEditable(false);
		tfield_username.setColumns(10);
		tfield_username.setBounds(123, 52, 141, 19);
		panel1_admin.add(tfield_username);

		JLabel lblNewLabel1_password = new JLabel("Password:");
		lblNewLabel1_password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_password.setBounds(28, 84, 106, 14);
		panel1_admin.add(lblNewLabel1_password);

		pfield_password = new JPasswordField();
		pfield_password.setEditable(false);
		pfield_password.setBounds(123, 81, 141, 19);
		panel1_admin.add(pfield_password);

		JLabel lblNewLabel1_password_1 = new JLabel("Date Of Birth:");
		lblNewLabel1_password_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_password_1.setBounds(28, 115, 106, 14);
		panel1_admin.add(lblNewLabel1_password_1);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 0);
		JDateChooser dateChooser = new JDateChooser(c.getTime());
		dateChooser.setBounds(123, 110, 141, 19);
		panel1_admin.add(dateChooser);

		JLabel lblNewLabel1_password_1_1 = new JLabel("First name:");
		lblNewLabel1_password_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_password_1_1.setBounds(294, 52, 106, 14);
		panel1_admin.add(lblNewLabel1_password_1_1);

		tfield_firstname = new JTextField();
		tfield_firstname.setEditable(false);
		tfield_firstname.setColumns(10);
		tfield_firstname.setBounds(383, 52, 141, 19);
		panel1_admin.add(tfield_firstname);

		JLabel lblNewLabel1_password_2 = new JLabel("Lastname:");
		lblNewLabel1_password_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_password_2.setBounds(294, 84, 73, 14);
		panel1_admin.add(lblNewLabel1_password_2);

		JLabel lblNewLabel1_password_1_2 = new JLabel("Email:");
		lblNewLabel1_password_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_password_1_2.setBounds(28, 144, 106, 14);
		panel1_admin.add(lblNewLabel1_password_1_2);

		tfield_email = new JTextField();
		tfield_email.setEditable(false);
		tfield_email.setColumns(10);
		tfield_email.setBounds(123, 139, 141, 19);
		panel1_admin.add(tfield_email);

		tfield_lastname = new JTextField();
		tfield_lastname.setEditable(false);
		tfield_lastname.setColumns(10);
		tfield_lastname.setBounds(383, 79, 141, 19);
		panel1_admin.add(tfield_lastname);

		JLabel lblNewLabel1_password_2_1 = new JLabel("Address:");
		lblNewLabel1_password_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel1_password_2_1.setBounds(294, 115, 73, 14);
		panel1_admin.add(lblNewLabel1_password_2_1);

		tfield_address = new JTextField();
		tfield_address.setEditable(false);
		tfield_address.setColumns(10);
		tfield_address.setBounds(383, 110, 141, 19);
		panel1_admin.add(tfield_address);

		JButton btnUpdateAccount = new JButton("Update account");
		btnUpdateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfield_username.getText();
				String convert_password = pfield_password.getText();
				String password = BCrypt.hashpw(convert_password, BCrypt.gensalt(12));
				String firstname = tfield_firstname.getText();
				String lastname = tfield_lastname.getText();
				String address = tfield_address.getText();
				String email = tfield_email.getText();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String _date = df.format(dateChooser.getDate());
				Account updatedAccount = new Account(username, password, firstname, lastname, _date, address, email);
				updatedAccount.setId(user.getUserID());
				if (!(username.equals("") || email.equals("") || firstname.equals(""))) {
					AccountQuery accQuery = new AccountQuery();
					boolean result = accQuery.updateAccount(updatedAccount);
					if (result == false) {
						JOptionPane.showMessageDialog(null, "Fail to update a new account.");
					}
					else {
						JOptionPane.showMessageDialog(null, "The account was successfully updated in database.");
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Information must not be empty!");
				}
			}
		});
		btnUpdateAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateAccount.setBounds(388, 221, 136, 21);
		panel1_admin.add(btnUpdateAccount);
		
		JButton btnReturn = new JButton("Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileBlogPublic profileBlog = new ProfileBlogPublic(u);
				profileBlog.setVisible(true);
				profileBlog.showInfo();
				profileBlog.showBlog();
				EditProfileForm.this.dispose();
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturn.setBounds(42, 221, 86, 21);
		panel1_admin.add(btnReturn);

		JLabel lblAddAccount = new JLabel("EDIT PROFILE");
		lblAddAccount.setForeground(new Color(220, 20, 60));
		lblAddAccount.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAddAccount.setBounds(212, 10, 217, 37);
		contentPane.add(lblAddAccount);

		JLabel lblNewLabel = new JLabel(frame_bg);
		lblNewLabel.setBounds(0, 0, 586, 363);
		contentPane.add(lblNewLabel);
		showProfileInfo(String.valueOf(user.getUserID()));
	}
}

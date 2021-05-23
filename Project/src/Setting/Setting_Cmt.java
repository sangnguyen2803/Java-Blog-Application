package Setting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Blog.Blogs;
import Interactive.Interactives;
import LoginDesign.FrameLogin;
import PageHome.PageHome;
import Profile.ProfileBlogPublic;
import Query.QuerySQL;
import User.Users;

import javax.swing.JTextArea;

public class Setting_Cmt extends JFrame {

	Users user;
	int blogID;
	
	//////
	
	private JPanel contentPane;
	JList<String> list;
	QuerySQL squery;
	
	JRadioButton rdbtnNewRadioButton;
	JRadioButton rdbtnNotEdit;
	JRadioButton rdbtnCanDelete;
	JRadioButton rdbtnNotDelete;
	
	Boolean state_Edit;
	Boolean state_Cmt;
	Boolean state_Delete;
	int cmt_Id;
	private JTextField textField;
	ArrayList<Interactives> list_Cmt = new ArrayList<Interactives>();
	JTextArea textArea;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Setting_Cmt frame = new Setting_Cmt();
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
	public Setting_Cmt(Users u, int blogId) {
		this.user = u;
		this.blogID = blogId;
		
		this.setTitle("Setting Blog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 642, 60);
		panel.setBorder(new LineBorder(new Color(0, 255, 255), 0));
		contentPane.add(panel);
		panel.setLayout(null);
		String usname = user.getUsername() +"";
		
		JLabel lblNewLabel = new JLabel("Username: " + usname);
		lblNewLabel.setBounds(10, 10, 169, 30);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Page home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PageHome pageHome = new PageHome(user); 
				pageHome.setVisible(true);
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(51, 255, 255));
		btnNewButton.setBounds(204, 5, 114, 40);
		panel.add(btnNewButton);
		
		JButton btnPagePersonnel = new JButton("Page personnel");
		btnPagePersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ProfileBlogPublic frame = new ProfileBlogPublic(user);
				frame.setVisible(true);
				frame.showInfo();
				frame.showBlog();
			}
		});
		btnPagePersonnel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPagePersonnel.setBackground(new Color(255, 204, 153));
		btnPagePersonnel.setBounds(349, 5, 114, 40);
		panel.add(btnPagePersonnel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FrameLogin lg = new FrameLogin();
				lg.setVisible(true);
			}
		});
		btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogout.setBackground(new Color(255, 255, 153));
		btnLogout.setBounds(504, 5, 114, 40);
		panel.add(btnLogout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 112, 204, 253);
		contentPane.add(scrollPane);
		
		squery = new QuerySQL();
		squery.getAllCmt(this.blogID);
		list_Cmt.clear();
		for(int i = 0; i < squery.getCmt().size(); i++) {
			list_Cmt.add(squery.getCmt().get(i));
		}
		
		String[] list_cmt = new String[list_Cmt.size()];
		for(int i = 0; i < list_Cmt.size(); i++) {
			list_cmt[i] = list_Cmt.get(i).get_Username();
		}
		list = new JList(list_cmt);
		list.setBorder(new LineBorder(new Color(152, 251, 152)));
		scrollPane.setViewportView(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(175, 238, 238)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(201, 112, 441, 253);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Edit");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 79, 90, 24);
		panel_1.add(lblNewLabel_1);
		
		rdbtnNewRadioButton = new JRadioButton("Can edit");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		rdbtnNewRadioButton.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setBounds(77, 109, 103, 21);
		panel_1.add(rdbtnNewRadioButton);
		
		rdbtnNotEdit = new JRadioButton("Not edit");
		rdbtnNotEdit.setBackground(new Color(255, 255, 255));
		rdbtnNotEdit.setBounds(77, 132, 103, 21);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNotEdit);
		panel_1.add(rdbtnNotEdit);
		
		
		ButtonGroup group_cmt = new ButtonGroup();
		
		JLabel lblNewLabel_1_2 = new JLabel("Delete");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(10, 159, 90, 24);
		panel_1.add(lblNewLabel_1_2);
		
		rdbtnCanDelete = new JRadioButton("Can delete");
		rdbtnCanDelete.setBackground(Color.WHITE);
		rdbtnCanDelete.setBounds(77, 189, 103, 21);
		panel_1.add(rdbtnCanDelete);
		
		rdbtnNotDelete = new JRadioButton("Not delete");
		rdbtnNotDelete.setBackground(Color.WHITE);
		rdbtnNotDelete.setBounds(77, 212, 103, 21);
		
		ButtonGroup group_delete = new ButtonGroup();
		group_delete.add(rdbtnCanDelete);
		group_delete.add(rdbtnNotDelete);
		
		panel_1.add(rdbtnNotDelete);
		
		JLabel lblNewLabel_2 = new JLabel("Body");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 10, 90, 29);
		panel_1.add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(110, 15, 250, 64);
		panel_1.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setEnabled(false);
//		textArea.setPreferredSize(new Dimension(240, 50));
//		scrollPane_1.setPreferredSize(new Dimension(250,50));
		scrollPane_1.setViewportView(textArea);
		
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(173, 216, 230)));
		panel_2.setBounds(0, 59, 642, 51);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Get all comment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_Cmt.clear();
				for(int i = 0; i < squery.getCmt().size(); i++) {
					list_Cmt.add(squery.getCmt().get(i));
				}
				
				String[] list_blog = new String[list_Cmt.size()];
				for(int i = 0; i < list_Cmt.size(); i++) {
					list_blog[i] = list_Cmt.get(i).get_Username();
				}
				list.setListData(list_blog);
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(new Color(245, 245, 220));
		btnNewButton_1.setBounds(10, 10, 189, 31);
		panel_2.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(213, 10, 281, 31);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_Cmt.clear();
				if(textField.getText().length() > 0) {
					String s = textField.getText();
					for(int i = 0; i < squery.getCmt().size(); i++) {
						if(squery.getCmt().get(i).get_Username().toLowerCase().contains(s.toLowerCase())) {
							list_Cmt.add(squery.getCmt().get(i));
						}
					}
					String[] list_blog = new String[list_Cmt.size()];
					for(int i = 0; i < list_Cmt.size(); i++) {
						list_blog[i] = list_Cmt.get(i).get_Username();
					}
					list.setListData(list_blog);
					
				}
				else {
					list_Cmt.clear();
					for(int i = 0; i < squery.getCmt().size(); i++) {
						list_Cmt.add(squery.getCmt().get(i));
					}
					
					String[] list_blog = new String[list_Cmt.size()];
					for(int i = 0; i < list_Cmt.size(); i++) {
						list_blog[i] = list_Cmt.get(i).get_Username();
					}
					list.setListData(list_blog);
				}
			}
		});
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBackground(new Color(135, 206, 250));
		btnNewButton_2.setBounds(504, 10, 112, 31);
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Save");
	
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBackground(new Color(135, 206, 250));
		btnNewButton_3.setBounds(386, 369, 85, 44);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Delete");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cmt_Id > 0) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this cmt?", "Confirmation",JOptionPane.YES_NO_OPTION) == 0){
						try {
							Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
							String DB_URL = "jdbc:sqlserver://localhost:62673;databaseName=Social_Network;integratedSecurity=true;";
				    		Connection conn = DriverManager.getConnection(DB_URL);					
				    		Statement stmt = conn.createStatement();
				    		String delete = "Delete from Comment Where CommentID = " + cmt_Id;
				    		stmt.executeUpdate(delete);
				    		
				    		squery.getAllCmt(blogID);
				    		list_Cmt.clear();
				    		for(int i = 0; i < squery.getCmt().size(); i++) {
				    			list_Cmt.add(squery.getCmt().get(i));
				    		}
				    		String[] list_blog = new String[list_Cmt.size()];
							for(int i = 0; i < list_Cmt.size(); i++) {
								list_blog[i] = list_Cmt.get(i).get_Username();
							}
							list.setListData(list_blog);
				    		
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
	    		
			}
		});
		btnNewButton_3_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3_1.setBackground(new Color(255, 69, 0));
		btnNewButton_3_1.setBounds(10, 369, 85, 44);
		contentPane.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("Back");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Setting_Cmt.this.dispose();
				Setting_Blog st = new Setting_Blog(user);
				st.setVisible(true);
			}
		});
		btnNewButton_3_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3_2.setBackground(new Color(238, 232, 170));
		btnNewButton_3_2.setBounds(105, 369, 85, 44);
		contentPane.add(btnNewButton_3_2);
		
		
		
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = list.getSelectedIndex();
				cmt_Id = list_Cmt.get(idx).get_CmtID();
				if (idx != -1)
				{
					Border border = BorderFactory.createTitledBorder(list_Cmt.get(idx).get_Date());
					textArea.setText(list_Cmt.get(idx).get_Body());
					panel_1.setBorder(border);
					if(list_Cmt.get(idx).get_EditCmt()) {
						rdbtnNewRadioButton.setSelected(true);
						state_Edit = true;
					}
					else {
						rdbtnNotEdit.setSelected(true);
						state_Edit = false;
					}
					
					if(list_Cmt.get(idx).get_DeleteCmt()) {
						rdbtnCanDelete.setSelected(true);
						state_Delete = true;
					}
					else {
						rdbtnNotDelete.setSelected(true);
						state_Delete = false;
					}
				}
				
			}
			
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdbtnNewRadioButton.isSelected()) {
					if(!state_Edit) {
						squery.SetEditCmt(cmt_Id);
					}
				}
				else {
					if(state_Edit) {
						squery.SetEditCmt(cmt_Id);
					}
				}
				if(rdbtnCanDelete.isSelected()) {
					if(!state_Delete) {
						squery.SetDeleteCmt(cmt_Id);
					}
				}
				else {
					if(state_Delete) {
						squery.SetDeleteCmt(cmt_Id);
					}
				}
				list_Cmt.clear();
				for(int i = 0; i < squery.getCmt().size(); i++) {
					list_Cmt.add(squery.getCmt().get(i));
				}
				
				String[] list_blog = new String[list_Cmt.size()];
				for(int i = 0; i < list_Cmt.size(); i++) {
					list_blog[i] = list_Cmt.get(i).get_Username();
				}
				list.setListData(list_blog);
				
			}
		});
	}
}

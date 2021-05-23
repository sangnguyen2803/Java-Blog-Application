package Blog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.time.*;

import User.Users;
import Blog.*;
import Status.BlogStatus; 

public class postBlogFrame extends JFrame implements ActionListener {
	
	JTextField title = new JTextField(20); 
	JTextArea body = new JTextArea(3, 20);
	static Users user; 
	
	public postBlogFrame(Users u) {
		user = u; 
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(500, 350); 
		setBounds(700, 200, 500, 350); 
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS)); 
		titlePanel.add(new JLabel("Title: ")); 
		titlePanel.add(title); 
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS)); 
		bodyPanel.add(new JLabel("Body: ")); 
		bodyPanel.add(body); 
		body.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int countLine = 1; 
				JTextArea bodyText = (JTextArea) e.getSource(); 
				String text = bodyText.getText(); 
				if (text.length() % 30 == 0 && text.length() != 0) {
					text += "\n"; 
					countLine++; 
				}
				if (countLine > 3) {
					int newHeight = getContentPane().getHeight() + 50; 
					postBlogFrame.this.setSize(500, newHeight); 
					postBlogFrame.this.setBounds(700, 200, 500, newHeight); 
					postBlogFrame.this.revalidate();
					postBlogFrame.this.repaint(); 
					
				}
				
				bodyText.setText(text);
			}
		});
		
		JButton button = new JButton("OK"); 
		button.addActionListener(this);
		
		
		add(titlePanel); 
		add(Box.createRigidArea(new Dimension(0, 5))); 
		add(bodyPanel); 
		add(button); 
		
		pack();
		setVisible(true); 
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand(); 
		if (command.equals("OK")) {
			String titleText = title.getText(); 
			String bodyText = body.getText(); 
			
			if (!titleText.equals("") && !bodyText.equals("")) {
				LocalDate date = LocalDate.now(); 
				BlogStatus status = new BlogStatus();
				
				Blogs blog = new Blogs(title.getText(), user.getUsername(), body.getText(), true, false, 
										String.valueOf(date), false, status); 
				user.PostBlog(blog); 
				
				// reset field after submission
				title.setText("");
				body.setText("");
				dispose(); 
			}
			else {
				System.out.println("Missing title or body"); 
			}
		}
	}

}

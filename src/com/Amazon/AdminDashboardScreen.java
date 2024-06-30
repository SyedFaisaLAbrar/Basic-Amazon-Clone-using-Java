package com.Amazon;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AdminDashboardScreen extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigationManager navigationManager;
    private userDetails USER;
    String cartPath="";
    public AdminDashboardScreen(NavigationManager navigationManager,userDetails USER, String cartPath) {
        this.navigationManager = navigationManager;
        this.USER = USER;
        this.cartPath= cartPath;
        setTitle("Admin Dashboard");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
    	JLabel logoLabel = new JLabel("");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/logo.png"));
		// Get the image and scale it
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(80, 27, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledImg));
		logoLabel.setBounds(600, 10, 143, 34);
		panel.add(logoLabel);
		
        JLabel navbar = new JLabel("");
		navbar.setBackground(new Color(0, 0, 0));
		navbar.setForeground(new Color(255, 255, 255));
		navbar.setBounds(0, 0, 1280, 48);
		navbar.setOpaque(true);
		panel.add(navbar);
		
		JLabel cartLabel = new JLabel("List New Product");
		cartLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 28));
		cartLabel.setBounds(120, 146, 270, 48);
		panel.add(cartLabel);

		
		JLabel lblNewLabel_3 = new JLabel("Product Name  ");
		lblNewLabel_3.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(141, 256, 127, 27);
		panel.add(lblNewLabel_3);
		
		JTextField pnameInput = new JTextField();
		pnameInput.setFont(new Font("Amazon Ember Mono", Font.PLAIN, 16));
		pnameInput.setBounds(375, 257, 190, 30);
		panel.add(pnameInput);
		////////////////////////////////////////////////////////////////
		JLabel cardLabel = new JLabel("Price  ");
		cardLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 17));
		cardLabel.setBounds(141, 300, 127, 27);
		panel.add(cardLabel);
			
		JTextField priceInput = new JTextField();
		priceInput.setFont(new Font("Amazon Ember Mono", Font.PLAIN, 16));
		priceInput.setBounds(375, 300, 190, 30);
		panel.add(priceInput);
		
		JLabel pathLabel = new JLabel("Path  ");
		pathLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 17));
		pathLabel.setBounds(141, 344, 127, 27);
		panel.add(pathLabel);
		
		JTextField pathInput = new JTextField();
		pathInput.setFont(new Font("Amazon Ember Mono", Font.PLAIN, 16));
		pathInput.setBounds(375, 344, 190, 30);
		panel.add(pathInput);
        
        
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigationManager.showHomeScreen(USER, cartPath,false);
                dispose();
			}
		});
		btnBack.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setOpaque(true);
		btnBack.setFocusPainted(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(290, 440, 127, 34);
		panel.add(btnBack);
		
		JButton btnAdd = new JButton("List Product");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                    // Specify the path to create the file two directories up
	                    FileWriter writer = new FileWriter("productslist.csv", true);
	                    writer.write(pnameInput.getText() + ","+ priceInput.getText() +"," +pathInput.getText()+ "\n");
	                    writer.close();
	                } catch (IOException f) {
	                    System.out.println("An error occurred.");
	                    f.printStackTrace();
	                }
				JOptionPane.showMessageDialog(null, "Product successfully Listed on Amazon!");
			}
		});
		btnAdd.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		btnAdd.setBackground(new Color(245, 200, 71));
		btnAdd.setOpaque(true);
		btnAdd.setFocusPainted(false);
		btnAdd.setBorderPainted(false);
		btnAdd.setBounds(435, 440, 127, 34);

		panel.add(btnAdd);
		
		JLabel lblNewLabel_b = new JLabel("");
		lblNewLabel_b.setBounds(120, 226, 527, 280);
		lblNewLabel_b.setBackground(new Color(255, 255, 255));
		lblNewLabel_b.setOpaque(true);
		panel.add(lblNewLabel_b);
		
		JLabel userImage = new JLabel("");
		userImage.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/amazonuser.png"));
		// Get the image and scale it
        Image img1 = icon1.getImage();
        Image scaledImg1 = img1.getScaledInstance(500, 470, Image.SCALE_SMOOTH);
        userImage.setIcon(new ImageIcon(scaledImg1));
		userImage.setBounds(660, 170, 574, 470);
		panel.add(userImage);
    }
}

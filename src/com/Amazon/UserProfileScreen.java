package com.Amazon;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileScreen extends JFrame {
    private NavigationManager navigationManager;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField addressField;
    private userDetails USER;
    String cartPath="";
    public UserProfileScreen(NavigationManager navigationManager, userDetails USER, String cartPath) {
        this.navigationManager = navigationManager;
        this.USER = USER;
        this.cartPath= cartPath;
        setTitle("User Profile");
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
		
		JLabel cartLabel = new JLabel("User Details");
		cartLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 28));
		cartLabel.setBounds(120, 146, 270, 48);
		panel.add(cartLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigationManager.showHomeScreen(USER, cartPath,false);
                dispose();
			}
		});
		btnBack.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		btnBack.setBackground(new Color(245, 193, 71));
		btnBack.setOpaque(true);
		btnBack.setFocusPainted(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(120, 560, 127, 34);

		panel.add(btnBack);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Username        |");
		lblNewLabel_3.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(141, 256, 127, 27);
		panel.add(lblNewLabel_3);
		
		JLabel cardLabel = new JLabel("Country            |");
		cardLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 17));
		cardLabel.setBounds(141, 340, 127, 27);
		panel.add(cardLabel);
		
		JLabel lblNewLabel_3_1 = new JLabel(USER.getUserName());
		lblNewLabel_3_1.setFont(new Font("Amazon Ember Mono", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(399, 257, 178, 27);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel(USER.getUserCountry());
		lblNewLabel_3_1_1.setFont(new Font("Amazon Ember Mono", Font.PLAIN, 16));
		lblNewLabel_3_1_1.setBounds(399, 341, 178, 27);
		panel.add(lblNewLabel_3_1_1);
		
		String countryFlagp = "/" + USER.getUserCountry() +".png";
		JLabel countryFlag = new JLabel("");
		countryFlag.setBounds(490, 349, 46, 14);
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource(countryFlagp));
		// Get the image and scale it
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH); // scale to 113x37
        countryFlag.setIcon(new ImageIcon(scaledImg2));
        panel.add(countryFlag);
        
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
        Image scaledImg1 = img1.getScaledInstance(520, 470, Image.SCALE_SMOOTH);
        userImage.setIcon(new ImageIcon(scaledImg1));
		userImage.setBounds(660, 170, 574, 470);
		panel.add(userImage);
    }
}

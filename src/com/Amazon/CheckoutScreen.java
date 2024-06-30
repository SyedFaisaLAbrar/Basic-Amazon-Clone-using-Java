package com.Amazon;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutScreen extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigationManager navigationManager;
    private userDetails USER;
    String cartPath="";
    int totalPrice = 0;
    public CheckoutScreen(NavigationManager navigationManager,userDetails USER , String cartPath, int totalPrice) {
        this.navigationManager = navigationManager;
        this.cartPath= cartPath;
        this.USER = USER;
        this.totalPrice = totalPrice;
        setTitle("Checkout");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        placeComponents(panel);

        setVisible(true);
    }

    @SuppressWarnings("null")
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
		
		JLabel cartLabel = new JLabel("Payment Details");
		cartLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 28));
		cartLabel.setBounds(556, 125, 270, 48);
		panel.add(cartLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sub Total : ");
		lblNewLabel_1.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(703, 540, 108, 34);
		panel.add(lblNewLabel_1);
		
		JLabel priceLabel = new JLabel(Integer.toString(totalPrice));
		priceLabel.setFont(new Font("Amazon Ember Medium", Font.BOLD, 20));
		priceLabel.setBounds(831, 543, 105, 27);
		panel.add(priceLabel);
		
		JLabel lblNewLabel_2 = new JLabel("$");
		lblNewLabel_2.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(809, 543, 12, 27);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Pay Now");
		btnNewButton.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		btnNewButton.setBounds(809, 599, 127, 34);
		btnNewButton.setBackground(new Color(245, 193, 71));
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////Exception handeling is remaining
				navigationManager.showHomeScreen(USER,cartPath,true);
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigationManager.showHomeScreen(USER, cartPath,false);
                dispose();
			}
		});
		btnBack.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		btnBack.setBounds(668, 599, 127, 34);
		btnBack.setFocusPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		panel.add(btnBack);
		
		
		JLabel lblNewLabel_3 = new JLabel("Account Title");
		lblNewLabel_3.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(403, 257, 98, 27);
		panel.add(lblNewLabel_3);
		
		JTextField accountnameTxt = new JTextField();
		accountnameTxt.setBounds(403, 282, 289, 27);
		panel.add(accountnameTxt);
		accountnameTxt.setColumns(10);
		
		JLabel cardLabel = new JLabel("Card Number");
		cardLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		cardLabel.setBounds(403, 326, 98, 27);
		panel.add(cardLabel);
		
		JTextField cardTxt = new JTextField();
		cardTxt.setColumns(10);
		cardTxt.setBounds(403, 353, 289, 27);
		panel.add(cardTxt);
		
		JLabel pngStrip = new JLabel("");
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/PngItem_2918799.png"));
		// Get the image and scale it
        Image img1 = icon1.getImage();
        Image scaledImg1 = img1.getScaledInstance(289, 64, Image.SCALE_SMOOTH);
        pngStrip.setIcon(new ImageIcon(scaledImg1));
		pngStrip.setBounds(403, 424, 289, 64);
		panel.add(pngStrip);
		
		JLabel lblNewLabel_3_1 = new JLabel("CVV");
		lblNewLabel_3_1.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(751, 257, 98, 27);
		panel.add(lblNewLabel_3_1);
		
		JTextField cvvTxt = new JTextField();
		cvvTxt.setColumns(10);
		cvvTxt.setBounds(751, 282, 117, 27);
		panel.add(cvvTxt);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Expirey Date");
		lblNewLabel_3_1_1.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		lblNewLabel_3_1_1.setBounds(751, 326, 98, 27);
		panel.add(lblNewLabel_3_1_1);
		
		JTextField expiryTxt = new JTextField();
		expiryTxt.setFont(new Font("Amazon Ember Mono", Font.PLAIN, 13));
		expiryTxt.setText("MM/YY");
		expiryTxt.setColumns(10);
		expiryTxt.setBounds(751, 351, 117, 27);
		panel.add(expiryTxt);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Address");
		lblNewLabel_3_1_2.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		lblNewLabel_3_1_2.setBounds(751, 395, 98, 27);
		panel.add(lblNewLabel_3_1_2);
		
		JTextField addressTxt = new JTextField();
		addressTxt.setFont(new Font("Amazon Ember Mono", Font.PLAIN, 13));
		addressTxt.setColumns(10);
		addressTxt.setBounds(751, 420, 117, 57);
		panel.add(addressTxt);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(360, 211, 576, 309);
		lblNewLabel.setOpaque(true);
		panel.add(lblNewLabel);
    }
}

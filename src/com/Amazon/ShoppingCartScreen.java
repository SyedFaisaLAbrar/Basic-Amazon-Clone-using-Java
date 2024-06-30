package com.Amazon;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ShoppingCartScreen extends JFrame {
    private NavigationManager navigationManager;
    private userDetails USER;
    String cartPath="";
    int totalPrice = 0;
    public ShoppingCartScreen(NavigationManager navigationManager, userDetails USER, String cartPath) {
        this.navigationManager = navigationManager;
        this.USER = USER;
        this.cartPath= cartPath;
        setTitle("Shopping Cart");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        placeComponents(panel);

        setVisible(true);
    }
    
    public void loadCart(JPanel panel) {
    	
    	int postWidth = 536;
        int postHeight = 66;
        int verticalSpacing = 10;
        int startX = 378;
        int startY = 221;
        int labelHeight = 203;
        int photoHeight = 238;
        
        try (BufferedReader br = new BufferedReader(new FileReader(cartPath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	String[] cartDetails = line.split(",");

                String productname = cartDetails[0].trim();
                String productprice = cartDetails[1].trim();
                String productpath = cartDetails[2].trim();
                totalPrice+=Integer.parseInt(productprice);
                // Create and configure the components for the post
                String one="";
                String two="";

                int pointer=0;
                for(int j=0;j<productname.length();j++) {
   
                	if (pointer < 41) {
                        one += productname.charAt(j);
                    } else if (pointer < 63) {
                        two += productname.charAt(j);
                    } 
                    pointer++;
                }
                String linetxt = one + "<br/>" + two;
                String text = String.format("<html>%s</html>", linetxt);
                
                
                JLabel lblNewLabel_3 = new JLabel(text);
        		lblNewLabel_3.setBounds(395, labelHeight, 401, 84);
        		panel.add(lblNewLabel_3);
        		
        		JLabel lblNewLabel_p = new JLabel(" $ "+ productprice);
        		lblNewLabel_p.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 18));
        		lblNewLabel_p.setBounds(700, labelHeight+30, 80, 45);
        		lblNewLabel_p.setBackground(new Color(255, 255, 255));
        		lblNewLabel_p.setOpaque(true);
        		panel.add(lblNewLabel_p);
        		
        		JLabel productImage = new JLabel("");
        		productImage.setBackground(new Color(255, 255, 255));
        		productImage.setBounds(831, photoHeight, 51, 39);
        		ImageIcon icon4 = new ImageIcon(this.getClass().getResource(productpath));
                Image img4 = icon4.getImage();
                Image scaledImg4 = img4.getScaledInstance(51, 39, Image.SCALE_SMOOTH); 
                productImage.setIcon(new ImageIcon(scaledImg4));
                panel.add(productImage);
                
                JLabel lblNewLabel_4 = new JLabel("");
        		lblNewLabel_4.setBackground(new Color(251, 251, 251));
        		lblNewLabel_4.setBounds(startX, startY, postWidth, postHeight);
        		lblNewLabel_4.setOpaque(true);
        		panel.add(lblNewLabel_4);
        		
        		labelHeight = labelHeight + postHeight+ (verticalSpacing/2) + 15;
        		photoHeight = photoHeight + postHeight+ (verticalSpacing/2) + 15;
        		startY = startY + postHeight+verticalSpacing;
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
		
		JLabel cartLabel = new JLabel("Added to Cart");
		cartLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 28));
		cartLabel.setBounds(556, 125, 270, 48);
		panel.add(cartLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sub Total : ");
		lblNewLabel_1.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(703, 540, 108, 34);
		panel.add(lblNewLabel_1);
		
		JLabel priceLabel = new JLabel("0");
		priceLabel.setFont(new Font("Amazon Ember Medium", Font.BOLD, 20));
		priceLabel.setBounds(831, 543, 105, 27);
		panel.add(priceLabel);
		
		JLabel lblNewLabel_2 = new JLabel("$");
		lblNewLabel_2.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(809, 543, 12, 27);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Checkout");
		btnNewButton.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
		btnNewButton.setBounds(809, 599, 127, 34);
		btnNewButton.setBackground(new Color(245, 193, 71));
		btnNewButton.setOpaque(true);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(totalPrice>0)
					navigationManager.showCheckoutScreen(USER,cartPath,totalPrice);
				else
					JOptionPane.showMessageDialog(null, "Process the cart or Add product in the cart first if cart is empty!");
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
		btnBack.setFocusPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setBounds(668, 599, 127, 34);

		panel.add(btnBack);
		
		
		
		///////////////////////////////////////////////////////////////////
		
		loadCart(panel);
		///////////////////////////////////////////////////////////////////////////
        JButton btnProcess = new JButton("Process Price");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priceLabel.setText(Integer.toString(totalPrice));
			}
		});
		btnProcess.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 13));
		btnProcess.setBackground(new Color(245, 193, 71));
		btnProcess.setOpaque(true);
		btnProcess.setBorderPainted(false);
		btnProcess.setBounds(800, 475, 120, 30);
		panel.add(btnProcess);
		
		JButton btnDel = new JButton("Empty Cart");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priceLabel.setText(Integer.toString(totalPrice));
			}
		});
		btnDel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 13));
		btnDel.setBackground(new Color(0, 0, 0));
		btnDel.setForeground(new Color(255, 255, 255));
		btnDel.setBounds(600, 475, 120, 30);
		btnDel.setOpaque(true);
		btnDel.setBorderPainted(false);
		panel.add(btnDel);
		
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    // Specify the path to create the file two directories up
                    FileWriter writer = new FileWriter(cartPath);
                    writer.write("");
                    writer.close();
                    navigationManager.showHomeScreen(USER,cartPath, false);
                    
                    ///////////////////////////////////////////////////////////
                    
                } catch (IOException f) {
                    System.out.println("An error occurred.");
                    f.printStackTrace();
                }
			}
		});
        
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(360, 211, 576, 309);
		lblNewLabel.setOpaque(true);
		panel.add(lblNewLabel);
    }
}

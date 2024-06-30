package com.Amazon;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ProductClicked extends JFrame {
    private NavigationManager navigationManager;
    private userDetails USER;
    String pname, pprice, psource;
    String cartPath="";
    public ProductClicked(NavigationManager navigationManager, userDetails USER,String productname,String productprice,String productpath, String cartPath) {
        this.navigationManager = navigationManager;
        this.USER = USER;
        this.pname = productname;
        this.pprice = productprice;
        this.psource = productpath;
        this.cartPath= cartPath;
        setTitle("Product Listing");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        
        
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
        
        
        
        JLabel productsLabel = new JLabel(pname);
        productsLabel.setForeground(new Color(0, 0, 0));
        productsLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 25));
        productsLabel.setBounds(100, 80, 980, 125);
        panel.add(productsLabel);
        
        JLabel productsImage = new JLabel("");
        ImageIcon icon4 = new ImageIcon(this.getClass().getResource(psource));
        Image img4 = icon4.getImage();
        Image scaledImg4 = img4.getScaledInstance(320, 300, Image.SCALE_SMOOTH); 
        productsImage.setIcon(new ImageIcon(scaledImg4));
        productsImage.setBounds(100, 200, 320, 300);
        panel.add(productsImage);
        
        JButton addButton = new JButton("Add to Cart");
        addButton.setBounds(690, 550, 180, 30);
        addButton.setBackground(new Color(245, 193, 71));
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		
                    // Specify the path to create the file two directories up
                    FileWriter writer = new FileWriter(cartPath, true);
                    writer.write(pname + ","+ pprice +"," +psource+ "\n");
                    writer.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException f) {
                    System.out.println("An error occurred.");
                    f.printStackTrace();
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(470, 550, 180, 30);
        backButton.setBorderPainted(false);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigationManager.showHomeScreen(USER, cartPath, false);
                dispose();
            }
        });
    }
}

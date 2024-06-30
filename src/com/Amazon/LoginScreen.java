package com.Amazon;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends JFrame {
    private NavigationManager navigationManager;
    private userDetails USER;
    private static String country="";
    
    public LoginScreen(NavigationManager navigationManager, userDetails USER) {
        this.navigationManager = navigationManager;
        this.USER = USER;
        setTitle("Login to account");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }
    private Map<String, String> userCredentials;

    public static Map<String, String> readUserCredentials(String filePath) {
        Map<String, String> userCredentials = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
 
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                String username = userDetails[0].trim();
                String password = userDetails[1].trim();
                country = userDetails[2].trim();
                userCredentials.put(username, password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userCredentials;
    }
    
    public void AuthenticationManager(String credentialsFilePath) {
        this.userCredentials = readUserCredentials(credentialsFilePath);
    }

    public boolean verifyCredentials(String username, String password) {
    	
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
    
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Eclipse\\workspace\\com.Amazon\\img\\icons8-amazon-96.png"));
		setTitle("Login to account");
        
		JLabel labl = new JLabel("Login");
        labl.setBounds(610, 235, 90, 30);
        labl.setFont(new Font("Calibri", Font.PLAIN, 30));
        labl.setForeground(new Color(0, 0, 0));
        panel.add(labl);
		
		JLabel logoLabel1 = new JLabel("");
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/logo.png"));
		// Get the image and scale it
        Image img1 = icon1.getImage();
        Image scaledImg1 = img1.getScaledInstance(100, 25, Image.SCALE_SMOOTH); // scale to 113x37
        logoLabel1.setIcon(new ImageIcon(scaledImg1));
		logoLabel1.setBounds(20, 6, 143, 34);
		panel.add(logoLabel1);
		
		JLabel navbar = new JLabel("");
		navbar.setBackground(new Color(0, 0, 0));
		navbar.setForeground(new Color(255, 255, 255));
		navbar.setBounds(0, 0, 1280, 48);
		navbar.setOpaque(true);
		panel.add(navbar);
        
        JLabel logoLabel = new JLabel("");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Amazon-Logo.jpeg"));
		// Get the image and scale it
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(100, 35, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledImg));
		logoLabel.setBounds(590, 140, 143, 34);
		panel.add(logoLabel);
        
        
        
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(520, 330, 80, 25);
        userLabel.setForeground(new Color(0, 0, 0));
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(600, 330, 175, 30);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(520, 370, 80, 25);
        passwordLabel.setForeground(new Color(0, 0, 0));
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(600, 370, 175, 30);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(600, 430, 120, 30);
        loginButton.setBackground(new Color(245, 193, 71));
        loginButton.setOpaque(true);
        panel.add(loginButton);
        
        JButton SignupButton = new JButton("Signup");
        SignupButton.setBounds(600, 470, 120, 30);
        SignupButton.setBackground(new Color(245, 245, 245));
        SignupButton.setOpaque(true);
        panel.add(SignupButton);
        
        JLabel loginBG = new JLabel();
        loginBG.setBounds(490, 100, 310, 450);
        loginBG.setBackground(new Color(250, 250, 250));
//        loginBG.setForeground(new Color(0, 0, 0));
        loginBG.setOpaque(true);
        panel.add(loginBG);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                AuthenticationManager("user_history.csv");
                
                if (verifyCredentials(username,password)) {
                	USER.setUserName(username);
                	USER.setUserCountry(country);
                	String cartPath =USER.getUserName()+"Cart.csv";
                	//creating a file to maintain user's cart.
                	// Normalize and get the absolute path
                    Path path = Paths.get(cartPath).normalize().toAbsolutePath();

                    // Ensure the parent directories exist
                    try {
                        Files.createDirectories(path.getParent());
                    } catch (IOException f) {
                        f.printStackTrace();
                        
                        return;
                    }

                    // Create the file
                    try (FileWriter writer = new FileWriter(path.toFile(), true)) {
                        writer.close();
                        System.out.println("Successfully created the file: " + path);
                    } catch (IOException g) {
                        g.printStackTrace();
                        System.out.println("Failed to create the file.");
                    }
                	///////////////////
                    navigationManager.showHomeScreen(USER, cartPath, false);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!");
                }
            }
        });
        
        SignupButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		USER.setUserName("");
        		USER.setUserCountry("");
        		navigationManager.showSignupScreen(USER);
        	}
        });
        
    }
}
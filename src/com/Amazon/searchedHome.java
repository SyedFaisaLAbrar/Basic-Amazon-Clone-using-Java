package com.Amazon;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class searchedHome extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigationManager navigationManager;
	public String userName = "";
	public userDetails USER;
	String cartPath="";
	int HomeScreenHeight=1520;
	int maxYStart = 570;
	int footerImgYHeight=1230;
	int footerPanelYHeight = 1160;
	int postRowHeight = 300;
	boolean flag=false;
	int counter=0;
	String searchtxt="";
	
//    public searchedHome(NavigationManager navigationManager2, userDetails uSER2, String cartPath2, boolean flag2,
//			String searchtxt2) {
//		// TODO Auto-generated constructor stub
//	}
	public searchedHome(NavigationManager navigationManager,userDetails USER, String cartPath, boolean flag, String s) {
        this.navigationManager = navigationManager;
        this.userName = USER.getUserName();
        this.USER = USER;
        this.cartPath= cartPath;
        this.flag=flag;
        this.searchtxt = s;
        setTitle("Home");
        setSize(1280, HomeScreenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,255,255));
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }
    public void addLabel(JPanel panel, String text, int fontSize, Color color) {
	    JLabel label = new JLabel(text);
	    label.setFont(new Font("Arial", Font.PLAIN, fontSize));
	    label.setForeground(color);
	    panel.add(label);
	}
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1280, HomeScreenHeight));
        /////////////////////////////////PRODUCTS///////////////////////////////////////////////
        
        JLabel nLabel = new JLabel("Latest Products");
        nLabel.setBounds(580, 190, 250, 90);
        nLabel.setForeground(new Color(0, 0, 0));
        nLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 28));
        panel.add(nLabel);
        
        int postWidth = 217;
        int postHeight = 280;
        int horizontalSpacing = 20;
        int verticalSpacing = 20;
        int startX = 69;
        int startY = 270;
        
        // Calculate the number of posts that fit in one row
        int postsPerRow = (1280 - startX) / (postWidth + horizontalSpacing);
        
        int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader("productslist.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
            	
            	String[] postDetails = line.split(",");

                String productname = postDetails[0].trim();
                String productprice = postDetails[1].trim();
                String productpath = postDetails[2].trim();
                
                boolean exists = productname.contains(searchtxt);
                if(exists) {
                	counter+=1;
                	// Calculate the position for the current post
                    int x = startX + (i % postsPerRow) * (postWidth + horizontalSpacing);
                    int y = startY + (i / postsPerRow) * (postHeight + verticalSpacing);
                    i+=1;
                    // Create and configure the components for the post
                    String one="";
                    String two="";
                    String three="";
                    int pointer=0;
                    for(int j=0;j<productname.length();j++) {
       
                    	if (pointer < 21) {
                            one += productname.charAt(j);
                        } else if (pointer < 43) {
                            two += productname.charAt(j);
                        } else {
                            three += productname.charAt(j);
                        }
                        pointer++;
                    }
                    String linetxt = one + "<br/>" + two + "<br/>" + three;
                    String text = String.format("<html>%s</html>", linetxt);
                    
                    JLabel lblNewLabel = new JLabel(text);
                    lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
                    lblNewLabel.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 14));
                    lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
                    lblNewLabel.setForeground(new Color(100, 204, 238));
                    lblNewLabel.setBounds(x + 15, y + 157, 186, 72); // Adjusted position relative to the post panel
                    panel.add(lblNewLabel);
                    
                    JLabel pricetag1 = new JLabel("$ "+ productprice);
                    pricetag1.setFont(new Font("Amazon Ember Medium", Font.PLAIN, 20));
                    pricetag1.setBounds(x + 15, y + 236, 103, 25); // Adjusted position relative to the post panel
                    panel.add(pricetag1);
                    
                    JButton productPicBtn = new JButton("");
                    ImageIcon icon4 = new ImageIcon(this.getClass().getResource(productpath));
                    Image img4 = icon4.getImage();
                    Image scaledImg4 = img4.getScaledInstance(186, 133, Image.SCALE_SMOOTH); // scale to 113x37
                    productPicBtn.setIcon(new ImageIcon(scaledImg4));
                    productPicBtn.setBounds(x + 15, y+10, 186, 133); // Adjusted position relative to the post panel
                    productPicBtn.setBorderPainted(false);
                    panel.add(productPicBtn);
                    
                    productPicBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            navigationManager.showProductClickedScreen(USER, productname, productprice, productpath, cartPath);
                            dispose();
                        }
                    });

                    
                    JLabel postLabel = new JLabel("");
                    postLabel.setForeground(new Color(0, 0, 0));
                    postLabel.setBackground(new Color(255, 255, 255));
                    postLabel.setBounds(x, y, postWidth, postHeight); // Adjusted position for the post panel
                    postLabel.setOpaque(true);
                    panel.add(postLabel);
                    
                   
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the panel to a JScrollPane for better viewing
         JScrollPane scrollPane = new JScrollPane(panel);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         add(scrollPane);
     
        /////////////////////////////////////////////////////////////////////////////
         JLabel searchfail = new JLabel("");
         searchfail.setBounds(470, 500, 500, 25);
         searchfail.setForeground(new Color(0, 0, 0));
         searchfail.setFont(new Font("Amazon Ember Mono", Font.PLAIN, 16));
         panel.add(searchfail);
         if (counter==0) {
        	 
        	 JLabel searchfailimage = new JLabel("");
        	 ImageIcon iconF = new ImageIcon(this.getClass().getResource("/not-found.png"));
             Image imgF = iconF.getImage();
             Image scaledImgF = imgF.getScaledInstance(60, 60, Image.SCALE_SMOOTH); // scale to 113x37
             searchfailimage.setIcon(new ImageIcon(scaledImgF));
        	 searchfailimage.setBounds(630, 410, 60, 60);
        	 searchfailimage.setForeground(new Color(0, 0, 0));
             panel.add(searchfailimage);
             
        	 searchfail.setText("We cannot find any matches for your search.");
         }
         
        ////////////////////////////////////////////////////////////////////////////

        JLabel welcomeLabel = new JLabel("Welcome to Amazon Online Store!");
        welcomeLabel.setBounds(15, 53, 300, 25);
        welcomeLabel.setForeground(new Color(255, 255, 255));
        panel.add(welcomeLabel);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Eclipse\\workspace\\com.Amazon\\img\\icons8-amazon-96.png"));
		setTitle("Online Shopping");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		
		
		JLabel logoLabel = new JLabel("");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/logo.png"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(100, 25, Image.SCALE_SMOOTH); // scale to 113x37
        logoLabel.setIcon(new ImageIcon(scaledImg));
		logoLabel.setBounds(20, 6, 143, 34);
		panel.add(logoLabel);
		
		JLabel CountryLabel = new JLabel("");
		String countryFlag = "/" + USER.getUserCountry() +".png";
	
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource(countryFlag));
		// Get the image and scale it
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH); // scale to 113x37
        CountryLabel.setIcon(new ImageIcon(scaledImg2));
        CountryLabel.setBounds(920, 12, 33, 27);
		panel.add(CountryLabel);
        
		JLabel userLabel = new JLabel("Hello, "+this.userName);
        userLabel.setBounds(970, 11, 80, 29);
        userLabel.setForeground(new Color(249, 199, 49));
        userLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(userLabel);
		
        JButton logout = new JButton("Log out");
        logout.setFont(new Font("Tahoma", Font.BOLD, 12));
        logout.setForeground(new Color(255, 255, 255));
        logout.setBackground(new Color(31, 31, 31));
        logout.setBounds(1057, 11, 89, 29);
        logout.setBorderPainted(false);
        panel.add(logout);
        
		
		JTextField searchinput = new JTextField();
		searchinput.setBounds(427, 10, 364, 27);
		panel.add(searchinput);
		searchinput.setOpaque(true);
		searchinput.setColumns(10);
		
		JButton searchButton = new JButton("");
		ImageIcon search = new ImageIcon(this.getClass().getResource("/find.png"));
		Image s_img = search.getImage();
        Image s_scaledImg = s_img.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        searchButton.setIcon(new ImageIcon(s_scaledImg));
		searchButton.setBackground(new Color(245, 193, 71));
		searchButton.setOpaque(true);
		searchButton.setFocusPainted(false);
		searchButton.setBounds(792, 10, 33, 27);
		searchButton.setBorderPainted(false);
		panel.add(searchButton);
		
		JButton cartButton = new JButton("");
		ImageIcon cart = new ImageIcon(this.getClass().getResource("/searchicon.png"));
		Image c_img = cart.getImage();
        Image c_scaledImg = c_img.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        cartButton.setIcon(new ImageIcon(c_scaledImg));
		cartButton.setBounds(1200, 10, 33, 27);
		cartButton.setBackground(new Color(245, 193, 71));
		cartButton.setOpaque(true);
		cartButton.setBorderPainted(false);
		panel.add(cartButton);
		
		
		JLabel navbar = new JLabel("");
		navbar.setBackground(new Color(0, 0, 0));
		navbar.setForeground(new Color(255, 255, 255));
		navbar.setBounds(0, 0, 1280, 48);
		navbar.setOpaque(true);
		panel.add(navbar);
		
        //////////////////////////////////////////////////////////////////////////////
		
        JButton productListButton = new JButton("Contact Us");
        productListButton.setBounds(1100, 54, 150, 25);
        productListButton.setBackground(new Color(20, 20, 20));
        productListButton.setForeground(new Color(255, 255, 255));
        productListButton.setBorderPainted(false);
        panel.add(productListButton);

        
        JButton userP = new JButton("User Profile");
        userP.setBounds(950, 54, 150, 25);
        userP.setBackground(new Color(20, 20, 20));
        userP.setForeground(new Color(255, 255, 255));
        userP.setBorderPainted(false);
        panel.add(userP);

        
        JButton adminDash = new JButton("Admin Dashboard");
        adminDash.setBounds(800, 54, 150, 25);
        adminDash.setBackground(new Color(20, 20, 20));
        adminDash.setForeground(new Color(255, 255, 255));
        adminDash.setBorderPainted(false);
        panel.add(adminDash);
        
		JLabel navbar2 = new JLabel("");
		navbar2.setBackground(new Color(25, 25, 25));
		navbar2.setForeground(new Color(255, 255, 255));
		navbar2.setBounds(0, 48, 1280, 40);
		navbar2.setOpaque(true);
		panel.add(navbar2);
		
		if (flag) {
			
			JLabel navbar3 = new JLabel("  Order has been Placed. Thank you " + userName+" !");
			navbar3.setBackground(new Color(144, 238, 144));
			navbar3.setForeground(new Color(255, 255, 255));
			navbar3.setFont(new Font("Amazon Ember Medium", Font.BOLD, 13));
			navbar3.setBounds(0,90, 1280, 40);
			navbar3.setOpaque(true);
			panel.add(navbar3);
			//
			Timer timer = new Timer(3000, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	navbar3.setVisible(false); // Hide the label
	            }
	        });
			timer.setRepeats(false); // It Make sure the timer only runs once
	        timer.start();
		}
		
		JLabel BannerLabel = new JLabel("");
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("/banner1.jpg"));
		// Get the image and scale it
        Image img3 = icon3.getImage();
        Image scaledImg3 = img3.getScaledInstance(1280, 100, Image.SCALE_SMOOTH); // scale to 113x37
        BannerLabel.setIcon(new ImageIcon(scaledImg3));
        BannerLabel.setBounds(0, 90, 1280, 100);
		panel.add(BannerLabel);
		
		
		
		////////////////////////////////////////////////
		
		JLabel logoLabel2 = new JLabel("");
		ImageIcon icon22 = new ImageIcon(this.getClass().getResource("/logo.png"));
		// Get the image and scale it
        Image img22 = icon22.getImage();
        Image scaledImg22 = img22.getScaledInstance(100, 25, Image.SCALE_SMOOTH); // scale to 113x37
        logoLabel2.setIcon(new ImageIcon(scaledImg22));
		logoLabel2.setBounds(600, footerImgYHeight, 143, 34);
		panel.add(logoLabel2);
		
		// Footer panel
		JPanel footerPanel = new JPanel();
		footerPanel.setBounds(0, footerPanelYHeight, 1280, 400); // Position the footer starting at y=900px
		footerPanel.setBackground(Color.BLACK);
		footerPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;

		// Section 1
		JPanel section1 = new JPanel();
		section1.setLayout(new BoxLayout(section1, BoxLayout.Y_AXIS));
		section1.setOpaque(false);
		addLabel(section1, "Amazon.com", 16, Color.WHITE);
		addLabel(section1, "Careers", 14, Color.GRAY);
		addLabel(section1, "Blog", 14, Color.GRAY);
		addLabel(section1, "About Amazon", 14, Color.GRAY);
		addLabel(section1, "Investor Relations", 14, Color.GRAY);
		addLabel(section1, "Amazon Devices", 14, Color.GRAY);
		gbc.gridx = 0;
		gbc.gridy = 0;
		footerPanel.add(section1, gbc);

		// Section 2
		JPanel section2 = new JPanel();
		section2.setLayout(new BoxLayout(section2, BoxLayout.Y_AXIS));
		section2.setOpaque(false);
		addLabel(section2, "Make Money with Us", 16, Color.WHITE);
		addLabel(section2, "Sell on Amazon", 14, Color.GRAY);
		addLabel(section2, "Sell Your Services on Amazon", 14, Color.GRAY);
		addLabel(section2, "Sell on Amazon Business", 14, Color.GRAY);
		addLabel(section2, "Sell Your Apps on Amazon", 14, Color.GRAY);
		addLabel(section2, "Become an Affiliate", 14, Color.GRAY);
		gbc.gridx = 1;
		gbc.gridy = 0;
		footerPanel.add(section2, gbc);

		// Section 3
		JPanel section3 = new JPanel();
		section3.setLayout(new BoxLayout(section3, BoxLayout.Y_AXIS));
		section3.setOpaque(false);
		addLabel(section3, "Amazon Payment Products", 16, Color.WHITE);
		addLabel(section3, "Amazon Rewards Visa Signature Cards", 14, Color.GRAY);
		addLabel(section3, "Amazon.com Store Card", 14, Color.GRAY);
		addLabel(section3, "Amazon Business Card", 14, Color.GRAY);
		addLabel(section3, "Amazon.com Corporate Credit Line", 14, Color.GRAY);
		addLabel(section3, "Shop with Points", 14, Color.GRAY);
		gbc.gridx = 2;
		gbc.gridy = 0;
		footerPanel.add(section3, gbc);

		// Section 4
		JPanel section4 = new JPanel();
		section4.setLayout(new BoxLayout(section4, BoxLayout.Y_AXIS));
		section4.setOpaque(false);
		addLabel(section4, "Let Us Help You", 16, Color.WHITE);
		addLabel(section4, "Your Account", 14, Color.GRAY);
		addLabel(section4, "Your Orders", 14, Color.GRAY);
		addLabel(section4, "Shipping Rates & Policies", 14, Color.GRAY);
		addLabel(section4, "Returns & Replacements", 14, Color.GRAY);
		addLabel(section4, "Manage Your Content and Devices", 14, Color.GRAY);
		gbc.gridx = 3;
		gbc.gridy = 0;
		footerPanel.add(section4, gbc);

		// Add footer to the existing panel
		panel.add(footerPanel);

		
		
		//////////////////////////////////////////////////////////////////////
        
		
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigationManager.showShoppingCartScreen(USER, cartPath);
                dispose();
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	searchtxt = searchinput.getText();
            	if(searchtxt.length()>0)
                	navigationManager.showsearchedHomeScreen(USER, cartPath, flag, searchtxt);
            	else
            		navigationManager.showHomeScreen(USER, cartPath, flag);
           }
        });
        
        userP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigationManager.showUserProfileScreen(USER, cartPath);
                dispose();
            }
        });
        
        adminDash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigationManager.showAdminDashboardScreen(USER, cartPath);
                dispose();
            }
        });
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	USER.setUserName("");
            	USER.setUserCountry("");
                navigationManager.showLoginScreen(USER);
                dispose();
            }
        });
    }
}

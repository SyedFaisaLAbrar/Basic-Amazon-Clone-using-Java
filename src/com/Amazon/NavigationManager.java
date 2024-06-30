package com.Amazon;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

public class NavigationManager {
	
	private static userDetails USER = new userDetails();
	
    private JFrame currentFrame;

    public void showLoginScreen(userDetails USER) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        
        currentFrame = new LoginScreen(this, USER);
        currentFrame.setVisible(true);
    }

    public void showHomeScreen(userDetails USER, String cartPath, boolean flag) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new HomeScreen(this, USER, cartPath, flag);
        currentFrame.setVisible(true);
    }
    
    public void showsearchedHomeScreen(userDetails USER, String cartPath, boolean flag, String searchtxt) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new searchedHome(this, USER, cartPath, flag, searchtxt);
        currentFrame.setVisible(true);
    }
    
    public void showProductClickedScreen(userDetails USER,String productname,String productprice,String productpath, String cartPath) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new ProductClicked(this, USER, productname, productprice, productpath, cartPath);
        currentFrame.setVisible(true);
    }

    public void showShoppingCartScreen(userDetails USER, String cartPath) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new ShoppingCartScreen(this, USER, cartPath);
        currentFrame.setVisible(true);
    }


    public void showCheckoutScreen(userDetails USER, String cartPath, int totalPrice) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new CheckoutScreen(this, USER, cartPath, totalPrice); // Pass the cart model to the checkout screen
        currentFrame.setVisible(true);
        
    }

    public void showOrderConfirmationScreen(userDetails USER, String cartPath) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new OrderConfirmationScreen(this, USER, cartPath);
        currentFrame.setVisible(true);
    }


    public void showUserProfileScreen(userDetails USER, String cartPath) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new UserProfileScreen(this, USER, cartPath);
        currentFrame.setVisible(true);
    }

    public void showAdminDashboardScreen(userDetails USER, String cartPath) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new AdminDashboardScreen(this, USER, cartPath);
        currentFrame.setVisible(true);
    }

    public void showSignupScreen(userDetails USER) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new SignupScreen(this, USER);
        currentFrame.setVisible(true);
    }

    public static void main(String[] args) {
        NavigationManager navigationManager = new NavigationManager();
        navigationManager.showLoginScreen(USER);
    }
}

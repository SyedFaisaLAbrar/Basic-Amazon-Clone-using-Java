package com.Amazon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderConfirmationScreen extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigationManager navigationManager;
    private userDetails USER;
    String cartPath="";
    public OrderConfirmationScreen(NavigationManager navigationManager, userDetails USER, String cartPath) {
        this.navigationManager = navigationManager;
        this.USER = USER;
        this.cartPath = cartPath;
        setTitle("Order Confirmation");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        JLabel confirmationLabel = new JLabel(USER.getUserName() + ", Your order has been placed successfully!");
        confirmationLabel.setBounds(10, 20, 300, 25);
        panel.add(confirmationLabel);

        JLabel orderNumberLabel = new JLabel("Order Number: 123456");
        orderNumberLabel.setBounds(10, 50, 300, 25);
        panel.add(orderNumberLabel);

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(10, 80, 150, 25);
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

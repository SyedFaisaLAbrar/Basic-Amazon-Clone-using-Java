package com.Amazon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class demo extends JFrame
{
    private JPanel middlePanel;
    
    /** Creates a new instance of ExpandingFrame */
    public demo()
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        setSize(376,195);
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.RED);
        topPanel.setSize(400,250);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLUE);
        bottomPanel.setSize(200,0);
        
        middlePanel = new JPanel();
        middlePanel.setBackground(Color.WHITE);
        middlePanel.setSize(500, 100);
               
        JButton button = new JButton("Expand");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                getContentPane().add(middlePanel, BorderLayout.CENTER);
                pack();
            }
        }
        );
        
        JButton button2 = new JButton("Contract");
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                remove(middlePanel);
                pack();
            }
        }
        );        
        
        bottomPanel.add(button);
        bottomPanel.add(button2);
        
        getContentPane().setLayout(new BorderLayout());
        
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        pack();
       
    }
    
}
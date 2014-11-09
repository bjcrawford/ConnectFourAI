/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author bcrawford
 */
public class WelcomePanel extends JPanel {
    
    /** Top panel components */
    JPanel topPanel;
    JLabel imageLabel;
    JLabel titleLabel;
    
    /* Spacer component */
    JPanel leftPanel;
    
    /* Center panel components */
    JPanel centerPanel;
    JTextArea introTextArea;
    OptionsPanel optionsPanel;
    
    /* Spacer component */
    JPanel rightPanel;
    
    /* Bottom panel components */
    JPanel bottomPanel;
    JPanel bottomLeftSpacer;
    JPanel buttonPanel;
    JPanel bottomRightSpacer;

    
    public WelcomePanel() {
        
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setPreferredSize(new Dimension(800, 240));
        topPanel.setMaximumSize(new Dimension(800, 240));
        
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(240, 240));
        imageLabel.setMaximumSize(new Dimension(240, 240));
        imageLabel.setIcon(new ImageIcon("cfb.png"));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(560, 240));
        titleLabel.setMaximumSize(new Dimension(560, 240));
        titleLabel.setIcon(new ImageIcon("title.png"));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        topPanel.add(imageLabel);
        topPanel.add(titleLabel);
        
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(40, 310));
        leftPanel.setMaximumSize(new Dimension(40, 310));
        
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setPreferredSize(new Dimension(720, 310));
        centerPanel.setMaximumSize(new Dimension(720, 310));
        
        introTextArea = new JTextArea();
        introTextArea.setEnabled(false);
        introTextArea.setBackground(new Color(0, 0, 0, 0));
        introTextArea.setText("This can hold the intro text");
        introTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
        introTextArea.setPreferredSize(new Dimension(720, 170));
        introTextArea.setMaximumSize(new Dimension(720, 170));
        introTextArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        optionsPanel = new OptionsPanel();
        
        centerPanel.add(introTextArea);
        centerPanel.add(optionsPanel);
        
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(40, 310));
        rightPanel.setMaximumSize(new Dimension(40, 310));
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setPreferredSize(new Dimension(800, 50));
        bottomPanel.setMaximumSize(new Dimension(800, 50));
        
        bottomLeftSpacer = new JPanel();
        bottomLeftSpacer.setPreferredSize(new Dimension(40, 50));
        bottomLeftSpacer.setMaximumSize(new Dimension(40, 50));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.setPreferredSize(new Dimension(720, 50));
        buttonPanel.setMaximumSize(new Dimension(720, 50));
        buttonPanel.add(new StartButton());
        buttonPanel.add(new StartButton());
        buttonPanel.add(new StartButton());
        
        bottomRightSpacer = new JPanel();
        bottomRightSpacer.setPreferredSize(new Dimension(40, 50));
        bottomRightSpacer.setMaximumSize(new Dimension(40, 50));
        
        bottomPanel.add(bottomLeftSpacer);
        bottomPanel.add(buttonPanel);
        bottomPanel.add(bottomRightSpacer);
        
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

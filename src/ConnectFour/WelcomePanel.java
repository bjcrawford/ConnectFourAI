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
    JLabel introLabel;
    JPanel introLabelPanel;
    JPanel optionsLabelsPanel;
    JLabel playerOneLabel;
    JLabel boardSizeLabel;
    JLabel playerTwoLabel;
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
        imageLabel.setIcon(new ImageIcon("res/cfb.png"));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(560, 240));
        titleLabel.setMaximumSize(new Dimension(560, 240));
        titleLabel.setIcon(new ImageIcon("res/title.png"));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        topPanel.add(imageLabel);
        topPanel.add(titleLabel);
        
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(40, 300));
        leftPanel.setMaximumSize(new Dimension(40, 300));
        
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setPreferredSize(new Dimension(720, 300));
        centerPanel.setMaximumSize(new Dimension(720, 300));
        
        introLabelPanel = new JPanel();
        introLabelPanel.setLayout(new BoxLayout(introLabelPanel, BoxLayout.X_AXIS));
        introLabelPanel.setPreferredSize(new Dimension(720, 170));
        introLabelPanel.setMaximumSize(new Dimension(720, 170));
        
        introLabel = new JLabel();
        introLabel.setPreferredSize(new Dimension(720, 170));
        introLabel.setMaximumSize(new Dimension(720, 170));
        introLabel.setIcon(new ImageIcon("res/intro.png"));
        introLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        introLabelPanel.add(introLabel);
        
        optionsLabelsPanel = new JPanel();
        optionsLabelsPanel.setLayout(new BoxLayout(optionsLabelsPanel, BoxLayout.X_AXIS));
        optionsLabelsPanel.setPreferredSize(new Dimension(720, 80));
        optionsLabelsPanel.setMaximumSize(new Dimension(720, 80));
        
        playerOneLabel = new JLabel();
        playerOneLabel.setPreferredSize(new Dimension(260, 80));
        playerOneLabel.setMaximumSize(new Dimension(260, 80));
        playerOneLabel.setIcon(new ImageIcon("res/playerone.png"));
        playerOneLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        boardSizeLabel = new JLabel();
        boardSizeLabel.setPreferredSize(new Dimension(200, 80));
        boardSizeLabel.setMaximumSize(new Dimension(200, 80));
        boardSizeLabel.setIcon(new ImageIcon("res/boardsize.png"));
        boardSizeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        playerTwoLabel = new JLabel();
        playerTwoLabel.setPreferredSize(new Dimension(260, 80));
        playerTwoLabel.setMaximumSize(new Dimension(260, 80));
        playerTwoLabel.setIcon(new ImageIcon("res/playertwo.png"));
        playerTwoLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        optionsLabelsPanel.add(playerOneLabel);
        optionsLabelsPanel.add(boardSizeLabel);
        optionsLabelsPanel.add(playerTwoLabel);
        
        optionsPanel = new OptionsPanel();
        
        centerPanel.add(introLabelPanel);
        centerPanel.add(optionsLabelsPanel);
        centerPanel.add(optionsPanel);
        
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(40, 300));
        rightPanel.setMaximumSize(new Dimension(40, 300));
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setPreferredSize(new Dimension(800, 60));
        bottomPanel.setMaximumSize(new Dimension(800, 60));
        
        bottomLeftSpacer = new JPanel();
        bottomLeftSpacer.setPreferredSize(new Dimension(40, 60));
        bottomLeftSpacer.setMaximumSize(new Dimension(40, 60));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.setPreferredSize(new Dimension(720, 40));
        buttonPanel.setMaximumSize(new Dimension(720, 40));
        buttonPanel.add(new ExitButton(1));
        buttonPanel.add(new InfoButton());
        buttonPanel.add(new StartButton());
        
        bottomRightSpacer = new JPanel();
        bottomRightSpacer.setPreferredSize(new Dimension(40, 60));
        bottomRightSpacer.setMaximumSize(new Dimension(40, 60));
        
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

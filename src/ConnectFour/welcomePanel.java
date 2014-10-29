/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author bcrawford
 */
public class welcomePanel extends JPanel {
    public startButton start;
            
    public welcomePanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        
         //this is just temporary. See main and you will know why I used this.
        paintButton();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoard(g, 20, 20, 8, 8);
        
       
    }
    
    public void paintBoard(Graphics g, int x, int y, int rows, int cols) {
        
        int bgWidth = 200;
        int bgHeight = 200;
        int borderWidth = 10;
        int boardWidth = bgWidth - (2 * borderWidth);
        int boardHeight = bgHeight - (2 * borderWidth);
        
        
        System.out.println("bgWidth: " + bgWidth + "\n");
        System.out.println("bgHieght: " + bgHeight + "\n");
        System.out.println("borderWidth: " + borderWidth + "\n");
        System.out.println("boardWidth: " + boardWidth + "\n");
        System.out.println("boardHeight: " + boardHeight + "\n");
        
        g.setColor(Color.GRAY);
        g.fillRect(x, y, bgWidth, bgHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, bgWidth, bgHeight);
        g.setColor(Color.YELLOW);
        g.fillRect(x + borderWidth,
                   y + borderWidth,
                   boardWidth, 
                   boardHeight);
        
        int rowSep = (boardWidth - borderWidth)/rows;
        int colSep = (boardWidth - borderWidth)/cols;
        
        System.out.println("rowSep: " + rowSep + "\n");
        System.out.println("colSep: " + colSep + "\n");
        
        g.setColor(Color.GRAY);
        for(int i = y + 2 * borderWidth; i < (y + boardHeight); i = i + rowSep)
        {
            
            for(int j = x + 2 * borderWidth; j < (x + boardHeight); j = j + colSep)
            {
                System.out.println("i: " + i + "; j: " + j + "\n");
                g.fillOval(j, i, rowSep - 10, colSep - 10);
            }
        }
    }
    
    public void paintButton(){
        this.start = new startButton();
        add(start);
    }
}

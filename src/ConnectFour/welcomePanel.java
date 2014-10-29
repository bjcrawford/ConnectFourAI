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
import javax.swing.JPanel;

/**
 *
 * @author bcrawford
 */
public class welcomePanel extends JPanel {
    
    public welcomePanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
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
        
        g.setColor(Color.GRAY);
        g.fillRect(x, y, boardWidth, boardHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, boardWidth, boardHeight);
        g.setColor(Color.YELLOW);
        g.fillRect(2 * borderWidth,
                   2 * borderWidth,
                   boardWidth, 
                   boardHeight);
        
        int rowSep = (boardWidth - 2 * borderWidth)/rows;
        int colSep = (boardWidth - 2 * borderWidth)/cols;
        
        g.setColor(Color.GRAY);
        for(int i = 2 * borderWidth; i < (200); i = i + rowSep)
        {
            
            for(int j = 2 * borderWidth; j < (200); j = j + colSep)
            {
                g.fillOval(j, i, rowSep - 10, colSep - 10);
            }
        }
    }
}

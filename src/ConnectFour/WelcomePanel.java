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
public class WelcomePanel extends JPanel {
    
    ConnectFourBoard board;
    StartButton start;

    
    public WelcomePanel(ConnectFourBoard board) {
        this.board = board;
        this.start = new StartButton();
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(start);
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Welcome! Press start to play.", 300, 300);
    }
    
    public void paintButton(){
        this.start = new StartButton();
        add(start);
    }
}

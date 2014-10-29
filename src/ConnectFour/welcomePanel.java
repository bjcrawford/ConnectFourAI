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
    
    ConnectFourBoard myBoard;
    
    public welcomePanel(ConnectFourBoard board) {
        this.myBoard = board;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        myBoard.drawBoard(g, 20, 20);
    }
}

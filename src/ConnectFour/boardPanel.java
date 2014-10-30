/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Beaker
 */
public class boardPanel extends JPanel{
    ConnectFourBoard board;
    
    public boardPanel(ConnectFourBoard board){
        this.board = board;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.drawBoard(g, 20, 20);
    }
}

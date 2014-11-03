/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Beaker
 */
public class EndPanel extends JPanel {
    ConnectFourBoard board;
    
    
    public EndPanel(ConnectFourBoard board, BoardPanel bP, boolean winner){
        this.board = board;
        setLayout(new BorderLayout());
        add(bP, BorderLayout.CENTER);
        if(winner)
            add(new JLabel(new ImageIcon("Red.png")), BorderLayout.NORTH);
        else
            add(new JLabel(new ImageIcon("Black.png")), BorderLayout.NORTH);
        
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

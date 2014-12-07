/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author bcrawford
 */
public class InfoButton extends JButton implements ActionListener {

    public InfoButton() {
        this.addActionListener(this);
        this.setText("More Info");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JFrame info = new JFrame("More Info");
        info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JOptionPane.showMessageDialog(info, 
                "Here is a little more info about the AIs:\n\n" +
                "  AI Random:\n" +
                "    Makes random insertions into the board\n" +
                "  AI Blocker:\n" +
                "    Blocks opponents wins\n" +
                "  AI Minimax 1:\n" +
                "    Uses Minimax with four move look ahead\n" +
                "  AI Minimax 2:\n" +
                "    Optimized Minimax 1, uses weighted scores\n" +
                "  AI AlphaBeta:\n" +
                "    Optimized Minimax 2, reduces state space\n" +
                "  AI Heuristic:\n" +
                "    Optmized Minimax 2, scores non-terminal states\n" +
                "  AI MonteCarlo 1:\n" +
                "    Uses Monte Carlo method to evaluate moves\n" +
                "  AI MonteCarlo 2:\n" +
                "    Optimized MonteCarlo 1, uses concurrency\n" );
        
        info.pack();
        info.setVisible(true);
    }
    
}

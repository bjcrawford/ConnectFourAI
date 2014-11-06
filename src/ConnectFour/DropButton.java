package ConnectFour;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class DropButton extends JButton implements ActionListener {

    int col;
    HumanPlayer player;
    ConnectFourBoard board;

    public DropButton(int col, HumanPlayer player, ConnectFourBoard CFB) {
        
        this.col = col;
        this.player = player;
        this.board = CFB;
        this.setText(col + "");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Calling setNextMove() triggers a release on the waiters 
        // for user input(the main thread)
        player.setNextMove(col - 1);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
    }
}


package ConnectFour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 *
 * @author Beaker
 */
public class GamePanel extends JPanel{
    ConnectFourBoard board;
    BoardPanel bP;
    
    public GamePanel(ConnectFourBoard board){
        this.board = board;
        setLayout(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(1, board.getWidth()));
        for(int i = 1; i < board.getWidth() + 1; i++){
            buttons.add(new DropButton(board, i, "" + i));
        }
        bP = new BoardPanel(board);
        add(buttons, BorderLayout.NORTH);
        add(this.bP, BorderLayout.CENTER);
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

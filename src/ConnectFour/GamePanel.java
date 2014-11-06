
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
public class GamePanel extends JPanel {
    
    ConnectFourBoard board;
    BoardPanel boardPanel;
    JPanel buttonPanel;
    DropButton buttons[]; 
    
    public GamePanel(ConnectFourBoard board){
        
        this.board = board;
        boardPanel = new BoardPanel(board);
        buttonPanel = new JPanel(new GridLayout(1, board.getWidth()));
        buttons = new DropButton[board.getWidth()];
        setLayout(new BorderLayout());
        
        for(int i = 0; i < board.getWidth(); i++) {
            buttons[i] = new DropButton(i + 1, (HumanPlayer) ConnectFour.playerOne, board);
            buttonPanel.add(buttons[i]);
        }
        
        add(buttonPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
    }
    
    /**
     * Updates the drop buttons on the game panel. Columns on
     * the board which are filled will have their corresponding 
     * drop buttons grayed out.
     * 
     */
    public void updateButtons() {
        
        for(int i = 0; i < board.getWidth(); i++)
            buttons[i].setEnabled(!board.isColFull(i));
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

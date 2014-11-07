
package ConnectFour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
/**
 *
 * @author Beaker
 */
public class GamePanel extends JPanel {
    
    ConnectFourBoard board;
    BoardPanel boardPanel;
    JPanel buttonPanel;
    JPanel leftSpacer;
    JPanel rightSpacer;
    DropButton buttons[]; 
    JPanel topPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    
    public GamePanel(ConnectFourBoard board){
        
        this.board = board;
        setLayout(new BorderLayout());
        
        boardPanel = new BoardPanel(board);
        
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setPreferredSize(new Dimension(800, 30));
        topPanel.setMaximumSize(new Dimension(800, 30));
        
        leftSpacer = new JPanel();
        leftSpacer.setPreferredSize(new Dimension(207, 30));
        leftSpacer.setMaximumSize(new Dimension(207, 30));
        
        buttonPanel = new JPanel(new GridLayout(1, board.getWidth()));
        buttonPanel.setPreferredSize(new Dimension(380, 30));
        buttonPanel.setMaximumSize(new Dimension(380, 30));
        
        buttons = new DropButton[board.getWidth()];
        for(int i = 0; i < board.getWidth(); i++) {
            buttons[i] = new DropButton(i + 1, (HumanPlayer) ConnectFour.playerOne, board);
            buttonPanel.add(buttons[i]);
        }
        
        rightSpacer = new JPanel();
        rightSpacer.setPreferredSize(new Dimension(210, 30));
        rightSpacer.setMaximumSize(new Dimension(210, 30));
        
        topPanel.add(leftSpacer);
        topPanel.add(buttonPanel);
        topPanel.add(rightSpacer);
        
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 400));
        leftPanel.setMaximumSize(new Dimension(200, 400));
        
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, 400));
        rightPanel.setMaximumSize(new Dimension(200, 400));
        
        
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
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

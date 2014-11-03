
package ConnectFour;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    static ConnectFourBoard board;
    static JFrame gui;
    static WelcomePanel wp;
    static GamePanel gp;
    static EndPanel ep;
    static AIType1Player ait1;

    public static void main(String args[]) {
        
        board = new ConnectFourBoard(8, 8);
        gui = new JFrame("Connect Four AI");
        wp = new WelcomePanel(board);
        ait1 = new AIType1Player(2, board);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initGUI();
            }
        });
        
    }
    
    private static void initGUI() {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.add(wp);
        gp = new GamePanel(board);
        gui.pack();
        gui.setVisible(true);
        
    }
    
}

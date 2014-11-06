
package ConnectFour;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    static int restart = 0;
    static ConnectFourBoard board;
    static JFrame gui;
    static WelcomePanel wp;
    static GamePanel gp;
    static EndPanel ep;
    static AIType1Player ait1;

    public static void main(String args[]) {
        
        gui = new JFrame("Connect Four AI");
        wp = new WelcomePanel();
        
        // Enter restart loop
        // while(restart)
        
        // Updates to the UI should be be called on the event
        // dispatch thread.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initWelcomePanel();
            }
        });
        
        // Condition variable here to wait for user options input
        
        // These objects should be defined after welcome screen
        // using user input, use condition variables to wait
        board = new ConnectFourBoard(8, 8);
        gp = new GamePanel(board);
        // end panel should be created here
        ait1 = new AIType1Player(2, board);
        
        // use invoke later to make call to init gamepanel
        
        // Begin win loop
        // while(No Win)
        // {
        //     use invokelater to make call to update gamepanel
        //     
        //     if(player one turn)
        //     {
        //         Condition variable here to wait for player one input
        //         insert piece
        //     }
        //     else
        //     {
        //         Condtion variable here to wait for player two input
        //         insert piece
        //     }
        //     check win
        // }
        
        // use invoke later to make call to init end panel
                
        // condition variable here to wait for user restart input
        
        // End restart loop
    }
    
    private static void initWelcomePanel() {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.add(wp);
        gui.pack();
        gui.setVisible(true);
    }
}

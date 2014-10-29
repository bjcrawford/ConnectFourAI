
package ConnectFour;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    static ConnectFourBoard myBoard;
    
    public static void main(String args[]) {
        
        myBoard = new ConnectFourBoard(8, 8);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        
        System.out.println(myBoard);
    }
    
    private static void createAndShowGUI() {
        JFrame gui = new JFrame("Connect Four AI");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.add(new welcomePanel(myBoard));
        gui.pack();
        gui.setVisible(true);
    }
}

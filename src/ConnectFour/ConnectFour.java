
package ConnectFour;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    public static void main(String args[]) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        JFrame gui = new JFrame("Connect Four AI");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomePanel WP = new welcomePanel();
        gui.add(WP);
        gui.pack();
        gui.setVisible(true);
        
    }
}


package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    static ConnectFourBoard myBoard;
    static welcomePanel WP;
    static GamePlayUI GPI;
    static JFrame gui;
    
    public static void main(String args[]) {
        
        myBoard = new ConnectFourBoard(8, 8);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
        
        System.out.println(myBoard);
    }
    
    private static void createAndShowGUI() {
        gui = new JFrame("Connect Four AI");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WP = new welcomePanel(myBoard);
        GPI = new GamePlayUI(myBoard);
        gui.add(WP);
        gui.pack();
        gui.setVisible(true);
        
    }
    
}

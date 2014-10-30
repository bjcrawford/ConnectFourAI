
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
        gui = new JFrame("Connect Four AI");
        WP = new welcomePanel(myBoard);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initGUI();
            }
        });
        
        /*
        for(int i = 0; i < 10; i++)
        {
            if(!myBoard.insertPiece(1, 4))
                System.out.println("Cannot insert piece\n");
        }
        */
        
        System.out.println(myBoard);
        
        myBoard.insertPiece(2, 3);
        myBoard.insertPiece(2, 3);
        myBoard.insertPiece(2, 3);
        myBoard.insertPiece(2, 2);
        myBoard.insertPiece(2, 2);
        myBoard.insertPiece(2, 1);
        
        myBoard.insertPiece(1, 0);
        myBoard.insertPiece(1, 1);
        myBoard.insertPiece(1, 2);
        myBoard.insertPiece(1, 3);
        
        System.out.println(myBoard);
        
        int win = myBoard.checkWin();
        if(win == 1)
            System.out.println("Red Wins!\n");
        else if(win == 2)
            System.out.println("Black wins!\n");
        else
            System.out.println("No winner\n");
    }
    
    private static void createAndShowGUI() {
        gui = new JFrame("Connect Four AI");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WP = new welcomePanel(myBoard);
        GPI = new GamePlayUI(myBoard);
        gui.add(WP);
    }
    
    private static void initGUI() {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.add(WP);
        GPI = new GamePlayUI(myBoard);
        gui.pack();
        gui.setVisible(true);
        
    }
    
}

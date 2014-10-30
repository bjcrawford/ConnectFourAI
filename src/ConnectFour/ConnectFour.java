
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
        
        /*
        myBoard.insertPiece(0);//red
        myBoard.insertPiece(1);//black
        myBoard.insertPiece(1);//red
        myBoard.insertPiece(2);//black
        myBoard.insertPiece(3);//red
        myBoard.insertPiece(2);//black
        myBoard.insertPiece(2);//red
        myBoard.insertPiece(3);//black
        myBoard.insertPiece(4);//red
        myBoard.insertPiece(3);//black
        myBoard.insertPiece(3);//red
        
        
        System.out.println(myBoard);
        */
        
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

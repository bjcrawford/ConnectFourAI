
package ConnectFour;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    static ConnectFourBoard myBoard;
    static JFrame gui;
    static WelcomePanel wp;
    
    public static void main(String args[]) {
        
        myBoard = new ConnectFourBoard(8, 8);
        gui = new JFrame("Connect Four AI");
        wp = new WelcomePanel(myBoard);
        
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
    
    private static void initGUI() {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.add(wp);
        gui.pack();
        gui.setVisible(true);
        
    }
}


package ConnectFour;

import java.util.concurrent.Semaphore;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    static boolean restart;
    static int win;
    static int playerTurn;
    
    static ConnectFourBoard board;
    static AbstractPlayer playerOne;
    static AbstractPlayer playerTwo;
    
    static JFrame gui;
    static WelcomePanel wp;
    static GamePanel gp;
    static EndPanel ep;
    
    static Semaphore userOptionsInputSem;
    static Semaphore userRestartInputSem;

    public static void main(String args[]) throws InterruptedException {
        
        gui = new JFrame("Connect Four AI");
        restart = true;
        userOptionsInputSem = new Semaphore(0); // Start with no user input available
        userRestartInputSem = new Semaphore(0); // Start with no user input available
        
        while(restart)
        {
            restart = false;
            win = 0;
            playerTurn = 1;
            
            // Updates to the UI should be be called on the event
            // dispatch thread using invoke later
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    initWelcomePanel();
                }
            });

            // Synchronization here to wait for user options input
            userOptionsInputSem.acquire();
            System.out.println("User input, start, released main thread");

            // These can be set at runtime when the welcome screen 
            // is built to take user options
            board = new ConnectFourBoard(8, 8);
            playerOne = new HumanPlayer(1, board);
            playerTwo = new AIType1Player(2, 4, board);

            // use invoke later to make call to init gamepanel
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    initGamePanel();
                }
            });

            while(win == 0)
            {
                // use invokelater to make call to update gamepanel
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        updateGamePanel();
                    }
                });
                int col;
                if(playerTurn == 1)
                {
                    col = playerOne.getNextMove();
                    board.insertPiece(playerOne.getPieceColor(), col);
                    playerTurn = 2;
                }
                else
                {
                    col = playerTwo.getNextMove();
                    board.insertPiece(playerTwo.getPieceColor(), col);
                    playerTurn = 1;
                }
                win = board.checkWin();
                gp.updateButtons();
                System.out.println(board);
            }

            // use invoke later to make call to init end panel
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    initEndPanel(win);
                }
            });

            // synchronization here to wait for user restart input
            userRestartInputSem.acquire();
            System.out.println("User input, " + 
                               (restart ? "restart" : "exit") + 
                               ", released main thread");
            
            // use invoke later to make call to destroy end panel
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    destroyEndPanel();
                }
            });

        }
        
        System.exit(0);
    }
    
    private static void initWelcomePanel() {
        
        System.out.println("initWelcomePanel");
        wp = new WelcomePanel();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.add(wp);
        gui.pack();
        gui.setVisible(true);
    }
    
    private static void initGamePanel() {
        
        System.out.println("initGamePanel");
        gp = new GamePanel(board);
        gui.remove(wp);
        gp.buttonPanel.setVisible(true);
        gp.boardPanel.setVisible(true);
        gp.repaint();
        gui.add(gp);
        gui.pack();
        gui.setVisible(true);
    }
    
    private static void updateGamePanel() {
        
        System.out.println("updateGamePanel");
        gui.repaint();
    }
    
    private static void initEndPanel(int win) {
        
        System.out.println("initEndPanel");
        ep = new EndPanel(board, gp.boardPanel, win);
        gui.remove(gp);
        gui.add(ep);
        gui.pack();
    }
    
    private static void destroyEndPanel() {
        
        gui.remove(ep);
    }
}

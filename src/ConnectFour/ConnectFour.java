
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
            System.out.println("User input, start, released main thread\n");

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
                    System.out.println("Player 1 inserts into column " + col + "\n");
                    board.insertPiece(playerOne.getPieceColor(), col);
                    playerTurn = 2;
                }
                else
                {
                    col = playerTwo.getNextMove();
                    System.out.println("Player 2 inserts into column " + col + "\n");
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
                               ", released main thread\n");
            
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
        
        wp = new WelcomePanel();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.getContentPane().add(wp);
        gui.pack();
        gui.setVisible(true);
    }
    
    private static void initGamePanel() {
        
        gp = new GamePanel(board);
        gui.getContentPane().remove(wp);
        gui.getContentPane().add(gp);
        gui.pack();
    }
    
    private static void updateGamePanel() {
        
        gui.repaint();
    }
    
    private static void initEndPanel(int win) {
        
        ep = new EndPanel(board, gp.boardPanel, win);
        gui.getContentPane().remove(gp);
        gui.getContentPane().add(ep);
        gui.pack();
    }
    
    private static void destroyEndPanel() {
        
        gui.getContentPane().remove(ep);
    }
}


package ConnectFour;

import java.util.concurrent.Semaphore;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    static boolean exit;
    static boolean restart;
    static int win;
    static int playerTurn;
    
    static int playerOneSelection;
    static int playerTwoSelection;
    static int boardSizeSelection;
    
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
        exit = false;
        restart = true;
        userOptionsInputSem = new Semaphore(0); // Start with no user input available
        userRestartInputSem = new Semaphore(0); // Start with no user input available
        
        while(restart)
        {
            restart = false;
            win = 0;
            playerTurn = 1;
            playerOneSelection = 0;
            playerTwoSelection = 0;
            
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
            
            if(exit)
                System.exit(0);

            // These can be set at runtime when the welcome screen 
            // is built to take user options
            switch(boardSizeSelection) {
                case 0:
                    board = new ConnectFourBoard(6, 7);
                    break;
                case 1:
                    board = new ConnectFourBoard(7, 7);
                    break;
                case 2:
                    board = new ConnectFourBoard(7, 8);
                    break;
                case 3:
                    board = new ConnectFourBoard(8, 8);
                    break;
                case 4:
                    board = new ConnectFourBoard(8, 9);
                    break;
                case 5:
                    board = new ConnectFourBoard(9, 9);
                    break;
                case 6:
                    board = new ConnectFourBoard(9, 10);
                    break;
                case 7:
                    board = new ConnectFourBoard(10, 10);
                    break;
                default:
                    System.err.println("Invalid boardSizeSelection value.");
                    System.exit(1);
                    break;
            }
            
            switch(playerOneSelection) {
                case 0:
                    System.out.println("Player 1: Human");
                    playerOne = new HumanPlayer(1, board);
                    break;
                case 1:
                    System.out.println("Player 1: AI Random");
                    playerOne = new AIRandomPlayer(1, board);
                    break;
                case 2:
                    System.out.println("Player 1: AI Blocker");
                    playerOne = new AIBlockerPlayer(1, board);
                    break;
                case 3:
                    System.out.println("Player 1: AI Minimax 1");
                    playerOne = new AIMinimax1Player(1, 4, board);
                    break;
                case 4:
                    System.out.println("Player 1: AI Minimax 2");
                    playerOne = new AIMinimax2Player(1, 4, board);
                    break;
                case 5:
                    System.out.println("Player 1: AI AlphaBeta");
                    playerOne = new AIAlphaBetaPlayer(1, 5, board);
                    break;
                case 6:
                    System.out.println("Player 1: AI Heuristic");
                    playerOne = new AIHeuristicPlayer(1, 4, board);
                    break;
                case 7:
                    System.out.println("Player 1: AI MonteCarlo 1");
                    playerOne = new AIMonteCarlo1Player(1, 20000, board);
                    break;
                case 8:
                    System.out.println("Player 1: AI MonteCarlo 2");
                    playerOne = new AIMonteCarlo2Player(1, 20000, board);
                    break;
                default:
                    System.err.println("Invalid playerOneSelection value.");
                    System.exit(1);
                    break;
            }
            
            switch(playerTwoSelection) {
                case 0:
                    System.out.println("Player 2: AI Random");
                    playerTwo = new AIRandomPlayer(2, board);
                    break;
                case 1:
                    System.out.println("Player 2: AI Blocker");
                    playerTwo = new AIBlockerPlayer(2, board);
                    break;
                case 2:
                    System.out.println("Player 2: AI Minimax 1");
                    playerTwo = new AIMinimax1Player(2, 4, board);
                    break;
                case 3:
                    System.out.println("Player 2: AI Minimax 2");
                    playerTwo = new AIMinimax2Player(2, 4, board);
                    break;
                case 4:
                    System.out.println("Player 2: AI AlphaBeta");
                    playerTwo = new AIAlphaBetaPlayer(2, 5, board);
                    break;
                case 5:
                    System.out.println("Player 2: AI AlphaBeta");
                    playerTwo = new AIHeuristicPlayer(2, 4, board);
                    break;
                case 6:
                    System.out.println("Player 2: AI MonteCarlo 1");
                    playerTwo = new AIMonteCarlo1Player(2, 20000, board);
                    break;
                case 7:
                    System.out.println("Player 2: AI MonteCarlo 2");
                    playerTwo = new AIMonteCarlo2Player(2, 20000, board);
                    break;
                default:
                    System.err.println("Invalid playerTwoSelection value.");
                    System.exit(1);
                    break;
            }
            System.out.println("");
            playerOne.toggleVerbose();
            playerTwo.toggleVerbose();

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
                    long start = System.currentTimeMillis();
                    col = playerOne.getNextMove();
                    long end = System.currentTimeMillis();
                    System.out.println("  Time to compute move: " + (end-start) + " ms\n");
                    System.out.println("Player 1 inserts into column " + col + "\n");
                    board.insertPiece(playerOne.getPieceColor(), col);
                    playerTurn = 2;
                }
                else
                {
                    long start = System.currentTimeMillis();
                    col = playerTwo.getNextMove();
                    long end = System.currentTimeMillis();
                    System.out.println("  Time to compute move: " + (end-start) + " ms\n");
                    System.out.println("Player 2 inserts into column " + col + "\n");
                    board.insertPiece(playerTwo.getPieceColor(), col);
                    playerTurn = 1;
                }
                win = board.checkWin();
                if(playerOne.isHuman() || playerTwo.isHuman())
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

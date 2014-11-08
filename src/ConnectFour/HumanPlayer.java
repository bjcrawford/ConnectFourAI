
package ConnectFour;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A player class to represent a human player. This class will 
 * handle synchronization between the program logic and the GUI.
 * 
 */
public class HumanPlayer extends AbstractPlayer {
    
    /* The next move the player wishes to make */
    public int nextMove;
    
    /* A mutex lock to ensure mutual exclusion */
    public Semaphore mutexLock;
    
    /* A semaphore representing available input from the GUI */
    public Semaphore userDropInputSem;
    
    /**
     * Creates a human player object.
     * 
     * @param pieceColor The player's piece color
     * @param cfb The connect four board
     */
    public HumanPlayer(int pieceColor, ConnectFourBoard cfb) {
        
        super(pieceColor, true, cfb);
        nextMove = -1; // -1 means we are waiting on user input
        mutexLock = new Semaphore(1);
        userDropInputSem = new Semaphore(0); // Start with no available input
    }
    
    /**
     * Gets the player's next move. This method will block 
     * until user input is detected via a release on the 
     * user drop input semaphore.
     * 
     * @return The column the player wishes to insert into
     */
    @Override
    public int getNextMove() {
        
        int result;
        try {
            userDropInputSem.acquire(); // Wait for user input
            mutexLock.acquire();
            result = nextMove;
            nextMove = -1;
            mutexLock.release();
        } 
        catch(InterruptedException ex) {
            Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
            result = -2;
        }
        
        return result;
    }
    
    /**
     * Sets the player's next move. This method should be called
     * from the actionPerformed() method of a drop button. This 
     * method will release any waiters in the getNextMove() method.
     * 
     * @param move The column the player wishes to insert into
     * @return True if the set was successful, false otherwise
     */
    public boolean setNextMove(int move) {
        
        boolean result = false;
        try {
            mutexLock.acquire();
            if(nextMove == -1) {
                nextMove = move;
                userDropInputSem.release(); // Release waiters
                result = true;
            }
            mutexLock.release();
        } 
        catch(InterruptedException ex) {
            Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}

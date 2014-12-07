
package ConnectFour;

/**
 * An abstract player class.
 * 
 */
public abstract class AbstractPlayer {
    
    /** The player's piece color */
    protected int pieceColor;
    
    /** Indicates a human player */
    protected boolean human;
    
    /** A toggle for printing getNextMove() info */
    boolean verbose;
    
    /** The connect four board */
    protected ConnectFourBoard cfb;
    
    /**
     * A constructor to be called from a class inheriting from this class
     * 
     * @param pieceColor The player's piece color (1: red, 2: black)
     * @param cfb The connect four board
     */
    public AbstractPlayer(int pieceColor, boolean human, ConnectFourBoard cfb) {
        
        this.pieceColor = pieceColor;
        this.human = human;
        this.cfb = cfb;
        this.verbose = false;
    }
    
    /**
     * Toggles the verbose flag
     * 
     * @return The new state of the flag
     */
    public boolean toggleVerbose() {
        
        return (verbose = !verbose);
    }
    
    /**
     * Gets the player's next move
     * 
     * @return the player's column choice
     */
    public abstract int getNextMove();
    
    /**
     * Gets the player's piece color
     * 
     * @return 1 if red, 2 if black
     */
    public int getPieceColor() {
        
        return pieceColor;
    }
    
    /**
     * Gets the player's type
     * 
     * @return True if human, otherwise false
     */
    public boolean isHuman() {
        
        return human;
    }
    
}

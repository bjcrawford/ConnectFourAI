
package ConnectFour;

/**
 *
 * @author bcrawford
 */
public abstract class AbstractPlayer {
    
    protected int pieceColor;
    protected ConnectFourBoard cfb;
    
    public AbstractPlayer(int pieceColor, ConnectFourBoard cfb) {
        
        this.pieceColor = pieceColor;
        this.cfb = cfb;
    }
    
    public abstract int getNextMove();
    
}

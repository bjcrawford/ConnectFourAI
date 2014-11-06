
package ConnectFour;

/**
 *
 * @author bcrawford
 */
public class HumanPlayer extends AbstractPlayer {
    
    public HumanPlayer(int pieceColor, ConnectFourBoard cfb) {
        
        super(pieceColor, cfb);
    }
    
    @Override
    public int getNextMove() {
        
        return 0;
    }
    
}

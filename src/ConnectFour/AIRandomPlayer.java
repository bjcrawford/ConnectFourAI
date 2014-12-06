package ConnectFour;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This player is a very simple AI which makes all of its
 * moves at random. This should be the simplest AI to play 
 * against.
 * 
 */
public class AIRandomPlayer extends AbstractPlayer {


    public AIRandomPlayer(int pieceColor, ConnectFourBoard cfb) {

        super(pieceColor, false, cfb);
    }

    /**
     * Gets the AI's next move.
     *
     * @return The column the AI wishes to insert into
     */
    @Override
    public int getNextMove() {

        Random rand = new Random(System.currentTimeMillis());
        ArrayList<Integer> moves = cfb.getPossibleMoves();
        int bestMove = moves.get(rand.nextInt(moves.size()));
        
        // Pause for a moment
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getLocalizedMessage());
        }
        
        System.out.println("Player " + pieceColor + " Move Info:");
        System.out.println("\n  AI Type: Random");
        System.out.println("\n  Possible Moves:\n");
        for(int move : moves) {
            
            System.out.println("    Column: " + (move+1));
        }
        System.out.println("\n  Considered Moves:\n");
        System.out.println("    Column: " + (bestMove+1));
        System.out.println();

        return bestMove;
    }
}

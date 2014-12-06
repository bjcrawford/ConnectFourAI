package ConnectFour;

import java.util.ArrayList;
import java.util.Random;

/**
 * This player is a very simple AI that takes a defensive approach.
 * This AI will attempt to block any sequences of opponents piece
 * that are three pieces in length. If no blocking moves are found,
 * the AI will insert pieces at random.
 * 
 */
public class AIBlockerPlayer extends AbstractPlayer {


    public AIBlockerPlayer(int pieceColor, ConnectFourBoard cfb) {

        super(pieceColor, false, cfb);
    }

    /**
     * Gets the AI's next move.
     *
     * @return The column the AI wishes to insert into
     */
    @Override
    public int getNextMove() {

        boolean randomMove = false;
        int bestMove = getBlockMove();
        ArrayList<Integer> moves = cfb.getPossibleMoves();
        if(bestMove == -1) {
            Random rand = new Random(System.currentTimeMillis());
            bestMove = moves.get(rand.nextInt(moves.size()));
            randomMove = true;
        }
        System.out.println("Player " + pieceColor + " Move Info:");
        System.out.println("\n  AI Type: Random");
        System.out.println("\n  Possible Moves:\n");
        for(int move : moves) 
            System.out.println("    Column: " + (move+1));
        System.out.println("\n  Considered Moves:\n");
        if(randomMove)
            System.out.println("    Column: " + (bestMove+1) + " (random)");
        else
            System.out.println("    Column: " + (bestMove+1) + " (block)");
        System.out.println();

        return bestMove;
    }
    
    /**
     * Checks for open spots on the board with adjacent three-in-a-row
     * sequences of the opponents pieces. Returns the column of the first 
     * such spot found when searching from the left side of the board to 
     * the right.
     * 
     * @return The column of the block move, -1 if no move is found
     */
    public int getBlockMove() {
        
        int move = -1;
        int pc = ((pieceColor + 2) % 2) + 1;
        int[][] board = cfb.getBoard();
        int height = cfb.getHeight();
        int width = cfb.getWidth();
        boolean found; /* position of possible insert */
        
        for(int col = 0; col < width && move == -1; col++) {
            
            found = false;
            for(int row = height-1; row >= 0 && !found; row--) {
                
                if(board[row][col] == 0) {
                
                    // Found the first open row position in the column
                    found = true;
                    
                    // Check north
                    // North is not possible in connect four

                    // Check north east
                    if(col < (width - 3) && row >= 3) {
                        if(board[row - 1][col + 1] == pc &&
                           board[row - 2][col + 2] == pc &&
                           board[row - 3][col + 3] == pc) {
                            move = col;
                        }
                    }
                    // Check east
                    if(col < (width - 3)) {
                        if(board[row][col + 1] == pc &&
                           board[row][col + 2] == pc &&
                           board[row][col + 3] == pc) {
                            move = col;
                        }
                    }
                    // Check south east
                    if(col < (width - 3) && row < (height - 3)) {
                        if(board[row + 1][col + 1] == pc &&
                           board[row + 2][col + 2] == pc &&
                           board[row + 3][col + 3] == pc) {
                            move = col;
                        }
                    }
                    // Check south
                    if(row < (height - 3)) {
                        if(board[row + 1][col] == pc &&
                           board[row + 2][col] == pc &&
                           board[row + 3][col] == pc) {
                            move = col;
                        }
                    }
                    // Check south west
                    if(col >= 3 && row < (height - 3)) {
                        if(board[row + 1][col - 1] == pc &&
                           board[row + 2][col - 2] == pc &&
                           board[row + 3][col - 3] == pc) {
                            move = col;
                        }
                    }
                    // Check west
                    if(col >= 3) {
                        if(board[row][col - 1] == pc &&
                           board[row][col - 2] == pc &&
                           board[row][col - 3] == pc) {
                            move = col;
                        }
                    }
                    // Check north west
                    if(col >= 3 && row >= 3) {
                        if(board[row - 1][col - 1] == pc &&
                           board[row - 2][col - 2] == pc &&
                           board[row - 3][col - 3] == pc) {
                            move = col;
                        }
                    }
                }
            }
        }
        
        return move;
    }
}

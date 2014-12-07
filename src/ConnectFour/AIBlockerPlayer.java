package ConnectFour;

import java.util.ArrayList;
import java.util.Random;

/**
 * This player is a very simple AI that takes a defensive approach.
 * This AI will attempt to block any sequences of opponents pieces
 * that will result in an opponent win on the very next move. If no 
 * blocking moves are found, the AI will insert pieces at random.
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
        
        if(verbose) {
            // Pause for a moment
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(e.getLocalizedMessage());
            }
        
            System.out.println("Player " + pieceColor + " Move Info:");
            System.out.println("\n  AI Type: Blocker");
            System.out.println("\n  Possible Moves:\n");
            for(int move : moves) 
                System.out.println("    Column: " + (move+1));
            System.out.println("\n  Considered Moves:\n");
            if(randomMove)
                System.out.println("    Column: " + (bestMove+1) + " (random)");
            else
                System.out.println("    Column: " + (bestMove+1) + " (block)");
            System.out.println();
        }
        return bestMove;
    }
    
    /**
     * Checks for open spots on the board where the opponent can win
     * in the following move. Returns the column of the first such spot 
     * found when searching from the left side of the board to the right.
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
                    // North is not possible

                    // Check north east
                    // 0xxx
                    if(col < (width - 3) && row >= 3) {
                        if(board[row - 1][col + 1] == pc &&
                           board[row - 2][col + 2] == pc &&
                           board[row - 3][col + 3] == pc) {
                            move = col;
                        }
                    }
                    // x0xx
                    if(col >= 1 && col < (width - 2) &&
                       row >= 2 && row < (width - 1)) {
                        if(board[row + 1][col - 1] == pc &&
                           board[row - 1][col + 1] == pc &&
                           board[row - 2][col + 2] == pc) {
                            move = col;
                        }
                    }
                    // Check east
                    // 0xxx
                    if(col < (width - 3)) {
                        if(board[row][col + 1] == pc &&
                           board[row][col + 2] == pc &&
                           board[row][col + 3] == pc) {
                            move = col;
                        }
                    }
                    // x0xx
                    if(col >= 1 && col < (width - 2)) {
                        if(board[row][col - 1] == pc &&
                           board[row][col + 1] == pc &&
                           board[row][col + 2] == pc) {
                            move = col;
                        }
                    }
                    // Check south east
                    // 0xxx
                    if(col < (width - 3) && row < (height - 3)) {
                        if(board[row + 1][col + 1] == pc &&
                           board[row + 2][col + 2] == pc &&
                           board[row + 3][col + 3] == pc) {
                            move = col;
                        }
                    }
                    // x0xx
                    if(col >= 1 && col < (width - 2) && 
                       row >= 1 && row < (height - 2)) {
                        if(board[row - 1][col - 1] == pc &&
                           board[row + 1][col + 1] == pc &&
                           board[row + 2][col + 2] == pc) {
                            move = col;
                        }
                    }
                    // Check south
                    // 0xxx
                    if(row < (height - 3)) {
                        if(board[row + 1][col] == pc &&
                           board[row + 2][col] == pc &&
                           board[row + 3][col] == pc) {
                            move = col;
                        }
                    }
                    // x0xx - not possible
                    // Check south west
                    // xxx0
                    if(col >= 3 && row < (height - 3)) {
                        if(board[row + 1][col - 1] == pc &&
                           board[row + 2][col - 2] == pc &&
                           board[row + 3][col - 3] == pc) {
                            move = col;
                        }
                    }
                    // xx0x
                    if(col >= 2 && col < (width - 1) &&
                       row >= 1 && row < (height - 2)) {
                        if(board[row - 1][col + 1] == pc &&
                           board[row + 1][col - 1] == pc &&
                           board[row + 2][col - 2] == pc) {
                            move = col;
                        }
                    }
                    // Check west
                    // xxx0
                    if(col >= 3) {
                        if(board[row][col - 1] == pc &&
                           board[row][col - 2] == pc &&
                           board[row][col - 3] == pc) {
                            move = col;
                        }
                    }
                    // xx0x
                    if(col >= 2 && col < (width - 1)) {
                        if(board[row][col + 1] == pc &&
                           board[row][col - 1] == pc &&
                           board[row][col - 2] == pc) {
                            move = col;
                        }
                    }
                    // Check north west
                    // xxx0
                    if(col >= 3 && row >= 3) {
                        if(board[row - 1][col - 1] == pc &&
                           board[row - 2][col - 2] == pc &&
                           board[row - 3][col - 3] == pc) {
                            move = col;
                        }
                    }
                    // xx0x
                    if(col >= 2 && col < (width - 1) &&
                       row >= 2 && row < (width - 1)) {
                        if(board[row + 1][col + 1] == pc &&
                           board[row - 1][col - 1] == pc &&
                           board[row - 2][col - 2] == pc) {
                            move = col;
                        }
                    }
                }
            }
        }
        
        return move;
    }
}

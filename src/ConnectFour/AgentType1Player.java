
package ConnectFour;

/**
 *
 * @author bcrawford
 */
public class AgentType1Player {
    
    int pieceColor;
    int minimaxScore;
    
    public AgentType1Player(int pieceColor) {
        
        this.pieceColor = pieceColor;
        this.minimaxScore = 0;
    }
    
    public int getNextMove(int board[][]) {
        int result = 0;
        
        // Create tree to hold state space looking two moves ahead
        
        // Send to to method for evaluation
        
        // Store column to be inserted into result
        
        return result;
    }
    
        /**
     * Inserts a game piece into the given board.
     * 
     * @param col The column to insert the piece into
     * @param board The two-dimensional board to insert into
     * @return True if the insertion was successful, otherwise false
     */
    public boolean insertPiece(int col, int board[][]) {
        
        boolean result = false;
        if( (pieceColor == 1 || pieceColor == 2) &&  
             col >= 0 && col < board[0].length)
        {
            for(int row = board.length - 1; row >= 0; row--)
            {
                if(board[row][col] == 0)
                {
                    board[row][col] = pieceColor;
                    result = true;
                    break;
                }
            }
        }
        
        return result;
    }
    
}

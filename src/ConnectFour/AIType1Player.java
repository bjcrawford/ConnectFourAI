
package ConnectFour;

import com.gaurav.tree.KAryTree;
import com.gaurav.tree.NodeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


/**
 * This is the Minimax AI for our Connect Four implementation. This
 * AI is capable of looking 5 moves ahead. No other factors besides
 * win or lose have been considered.
 * 
 * @author bcrawford
 */
public class AIType1Player {
    
    int pieceColor;
    ConnectFourBoard CFB;
    ConnectFourBoardState CFBS[];
    KAryTree<ConnectFourBoardState> stateSpace;
    
    
    public AIType1Player(int pieceColor, ConnectFourBoard CFB) {
        
        this.pieceColor = pieceColor;
        this.CFB = CFB;
    }
    
    public int getNextMove() throws NodeNotFoundException {
        
        int result;
        int cols = CFB.getBoard().length;
        int pc = ((pieceColor + 2) % 2) + 1; // Opponents piece color
        CFBS = new ConnectFourBoardState[6];
        stateSpace = new KAryTree<>(cols);
        
        // Create and insert the root
        CFBS[0] = new ConnectFourBoardState(CFB.getBoard(), pc);
        stateSpace.add(CFBS[0]);
        
        // Build the state space with a depth of three
        // starting from the first level (the root is zero level)
        buildStateSpaceRecursive(cols, 3, 1);
        
        List<ConnectFourBoardState> lotL = stateSpace.levelOrderTraversal();
        ListIterator<ConnectFourBoardState> lotLI = lotL.listIterator();
        
        List<ConnectFourBoardState> list = new ArrayList<>();
        int highest = -101;
        
        ConnectFourBoardState c = lotLI.next();
        int d = 1;
        
        System.out.println("Possible Moves:\n");
        while(lotLI.hasNext() && (c = lotLI.next()).getDepth() < 2)
        {
            System.out.println("Col: " + c.getColInserted() + 
                               " Path: " + c.getPath() + 
                               " Score: " + c.getScore());
            
            
            if(c.getScore() > highest)
            {
                highest = c.getScore();
                list.clear();
                list.add(c);
            }
            else if(c.getScore() == highest)
            {
                list.add(c);
            }
            
        }
        
        System.out.println("\nConsidered Moves:\n");
        for(ConnectFourBoardState cfbs : list)
        {
            //System.out.println(cfbs);
            System.out.println("Column: " + cfbs.getColInserted() + 
                               " Path: " + cfbs.getPath() + 
                               " Score: " + cfbs.getScore());
        }
        
        int numMoves = list.size();
        Random rand = new Random();
        int choice = rand.nextInt(numMoves);
        int col = list.get(choice).getColInserted();
        System.out.println("\nInserting into column " + col + "\n");
        result = list.get(choice).getColInserted();
        
        /*
        lotLI = lotL.listIterator();
        System.out.println("\nState Space:");
        while(lotLI.hasNext() && (c = lotLI.next()).getDepth() < 3) {
            System.out.println("Score: " + c.getScore() + 
                               " Path: " + c.getPath() + 
                               " Col: " + c.getColInserted());
        }
        */
        
        return result;
    }
    
    public void buildStateSpaceRecursive(int k, int maxDepth, int currentDepth) throws NodeNotFoundException {
        
        if(maxDepth >= 0)
        {
            for(int i = 0; i < k; i++)
            {
                // Create and insert i-th child of the parent node
                CFBS[currentDepth] = CFBS[currentDepth - 1].createChildState(i);

                // If the child did not result in a failed insert (i.e., insert
                // into full column), add the child to the tree and create
                // any children it may have
                if(!CFBS[currentDepth].getFailedInsert());
                {
                    CFBS[currentDepth].setScore(evaluateBoardState(CFBS[currentDepth].getBoard()));
                    stateSpace.add(CFBS[currentDepth - 1], CFBS[currentDepth]);
                    buildStateSpaceRecursive(k, maxDepth - 1, currentDepth + 1);
                }
                
                // If the parent has not had a score set, give it the child's score
                if(!CFBS[currentDepth - 1].getScoreEvaluated())
                    CFBS[currentDepth - 1].setScore(CFBS[currentDepth].getScore());
                
                // On even depths, give the parent the min score
                // On odd depths, give the parent the max score
                if(currentDepth % 2 == 0)
                {
                    if(CFBS[currentDepth].getScore() < CFBS[currentDepth - 1].getScore())
                        CFBS[currentDepth - 1].setScore(CFBS[currentDepth].getScore());
                }
                else
                {
                    if(CFBS[currentDepth].getScore() > CFBS[currentDepth - 1].getScore())
                        CFBS[currentDepth - 1].setScore(CFBS[currentDepth].getScore());
                }
            }
        }
    }
    
    public int evaluateBoardState(int[][] board) {
        int result;
        int win = ConnectFourBoardState.checkWin(board);
        if(win == 0)
            result = 0;
        else if(win == pieceColor)
            result = 100;
        else
            result = -100;
        return result;
    }
    
        /**
     * Creates a copy of the given board and attempts to insert a
     * piece. 
     * 
     * @param pieceColor The color of the piece to insert
     * @param col The column to insert the piece into
     * @param board The two-dimensional board to insert into
     * @return Returns the new board if the insertion was successful, 
     *         otherwise returns null
     */
    public int[][] insertPiece(int pieceColor, int col, int board[][]) {
        
        int newBoard[][] = new int[board.length][board[0].length];
        
        if( (pieceColor == 1 || pieceColor == 2) &&  
             col >= 0 && col < board[0].length)
        {
            for(int i = 0; i < board.length; i++)
                for(int j = 0; j < board[i].length; j++)
                    newBoard[i][j] = board[i][j];
            
            for(int row = newBoard.length - 1; row >= 0; row--)
            {
                if(newBoard[row][col] == 0)
                {
                    newBoard[row][col] = pieceColor;
                    break;
                }
            }
        }
        
        return newBoard;
    }
    
    
    
}

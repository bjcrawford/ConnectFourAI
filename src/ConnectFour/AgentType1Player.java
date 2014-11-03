
package ConnectFour;

import com.gaurav.tree.KAryTree;
import com.gaurav.tree.NodeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


/**
 * This is the Minimax AI for our Connect Four implementation. This
 * AI is capable of looking 5 moves ahead. No other factors besides
 * win or lose have been considered.
 * 
 * @author bcrawford
 */
public class AgentType1Player {
    
    int pieceColor;
    ConnectFourBoard CFB;
    
    public AgentType1Player(int pieceColor, ConnectFourBoard CFB) {
        
        this.pieceColor = pieceColor;
        this.CFB = CFB;
    }
    
    public int getNextMove() throws NodeNotFoundException {
        
        int cols = CFB.getBoard().length;
        int result = cols/2;
        int pc1 = pieceColor;
        int pc2 = ((pieceColor + 2) % 2) + 1;
        ConnectFourBoardState CFBS[] = new ConnectFourBoardState[7];
        KAryTree<ConnectFourBoardState> stateSpace = new KAryTree<>(cols);
        
        // Create and insert the root (depth 0)
        CFBS[0] = new ConnectFourBoardState(CFB.getBoard(), 0, "0", false);
        stateSpace.add(CFBS[0]);
        for(int i = 0; i < cols; i++)
        {
            // Create and insert the i-th child of the root (depth 1)
            CFBS[1] = CFBS[0].createChildState(pc1, i);
            
            if(CFBS[1].getFailedInsert());
                //Do Nothing
            else if(CFBS[1].getWin())
            {
                CFBS[1].setScore(evaluateBoardState(CFBS[1].getBoard()));
                stateSpace.add(CFBS[0], CFBS[1]);
            }
            else 
            {
                
                stateSpace.add(CFBS[0], CFBS[1]);
                for(int j = 0; j < cols; j++)
                {
                    // Create and insert the j-th child of the i-th node (depth 2)
                    CFBS[2] = CFBS[1].createChildState(pc2, j);

                    if(CFBS[2].getFailedInsert());
                        //Do Nothing
                    else if(CFBS[2].getWin())
                    {
                        CFBS[2].setScore(evaluateBoardState(CFBS[2].getBoard()));
                        stateSpace.add(CFBS[1], CFBS[2]);
                    }
                    else
                    {
                        stateSpace.add(CFBS[1], CFBS[2]);
                        for(int k = 0; k < cols; k++)
                        {
                            // Create and insert the k-th child of the j-th node (depth 3);
                            CFBS[3] = CFBS[2].createChildState(pc1, k);
                            
                            if(CFBS[3].getFailedInsert());
                                //Do Nothing
                            else if(CFBS[3].getWin())
                            {
                                CFBS[3].setScore(evaluateBoardState(CFBS[3].getBoard()));
                                stateSpace.add(CFBS[2], CFBS[3]);
                            }
                            else
                            {
                                stateSpace.add(CFBS[2], CFBS[3]);
                                for(int l = 0; l < cols; l++)
                                {
                                    // Create and insert the l-th child of the k-th node (depth 4);
                                    CFBS[4] = CFBS[3].createChildState(pc2, l);
                                    
                                    if(CFBS[4].getFailedInsert());
                                        //Do Nothing
                                    else if(CFBS[4].getWin())
                                    {
                                        CFBS[4].setScore(evaluateBoardState(CFBS[4].getBoard()));
                                        stateSpace.add(CFBS[3], CFBS[4]);
                                    }
                                    else
                                    {
                                        stateSpace.add(CFBS[3], CFBS[4]);
                                        for(int m = 0; m < cols; m++)
                                        {
                                            // Create and insert the m-th child of the l-th node (depth 5);
                                            CFBS[5] = CFBS[4].createChildState(pc1, m);
                                            
                                            if(CFBS[5].getFailedInsert());
                                                //Do Nothing
                                            else
                                            {
                                                CFBS[5].setScore(evaluateBoardState(CFBS[5].getBoard()));
                                                stateSpace.add(CFBS[4], CFBS[5]);
                                            }
                                            if(!CFBS[4].getScoreEvaluated())
                                                CFBS[4].setScore(CFBS[5].getScore());
                                            if(CFBS[5].getScore() > CFBS[4].getScore())
                                                CFBS[4].setScore(CFBS[5].getScore());
                                        }
                                    }
                                    if(!CFBS[3].getScoreEvaluated())
                                        CFBS[3].setScore(CFBS[4].getScore());
                                    if(CFBS[4].getScore() < CFBS[3].getScore())
                                        CFBS[3].setScore(CFBS[4].getScore());
                                }
                            }
                            if(!CFBS[2].getScoreEvaluated())
                                CFBS[2].setScore(CFBS[3].getScore());
                            if(CFBS[3].getScore() > CFBS[2].getScore())
                                CFBS[2].setScore(CFBS[3].getScore());
                        }
                    }
                    if(!CFBS[1].getScoreEvaluated())
                        CFBS[1].setScore(CFBS[2].getScore());
                    if(CFBS[2].getScore() < CFBS[1].getScore())
                        CFBS[1].setScore(CFBS[2].getScore());
                }
            }
            if(!CFBS[0].getScoreEvaluated())
                CFBS[0].setScore(CFBS[1].getScore());
            if(CFBS[1].getScore() > CFBS[0].getScore())
                CFBS[0].setScore(CFBS[1].getScore());
        }
        
        
        List<ConnectFourBoardState> lotL = stateSpace.levelOrderTraversal();
        ListIterator<ConnectFourBoardState> lotLI = lotL.listIterator();
        
        List<ConnectFourBoardState> list = new ArrayList<>();
        int highest = -101;
        
        ConnectFourBoardState c = lotLI.next();
        int d = 1;
        
        System.out.println("Possible Moves:");
        while(lotLI.hasNext() && (c = lotLI.next()).getDepth() < 2)
        {
            /*
            if(d == c.getDepth())
            {
                System.out.println("\nDepth: " + d + "\n");
                d++;
            }*/
            System.out.println("Score: " + c.getScore() + 
                               " Path: " + c.getPath() + 
                               " Col: " + c.getColInserted());
            
            
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
        
        System.out.println("\nConsidered Moves:");
        for(ConnectFourBoardState cfbs : list)
        {
            //System.out.println(cfbs);
            System.out.println("Score: " + cfbs.getScore() + 
                               " Path: " + cfbs.getPath() + 
                               " Col: " + cfbs.getColInserted());
            result = cfbs.getColInserted();
        }
        
        return result;
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

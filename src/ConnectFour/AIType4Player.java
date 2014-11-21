
package ConnectFour;

import com.gaurav.tree.KAryTree;
import com.gaurav.tree.NodeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


/**
 * This is the Type 4 for our Connect Four implementation. This
 * AI uses a slightly improved minimax algorithm along with a 
 * heuristic function that is based on each player's number of 
 * open triples. It is capable of looking n moves ahead. When 
 * evaluating the score at a givennode, those nodes closer to 
 * the root are given a larger weight than nodes that are 
 * further from the root. This allows the AI to differentiate 
 * between a win in two moves versus a win in four moves.
 */
public class AIType4Player extends AbstractPlayer {
    
    private int lookAhead;
    private ConnectFourBoardState CFBS[];
    private KAryTree<ConnectFourBoardState> stateSpace;
    
    public AIType4Player(int pieceColor, int lookAhead, ConnectFourBoard cfb) {
        
        super(pieceColor, false, cfb);
        this.lookAhead = lookAhead;
    }
    
    /**
     * Gets the AI's next move. 
     * 
     * @return The column the AI wishes to insert into
     */
    @Override
    public int getNextMove() {
        
        int result;
        int cols = cfb.getBoard().length;
        int pc = ((pieceColor + 2) % 2) + 1; // Opponents piece color
        CFBS = new ConnectFourBoardState[lookAhead + 1];
        stateSpace = new KAryTree<>(cols);
        
        // Create and insert the root
        CFBS[0] = new ConnectFourBoardState(cfb.getBoard(), pc);
        stateSpace.add(CFBS[0]);
        
        // Build the state space with a depth of three
        // starting from the first level (the root is zero level)
        try {
            buildStateSpaceRecursive(cols, lookAhead - 1, 1);
        }
        catch(NodeNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            return -1;
        }
        
        List<ConnectFourBoardState> lotL = stateSpace.levelOrderTraversal();
        ListIterator<ConnectFourBoardState> lotLI = lotL.listIterator();
        
        List<ConnectFourBoardState> list = new ArrayList<>();
        int highest = (-1000 * lookAhead) - 1;
        
        ConnectFourBoardState c = lotLI.next();
        int d = 1;
        System.out.println("Player " + pieceColor + " Move Info:");
        System.out.println("\n  AI Type 3");
        System.out.println("\n  Possible Moves:\n");
        while(lotLI.hasNext() && (c = lotLI.next()).getDepth() < 2)
        {
            System.out.println("    Col: " + c.getColInserted() + 
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
        
        System.out.println("\n  Considered Moves:\n");
        for(ConnectFourBoardState cfbs : list)
        {
            //System.out.println(cfbs);
            System.out.println("    Column: " + cfbs.getColInserted() + 
                               " Path: " + cfbs.getPath() + 
                               " Score: " + cfbs.getScore());
        }
        
        int numMoves = list.size();
        Random rand = new Random();
        int choice = rand.nextInt(numMoves);
        int col = list.get(choice).getColInserted();
        result = list.get(choice).getColInserted();
        
        /*
        lotLI = lotL.listIterator();
        System.out.println("\n  State Space:");
        while(lotLI.hasNext() && (c = lotLI.next()).getDepth() < 4) {
            System.out.println("    Path: " + c.getPath() + " Score: " + c.getScore());
        }
        */
        
        System.out.println("");
        
        
        return result;
    }
    /**
     * Builds the state space for the getNextMove() method. 
     * 
     * @param k The max number of children per node
     * @param maxDepth The max depth to continue building past this call's level
     * @param currentDepth The current depth within the tree
     * @throws NodeNotFoundException 
     */
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
                if(!CFBS[currentDepth].getFailedInsert())
                {
                    stateSpace.add(CFBS[currentDepth - 1], CFBS[currentDepth]);
                    
                    // If this state doesn't result in a win, continue building
                    // the state space.
                    if(CFBS[currentDepth].checkWin() == 0)
                        buildStateSpaceRecursive(k, maxDepth - 1, currentDepth + 1);
                
                    // Evaluate scores at the leaves of the tree
                    if(maxDepth == 0)
                        CFBS[currentDepth].setScore(evaluateBoardState(CFBS[currentDepth]));
                    
                    // At any node, if the state results in a win, update the node score
                    // and ignore the value given by the children. The node score is
                    // multiplied by (maxDepth + 1) to give the score a weight. This
                    // gives priority to the moves nearest the root in the state space.
                    if(CFBS[currentDepth].checkWin() != 0)
                        CFBS[currentDepth].setScore((maxDepth + 1) * evaluateBoardState(CFBS[currentDepth]));
                    
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
    }
    
    /**
     * Evaluates a connect four board state.
     * 
     * @param cfbs The board state
     * @return The score for the board state
     */
    public int evaluateBoardState(ConnectFourBoardState cfbs) {
        int result;
        int win = cfbs.checkWin();
        if(win == 0)
            result = checkOpenTriples(cfbs);
        else if(win == pieceColor)
            result = 1000;
        else
            result = -1000;
        return result;
    }
    
    /**
     * Check all open spots on the board for adjacent "three-in-a-row"
     * combinations. The players open triples are given a positive 
     * score value and opponent open triples are given a negative 
     * score value. The net score is returned. (If the player has
     * more open triples than the opponent, a positive score will
     * be return. If the opponent has more open triples than the 
     * player, a negative value will be returned. If both have an
     * equal number of open triples, 0 will be returned.
     * 
     * @param cfbs The board state
     * @return The heuristic score for the board state
     */
    public int checkOpenTriples(ConnectFourBoardState cfbs) {
        
        int result = 0;
        int pc1 = cfbs.getPieceColor();
        int pc2 = ((pc1 + 2) % 2) + 1;
        int[][] board = cfbs.getBoard();
        int height = cfbs.getHeight();
        int width = cfbs.getWidth();
        
        for(int row = 0; row < height; row++) {
            
            for(int col = 0; col < width; col++) {
                
                if(board[row][col] == 0) {
                
                    // Check north
                    // North is not possible in connect four

                    // Check north east
                    if(col < (width - 3) && row >= 3) {
                        if(board[row - 1][col + 1] == pc1 &&
                           board[row - 2][col + 2] == pc1 &&
                           board[row - 3][col + 3] == pc1) {
                            result += 50;
                            //System.out.println("NE+: " + row + " " + col);
                        }
                        else if(board[row - 1][col + 1] == pc2 &&
                                board[row - 2][col + 2] == pc2 &&
                                board[row - 3][col + 3] == pc2) {
                            result -= 50;
                            //System.out.println("NE-: " + row + " " + col);
                        }
                    }

                    // Check east
                    if(col < (width - 3)) {
                        if(board[row][col + 1] == pc1 &&
                           board[row][col + 2] == pc1 &&
                           board[row][col + 3] == pc1) {
                            result += 50;
                            //System.out.println("E+: " + row + " " + col);
                        }
                        else if(board[row][col + 1] == pc2 &&
                                board[row][col + 2] == pc2 &&
                                board[row][col + 3] == pc2) {
                            result -= 50;
                            //System.out.println("E-: " + row + " " + col);
                        }
                    }

                    // Check south east
                    if(col < (width - 3) && row < (height - 3)) {
                        if(board[row + 1][col + 1] == pc1 &&
                           board[row + 2][col + 2] == pc1 &&
                           board[row + 3][col + 3] == pc1) {
                            result += 50;
                            //System.out.println("SE+: " + row + " " + col);
                        }
                        else if(board[row + 1][col + 1] == pc2 &&
                                board[row + 2][col + 2] == pc2 &&
                                board[row + 3][col + 3] == pc2) {
                            result -= 50;
                            //System.out.println("SE-: " + row + " " + col);
                        }
                    }

                    // Check south
                    if(row < (height - 3)) {
                        if(board[row + 1][col] == pc1 &&
                           board[row + 2][col] == pc1 &&
                           board[row + 3][col] == pc1) {
                            result += 50;
                            //System.out.println("S+: " + row + " " + col);
                        }
                        else if(board[row + 1][col] == pc2 &&
                                board[row + 2][col] == pc2 &&
                                board[row + 3][col] == pc2) {
                            result -= 50;
                            //System.out.println("S-: " + row + " " + col);
                        }
                    }

                    // Check south west
                    if(col >= 3 && row < (height - 3)) {
                        if(board[row + 1][col - 1] == pc1 &&
                           board[row + 2][col - 2] == pc1 &&
                           board[row + 3][col - 3] == pc1) {
                            result += 50;
                            //System.out.println("SW+: " + row + " " + col);
                        }
                        else if(board[row + 1][col - 1] == pc2 &&
                                board[row + 2][col - 2] == pc2 &&
                                board[row + 3][col - 3] == pc2) {
                            result -= 50;
                            //System.out.println("SW-: " + row + " " + col);
                        }
                    }

                    // Check west
                    if(col >= 3) {
                        if(board[row][col - 1] == pc1 &&
                           board[row][col - 2] == pc1 &&
                           board[row][col - 3] == pc1) {
                            result += 50;
                            //System.out.println("W+: " + row + " " + col);
                        }
                        else if(board[row][col - 1] == pc2 &&
                                board[row][col - 2] == pc2 &&
                                board[row][col - 3] == pc2) {
                            result -= 50;
                            //System.out.println("W-: " + row + " " + col);
                        }
                    }

                    // Check north west
                    if(col >= 3 && row >= 3) {
                        if(board[row - 1][col - 1] == pc1 &&
                           board[row - 2][col - 2] == pc1 &&
                           board[row - 3][col - 3] == pc1) {
                            result += 50;
                            //System.out.println("NW+: " + row + " " + col);
                        }
                        else if(board[row - 1][col - 1] == pc2 &&
                                board[row - 2][col - 2] == pc2 &&
                                board[row - 3][col - 3] == pc2) {
                            result -= 50;
                            //System.out.println("NW-: " + row + " " + col);
                        }
                    }
                }
            }
        }
        
        return result;
    }
}

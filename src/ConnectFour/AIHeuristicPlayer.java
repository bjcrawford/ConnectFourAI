
package ConnectFour;

import com.gaurav.tree.KAryTree;
import com.gaurav.tree.NodeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


/**
 * This player is an AI that makes use of the minimax technique.
 * This AI is an improved version of the AIMinimax2Player that makes 
 * use of a heuristic function that is based on each player's number 
 * of "open-triples" (see method for details) to evaluate neutral 
 * states (no win/loss). It is capable of looking n moves ahead. When 
 * evaluating the score at a given state, those win/loss states that 
 * are closer to the root in the state space are given a larger weight 
 * than states that are further from the root. This allows the AI to 
 * differentiate between a win in two moves versus a win in four moves 
 * and a loss in one move versus a loss in three moves.
 */
public class AIHeuristicPlayer extends AbstractPlayer {
    
    private int lookAhead;
    private ConnectFourBoardState CFBS[];
    private KAryTree<ConnectFourBoardState> stateSpace;
    
    public AIHeuristicPlayer(int pieceColor, int lookAhead, ConnectFourBoard cfb) {
        
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
        int opc = (pieceColor % 2) + 1; // Opponents piece color
        CFBS = new ConnectFourBoardState[lookAhead + 1];
        stateSpace = new KAryTree<>(cfb.getWidth());
        
        // Create and insert the root
        CFBS[0] = new ConnectFourBoardState(cfb.getBoard(), opc);
        stateSpace.add(CFBS[0]);
        
        // Build the state space with a depth of three
        // starting from the first level (the root is zero level)
        try {
            buildStateSpaceRecursive(lookAhead - 1, 1);
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
        
        if(verbose) {
            System.out.println("Player " + pieceColor + " Move Info:");
            System.out.println("\n  AI Minimax 3");
            System.out.println("\n  Possible Moves:\n");
        }
        while(lotLI.hasNext() && (c = lotLI.next()).getDepth() < 2) {
            
            if(verbose) {
                System.out.println("    Column: " + (c.getColInserted()+1) + 
                                   " Score: " + c.getScore());
            }
            
            
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
        
        if(verbose) {
            System.out.println("\n  Considered Moves:\n");
        }
        for(ConnectFourBoardState cfbs : list) {
            if(verbose) {
                System.out.println("    Column: " + (cfbs.getColInserted()+1) +
                                   " Score: " + cfbs.getScore());
            }
        }
        
        int numMoves = list.size();
        Random rand = new Random(System.currentTimeMillis());
        int choice = rand.nextInt(numMoves);
        result = list.get(choice).getColInserted();
        
        /*
        lotLI = lotL.listIterator();
        System.out.println("\n  State Space:");
        while(lotLI.hasNext() && (c = lotLI.next()).getDepth() < 4) {
            System.out.println("    Path: " + c.getPath() + " Score: " + c.getScore());
        }
        */
        
        System.out.println();
        
        
        return result;
    }
    /**
     * Builds the state space for the getNextMove() method. 
     * 
     * @param remainingDepth The remaining depth to continue building
     * @param currentDepth The current depth within the tree
     * @throws NodeNotFoundException 
     */
    public void buildStateSpaceRecursive(int remainingDepth, int currentDepth) throws NodeNotFoundException {
        
        if(remainingDepth >= 0) {
            
            int k = cfb.getWidth();
            for(int i = 0; i < k; i++) {
                
                // Create and insert i-th child of the parent node
                CFBS[currentDepth] = CFBS[currentDepth - 1].createChildState(i);

                // If the child resulted in a successful insert (i.e., insert
                // into a column with available space), add the child to the 
                // tree and create any children it may have.
                // If the child resulted in a failed insert, its children
                // are effectively pruned from the tree.
                if(CFBS[currentDepth].getInsertSuccess()) {
                    
                    stateSpace.add(CFBS[currentDepth - 1], CFBS[currentDepth]);
                    
                    // If this state doesn't result in a win, continue building
                    // the state space.
                    if(CFBS[currentDepth].checkWin() == 0)
                        buildStateSpaceRecursive(remainingDepth - 1, currentDepth + 1);
                    // If the state results in a win, update the node score. The node 
                    // score is multiplied by (maxDepth + 1) to give the score a weight. 
                    // This gives priority to the moves nearest the root in the state space.
                    else
                        CFBS[currentDepth].setScore((remainingDepth + 1) * evaluateBoardState(CFBS[currentDepth]));
                    
                    // Evaluate scores at the leaves of the tree
                    if(remainingDepth == 0)
                        CFBS[currentDepth].setScore(evaluateBoardState(CFBS[currentDepth]));
                   
                    // If the parent has not had a score set, give it the child's score
                    if(!CFBS[currentDepth - 1].getScoreEvaluated())
                        CFBS[currentDepth - 1].setScore(CFBS[currentDepth].getScore());
                    
                    // On even depths, give the parent the min score
                    // On odd depths, give the parent the max score
                    if(currentDepth % 2 == 0) {
                        
                        if(CFBS[currentDepth].getScore() < CFBS[currentDepth - 1].getScore())
                            CFBS[currentDepth - 1].setScore(CFBS[currentDepth].getScore());
                    }
                    else {
                        
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
        int opc = (pieceColor % 2) + 1;
        int win = cfbs.checkWin();
        if(win == pieceColor)
            result = 1000;
        else if(win == opc)
            result = -1000;
        else
            result = checkOpenTriples(cfbs);
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
        int pc = cfbs.getPieceColor();
        int opc = (pc % 2) + 1;
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
                        if(board[row - 1][col + 1] == pc &&
                           board[row - 2][col + 2] == pc &&
                           board[row - 3][col + 3] == pc) {
                            result += 50;
                            //System.out.println("NE+: " + row + " " + col);
                        }
                        else if(board[row - 1][col + 1] == opc &&
                                board[row - 2][col + 2] == opc &&
                                board[row - 3][col + 3] == opc) {
                            result -= 50;
                            //System.out.println("NE-: " + row + " " + col);
                        }
                    }

                    // Check east
                    if(col < (width - 3)) {
                        if(board[row][col + 1] == pc &&
                           board[row][col + 2] == pc &&
                           board[row][col + 3] == pc) {
                            result += 50;
                            //System.out.println("E+: " + row + " " + col);
                        }
                        else if(board[row][col + 1] == opc &&
                                board[row][col + 2] == opc &&
                                board[row][col + 3] == opc) {
                            result -= 50;
                            //System.out.println("E-: " + row + " " + col);
                        }
                    }

                    // Check south east
                    if(col < (width - 3) && row < (height - 3)) {
                        if(board[row + 1][col + 1] == pc &&
                           board[row + 2][col + 2] == pc &&
                           board[row + 3][col + 3] == pc) {
                            result += 50;
                            //System.out.println("SE+: " + row + " " + col);
                        }
                        else if(board[row + 1][col + 1] == opc &&
                                board[row + 2][col + 2] == opc &&
                                board[row + 3][col + 3] == opc) {
                            result -= 50;
                            //System.out.println("SE-: " + row + " " + col);
                        }
                    }

                    // Check south
                    if(row < (height - 3)) {
                        if(board[row + 1][col] == pc &&
                           board[row + 2][col] == pc &&
                           board[row + 3][col] == pc) {
                            result += 50;
                            //System.out.println("S+: " + row + " " + col);
                        }
                        else if(board[row + 1][col] == opc &&
                                board[row + 2][col] == opc &&
                                board[row + 3][col] == opc) {
                            result -= 50;
                            //System.out.println("S-: " + row + " " + col);
                        }
                    }

                    // Check south west
                    if(col >= 3 && row < (height - 3)) {
                        if(board[row + 1][col - 1] == pc &&
                           board[row + 2][col - 2] == pc &&
                           board[row + 3][col - 3] == pc) {
                            result += 50;
                            //System.out.println("SW+: " + row + " " + col);
                        }
                        else if(board[row + 1][col - 1] == opc &&
                                board[row + 2][col - 2] == opc &&
                                board[row + 3][col - 3] == opc) {
                            result -= 50;
                            //System.out.println("SW-: " + row + " " + col);
                        }
                    }

                    // Check west
                    if(col >= 3) {
                        if(board[row][col - 1] == pc &&
                           board[row][col - 2] == pc &&
                           board[row][col - 3] == pc) {
                            result += 50;
                            //System.out.println("W+: " + row + " " + col);
                        }
                        else if(board[row][col - 1] == opc &&
                                board[row][col - 2] == opc &&
                                board[row][col - 3] == opc) {
                            result -= 50;
                            //System.out.println("W-: " + row + " " + col);
                        }
                    }

                    // Check north west
                    if(col >= 3 && row >= 3) {
                        if(board[row - 1][col - 1] == pc &&
                           board[row - 2][col - 2] == pc &&
                           board[row - 3][col - 3] == pc) {
                            result += 50;
                            //System.out.println("NW+: " + row + " " + col);
                        }
                        else if(board[row - 1][col - 1] == opc &&
                                board[row - 2][col - 2] == opc &&
                                board[row - 3][col - 3] == opc) {
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

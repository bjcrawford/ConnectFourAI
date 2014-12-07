package ConnectFour;

import com.gaurav.tree.KAryTree;
import com.gaurav.tree.NodeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * This player is an AI that makes use of the minimax technique.
 * It is capable of looking n moves ahead. No other factors
 * besides win or lose have been considered. If there is a tie
 * between the scores of possible moves, a move is chosen at 
 * random.
 */
public class AIMinimax1Player extends AbstractPlayer {

    /* The number of moves to look ahead */
    private int lookAhead;
    
    /* Used as temporary storage while building the state space */
    private ConnectFourBoardState CFBS[];
    
    /* A k-ary tree to hold the state space */
    private KAryTree<ConnectFourBoardState> stateSpace;

    /**
     * Creates an AI minimax type 1 player.
     * 
     * @param pieceColor The piece color for the player
     * @param lookAhead The number of move to look ahead
     * @param cfb The Connect Four board
     */
    public AIMinimax1Player(int pieceColor, int lookAhead, ConnectFourBoard cfb) {

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

        int bestMove;
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
        } catch (NodeNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            return -1;
        }

        List<ConnectFourBoardState> lotL = stateSpace.levelOrderTraversal();
        ListIterator<ConnectFourBoardState> lotLI = lotL.listIterator();

        List<ConnectFourBoardState> list = new ArrayList<>();
        int highest = -101;

        ConnectFourBoardState c = lotLI.next();
        if(verbose) {
            System.out.println("Player " + pieceColor + " Move Info:");
            System.out.println("\n  AI Minimax 1");
            System.out.println("\n  Possible Moves:\n");
        }
        while (lotLI.hasNext() && (c = lotLI.next()).getDepth() < 2) {
            if(verbose) {
                System.out.println("    Column: " + (c.getColInserted()+1) +
                                   " Score: " + c.getScore());
            }

            if (c.getScore() > highest) {
                highest = c.getScore();
                list.clear();
                list.add(c);
            } else if (c.getScore() == highest) {
                list.add(c);
            }

        }
        if(verbose) {
            System.out.println("\n  Considered Moves:\n");
        }
        for (ConnectFourBoardState cfbs : list) {
            if(verbose) {
                System.out.println("    Column: " + (cfbs.getColInserted()+1) +
                                   " Score: " + cfbs.getScore());
            }
        }
        if(verbose) {
            System.out.println();
        }

        int numMoves = list.size();
        Random rand = new Random(System.currentTimeMillis());
        int choice = rand.nextInt(numMoves);
        bestMove = list.get(choice).getColInserted();

        /* View a few levels of the state space
         lotLI = lotL.listIterator();
         System.out.println("\n  State Space:");
         while(lotLI.hasNext() && (c = lotLI.next()).getDepth() < 4) {
         System.out.println("    Path: " + c.getPath() + " Score: " + c.getScore());
         }
         */

        return bestMove;
    }

    /**
     * Builds the state space for the getNextMove() method.
     *
     * @param remainingDepth The remaining depth to continue building
     * @param currentDepth The current depth within the tree
     * @throws NodeNotFoundException
     */
    public void buildStateSpaceRecursive(int remainingDepth, int currentDepth) throws NodeNotFoundException {

        if (remainingDepth >= 0) {
            int k = cfb.getWidth();
            for (int i = 0; i < k; i++) {
                // Create and insert i-th child of the parent node
                CFBS[currentDepth] = CFBS[currentDepth - 1].createChildState(i);

                // If the child resulted in a successful insert (i.e., insert
                // into a column with available space), add the child to the 
                // tree and create any children it may have.
                // If the child resulted in a failed insert, its children
                // are effectively pruned from the tree.
                if (CFBS[currentDepth].getInsertSuccess()) {
                    stateSpace.add(CFBS[currentDepth - 1], CFBS[currentDepth]);

                    // If this state does not result in a win, continue building
                    // the state space.
                    if (evaluateBoardState(CFBS[currentDepth]) == 0) {
                        buildStateSpaceRecursive(remainingDepth - 1, currentDepth + 1);
                    }
                    // If the state results in a win, update the node score
                    else {
                        CFBS[currentDepth].setScore(evaluateBoardState(CFBS[currentDepth]));
                    }

                    // Evaluate scores at the leaves of the tree
                    if (remainingDepth == 0) {
                        CFBS[currentDepth].setScore(evaluateBoardState(CFBS[currentDepth]));
                    }

                    // If the parent has not had a score set, give it the child's score
                    if (!CFBS[currentDepth - 1].getScoreEvaluated()) {
                        CFBS[currentDepth - 1].setScore(CFBS[currentDepth].getScore());
                    }

                    // On even depths, give the parent the min score
                    // On odd depths, give the parent the max score
                    if (currentDepth % 2 == 0) {
                        if (CFBS[currentDepth].getScore() < CFBS[currentDepth - 1].getScore()) {
                            CFBS[currentDepth - 1].setScore(CFBS[currentDepth].getScore());
                        }
                    } 
                    else {
                        if (CFBS[currentDepth].getScore() > CFBS[currentDepth - 1].getScore()) {
                            CFBS[currentDepth - 1].setScore(CFBS[currentDepth].getScore());
                        }
                    }
                }
            }
        }
    }

    /**
     * Evaluates a connect four board state.
     *
     * @param cfbs The board state
     * @return 0 for no win, 100 for player win, -100 for opponent win
     */
    public int evaluateBoardState(ConnectFourBoardState cfbs) {
        int result;
        int opc = (pieceColor % 2) + 1; // Opponents piece color
        int win = cfbs.checkWin();
        if (win == pieceColor) {
            result = 100;
        } 
        else if(win == opc) {
            result = -100;
        }
        else {
            result = 0;
        }
        return result;

    }
}

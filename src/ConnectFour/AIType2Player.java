package ConnectFour;

import com.gaurav.tree.KAryTree;
import com.gaurav.tree.NodeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * This is the Type 2 for our Connect Four implementation. This AI uses a
 * slightly improved minimax algorithm. It is capable of looking n moves ahead.
 * When evaluating the score at a given node, those nodes closer to the root are
 * given a larger weight than nodes that are further from the root. This allows
 * the AI to differentiate between a win in two moves versus a win in four
 * moves.
 */
public class AIType2Player extends AbstractPlayer {

    private int lookAhead;
    private ConnectFourBoardState CFBS[];
    private KAryTree<ConnectFourBoardState> stateSpace;

    public AIType2Player(int pieceColor, int lookAhead, ConnectFourBoard cfb) {

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
        } catch (NodeNotFoundException e) {
            return -1;
        }

        List<ConnectFourBoardState> lotL = stateSpace.levelOrderTraversal();
        ListIterator<ConnectFourBoardState> lotLI = lotL.listIterator();

        List<ConnectFourBoardState> list = new ArrayList<>();
        int highest = (-100 * lookAhead) - 1;

        ConnectFourBoardState c = lotLI.next();
        int d = 1;
        System.out.println("Player " + pieceColor + " Move Info:");
        System.out.println("\n  AI Type 1");
        System.out.println("\n  Possible Moves:\n");
        while (lotLI.hasNext() && (c = lotLI.next()).getDepth() < 2) {
            System.out.println("    Col: " + c.getColInserted()
                    + " Path: " + c.getPath()
                    + " Score: " + c.getScore());

            if (c.getScore() > highest) {
                highest = c.getScore();
                list.clear();
                list.add(c);
            } else if (c.getScore() == highest) {
                list.add(c);
            }

        }

        System.out.println("\n  Considered Moves:\n");
        for (ConnectFourBoardState cfbs : list) {
            //System.out.println(cfbs);
            System.out.println("    Column: " + cfbs.getColInserted()
                    + " Path: " + cfbs.getPath()
                    + " Score: " + cfbs.getScore());
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

        if (maxDepth >= 0) {
            for (int i = 0; i < k; i++) {
                // Create and insert i-th child of the parent node
                CFBS[currentDepth] = CFBS[currentDepth - 1].createChildState(i);

                // If the child did not result in a failed insert (i.e., insert
                // into full column), add the child to the tree and create
                // any children it may have
                if (!CFBS[currentDepth].getFailedInsert()) {
                    stateSpace.add(CFBS[currentDepth - 1], CFBS[currentDepth]);

                    // If this state doesn't result in a win, continue building
                    // the state space.
                    if (evaluateBoardState(CFBS[currentDepth]) == 0) {
                        buildStateSpaceRecursive(k, maxDepth - 1, currentDepth + 1);
                    }

                    // Evaluate scores at the leaves of the tree
                    if (maxDepth == 0) {
                        CFBS[currentDepth].setScore(evaluateBoardState(CFBS[currentDepth]));
                    }

                    // At any node, if the state results in a win, update the node score
                    // and ignore the value given by the children. The node score is
                    // multiplied by (maxDepth + 1) to give the score a weight. This
                    // gives priority to the moves nearest the root in the state space.
                    if (evaluateBoardState(CFBS[currentDepth]) != 0) {
                        CFBS[currentDepth].setScore((maxDepth + 1) * evaluateBoardState(CFBS[currentDepth]));
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
                    } else {
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
        int win = cfbs.checkWin();
        if (win == 0) {
            result = 0;
        } else if (win == pieceColor) {
            result = 100;
        } else {
            result = -100;
        }
        return result;
    }
}

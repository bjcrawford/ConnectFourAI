package ConnectFour;

import java.util.ArrayList;
import java.util.Random;

/**
 * This player is an AI that makes use of a probabilistic technique. 
 * This AI makes use of a simplified Monte Carlo Tree Search to 
 * determine the best next move to make. When a move is requested 
 * from the AI, it considers the possible moves that can be made, 
 * and from each of those possible moves a given number of randomly 
 * played games are simulated to determine the probability of a win 
 * or loss for the considered possible move.
 */
public class AIType5Player extends AbstractPlayer {

    private int numOfSimGames;

    public AIType5Player(int pieceColor, int numOfSimGames, ConnectFourBoard cfb) {

        super(pieceColor, false, cfb);
        this.numOfSimGames = numOfSimGames;
    }

    /**
     * Gets the AI's next move.
     *
     * @return The column the AI wishes to insert into
     */
    @Override
    public int getNextMove() {

        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;
        int currentScore;
        ConnectFourBoard newCFB = new ConnectFourBoard(cfb);
        ArrayList<Integer> moves = cfb.getPossibleMoves();
        
        System.out.println("Player " + pieceColor + " Move Info:");
        System.out.println("\n  AI MonteCarlo");
        System.out.println("\n  Possible Moves:\n");
        for(int move : moves) {
            
            System.out.println("    Column: " + (move+1));
        }
        System.out.println("\n  Considered Moves:\n");
        for(int move : moves) {
            
            currentScore = evaluateMove(newCFB, move);
            if(currentScore > bestScore)
            {
                bestScore = currentScore;
                bestMove = move;
            }
            System.out.println("    Column: " + (move+1) + 
                               " Score: " + currentScore);
        }
        System.out.println();

        return bestMove;
    }

    /**
     * Evaluates a board state by performing a given number of 
     * simulated games and recording the wins and losses. The
     * value returned is a score for the state in the range 
     * [-100, 100]
     * 
     * @param CFB
     * @param move
     * @return 
     */
    public int evaluateMove(ConnectFourBoard CFB, int move)
    {
        int score = 0;
        int win = CFB.checkWin();
        int opc = (pieceColor % 2) + 1; // Opponents piece color
        
        if(win == -1)
            score = 0;
        else if(win == pieceColor)
            score = 100;
        else if(win == opc)
            score = -100;
        else {
            
            for(int game = 0; game < numOfSimGames; game++)
            {
                ConnectFourBoard newCFB = new ConnectFourBoard(CFB);
                newCFB.insertPiece(pieceColor, move);
                int winner = simulateGame(newCFB);
                if(winner == pieceColor)
                    score++;
                else if(winner == opc)
                    score--;
            }
            score = score / (numOfSimGames / 100);
        }
        
        return score;
    }
    
    /**
     * Simulates a game from a given board using random insertions.
     * 
     * @param CFB The connect four board
     * @return -1 if draw, 1 if red win, 2 if black win
     */
    public int simulateGame(ConnectFourBoard CFB)
    {
        int move;
        int count = 0;
        int opc = (pieceColor % 2) + 1; // Opponents piece color
        Random rand = new Random(System.currentTimeMillis());
        while(CFB.checkWin() == 0)
        {
            ArrayList<Integer> moves = CFB.getPossibleMoves();
            move = rand.nextInt(moves.size());
            if(count % 2 == 0)
                CFB.insertPiece(opc, moves.get(move));
            else
                CFB.insertPiece(pieceColor, moves.get(move));
            count++;
        }
        
        return CFB.checkWin();
    }
}

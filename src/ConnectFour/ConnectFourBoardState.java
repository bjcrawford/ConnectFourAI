package ConnectFour;

/**
 * A class representing a Connect Four board state. This class is
 * used for representing states within the state space searches 
 * of the AIs which make use of a state space search.
 */
public class ConnectFourBoardState {

    /**
     * The score associated with the board state
     */
    private int score;
    
    /**
     * The height of the board
     */
    private int height;
    
    /**
     * The width of the board
     */
    private int width;
    
    /**
     * A two-dimensional array to hold the contents of the board
     */
    private int board[][];
    
    /**
     * A boolean to represented the win state of the board
     */
    private boolean win;
    
    /**
     * The depth of the state within a state space tree
     */
    private int depth;
    
    /**
     * The path from the root of the state space tree to this state
     */
    private String path;
    
    /**
     * The piece color of the last player to insert
     */
    private int pieceColor;
    
    /**
     * The column of the last insert
     */
    private int colInserted;
    
    /**
     * A boolean to represent the success of the last insert
     */
    private boolean insertSuccess;
    
    /**
     * A boolean to represent if the state's score has been evaluated
     */
    private boolean scoreEvaluated;
    
    /**
     * The alpha value of the state
     */
    private int alpha = -100;
    
    /**
     * The beta value of the state
     */
    private int beta = 100;

    /**
     * Creates a Connect Four board state using the given board and
     * piece color. This should only be used to create the root
     * of a state space. To create children of the root, use the
     * createChildState method.
     * 
     * @param board The board to create the state from
     * @param pieceColor The piece color of the last player to insert
     */
    public ConnectFourBoardState(int board[][], int pieceColor) {

        this.score = 0;
        this.height = board.length;
        this.width = board[0].length;
        this.board = board;
        this.win = false;
        this.depth = 0;
        this.path = "0";
        this.pieceColor = pieceColor;
        this.colInserted = -1;
        this.insertSuccess = true;
        this.scoreEvaluated = false;
    }

    /**
     * Creates a copy of a Connect Four board state. This method
     * is for internal use only and should only be called from 
     * the createChildState method
     * 
     * @param toCopy The Connect Four board state to copy
     */
    public ConnectFourBoardState(ConnectFourBoardState toCopy) {

        this.score = 0;
        this.height = toCopy.getHeight();
        this.width = toCopy.getWidth();
        this.board = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.board[i][j] = toCopy.getBoard()[i][j];
            }
        }
        this.win = toCopy.getWin();
        this.depth = toCopy.getDepth();
        this.path = toCopy.getPath();
        this.colInserted = -1;
        this.insertSuccess = true;
        this.scoreEvaluated = false;
    }

    /**
     * Creates a child Connect Four board state from a parent
     * and inserts the appropriate piece color into the given
     * column.
     * 
     * @param col The column to insert into during the creation
     *            of the child state
     * @return The child Connect Four board state
     */
    public ConnectFourBoardState createChildState(int col) {

        ConnectFourBoardState child = new ConnectFourBoardState(this);

        child.score = 0;
        child.pieceColor = (this.getPieceColor() % 2) + 1;

        boolean inserted = false;
        for (int row = child.getBoard().length - 1; row >= 0; row--) {
            if (child.getBoard()[row][col] == 0) {
                child.getBoard()[row][col] = child.pieceColor;
                inserted = true;
                break;
            }
        }

        if (inserted) {
            child.win = child.checkWin() != 0;
        }
        child.depth++;
        child.path = this.path + "," + col;
        child.colInserted = col;
        child.insertSuccess = inserted;
        child.scoreEvaluated = false;

        return child;
    }

    /**
     * Returns the score of the board state.
     * 
     * @return The board state's score
     */
    public int getScore() {

        return score;
    }

    /**
     * Returns the height of the board state's board.
     * 
     * @return The board state's board height
     */
    public int getHeight() {

        return height;
    }

    /**
     * Returns the width of the board state's board.
     * 
     * @return The board state's board width
     */
    public int getWidth() {

        return width;
    }

    /**
     * Returns the two-dimensional array representation of the
     * board state's board
     * 
     * @return The two-dimensional array representing the board
     */
    public int[][] getBoard() {

        return board;
    }

    /**
     * Returns the win state of the board.
     * 
     * @return true if win, false otherwise
     */
    public boolean getWin() {

        return win;
    }

    /**
     * Returns the depth of the board state with the state space tree.
     * 
     * @return The board state's depth 
     */
    public int getDepth() {

        return depth;
    }

    /**
     * Returns the path from the root of the state space tree to
     * the board state.
     * 
     * @return The path from root to board state
     */
    public String getPath() {

        return path;
    }

    
    public int getPieceColor() {

        return pieceColor;
    }

    public int getColInserted() {

        return colInserted;
    }

    public boolean getInsertSuccess() {

        return insertSuccess;
    }

    public boolean getScoreEvaluated() {

        return scoreEvaluated;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getBeta() {
        return beta;
    }
    
    public void setScore(int newScore) {

        this.score = newScore;
        this.scoreEvaluated = true;
    }

    public void setAlpha(int Alpha) {
        this.alpha = Alpha;
    }

    public void setBeta(int Beta) {
        this.beta = Beta;
    }

    /**
     * Checks the game board for a win.
     *
     * @return 0 if no win, 1 if red win, 2 if black win
     */
    public int checkWin() {

        int result = 0;

        // Check horizontals
        for (int row = 0; row < board.length && result == 0; row++) {
            for (int col = 3; col < board[0].length && result == 0; col++) {
                // Red team win
                if (board[row][col - 3] == 1
                        && board[row][col - 2] == 1
                        && board[row][col - 1] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // Black team win
                if (board[row][col - 3] == 2
                        && board[row][col - 2] == 2
                        && board[row][col - 1] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
            }
        }

        // Check verticals
        for (int row = 3; row < board.length && result == 0; row++) {
            for (int col = 0; col < board[0].length && result == 0; col++) {
                // Red team win
                if (board[row - 3][col] == 1
                        && board[row - 2][col] == 1
                        && board[row - 1][col] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // Black team win
                if (board[row - 3][col] == 2
                        && board[row - 2][col] == 2
                        && board[row - 1][col] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
            }
        }

        // Check positive slope diagonals
        for (int row = 3; row < board.length && result == 0; row++) {
            for (int col = 3; col < board[0].length && result == 0; col++) {
                // Red team win
                if (board[row][col - 3] == 1
                        && board[row - 1][col - 2] == 1
                        && board[row - 2][col - 1] == 1
                        && board[row - 3][col] == 1) {
                    result = 1;
                }
                // Black team win
                if (board[row][col - 3] == 2
                        && board[row - 1][col - 2] == 2
                        && board[row - 2][col - 1] == 2
                        && board[row - 3][col] == 2) {
                    result = 2;
                }
            }
        }

        // Check negative slope diagonals
        for (int row = 3; row < board.length && result == 0; row++) {
            for (int col = 3; col < board[0].length && result == 0; col++) {
                // Red team win
                if (board[row - 3][col - 3] == 1
                        && board[row - 2][col - 2] == 1
                        && board[row - 1][col - 1] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // Black team win
                if (board[row - 3][col - 3] == 2
                        && board[row - 2][col - 2] == 2
                        && board[row - 1][col - 1] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
            }
        }

        return result;
    }

    public int checkThree() {

        int result = 0;

        // Check horizontals
        for (int row = 0; row < board.length && result == 0; row++) {
            for (int col = 3; col < board[0].length && result == 0; col++) {
                // 0rrr
                if (board[row][col - 3] == 0
                        && board[row][col - 2] == 1
                        && board[row][col - 1] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // 0bbb
                if (board[row][col - 3] == 0
                        && board[row][col - 2] == 2
                        && board[row][col - 1] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
                //rrr0
                if (board[row][col - 3] == 1
                        && board[row][col - 2] == 1
                        && board[row][col - 1] == 1
                        && board[row][col] == 0) {
                    result = 1;
                }
                // bbb0
                if (board[row][col - 3] == 2
                        && board[row][col - 2] == 2
                        && board[row][col - 1] == 2
                        && board[row][col] == 0) {
                    result = 2;
                }
                //r0rr
                if (board[row][col - 3] == 1
                        && board[row][col - 2] == 0
                        && board[row][col - 1] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // b0bb
                if (board[row][col - 3] == 2
                        && board[row][col - 2] == 0
                        && board[row][col - 1] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
                //rr0r
                if (board[row][col - 3] == 1
                        && board[row][col - 2] == 1
                        && board[row][col - 1] == 0
                        && board[row][col] == 1) {
                    result = 1;
                }
                // bb0b
                if (board[row][col - 3] == 2
                        && board[row][col - 2] == 2
                        && board[row][col - 1] == 0
                        && board[row][col] == 2) {
                    result = 2;
                }
            }
        }

        // Check verticals
        for (int row = 3; row < board.length && result == 0; row++) {
            for (int col = 0; col < board[0].length && result == 0; col++) {
                // 0rrr
                if (board[row - 3][col] == 0
                        && board[row - 2][col] == 1
                        && board[row - 1][col] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // 0bbb
                if (board[row - 3][col] == 0
                        && board[row - 2][col] == 2
                        && board[row - 1][col] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
                // rrr0
                if (board[row - 3][col] == 1
                        && board[row - 2][col] == 1
                        && board[row - 1][col] == 1
                        && board[row][col] == 0) {
                    result = 1;
                }
                // bbb0
                if (board[row - 3][col] == 2
                        && board[row - 2][col] == 2
                        && board[row - 1][col] == 2
                        && board[row][col] == 0) {
                    result = 2;
                }
                //r0rr
                if (board[row - 3][col] == 1
                        && board[row - 2][col] == 0
                        && board[row - 1][col] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // b0bb
                if (board[row - 3][col] == 2
                        && board[row - 2][col] == 0
                        && board[row - 1][col] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
                // rr0r
                if (board[row - 3][col] == 1
                        && board[row - 2][col] == 1
                        && board[row - 1][col] == 0
                        && board[row][col] == 1) {
                    result = 1;
                }
                // bb0b
                if (board[row - 3][col] == 2
                        && board[row - 2][col] == 2
                        && board[row - 1][col] == 0
                        && board[row][col] == 2) {
                    result = 2;
                }
            }
        }

        // Check negative slope diagonals
        for (int row = 3; row < board.length && result == 0; row++) {
            for (int col = 3; col < board[0].length && result == 0; col++) {
                // 0rrr
                if (board[row][col - 3] == 0
                        && board[row - 1][col - 2] == 1
                        && board[row - 2][col - 1] == 1
                        && board[row - 3][col] == 1) {
                    result = 1;
                }
                // 0bbb
                if (board[row][col - 3] == 0
                        && board[row - 1][col - 2] == 2
                        && board[row - 2][col - 1] == 2
                        && board[row - 3][col] == 2) {
                    result = 2;
                }
                //rrr0
                if (board[row][col - 3] == 1
                        && board[row - 1][col - 2] == 1
                        && board[row - 2][col - 1] == 1
                        && board[row - 3][col] == 0) {
                    result = 1;
                }
                // bbb0
                if (board[row][col - 3] == 2
                        && board[row - 1][col - 2] == 2
                        && board[row - 2][col - 1] == 2
                        && board[row - 3][col] == 0) {
                    result = 2;
                }
                //r0rr
                if (board[row][col - 3] == 1
                        && board[row - 1][col - 2] == 0
                        && board[row - 2][col - 1] == 1
                        && board[row - 3][col] == 1) {
                    result = 1;
                }
                // b0bb
                if (board[row][col - 3] == 2
                        && board[row - 1][col - 2] == 0
                        && board[row - 2][col - 1] == 2
                        && board[row - 3][col] == 2) {
                    result = 2;
                }
                //rr0r
                if (board[row][col - 3] == 1
                        && board[row - 1][col - 2] == 1
                        && board[row - 2][col - 1] == 0
                        && board[row - 3][col] == 1) {
                    result = 1;
                }
                // bb0b
                if (board[row][col - 3] == 2
                        && board[row - 1][col - 2] == 2
                        && board[row - 2][col - 1] == 0
                        && board[row - 3][col] == 2) {
                    result = 2;
                }
            }
        }

        // Check positive slope diagonals
        for (int row = 3; row < board.length && result == 0; row++) {
            for (int col = 3; col < board[0].length && result == 0; col++) {
                //0rrr
                if (board[row - 3][col - 3] == 0
                        && board[row - 2][col - 2] == 1
                        && board[row - 1][col - 1] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // 0bbb
                if (board[row - 3][col - 3] == 0
                        && board[row - 2][col - 2] == 2
                        && board[row - 1][col - 1] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
                //rrr0
                if (board[row - 3][col - 3] == 1
                        && board[row - 2][col - 2] == 1
                        && board[row - 1][col - 1] == 1
                        && board[row][col] == 0) {
                    result = 1;
                }
                // bbb0
                if (board[row - 3][col - 3] == 2
                        && board[row - 2][col - 2] == 2
                        && board[row - 1][col - 1] == 2
                        && board[row][col] == 0) {
                    result = 2;
                }
                //rr0r
                if (board[row - 3][col - 3] == 1
                        && board[row - 2][col - 2] == 1
                        && board[row - 1][col - 1] == 0
                        && board[row][col] == 1) {
                    result = 1;
                }
                // bb0b
                if (board[row - 3][col - 3] == 2
                        && board[row - 2][col - 2] == 2
                        && board[row - 1][col - 1] == 0
                        && board[row][col] == 2) {
                    result = 2;
                }
                //r0rr
                if (board[row - 3][col - 3] == 1
                        && board[row - 2][col - 2] == 0
                        && board[row - 1][col - 1] == 1
                        && board[row][col] == 1) {
                    result = 1;
                }
                // b0bb
                if (board[row - 3][col - 3] == 2
                        && board[row - 2][col - 2] == 0
                        && board[row - 1][col - 1] == 2
                        && board[row][col] == 2) {
                    result = 2;
                }
            }
        }

        return result;
    }

    public int checkLineCon() {
        int result = 0;
        for (int row = 0; row < board.length && result == 0; row++) {
            for (int col = 3; col < board[0].length && result == 0; col++) {
                // 0rr0
                if (row == 0) {
                    if (board[row][col - 3] == 0
                            && board[row][col - 2] == 1
                            && board[row][col - 1] == 1
                            && board[row][col] == 0) {
                        result = 1;
                    }
                    // 0bb0
                    if (board[row][col - 3] == 0
                            && board[row][col - 2] == 2
                            && board[row][col - 1] == 2
                            && board[row][col] == 0) {
                        result = 2;
                    }
                }else{
                    if (board[row][col - 3] == 0
                            && board[row][col - 2] == 1
                            && board[row][col - 1] == 1
                            && board[row][col] == 0
                            &&(board[row - 1][col - 3] == 1 || board[row - 1][col - 3] == 2)
                            &&(board[row - 1][col] == 1 || board[row - 1][col] == 2)) {
                        result = 1;
                    }
                    // 0bb0
                    if (board[row][col - 3] == 0
                            && board[row][col - 2] == 2
                            && board[row][col - 1] == 2
                            && board[row][col] == 0
                            &&(board[row - 1][col - 3] == 1 || board[row - 1][col - 3] == 2)
                            &&(board[row - 1][col] == 1 || board[row - 1][col] == 2)) {
                        result = 2;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "score: " + score + "\n";
        result += "depth: " + depth + "\n";
        result += "path: " + path + "\n";
        result += "colInserted: " + colInserted + "\n";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;

    }
}

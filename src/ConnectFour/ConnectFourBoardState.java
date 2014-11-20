package ConnectFour;

/**
 *
 * @author bcrawford
 */
public class ConnectFourBoardState {

    private int score;
    private int height;
    private int width;
    private int board[][];
    private boolean win;
    private int depth;
    private String path;
    private int pieceColor;
    private int colInserted;
    private boolean failedInsert;
    private boolean scoreEvaluated;
    private int alpha = -100;
    private int beta = 100;

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
        this.failedInsert = false;
        this.scoreEvaluated = false;
    }

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
        this.failedInsert = false;
        this.scoreEvaluated = false;
    }

    public ConnectFourBoardState createChildState(int col) {

        ConnectFourBoardState child = new ConnectFourBoardState(this);

        child.score = 0;
        child.pieceColor = ((this.getPieceColor() + 2) % 2) + 1;

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
        child.failedInsert = !inserted;
        child.scoreEvaluated = false;

        return child;
    }

    public int getScore() {

        return score;
    }

    public int getHeight() {

        return height;
    }

    public int getWidth() {

        return width;
    }

    public int[][] getBoard() {

        return board;
    }

    public boolean getWin() {

        return win;
    }

    public int getDepth() {

        return depth;
    }

    public String getPath() {

        return path;
    }

    public int getPieceColor() {

        return pieceColor;
    }

    public int getColInserted() {

        return colInserted;
    }

    public boolean getFailedInsert() {

        return failedInsert;
    }

    public boolean getScoreEvaluated() {

        return scoreEvaluated;
    }

    public void setScore(int newScore) {

        this.score = newScore;
        this.scoreEvaluated = true;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getBeta() {
        return beta;
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

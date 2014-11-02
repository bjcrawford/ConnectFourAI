
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
    private boolean child;
    private int colInserted;
    private boolean failedInsert;
    
    public ConnectFourBoardState(int score, int board[][], int depth, String path, boolean win) {
        
        this.score = score;
        this.height = board.length;
        this.width = board[0].length;
        this.board = board;
        this.win = win;
        this.depth = depth;
        this.path = path;
        this.child = false;
        this.colInserted = -1;
        this.failedInsert = false;
    }
    
    public ConnectFourBoardState(ConnectFourBoardState toCopy) {
        
        this.score = toCopy.getScore();
        this.height = toCopy.getHeight();
        this.width = toCopy.getWidth();
        this.board = new int[height][width];
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                this.board[i][j] = toCopy.getBoard()[i][j];
        this.win = toCopy.getWin();
        this.depth = toCopy.getDepth();
        this.path = toCopy.getPath();
        this.child = false;
        this.colInserted = -1;
        this.failedInsert = false;
    }
    
    public ConnectFourBoardState createChildState(int pieceColor, int col) {
        
        ConnectFourBoardState child = new ConnectFourBoardState(this);
        child.score = 0;
        boolean inserted = false; 
        for(int row = child.getBoard().length - 1; row >= 0; row--)
        {
            if(child.getBoard()[row][col] == 0)
            {
                child.getBoard()[row][col] = pieceColor;
                inserted = true;
                break;
            }
        }
        if(inserted)
            child.win = checkWin(child.getBoard()) != 0;
        child.depth++;
        child.path = this.path + "," + col;
        child.child = true;
        child.colInserted = col;
        child.failedInsert = !inserted;
        
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
    
    public int getColInserted() {
        
        return colInserted;
    }
    
    public boolean getFailedInsert() {
        
        return failedInsert;
    }
    
    public void setScore(int newScore) {
        
        this.score = newScore;
    }
    
    /**
     * Checks the game board for a win.
     * 
     * @return 0 if no win, 1 if red win, 2 if black win
     */
    public static int checkWin(int[][] board) {
        
        int result = 0;
        
        // Check horizontals
        for(int row = 0; row < board.length && result == 0; row++)
        {
            for(int col = 3; col < board[0].length && result == 0; col++)
            {
                // Red team win
                if(board[row][col - 3] == 1 &&
                   board[row][col - 2] == 1 &&
                   board[row][col - 1] == 1 &&
                   board[row][col]     == 1)
                    result = 1;
                // Black team win
                if(board[row][col - 3] == 2 &&
                   board[row][col - 2] == 2 &&
                   board[row][col - 1] == 2 &&
                   board[row][col]     == 2)
                    result = 2;
            }
        }
        
        // Check verticals
        for(int col = 0; col < board.length && result == 0; col++)
        {
            for(int row = 3; row < board[0].length && result == 0; row++)
            {
                // Red team win
                if(board[row - 3][col] == 1 &&
                   board[row - 2][col] == 1 &&
                   board[row - 1][col] == 1 &&
                   board[row][col]     == 1)
                    result = 1;
                // Black team win
                if(board[row - 3][col] == 2 &&
                   board[row - 2][col] == 2 &&
                   board[row - 1][col] == 2 &&
                   board[row][col]     == 2)
                    result = 2;
            }
        }
        
        // Check positive slope diagonals
        for(int col = 3; col < board.length && result == 0; col++)
        {
            for(int row = 3; row < board[0].length && result == 0; row++)
            {
                // Red team win
                if(board[row]    [col - 3] == 1 &&
                   board[row - 1][col - 2] == 1 &&
                   board[row - 2][col - 1] == 1 &&
                   board[row - 3][col]     == 1)
                    result = 1;
                // Black team win
                if(board[row]    [col - 3] == 2 &&
                   board[row - 1][col - 2] == 2 &&
                   board[row - 2][col - 1] == 2 &&
                   board[row - 3][col]     == 2)
                    result = 2;
            }
        }
        
        // Check negative slope diagonals
        for(int col = 3; col < board.length && result == 0; col++)
        {
            for(int row = 3; row < board[0].length && result == 0; row++)
            {
                // Red team win
                if(board[row - 3][col - 3] == 1 &&
                   board[row - 2][col - 2] == 1 &&
                   board[row - 1][col - 1] == 1 &&
                   board[row]    [col]     == 1)
                    result = 1;
                // Black team win
                if(board[row - 3][col - 3] == 2 &&
                   board[row - 2][col - 2] == 2 &&
                   board[row - 1][col - 1] == 2 &&
                   board[row]    [col]     == 2)
                    result = 2;
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
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
        
    }
}

package ConnectFour;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A class representing the Connect Four game board
 *
 * The array for the board will follow the convention of rows by columns (i.e,
 * board[row][col])
 *
 * The visual representation will look as follows:
 *
 *       0 1 2 3 ... 
 *      ___________ 
 *   0 | 0 0 0 0 
 *   1 | 0 0 2 0 
 *   2 | 0 0 1 0 
 *   3 | 1 1 2 0 
 *  ...|
 *
 * Empty slots: 0, Red pieces: 1, Black pieces: 2
 */
public class ConnectFourBoard {

    /**
     * The height of the board
     */
    private final int height;

    /**
     * The width of the board
     */
    private final int width;

    /**
     * A two-dimensional array to hold the contents of the board
     */
    private final int board[][];
    
    /**
     * The total number of pieces currently in the board
     */
    private int totalPieces;

    /**
     * Creates a Connect Four board representation with a given 
     * width and height for the board.
     *
     * @param height The height of the board
     * @param width The width of the board
     */
    public ConnectFourBoard(int height, int width) {

        this.height = height;
        this.width = width;
        this.board = new int[height][width];
        this.totalPieces = 0;
    }
    
    /**
     * Creates a copy of a Connect Four Board representation.
     * 
     * @param CFB The Connect Four Board to copy
     */
    public ConnectFourBoard(ConnectFourBoard CFB) {
        
        this.height = CFB.getHeight();
        this.width = CFB.getWidth();
        this.board = new int[this.height][this.width];
        for(int row = 0; row < this.height; row++) {
            for(int col = 0; col < this.width; col++) {
                this.board[row][col] = CFB.getBoard()[row][col];
            }
        }
        this.totalPieces = CFB.getTotalPieces();
    }

    /**
     * Returns the height of the board
     *
     * @return The height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the board
     *
     * @return The width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns a copy of the two-dimensional array representation of the board.
     *
     * @return A copy of the board
     */
    public int[][] getBoard() {
        int copy[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }
    
    /**
     * Returns the total number of pieces in the board.
     * 
     * @return The total number of pieces on the board
     */
    public int getTotalPieces() {
        return totalPieces;
    }
    
    /**
     * Checks if a given column is full
     * 
     * @param col The column to be checked
     * @return true if the colum is full, otherwise false
     */
    public boolean isColFull(int col) {
        boolean ret = true;
        for (int row = height - 1; row >= 0; row--) {
            if (board[row][col] == 0) {
                ret = false;
            }
        }
        return ret;
    }

    /**
     * Resets the board to an empty state
     */
    public void resetBoard() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }
        totalPieces = 0;
    }

    /**
     * Inserts a game piece into the board.
     *
     * @param pieceColor The color of the piece to be inserted
     * @param col The column to insert the piece into
     * @return True if the insertion was successful, otherwise false
     */
    public boolean insertPiece(int pieceColor, int col) {

        boolean result = false;
        if ((pieceColor == 1 || pieceColor == 2) && col >= 0 && col < width) {
            for (int row = height - 1; row >= 0; row--) {
                if (board[row][col] == 0) {
                    board[row][col] = pieceColor;
                    result = true;
                    totalPieces++;
                    break;
                }
            }
        }

        return result;
    }
    
    /**
     * Returns the board states possible moves.
     * 
     * @return An ArrayList containing the columns of possible moves
     */
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        for(int col = 0; col < width; col++) {
            for (int row = height - 1; row >= 0; row--) {
                if (board[row][col] == 0) {
                    moves.add(col);
                    break;
                }
            }
        }
        
        return moves;
    }

    /**
     * Checks the game board for a win.
     *
     * @return -1 if draw, 0 if no win, 1 if red win, 2 if black win
     */
    public int checkWin() {

        int result = 0;

        if(totalPieces == width * height)
            result = -1;
        else {
            
            // Check horizontals
            for (int row = 0; row < height && result == 0; row++) {
                for (int col = 3; col < width && result == 0; col++) {
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
            for (int row = 3; row < height && result == 0; row++) {
                for (int col = 0; col < width && result == 0; col++) {
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
            for (int row = 3; row < height && result == 0; row++) {
                for (int col = 3; col < width && result == 0; col++) {
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
            for (int row = 3; row < height && result == 0; row++) {
                for (int col = 3; col < width && result == 0; col++) {
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
        }

        return result;
    }

    /**
     * Draws a visual representation of the board to the given graphics object.
     * The board is drawn at a fixed size of 400 by 400 pixels.
     *
     * @param g The graphics object to draw with
     * @param x The x coordinate at which to draw the left edge of the board
     * @param y The y coordinate at which to draw the top edge of the board
     */
    public void drawBoard(Graphics g, int x, int y) {

        int bgHeight = 400;
        int bgWidth = 400;
        int borderSize = 10;
        int boardHeight = bgHeight - (2 * borderSize);
        int boardWidth = bgWidth - (2 * borderSize);
        int squareSize = boardWidth / ((width > height) ? width : height);
        int rowSep = squareSize;
        int colSep = squareSize;
        int pieceWidth = (rowSep * 3) / 4;
        int pieceHeight = (colSep * 3) / 4;
        int row = 0;
        int col = 0;

        /*
         System.out.println("bgHeight: " + bgHeight + "\n");
         System.out.println("bgWidth: " + bgWidth + "\n");
         System.out.println("borderSize: " + borderSize + "\n");
         System.out.println("boardHeight: " + boardHeight + "\n");
         System.out.println("boardWidth: " + boardWidth + "\n");
         System.out.println("rowSep: " + rowSep + "\n");
         System.out.println("colSep: " + colSep + "\n");
         */
        // Draw board background
        g.setColor(Color.GRAY);
        g.fillRect(x, y, bgWidth, bgHeight);

        // Draw background border
        g.setColor(Color.BLACK);
        g.drawRect(x, y, bgWidth, bgHeight);

        // Draw board
//        g.setColor(Color.YELLOW);
//        g.fillRect(x + borderSize,
//                   y + borderSize,
//                   boardWidth, 
//                   boardHeight);
        
        
        for(int i = y + borderSize; row < height; i += rowSep, row++)
        {
            col = 0;
            for(int j = x + borderSize; col < width; j += colSep, col++)
            {
                g.setColor(Color.YELLOW);
                g.fillRect(j, i, colSep, rowSep);

                if (board[row][col] == 0) {
                    g.setColor(Color.GRAY);
                } else if (board[row][col] == 1) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                int xOval = j + (colSep / 8);
                int yOval = i + (rowSep / 8);
                g.fillOval(xOval, yOval, pieceWidth, pieceHeight);
                
            }
        }
    }


    /**
     * Returns a string representation of the board.
     *
     * @return A string containing the board state
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result = result + board[i][j] + " ";
            }
            result = result + "\n";
        }
        return result;
    }
}

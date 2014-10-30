
package ConnectFour;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * A class representing the Connect Four game board
 * 
 */
public class ConnectFourBoard {
    private final int width;
    private final int height;
    private final int board[][]; 
    
    public ConnectFourBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        
        Random random = new Random();
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                board[i][j] = random.nextInt(3);
            }
        }
    }
    
    public void drawBoard(Graphics g, int x, int y) {
        
        int bgWidth = 200;
        int bgHeight = 200;
        int borderWidth = 10;
        int boardWidth = bgWidth - (2 * borderWidth);
        int boardHeight = bgHeight - (2 * borderWidth);
        
        
        System.out.println("bgWidth: " + bgWidth + "\n");
        System.out.println("bgHieght: " + bgHeight + "\n");
        System.out.println("borderWidth: " + borderWidth + "\n");
        System.out.println("boardWidth: " + boardWidth + "\n");
        System.out.println("boardHeight: " + boardHeight + "\n");
        
        g.setColor(Color.GRAY);
        g.fillRect(x, y, bgWidth, bgHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, bgWidth, bgHeight);
        g.setColor(Color.YELLOW);
        g.fillRect(x + borderWidth,
                   y + borderWidth,
                   boardWidth, 
                   boardHeight);
        
        int rowSep = (boardWidth - borderWidth) / width;
        int colSep = (boardWidth - borderWidth) / height;
        
        System.out.println("rowSep: " + rowSep + "\n");
        System.out.println("colSep: " + colSep + "\n");
        
        g.setColor(Color.GRAY);
        int row = 0;
        int col = 0;
        for(int i = y + 2 * borderWidth; row < height; i = i + rowSep, row++)
        {
            col = 0;
            for(int j = x + 2 * borderWidth; col < width; j = j + colSep, col++)
            {
                if(board[row][col] == 0)
                    g.setColor(Color.GRAY);
                else if(board[row][col] == 1)
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.BLACK);
                g.fillOval(j, i, rowSep - 10, colSep - 10);
            }
        }
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                result = result + board[j][i] + " ";
            }
            result = result + "\n";
        }
        return result;
    }
}

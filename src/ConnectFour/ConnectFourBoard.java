
package ConnectFour;

/**
 * A class representing the Connect Four game board
 * 
 */
public class ConnectFourBoard {
    private final int width;
    private final int height;
    
    public ConnectFourBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public String toString() {
        return "The board is " + this.width + " wide " +
                "by " + this.height + " high.\n";
    }
}

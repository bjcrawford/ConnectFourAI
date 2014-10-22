
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
    
    public void printDimensions() {
        System.out.println("The board is " + this.width + " wide ");
        System.out.println("by " + this.height + "high.\n");
    }
}

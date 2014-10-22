
package ConnectFour;

/**
 * This class is the main logic loop for the Connect Four game.
 * 
 */
public class ConnectFour {
    
    public static void main(String args[]) {
        
        System.out.println("Begin test\n");
        
        ConnectFourBoard board = new ConnectFourBoard(4,5);
        System.out.println(board);
        
        System.out.println("End test");
    }
    
}

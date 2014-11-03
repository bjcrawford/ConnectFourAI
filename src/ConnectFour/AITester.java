
package ConnectFour;

import com.gaurav.tree.NodeNotFoundException;
import java.util.Scanner;

/**
 *
 * @author bcrawford
 */
public class AITester {
    
    public static void main(String args[]) {
        int i = 1;
        Scanner input = new Scanner(System.in);
        ConnectFourBoard CFB = new ConnectFourBoard(8, 8);
        AIType1Player AT1 = new AIType1Player(2, CFB);
        int win = CFB.checkWin();
        
        while(win == 0)
        {
            System.out.println("\nGame Board - Turn " + i + "\n");
            System.out.println(CFB);
            int col = -1;
            if(i % 2 == 1)
            {
                System.out.println("Red Turn\n");
                while(col < 0 || col > 8)
                {
                    System.out.println("Please enter a col: ");
                    col = input.nextInt();
                }
                CFB.insertPiece(col);
            }
            else
            {
                System.out.println("Black Turn\n");
                try {
                    col = AT1.getNextMove();
                }
                catch(NodeNotFoundException e)
                {
                    System.out.println("Oops");
                }
                CFB.insertPiece(col);
                System.out.println("\nBlack inserts in col " + col);
            }
            i++;
            win = CFB.checkWin();
        }
        
        System.out.println("\nGame Board - Turn " + i);
        System.out.println(CFB);
        if(win == 1)
            System.out.println("Red Wins!");
        else
            System.out.println("Black Wins!");
        
    }
    
    public void test() {
        ConnectFourBoard CFB = new ConnectFourBoard(8, 8);
        AIType1Player AT1 = new AIType1Player(1, CFB);
        
        CFB.insertPiece(0);
        CFB.insertPiece(0);
        CFB.insertPiece(2);
        CFB.insertPiece(2);
        CFB.insertPiece(2);
        CFB.insertPiece(3);
        CFB.insertPiece(6);
        CFB.insertPiece(3);
        CFB.insertPiece(3);
        CFB.insertPiece(4);
        CFB.insertPiece(3);
        CFB.insertPiece(5);
        CFB.insertPiece(7);
        
        System.out.println("Intial Board State\n");
        System.out.println(CFB);
        
        try {
            AT1.getNextMove();
        }
        catch(NodeNotFoundException e) {
            System.out.println("Oops");
        }
    }

    
}
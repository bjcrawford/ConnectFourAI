
package ConnectFour;

import java.util.Scanner;

/**
 *
 * @author bcrawford
 */
public class AITester {
    
    public static void main(String args[]) {
        playGame();
    }
    
    public static void playGame() {
        int i = 1;
        Scanner input = new Scanner(System.in);
        ConnectFourBoard CFB = new ConnectFourBoard(8, 8);
        AIType1Player AT1 = new AIType1Player(2, 4, CFB);
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
                CFB.insertPiece(1, col);
            }
            else
            {
                System.out.println("Black Turn\n");
                
                col = AT1.getNextMove();
                
                CFB.insertPiece(2, col);
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
    
    public static void test() {
        ConnectFourBoard CFB = new ConnectFourBoard(8, 8);
        AIType1Player AT1 = new AIType1Player(1, 4, CFB);
        
        CFB.insertPiece(1, 0);
        CFB.insertPiece(2, 0);
        CFB.insertPiece(1, 2);
        CFB.insertPiece(2, 2);
        CFB.insertPiece(1, 2);
        CFB.insertPiece(2, 3);
        CFB.insertPiece(1, 6);
        CFB.insertPiece(2, 3);
        CFB.insertPiece(1, 3);
        CFB.insertPiece(2, 4);
        CFB.insertPiece(1, 3);
        CFB.insertPiece(2, 5);
        CFB.insertPiece(1, 7);
        
        System.out.println("Intial Board State\n");
        System.out.println(CFB);
        
        AT1.getNextMove();
    }

    
}

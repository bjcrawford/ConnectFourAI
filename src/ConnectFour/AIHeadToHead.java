package ConnectFour;


public class AIHeadToHead {
    
    public static void main(String args[]) {
        playGames(3000);
    }
    
    public static void playGames(int numOfGames) {
        int i;
        int games = 0;
        int redWins = 0;
        int blackWins = 0;
        int draws = 0;
        int win;
        
        ConnectFourBoard CFB = new ConnectFourBoard(8, 8);
        AbstractPlayer AI1;
        AbstractPlayer AI2;
        
        while(games < numOfGames)
        {
            i = 1;
            win = 0;
            CFB.resetBoard();
            AI1 = new AIMinimax1Player(1, 4, CFB);
            AI2 = new AIMinimax1Player(2, 4, CFB);

            while(win == 0)
            {
                //System.out.println("\nGame Board - Turn " + i + "\n");
                //System.out.println(CFB);
                int col;
                if(i % 2 == 1)
                {
                    //System.out.println("Red Turn\n");
                    //long start = System.currentTimeMillis();
                    col = AI1.getNextMove();
                    //long end = System.currentTimeMillis();
                    //System.out.println("Move took " + (end-start) + " ms to compute");
                    CFB.insertPiece(1, col);
                    //System.out.println("\nRed inserts in column " + (col+1));
                }
                else
                {
                    //System.out.println("Black Turn\n");
                    //long start = System.currentTimeMillis();
                    col = AI2.getNextMove();
                    //long end = System.currentTimeMillis();
                    //System.out.println("Move took " + (end-start) + " ms to compute");
                    CFB.insertPiece(2, col);
                    //System.out.println("\nBlack inserts in column " + (col+1));
                }
                i++;
                win = CFB.checkWin();
            }

            //System.out.println("\nGame Board - Turn " + i);
            //System.out.println(CFB);
            if(win == 1)
            {
                //System.out.println("Red Wins!");
                redWins++;
            }
            else if(win == 2)
            {
                //System.out.println("Black Wins!");
                blackWins++;
            }
            else
            {
                //System.out.println("Draw!");
                draws++;
            }
            games++;
            System.out.println("\n");
            System.out.println("Out of " + games + " games played: ");
            System.out.println("  Red wins: " + redWins);
            System.out.println("  Black wins: " + blackWins);
            System.out.println("  Draws: " + draws);
        }
    }
}

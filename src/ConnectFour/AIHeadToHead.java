package ConnectFour;


public class AIHeadToHead {
    
    public static void main(String args[]) {
        playGames(1000);
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
            AI2 = new AIHeuristicPlayer(2, 4, CFB);

            while(win == 0)
            {
                System.out.println("\nGame Board - Turn " + i + "\n");
                System.out.println(CFB);
                int col;
                if(i % 2 == 1)
                {
                    System.out.println("Red Turn\n");

                    col = AI1.getNextMove();

                    CFB.insertPiece(1, col);
                    System.out.println("\nRed inserts in col " + col);
                }
                else
                {
                    System.out.println("Black Turn\n");

                    col = AI2.getNextMove();

                    CFB.insertPiece(2, col);
                    System.out.println("\nBlack inserts in col " + col);
                }
                i++;
                win = CFB.checkWin();
            }

            System.out.println("\nGame Board - Turn " + i);
            System.out.println(CFB);
            if(win == 1)
            {
                System.out.println("Red Wins!");
                redWins++;
            }
            else if(win == 2)
            {
                System.out.println("Black Wins!");
                blackWins++;
            }
            else
            {
                System.out.println("Draw!");
                draws++;
            }
            games++;
        }
        System.out.println("Out of " + numOfGames + " games played: ");
        System.out.println("Red wins: " + redWins);
        System.out.println("Black wins: " + blackWins);
        System.out.println("Draws: " + draws);
    }
}

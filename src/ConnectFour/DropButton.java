package ConnectFour;

import com.gaurav.tree.NodeNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class DropButton extends JButton implements ActionListener {

    int col;
    ConnectFourBoard board;
    String name;

    public DropButton(ConnectFourBoard CFB, int col, String name) {
        this.col = col;
        this.name = name;
        this.board = CFB;
        this.setText(col + "");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        board.insertPiece(col - 1);
        
        if(board.checkWin() == 1){
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(board, ConnectFour.gp.bP, true);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            System.out.println("Red WINS");
        }
        else if(board.checkWin() == 2){
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(board, ConnectFour.gp.bP, false);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            System.out.println("Black WINS");
        }
        else{
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.gp = new GamePanel(board);
            ConnectFour.gui.add(ConnectFour.gp);
            ConnectFour.gui.pack();
            System.out.println("No winner yet");
        }
        System.out.println(board);
        ConnectFour.gui.setVisible(true);
        
        int aiMove = 0;
        try {
            aiMove = ConnectFour.ait1.getNextMove();
        }
        catch(NodeNotFoundException ex) {
            System.out.println("Crap, at1 failed");
            System.exit(-1);
        }
        board.insertPiece(aiMove);
        
        if(board.checkWin() == 1){
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(board, ConnectFour.gp.bP, true);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            System.out.println("Red WINS");
        }
        else if(board.checkWin() == 2){
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(board, ConnectFour.gp.bP, false);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            System.out.println("Black WINS");
        }
        else{
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.gp = new GamePanel(board);
            ConnectFour.gui.add(ConnectFour.gp);
            ConnectFour.gui.pack();
            System.out.println("No winner yet");
        }
        System.out.println(board);
        ConnectFour.gui.setVisible(true);
    }
}

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
        
        // We should create a thread here to make a call to 
        // the connect four class where all of this code can
        // be handled. 

        board.insertPiece(1, col - 1);
        setEnabled(!board.isColFull(col - 1));

        if (board.checkWin() == 1) {
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(board, ConnectFour.gp.bP, true);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            System.out.println("Red WINS");
        } else if (board.checkWin() == 2) {
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(board, ConnectFour.gp.bP, false);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            System.out.println("Black WINS");
        } else {
            ConnectFour.gp.repaint();
            System.out.println("No winner yet");
        }
        System.out.println(board);
        ConnectFour.gui.setVisible(true);

        int aiMove = 0;
        try {
            aiMove = ConnectFour.ait1.getNextMove();
        } catch (NodeNotFoundException ex) {
            System.out.println("Crap, at1 failed");
            System.exit(-1);
        }
        board.insertPiece(2, aiMove);
        setEnabled(!board.isColFull(aiMove));

        if (board.checkWin() == 1) {
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(board, ConnectFour.gp.bP, true);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            System.out.println("Red WINS");
        } else if (board.checkWin() == 2) {
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(board, ConnectFour.gp.bP, false);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            System.out.println("Black WINS");
        } else {
            ConnectFour.gp.repaint();
            System.out.println("No winner yet");
        }
        System.out.println(board);
        ConnectFour.gui.setVisible(true);

    }
}

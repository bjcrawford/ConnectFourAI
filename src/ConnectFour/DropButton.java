package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class DropButton extends JButton implements ActionListener {

    int col;
    ConnectFourBoard CFB;
    String name;

    public DropButton(ConnectFourBoard CFB, int col, String name) {
        this.col = col;
        this.name = name;
        this.CFB = CFB;
        this.setText(col + "");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CFB.insertPiece(col - 1);
        ConnectFour.gui.remove(ConnectFour.gp);
        ConnectFour.gp = new GamePanel(CFB);
        ConnectFour.gui.add(ConnectFour.gp);
        ConnectFour.gui.pack();
        if(CFB.checkWin() == 1){
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(CFB, ConnectFour.gp.bP, true);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            ConnectFour.gui.setVisible(true);
            System.out.println("Red WINS");
        }
        else if(CFB.checkWin() == 2){
            ConnectFour.gui.remove(ConnectFour.gp);
            ConnectFour.ep = new EndPanel(CFB, ConnectFour.gp.bP, false);
            ConnectFour.gui.add(ConnectFour.ep);
            ConnectFour.gui.pack();
            ConnectFour.gui.setVisible(true);
            System.out.println("Black WINS");
        }
        else{
            System.out.println("No winner yet");
        }
        System.out.println(CFB);
        ConnectFour.gui.setVisible(true);
    }
}

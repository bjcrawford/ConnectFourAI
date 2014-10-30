package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class dropButton extends JButton implements ActionListener {

    int col;
    ConnectFourBoard CFB;
    String name;

    public dropButton(ConnectFourBoard CFB, int col, String name) {
        this.col = col;
        this.name = name;
        this.CFB = CFB;
        this.setText(col + "");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CFB.insertPiece(col);
        ConnectFour.gui.remove(ConnectFour.GPI);
        ConnectFour.GPI = new GamePlayUI(CFB);
        ConnectFour.gui.add(ConnectFour.GPI);
        ConnectFour.gui.pack();
        ConnectFour.gui.setVisible(true);
    }
}

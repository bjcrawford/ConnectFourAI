package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class StartButton extends JButton implements ActionListener {

    private boolean start = false;

    public StartButton() {
        this.addActionListener(this);
        this.setText("Start!");
    }
    
    public StartButton(String title) {
        this.addActionListener(this);
        this.setText(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ConnectFour.restart == 0) {
            ConnectFour.gui.remove(ConnectFour.wp);
            ConnectFour.gui.add(ConnectFour.gp);
            ConnectFour.gp.repaint();
            ConnectFour.gui.pack();
            ConnectFour.gui.setVisible(true);
            ConnectFour.restart++;
        } else {
            ConnectFour.board.resetBoard();
            ConnectFour.gui.remove(ConnectFour.ep);
            ConnectFour.wp = new WelcomePanel(ConnectFour.board);
            ConnectFour.gui.add(ConnectFour.wp);
            ConnectFour.gui.pack();
            ConnectFour.gui.setVisible(true);
            ConnectFour.restart--;
        }
    }

}

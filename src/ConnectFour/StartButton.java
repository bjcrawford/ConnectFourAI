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

        @Override
        public void actionPerformed(ActionEvent e) {
            ConnectFour.gui.remove(ConnectFour.wp);
            ConnectFour.gui.add(ConnectFour.gp);
            ConnectFour.gui.pack();
            ConnectFour.gui.setVisible(true);
        }

    }


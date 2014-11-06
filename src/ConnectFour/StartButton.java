package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class StartButton extends JButton implements ActionListener {

    public StartButton() {
        
        this.addActionListener(this);
        this.setText("Start");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Use synchronization to release blocked main thread
        ConnectFour.userOptionsInputSem.release();
    }

}

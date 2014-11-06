
package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author bcrawford
 */
public class RestartButton extends JButton implements ActionListener {

    public RestartButton() {
        
        this.addActionListener(this);
        this.setText("Restart");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Use synchronization to release blocked main thread
        ConnectFour.restart = true;
        ConnectFour.userRestartInputSem.release();
    }

}

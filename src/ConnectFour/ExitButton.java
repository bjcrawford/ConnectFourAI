
package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author bcrawford
 */
public class ExitButton extends JButton implements ActionListener {
    
    public ExitButton() {
        
        this.addActionListener(this);
        this.setText("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Use synchronization to release blocked main thread
        ConnectFour.restart = false;
        ConnectFour.userRestartInputSem.release();
    }
    
}

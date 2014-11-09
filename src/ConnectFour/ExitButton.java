
package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author bcrawford
 */
public class ExitButton extends JButton implements ActionListener {
    
    int id; // Use 1 for welcome screen button, 2 for end screen button
    
    public ExitButton(int id) {
        
        this.addActionListener(this);
        this.id = id;
        this.setText("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(id == 1) {
            // Use synchronization to release blocked main thread
            ConnectFour.exit = true;
            ConnectFour.userOptionsInputSem.release();
        }
        else {
            // Use synchronization to release blocked main thread
            ConnectFour.restart = false;
            ConnectFour.userRestartInputSem.release();
        }
    }
    
}

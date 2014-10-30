
package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class DropButton extends JButton implements ActionListener{
    int col;
    ConnectFourBoard CFB;
    
    public DropButton(ConnectFourBoard CFB, int col){
        this.col = col;
        this.CFB = CFB;
        this.setText(col+"");
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
}

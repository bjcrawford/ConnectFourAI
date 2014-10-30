package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class startButton extends JButton implements ActionListener{
            
    public startButton(){
        this.addActionListener(this);
        this.setText("Start!");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        ConnectFour.gui.remove(ConnectFour.WP);
        ConnectFour.gui.add(ConnectFour.GPI);
        ConnectFour.gui.pack();
        ConnectFour.gui.setVisible(true);
    }
    
}
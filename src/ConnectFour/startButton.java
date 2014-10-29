package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Beaker
 */
public class startButton extends JButton implements ActionListener{
    private boolean start = false;
            
    public startButton(){
        this.addActionListener(this);
        this.setText("Start!");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        this.start = true;
    }
    
    public boolean ready(){
        return this.start;
    }
}
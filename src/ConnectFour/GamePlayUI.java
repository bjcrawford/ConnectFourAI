
package ConnectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Beaker
 */
public class GamePlayUI extends JPanel{
    
    public GamePlayUI(){
        setLayout(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(1, 7));
        add(buttons);
    }
    
}

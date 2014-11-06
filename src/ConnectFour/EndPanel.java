
package ConnectFour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Beaker
 */
public class EndPanel extends JPanel {
    
    public EndPanel(ConnectFourBoard board, BoardPanel bP, int winner){
        
        setLayout(new BorderLayout());
        add(bP, BorderLayout.CENTER);
        
        if(winner == 1)
            add(new JLabel(new ImageIcon("Red.png")), BorderLayout.NORTH);
        else
            add(new JLabel(new ImageIcon("Black.png")), BorderLayout.NORTH);
        
        JPanel buttons = new JPanel(new GridLayout(1, 2));
        buttons.add(new ExitButton());
        buttons.add(new RestartButton());
        add(buttons, BorderLayout.SOUTH);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}

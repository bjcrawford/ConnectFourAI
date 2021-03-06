
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
    
    ConnectFourBoard board;
    JPanel buttonPanel;
    DropButton buttons[]; 
    JPanel topPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    
    public EndPanel(ConnectFourBoard board, BoardPanel boardPanel, int winner){
        
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        
        switch(winner) {
            case -1:
                topPanel.add(new JLabel(new ImageIcon("res/draw.png")));
                break;
            case 1:
                topPanel.add(new JLabel(new ImageIcon("res/redwin.png")));
                break;
            case 2:
                topPanel.add(new JLabel(new ImageIcon("res/blackwin.png")));
                break;
            default:
                System.exit(1);
        }
        
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 400));
        leftPanel.setMaximumSize(new Dimension(200, 400));
        
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, 400));
        rightPanel.setMaximumSize(new Dimension(200, 400));
        
        buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.setPreferredSize(new Dimension(800, 30));
        buttonPanel.setMaximumSize(new Dimension(800, 30));
        buttonPanel.add(new ExitButton(2));
        buttonPanel.add(new RestartButton());
        
        
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author bcrawford
 */
public class InfoButton extends JButton implements ActionListener {

    public InfoButton() {
        this.addActionListener(this);
        this.setText("More Info");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JFrame info = new JFrame("More Info");
        info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JOptionPane.showMessageDialog(info, "Here is a little more info");
        
        info.pack();
        info.setVisible(true);
    }
    
}

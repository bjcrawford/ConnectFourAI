/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author bcrawford
 */
public class OptionsPanel extends JPanel  implements ActionListener {

    JPanel playerOnePanel;
    JTextArea playerOneTextArea;
    JComboBox playerOneComboBox;
    String[] playerOneOptions = { "Human", "AI Type 1", "AI Type 2" };
    
    JPanel playerTwoPanel;
    JTextArea playerTwoTextArea;
    JComboBox playerTwoComboBox;
    String[] playerTwoOptions = { "AI Type 1", "AI Type 2" };
    
    public OptionsPanel() {
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        playerOnePanel = new JPanel();
        playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
        playerOnePanel.setPreferredSize(new Dimension(360, 140));
        playerOnePanel.setMaximumSize(new Dimension(360, 140));
        
        playerOneTextArea = new JTextArea();
        playerOneTextArea.setEnabled(false);
        playerOneTextArea.setBackground(new Color(0, 0, 0, 0));
        playerOneTextArea.setText("Player One");
        playerOneTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        playerOneTextArea.setPreferredSize(new Dimension(360, 50));
        playerOneTextArea.setMaximumSize(new Dimension(360, 50));
        playerOneTextArea.setMargin(new Insets(10, 30, 10, 30));
        
        playerOneComboBox = new JComboBox(playerOneOptions);
        playerOneComboBox.addActionListener(this);
        playerOneComboBox.setPreferredSize(new Dimension(360, 50));
        playerOneComboBox.setMaximumSize(new Dimension(360, 50));
        playerOneComboBox.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        
        playerOnePanel.add(playerOneTextArea);
        playerOnePanel.add(playerOneComboBox);
        
        playerTwoPanel = new JPanel();
        playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
        playerTwoPanel.setPreferredSize(new Dimension(360, 140));
        playerTwoPanel.setMaximumSize(new Dimension(360, 140));
        
        playerTwoTextArea = new JTextArea();
        playerTwoTextArea.setEnabled(false);
        playerTwoTextArea.setBackground(new Color(0, 0, 0, 0));
        playerTwoTextArea.setText("Player Two");
        playerTwoTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        playerTwoTextArea.setPreferredSize(new Dimension(360, 50));
        playerTwoTextArea.setMaximumSize(new Dimension(360, 50));
        playerTwoTextArea.setMargin(new Insets(10, 30, 10, 30));
        
        playerTwoComboBox = new JComboBox(playerTwoOptions);
        playerTwoComboBox.addActionListener(this);
        playerTwoComboBox.setPreferredSize(new Dimension(360, 50));
        playerTwoComboBox.setMaximumSize(new Dimension(360, 50));
        playerTwoComboBox.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        
        playerTwoPanel.add(playerTwoTextArea);
        playerTwoPanel.add(playerTwoComboBox);
        
        add(playerOnePanel);
        add(playerTwoPanel);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 140);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JComboBox cb = (JComboBox) e.getSource();
        int selection = cb.getSelectedIndex();
        
        if(cb == playerOneComboBox) 
            ConnectFour.playerOneSelection = selection;
        
        else 
            ConnectFour.playerTwoSelection = selection;
    }
    
}

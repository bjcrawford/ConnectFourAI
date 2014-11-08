/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    String[] playerOneOptions = { "Human", "AI Type 1" };
    
    JPanel playerTwoPanel;
    JTextArea playerTwoTextArea;
    JComboBox playerTwoComboBox;
    String[] playerTwoOptions = { "AI Type 1" };
    
    public OptionsPanel() {
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        playerOnePanel = new JPanel();
        playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
        playerOnePanel.setPreferredSize(new Dimension(360, 140));
        playerOnePanel.setMaximumSize(new Dimension(360, 140));
        
        playerOneTextArea = new JTextArea();
        playerOneTextArea.setEditable(false);
        playerOneTextArea.setEnabled(false);
        playerOneTextArea.setBackground(new Color(0, 0, 0, 0));
        playerOneTextArea.setText("Player One");
        playerOneTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        playerOneTextArea.setPreferredSize(new Dimension(360, 80));
        playerOneTextArea.setMaximumSize(new Dimension(360, 80));
        playerOneTextArea.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        playerOneComboBox = new JComboBox(playerOneOptions);
        playerOneComboBox.addActionListener(this);
        playerOneComboBox.setPreferredSize(new Dimension(360, 60));
        playerOneComboBox.setMaximumSize(new Dimension(360, 60));
        playerOneComboBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        playerOnePanel.add(playerOneTextArea);
        playerOnePanel.add(playerOneComboBox);
        
        playerTwoPanel = new JPanel();
        playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
        playerTwoPanel.setPreferredSize(new Dimension(360, 140));
        playerTwoPanel.setMaximumSize(new Dimension(360, 140));
        
        playerTwoTextArea = new JTextArea();
        playerTwoTextArea.setEditable(false);
        playerTwoTextArea.setEnabled(false);
        playerTwoTextArea.setBackground(new Color(0, 0, 0, 0));
        playerTwoTextArea.setText("Player Two");
        playerTwoTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        playerTwoTextArea.setPreferredSize(new Dimension(360, 80));
        playerTwoTextArea.setMaximumSize(new Dimension(360, 80));
        playerTwoTextArea.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        playerTwoComboBox = new JComboBox(playerTwoOptions);
        playerTwoComboBox.addActionListener(this);
        playerTwoComboBox.setPreferredSize(new Dimension(360, 60));
        playerTwoComboBox.setMaximumSize(new Dimension(360, 60));
        playerTwoComboBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
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
        
        if(cb == playerOneComboBox) {
            System.out.println("Player One Combo Box");
            System.out.println("Selected: " + selection);
            ConnectFour.playerOneSelection = selection;
        }
        else {
            System.out.println("Player Two Combo Box");
            System.out.println("Selected: " + selection);
            ConnectFour.playerTwoSelection = selection;
        }
    }
    
}

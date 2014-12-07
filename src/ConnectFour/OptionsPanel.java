/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author bcrawford
 */
public class OptionsPanel extends JPanel  implements ActionListener {

    JPanel playerOnePanel;
    JComboBox playerOneComboBox;
    String[] playerOneOptions = { "Human", 
                                  "AI Random Player",
                                  "AI Blocker Player",
                                  "AI Minimax 1 Player", 
                                  "AI Minimax 2 Player", 
                                  "AI AlphaBeta Player", 
                                  "AI Heuristic Player", 
                                  "AI MonteCarlo 1 Player",
                                  "AI MonteCarlo 2 Player" };
    
    JPanel boardSizePanel;
    JComboBox boardSizeComboBox;
    String[] boardSizeOptions = { "6x7",
                                  "7x7",
                                  "7x8",
                                  "8x8",
                                  "8x9",
                                  "9x9",
                                  "9x10",
                                  "10x10" };
    
    JPanel playerTwoPanel;
    JComboBox playerTwoComboBox;
    String[] playerTwoOptions = { "AI Random",
                                  "AI Blocker Player",
                                  "AI Minimax 1 Player", 
                                  "AI Minimax 2 Player", 
                                  "AI AlphaBeta Player", 
                                  "AI Heuristic Player",
                                  "AI MonteCarlo 1 Player",
                                  "AI MonteCarlo 2 Player" };
    
    public OptionsPanel() {
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        playerOnePanel = new JPanel();
        playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
        playerOnePanel.setPreferredSize(new Dimension(260, 50));
        playerOnePanel.setMaximumSize(new Dimension(260, 50));
        
        playerOneComboBox = new JComboBox(playerOneOptions);
        playerOneComboBox.addActionListener(this);
        playerOneComboBox.setPreferredSize(new Dimension(260, 50));
        playerOneComboBox.setMaximumSize(new Dimension(260, 50));
        playerOneComboBox.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        playerOnePanel.add(playerOneComboBox);
        
        boardSizePanel = new JPanel();
        boardSizePanel.setLayout(new BoxLayout(boardSizePanel, BoxLayout.Y_AXIS));
        boardSizePanel.setPreferredSize(new Dimension(200, 50));
        boardSizePanel.setMaximumSize(new Dimension(200, 50));
        
        boardSizeComboBox = new JComboBox(boardSizeOptions);
        boardSizeComboBox.addActionListener(this);
        boardSizeComboBox.setPreferredSize(new Dimension(200, 50));
        boardSizeComboBox.setMaximumSize(new Dimension(200, 50));
        boardSizeComboBox.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        boardSizePanel.add(boardSizeComboBox);
        
        playerTwoPanel = new JPanel();
        playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
        playerTwoPanel.setPreferredSize(new Dimension(260, 50));
        playerTwoPanel.setMaximumSize(new Dimension(260, 50));
        
        playerTwoComboBox = new JComboBox(playerTwoOptions);
        playerTwoComboBox.addActionListener(this);
        playerTwoComboBox.setPreferredSize(new Dimension(260, 50));
        playerTwoComboBox.setMaximumSize(new Dimension(260, 50));
        playerTwoComboBox.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        playerTwoPanel.add(playerTwoComboBox);
        
        add(playerOnePanel);
        add(boardSizePanel);
        add(playerTwoPanel);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 50);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JComboBox cb = (JComboBox) e.getSource();
        int selection = cb.getSelectedIndex();
        
        if(cb == playerOneComboBox) 
            ConnectFour.playerOneSelection = selection;
        else if(cb == boardSizeComboBox)
            ConnectFour.boardSizeSelection = selection;
        else 
            ConnectFour.playerTwoSelection = selection;
    }
    
}

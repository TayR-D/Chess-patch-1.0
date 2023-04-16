package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener{		
	private JPanel board = new Board();         // implicit casting
	private SidePanel Side_panel = new SidePanel();
	
    private ImageIcon FrameIcon = new ImageIcon("New Chess Game/res/GameIcon/BlackHorse_GameIcon.png");
    
    public GameFrame(){
        // CreateFrame
    	super("Real Chess Board");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLayout(new BorderLayout());
        
        // Add contents of the program
        this.add(board, BorderLayout.CENTER);
        this.add(Side_panel, BorderLayout.EAST);
                
        
        // Register listener
        Side_panel.Reset_btn.addActionListener(this);

        this.setIconImage(FrameIcon.getImage());
        this.pack(); // Make the frame itself adjust the size to fit all components
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Reset Board") {
			this.remove(board);
			board = new Board();
			this.add(board, BorderLayout.CENTER);
			
			SwingUtilities.updateComponentTreeUI(this);
		}		
	}
}
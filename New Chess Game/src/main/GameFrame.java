package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener{		
	JPanel board = new Board();
	SidePanel Side_panel = new SidePanel();
	
    ImageIcon FrameIcon = new ImageIcon("New Chess Game/res/GameIcon/BlackHorse_GameIcon.png");
    
    public GameFrame(){
        // CreateFrame
    	super("Real Chess Board");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLayout(new BorderLayout());
        
        // Add contents of the program
        this.add(board, BorderLayout.CENTER);
        this.add(Side_panel, BorderLayout.EAST);
                
        
        // Register listener
        Side_panel.Start_btn.addActionListener(this);

        this.setIconImage(FrameIcon.getImage());
        this.pack(); // Make the frame itself adjust the size to fit all components
        this.setVisible(true);
        this.setResizable(true);
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

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import util.AddPiecePanel;
import util.ChessTimer;

public class SidePanel extends JPanel implements ActionListener{
	private final int WIDTH = 2 * (Board.BOARD_SIZE / Board.DIMENSION);
	private final int HEIGHT = Board.BOARD_SIZE;

	private JButton StartBlackTimer_btn = new JButton("Pass Turn");
	private JButton StartWhiteTimer_btn = new JButton("Pass Turn");
	JButton Start_btn = new JButton("Reset Board");
	JButton addPiece_Btb = new JButton("Add Piece");

	private ChessTimer White_timer = new ChessTimer();
	private ChessTimer Black_timer = new ChessTimer();

	JComboBox<String> Game_duration_rd = new JComboBox<>();

	AddPiecePanel AddPiece_pn = new AddPiecePanel();

	// 
	
	SidePanel(){		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// Start Game button
		this.add(Start_btn);
		Dimension Start_btn_size = Start_btn.getPreferredSize();
		Start_btn.setBounds((WIDTH - (Start_btn_size.width)) / 2, (HEIGHT - Start_btn_size.height) /2, Start_btn_size.width, Start_btn_size.height);
		

		// set game duration
		Game_duration_rd.addItem("Choose Game Length");
		Game_duration_rd.addItem("1 Min");
		Game_duration_rd.addItem("3 Min");
		Game_duration_rd.addItem("5 Min");
		Game_duration_rd.addItem("10 Min");
		this.add(Game_duration_rd);
		Game_duration_rd.setBounds((WIDTH - Game_duration_rd.getPreferredSize().width)/ 2, 4*(WIDTH/2) + 18, Game_duration_rd.getPreferredSize().width, Game_duration_rd.getPreferredSize().height);
		

		// White time clock display
		this.add(White_timer);
		White_timer.setLocation((WIDTH - White_timer.getPreferredSize().width) / 2 , 7*(WIDTH/2) - (White_timer.getPreferredSize().height / 2));
		// White's pass turn button
		this.add(StartBlackTimer_btn);
		Dimension StartBlackTimer_btn_size = StartBlackTimer_btn.getPreferredSize();
		StartBlackTimer_btn.setBounds((WIDTH - (StartBlackTimer_btn_size.width)) / 2, (7*WIDTH - StartBlackTimer_btn_size.height) / 2 - 30, StartBlackTimer_btn_size.width, StartBlackTimer_btn_size.height);	
		
		
		// Black time clock display
		Black_timer.setAlignmentX(SwingConstants.CENTER);
		this.add(Black_timer);
		Black_timer.setLocation((WIDTH - Black_timer.getPreferredSize().width) /2, 1*(WIDTH/2) - (Black_timer.getPreferredSize().height / 2));
		// Black's pass turn button
		this.add(StartWhiteTimer_btn);
		Dimension StartWhiteTimer_btn_size = StartWhiteTimer_btn.getPreferredSize();
		StartWhiteTimer_btn.setBounds((WIDTH - (StartWhiteTimer_btn_size.width)) / 2, 1*(WIDTH/2) - (StartWhiteTimer_btn_size.height / 2) + 30, StartWhiteTimer_btn_size.width, StartWhiteTimer_btn_size.height);
	
		
		// Add-piece button
		this.add(AddPiece_pn);
		AddPiece_pn.setLocation((WIDTH - AddPiece_pn.getSize().width) /2, 3*(WIDTH/2) - (AddPiece_pn.getSize().height / 2) - 35);


		// Register Listener
		Game_duration_rd.addActionListener(this);
		StartBlackTimer_btn.addActionListener(this);
		StartWhiteTimer_btn.addActionListener(this);
	
	}
	
	void startClock() {
		White_timer.startTimer();
		Black_timer.startTimer();
	}
	
	long getDuration(String d) {
		long l = 0; // Default value of l, no more need for default switch case
		switch(d) {
			case "1 Min": l = 60000; 
				break;
			case "3 Min": l = 3 * 60000;
				break;
			case "5 Min": l = 5 * 60000;
				break;
			case "10 Min": l = 10 * 60000;
				break;
		}
		
		return l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Game_duration_rd) {
			System.out.println(Game_duration_rd.getSelectedItem().toString());
			long duration = getDuration(Game_duration_rd.getSelectedItem().toString());
			White_timer.setDuration(duration);
			Black_timer.setDuration(duration);

			White_timer.stopTimer();
			Black_timer.stopTimer();
		}
		
		if (e.getSource() == StartBlackTimer_btn) {
			White_timer.stopTimer();			
			Black_timer.startTimer();
		}
		
		if (e.getSource() == StartWhiteTimer_btn) {
			Black_timer.stopTimer();			
			White_timer.startTimer();
		}
	}
}
package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import util.AddPiecePanel;
import util.ChessTimer;

public class SidePanel extends JPanel implements ActionListener{
	private final int WIDTH = 200;
	private final int HEIGHT = Board.BOARD_SIZE;

	JButton Reset_btn = new JButton("Reset Board");
	JButton addPiece_Btn = new JButton("Add Piece");
	private JButton StartBlackTimer_btn = new JButton("Pass Turn");
	private JButton StartWhiteTimer_btn = new JButton("Pass Turn");

	private ChessTimer White_timer = new ChessTimer();
	private ChessTimer Black_timer = new ChessTimer();

	private JComboBox<String> Game_duration_cb = new JComboBox<>();
	private long duration = 0;				// implicit casting (int -> long)
	private int increment;

	private AddPiecePanel AddPiece_pn = new AddPiecePanel();

	private int turnCircle_pos;

	private int move_count = 0;
	private JLabel moveCount_jlb;
	
	SidePanel(){		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// Start Game button
		this.add(Reset_btn);
		Dimension Reset_btn_size = Reset_btn.getPreferredSize();
		Reset_btn.setBounds((WIDTH - (Reset_btn_size.width)) / 2, (HEIGHT - Reset_btn_size.height) /2, Reset_btn_size.width, Reset_btn_size.height);
		

		// set game duration
		Game_duration_cb.addItem("Choose Game Length");
		Game_duration_cb.addItem("1 Min | +1s");
		Game_duration_cb.addItem("3 Min | +0s");
		Game_duration_cb.addItem("3 Min | +2s");
		Game_duration_cb.addItem("5 Min | +5s");
		Game_duration_cb.addItem("10 Min | +0s");
		Game_duration_cb.addItem("15 Min | +10s");
		Game_duration_cb.addItem("30 Min | +0s");
		this.add(Game_duration_cb);
		Game_duration_cb.setBounds((WIDTH - Game_duration_cb.getPreferredSize().width)/ 2, 4*(WIDTH/2) + 18, Game_duration_cb.getPreferredSize().width, Game_duration_cb.getPreferredSize().height);
		

		// Black time clock display
		Black_timer.setAlignmentX(SwingConstants.CENTER);
		this.add(Black_timer);
		Black_timer.setLocation((WIDTH - Black_timer.getPreferredSize().width) /2, 1*(WIDTH/2) - (Black_timer.getPreferredSize().height / 2));
		// Black's pass turn button
		this.add(StartWhiteTimer_btn);
		Dimension StartWhiteTimer_btn_size = StartWhiteTimer_btn.getPreferredSize();
		StartWhiteTimer_btn.setBounds((WIDTH - (StartWhiteTimer_btn_size.width)) / 2, 1*(WIDTH/2) - (StartWhiteTimer_btn_size.height / 2) + 30, StartWhiteTimer_btn_size.width, StartWhiteTimer_btn_size.height);


		// White time clock display
		this.add(White_timer);
		White_timer.setLocation((WIDTH - White_timer.getPreferredSize().width) / 2 , 7*(WIDTH/2) - (White_timer.getPreferredSize().height / 2));
		// White's pass turn button
		Dimension StartBlackTimer_btn_size = StartBlackTimer_btn.getPreferredSize();
		StartBlackTimer_btn.setBounds((WIDTH - (StartBlackTimer_btn_size.width)) / 2, (7*WIDTH - StartBlackTimer_btn_size.height) / 2 - 30, StartBlackTimer_btn_size.width, StartBlackTimer_btn_size.height);	
			
		
		// Add-piece button
		this.add(AddPiece_pn);
		AddPiece_pn.setLocation((WIDTH - AddPiece_pn.getSize().width) /2, 3*(WIDTH/2) - (AddPiece_pn.getSize().height / 2) - 35);

		// Move counter
		moveCount_jlb = new JLabel("Move: " + move_count);
		moveCount_jlb.setBounds(150, 0, 50, 20);
		this.add(moveCount_jlb);

		// Register Listener
		Reset_btn.addActionListener(this);
		Game_duration_cb.addActionListener(this);
		StartBlackTimer_btn.addActionListener(this);
		StartWhiteTimer_btn.addActionListener(this);
	
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (turnCircle_pos != 0 && duration != 0){
			Graphics2D g2d = (Graphics2D) g; 		// Explicit casting (Graphics -> Graphics2D)
			g2d.setStroke(new BasicStroke(3));
			g2d.setColor(Color.GREEN);
			g2d.drawOval((WIDTH - 30) / 2, turnCircle_pos, 30, 30);
			g2d.setStroke(new BasicStroke()); // set stroke back to defualt (only applying stroke size to the oval and not all other components)
		}
	}

	private void innitializeTurnBtn(){
		this.remove(StartBlackTimer_btn);
		this.add(StartWhiteTimer_btn);
	}

	private void innitializeMoveCounter(){
		move_count = 0;
		moveCount_jlb.setText("Move : 0");
	}
	
	public void setClock(String d) {
		switch(d) {
			case "1 Min | +1s": 
				duration = 60000; 
				increment = 1000;
				break;
			case "3 Min | +0s": 
				duration = 3 * 60000;
				increment = 0;
				break;
			case "3 Min | +2s": 
				duration = 3 * 60000;
				increment = 2000;
				break;
			case "5 Min | +5s": 
				duration = 5 * 60000;
				increment = 5000;
				break;
			case "10 Min | +0s": 
				duration = 10 * 60000;
				increment = 0;
				break;
			case "15 Min | +10s": 
				duration = 15 *60000;
				increment = 10000;
				break;
			case "30 Min | +0s": 
				duration = 30 * 60000;
				increment = 0;
				break;
			default:
				break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Reset_btn){
			turnCircle_pos = 0;
			duration = 0;
			White_timer.setDuration(duration);
			Black_timer.setDuration(duration);
			Game_duration_cb.setSelectedIndex(0);
			
			innitializeMoveCounter();
			innitializeTurnBtn();
			this.repaint();
		}

		if (e.getSource() == Game_duration_cb) {
			turnCircle_pos = 0;
			setClock(Game_duration_cb.getSelectedItem().toString());
			White_timer.setDuration(duration);
			White_timer.setIncrement(increment);
			Black_timer.setDuration(duration);
			Black_timer.setIncrement(increment);

			White_timer.stopTimer();
			Black_timer.stopTimer();
			innitializeTurnBtn();
		}
		
		if (e.getSource() == StartBlackTimer_btn) {
			if (duration != 0){
				this.remove(StartBlackTimer_btn);
				this.add(StartWhiteTimer_btn);
			}
			White_timer.stopTimer();
			White_timer.incTime();			
			Black_timer.startTimer();
			// turn start
			move_count += 1;
			moveCount_jlb.setText("Move: " + move_count);
			turnCircle_pos = 1*(WIDTH - 30) / 2 - 40;
			repaint();
		}
		
		if (e.getSource() == StartWhiteTimer_btn) {
			if (duration != 0){
				this.remove(StartWhiteTimer_btn);
				this.add(StartBlackTimer_btn);
			}
			Black_timer.stopTimer();
			if (move_count >  0)
				Black_timer.incTime();	
			White_timer.startTimer();
			
			turnCircle_pos = 8*(WIDTH - 30) / 2 +40;
			repaint();
		}
	}
}
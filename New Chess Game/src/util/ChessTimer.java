package util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

import javax.swing.*;

public class ChessTimer extends JLabel implements ActionListener{
    private long duration;
	private int increment;
	
	public Timer timer = new Timer(1, this);
	private SimpleDateFormat df= new SimpleDateFormat("mm:ss");
	
	public ChessTimer(){
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
		this.setText("  " + df.format(duration) + "  ");
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setSize(this.getPreferredSize());
	}
	
	public ChessTimer(long duration, int inc){
		this.duration = duration;
		this.increment = inc;
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
		this.setText(" " + df.format(duration) + " ");
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setSize(this.getPreferredSize());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if (duration > 0) {
        	duration -= 15; // duration set to -15(ms) although timer tick every 1ms, suspected that my laptop or java swing itself only update at ~10ms
        }else {timer.stop();}
		
		this.setText(df.format(duration));
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
		
	}

	public void incTime() {
		duration += increment;
		this.setText(df.format(duration));
	}
	
	public void setDuration(long dur) {
		this.duration = dur;
		this.setText(df.format(dur));
	}

	public void setIncrement(int inc) {
		this.increment = inc;
	}
}

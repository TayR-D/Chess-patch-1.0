package util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

import javax.swing.*;

public class ChessTimer extends JLabel implements ActionListener{
    private long duration = 0;
	
	public Timer timer = new Timer(1, this);
	SimpleDateFormat df = new SimpleDateFormat("mm:ss");
	
	public ChessTimer(){
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
		this.setText("  " + df.format(duration) + "  ");
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setSize(this.getPreferredSize());
	}
	
	public ChessTimer(long duration){
		this.duration = duration;
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
        	duration -= 15; // duration set to -15 although timer tick ever 1ms, suspected that my laptop or java swing itself only update at 10ms
        }else {timer.stop();}
        
        this.setText(df.format(duration));
//        System.out.println(duration);
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
		
	}
	
	public void setDuration(long l) {
		this.duration = l;
		this.setText(df.format(l));
	}
}

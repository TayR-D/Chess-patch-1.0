package main;

import java.awt.*;
import javax.swing.*;


public class Square extends JPanel{
    private int row;
    private int col;
    
    private Pieces innerPiece;
	
	public Square(int row, int col){
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.row = row;
		this.col = col;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, Board.BOARD_SIZE/Board.DIMENSION, Board.BOARD_SIZE/Board.DIMENSION);
		if (innerPiece != null) {
			innerPiece.repaint();
		}
	}
	
	public Pieces getInnerPiece() {
		return innerPiece;
	}
	
	public void Set_Piece(Pieces p){
		this.add(p);
		innerPiece = p;

		p.repaint();
		this.repaint();
	    }
	
	public void Remove_Piece(Pieces p) {
		this.remove(p);
		innerPiece = null;
		
		p.repaint();
		this.repaint();
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}

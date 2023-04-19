package main;

import java.awt.*;
import javax.swing.*;


public class Square extends JPanel{
    private int row;
    private int col;
    
    private Pieces innerPiece;
	private String SqaureName;

	char[] file = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	
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
	
	public void Set_Piece(Pieces p){ // implicit casting
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

	public int getRank(){
		if (row == 1)
			return 8;

		if (row == 2)
			return 7;

		if (row == 3)
			return 6;

		if (row == 4)
			return 5;

		if (row == 5)
			return 4;

		if (row == 6)
			return 3;

		if (row == 7)
			return 2;

		if (row == 8)
			return 1;

		else return 0;
	}

	public String getSqareName(){
		char file = this.file[col - 1];
		int rank = getRank();
		SqaureName = file + "" + rank;
		return SqaureName;
	}
}

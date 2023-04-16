package main;

import javax.swing.*;

import pieces.*;

import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener{
	public static final int DIMENSION = 8;
	public static final int BOARD_SIZE = 800;
	
	public static Square[][] SquareGrid = new Square[DIMENSION][DIMENSION];
	
	private Color lightColor = new Color(193, 154, 109);
    private Color darkColor = new Color(118, 74, 52);
	private Pieces Focused_piece; 
	private Square Focused_sq;
	private int x_pos;
	private int y_pos;

    public Board(){    	
		this.setLayout(new GridLayout(DIMENSION, DIMENSION));
		this.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));

        innitializeSquare();
        innitializePieces();
        
        // registering action listener
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    void innitializeSquare() {
        for (int i = 1; i <= DIMENSION; i++){
            for (int j = 1; j <= DIMENSION; j++){
                if ((i % 2 != 0) && (j % 2 != 0)){
                    makeSquare(i, j, true); // make white sq when row, and col is odd
                }
                else if ((i % 2 == 0) && (j % 2 == 0)){ 
                	makeSquare(i, j, true); // make white sq when row, and col is even
                }
                else {
                	makeSquare(i, j, false); // other sq is black
                }

            }
        }
    }
    
    // Assigns all default pieces of a chess game to its corresponding square
    void innitializePieces() {
    	// White Pieces
    	for (int i = 1; i <= Board.DIMENSION; i++) {
        	getSquare(7, i).Set_Piece(new Pawn(true));
        	
        }
		getSquare(8, 1).Set_Piece(new Rook(true));
		getSquare(8, 2).Set_Piece(new Horse(true));
		getSquare(8, 3).Set_Piece(new Bishop(true));
		getSquare(8, 4).Set_Piece(new Queen(true));
		getSquare(8, 5).Set_Piece(new King(true));
		getSquare(8, 6).Set_Piece(new Bishop(true));
		getSquare(8, 7).Set_Piece(new Horse(true));
		getSquare(8, 8).Set_Piece(new Rook(true));
		
		// Black Pieces
		for (int i = 1; i <= Board.DIMENSION; i++) {
        	getSquare(2, i).Set_Piece(new Pawn(false));
        }
		getSquare(1, 1).Set_Piece(new Rook(false));
		getSquare(1, 2).Set_Piece(new Horse(false));
		getSquare(1, 3).Set_Piece(new Bishop(false));
		getSquare(1, 4).Set_Piece(new Queen(false));
		getSquare(1, 5).Set_Piece(new King(false));
		getSquare(1, 6).Set_Piece(new Bishop(false));
		getSquare(1, 7).Set_Piece(new Horse(false));
		getSquare(1, 8).Set_Piece(new Rook(false));
    }
    
    // Create a new instance of Square and set an appropriate color
    void makeSquare(int i, int j, boolean lightTile){
    	Square newSquare_panel = new Square(i, j);
    	
        if (lightTile) {
            newSquare_panel.setBackground(lightColor);
        }
        else {
            newSquare_panel.setBackground(darkColor);
        }
        add(newSquare_panel);
        Board.SquareGrid[i-1][j-1] = newSquare_panel;
    }
    
    public Square getSquare(int row, int col) {
    	return SquareGrid[row -1][col -1];
    }
	
	// Search through all square and find the first one with no piece inside, used when adding new piece
	public static Square getAvailableSquare(){
		Square available_sq = null;
		for (Square[] each_row: SquareGrid){
			for (Square that_sq: each_row){
				if (that_sq.getInnerPiece() == null){
					available_sq = that_sq;
				}
			}
		}
		return available_sq;
	}
    
	// use method getSquare() to asign the squre under curser to variable Focused_sq
    private void checkSquare(int x_pos, int y_pos) {
    	int row = Math.ceilDiv(y_pos, (BOARD_SIZE/DIMENSION));
    	int col = Math.ceilDiv(x_pos, (BOARD_SIZE/DIMENSION));
    	
    	Focused_sq = getSquare(row, col);
    }

    public void paint(Graphics g) {
    	super.paint(g);
    	if (Focused_piece != null) {
    		g.drawImage(Focused_piece.getPieceImage(), x_pos - (Focused_piece.getPieceSize() / 2), y_pos - (Focused_piece.getPieceSize() / 2), null);	
    	}
    }
    
	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
			x_pos = e.getX();
			y_pos = e.getY();
			
			this.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			checkSquare(e.getX(), e.getY());
			if (Focused_sq.getInnerPiece() != null) {
				x_pos = e.getX();
				y_pos = e.getY();
				Focused_piece = Focused_sq.getInnerPiece();
				Focused_sq.Remove_Piece(Focused_piece);
				
				this.repaint();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Square sq_temp = Focused_sq; // save the original square

			if (Focused_piece != null){
				try {
					checkSquare(e.getX(), e.getY());
				} catch (Exception o) {							// if the piece is released outside the board, action taken as to remove the piece as if has fallen off the table
					System.out.println("Piece went out of bound");
					Focused_sq.Remove_Piece(Focused_piece);
					Focused_sq = null;
					Focused_piece = null; //Clear focused items
					return;
				}
		
				if (Focused_sq.getInnerPiece() == null) {	// Free square
					x_pos = Math.ceilDiv(e.getX(), (BOARD_SIZE/DIMENSION));
					y_pos = Math.ceilDiv(e.getY(), (BOARD_SIZE/DIMENSION));
					
					Focused_sq.Set_Piece(Focused_piece);
					
					this.repaint();
				}
				else if(Focused_sq.getInnerPiece().isWhite == Focused_piece.isWhite) {	// Piece got same color
					x_pos = Math.ceilDiv(e.getX(), (BOARD_SIZE/DIMENSION));
					y_pos = Math.ceilDiv(e.getY(), (BOARD_SIZE/DIMENSION));
					
					sq_temp.Set_Piece(Focused_piece);
					
					this.repaint();
				}
				else if (Focused_sq.getInnerPiece().isWhite != Focused_piece.isWhite)	{ // Piece got different color
					Focused_sq.Remove_Piece(Focused_sq.getInnerPiece());
					
					Focused_sq.Set_Piece(Focused_piece);
					
					this.repaint();
				}

			Focused_sq = null;
			Focused_piece = null; //Clear focused items
			}
		}
	}

	// unused listener medthods
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}


	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}

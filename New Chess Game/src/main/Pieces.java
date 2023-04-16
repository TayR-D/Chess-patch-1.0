package main;

import java.awt.*;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Pieces extends JLabel{
	private Map<String, ImageIcon> PiecesIcon = new HashMap<>() {{
		// White pieces (isWhite = true)
		put("WKing", new ImageIcon("res/PieceIcon/WKingIcon.png"));
		put("WQueen", new ImageIcon("res/PieceIcon/WQueenIcon.png"));
		put("WRook", new ImageIcon("res/PieceIcon/WRookIcon.png"));
		put("WBishop", new ImageIcon("res/PieceIcon/WBishopIcon.png"));
		put("WHorse", new ImageIcon("res/PieceIcon/WHorseIcon.png"));
		put("WPawn", new ImageIcon("res/PieceIcon/WPawnIcon.png"));
		
		// Black Pieces (isWhite = false)
		put("BKing", new ImageIcon("res/PieceIcon/BKingIcon.png"));
		put("BQueen", new ImageIcon("res/PieceIcon/BQueenIcon.png"));
		put("BRook", new ImageIcon("res/PieceIcon/BRookIcon.png"));
		put("BBishop", new ImageIcon("res/PieceIcon/BBishopIcon.png"));
		put("BHorse", new ImageIcon("res/PieceIcon/BHorseIcon.png"));
		put("BPawn", new ImageIcon("res/PieceIcon/BPawnIcon.png"));		
	}};
	
	protected int size = Board.BOARD_SIZE /Board.DIMENSION;
	
	protected String pieceIcon_key;
	protected Image pieceImage;
	protected boolean isWhite;

    protected Pieces(String iconName, boolean isWhite){
    	this.setOpaque(false);
        pieceIcon_key = iconName;
        this.isWhite = isWhite;
        
        // using isWhite to defines which icon path to be use
        if (isWhite) {
        	pieceIcon_key = "W" + pieceIcon_key;
        }
        else {pieceIcon_key = "B" + pieceIcon_key;}
        
        // set image
        pieceImage = PiecesIcon.get(pieceIcon_key).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
    }
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(pieceImage, 0, 0, null);
    }
      
    public Image getPieceImage() {
		return pieceImage;
    }
    
    public String getPieceName() {
    	return pieceIcon_key;
    }
    
    public int getPieceSize() {
    	return size;
    }
}

package main;

import java.awt.*;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Pieces extends JPanel{
	private Map<String, ImageIcon> PiecesIcon = new HashMap<>() {{
		// White pieces (isWhite = true)
		put("WKing", new ImageIcon("New Chess Game/res/PieceIcon/WKingIcon.png"));
		put("WQueen", new ImageIcon("New Chess Game/res/PieceIcon/WQueenIcon.png"));
		put("WRook", new ImageIcon("New Chess Game/res/PieceIcon/WRookIcon.png"));
		put("WBishop", new ImageIcon("New Chess Game/res/PieceIcon/WBishopIcon.png"));
		put("WHorse", new ImageIcon("New Chess Game/res/PieceIcon/WHorseIcon.png"));
		put("WPawn", new ImageIcon("New Chess Game/res/PieceIcon/WPawnIcon.png"));
		
		// Black Pieces (isWhite = false)
		put("BKing", new ImageIcon("New Chess Game/res/PieceIcon/BKingIcon.png"));
		put("BQueen", new ImageIcon("New Chess Game/res/PieceIcon/BQueenIcon.png"));
		put("BRook", new ImageIcon("New Chess Game/res/PieceIcon/BRookIcon.png"));
		put("BBishop", new ImageIcon("New Chess Game/res/PieceIcon/BBishopIcon.png"));
		put("BHorse", new ImageIcon("New Chess Game/res/PieceIcon/BHorseIcon.png"));
		put("BPawn", new ImageIcon("New Chess Game/res/PieceIcon/BPawnIcon.png"));		
	}};
	
	protected int size = Board.BOARD_SIZE /Board.DIMENSION;
	protected int x_pos = 0;
	protected int y_pos = 0;
	
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
    	g.drawImage(pieceImage, x_pos, y_pos, null);
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

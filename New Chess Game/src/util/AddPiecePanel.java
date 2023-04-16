package util;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.Board;
import main.Pieces;
import pieces.*;

public class AddPiecePanel extends JPanel implements ActionListener{
    private JButton addWhitePiece_btn = new JButton("Add White Piece");
    private JButton addBlackPiece_btn = new JButton("Add Black Piece");
    private JButton ok_btn = new JButton("OK");

    private ButtonGroup pieceChoices_ButtonGroup = new ButtonGroup();

    private JRadioButton king_Choice = new JRadioButton("King");
    private JRadioButton queen_Choice = new JRadioButton("Queen");
    private JRadioButton rook_Choice = new JRadioButton("Rook");
    private JRadioButton bishop_Choice = new JRadioButton("Bishop");
    private JRadioButton horse_Choice = new JRadioButton("Horse");
    private JRadioButton pawn_Choice = new JRadioButton("Pawn");

    private boolean isWhite;

    public AddPiecePanel(){
        this.setLayout(new FlowLayout());
        this.setSize(150, 200);
        // this.setBackground(Color.gray);

        this.add(addWhitePiece_btn);
        this.add(addBlackPiece_btn);

        pieceChoices_ButtonGroup.add(king_Choice);
		pieceChoices_ButtonGroup.add(queen_Choice);
        pieceChoices_ButtonGroup.add(rook_Choice);
        pieceChoices_ButtonGroup.add(queen_Choice);
        pieceChoices_ButtonGroup.add(bishop_Choice);
        pieceChoices_ButtonGroup.add(horse_Choice);
        pieceChoices_ButtonGroup.add(pawn_Choice);

        // register listener
        addWhitePiece_btn.addActionListener(this);
        addBlackPiece_btn.addActionListener(this);
        king_Choice.addActionListener(this);
        queen_Choice.addActionListener(this);
		rook_Choice.addActionListener(this);
		bishop_Choice.addActionListener(this);
		horse_Choice.addActionListener(this);
		pawn_Choice.addActionListener(this);
        ok_btn.addActionListener(this);
    }

    private void addChoices(){
        this.add(king_Choice);
        this.add(queen_Choice);
        this.add(rook_Choice);
        this.add(bishop_Choice);
        this.add(horse_Choice);
        this.add(pawn_Choice);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void warnPlayer(){
        pieceChoices_ButtonGroup.clearSelection();
        this.removeAll();
        this.add(new JLabel("No Free Sqaure"));
        this.add(new JLabel("Please reset board"));
        this.add(ok_btn);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void addPieceToBoard(Pieces p){
        Board.getAvailableSquare().Set_Piece(p);
        System.out.println("piece setted");  
        
        this.removeAll();
        pieceChoices_ButtonGroup.clearSelection();
        this.add(addWhitePiece_btn);
        this.add(addBlackPiece_btn);
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add White Piece":
                this.removeAll();
                isWhite = true;
                addChoices();
                break;

            case "Add Black Piece":
                isWhite = false;
                this.removeAll();
                addChoices();;
                break;

            case "King":
                try {
                    addPieceToBoard(new King(isWhite));
                    break;
                } catch (NullPointerException o) {
                    warnPlayer();
                    break;
            }

            case "Queen":
                try {
                    addPieceToBoard(new Queen(isWhite));
                    break;
                } catch (NullPointerException o) {
                    warnPlayer();
                    break;
                }

            case "Rook":
            try {
                addPieceToBoard(new Rook(isWhite));
                break;
            } catch (NullPointerException o) {
                warnPlayer();
                break;
            }

            case "Bishop":
            try {
                addPieceToBoard(new Bishop(isWhite));
                break;
            } catch (NullPointerException o) {
                warnPlayer();
                break;
            }

            case "Horse":
            try {
                addPieceToBoard(new Horse(isWhite));
                break;
            } catch (NullPointerException o) {
                warnPlayer();
                break;
            } 

            case "Pawn":
            try {
                addPieceToBoard(new Pawn(isWhite));
                break;
            } catch (NullPointerException o) {
                warnPlayer();
                break;
            } 

            case "OK":
                this.removeAll();
                this.add(addWhitePiece_btn);
                this.add(addBlackPiece_btn);
                SwingUtilities.updateComponentTreeUI(this);
                break;
            default:
                break;
        }
    }
}

import java.awt.*;
import javax.swing.*;

public class TestBug extends JPanel{

    ButtonGroup pieceChoices_ButtonGroup = new ButtonGroup();
    JRadioButton queen_Choice = new JRadioButton("Queen");
    JRadioButton rook_Choice = new JRadioButton("Rook");
    JRadioButton bishop_Choice = new JRadioButton("Bishop");
    JRadioButton horse_Choice = new JRadioButton("Horse");
    JRadioButton pawn_Choice = new JRadioButton("Pawn");

	TestBug(){
		this.setLayout(new FlowLayout());
		this.add(queen_Choice);
		queen_Choice.setSize(20, 800);
		this.add(rook_Choice);
		this.add(bishop_Choice);
		this.add(horse_Choice);
		this.add(pawn_Choice);

		pieceChoices_ButtonGroup.add(queen_Choice);
        pieceChoices_ButtonGroup.add(rook_Choice);
        pieceChoices_ButtonGroup.add(queen_Choice);
        pieceChoices_ButtonGroup.add(bishop_Choice);
        pieceChoices_ButtonGroup.add(horse_Choice);
        pieceChoices_ButtonGroup.add(pawn_Choice);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		f.setSize(200, 200);

		JPanel p = new TestBug();

		f.add(p);
		f.setVisible(true);
	}
}

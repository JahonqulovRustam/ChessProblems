import chess.Square;
import org.junit.Test;
import rookmate.RookMate;

import static org.junit.Assert.assertFalse;

public class RookMateTest {
	private static final RookMate rookMate = new RookMate();
	@Test
	public void test() {
		Square blackKing = new Square("h8");
		Square whiteKing = new Square("a1");
		Square rook = new Square("a7");
		
		assertFalse(rookMate.isCheckmate(whiteKing, rook, blackKing));
	}
	
	@Test
	public void newTest() {
		Square blackKing = new Square("a8");
		Square whiteKing = new Square("c7");
		Square rook = new Square("c6");
		
		assertFalse(rookMate.isCheckmate(whiteKing, rook, blackKing));
	}
}

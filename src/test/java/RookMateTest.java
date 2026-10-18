import chess.Square;
import org.junit.Test;
import rookmate.RookMate;

import static org.junit.Assert.*;

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
	
	@Test
	public void test1() {
		Square blackKing = new Square("a5");
		Square whiteKing = new Square("c5");
		Square rook = new Square("a3");
		
		assertTrue(rookMate.isCheckmate(whiteKing, rook, blackKing));
	}
	
	@Test
	public void test2() {
		Square blackKing = new Square("d8");
		Square whiteKing = new Square("d4");
		Square rook = new Square("h8");
		
		assertFalse(rookMate.isCheckmate(whiteKing, rook, blackKing));
	}
	
	@Test
	public void test3() {
		Square blackKing = new Square("h6");
		Square whiteKing = new Square("g6");
		Square rook = new Square("h3");
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
				rookMate.isCheckmate(whiteKing, rook, blackKing)
		);
		
		assertEquals("Illegal chess rules!", exception.getMessage());
	}
	
//	@Test
//	public void test4() {
//		Square blackKing = new Square("a3");
//		Square whiteKing = new Square("c3");
//
//		List<Square> expected = List.of(
//				new Square("a1"),
//				new Square("a2"),
//				new Square("a4"),
//				new Square("a5"),
//				new Square("a6"),
//				new Square("a7"),
//				new Square("a8"),
//				new Square("b3")
//		);
//	}
}

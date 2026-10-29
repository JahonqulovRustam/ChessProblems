import chess.Square;
import org.junit.Test;
import rookmate.RookMate;

import java.util.List;

import static org.junit.Assert.*;

public class RookMateTest {
	@Test
    public void noMateWhenWKTooFarToBlackKing() {
		Square whiteKing = new Square("e5");
		Square blackKing = new Square("e8");

        List<Square> expected = List.of();
		assertEquals(expected, RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
	}
	
	@Test
	public void noMateWhenKingsAreApart() {
		Square whiteKing = new Square("e5");
		Square blackKing = new Square("h5");
		
		List<Square> expected = List.of();
		assertEquals(expected, RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
	}
	
	@Test
	public void checkAdjacentKings() {
		Square whiteKing = new Square("g2");
		Square blackKing = new Square("h1");
		
		Exception e = assertThrows(IllegalArgumentException.class, () ->
				RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
		
		assertEquals("Two kings can never be adjacent in chess!", e.getMessage());
	}

	@Test
    public void horizontalMate() {
		Square whiteKing = new Square("g6");
		Square blackKing = new Square("g8");

        List<Square> expected = List.of(new Square("a8"), new Square("b8"), new Square("c8"), new Square("d8"), new Square("e8"));
		assertEquals(expected, RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
	}

	@Test
    public void verticalMate() {
		Square whiteKing = new Square("f5");
		Square blackKing = new Square("h5");

        List<Square> expected = List.of(new Square("h1"), new Square("h2"), new Square("h3"), new Square("h7"), new Square("h8"));
		assertEquals(expected, RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
	}

	@Test
    public void cornerMate() {
		Square whiteKing = new Square("a3");
		Square blackKing = new Square("a1");

        List<Square> expected = List.of(new Square("c1"), new Square("d1"), new Square("e1"), new Square("f1"), new Square("g1"), new Square("h1"));
		assertEquals(expected, RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
	}

	@Test
    public void testDifferentFileCornerCheckmate() {
		Square whiteKing = new Square("b3");
		Square blackKing = new Square("a1");

        List<Square> expected = List.of(new Square("c1"), new Square("d1"), new Square("e1"), new Square("f1"), new Square("g1"), new Square("h1"));
		assertEquals(expected, RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
	}

	@Test
    public void horizontalMateOnBottom() {
		Square whiteKing = new Square("b3");
		Square blackKing = new Square("b1");

        List<Square> expected = List.of(new Square("d1"), new Square("e1"), new Square("f1"), new Square("g1"), new Square("h1"));
		assertEquals(expected, RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
	}
	
	@Test
    public void verticalMateOnTop() {
		Square whiteKing = new Square("c7");
		Square blackKing = new Square("a7");

        List<Square> expected = List.of(new Square("a1"), new Square("a2"), new Square("a3"), new Square("a4"), new Square("a5"));
		assertEquals(expected, RookMate.findCheckMatingRookSquares(whiteKing, blackKing));
	}
}

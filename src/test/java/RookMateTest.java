import chess.Square;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rookmate.RookMate;

import java.util.List;

import static org.junit.Assert.*;

public class RookMateTest {
	
	@ParameterizedTest
	@CsvSource({
			"e5, e8",
			"e5, h5",
			"a3, c1"
	})
	
	public void isNotMateByRook(String whiteKing, String blackKing) {
		List<Square> expected = RookMate.findCheckMatingRookSquares(new Square(whiteKing), new Square(blackKing));
		assertTrue(expected.isEmpty());
	}
	
	@ParameterizedTest
	@CsvSource({
			"g2, h1",
			"a4, b4",
			"d5, d6",
			"h8, g7"
	})
	
	public void checkAdjacentKings(String whiteKing, String blackKing) {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
				RookMate.findCheckMatingRookSquares(new Square(whiteKing), new Square(blackKing)));
		
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
	public void testDifferentRookCornerCheckmate() {
		Square whiteKing = new Square("c2");
		Square blackKing = new Square("a1");

		List<Square> expected = List.of(new Square("a3"), new Square("a4"), new Square("a5"), new Square("a6"), new Square("a7"), new Square("a8"));
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

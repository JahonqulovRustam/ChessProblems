import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import chess.*;
import java.util.*;

public class ChessCheckDetectorTest {
	
	private final ChessCheckDetector detector = new ChessCheckDetector();
	
	@ParameterizedTest
	@CsvSource({
			"e4, h8, e4, false",
			"e4, e4, h8, false",
			"a1, e4, f4, false",
			"a1, e4, e5, false",
			"a1, e4, f5, false",
			"c2, h8, f5, true"
	})
	
	void testIsKingsNotAdjacentAndNoOverlap(String whiteQueen, String whiteKing, String blackKing, boolean expected) {
		
		assertEquals(
				expected, detector.isKingsNotAdjacentAndNoOverlap(new Square(whiteQueen), new Square(whiteKing), new chess.Square(blackKing))
		);
	}
	
	
	@ParameterizedTest
	@CsvSource({
			"a4, h1, e4, true",
			"h5, a1, b5, true",
			"d8, a1, d4, true",
			"b7, h1, e4, true",
			"a4, d1, h4, true",
			"a1, h8, e4, false",
			"c2, a8, f6, false"
	})
	void testIsBlackKingInCheck(String whiteQueen, String whiteKing, String blackKing, boolean expected) {
		assertEquals(
				expected, detector.isBlackKingInCheck(new Square(whiteQueen), new Square(whiteKing),new Square(blackKing))
		);
	}
	
	@Test
	public void shouldReturnOnlySafeSquaresWhenQueenIsNearby() {
		Square whiteQueen = new Square("b2");
		Square blackKing = new Square("c3");
		Square whiteKing = new Square("a1");
		
		ChessCheckDetector detector = new ChessCheckDetector();
		
		List<Square> result = detector.canMove(whiteQueen, whiteKing, blackKing);
		
		assertEquals(2, result.size());
		
		assertTrue(result.contains(new Square("c4")));
		assertTrue(result.contains(new Square("d3")));
		
		assertFalse(result.contains(new Square("b2")));
		assertFalse(result.contains(new Square("b3")));
		assertFalse(result.contains(new Square("b4")));
		assertFalse(result.contains(new Square("c2")));
		assertFalse(result.contains(new Square("d2")));
		assertFalse(result.contains(new Square("d4")));
	}
	
	@Test
	public void testCanEscape() {
		
		Square whiteQueen = new Square("b2");
		Square blackKing  = new Square("c3");
		Square whiteKing  = new Square("a1");
		
		List<Square> list = detector.canMove(whiteQueen, whiteKing, blackKing);
		
		assertNotNull(list);
		assertFalse(list.isEmpty());
		
		Square candidate = detector.canEscape(whiteQueen, whiteKing, blackKing, list);
		
		assertNotNull(candidate);
		assertTrue(list.contains(candidate));
	}
	
	
	@Test
	public void testBlackKingNotInCheck() {
		Square whiteQueen = new Square("a1");
		Square blackKing  = new Square("e4");
		Square whiteKing  = new Square("h8");
		
		assertFalse(detector.isBlackKingInCheck(whiteQueen, whiteKing, blackKing));
		
		List<Square> legalMoves = detector.canMove(whiteQueen, whiteKing, blackKing);
		assertFalse(legalMoves.isEmpty());
		
		Square candidate = detector.canEscape(whiteQueen, whiteKing, blackKing, legalMoves);
		assertNull(candidate);
		
		assertFalse(detector.isBlackKingInMate(whiteQueen, whiteKing, blackKing));
	}
	
	@Test
	public void testBlackKingInMate() {
		Square whiteQueen = new Square("g7");
		Square whiteKing  = new Square("f6");
		Square blackKing  = new Square("h8");
		
		List<Square> list = detector.canMove(whiteQueen, whiteKing, blackKing);
		
		assertTrue(list.isEmpty());
		
		Square candidate = detector.canEscape(whiteQueen, whiteKing, blackKing, list);
		
		assertNull(candidate);
		
		assertTrue(detector.isBlackKingInMate(whiteQueen, whiteKing, blackKing));
	}
}

import chess.CheckmateChecker;
import chess.ChessCheckDetector;
import chess.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CheckmateCheckerTest {
	
	private final ChessCheckDetector chessCheckDetector =
			new ChessCheckDetector();
	
	private final CheckmateChecker checkmateChecker =
			new CheckmateChecker(chessCheckDetector);
	
	
	@ParameterizedTest
	@CsvSource({
			"g6, f7, h8, true",
			"b6, c7, a8, true",
			"b3, c2, a1, true",
			"g3, f2, h1, true",
			"g6, f6, h8, true",
			"b6, c6, a8, true",
			
			"a1, h8, e4, false",
			"g6, f7, h7, false"
	})
	
	void isBlackKingInPatTest(String whiteQueen, String whiteKing, String blackKing, boolean expected) {
		assertEquals(expected, checkmateChecker.isBlackKingInPat(new Square(whiteQueen), new Square(whiteKing), new Square(blackKing)));
	}
	
	
	@Test
	void getQueenReachableSquareTest() {
		Square whiteQueen = new Square("c2");
		Square whiteKing = new Square("b3");
		Square blackKing = new Square("a1");
		
		List<Square> actual = checkmateChecker.getQueenReachableSquares(whiteQueen, whiteKing, blackKing);
		
		List<String> expected = List.of("a2", "b2", "d2", "e2", "f2", "g2", "h2", "c1", "c3", "c4",
				"c5", "c6", "c7", "c8", "d1", "b1", "d3", "e4", "f5", "g6", "h7");
		
		assertEquals(21, actual.size());
		
		for (String str : expected) {
			Square square = new Square(str);
			
			assertTrue(actual.contains(square));
		}
	}
	
	@Test
	void canBlackKingInMateWhileWhitePieceMovesOnceTest() {
		
		Square whiteQueen = new Square("c2");
		Square whiteKing = new Square("b3");
		Square blackKing = new Square("a1");
		
		List<String> expected = List.of("a2", "b2", "c1", "b1", "d1");
		
		assertNotNull(expected);
		assertFalse(expected.isEmpty());
		
		Square candidate = checkmateChecker.canBlackKingInMateWhileWhitePieceMovesOnce(whiteQueen, whiteKing, blackKing);
		
		assertNotNull(candidate);
		assertTrue(expected.contains(candidate.toString()));
	}
}

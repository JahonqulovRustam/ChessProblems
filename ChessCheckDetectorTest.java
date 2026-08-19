
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessCheckDetectorTest {
	
	@Test
	void testIsBlackKingInCheck() {
		
		ChessCheckDetector detector = new ChessCheckDetector();
		
		assertTrue(detector.isValid(new Position("a4"), new Position("h8"), new Position("e4")));
		assertTrue(detector.isBlackKingInCheck(new Position("a4"), new Position("e4")));
		
		assertTrue(detector.isValid(new Position("h5"), new Position("a1"), new Position("b5")));
		assertTrue(detector.isBlackKingInCheck(new Position("h5"), new Position("b5")));
		
		assertTrue(detector.isValid(new Position("d8"), new Position("a1"), new Position("d4")));
		assertTrue(detector.isBlackKingInCheck(new Position("d8"), new Position("d4")));
		
		assertTrue(detector.isValid(new Position("b7"), new Position("h1"), new Position("e4")));
		assertTrue(detector.isBlackKingInCheck(new Position("b7"), new Position("e4")));
		
		assertTrue(detector.isValid(new Position("a1"), new Position("h8"), new Position("e4")));
		assertFalse(detector.isBlackKingInCheck(new Position("a1"), new Position("e4")));
		
		assertTrue(detector.isValid(new Position("h1"), new Position("a8"), new Position("d4")));
		assertFalse(detector.isBlackKingInCheck(new Position("h1"), new Position("d4")));
		
		assertTrue(detector.isValid(new Position("c2"), new Position("h8"), new Position("f6")));
		assertFalse(detector.isBlackKingInCheck(new Position("c2"), new Position("f6")));
		
		
		assertTrue(detector.isValid(new Position("a4"), new Position("b6"), new Position("h4")));
		assertTrue(detector.isBlackKingInCheck(new Position("a4"), new Position("h4")));
	}
	
	
	@Test
	void testIsValid() {

		ChessCheckDetector detector = new ChessCheckDetector();
		
		assertFalse(detector.isValid(new Position("e4"), new Position("h8"), new Position("e4")));
		
		assertFalse(detector.isValid(new Position("e4"), new Position("e4"), new Position("h8")));
		
		assertFalse(detector.isValid(new Position("a1"), new Position("e4"), new Position("f4")));
		
		assertFalse(detector.isValid(new Position("a1"), new Position("e4"), new Position("e5")));
		
		assertFalse(detector.isValid(new Position("a1"), new Position("e4"), new Position("f5")));
		
		assertTrue(detector.isValid(new Position("c2"), new Position("h8"), new Position("f5")));
	}
}

import chess.Square;
import exceptions.InvalidSquareException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {
	
	
	@ParameterizedTest
	@CsvSource({
			"e4, 4",
			"a1, 1",
			"e5, 5",
			"f5, 5",
			"c2, 2"
	})
	
	void testGetRank(String square, int rank) {
		
		assertEquals(
				rank, new Square(square).getRank()
		);
	}
	
	@ParameterizedTest
	@CsvSource({
			"e4, 4",
			"a1, 0",
			"d5, 3",
			"f5, 5",
			"c2, 2"
	})
	
	void testGetFile(String square, int file) {
		assertEquals(
				file, new Square(square).getFile()
		);
	}
	
	
	@ParameterizedTest
	@CsvSource({
			"e4, true",
			"m5, false",
			"d5, true",
			"f9, false",
			"c0, false"
	})
	
	void testisValidSquare(String square, boolean expected) {
		
		if (expected) {
			assertDoesNotThrow(() -> new Square(square));
		} else {
			assertThrows(InvalidSquareException.class, () -> new Square(square));
		}
	}
	
	@Test
	public void testToString() {
		assertEquals("e4", new Square("e4").toString());
		assertNotEquals("f3", new Square("a4").toString());
	}
	
	@ParameterizedTest
	@CsvSource({
			"e4, e4, true",
			"a1, a2, false",
			"d5, d5, true",
			"f5, f2, false",
			"c2, c2, true"
	})
	
	void testEqualsObjects(String square1, String square2, boolean expected) {
		assertEquals(
				expected, new Square(square1).equals(new Square(square2))
		);
	}
	
	
	
}

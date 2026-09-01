import java.util.*;
import chess.ChessCheckDetector;
import chess.Square;
import exceptions.InvalidSquareException;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessCheckDetector checkDetector = new ChessCheckDetector();
		
		Square posWhiteQueen = readPosition(sc, "whiteQueen");
		Square posWhiteKing = readPosition(sc, "whiteKing");
		Square posBlackKing = readPosition(sc, "blackKing");
		
		if (checkDetector.isKingsNotAdjacentAndNoOverlap(posWhiteQueen, posWhiteKing, posBlackKing)) {
			
			boolean result = checkDetector.isBlackKingInCheck(posWhiteQueen, posWhiteKing, posBlackKing);
			
			if (result) {
				System.out.println("BlackKing in check!");
			} else {
				System.out.println("BlackKing is not in check!");
			}
		} else {
			
			System.out.println("Illegal chess position");
		}
		
		Square res = checkDetector.findEscapeSquare(posWhiteQueen, posWhiteKing, posBlackKing);
		
		if (res != null) {
			System.out.println(res.toString());
		}
		
		if (checkDetector.isBlackKingInMate(posWhiteQueen, posWhiteKing, posBlackKing)) {
			System.out.println("BlackKing in mate");
		}
	}
	
	public static Square readPosition(Scanner sc, String name) {
		
		while (true) {
			
			try {
				System.out.print("Position of " + name + " : ");
				
				return new Square(sc.next());
			} catch (InvalidSquareException e) {
				
				System.out.println(e.getMessage());
			}
		}
	}
}

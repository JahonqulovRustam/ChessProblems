
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
		System.out.print("Position of whiteQueen: ");
		Position posWhiteQueen = new Position(scanner.next());
		System.out.print("Position of whiteKing: ");
		Position posWhiteKing = new Position(scanner.next());
		System.out.print("Position of blackKing: ");
		Position posBlackKing = new Position(scanner.next());
		
		ChessCheckDetector checkDetector = new ChessCheckDetector();
		
		if (checkDetector.isValid(posWhiteQueen, posWhiteKing, posBlackKing)) {
			
			boolean result = checkDetector.isBlackKingInCheck(posWhiteQueen, posBlackKing);
			
			System.out.println(result);
		} else {
			
			System.out.println("Positions might be invalid!");
		}
	}
}
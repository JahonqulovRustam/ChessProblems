import chess.ChessCheckDetector;
import chess.Square;
import exceptions.InvalidSquareException;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessCheckDetector checkDetector = new ChessCheckDetector();
		
		Square whiteQueen = readSquare(sc, "whiteQueen");
		Square whiteKing = readSquare(sc, "whiteKing");
		Square blackKing = readSquare(sc, "blackKing");
		
		if (checkDetector.isKingsNotAdjacentAndNoOverlap(whiteQueen, whiteKing, blackKing)) {
			
			boolean result = checkDetector.isBlackKingInCheck(whiteQueen, whiteKing, blackKing);
			
			if (result) {
				System.out.println("BlackKing in check!");
			} else {
				System.out.println("BlackKing is not in check!");
			}
		} else {
			
			System.out.println("Illegal chess position");
		}
		
		Square res = checkDetector.findEscapeSquare(whiteQueen, whiteKing, blackKing);
		
		if (res != null) {
			System.out.println(res.toString());
		}
		
		if (checkDetector.isBlackKingInMate(whiteQueen, whiteKing, blackKing)) {
			System.out.println("BlackKing in mate");
		}
	}
	
	public static Square readSquare(Scanner sc, String name) {
		
		while (true) {
			
			try {
				System.out.print("Square of " + name + " : ");
				
				return new Square(sc.next());
			} catch (InvalidSquareException e) {
				
				System.out.println(e.getMessage());
			}
		}
	}
}

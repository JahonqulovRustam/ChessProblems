import chess.ChessCheckDetector;
import chess.MateInOneMove;
import chess.Square;
import exceptions.InvalidSquareException;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessCheckDetector checkDetector = new ChessCheckDetector();
		MateInOneMove checker = new MateInOneMove(checkDetector);

		Square whiteQueen = readSquare(sc, "whiteQueen");
		Square whiteKing = readSquare(sc, "whiteKing");
		Square blackKing = readSquare(sc, "blackKing");

		if (!checkDetector.isKingsNotAdjacentAndNoOverlap(whiteQueen, whiteKing, blackKing)) {

			System.out.println("Illegal chess position");
			return;
		}

		boolean inCheck = checkDetector.isBlackKingInCheck(whiteQueen, whiteKing, blackKing);
		Square escapeSquare = checkDetector.findEscapeSquare(whiteQueen, whiteKing, blackKing);

		if (inCheck) {
			System.out.println("BlackKing in check!");
			if (escapeSquare == null) {
				System.out.println("BlackKing in mate!");
			} else {
				System.out.println("BlackKing can escape to " + escapeSquare + " escapeSquare");
			}
		} else {
			if(checker.isBlackKingInPat(whiteQueen, whiteKing, blackKing)) {
				System.out.println("Pat!");
			} else {
				
				Square square = checker.findNewSquare(whiteQueen, whiteKing, blackKing);
				if (square != null) {
					System.out.println("Move the queen to " + square + " for checkmate!");
				} else {
					System.out.println("BlackKing is not in check!");
				}
			}
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

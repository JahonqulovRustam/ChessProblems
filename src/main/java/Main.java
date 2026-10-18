import chess.ChessCheckDetector;
import chess.MateInOneMove;
import chess.Square;
import exceptions.InvalidSquareException;
import rookmate.RookMate;

import java.util.List;
import java.util.Scanner;

public class Main {
	private static final Scanner sc = new Scanner(System.in);
	private static final ChessCheckDetector checkDetector = new ChessCheckDetector();
	private static final MateInOneMove checker = new MateInOneMove(checkDetector);
	private static final RookMate rookMate = new RookMate();
	
	public static void main(String[] args) {
		while (true) {
			System.out.println("1.Queen bilan ishlash\n2.Rook bilan ishlash");
			int choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					processWithWhiteQueen();
					break;
				case 2:
					processWithRook();
					break;
				default:
					return;
			}
		}
	}
	
	private static void processWithWhiteQueen() {
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
			if(checker.isStalemate(whiteQueen, whiteKing, blackKing)) {
				System.out.println("Stalemate!");
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
	
	private static void processWithRook() {
		Square whiteKing = readSquare(sc, "whiteKing");
		Square blackKing = readSquare(sc, "blackKing");
		
		
		
		try {
			List<Square> result = rookMate.findRookMoves(whiteKing, blackKing);
			result.forEach(System.out::println);
		} catch (IllegalArgumentException e) {
			System.out.println("Ikkita shoh hech qachon yonma yon bo'la olmaydi");
		}
	}
	
	private static Square readSquare(Scanner sc, String name) {

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

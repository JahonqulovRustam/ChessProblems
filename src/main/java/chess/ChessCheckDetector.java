
package chess;
import java.util.*;


public class ChessCheckDetector {
	
	public boolean isKingsNotAdjacentAndNoOverlap(Square whiteQueen, Square whiteKing, Square blackKing) {
		
		boolean isSameCell = (whiteQueen.getRank() == blackKing.getRank() && whiteQueen.getFile() == blackKing.getFile()) ||
				(whiteKing.getRank() == blackKing.getRank() && whiteKing.getFile() == blackKing.getFile()) ||
				(whiteQueen.getRank() == whiteKing.getRank() && whiteQueen.getFile() == whiteKing.getFile());
		
		boolean isAdjacent = isSquareAttackedByWhiteKing(whiteKing, blackKing);
		
		return !(isSameCell || isAdjacent);
	}
	
	public boolean isWhiteKingBetweenWhiteQueenAndBlackKing(Square whiteQueen, Square whiteKing, Square blackKing) {
		boolean isStraightLine = false;
		boolean isInBoundaries = false;
		boolean isSameRow = false;
		boolean isSameColumn = false;
		boolean isSameDiagonal = false;
		
		if (isKingsNotAdjacentAndNoOverlap(whiteQueen, whiteKing, blackKing)) {
			
			int x1 = whiteQueen.getRank();
			int y1 = whiteQueen.getFile();
			
			int x2 = blackKing.getRank();
			int y2 = blackKing.getFile();
			
			int x3 = whiteKing.getRank();
			int y3 = whiteKing.getFile();
			
			isStraightLine = (x3 - x1) * (y2 - y1) == (y3 - y1) * (x2 - x1);
			
			isInBoundaries = Math.min(x1, x2) <= x3 && x3 <= Math.max(x1, x2) &&
					Math.min(y1, y2) <= y3 && y3 <= Math.max(y1, y2);
			
			
			isSameRow = (x1 == x2 && x2 == x3);
			isSameColumn = (y1 == y2 && y2 == y3);
			isSameDiagonal = Math.abs(x1 - x2) == Math.abs(y1 - y2);
		}
		
		return (isSameRow || isSameColumn || isSameDiagonal) && isStraightLine && isInBoundaries;
	}
	
	
	public boolean isBlackKingInCheck(Square whiteQueen, Square whiteKing, Square blackKing) {
		
		return (whiteQueen.getRank() == blackKing.getRank() ||
				whiteQueen.getFile() == blackKing.getFile() ||
				Math.abs(whiteQueen.getRank() - blackKing.getRank()) == Math.abs(whiteQueen.getFile() - blackKing.getFile())) && !isWhiteKingBetweenWhiteQueenAndBlackKing(whiteQueen, whiteKing, blackKing);
	}
	
	public boolean isSquareAttackedByWhiteKing(Square whiteKing, Square candidate) {
		
		return Math.abs(whiteKing.getRank() - candidate.getRank()) <= 1 && Math.abs(whiteKing.getFile() - candidate.getFile()) <= 1;
	}
	
	public boolean isSquareAttackedByWhiteQueen(Square whiteQueen, Square whiteKing,Square candidate) {
		
		boolean blocked = isWhiteKingBetweenWhiteQueenAndBlackKing(whiteQueen, candidate, whiteKing);
		
		boolean isSameRank = whiteQueen.getRank() == candidate.getRank();
		boolean isSameFile = whiteQueen.getFile() == candidate.getFile();
		boolean isSameDiagonal = Math.abs(whiteQueen.getRank() - candidate.getRank()) == Math.abs(whiteQueen.getFile() - candidate.getFile());
		
		return (isSameRank || isSameFile || isSameDiagonal) && !blocked;
	}
	
	public List<Square> canMove(Square whiteQueen, Square whiteKing, Square blackKing) {
		List<Square> squares = new ArrayList<>();
		
		int currentRank = blackKing.getRank();
		int currentFile = blackKing.getFile();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				
				if (i == 0 && j == 0) {
					continue;
				}
				
				int newRank = currentRank + i;
				int newFile = currentFile + j;
				
				if (0 <= newRank && newRank <= 7 &&
						0 <= newFile && newFile <= 7) {
					
					char file = (char) ('a' + newFile);
					char rank = (char) ('8' - newRank);
					
					String newSquare =  "" + file + rank;
					Square square = new Square(newSquare);
					
					if (square.equals(whiteQueen) || square.equals(whiteKing)) {
						continue;
					}
					
					if (isSquareAttackedByWhiteKing(whiteKing, square) || isSquareAttackedByWhiteQueen(whiteQueen, whiteKing, square)) {
						continue;
					}
					
					squares.add(square);
				}
			}
		}
		
		return squares;
	}
	
	public Square findEscapeSquare(Square whiteQueen, Square whiteKing, Square blackKing) {
		
		List<Square> squares = canMove(whiteQueen, whiteKing, blackKing);
		
		if (isBlackKingInCheck(whiteQueen, whiteKing, blackKing)) {
			
			for (Square newBlackKingSquare : squares) {
				
				if (!isBlackKingInCheck(whiteQueen, whiteKing, newBlackKingSquare)) {
					return newBlackKingSquare;
				}
			}
		}
		
		return null;
	}
	
	public boolean isBlackKingInMate(Square whiteQueen, Square whiteKing, Square blackKing) {
		
		List<Square> legalMoves = canMove(whiteQueen, whiteKing, blackKing);
		
		return isBlackKingInCheck(whiteQueen, whiteKing, blackKing) && legalMoves.isEmpty();
	}
}

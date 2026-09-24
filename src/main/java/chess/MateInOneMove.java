package chess;

import java.util.ArrayList;
import java.util.List;

public class MateInOneMove {
	
	private final ChessCheckDetector chessCheckDetector;
	
	public MateInOneMove(ChessCheckDetector chessCheckDetector) {
		this.chessCheckDetector = chessCheckDetector;
	}
	
	public boolean isStalemate(Square whiteQueen, Square whiteKing, Square blackKing) {
		
		return !chessCheckDetector.isBlackKingInCheck(whiteQueen, whiteKing, blackKing) &&
				chessCheckDetector.canMove(whiteQueen, whiteKing, blackKing).isEmpty();
	}
	
	public Square findNewSquare(Square whiteQueen, Square whiteKing, Square blackKing) {
		List<Square> reachableSquares = getQueenReachableSquares(whiteQueen, whiteKing, blackKing);
		
		for (Square newQueenSquare : reachableSquares) {
			if (chessCheckDetector.isBlackKingInMate(newQueenSquare, whiteKing, blackKing)) {
				return newQueenSquare;
			}
		}
		
		return null;
	}
	
	public List<Square> getQueenReachableSquares(Square whiteQueen, Square whiteKing, Square blackKing) {
		List<Square> squares = new ArrayList<>();
		
		int[][] directions = {
				{1, 0}, {-1, 0},
				{0, 1}, {0, -1},
				{1, 1}, {1, -1},
				{-1, 1}, {-1, -1}
		};
		
		for (int[] direction : directions) {
			int rank = direction[0];
			int file = direction[1];
			
			int currentRank = whiteQueen.getRank();
			int currentFile = whiteQueen.getFile();
			
			while(true) {
				currentRank += rank;
				currentFile += file;
				
				if (currentRank < 1 || currentRank > 8 || currentFile < 0 || currentFile > 7) break;
				
				String sq = "" + (char) ('a' + currentFile) + (char) ('0' + currentRank);
				Square square = new Square(sq);
				
				if (square.equals(blackKing) || square.equals(whiteKing)) break;
				
				squares.add(square);
 			}
		}
		
		return squares;
	}
}

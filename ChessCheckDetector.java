

class ChessCheckDetector {
	
	public boolean isValid(Position whiteQueen, Position whiteKing, Position blackKing) {
		
		boolean isSameCell = (whiteQueen.getRow() == blackKing.getRow() && whiteQueen.getColumn() == blackKing.getColumn()) ||
				(whiteKing.getRow() == blackKing.getRow() && whiteKing.getColumn() == blackKing.getColumn()) ||
				(whiteQueen.getRow() == whiteKing.getRow() && whiteQueen.getColumn() == whiteKing.getColumn());
		
		boolean isAdjacent = Math.abs(whiteKing.getRow() - blackKing.getRow()) <= 1 &&
				Math.abs(whiteKing.getColumn() - blackKing.getColumn()) <= 1;
		
		return !isSameCell && !isAdjacent;
	}
	
	public boolean isBlackKingInCheck(Position whiteQueen, Position blackKing) {
		
		return whiteQueen.getRow() == blackKing.getRow() ||
				whiteQueen.getColumn() == blackKing.getColumn() ||
				Math.abs(whiteQueen.getRow() - blackKing.getRow()) == Math.abs(whiteQueen.getColumn() - blackKing.getColumn());
	}
}

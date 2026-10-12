package rookmate;

import chess.Square;

public class RookMate {
	
	private boolean isRookAttacking(Square rook, Square whiteKing, Square target) {
		if (rook.equals(target)) return false;
		
		if (rook.getRank() == target.getRank()) {
			boolean blocked = whiteKing.getRank() == rook.getRank() && isBetween(whiteKing.getFile(), rook.getFile(), target.getFile());
			//if rook is blocked by whiteKing, we can consider that rook isn't attacking
			return !blocked;
		}
		
		if (rook.getFile() == target.getFile()) {
			boolean blocked = whiteKing.getFile() == rook.getFile() && isBetween(whiteKing.getRank(), rook.getRank(), target.getRank());
			
			return !blocked;
		}
		
		return false;
	}
	
	private boolean isKingAttacking(Square whiteKing, Square target) {
		
		for (int rank = -1; rank <= 1; rank++) {
			for (int file = -1; file <= 1; file++) {
				if (rank == 0 && file == 0) continue;
				
				char column = (char)('a' + file + whiteKing.getFile());
				char row = (char)('0' + rank + whiteKing.getRank());
				String square = "" + column + row;
				
				if (!Square.isValidSquare(square)) {
					continue;
				}
				
				Square next = new Square(square);
				if (next.equals(target)) return true;
			}
		}
		
		return false;
	}
	
	private boolean isSquareAttacked(Square target, Square whiteKing, Square rook) {
		boolean isAttackedByWhiteKing = isKingAttacking(whiteKing, target);
		boolean isAttackedByRook = isRookAttacking(rook, whiteKing, target);
		
		return isAttackedByWhiteKing || isAttackedByRook;
	}
	
	private boolean hasLegalMove(Square blackKing, Square whiteKing, Square rook) {
		return !isSquareAttacked(blackKing, whiteKing, rook);
	}
	
	public boolean isCheckmate(Square whiteKing, Square rook, Square blackKing) {
		boolean isBlackKingInCheck = isRookAttacking(rook, whiteKing, blackKing) || isKingAttacking(whiteKing, blackKing);
		
		return !(!isBlackKingInCheck || hasLegalMove(blackKing, whiteKing, rook));
	}
	
	private boolean isBetween(int x, int a, int b) {
		return x > Math.min(a, b) && x < Math.max(a, b);
	}
	
	private boolean isAdjacent(Square a, Square b) {
		return Math.abs(a.getRank() - b.getRank()) <= 1
				&& Math.abs(a.getFile() - b.getFile()) <= 1;
	}
}

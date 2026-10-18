package rookmate;

import chess.Square;

import java.util.ArrayList;
import java.util.List;

public class RookMate {
	
	public boolean isCheckmate(Square whiteKing, Square rook, Square blackKing) {
		boolean isBlackKingInCheck = isRookAttacking(rook, whiteKing, blackKing) || isKingAttacking(whiteKing, blackKing);
		//blackKing in check and no legal move
		return isBlackKingInCheck && !hasLegalMove(blackKing, whiteKing, rook);
	}
	
	public List<Square> findRookMoves(Square whiteKing, Square blackKing) {
		
		List<Square> legalMoves = new ArrayList<>();
		
		for (int file = 0; file < 8; file++) {
			for (int rank = 1; rank <= 8; rank++) {
				Square rook = new Square("" + (char) ('a' + file) + rank);
				
				if (rook.equals(whiteKing) || rook.equals(blackKing)) continue;
				if (isCheckmate(whiteKing, rook, blackKing)) {
					legalMoves.add(rook);
				}
			}
		}
		
		return legalMoves;
	}
	
	private boolean isRookAttacking(Square rook, Square whiteKing, Square target) {
		if (rook.equals(target)) return false;

		if (rook.getRank() == target.getRank()) {
			boolean blocked = whiteKing.getRank() == rook.getRank()
					&& isBetween(whiteKing.getFile(), rook.getFile(), target.getFile());
			//if rook is blocked by whiteKing, we can consider that rook isn't attacking
			return !blocked;
		}

		if (rook.getFile() == target.getFile()) {
			boolean blocked = whiteKing.getFile() == rook.getFile()
					&& isBetween(whiteKing.getRank(), rook.getRank(), target.getRank());

			return !blocked;
		}

		return false;
	}

	//whiteKing
	private boolean isKingAttacking(Square whiteKing, Square target) {
		if (isAdjacent(target, whiteKing)) {
			throw new IllegalArgumentException("Illegal chess rules!");
		}
		
		if (target.getFile() == whiteKing.getFile()) {
			return Math.abs(target.getRank() - whiteKing.getRank()) == 2;
		}
		
		if (target.getRank() == whiteKing.getRank()) {
			return Math.abs(target.getFile() - whiteKing.getFile()) == 2;
		}

		return false;
	}

	private boolean hasLegalMove(Square blackKing, Square whiteKing, Square rook) {
		boolean isAttackedByWhiteKing = isKingAttacking(whiteKing, blackKing);
		boolean isAttackedByRook = isRookAttacking(rook, whiteKing, blackKing);
		
		if (isAttackedByRook && isAttackedByWhiteKing) return false;
		return isAttackedByRook || isAttackedByWhiteKing;
	}
	
	private boolean isBetween(int x, int a, int b) {
		return x > Math.min(a, b) && x < Math.max(a, b);
	}
	
	private boolean isAdjacent(Square a, Square b) {
		return Math.abs(a.getRank() - b.getRank()) <= 1
				&& Math.abs(a.getFile() - b.getFile()) <= 1;
	}
}

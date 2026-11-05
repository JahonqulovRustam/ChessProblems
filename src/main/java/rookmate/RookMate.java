package rookmate;

import chess.Square;

import java.util.*;

public class RookMate {
	public static List<Square> findCheckMatingRookSquares(Square whiteKing, Square blackKing) {
		List<Square> squares = new ArrayList<>();
		
		int whiteKingFile = whiteKing.getFile();
		int whiteKingRank = whiteKing.getRank();
		int blackKingFile = blackKing.getFile();
		int blackKingRank = blackKing.getRank();
		
		//Two kings can never be adjacent in chess
		if (Math.max(Math.abs(whiteKingFile - blackKingFile), Math.abs(whiteKingRank - blackKingRank)) <= 1) {
			throw new IllegalArgumentException("Two kings can never be adjacent in chess!");
		}
		
		//horizontal corner mates
		boolean hasHorizontalPair =
				(whiteKing.equals(new Square("b3")) && blackKing.equals(new Square("a1"))) ||
				(whiteKing.equals(new Square("b6")) && blackKing.equals(new Square("a8"))) ||
				(whiteKing.equals(new Square("g3")) && blackKing.equals(new Square("h1"))) ||
				(whiteKing.equals(new Square("g6")) && blackKing.equals(new Square("h8")));
		
		//vertical corner mates
		boolean hasVerticalPair =
				(whiteKing.equals(new Square("c7")) && blackKing.equals(new Square("a8"))) ||
				(whiteKing.equals(new Square("f7")) && blackKing.equals(new Square("h8"))) ||
				(whiteKing.equals(new Square("f2")) && blackKing.equals(new Square("h1"))) ||
				(whiteKing.equals(new Square("c2")) && blackKing.equals(new Square("a1")));
		
		//horizontal mate
		if (hasHorizontalPair || ((blackKingRank == 1 || blackKingRank == 8) && ((whiteKingFile == blackKingFile && Math.abs(whiteKingRank - blackKingRank) == 2)))) {
			for (int file = 0; file < 8; file++) {
				if (file == blackKingFile) continue;

				boolean attackedByBlackKing = Math.abs(file - blackKingFile) <= 1;
				if (!attackedByBlackKing) {
					squares.add(new Square("" + (char) ('a' + file) + blackKingRank));
				}
			}
		}

		//vertical mate
		if (hasVerticalPair || ((blackKingFile == 0 || blackKingFile == 7) && ((whiteKingRank == blackKingRank && Math.abs(whiteKingFile - blackKingFile) == 2)))) {
			for (int rank = 1; rank <= 8; rank++) {
				if (rank == blackKingRank) continue;

				boolean attackedByBlackKing = Math.abs(rank - blackKingRank) <= 1;
				if (!attackedByBlackKing) {
					squares.add(new Square("" + (char) ('a' + blackKingFile) + rank));
				}
			}
		}

		return squares;
	}
}

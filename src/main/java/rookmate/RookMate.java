package rookmate;

import chess.Square;

import java.util.*;

public class RookMate {
	public static List<Square> findCheckMatingRookSquares(Square whiteKing, Square blackKing) {
		HashMap<Square, Square> horizontalExceptions = new HashMap<>();
		//horizontal corner mates
		horizontalExceptions.put(new Square("b3"), new Square("a1"));
		horizontalExceptions.put(new Square("b6"), new Square("h8"));
		horizontalExceptions.put(new Square("g3"), new Square("h1"));
		horizontalExceptions.put(new Square("g6"), new Square("h8"));
		//vertical corner mates
		HashMap<Square, Square> verticalExceptions = new HashMap<>();
		verticalExceptions.put(new Square("c7"), new Square("a8"));
		verticalExceptions.put(new Square("f7"), new Square("h8"));
		verticalExceptions.put(new Square("f2"), new Square("h1"));
		verticalExceptions.put(new Square("c2"), new Square("a1"));
		
		List<Square> squares = new ArrayList<>();
		
		int whiteKingFile = whiteKing.getFile();
		int whiteKingRank = whiteKing.getRank();
		int blackKingFile = blackKing.getFile();
		int blackKingRank = blackKing.getRank();
		boolean hasHorizontalPair = Objects.equals(horizontalExceptions.get(whiteKing), blackKing);
		boolean hasVerticalPair = Objects.equals(verticalExceptions.get(whiteKing), blackKing);

		//Two kings can never be adjacent in chess
		if (Math.max(Math.abs(whiteKingFile - blackKingFile), Math.abs(whiteKingRank - blackKingRank)) <= 1) {
			throw new IllegalArgumentException("Two kings can never be adjacent in chess!");
		}

		//horizontal mate
		if ((blackKingRank == 1 || blackKingRank == 8) && ((whiteKingFile == blackKingFile && Math.abs(whiteKingRank - blackKingRank) == 2) || hasHorizontalPair)) {
			for (int file = 0; file < 8; file++) {
				if (file == blackKingFile) continue;

				boolean attackedByBlackKing = Math.abs(file - blackKingFile) <= 1;
				if (!attackedByBlackKing) {
					squares.add(new Square("" + (char) ('a' + file) + blackKingRank));
				}
			}
		}

		//vertical mate
		if ((blackKingFile == 0 || blackKingFile == 7) && ((whiteKingRank == blackKingRank && Math.abs(whiteKingFile - blackKingFile) == 2) || hasVerticalPair)) {
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

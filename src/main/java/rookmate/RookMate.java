package rookmate;

import chess.Square;

import java.util.ArrayList;
import java.util.List;

public class RookMate {

	public static List<Square> findCheckMatingRookSquares(Square whiteKing, Square blackKing) {
		List<Square> squares = new ArrayList<>();

		int whiteKingFile = whiteKing.getFile();
		int whiteKingRank = whiteKing.getRank();
		int blackKingFile = blackKing.getFile();
		int blackKingRank = blackKing.getRank();

		//Two kings can never be adjacent in chess
		if (Math.max(Math.abs(whiteKingFile - blackKingFile), Math.abs(whiteKingRank - blackKingRank)) <= 1) {
			return squares;
		}

		//horizontal mate
		if ((blackKingRank == 1 || blackKingRank == 8) && whiteKingFile == blackKingFile && Math.abs(whiteKingRank - blackKingRank) == 2) {
			for (int file = 0; file < 8; file++) {
				if (file == blackKingFile) continue;

				boolean attackedByBlackKing = Math.abs(file - blackKingFile) <= 1;
				boolean protectedByWhiteKing = Math.max(Math.abs(file - whiteKingFile), Math.abs(blackKingRank - whiteKingRank)) <= 1;
				if (!attackedByBlackKing || protectedByWhiteKing) {
					squares.add(new Square("" + (char) ('a' + file) + blackKingRank));
				}
			}
		}

		//vertical mate
		if ((blackKingFile == 0 || blackKingFile == 7) && whiteKingRank == blackKingRank && Math.abs(whiteKingFile - blackKingFile) == 2) {
			for (int rank = 1; rank <= 8; rank++) {
				if (rank == blackKingRank) continue;

				boolean attackedByBlackKing = Math.abs(rank - blackKingRank) <= 1;
				boolean protectedByWhiteKing = Math.max(Math.abs(blackKingFile - whiteKingFile), Math.abs(rank - whiteKingRank)) <= 1;
				if (!attackedByBlackKing || protectedByWhiteKing) {
					squares.add(new Square("" + (char) ('a' + blackKingFile) + rank));
				}
			}
		}

		return squares;
	}
}

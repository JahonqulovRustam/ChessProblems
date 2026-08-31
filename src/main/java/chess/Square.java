package chess;

public class Square {
	
	private final int rank;
	private final int file;
	
	public Square(String square) {
		if (!isValidSquare(square)) {
			throw new exceptions.InvalidSquareException("Invalid square: " + square);
		} else {
			rank = 8 - (square.charAt(1) - '0');
			file = Character.toLowerCase(square.charAt(0)) - 'a';
		}
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getFile() {
		return file;
	}
	
	public boolean isValidSquare(String square) {
		
		if (square == null || square.length() != 2) {
			return false;
		}
		
		char rank = square.charAt(1);
		char file = Character.toLowerCase(square.charAt(0));
		
		return file >= 'a' && file <= 'h' &&
				rank >= '1' && rank <= '8';
	}
	
	
	@Override
	public String toString() {
		char column = (char) ('a' + getFile());
		char row = (char) ('8' - getRank());
		
		return "" + column + row;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (!(obj instanceof Square)) {
			return false;
		}
		
		Square other = (Square) obj;
		
		return rank == other.rank && file == other.file;
	}
}

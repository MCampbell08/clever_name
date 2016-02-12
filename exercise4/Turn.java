package exercise4;

public enum Turn {
	LEFT(270), RIGHT(90);
	
	private int turn;
	
	Turn(int turn) {
		this.turn = turn;
	}
	
	public int getTurn() {
		return turn;
	}
}

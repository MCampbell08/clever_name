package exercise5;

public enum CarColor {
	BLUE("images\\topCarBlue.png"), PURPLE("images\\topCarPurple.png"), RED("images\\topCarRed.png"), YELLOW("images\\topCarYellow.png");
	
	private String color;
	
	CarColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
}

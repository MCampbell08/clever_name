package exercise1;

public enum Direction {
	NORTH(270, 180),
	SOUTH(90, 0),
	EAST(0, -90),
	WEST(180, 90);

	private int carRotation;
	private int lightRotation;

	private Direction(int carRotation, int lightRotation){
		this.carRotation = carRotation;
		this.lightRotation = lightRotation;
	}
	
	public int getCarRotation(){
		return carRotation;
	}
	public int getLightRotation(){
		return lightRotation;
	}
	public boolean isHorizontal(){
		return this == EAST || this == WEST;
	}
	
}


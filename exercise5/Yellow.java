package exercise5;

public class Yellow extends Wrappable {

	public Yellow(Direction direction) {
		super(direction);
		this.setImage(CarColor.YELLOW.getColor());
	}
	
	public void act() {
		super.act();
	}

	@Override
	public void turn() {
		int randTurn = rand.nextInt(4);
		assert (speed == SLOW_SPEED && intersection.isRed(direction));
		if (randTurn == 0 && isTurning == false) {
			move(RIGHT_TURN_LENGTH);
			this.turn(Turn.RIGHT.getTurn());
			move(speed);
			if (direction == Direction.NORTH) {
				direction = Direction.EAST;
			} else if (direction == Direction.EAST) {
				direction = Direction.SOUTH;
			} else if (direction == Direction.SOUTH) {
				direction = Direction.WEST;
			} else if (direction == Direction.WEST) {
				direction = Direction.NORTH;
			}
		}
	}
	@Override
	public void getCarColor(Car c) {
		this.getImage();
	}
}

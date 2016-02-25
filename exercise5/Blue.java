package exercise5;

public class Blue extends Car {
	
	private int randTurn = 0;

	public Blue(Direction direction) {
		super(direction);
		this.setImage(CarColor.BLUE.getColor());
	}

	public void act() {
		super.act();
		if (!dead) {
			if (this.isAtEdge()) {
				getWorld().removeObject(this);
			}
		}
	}

	public void turn() {
		if (randTurn == 0) {
			move(LEFT_TURN_LENGTH);
			this.turn(Turn.LEFT.getTurn());
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
			randTurn++;
		} else if (randTurn == 1) {
			move(RIGHT_TURN_LENGTH);
			this.turn(Turn.RIGHT.getTurn());
			move(speed);
			if (direction == Direction.NORTH) {
				direction = Direction.WEST;
			} else if (direction == Direction.WEST) {
				direction = Direction.SOUTH;
			} else if (direction == Direction.SOUTH) {
				direction = Direction.EAST;
			} else if (direction == Direction.EAST) {
				direction = Direction.NORTH;
			}
			randTurn--;
		}
	}

	@Override
	public void getCarColor(Car c) {
		c.getImage();
	}
}

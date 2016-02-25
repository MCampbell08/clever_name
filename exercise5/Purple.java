package exercise5;

public class Purple extends Car {

	public Purple(Direction direction) {
		super(direction);
		this.setImage(CarColor.PURPLE.getColor());
	}
	
	public void act(){
		super.act();
		if (!dead) {
			if (this.isAtEdge()) {
				getWorld().removeObject(this);
			}
		}
	}

	@Override
	public void turn() {
		int randTurn = rand.nextInt(4);
		assert(speed == SLOW_SPEED && intersection.isRed(direction));
		if(randTurn == 0 && isTurning == false) {
			move(LEFT_TURN_LENGTH);
			this.turn(Turn.LEFT.getTurn());
			move(speed);
			if(direction == Direction.NORTH) {
				direction = Direction.WEST;
			}
			else if(direction == Direction.WEST) {
				direction = Direction.SOUTH;
			}
			else if(direction == Direction.SOUTH) {
				direction = Direction.EAST;
			}
			else if(direction == Direction.EAST) {
				direction = Direction.NORTH;
			}		
		}
	}
	@Override
	public void getCarColor(Car c) {
		c.getImage();
	}
}

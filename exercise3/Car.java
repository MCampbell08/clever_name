package exercise3;

import java.util.Random;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener{
	private String[] COLORS = {"images\\topCarBlue.png", "images\\topCarPurple.png", "images\\topCarRed.png", "images\\topCarYellow.png"};
	private static final int NUM_OF_CARS = 4;
	Random rand = new Random();
	private State state = State.OUTSIDE;
	private Intersection intersection;
	private static final int FULL_SPEED = 4;
	private static final int SLOW_SPEED = 1;
	private int speed = 4;
	private Direction direction;
	private Car secondCar;

	private enum State{
		OUTSIDE, APPROACHING, INSIDE;
	}
	
	private enum Turn {
		LEFT(270), RIGHT(90);
		
		private int turn;
		
		Turn(int turn) {
			this.turn = turn;
		}
		
		public int getTurn() {
			return turn;
		}
		
		
	}

	public Car(Direction direction){
		int x = rand.nextInt(NUM_OF_CARS);
		GreenfootImage image = new GreenfootImage(COLORS[x]);
		this.setImage(image);
		this.setRotation(direction.getCarRotation());
		this.direction = direction;	
	}

	public void act(){
		move(speed);
		if(isAtEdge()){
			assert(this.isAtEdge());
			getWorld().removeObject(this);
			return;
		}
		switch(state){
		case OUTSIDE:
			if(speed < FULL_SPEED){
			speed++;
			}
			break;
		case APPROACHING:
			if(intersection.isGreen(direction)){
				if(speed < FULL_SPEED){
					speed++;
				}
			}else{
				if(speed > SLOW_SPEED){
					speed--;
				}
			}
			break;
		case INSIDE:
			if(intersection.isRed(direction) && speed < FULL_SPEED){
				speed = 0;
				turn();
			}else{
				speed = FULL_SPEED;
			}
			break;
		}
		explosion();
	}
	
	public void explosion() {
		try {
			if(this.isTouching(Car.class)) {
				throw new Exception("Collision!");
			}
		}catch(Exception e){
			secondCar = (Car) getOneIntersectingObject(Car.class);
			TrafficWorld world = (TrafficWorld)getWorld();
			world.explosion(0, this.getX(), this.getY());
			getWorld().removeObject(secondCar);
			getWorld().removeObject(this);
			//return;
		}
	}
	
	public void turn() {
		int randTurn = rand.nextInt(3);
		assert(speed == SLOW_SPEED && intersection.isRed(direction));
		switch(randTurn) {
		case 1:
			move(55);
			this.turn(Turn.LEFT.getTurn());
			move(speed);
			if(direction == Direction.NORTH) {
				direction = Direction.EAST;
			}
			else if(direction == Direction.EAST) {
				direction = Direction.SOUTH;
			}
			else if(direction == Direction.SOUTH) {
				direction = Direction.WEST;
			}
			else if(direction == Direction.WEST) {
				direction = Direction.NORTH;
			}
			break;
		case 2:
			move(30);
			this.turn(Turn.RIGHT.getTurn());
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
	public void isApproaching(Intersection i) {
		state = State.APPROACHING;
		intersection = i;
	}
	@Override
	public void isIn(Intersection i) {
		state = State.INSIDE;
		intersection = i;
	}
	@Override
	public void isLeaving(Intersection i) {
		state = State.OUTSIDE;
		intersection = i;
	}

}

package exercise5;

import java.util.Random;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public abstract class Car extends Actor implements Listener {
	protected String[] Colors = { "images\\topCarBlue.png", "images\\topCarPurple.png", "images\\topCarRed.png",
	"images\\topCarYellow.png" };
	private static final int NUM_OF_CARS = 4;
	protected static final int LEFT_TURN_LENGTH = 55;
	protected static final int RIGHT_TURN_LENGTH = 30;
	Random rand = new Random();
	private State state = State.OUTSIDE;
	protected Intersection intersection;
	protected static final int FULL_SPEED = 4;
	protected static final int SLOW_SPEED = 1;
	protected int speed = 4;
	protected boolean dead = false;
	protected boolean isTurning = false;
	public Direction direction;
	private Car secondCar;
	private static Data data = new Data();
	private boolean left = false;

	private enum State {
		OUTSIDE, APPROACHING, INSIDE;
	}

	public Car(Direction direction) {
		int x = rand.nextInt(NUM_OF_CARS);
		GreenfootImage image = new GreenfootImage(Colors[x]);
		this.setImage(image);
		this.setRotation(direction.getCarRotation());
		this.direction = direction;

	}

	public void act() {

		move(speed);
		switch (state) {
		case OUTSIDE:
			if (speed < FULL_SPEED) {
				speed++;
			}
			break;
		case APPROACHING:
			if (intersection.isGreen(direction)) {
				if (speed < FULL_SPEED) {
					speed++;
					isTurning = false;
				}
			} else {
				if (speed > SLOW_SPEED) {
					speed--;
				}
			}
			left = false;
			break;
		case INSIDE:
			if (intersection.isRed(direction) && speed < FULL_SPEED) {
				speed = 0;
				turn();
				isTurning = true;
			} else {
				speed = FULL_SPEED;
			}
			if(!left && !dead) {
				data.addCars(this);
				left = true;
			}
			break;
		}
		explosion();
		getCarColor(this);
	}

	public void explosion() {
		try {
			if (this.isTouching(Car.class)) {
				throw new Exception("Collision!");
			}
		} catch (Exception e) {
			dead = true;
			secondCar = (Car) getOneIntersectingObject(Car.class);
			TrafficWorld world = (TrafficWorld) getWorld();
			world.explosion(0, this.getX(), this.getY());
			getWorld().removeObject(secondCar);
			getWorld().removeObject(this);
		}
	}

	public abstract void turn();

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
	public boolean isIntersection(Intersection i, Car c) {
		
		return false;
	}
}

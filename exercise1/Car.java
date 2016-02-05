package exercise1;

import java.util.Random;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor{
	private String[] CAR_COLORS = {"images\\topCarBlue.png", "images\\topCarPurple.png", "images\\topCarRed.png", "images\\topCarYellow.png"};
	private static final int NUM_OF_CARS = 4;
	Random rand = new Random();
	private State state = State.OUTSIDE;
	private static final int FULL_SPEED = 4;
	private static final int SLOW_SPEED = 1;
	private int speed = 4;
	private Direction direction;

	private enum State{
		OUTSIDE, APPROACHING, INSIDE;
	}

	public Car(Direction direction){
		int z = rand.nextInt(NUM_OF_CARS);
		GreenfootImage image = new GreenfootImage(CAR_COLORS[z]);
		this.setImage(image);
		this.setRotation(direction.getCarRotation());
		this.direction = direction;	
	}

	public void act(){
		move(speed);
		if(this.getRotation() == Direction.EAST.getCarRotation() && isAtEdge()){
			setLocation(0, getY());
		}else if(this.getRotation() == Direction.WEST.getCarRotation() && isAtEdge()){
			setLocation(TrafficWorld.WIDTH, getY());
		}else if(this.getRotation() == Direction.SOUTH.getCarRotation() && isAtEdge()){
			setLocation(getX(), 0);
		}else if(this.getRotation() == Direction.NORTH.getCarRotation() && isAtEdge()){
			setLocation(getX(), TrafficWorld.HEIGHT);
		}
	}
}

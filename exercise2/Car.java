package exercise2;

import java.util.Random;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener{
	private String[] CAR_COLORS = {"images\\topCarBlue.png", "images\\topCarPurple.png", "images\\topCarRed.png", "images\\topCarYellow.png"};
	private static final int NUM_OF_CARS = 4;
	Random rand = new Random();
	private State state = State.OUTSIDE;
	private Intersection curIntersection;
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
		switch(state){
		case OUTSIDE:
			fullSpeed();
			break;
		case APPROACHING:
			if(curIntersection.isGreen(direction)){
				fullSpeed();
			}else{
				slowSpeed();
			}
			break;
		case INSIDE:
			if(curIntersection.isRed(direction) && speed < FULL_SPEED){
				speed = 0;
			}else{
				speed = FULL_SPEED;
			}
			break;
		}
	}
	private void slowSpeed() {
		if(speed > SLOW_SPEED){
			speed--;
		}
	}
	public void fullSpeed(){
		if(speed < FULL_SPEED){
			speed++;
		
		}
	}
	@Override
	public void isApproaching(Intersection i) {
		state = State.APPROACHING;
		curIntersection = i;
	}
	@Override
	public void isIn(Intersection i) {
		state = State.INSIDE;
		curIntersection = i;
	}
	@Override
	public void isLeaving(Intersection i) {
		state = State.OUTSIDE;
		curIntersection = i;
	}

}

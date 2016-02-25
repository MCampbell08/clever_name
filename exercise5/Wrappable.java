package exercise5;

import greenfoot.GreenfootImage;

public abstract class Wrappable extends Car {

	public Wrappable(Direction direction) {
		super(direction);
	}

	protected GreenfootImage image;

	
	
	public void act() {
		super.act();
		if (!dead) {
			if (this.isAtEdge()) {
				atEdge();
			}
		}
	}

	public void atEdge() {
		if(this.getRotation() == Direction.EAST.getCarRotation()) {
			setLocation(0, getY());
		}
		else if(this.getRotation() == Direction.WEST.getCarRotation()) {
			setLocation(TrafficWorld.WIDTH, getY());
		}
		else if(this.getRotation() == Direction.SOUTH.getCarRotation()) {
			setLocation(getX(), 0);
		}
		else if(this.getRotation() == Direction.NORTH.getCarRotation()) {
			setLocation(getX(), TrafficWorld.HEIGHT);
		}
	}

}

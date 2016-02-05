package exercise3;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Explosion extends Actor {
	
	private int state;
	
	public Explosion(int state) {
		this.state = state;
		GreenfootImage image = new GreenfootImage("images\\explosion1.png");
		setImage(image);
	}
	public void act() {
		if(state == 0) {
			setImage("images\\explosion1.png");
			state = 1;
		}
		else if(state == 1) {
			setImage("images\\explosion2.png");
			state = 2;
		}
		else if(state == 2) {
			setImage("images\\explosion3.png");
		}
	}
	
}

package exercise1;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Roads extends Actor {
	
	public Roads(int w, int h){
		GreenfootImage image = new GreenfootImage(w, h);
		this.setImage(image);
		image.setColor(Color.GRAY);
		image.fill();
	}
}

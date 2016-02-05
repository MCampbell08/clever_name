package exercise2;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class TrafficLight extends Actor{
	public static enum Color{
		GREEN, YELLOW, RED;
		}
	private Color color;
	public String[] colorImages = {"images/trafficLightGreen.png","images/trafficLightYellow.png","images/trafficLightRed.png"};
	
	public TrafficLight(Color initialColor){
		GreenfootImage image = new GreenfootImage(colorImages[initialColor.ordinal()]);
		setImage(image);
		color = initialColor;
	}
	public void setColor(Color color){
		setImage(colorImages[color.ordinal()]);
		this.color = color;
	}
	public boolean isGreen(){
		return color == Color.GREEN;
	}
	public boolean isRed(){
		return color == Color.RED;
	}
}

package exercise3;

import java.util.ArrayList;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Intersection extends Actor{
	private int nSLightCounter;
	private int eWLightCounter;
	private static final int GREEN_COUNT = 150;
	private static final int YELLOW_COUNT = 50;
	private static final int RED_COUNT = GREEN_COUNT + YELLOW_COUNT;
	private TrafficLight nTL;
	private TrafficLight sTL;
	private TrafficLight eTL;
	private TrafficLight wTL;
	private TrafficLight.Color verticalColor;
	private TrafficLight.Color horizontalColor;
	private ArrayList<TrafficLight> lights = new ArrayList<TrafficLight>();
	private ArrayList<Car> prev = new ArrayList<Car>();
	private ArrayList<Car> current = new ArrayList<Car>();

	public Intersection(){
		GreenfootImage intersection = new GreenfootImage(TrafficWorld.ROAD_LENGTH, TrafficWorld.ROAD_LENGTH);
		setImage(intersection);
	}
	public void addNSLights(int nRotation, int sRotation){
		verticalColor = TrafficLight.Color.GREEN;
		sTL = new TrafficLight(verticalColor);
		getWorld().addObject(sTL, this.getX(), this.getY() + TrafficWorld.ROAD_LENGTH/2 + sTL.getImage().getHeight()/2);
		sTL.setRotation(sRotation);
		nTL = new TrafficLight(verticalColor);
		getWorld().addObject(nTL, this.getX(), this.getY() - TrafficWorld.ROAD_LENGTH/2 - nTL.getImage().getHeight()/2);
		nTL.setRotation(nRotation);
		lights.add(sTL);
		lights.add(nTL);
	}
	public void addEWLights(int eRotation, int wRotation){
		horizontalColor = TrafficLight.Color.RED;
		eTL = new TrafficLight(horizontalColor);
		getWorld().addObject(eTL, this.getX() + TrafficWorld.ROAD_LENGTH/2 + eTL.getImage().getHeight()/2, this.getY());
		eTL.setRotation(eRotation);
		wTL = new TrafficLight(horizontalColor);
		getWorld().addObject(wTL, this.getX() - TrafficWorld.ROAD_LENGTH/2 - wTL.getImage().getHeight()/2, this.getY());
		wTL.setRotation(wRotation);
		lights.add(eTL);
		lights.add(wTL);
	}
	public void notifyApproachingCars(){
		current = (ArrayList<Car>) getObjectsInRange(TrafficWorld.ROAD_LENGTH, Car.class);
		for(Car c: current){
			if(!prev.contains(c)){
				c.isApproaching(this);
			}
		}
		prev = current;
	}
	public void notifyInCars(){
		current = (ArrayList<Car>) getIntersectingObjects(Car.class);
		for(Car c: current){
			c.isIn(this);
		}
		prev = current;
	}
	public void notifyLeavingCars(){
		current = (ArrayList<Car>) getObjectsInRange(TrafficWorld.ROAD_LENGTH/2, Car.class);
		for(Car c: current){
			if(prev.contains(c)){
				c.isLeaving(this);
			}
		}
		prev = current;
	}
	public boolean isGreen(Direction d){
		return (d.isHorizontal() && eTL.isGreen()) || !d.isHorizontal() && nTL.isGreen();
	}
	public boolean isRed(Direction d){
		return (d.isHorizontal() && eTL.isRed()) || !d.isHorizontal() && nTL.isRed();
	}
	public void act(){
		nSLightCounter++;
		eWLightCounter++;
		switch(verticalColor){
		case GREEN:
			if(nSLightCounter == GREEN_COUNT){
				verticalColor = TrafficLight.Color.YELLOW;
				nTL.setColor(verticalColor);
				sTL.setColor(verticalColor);
				nSLightCounter = 0;
			}
			break;
		case RED:
			if(nSLightCounter == RED_COUNT){
				verticalColor = TrafficLight.Color.GREEN;
				nTL.setColor(verticalColor);
				sTL.setColor(verticalColor);
				nSLightCounter = 0;
			}
			break;
		case YELLOW:
			if(nSLightCounter == YELLOW_COUNT){
				verticalColor = TrafficLight.Color.RED;
				nTL.setColor(verticalColor);
				sTL.setColor(verticalColor);
				nSLightCounter = 0;
			}
			break;
		}
		switch(horizontalColor){
		case GREEN:
			if(eWLightCounter == GREEN_COUNT){
				horizontalColor = TrafficLight.Color.YELLOW;
				eTL.setColor(horizontalColor);
				wTL.setColor(horizontalColor);
				eWLightCounter = 0;
			}
			break;
		case RED:
			if(eWLightCounter == RED_COUNT){
				horizontalColor = TrafficLight.Color.GREEN;
				eTL.setColor(horizontalColor);
				wTL.setColor(horizontalColor);
				eWLightCounter = 0;
			}
			break;
		case YELLOW:
			if(eWLightCounter == YELLOW_COUNT){
				horizontalColor = TrafficLight.Color.RED;
				eTL.setColor(horizontalColor);
				wTL.setColor(horizontalColor);
				eWLightCounter = 0;
			}
			break;
		}
		notifyApproachingCars();
		notifyInCars();
		notifyLeavingCars();
	}
}

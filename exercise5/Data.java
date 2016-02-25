package exercise5;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
	
	private HashMap <Object, Double> cars = new HashMap<Object, Double>();
	private static HashMap <Object, Double> timer = new HashMap<Object, Double>();
	private double average;
	private static double secs = 0;
	private static double secsAmount = 0;
	private static final int INTERSECTIONS = 35;
	private static double number = 1;
	
	public Data() {
		cars.put(Blue.class, 0.0);
		cars.put(Purple.class, 0.0);
		cars.put(Red.class, 0.0);
		cars.put(Yellow.class, 0.0);
		timer.put(Car.class, 0.0);
	}
	
	public double carsInIntersection(ArrayList<Intersection> inters) {
		
		for(int i = 0; i < inters.size(); i++) {
			if(!this.cars.containsKey(inters.get(i).hashCode())) {
				this.cars.put(inters.get(i), number);
				inters.get(i).setName(i);
			}
			else {
				number = this.cars.get(inters.get(i));
				number += 1;
				this.cars.put(inters.get(i), number);
				inters.get(i).setName(i);
			}
		}
		return number;
	}
	
	public void addCars(Car car) {
		double d = cars.get(car.getClass().asSubclass(Car.class));
		d += 1 ;
		cars.put(car.getClass().asSubclass(Car.class), d);
	}
	
	public double average() {
		average = ((cars.get(Blue.class) + cars.get(Purple.class) + cars.get(Red.class) + cars.get(Yellow.class)) / 4);
		return average;
	}
	
	public void time(Car c, double time) {
		timer.put(c, time);
		secsAmount += time;
		secs = (secsAmount / 1000) /  timer.size() / INTERSECTIONS;
	}
	
	public void print() {
		System.out.println("\nBlue: " + cars.get(Blue.class)
		 + "\nPurple: " + cars.get(Purple.class)
		 + "\nRed: " + cars.get(Red.class)
		 + "\nYellow: " + cars.get(Yellow.class)
		 + "\nAverage: " + average()
		 + "\nArrival Time: " + secs);
		
		for (int i = 0; i < INTERSECTIONS; i++) {
			System.out.println("Intersection " + i +" Amount: " +  TrafficWorld.intersection.get(i).setName(number));
		}
	}
}

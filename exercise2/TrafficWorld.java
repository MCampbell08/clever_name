package exercise2;

import java.awt.Color;
import java.util.Random;

import greenfoot.GreenfootImage;
import greenfoot.World;

public class TrafficWorld extends World {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	public static final int ROAD_LENGTH = 50;
	private static final int CELL_SIZE = 1;
	private static final int HORIZONTAL = 5;
	private static final int HORI_GAP = (HEIGHT - (HORIZONTAL * ROAD_LENGTH)) / (HORIZONTAL - 1);
	private static final int VERTICAL = 7;
	private static final int VERT_GAP = (WIDTH - (VERTICAL * ROAD_LENGTH)) / (VERTICAL - 1);
	private static int[] yLOC = new int[HORIZONTAL];
	private static final int CAR_POSITION = 10;
	private static int[] xLOC = new int[VERTICAL];
	static Random rand = new Random();

	public TrafficWorld() {
		super(WIDTH, HEIGHT, CELL_SIZE);
		GreenfootImage background = this.getBackground();
		background.setColor(Color.GREEN);
		background.fill();
		
		setup();
	}
	public void setup() {
		placeHRoads();
		placeVRoads();
		addHorizontalCars();
		addVerticalCars();
		addIntersections();
	}

	public void placeVRoads() {
		for (int z = 0; z < VERTICAL; z++) {
			Roads road = new Roads(ROAD_LENGTH, HEIGHT);
			int roadsX = (VERT_GAP + ROAD_LENGTH) * z + ROAD_LENGTH / 2;
			addObject(road, roadsX, HEIGHT / 2);
			xLOC[z] = roadsX;
		}
	}

	public void placeHRoads() {
		for (int z = 0; z < HORIZONTAL; z++) {
			Roads road = new Roads(WIDTH, ROAD_LENGTH);
			int roadsY = (HORI_GAP + ROAD_LENGTH) * z + ROAD_LENGTH / 2;
			addObject(road, WIDTH / 2, roadsY);
			yLOC[z] = roadsY;
		}
	}
	
	public void addIntersections() {
		for (int z = 0; z < HORIZONTAL; z++) {
			for (int k = 0; k < VERTICAL; k++) {
				Intersection intersection = new Intersection();
				addObject(intersection, xLOC[k], yLOC[z]);
				intersection.addNSLights(Direction.NORTH.getLightRotation(), Direction.SOUTH.getLightRotation());
				intersection.addEWLights(Direction.EAST.getLightRotation(), Direction.WEST.getLightRotation());
			}
		}
	}

	public void addCars(Direction rotation, int x, int y) {
		Car car = new Car(rotation);
		addObject(car, x, y);
	}

	public void addHorizontalCars() {
		for (int z = 0; z < HORIZONTAL; z++) {
			addCars(Direction.EAST, rand.nextInt(WIDTH), yLOC[z] + CAR_POSITION);
			addCars(Direction.WEST, rand.nextInt(WIDTH), yLOC[z] - CAR_POSITION);
		}
	}

	public void addVerticalCars() {
		for (int z = 0; z < VERTICAL; z++) {
			addCars(Direction.SOUTH, xLOC[z] - CAR_POSITION, rand.nextInt(HEIGHT));
			addCars(Direction.NORTH, xLOC[z] + CAR_POSITION, rand.nextInt(HEIGHT));
		}
	}
}
package exercise5;

public class Red extends Wrappable {

	public Red(Direction direction) {
		super(direction);
		this.setImage(CarColor.RED.getColor());
	}
	
	public void act() {
		super.act();
	}

	@Override
	public void turn() {

	}
	@Override
	public void getCarColor(Car c) {
		c.getImage();
	}
}

package exercise4;

public class Red extends Wrappable {

	public Red(Direction direction) {
		super(direction);
		this.setImage(this.Colors[2]);
	}
	
	public void act() {
		super.act();
	}

	@Override
	public void turn() {

	}

}

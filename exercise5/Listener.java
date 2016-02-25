package exercise5;
public interface Listener {
	
	public void isApproaching(Intersection i);
	
	public void isIn(Intersection i);

	public void isLeaving(Intersection i);
	
	public void getCarColor(Car c);
}

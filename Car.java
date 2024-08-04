package auto_driving_car;

public class Car {
	
	String name;
	String direction;
	String rides;
	int xPoint;
	int yPoint;
	boolean isCollides;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getRides() {
		return rides;
	}
	public void setRides(String rides) {
		this.rides = rides;
	}
	public int getxPoint() {
		return xPoint;
	}
	public void setxPoint(int xPoint) {
		this.xPoint = xPoint;
	}
	public int getyPoint() {
		return yPoint;
	}
	public void setyPoint(int yPoint) {
		this.yPoint = yPoint;
	}
	public boolean isCollides() {
		return isCollides;
	}
	public void setCollides(boolean isCollides) {
		this.isCollides = isCollides;
	}
	
}

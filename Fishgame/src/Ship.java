import org.jsfml.graphics.CircleShape;

public class Ship {
	String name;
	int num, xPos, yPos, deltaX, deltaY;
	CircleShape c;
	boolean selected;

	public Ship(String n, int shipNum) {
		name = n;
		num = shipNum;
		xPos = 500;
		yPos = 500;
		c = new CircleShape();
		c.setRadius(10);
		c.setPosition(Vars.ORIGIN);
		c.setFillColor(Vars.SHIPCOLORS[shipNum]);

	}

	public void update(int xAxis, int yAxis, int shipSelected) {

		if (shipSelected == num) {
			xPos = xPos + xAxis * Vars.SHIPVELOCITY;
			yPos = yPos + yAxis * Vars.SHIPVELOCITY;

			deltaX = xAxis * Vars.SHIPVELOCITY;
			deltaY = yAxis * Vars.SHIPVELOCITY;

			c.setPosition(xPos, yPos);
		}

		if (shipSelected != num) {
			xPos = xPos + deltaX;
			yPos = yPos + deltaY;

			c.setPosition(xPos, yPos);

		}
	}

	public boolean checkInside() {
		if (((xPos - 500) ^ 2) + ((yPos - 500) ^ 2) >= 610) {
			return false;
		}
		if (((xPos - 500) ^ 2) + ((500 - yPos) ^ 2) >= 610) {
			return false;
		}
		if (((500 - xPos) ^ 2) + ((yPos - 500) ^ 2) >= 610) {
			return false;
		}
		if (((500 - xPos) ^ 2) + ((500 - yPos) ^ 2) >= 610) {
			return false;
		} else {
			return true;
		}
	}
}

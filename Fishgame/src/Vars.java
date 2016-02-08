import java.util.HashMap;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

public class Vars {
	static final Color[] SHIPCOLORS = new Color[] { Color.RED, new Color(255, 92, 0), Color.YELLOW, Color.GREEN,
			Color.BLUE, Color.MAGENTA, Color.CYAN, new Color(150, 0, 205), Color.WHITE };
	static HashMap<Integer, Ship> ships = new HashMap<Integer, Ship>();
	static final Vector2f ORIGIN = new Vector2f(500, 500);
	static final int SHIPVELOCITY = 5;

}

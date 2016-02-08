
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class Game {

	public void run() {
		RenderWindow window = new RenderWindow();
		window.create(new VideoMode(1000, 1000), "Fishes");
		boolean lost = false;
		int xAxis = 0, yAxis = 0;
		int shipSelected = 0;
		int numOfShips = 1;
		long timeStart, timeNow = 0;
		// long deltaTime = 0;
		CircleShape rim = new CircleShape(500, 500);
		rim.setFillColor(Color.BLACK);
		rim.setOutlineColor(Color.RED);
		rim.setOutlineThickness(50);
		rim.setRadius(500);
		Font ff = new Font();
		try {
			ff.loadFromFile(Paths.get("arial.ttf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Text lose = new Text("You lose close the game", ff);
		// Limit the framerate
		Vars.ships.put(0, new Ship("asd", 0));
		window.setFramerateLimit(30);
		timeStart = System.currentTimeMillis();

		// Main loop
		while (window.isOpen()) {
			if (!lost) {
				if (timeNow != 0) {
					// deltaTime = System.currentTimeMillis() - timeNow;
				}
				timeNow = System.currentTimeMillis() - timeStart;

				window.clear(Color.BLACK);

				for (int i : Vars.ships.keySet()) {
					if (!Vars.ships.get(i).checkInside()) {
						lost = true;

					}
				}
				Font f = new Font();
				try {
					f.loadFromFile(Paths.get("arial.ttf"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Text t = new Text(String.valueOf(timeNow / 10), f);
				window.draw(t);
				window.draw(rim);

				for (int i : Vars.ships.keySet()) {
					window.draw(Vars.ships.get(i).c);
				}

				window.display();

				// Handle events
				for (Event event : window.pollEvents()) {
					if (event.type == Event.Type.CLOSED) {
						// The user pressed the close button
						window.close(); // 6
					}
					if (event.type == Event.Type.KEY_PRESSED) {
						KeyEvent k = event.asKeyEvent();

						if (k.key.toString().length() == 4) {
							if (Vars.ships.containsKey((int) k.key.toString().charAt(3) - 48)) {
								shipSelected = (int) k.key.toString().charAt(3) - 48;
								System.out.println((int) k.key.toString().charAt(3) - 48);
							}
						}

						if (k.key.toString() == "A") {
							xAxis = -1;
						}
						if (k.key.toString() == "D") {
							xAxis = 1;
						}
						if (k.key.toString() == "W") {
							yAxis = -1;
						}
						if (k.key.toString() == "S") {
							yAxis = 1;
						}

					}
				}

				for (int i : Vars.ships.keySet()) {
					Vars.ships.get(i).update(xAxis, yAxis, shipSelected);
				}

				if (timeNow / 10 >= 1000 * numOfShips && numOfShips < 9) {

					Vars.ships.put(numOfShips, new Ship("a", numOfShips));
					numOfShips++;
				}
			}
			if (lost) {
				window.clear(Color.BLACK);
				window.draw(lose);
				window.display();
				for (Event event : window.pollEvents()) {
					if (event.type == Event.Type.CLOSED) {

						window.close();
					}
				}
			}
		}
	}
}
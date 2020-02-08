import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	int speedx;
	int speedy;

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speedx = 10;

	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

	public void right() {

	}

	public void left() {

	}

	public void down() {

	}

	public void up() {

	}

	void update() {
		x += speedx;
		if (x >= LeagueInvaders.WIDTH) {
			x = LeagueInvaders.WIDTH;
		}
		x -= speedx;
		if (x <= 0) {
			x = 0;
		}
		y += speedy;
		if (y >= LeagueInvaders.HEIGHT) {
			y = LeagueInvaders.HEIGHT;
		}
		y -= speedy;
		if (y <= 0) {
			y = 0;
		}
	}
}

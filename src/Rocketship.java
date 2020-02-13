import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	int speed;
	

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed=10;

	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

	public void right() {
		x += speed;
		if (x >= LeagueInvaders.WIDTH) {
			x = LeagueInvaders.WIDTH;
		}
	}

	public void left() {
		x -= speed;
		if (x <= 0) {
			x = 0;
		}
	}

	public void down() {
		y += speed;
		if (y >= LeagueInvaders.HEIGHT) {
			y = LeagueInvaders.HEIGHT;
		}
	}

	public void up() {
		y -= speed;
		if (y <= 0) {
			y = 0;
		}
	}

	void update() {
	
	
		
	
	}
}

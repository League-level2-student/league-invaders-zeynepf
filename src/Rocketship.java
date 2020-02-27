import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	int speed;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed=10;
		if (needImage) {
		    loadImage ("rocket.png");
		}
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}



	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
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

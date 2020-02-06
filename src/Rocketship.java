import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	Rocketship(int x, int y, int width, int height){
		super(x,y,width,height);
		int speed = 10;
	    
	}
	void draw(Graphics g){
		 g.setColor(Color.BLUE);
	        g.fillRect(x, y, width, height);
	}
    public void right() {
        x+=speed;
      
    }
    public void left() {
        x-=speed;
        if(x<0) {
        	x=0;
        }
    }
    public void down() {
        y+=speed;
    }
    public void up() {
        y-=speed;
        if(y<0) {
        	y=0;
        }
    }
}

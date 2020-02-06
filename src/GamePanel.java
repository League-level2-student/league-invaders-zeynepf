import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    Rocketship rocket = new Rocketship(250, 700, 50, 50);
    Font titleFont;
    Font startFont;
    Font instructionsFont;
    Font GameOverFont;   
    Font killedFont;
    Font restartFont;
    int currentState = MENU;
    Timer frameDraw;
    void updateMenuState() {  }
    void updateGameState() {  }
    void updateEndState()  {  }
    void drawMenuState(Graphics g) {
    	g.setColor(Color.BLUE);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.PINK);
    	g.drawString("LEAGUE INVADERS", 25, 10);
    	g.setFont(startFont);
    	g.setColor(Color.PINK);
    	g.drawString("press ENTER to start", 110, 400);
    	g.setFont(instructionsFont);
    	g.setColor(Color.PINK);
    	g.drawString("press SPACE for instructions",50, 550 );
    	  
    }
    void drawGameState(Graphics g) { 
    	g.setColor(Color.BLACK);
    g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    rocket.draw(g);
    }
    void drawEndState(Graphics g)  { 
    	g.setColor(Color.RED);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(GameOverFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("GAME OVER", 100, 150);
    	g.setFont(killedFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("You killed enemies", 120, 400);
    	g.setFont(restartFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("press ENTER to restart", 100, 550);
    }
    GamePanel(){
    	  titleFont = new Font("Arial", Font.PLAIN, 48);
    	  startFont = new Font("Arial", Font.PLAIN, 30);
    	  instructionsFont = new Font("Arial", Font.PLAIN, 30);
    	  GameOverFont = new Font("Arial", Font.PLAIN, 50);
    	  killedFont = new Font("Arial", Font.PLAIN, 30);
    	  restartFont = new Font("Arial", Font.PLAIN, 30);
    	    frameDraw = new Timer(1000/60,this);
    	    frameDraw.start();
    }
    
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}
		else if(currentState == GAME){
		    drawGameState(g);
		}
		else if(currentState == END){
		    drawEndState(g);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
System.out.println("action");
repaint();
    	  
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		}   
		if (e.getKeyCode()==KeyEvent.VK_UP&&currentState == GAME) {
		    System.out.println("UP");
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN&&currentState == GAME) {
		    System.out.println("DOWN");
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT&&currentState == GAME) {
		    System.out.println("LEFT");
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT&&currentState == GAME) {
		    System.out.println("RIGHT");
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

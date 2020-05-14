import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int INSTRUCTION = 3;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	ObjectManager objectManager = new ObjectManager(rocket);
	Font titleFont;
	Font startFont;
	Font instructionsFont;
	Font GameOverFont;
	Font killedFont;
	Font restartFont;
	int currentState = MENU;
	Timer frameDraw;
	Timer alienSpawn;

	void startGame() {
		alienSpawn = new Timer(1000, objectManager);
		alienSpawn.start();
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

	void updateMenuState() {
	}

	void updateGameState() {
		objectManager.update();
		if(!rocket.isActive) {
			currentState= END;
		}
	}

	void updateEndState() {
	}
	void updateInstructionState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.PINK);
		g.drawString("LEAGUE INVADERS", 25, 100);
		g.setFont(startFont);
		g.setColor(Color.PINK);
		g.drawString("press ENTER to start", 110, 400);
		g.setFont(instructionsFont);
		g.setColor(Color.PINK);
		g.drawString("press SPACE for instructions", 50, 550);

	}
	void drawInstructionState(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Instructions", 125,100 );
		g.setFont(startFont);
		g.setColor(Color.BLACK);
	g.drawString("Use the arrowkeys to move.",25, 250);
	g.drawString("To shoot the aliens hit space.", 25, 300);
	g.drawString("Don't get hit by the aliens.",25,350);
	g.drawString("Press enter to play.",25,400);
	}


	void drawGameState(Graphics g) {
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		objectManager.draw(g);
		g.setColor(Color.YELLOW);
		g.setFont(killedFont);
		String score = "score="+objectManager.getScore();
		g.drawString(score, 40, LeagueInvaders.HEIGHT-100);
	}

	void drawEndState(Graphics g) {
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

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		startFont = new Font("Arial", Font.PLAIN, 30);
		instructionsFont = new Font("Arial", Font.PLAIN, 30);
		GameOverFont = new Font("Arial", Font.PLAIN, 50);
		killedFont = new Font("Arial", Font.PLAIN, 30);
		restartFont = new Font("Arial", Font.PLAIN, 30);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == INSTRUCTION) {
			drawInstructionState(g);}
		else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		}
		else if (currentState == INSTRUCTION) {
			updateInstructionState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		//System.out.println("action");
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
				rocket=new Rocketship(250, 700, 50, 50);
				objectManager= new ObjectManager(rocket);
			} 
			else if(currentState ==INSTRUCTION) {
				currentState = GAME;
				startGame();
			}
			else if (currentState == MENU) {
				currentState = GAME;
				startGame();
			} else if (currentState == GAME) {
				currentState = END;
				alienSpawn.stop();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && currentState == GAME) {
			objectManager.addProjectile(rocket.getProjectile());
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && currentState == MENU) {
			currentState = INSTRUCTION;
		}


		if (e.getKeyCode() == KeyEvent.VK_UP && currentState == GAME) {
			System.out.println("UP");
			if (rocket.y > 0) {
				rocket.up();

			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentState == GAME) {
			System.out.println("DOWN");

			if (rocket.y < LeagueInvaders.HEIGHT - 75) {
				rocket.down();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentState == GAME) {
			System.out.println("LEFT");

			if (rocket.x > 0) {
				rocket.left();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentState == GAME) {
			System.out.println("RIGHT");

			if (rocket.x < LeagueInvaders.WIDTH - 50) {
				rocket.right();
			}
		}
	}


		
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

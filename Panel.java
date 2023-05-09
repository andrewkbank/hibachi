import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
public class Panel extends JPanel {
	public static final int floor=500;
	private static Panel currentPanel;
	private static Frame frame;
	private Player[] players;
	private boolean tutorial;
	private boolean done=false;
	private boolean success=false;
	private int partOfTutorial=0;
	private static Stage stage = new Stage();
	
	public Panel(boolean setTutorial) {
		currentPanel = this;
		tutorial=setTutorial;
		setPreferredSize(new Dimension(958,709));
	}
	public Panel(Player[] setPlayers) {
		currentPanel = this;
		setPreferredSize(new Dimension(958,709));
		players=setPlayers;
		
	}
	public Panel (Panel oldFrame) {
		players = oldFrame.getPlayers();
		currentPanel = this;
		/*
		for(Player p : players)
			if(p.getInput().getClass() == HumanInput.class)
				( (HumanInput) p ).rebindInput();
		*/
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public static Panel getPanel() { return currentPanel; }
	public static void setFrame(Frame frame) { Panel.frame = frame; }
	public static Frame getFrame() { return frame; }
	public Player[] getPlayers() { return players;}
	public static Stage getStage() {return stage;}
	public void paintComponent(Graphics g) {
		drawBackground(g);
		for(Player p:players) {
			drawPlayer(g,p);
			/*
			for(Box b: p.hurtboxes()) {
				g.setColor(new Color(255,255,0,127));
				g.fillArc((int)b.getX(), (int)b.getY(), (int)b.getRadius(),(int) b.getRadius(), 0, 360);
				g.setColor(Color.black);
				//g.drawString("*", (int)b.getCenterX(), (int)b.getCenterY());
			}
			//draw the hitboxes
			if(p.attacking()&&p.getFrameData().activeFrames(p.getAction()).contains(p.attackFrame())) {
				for(HitBox h:p.hitboxes()) {
					g.setColor(new Color(255,0,0,127));
					g.fillArc((int)h.getX(), (int)h.getY(), (int)h.getRadius(),(int) h.getRadius(), 0, 360);
					//System.out.println("draw x: "+h.getX());
				}
			}*/
		}
		if(done) {
			endScreen(g);
		}else if(tutorial) {
			drawStuffForTutorial(g);
		}
	}
	private void drawBackground(Graphics g) {
		BufferedImage picture = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//background is 958 by 709
		InputStream pic = classLoader.getResourceAsStream("Background.png");
		try {
			picture=ImageIO.read(pic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(picture, 0, 0, null);
	}
	private void drawPlayer(Graphics g, Player p) {
		BufferedImage picture = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream pic = classLoader.getResourceAsStream(p.getStance());
		try {
			picture=ImageIO.read(pic);
			if(!p.facing() == Player.RIGHT) {
				//flips image if its facing left
				AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
				tx.translate(-picture.getWidth(null), 0);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
				picture = op.filter(picture, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			System.out.println("error: picture name invalid at "+p.getStance());
		}
		//g.drawImage(picture,200,200,null);
		int x=(int) (p.getX());
		int y=(int) (p.getY());
		if(!p.facing()) {
			//System.out.println(picture.getWidth(null));
			//try {
				x-=picture.getWidth(null)-144;
			//}catch(NullPointerException e) {
				//just cuz it was throwing errors unnecessarily
			//}
		}
		//int height=picture.getWidth();
		//int width=picture.getHeight();
		g.drawImage(picture,x,y,null);
		//g.drawImage(picture, x, y, x+width/2, y+height/2, 0, 0, width, height, null);
	}
	
	private void drawStuffForTutorial(Graphics g) {
		final int x=250;
		final int y=100;
		g.setColor(Color.blue);
		g.fill3DRect(x-5, y-55, 500, 100, true);
		g.setColor(Color.LIGHT_GRAY);
		g.fill3DRect(x, y-50, 500, 100,true);
		g.setColor(Color.black);
		g.setFont(new Font("Sans",Font.BOLD,30));
		if(partOfTutorial==0) {
			g.drawString("Use WASD to move", x+100, y);
		}else if(partOfTutorial==1) {
			g.drawString("Jump with the Space Bar", x+50, y);
		}else if(partOfTutorial==2) {
			g.setColor(Color.blue);
			g.fill3DRect(x-105, y-55, 700, 150, true);
			g.setColor(Color.LIGHT_GRAY);
			g.fill3DRect(x-100, y-50, 700, 150,true);
			g.setColor(Color.black);
			g.drawString("Use the U button for light attacks", x-90, y);
			g.setFont(new Font("Sans",Font.BOLD,18));
			g.drawString("Holding a different direction and being airborn changes the light attack", x-90, y+50);
		}else if(partOfTutorial==3) {
			g.setColor(Color.blue);
			g.fill3DRect(x-105, y-55, 700, 150, true);
			g.setColor(Color.LIGHT_GRAY);
			g.fill3DRect(x-100, y-50, 700, 150,true);
			g.setColor(Color.black);
			g.drawString("Use the I button for heavy attacks",x-90,y);
			g.setFont(new Font("Sans",Font.BOLD,18));
			g.drawString("It can be held, and the direction you face can be changed while charging", x-90, y+50);
		}else if(partOfTutorial==4) {
			g.setColor(Color.blue);
			g.fill3DRect(x-105, y-55, 720, 150, true);
			g.setColor(Color.LIGHT_GRAY);
			g.fill3DRect(x-100, y-50, 720, 150,true);
			g.setColor(Color.black);
			g.drawString("To score kills, knock the enemy hard enough", x-90, y);
			g.drawString("against the walls, floor, or ceiling", x-90, y+50);
		}else if(partOfTutorial==5) {
			g.setColor(Color.blue);
			g.fill3DRect(x-105, y-55, 700, 100, true);
			g.setColor(Color.LIGHT_GRAY);
			g.fill3DRect(x-100, y-50, 700, 100,true);
			g.setColor(Color.black);
			g.drawString("Don't worry, it won't hurt you (for now)",x-70,y);
		}
	}
	public void endScreen(Graphics g) {
		final int x=250;
		final int y=300;
		g.setColor(Color.black);
		g.fill3DRect(x-25, y-100, 500, 150, false);
		g.setColor(Color.red);
		g.setFont(new Font("Sans",Font.BOLD,100));
		if(success) {
			g.drawString("Success!", x, y);
		}else {
			g.drawString("Failure", x+50, y);
		}
	}
	public void nextPartOfTutorial() {
		partOfTutorial++;
	}
	public void done(boolean passed) {
		done=true;
		success=passed;
	}
}
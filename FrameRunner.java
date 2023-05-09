import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
public class FrameRunner {
	public static void main(String[] args) {
		WelcomeScreen screen=new WelcomeScreen();
		//Timer timer = new Timer();
		//TimerTask task = new RunFrame(1);
		
		//timer.schedule(task, 0,1000/60);
		//timer.schedule(task, 0,25);
		//timer.schedule(task, 0,500);
	}
}
class WelcomeScreen extends JPanel implements KeyListener{
	private JFrame frame=new JFrame();
	private int level=1;
	private boolean tutorial=true;
	public WelcomeScreen() {
		setPreferredSize(new Dimension(958,709));
		//window.add(this);
		setBorder(BorderFactory.createEmptyBorder(500,500,500,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.addKeyListener(this);
		frame.pack();
		frame.setVisible(true);
	}
	public void paintComponent(Graphics g) {
		super.setBackground(Color.LIGHT_GRAY);
		g.setColor(Color.red);
		g.setFont(new Font("Sans",Font.BOLD,60));
		g.drawString("Hibachi!", 350, 300);
		g.setColor(Color.black);
		g.setFont(new Font("Sans",Font.BOLD,30));
		if(!tutorial)
			g.drawString("Level "+level, 450, 400);
		g.drawString("Press the Space Bar to start", 250, 500);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()==' ') {
			//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			Timer timer = new Timer();
			
			int num=(level-1)%4+1;
			int cpuDifficulty=level/3;
			TimerTask task = new RunFrame(num,false,cpuDifficulty,this,tutorial);
			
			//timer.schedule(task, 0,1000/60);
			timer.schedule(task, 0,25);
			//timer.schedule(task, 0,500);
		}
		if(e.getKeyChar()==',') {
			//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			Timer timer = new Timer();
			
			int num=(level-1)%4+1;
			int cpuDifficulty=level/3;
			TimerTask task = new RunFrame(num,true,cpuDifficulty,this,tutorial);
			//timer.schedule(task, 0,1000/60);
			timer.schedule(task, 0,25);
			//timer.schedule(task, 0,500);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void levelUp() {
		//System.out.println("level up");
		if(!tutorial)
			level++;
		else
			tutorial=false;
		repaint();
	}
}
class RunFrame extends TimerTask{
	private Player ramenDude; 
	private Player makiDude;
	ArrayList<Player> players=new ArrayList<Player>();
	private JFrame window;
	private Panel panel;
	private int numOfEnemies;
	private int level=1;
	private WelcomeScreen welcomeScreen;
	private boolean tutorialOn;
	private int count=0;
	private boolean done=false;
	
	public RunFrame(int enemies,boolean playAsMaki,int levelOfEnemy,WelcomeScreen screen,boolean tutorial) {
		window = new JFrame();
		Panel.setFrame(window);
		panel = new Panel(tutorial);
		ramenDude = new Player("Ramen", "human",tutorial);
		makiDude=new Player("Maki","human",tutorial);
		numOfEnemies=enemies;
		welcomeScreen=screen;
		tutorialOn=tutorial;
		
		Player[] placeHolder=new Player[players.size()];
		if(playAsMaki) {
			players.add(makiDude);
		}else {
			players.add(ramenDude);
		}
		panel.setPlayers(players.toArray(placeHolder));
		for(int i=0;i<numOfEnemies;i++) {
			players.add(new Player("Maki","AI"+level,tutorial));
		}
		placeHolder=new Player[players.size()];
		panel.setPlayers(players.toArray(placeHolder));

		panel.setBorder(BorderFactory.createEmptyBorder(500,500,500,500));
		
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(panel);
		window.pack();
		window.setVisible(true);
	}
	public void run() {
		ArrayList<HitBox> hitboxes=new ArrayList<HitBox>();
		//for(Player p:players) {
		for(int i=0;i<players.size();i++) {	
			hitboxes.clear();
			for(Player p1:players) {
				if(p1.attacking()&&!p1.equals(players.get(i))) {
					hitboxes.addAll(p1.hitboxes());
					//System.out.println("p: "+p+" p1: "+p1);
				}
			}
			players.get(i).update(players.get(i).getAction(),hitboxes);
			if(players.get(i).getDead()) {
				if(players.get(i).playerType().equals("human")||players.size()==2) {
					//System.out.println("cancel");
					boolean passed=false;
					if(!players.get(i).playerType().equals("human")&&players.size()==2) {
						welcomeScreen.levelUp();
						passed=true;
					}
					count=0;
					done=true;
					panel.done(passed);
				}
				players.remove(players.get(i));
				//System.out.println("Player "+i+" removed");
			}
		}
		if(tutorialOn) {
			count++;
			if(count%500==0)
				panel.nextPartOfTutorial();
		}
		if(done) {
			count++;
			if(count>=100) {
				window.dispose();
				this.cancel();
			}
				
		}
		panel.repaint();
	}
}

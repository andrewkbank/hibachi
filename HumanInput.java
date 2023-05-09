//Aidan's
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class HumanInput extends Input implements KeyListener{
	
	public static final int JUMP = 0, UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4, LIGHT = 5, HEAVY = 6, PAUSE = 7;
	int[] keyCodes = {32, 87, 83, 65, 68, 85, 73, 80};//TODO: space, w,s,a,d,(light),(heavy)
	ArrayList<Integer> heldKeys = new ArrayList<Integer>();
	ArrayList<Integer> pressedKeys=new ArrayList<Integer>();
	Player player;
	String lastAction = "";
	int consecutiveFrames=0;
	HumanInput(Player player) {
		this.player = player;
	}
	public void resetLastAction() {
		lastAction = "";
		consecutiveFrames=0;
	}
	private boolean holding(int index) {
		return heldKeys.contains(keyCodes[index]);
	}
	private boolean pressed(int index) {
		return pressedKeys.contains(keyCodes[index]);
	}
	public String getAction() {
		String action="";
		if(lastAction.equals("jump crouch")) {
			if(holding(JUMP)) {
				action="jump crouch";
			}else {
				if(consecutiveFrames>3) {
					action="full hop";
				}else {
					action="short hop";
				}
			}
			if(consecutiveFrames>4) {
				action="full hop";
			}
		}else if(player.attacking()==true) {
			consecutiveFrames++;
			action=lastAction;
			//makes sure action only contains the last attack, not the drift or fastfall
			if(action.contains("drift")) {
				int index=action.indexOf("drift");
				action=action.substring(0, index);
			}else if(action.contains("fastFall?")) {
				int index=action.indexOf("fastFall?");
				action=action.substring(0, index);
			}
			//stuff for turning the player around mid-special
			if(player.getSpecial()&&lastAction.contains("special charge")) {
				if(pressed(LEFT)) {
					player.setFacing(false);
				}else if(pressed(RIGHT)) {
					player.setFacing(true);
				}
			}
			if(player.lossOfControl()==true&&lastAction.equals("jab1")&&pressed(LIGHT)) {
				//System.out.println("jab2 initiated");
				action="jab2";
				player.resetAttackFrame();
				player.setLossOfControl(0);
			}else if(player.lossOfControl()==true&&lastAction.equals("jab2")&&pressed(LIGHT)){
				action="fTilt";
				player.resetAttackFrame();
				player.setLossOfControl(0);
			}else if(player.attackFrame()==player.getFrameData().framesOfAttack(lastAction)&&player.lossOfControl()==false) {
				if(lastAction.contains("special1")) {
					action="special charge";
					player.resetAttackFrame();
				}else if(action.contains("special charge")) {
					//attackFrame=1;
					if(!holding(HEAVY)) {
						action="special2";
						player.resetAttackFrame();
					}
				}else {
					player.setLossOfControl(player.getFrameData().endlag(lastAction));
				}
			}
			if(player.getOnGround()==false) {
				if(holding(LEFT))
					action+= "driftLeft";
				if(holding(RIGHT))
					action+= "driftRight";
				if(pressed(DOWN)) {
					action+="fastFall?";
				}
			}
			if(player.getOnGround()==true&&lastAction.contains("Air")) {
				//System.out.println("aerial interrupted");
				action="crouch";
				if(!player.lossOfControl()) {
					player.setLossOfControl(player.getFrameData().endlag(lastAction));
				}
			}
		}else {
			if(holding(PAUSE)) {
				lastAction += "";
			}
			if(player.getOnGround() == true) {
				if(heldKeys.size() == 0||holding(UP))
					action= "idle";
				if(holding(DOWN)) {
					action= "crouch";
					if(pressed(LIGHT)) {
						//System.out.println("down tilt");
						player.attack();
						action="dTilt";
					}
				}else if(pressed(JUMP)) {
					//return "jump";
					//System.out.println("jump crouch");
					action= "jump crouch";
				}else if(holding(LEFT)) {
					if(pressed(LIGHT)) {
						action= "fTilt";
						player.attack();
					}else if(pressed(HEAVY)) {
						action= "special1";
						player.attack();
					}else if((lastAction.equals("walkLeft")&&consecutiveFrames==10)||lastAction.equals("dashLeft")) {
						resetLastAction();
						action= "dashLeft";
					}
					else  {
						action= "walkLeft";
					}
				}
				else if (holding(RIGHT)) {
					if(pressed(LIGHT)) {
						player.attack();
						action= "fTilt";
					}else if(pressed(HEAVY)) {
						player.attack();
						action= "special1";
					}else if((lastAction.equals("walkRight")&&consecutiveFrames==10)||lastAction.equals("dashRight")) {
						resetLastAction();
						action= "dashRight";
					}else {
						action= "walkRight";
					}
				}else if(holding(UP)&&pressed(LIGHT)) {
					player.attack();
					action="uTilt";
				}else if(pressed(LIGHT)) {
					//System.out.println("jabstuff");
					player.attack();
					action="jab1";
					if(lastAction.equals("jab1")&&consecutiveFrames>player.getFrameData().framesOfAttack("Jab1")) {
						action="jab2";
					}else if(lastAction.equals("jab2")&&consecutiveFrames>player.getFrameData().framesOfAttack("Jab1")) {
						action="fTilt";
					}
					
				}else if(pressed(HEAVY)) {
					player.attack();
					action="special1";
				}
			}
			else {
				//System.out.println("airborn");
				if( ( holding(LEFT) && pressed(LIGHT) && player.facing() == Player.RIGHT ) || 
						( holding(RIGHT) && pressed(LIGHT) && player.facing() == Player.LEFT) ) {
					player.attack();
					action= "bAir";
				}
				else if( ( holding(LEFT) && pressed(LIGHT) && player.facing() == Player.LEFT ) ||
							( holding(RIGHT) && pressed(LIGHT) && player.facing() == Player.RIGHT) ) {
					player.attack();
					action= "fAir";
				}
				else if( holding(UP) && pressed(LIGHT) ) {
					player.attack();
					action= "uAir";
				}
				else if( holding(DOWN) && pressed(LIGHT) ) {
					player.attack();
					action= "dAir";
				}
				else if( pressed(LIGHT) ) {
					player.attack();
					action= "nAir";
				}else if(pressed(HEAVY)) {
					player.attack();
					action="special1";
				}else {
					action="jump";
				}
				if(holding(LEFT))
					action+= "driftLeft";
				if(holding(RIGHT))
					action+= "driftRight";
				if(pressed(DOWN)) {
					action+="fastFall?";
				}
			}
		}
		if(lastAction.equals(action)) {
			consecutiveFrames++;
		}else {
			consecutiveFrames=0;
		}
		lastAction=action;
		pressedKeys.clear();
		return action;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if( heldKeys.contains((Integer) arg0.getKeyCode()) == false) {
			for(int i:keyCodes) {
				if(i==(Integer) arg0.getKeyCode()) {
					heldKeys.add(arg0.getKeyCode());
					pressedKeys.add(arg0.getKeyCode());
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		heldKeys.remove((Integer) arg0.getKeyCode());
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
//Aidan's
import java.util.ArrayList;

public abstract class FrameData {
	private String name;
	private float groundSpeed;
	private float airSpeed;
	private float jumpSpeed;
	private float friction;
	private float shortHopFrame;
	private boolean backAirTurnsAround;
	private boolean specialJump;
	
	public String getName() {
		return name;
	}
	public void setName(String setName) {
		name=setName;
	}
	public float getGroundSpeed() {
		return groundSpeed;
	}
	public void setGroundSpeed(float setGroundSpeed) {
		groundSpeed=setGroundSpeed;
	}
	public float getAirSpeed() {
		return airSpeed;
	}
	public void setAirSpeed(float setAirSpeed) {
		airSpeed=setAirSpeed;
	}
	public float getJumpSpeed() {
		return jumpSpeed;
	}
	public void setJumpSpeed(float setJumpSpeed) {
		jumpSpeed=setJumpSpeed;
	}
	public float getFriction() {
		return friction;
	}
	public void setFriction(float setFriction) {
		friction=setFriction;
	}
	public float getShortHopFrame() {
		return shortHopFrame;
	}
	public void setShortHopFrame(float setShortHopFrame) {
		shortHopFrame=setShortHopFrame;
	}
	public boolean backAirTurnsAround() {
		return backAirTurnsAround;
	}
	public void setBackAirTurnAround(boolean backAirTurnAround) {
		backAirTurnsAround=backAirTurnAround;
	}
	public boolean specialJump() {
		return specialJump;
	}
	public void setSpecialJump(boolean jump) {
		specialJump=jump;
	}
	//for the abstract methods to be used more easily
	public int attackNameToInt(String attack) {
		int attackNum=-1;
		if(attack.equals("1Jab")||attack.equals("jab1")) {
			attackNum=0;
		}else if(attack.equals("2Jab")||attack.equals("jab2")){
			attackNum=1;
		}else if(attack.equals("Forward Tilt")||attack.equals("fTilt")) {
			attackNum=2;
		}else if(attack.equals("Up Tilt")||attack.equals("uTilt")) {
			attackNum=3;
		}else if(attack.equals("Down Tilt")||attack.equals("dTilt")) {
			attackNum=4;
		}else if(attack.equals("Neutral Air")||attack.contains("nAir")) {
			attackNum=5;
		}else if(attack.equals("Forward Air")||attack.contains("fAir")) {
			attackNum=6;
		}else if(attack.equals("Up Air")||attack.contains("uAir")) {
			attackNum=7;
		}else if(attack.equals("Back Air")||attack.contains("bAir")) {
			attackNum=8;
		}else if(attack.equals("Down Air")||attack.contains("dAir")) {
			attackNum=9;
		}else if(attack.contains("1Special")||attack.contains("special1")){
			attackNum=10;
		}else if(attack.contains("Special Charge")||attack.contains("special charge")) {
			attackNum=11;
		}else if(attack.contains("2Special")||attack.contains("special2")) {
			attackNum=12;
		}
		return attackNum;
	}
	public abstract int framesOfAttack(String attack);
	public abstract ArrayList<Integer> activeFrames(String attack);
	public abstract int endlag(String attack);
	public abstract ArrayList<HitBox> hitboxes(String attack,int frame,float playerX,float playerY,boolean right);
	//have hitboxes take the attack name with the number at the end, Ie "Down Tilt4" for it to determine what frame
	public abstract ArrayList<Box> hurtboxes(String action,float playerX,float playerY,boolean right);
	
	//hopefully we won't have to use this one, but just in case the frames are too fast, we can adjust
	public abstract int framesBetweenAnimations(String attack);
	
}

import java.util.ArrayList;

//Aidan's
public class Player implements Physics {
	public final static boolean RIGHT = true, LEFT = false; 
	private String skin;
	private String stance="Idle";
	private FrameData frameData;
	private boolean attacking=false;
	private boolean special=false;
	private int lossOfControlFrames = 0; //equal to the number of frames left before player has control, 0 means they have control
	private boolean facing=true;
	private int attackFrame=0;
	private int specialCharge=0;
	private int hitInvincibility=0;
	private ArrayList<HitBox> hitboxes=new ArrayList<HitBox>();
	private ArrayList<Box> hurtboxes=new ArrayList<Box>();
	private int damage=0;
	private boolean dead=false;
	private float x=500;
	private float y=498;
	private float xVelocity=0;
	private float yVelocity=0;
	private String playerType;
	private Input Input;
	public Player(String playerSkin, String playerType,boolean tutorial) {
		skin=playerSkin;
		//skin="Maki";
		this.playerType = playerType;
		if(playerType == "human") {
			x=0;
			Input = new HumanInput(this);
			Panel.getFrame().addKeyListener( (HumanInput) Input);
			if(skin.equals("Ramen"))
				frameData=new RamenFrameData();
			else
				frameData=new MakiFrameData();
		}else if (playerType.contains("AI")) {
			Input = new AI_Input(this, Panel.getPanel().getPlayers()[0],playerType,tutorial);
			frameData=new MakiFrameData();
			x=(int)Math.random()*300+200;
		}


	}
	/*
	public void rebindInput() {
		if(Input != null)
			Panel.getPanel().addKeyListener(Input);
	}
	 */
	public String getAction() {
		return Input.getAction();

	}
	public void update(String action,ArrayList<HitBox> allHitBoxes) {
		hurtboxes=frameData.hurtboxes(action, x, y, facing);
		if(hitInvincibility>0)
			hitInvincibility--;
		if(lossOfControlFrames > 0) {
			hitboxes.clear();
			specialCharge=0;
			//System.out.println("frames left: "+lossOfControlFrames);
			if(action.contains("crouch")) {
				stance="crouch";
			}
			if(!attacking) {
				//System.out.println("yVelocity: "+yVelocity);
				xVelocity*=.95;
				double velocity=Math.sqrt(xVelocity*xVelocity+yVelocity*yVelocity);
				//remember to change both somethings
				double something=10;
				if(velocity>something&&!stance.equals("Hit Controlled")) {
					if(stance.contains("1")) {
						stance="Hit Spiraling2";
					}else if(stance.contains("2")) {
						stance="Hit Spiraling3";
					}else {
						stance="Hit Spiraling1";
					}
				}else {
					stance="Hit Controlled";
					//DI
					if(action.contains("Right")) {
						xVelocity+=.4;
					}else if(action.contains("Left")){
						xVelocity-=.4;
					}
				}
			}
			lossOfControlFrames -= 1;
			if(lossOfControlFrames<=0) {
				//System.out.println("ending attack");
				//hitboxes.clear();
				if(action.contains("bAir")&&frameData.backAirTurnsAround()) {
					facing=!facing;
				}
				attackFrame=0;
				attacking=false;
				special=false;
				update(action,allHitBoxes);
			}
		}
		else {
			//hurtboxes=frameData.hurtboxes(action, x, y, facing);
			switch (action) {//moving horizontally
			case "jump" :
			{
				stance = "Jump";
				break;
			}
			case "jump crouch":
			{
				stance="Jump Crouch";
				break;
			}
			case "full hop":
			{
				stance="Jump";
				yVelocity=-frameData.getJumpSpeed();
				break;
			}
			case "short hop":
			{
				stance="Jump";
				yVelocity=-frameData.getJumpSpeed()/2;
				break;
			}
			case "walkRight" :
			{
				xVelocity = frameData.getGroundSpeed()/2;
				facing = Player.RIGHT;
				stance = "Dash";

				break;
			}
			case "walkLeft" : 
			{
				xVelocity = -frameData.getGroundSpeed()/2;
				facing = Player.LEFT;
				stance = "Dash";
				break;
			}
			case "dashRight" :
			{
				facing = Player.RIGHT;
				xVelocity=frameData.getGroundSpeed();
				stance = "Dash";
				break;
			}
			case "dashLeft" :
			{
				facing = Player.LEFT;
				xVelocity=-frameData.getGroundSpeed();
				stance = "Dash";
				break;
			}
			default : 
			{
				//xVelocity = 0;
				//System.out.println(skin);
				xVelocity/=frameData.getFriction();
				stance = "Idle";
			}
			}
			double DI=.8;
			if(action.contains("driftRight")) {
				stance="Jump";
				if(special) {
					DI=.4;
				}
				xVelocity += DI;
				if(xVelocity-1<=frameData.getAirSpeed()&&xVelocity>=frameData.getAirSpeed()) {
					xVelocity=frameData.getAirSpeed();
				}
				if(xVelocity+1>=-frameData.getAirSpeed()&&xVelocity<=-frameData.getAirSpeed()) {
					xVelocity=-frameData.getAirSpeed();
				}
			}
			if(action.contains("driftLeft")) {
				stance="Jump";
				if(special) {
					DI=.4;
				}
				xVelocity -= DI;
				if(xVelocity-1<=frameData.getAirSpeed()&&xVelocity>=frameData.getAirSpeed()) {
					xVelocity=frameData.getAirSpeed();
				}
				if(xVelocity+1>=-frameData.getAirSpeed()&&xVelocity<=-frameData.getAirSpeed()) {
					xVelocity=-frameData.getAirSpeed();
				}
			}
			if(action.contains("fastFall?")){
				stance="Jump";
				if(yVelocity>0)
					yVelocity=32;
			}
			if(action.equals("crouch")) {
				stance = "Crouch";
			}
			//attackstuff
			if(attacking) {
				attackFrame++;
				if(frameData.activeFrames(action).contains(attackFrame))
					hitboxes=frameData.hitboxes(action,attackFrame, x, y,facing);
				if(specialCharge>0) {
					for(HitBox h:hitboxes) {
						h.upDaKnockback(specialCharge);
					}
				}
				//hurtboxes=frameData.hurtboxes(action, x, y, facing);
				if(action.equals("jab1")) {
					stance="1Jab";
				}else if(action.equals("jab2")) {
					stance="2Jab";
				}else if(action.equals("fTilt")) {
					stance="Forward Tilt";
				}else if(action.equals("dTilt")) {
					stance="Down Tilt";
				}else if(action.equals("uTilt")) {
					stance="Up Tilt";
				}else if(action.contains("nAir")) {
					stance="Neutral Air";
				}else if(action.contains("fAir")) {
					stance="Forward Air";
				}else if(action.contains("uAir")) {
					stance="Up Air";
				}else if(action.contains("bAir")) {
					stance="Back Air";
				}else if(action.contains("dAir")) {
					stance="Down Air";
				}else if(action.contains("special1")) {
					special=true;
					stance="1Special";
				}else if(action.contains("special charge")) {
					stance="Special Charge";
					specialCharge++;
					if(attackFrame>2) {
						attackFrame=1;
					}
				}else if(action.contains("special2")) {
					stance="2Special";
					//specialCharge=0;
					if(attackFrame==1&&frameData.specialJump()) {
						yVelocity=-3*frameData.getJumpSpeed()/4;
					}else if(attackFrame==frameData.framesOfAttack("2Special")&&frameData.specialJump()) {
						yVelocity=0;
					}
				}
				stance+=attackFrame;
				if(frameData.activeFrames(action).contains(attackFrame)) {
					for(HitBox h:hitboxes) {
						h.accountForVelocity(xVelocity,yVelocity);
						//only y really gets messed up (by jumping and falling)
					}
				}
				//System.out.println(stance);
			}else {
				hitboxes.clear();
			}
		}
		for(Box b:hurtboxes) {
			b.accountForVelocity(xVelocity, yVelocity);
		}
		ArrayList<HitBox> boxesThatHit=new ArrayList<HitBox>();
		for(Box b:hurtboxes) {
			for(HitBox h:allHitBoxes) {
				if(b.hit(h)) {
					boxesThatHit.add(h);
					//System.out.println("hitbox: ("+h.getCenterX()+", "+h.getCenterY()+")");
				}
			}
		}
		double highestKnockback=0;
		HitBox boxThatHit=null;
		for(HitBox h:boxesThatHit) {
			if(h.getMagnitude()>highestKnockback) {
				highestKnockback=h.getMagnitude();
				boxThatHit=h;
			}
		}
		if(boxThatHit!=null&&hitInvincibility==0) {
			//System.out.println("hit");
			hitboxes.clear();
			lossOfControlFrames=boxThatHit.hitstun();
			attackFrame=0;
			attacking=false;
			special=false;
			hitInvincibility=20;
			//might need to change
			double launchVelocity=boxThatHit.getMagnitude()*(.1+((double)damage)/1000);
			//if launch velocity exceeds something, player loses control and spins
			double something=10;
			
			//all attacks do 1 damage cuz I'm lazy
			damage++;
			xVelocity=(float) (launchVelocity*Math.cos(boxThatHit.getAngle()));
			//maybe this line that causes the problem
			yVelocity=(float) -(launchVelocity*Math.sin(boxThatHit.getAngle()));
			//onGround=false;
			if(launchVelocity>something) {
				action="Hit Spiraling1";
			}else {
				action="Hit Controlled";
			}
			stance=action;
			//if(yVelocity>=0&&playerType.contains("AI")) {
			//	System.out.println("yVelocity: "+yVelocity);
			//}
		}
		incrementMovement(action.contains("Hit"));
	}
	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}
	@Override
	public void setX(float x) {
		this.x = x;

	}
	@Override
	public float getY() { return y;}
	@Override
	public void setY(float y) { this.y = y; }
	@Override
	public float getXVel() { return xVelocity; }
	@Override
	public void setXVel(float xVel) { xVelocity = xVel; }
	@Override
	public float getYVel() { return yVelocity; }
	@Override
	public void setYVel(float yVel) { yVelocity = yVel; }
	//@Override
	/*public boolean getOnGround() { 
		try {return Panel.getStage().isAPlatform((int)x, (int)y+1); }
		catch(Exception e) {
			e.printStackTrace();
			//System.out.println("x: " + x);
			//System.out.println("y: " + y);
			//System.out.println("xVel: " + xVelocity);
			//System.out.println("yVel: " + yVelocity);
			return true;
		}
	}*/
	public int getNearGround() {
		//if(skin.equals("Maki")) {
			//System.out.println("x: " + x);
			//System.out.println("y: " + y);
			//System.out.println("xVel: " + xVelocity);
			//System.out.println("yVel: " + yVelocity);
		//}
		//if(stance.contains("Hit")&&yVelocity<0) {
		//	return -1;
		//}
		for(int d = 0; d <= (int) yVelocity+1; d++) {
			if( Panel.getStage().isAPlatform((int)(x+x/Math.abs(y)*d), (int)y+1+d) )
				return d;
		}
		return -1;
	}
	@Override
	public void dead() {
		dead=true;
	}
	public boolean getDead() {
		return dead;
	}
	//@Override
	public int collidedWithSurface() { return 0; }
	@Override
	public boolean landed() { return false; }
	/**
	 * @return True for right, false for left.
	 * The result should ideally be compared to <code>Player.RIGHT</code> or <code>Player.LEFT</code>
	 */
	public boolean facing() { return facing; } // true = right, false = left
	public void attack() {
		if(attackFrame==0) {
			attacking=true;
			//attackFrame=0;
		}
	}
	public boolean attacking() {
		return attacking;
	}
	public int attackFrame() {
		return attackFrame;
	}
	public void resetAttackFrame() {
		attackFrame=0;
	}
	public void setLossOfControl(int setLossOfControl) {
		lossOfControlFrames=setLossOfControl;
	}
	public boolean lossOfControl() {
		if(lossOfControlFrames>0) {
			return true;
		}else {
			return false;
		}
	}
	public boolean getSpecial() {
		return special;
	}
	public void setFacing(boolean setFacing) {
		facing=setFacing;
	}
	public FrameData getFrameData() {
		return frameData;
	}
	public ArrayList<HitBox> hitboxes(){
		return hitboxes;
	}
	public ArrayList<Box> hurtboxes(){
		return hurtboxes;
	}
	public String playerType() {
		return playerType;
	}
	public String getStance() {
		return stance+" "+skin+".png";
	}
	//public String toString() {
	//	return skin;
	//}
}
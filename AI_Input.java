public class AI_Input extends Input{
	public static final int JUMP = 0, UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4, LIGHT = 5, HEAVY = 6, PAUSE = 7;
	private float attack = 0, move = 0;
	private float maxRange = 160;
	private Player controller;
	private Player target;
	private int level;
	private int consecutiveFrames=0;
	private String lastAction = "";
	private boolean tutorialMode;

	public AI_Input(Player controller, Player target,String name,boolean tutorial) {
		this.controller = controller;	
		this.target = target;
		level=Integer.parseInt(name.substring(2, 3));
		tutorialMode=tutorial;
	}
	@Override
	public String getAction() {
		if(tutorialMode)
			return "idle";

		String action="";
		boolean grounded = controller.getOnGround();
		String direction;
		float yDist = target.getY() - controller.getY();
		float xDist = target.getX() - controller.getX();
		if(xDist > 0)
			direction = "Right";
		else
			direction = "Left";
		if(lastAction.equals("jump crouch")) {
			if(Math.random()>.2) {
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
		}else if(controller.attacking()==true) {
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
			if(controller.getSpecial()&&lastAction.contains("special charge")) {
				if(direction.equals("Left")) {
					controller.setFacing(false);
				}else if(direction.equals("Right")) {
					controller.setFacing(true);
				}
			}
			if(controller.lossOfControl()==true&&lastAction.equals("jab1")&&Math.random()>.25) {
				action="jab2";
				controller.resetAttackFrame();
				controller.setLossOfControl(0);
			}else if(controller.lossOfControl()==true&&lastAction.equals("jab2")&&xDist<10&&Math.random()>.25){
				action="fTilt";
				controller.resetAttackFrame();
				controller.setLossOfControl(0);
			}else if(controller.attackFrame()==controller.getFrameData().framesOfAttack(lastAction)&&controller.lossOfControl()==false) {
				if(lastAction.contains("special1")) {
					action="special charge";
					controller.resetAttackFrame();
				}else if(action.contains("special charge")) {
					//attackFrame=1;
					if(Math.random()>.75) {
						action="special2";
						controller.resetAttackFrame();
					}
				}else {
					controller.setLossOfControl(controller.getFrameData().endlag(lastAction));
				}
			}
			if(controller.getOnGround()==false) {
				if(direction.equals("Left"))
					action+= "driftLeft";
				if(direction.equals("Right"))
					action+= "driftRight";
				if(dist(target, controller) > maxRange) {
					action+="fastFall?";
				}
			}
			if(controller.getOnGround()==true&&lastAction.contains("Air")) {
				//System.out.println("aerial interrupted");
				action="crouch";
				if(!controller.lossOfControl()) {
					controller.setLossOfControl(controller.getFrameData().endlag(lastAction));
				}
			}
		}else {
			if(controller.lossOfControl())
				action= "idle";
			else if(Math.random()>(double)(level)/50&&!controller.attacking()) {
				if(grounded)
					action="idle";
				else
					action="jump";
			}
			else if(dist(target, controller) <= maxRange) {

				if(Math.abs( yDist ) > Math.abs( xDist )) {
					if(yDist <= 0) {
						if(grounded)
							if(Math.random()>.5) {
								action= "uTilt";
								controller.attack();
							}else
								action="jump crouch";
						else {
							controller.attack();
							action= "uAir";
						}
					}
					else {
						if(grounded)
							action= "jump crouch";
						else {
							action= "dAir";
							controller.attack();
						}
					}
				}else if(controller.facing()!=direction.equals("Right")) {
					if(grounded) {
						if(Math.random()>.5) {
							action="jump crouch";
						}else {
							action="walk"+direction;
						}
					}else {
						controller.attack();
						action="bAir";
					}
				}else {
					float extraChoices = 0;
					if(grounded)
						extraChoices = 4;	
					int choice = (int) ( Math.random() * (4+extraChoices) );
					//System.out.println(choice);
					if(choice == 0) {
						controller.attack();
						if(grounded)
							action= "dTilt";
						else
							action= "dAir";
					}
					else if(choice == 1) {
						controller.attack();
						if(grounded)
							action= "fTilt";
						else
							action= "fAir";
					}
					else if(choice==2) {
						controller.attack();
						action="special1";
					}
					else if(choice==3) {
						controller.attack();
						if(grounded)
							action="jab1";
						else
							action="nAir";
					}
					else if(choice >= 4 && grounded) {
						action= "jump crouch";
					}
				}
			}else if(Math.abs( target.getX() - controller.getX() ) > 0) {
				if(grounded) {
					if(Math.random()>.1)
						action= "walk" + direction;
					else 
						action="jump crouch";
				}else
					action= "drift" + direction;
			}else {
				controller.attack();
				if(grounded)
					action= "uAir";
				else
					action= "uTilt";
			}
		}
		if(lastAction.equals(action)) {
			consecutiveFrames++;
		}else {
			consecutiveFrames=0;
		}
		lastAction=action;
		return action;

	}
	private float calcMoveScore() {
		if(dist(controller, target) > maxRange) {
			move = 1;
		}
		else
			move = 0;
		return move;
	}
	public Player getTarget() {
		return target;
	}
	public static float dist(float x1,float y1, float x2, float y2) {
		return (float) Math.sqrt( Math.pow(x2-x1,2) + Math.pow(y2-y1,2) );
	}
	public static float dist(Player p1,Player p2) {
		return dist(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	public void resetLastAction() {
		lastAction = "";
		consecutiveFrames=0;
	}
}
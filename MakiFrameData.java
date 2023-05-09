import java.util.ArrayList;
//Aidan's
public class MakiFrameData extends FrameData {
	public MakiFrameData() {
		setName("Maki");
		//might change stats later
		setGroundSpeed(15);
		setAirSpeed(15);
		setJumpSpeed(25);
		setFriction((float)1.1);
		setShortHopFrame(3);
		setBackAirTurnAround(false);
		setSpecialJump(true);
	}
	@Override
	public int framesOfAttack(String attack) {
		int attackNum=attackNameToInt(attack);
		if(attackNum==0) {
			//jab1
			return 7;
		}else if(attackNum==1) {
			//jab2
			return 8;
		}else if(attackNum==2) {
			//ftilt/dash attack
			return 13;
		}else if(attackNum==3) {
			//utilt
			return 10;
		}else if(attackNum==4) {
			//dtilt
			return 9;
		}else if(attackNum==5) {
			//nair
			return 16;
		}else if(attackNum==6) {
			//fair
			return 19;
		}else if(attackNum==7) {
			//uair
			return 16;
		}else if(attackNum==8) {
			//bair
			return 9;
		}else if(attackNum==9) {
			//dair
			return 12;
		}else if(attackNum==10) {
			//1special
			return 2;
		}else if(attackNum==11) {
			//special charge
			return 2;
		}else if(attackNum==12) {
			//2special
			return 11;
		}
		
		return 0;
	}

	@Override
	public ArrayList<Integer> activeFrames(String attack) {
		int attackNum=attackNameToInt(attack);
		ArrayList<Integer> frames=new ArrayList<Integer>();
		int start=0;
		int finish=0;
		if(attackNum==0) {
			//jab1
			start=1;
			finish=5;
		}else if(attackNum==1) {
			//jab2
			start=3;
			finish=5;
		}else if(attackNum==2) {
			//ftilt
			start=7;
			finish=10;
		}else if(attackNum==3) {
			//utilt
			start=5;
			finish=7;
		}else if(attackNum==4) {
			//dtilt
			start=2;
			finish=8;
		}else if(attackNum==5) {
			//nair
			start=2;
			finish=13;
		}else if(attackNum==6) {
			//fair
			start=8;
			finish=11;
		}else if(attackNum==7) {
			//uair
			start=2;
			finish=8;
		}else if(attackNum==8) {
			//bair
			start=2;
			finish=8;
		}else if(attackNum==9) {
			//dair
			start=6;
			finish=9;
		}else if(attackNum==12) {
			//special
			start=2;
			finish=6;
		}
		
		for(int i=start;i<=finish;i++) {
			frames.add(i);
		}
		return frames;
	}

	@Override
	public int endlag(String attack) {
		int attackNum=attackNameToInt(attack);
		if(attackNum==0) {
			//jab1
			return 10;
		}else if(attackNum==1) {
			//jab2
			return 10;
		}else if(attackNum==2) {
			//ftilt
			return 15;
		}else if(attackNum==3) {
			//utilt
			return 15;
		}else if(attackNum==4) {
			//dtilt
			return 5;
		}else if(attackNum==5) {
			//nair
			return 15;
		}else if(attackNum==6) {
			//fair
			return 15;
		}else if(attackNum==7) {
			//uair
			return 5;
		}else if(attackNum==8) {
			//bair
			return 15;
		}else if(attackNum==9) {
			//dair
			return 20;
		}else if(attackNum==12) {
			return 40;
		}
		return 0;
	}

	@Override
	public ArrayList<HitBox> hitboxes(String attack,int frame, float playerX, float playerY,boolean right) {
		//double xCoor,double yCoor,double rad,double launchAngle,double launchSpeed,int stun
				//add launchAngle and launchSpeed and stun after
				ArrayList<HitBox> hitboxes=new ArrayList<HitBox>();
				int length=0;
				
				int attackNum=attackNameToInt(attack);
				if(attackNum==0) {
					//jab1
					//1-5
					if(frame==1)
						hitboxes.add(new HitBox(playerX+100,playerY+70,50,Math.PI/3,20,10,right));
					if(frame==2)
						hitboxes.add(new HitBox(playerX+100,playerY+70,50,Math.PI/3,10,10,right));
					if(frame==3)
						hitboxes.add(new HitBox(playerX+110,playerY+70,50,Math.PI/3,10,10,right));
					if(frame==4)
						hitboxes.add(new HitBox(playerX+110,playerY+70,50,Math.PI/3,10,10,right));
					if(frame==5)
						hitboxes.add(new HitBox(playerX+100,playerY+70,50,Math.PI/3,60,10,right));
					length=90;
				}else if(attackNum==1) {
					//jab2
					//3-5
					if(frame==3)
						hitboxes.add(new HitBox(playerX+100,playerY+80,50,-Math.PI/3,20,10,right));
					if(frame==4)
						hitboxes.add(new HitBox(playerX+105,playerY+70,50,-Math.PI/3,20,10,right));
					if(frame==5)
						hitboxes.add(new HitBox(playerX+105,playerY+60,50,-Math.PI/3,20,10,right));
					length=90;
					//left off here
				}else if(attackNum==2) {
					//ftilt
					//7-10
					if(frame==7)
						hitboxes.add(new HitBox(playerX+90,playerY+40,50,Math.PI/3,150,10,right));
					if(frame==8)
						hitboxes.add(new HitBox(playerX+105,playerY+60,50,Math.PI/3,150,10,right));
					if(frame==9)
						hitboxes.add(new HitBox(playerX+110,playerY+80,50,Math.PI/3,150,10,right));
					if(frame==10)
						hitboxes.add(new HitBox(playerX+110,playerY+100,50,Math.PI/3,150,10,right));
					length=90;
					
				}else if(attackNum==3) {
					//utilt
					//5-7
					if(frame==5)
						hitboxes.add(new HitBox(playerX+23,playerY+10,100,Math.PI/2,200,20,right));
					if(frame==6)
						hitboxes.add(new HitBox(playerX+23,playerY,100,Math.PI/2,200,20,right));
					if(frame==7)
						hitboxes.add(new HitBox(playerX+23,playerY+10,100,Math.PI/2,200,20,right));
					length=45;
				}else if(attackNum==4) {
					//dtilt
					//2-8
					if(frame==2)
						hitboxes.add(new HitBox(playerX+90,playerY+100,50,Math.PI/4,100,20,right));
					if(frame==3)
						hitboxes.add(new HitBox(playerX+100,playerY+100,50,Math.PI/4,100,20,right));
					if(frame==4)
						hitboxes.add(new HitBox(playerX+100,playerY+100,50,Math.PI/4,100,20,right));
					if(frame==5)
						hitboxes.add(new HitBox(playerX+100,playerY+100,50,Math.PI/4,100,20,right));
					if(frame==6)
						hitboxes.add(new HitBox(playerX+100,playerY+100,50,Math.PI/4,100,20,right));
					if(frame==7)
						hitboxes.add(new HitBox(playerX+90,playerY+100,50,Math.PI/4,100,20,right));
					if(frame==8)
						hitboxes.add(new HitBox(playerX+90,playerY+100,50,Math.PI/4,100,20,right));
					length=90;
				}else if(attackNum==5) {
					//nair
					//2-13
					hitboxes.add(new HitBox(playerX+90,playerY+70,50,0,100,30,right));
					hitboxes.add(new HitBox(playerX,playerY+75,50,Math.PI,100,30,right));
					hitboxes.add(new HitBox(playerX+40,playerY+50,75,Math.PI/2,50,30,right));
					length=80;	
				}else if(attackNum==6) {
					//fair
					//8-11
					if(frame==8)
						hitboxes.add(new HitBox(playerX+90,playerY+55,80,0,200,30,right));
					if(frame==9)
						hitboxes.add(new HitBox(playerX+90,playerY+55,80,0,200,30,right));
					if(frame==10)
						hitboxes.add(new HitBox(playerX+90,playerY+60,80,0,200,30,right));
					if(frame==11)
						hitboxes.add(new HitBox(playerX+90,playerY+65,80,0,200,30,right));
					length=65;
					
				}else if(attackNum==7) {
					//uair
					//2-8
					if(frame==2)
						hitboxes.add(new HitBox(playerX+100,playerY+40,50,Math.PI/2,150,20,right));
					if(frame==3)
						hitboxes.add(new HitBox(playerX+90,playerY+20,50,Math.PI/2,150,20,right));
					if(frame==4)
						hitboxes.add(new HitBox(playerX+70,playerY+10,50,Math.PI/2,150,20,right));
					if(frame==5)
						hitboxes.add(new HitBox(playerX+50,playerY+10,50,Math.PI/2,150,20,right));
					if(frame==6)
						hitboxes.add(new HitBox(playerX+20,playerY+15,50,2*Math.PI/3,150,20,right));
					if(frame==7)
						hitboxes.add(new HitBox(playerX+10,playerY+30,50,2*Math.PI/3,150,20,right));
					if(frame==8)
						hitboxes.add(new HitBox(playerX,playerY+50,50,2*Math.PI/3,150,20,right));
					length=80;	
				}else if(attackNum==8) {
					//bair
					//2-8
					if(frame==2)
						hitboxes.add(new HitBox(playerX-10,playerY+40,50,Math.PI,120,10,right));
					if(frame==3)
						hitboxes.add(new HitBox(playerX-15,playerY+50,50,Math.PI,120,10,right));
					if(frame==4)
						hitboxes.add(new HitBox(playerX-15,playerY+60,50,Math.PI,120,10,right));
					if(frame==5)
						hitboxes.add(new HitBox(playerX-15,playerY+70,50,Math.PI,120,10,right));
					if(frame==6)
						hitboxes.add(new HitBox(playerX-15,playerY+80,50,Math.PI,120,10,right));
					if(frame==7)
						hitboxes.add(new HitBox(playerX-10,playerY+90,50,Math.PI,120,10,right));
					if(frame==8)
						hitboxes.add(new HitBox(playerX,playerY+90,50,Math.PI,120,10,right));
					length=80;
					
				}else if(attackNum==9) {
					//dair
					//6-9
					if(frame==6)
						hitboxes.add(new HitBox(playerX+40,playerY+80,75,3*Math.PI/2,200,30,right));
					if(frame==7)
						hitboxes.add(new HitBox(playerX+40,playerY+90,75,3*Math.PI/2,200,30,right));
					if(frame==8)
						hitboxes.add(new HitBox(playerX+40,playerY+100,75,3*Math.PI/2,200,30,right));
					if(frame==9)
						hitboxes.add(new HitBox(playerX+40,playerY+90,75,3*Math.PI/2,200,30,right));
					length=70;
					
				}else if(attackNum==12) {
					//special
					//2-6
					if(frame==2)
						hitboxes.add(new HitBox(playerX+90,playerY+50,70,Math.PI/2,300,30,right));
					if(frame==3) 
						hitboxes.add(new HitBox(playerX+90,playerY+15,50,Math.PI/2,300,30,right));
					if(frame==4)
						hitboxes.add(new HitBox(playerX+90,playerY-5,50,Math.PI/2,300,30,right));
					if(frame==5)
						hitboxes.add(new HitBox(playerX+90,playerY-15,50,Math.PI/2,300,30,right));
					if(frame==6)
						hitboxes.add(new HitBox(playerX+80,playerY-10,50,Math.PI/2,300,30,right));
					length=90;
				}
				if(!right) {
					for(HitBox h:hitboxes) {
						h.flipDaBox(playerX,length);
					}
				}
				return hitboxes;
	}

	@Override
	public ArrayList<Box> hurtboxes(String action, float playerX, float playerY,boolean right) {
		ArrayList<Box> hurtboxes=new ArrayList<Box>();
		int length=0;
		//System.out.println(action);
		if(action.contains("idle")||action.contains("jab")||action.contains("uTilt")||action.contains("special")||action.contains("walk")||action.contains("dash")||action.contains("fTilt")) {
			hurtboxes.add(new Box(playerX+30,playerY+60,87,right));
			length=57;
		}
		if(action.contains("crouch")||action.contains("dTilt")) {
			hurtboxes.add(new Box(playerX+30,playerY+85,50,right));
			hurtboxes.add(new Box(playerX+65,playerY+85,50,right));
			length=93;
		}
		if((action.contains("jump")&&!action.contains("crouch"))||action.contains("Air")||action.contains("drift")) {
			hurtboxes.add(new Box(playerX+30,playerY+55,87,right));
			length=57;
		}
		if(!right) {
			for(Box h:hurtboxes) {
				h.flipDaBox(playerX,length);
			}
		}
		//if(hurtboxes.size()==0) {
		//	System.out.println(action);
		//}
		return hurtboxes;
	}

	@Override
	public int framesBetweenAnimations(String attack) {
		// TODO Auto-generated method stub
		return 0;
	}

}

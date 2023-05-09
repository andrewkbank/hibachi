//Adian's
import java.util.ArrayList;

public class RamenFrameData extends FrameData {
	public RamenFrameData() {
		setName("Ramen");
		setGroundSpeed(15);
		setAirSpeed(15);
		setJumpSpeed(25);
		setFriction((float)1.1);
		setShortHopFrame(3);
		setBackAirTurnAround(true);
		setSpecialJump(false);
	}
	@Override
	public int framesOfAttack(String attack) {
		// update every time you finish an attack animation
		int attackNum=attackNameToInt(attack);
		if(attackNum==0) {
			//jab1
			return 10;
		}else if(attackNum==1) {
			//jab2
			return 4;
		}else if(attackNum==2) {
			//ftilt/dash attack
			return 12;
		}else if(attackNum==3) {
			//utilt
			return 17;
		}else if(attackNum==4) {
			//dtilt
			return 9;
		}else if(attackNum==5) {
			//nair
			return 16;
		}else if(attackNum==6) {
			//fair
			return 10;
		}else if(attackNum==7) {
			//uair
			return 12;
		}else if(attackNum==8) {
			//bair
			return 16;
		}else if(attackNum==9) {
			//dair
			return 18;
		}else if(attackNum==10) {
			//1special
			return 4;
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
			start=3;
			finish=8;
		}else if(attackNum==1) {
			//jab2
			start=1;
			finish=4;
		}else if(attackNum==2) {
			//ftilt
			start=3;
			finish=10;
		}else if(attackNum==3) {
			//utilt
			start=4;
			finish=15;
		}else if(attackNum==4) {
			//dtilt
			start=4;
			finish=8;
		}else if(attackNum==5) {
			//nair
			start=5;
			finish=12;
		}else if(attackNum==6) {
			//fair
			start=2;
			finish=9;
		}else if(attackNum==7) {
			//uair
			start=4;
			finish=10;
		}else if(attackNum==8) {
			//bair
			start=5;
			finish=12;
		}else if(attackNum==9) {
			//dair
			start=4;
			finish=8;
		}else if(attackNum==12) {
			//special
			start=4;
			finish=12;
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
			return 15;
		}else if(attackNum==2) {
			//ftilt
			return 25;
		}else if(attackNum==3) {
			//utilt
			return 10;
		}else if(attackNum==4) {
			//dtilt
			return 5;
		}else if(attackNum==5) {
			//nair
			return 20;
		}else if(attackNum==6) {
			//fair
			return 10;
		}else if(attackNum==7) {
			//uair
			return 10;
		}else if(attackNum==8) {
			//bair
			return 15;
		}else if(attackNum==9) {
			//dair
			return 15;
		}else if(attackNum==12) {
			return 40;
		}
		return 0;
	}
	@Override
	public ArrayList<HitBox> hitboxes(String attack,int frame, float playerX, float playerY,boolean right) {
		// TODO Auto-generated method stub
		//double xCoor,double yCoor,double rad,double launchAngle,double launchSpeed,int stun
		//add launchAngle and launchSpeed and stun after
		ArrayList<HitBox> hitboxes=new ArrayList<HitBox>();
		int length=0;
		
		int attackNum=attackNameToInt(attack);
		if(attackNum==0) {
			//jab1
			//3-8
			if(frame==3)
				hitboxes.add(new HitBox(playerX+120,playerY+80,75,Math.PI/3,20,10,right));
			if(frame==4)
				hitboxes.add(new HitBox(playerX+120,playerY+60,75,Math.PI/3,20,10,right));
			if(frame==5)
				hitboxes.add(new HitBox(playerX+120,playerY+40,75,Math.PI/3,20,10,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX+120,playerY+35,75,Math.PI/3,20,10,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX+115,playerY+25,75,Math.PI/3,20,10,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX+110,playerY+20,75,Math.PI/3,20,10,right));
			length=75;
		}else if(attackNum==1) {
			//jab2
			//1-4
			if(frame==1)
				hitboxes.add(new HitBox(playerX+115,playerY+20,75,Math.PI/3,20,10,right));
			if(frame==2)
				hitboxes.add(new HitBox(playerX+120,playerY+40,75,Math.PI/3,20,10,right));
			if(frame==3)
				hitboxes.add(new HitBox(playerX+120,playerY+60,75,Math.PI/3,20,10,right));
			if(frame==4)
				hitboxes.add(new HitBox(playerX+110,playerY+80,75,Math.PI/3,20,10,right));
			length=75;
		}else if(attackNum==2) {
			//ftilt
			//3-10
			if(frame==3)
				hitboxes.add(new HitBox(playerX+90,playerY+20,75,Math.PI/6,180,20,right));
			if(frame==4)
				hitboxes.add(new HitBox(playerX+100,playerY+30,75,Math.PI/6,180,20,right));
			if(frame==5)
				hitboxes.add(new HitBox(playerX+115,playerY+45,75,Math.PI/6,180,20,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX+115,playerY+60,75,Math.PI/6,180,20,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX+100,playerY+70,75,Math.PI/6,180,20,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX+80,playerY+80,75,Math.PI/6,180,20,right));
			if(frame==9)
				hitboxes.add(new HitBox(playerX+60,playerY+70,75,Math.PI/6,180,20,right));
			if(frame==10)
				hitboxes.add(new HitBox(playerX+40,playerY+60,75,Math.PI/6,180,20,right));
			length=75;
			
		}else if(attackNum==3) {
			//utilt
			//4-15
			if(frame==4)
				hitboxes.add(new HitBox(playerX-20,playerY+70,75,2*Math.PI/3,200,30,right));
			if(frame==5)
				hitboxes.add(new HitBox(playerX-20,playerY+60,75,2*Math.PI/3,200,30,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX-20,playerY+40,75,2*Math.PI/3,200,30,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX-20,playerY+20,75,Math.PI/2,200,20,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX-20,playerY,100,Math.PI/2,200,20,right));
			if(frame==9)
				hitboxes.add(new HitBox(playerX-15,playerY-10,100,Math.PI/2,200,20,right));
			if(frame==10)
				hitboxes.add(new HitBox(playerX,playerY-20,100,Math.PI/2,200,20,right));
			if(frame==11)
				hitboxes.add(new HitBox(playerX+20,playerY-20,100,Math.PI/2,200,20,right));
			if(frame==12)
				hitboxes.add(new HitBox(playerX+30,playerY-20,100,Math.PI/3,200,30,right));
			if(frame==13)
				hitboxes.add(new HitBox(playerX+50,playerY-20,100,Math.PI/3,200,30,right));
			if(frame==14)
				hitboxes.add(new HitBox(playerX+70,playerY-20,100,Math.PI/3,200,30,right));
			if(frame==15)
				hitboxes.add(new HitBox(playerX+75,playerY-15,100,Math.PI/3,200,30,right));
			length=55;
			
		}else if(attackNum==4) {
			//dtilt
			//4-8
			if(frame==4)
				hitboxes.add(new HitBox(playerX+80,playerY+100,75,2*Math.PI/3,150,30,right));
			if(frame==5)
				hitboxes.add(new HitBox(playerX+100,playerY+100,75,2*Math.PI/3,150,30,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX+110,playerY+100,75,2*Math.PI/3,150,30,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX+110,playerY+100,75,2*Math.PI/3,150,30,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX+110,playerY+100,75,2*Math.PI/3,150,30,right));
			length=65;
		}else if(attackNum==5) {
			//nair
			//5-12
			if(frame==5) {
				//bottom right
				hitboxes.add(new HitBox(playerX+80,playerY+100,75,0,200,30,right));
				//top right
				hitboxes.add(new HitBox(playerX+90,playerY,75,Math.PI/3,200,30,right));
				//bottom left
				hitboxes.add(new HitBox(playerX+30,playerY+100,75,Math.PI,200,30,right));
				//top left
				hitboxes.add(new HitBox(playerX+20,playerY,75,2*Math.PI/3,200,30,right));
				//center
				hitboxes.add(new HitBox(playerX+40,playerY+40,100,0,200,30,right));
			}
			if(frame==6) {
				//bottom right
				hitboxes.add(new HitBox(playerX+60,playerY+100,75,0,200,30,right));
				//top right
				hitboxes.add(new HitBox(playerX+110,playerY+10,75,Math.PI/3,200,30,right));
				//bottom left
				hitboxes.add(new HitBox(playerX+10,playerY+80,75,Math.PI,200,30,right));
				//top left
				hitboxes.add(new HitBox(playerX+30,playerY-10,75,2*Math.PI/3,200,30,right));
				//center
				hitboxes.add(new HitBox(playerX+40,playerY+40,100,0,200,30,right));
			}
			if(frame==7) {
				//center cuz i got lazy
				hitboxes.add(new HitBox(playerX+20,playerY+10,150,0,200,30,right));
			}
			if(frame==8)
				hitboxes.add(new HitBox(playerX+20,playerY+10,150,0,200,30,right));
			if(frame==9)
				hitboxes.add(new HitBox(playerX+20,playerY+10,150,0,200,30,right));
			if(frame==10)
				hitboxes.add(new HitBox(playerX+20,playerY+10,150,0,200,30,right));
			if(frame==11)
				hitboxes.add(new HitBox(playerX+20,playerY+10,150,0,200,30,right));
			if(frame==12)
				hitboxes.add(new HitBox(playerX+20,playerY+10,150,0,200,30,right));
			length=20;
		}else if(attackNum==6) {
			//fair
			//2-9
			if(frame==2)
				hitboxes.add(new HitBox(playerX+100,playerY-10,75,Math.PI/3,150,30,right));
			if(frame==3)
				hitboxes.add(new HitBox(playerX+110,playerY-10,75,Math.PI/3,150,30,right));
			if(frame==4)
				hitboxes.add(new HitBox(playerX+130,playerY,75,Math.PI/3,150,30,right));
			if(frame==5)
				hitboxes.add(new HitBox(playerX+140,playerY+25,75,Math.PI/3,150,30,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX+140,playerY+50,75,Math.PI/3,150,30,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX+130,playerY+70,75,Math.PI/3,150,30,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX+120,playerY+80,75,Math.PI/3,150,30,right));
			if(frame==9)
				hitboxes.add(new HitBox(playerX+110,playerY+80,75,Math.PI/3,150,30,right));
			length=65;
			
		}else if(attackNum==7) {
			//uair
			//4-10
			if(frame==4)
				hitboxes.add(new HitBox(playerX-10,playerY+10,75,2*Math.PI/3,150,30,right));
			if(frame==5)
				hitboxes.add(new HitBox(playerX-10,playerY,75,2*Math.PI/3,150,30,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX,playerY-10,75,Math.PI/2,150,30,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX+20,playerY-15,75,Math.PI/2,150,30,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX+50,playerY-20,75,Math.PI/2,150,30,right));
			if(frame==9)
				hitboxes.add(new HitBox(playerX+80,playerY-15,75,Math.PI/2,150,30,right));
			if(frame==10)
				hitboxes.add(new HitBox(playerX+100,playerY-5,75,Math.PI/2,150,30,right));
			length=60;	
		}else if(attackNum==8) {
			//bair
			//5-12
			if(frame==5)
				hitboxes.add(new HitBox(playerX-10,playerY+10,75,Math.PI,200,20,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX-15,playerY+30,75,Math.PI,200,20,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX-20,playerY+60,75,Math.PI,200,20,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX-20,playerY+80,75,Math.PI,200,20,right));
			if(frame==9)
				hitboxes.add(new HitBox(playerX-15,playerY+90,75,Math.PI,200,20,right));
			if(frame==10)
				hitboxes.add(new HitBox(playerX,playerY+90,75,Math.PI,200,20,right));
			if(frame==11)
				hitboxes.add(new HitBox(playerX+20,playerY+80,75,Math.PI,200,20,right));
			if(frame==12)
				hitboxes.add(new HitBox(playerX+40,playerY+70,75,Math.PI,200,20,right));
			length=60;
			
		}else if(attackNum==9) {
			//dair
			//4-8
			if(frame==4)
				hitboxes.add(new HitBox(playerX+120,playerY+80,75,3*Math.PI/2,200,20,right));
			if(frame==5)
				hitboxes.add(new HitBox(playerX+80,playerY+120,75,3*Math.PI/2,200,20,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX+40,playerY+125,75,3*Math.PI/2,200,20,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX+10,playerY+100,75,3*Math.PI/2,200,20,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX-5,playerY+40,75,3*Math.PI/2,200,20,right));
			length=60;
			
		}else if(attackNum==12) {
			//special
			//4-11
			//maybe implement charging?
			if(frame==4)
				hitboxes.add(new HitBox(playerX+50,playerY-5,75,Math.PI/6,350,50,right));
			if(frame==5)
				hitboxes.add(new HitBox(playerX+70,playerY-5,75,Math.PI/6,350,50,right));
			if(frame==6)
				hitboxes.add(new HitBox(playerX+110,playerY,75,Math.PI/6,350,50,right));
			if(frame==7)
				hitboxes.add(new HitBox(playerX+130,playerY+10,75,Math.PI/6,350,50,right));
			if(frame==8)
				hitboxes.add(new HitBox(playerX+140,playerY+30,75,Math.PI/6,350,50,right));
			if(frame==9)
				hitboxes.add(new HitBox(playerX+140,playerY+50,100,Math.PI/6,350,50,right));
			if(frame==10)
				hitboxes.add(new HitBox(playerX+140,playerY+70,100,Math.PI/6,350,50,right));
			length=70;
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
		if(action.contains("idle")||action.contains("jab")||action.contains("uTilt")||action.contains("special")) {
			hurtboxes.add(new Box(playerX+20,playerY+30,115,right));
			length=28;
		}
		if(action.contains("walk")||action.contains("dash")||action.contains("fTilt")) {
			hurtboxes.add(new Box(playerX+13,playerY+30,115,right));
			length=28;
		}
		if(action.contains("crouch")||action.contains("dTilt")) {
			hurtboxes.add(new Box(playerX+20,playerY+70,75,right));
			hurtboxes.add(new Box(playerX+60,playerY+70,75,right));
			length=65;
		}
		if((action.contains("jump")&&!action.contains("crouch"))||action.contains("Air")) {
			hurtboxes.add(new Box(playerX+30,playerY+20,115,right));
			length=28;
		}
		if(!right) {
			for(Box h:hurtboxes) {
				h.flipDaBox(playerX,length);
			}
		}
		return hurtboxes;
	}

	@Override
	public int framesBetweenAnimations(String attack) {
		// TODO Auto-generated method stub
		return 0;
	}

}

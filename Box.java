//Aidan's
public class Box {
	//called it box because it can be both a hitbox and a hurtbox
	private double x;
	private double y;
	//Did centerX and centerY because the drawing did x and y at the top left corner
	//change later if possible
	private double centerX;
	private double centerY;
	private double radius;
	public Box(double xCoor,double yCoor,double rad) {
		x=xCoor;
		y=yCoor;
		radius=rad;
	}
	public Box(double xCoor,double yCoor, double rad, boolean right) {
		x=xCoor;
		y=yCoor;
		radius=rad;
		if(right) {
			centerX=x+1.5*(radius/2)*Math.sqrt(2)/2;
		}else {
			centerX=x+1.5*(radius/2)*Math.sqrt(2)/2;
		}
		centerY=y+1.5*(radius/2)*Math.sqrt(2)/2;
	}
	public boolean hit(Box otherBox) {
		//System.out.println("centerX: "+centerX+" centerY: "+centerY);
		//System.out.println("Other coordinate: ("+otherBox.getCenterX()+", "+otherBox.getCenterY()+")");
		if(distanceFormula(centerX,centerY,otherBox.getCenterX(),otherBox.getCenterY())<=radius/2+otherBox.getRadius()/2) {
			return true;
		}else {
			return false;
		}
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getCenterX() {
		return centerX;
	}
	public double getCenterY() {
		return centerY;
	}
	public double getRadius() {
		return radius;
	}
	private double distanceFormula(double x1,double y1,double x2,double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	public boolean isHitBox() {
		return false;
	}
	public void flipDaBox(double xCoor,int length) {
		//System.out.println("center: "+(xCoor+length/2));
		//System.out.println("x: "+x);
		double distanceFromCenter=x-(xCoor+length/2);
		//System.out.println("distance from center: "+distanceFromCenter);
		x-=distanceFromCenter*2;
		centerX-=distanceFromCenter*2;
		//System.out.println("new x: "+x);
	}
	public void accountForVelocity(double xVel,double yVel) {
		x+=xVel;
		y+=yVel;
		centerX+=xVel;
		centerY+=yVel;
	}
}

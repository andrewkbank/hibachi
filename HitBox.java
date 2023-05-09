
public class HitBox extends Box {
	private double angle;
	private double magnitude;
	private int hitstun;
	public HitBox(double xCoor,double yCoor,double rad,double launchAngle,double launchSpeed,int stun) {
		super(xCoor, yCoor, rad);
		angle=launchAngle;
		magnitude=launchSpeed;
		hitstun=stun;
	}
	public HitBox(double xCoor,double yCoor,double rad,double launchAngle,double launchSpeed,int stun,boolean right) {
		super(xCoor, yCoor, rad, right);
		angle=launchAngle;
		if(!right) {
			angle=Math.PI-launchAngle;
		}
		magnitude=launchSpeed;
		hitstun=stun;
	}
	public double getAngle() {
		return angle;
	}
	public double getMagnitude() {
		return magnitude;
	}
	public boolean isHitBox() {
		return true;
	}
	public int hitstun() {
		return hitstun;
	}
	public void upDaKnockback(int charge) {
		charge/=2;
		magnitude+=charge*2;
	}

}

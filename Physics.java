public interface Physics {
	float gravity = 1;
	public final static float bounceThreshold = 10, lossThreshold = 15;
	int height = 144;
	int width = 144;
	public float getX();
	public void setX(float x);
	public float getY();
	public void setY(float y);
	public float getXVel();
	public void setXVel(float xVel);
	public float getYVel();
	public void setYVel(float yVel);
	public void dead();
	public default int getHeight() {return height;}
	public default int getWidth() {return width;}
	public default boolean getOnGround() {
		return Panel.getStage().isAPlatform((int)getX(), (int)getY()+getHeight());
	}
	public default int collidedWithSurfaceVerti() {
		double effY = getY();
		if(getYVel() >0 )
			effY += height;
		for(int d = 0; d <= (int) getYVel(); d++) {
			if( Panel.getStage().isAPlatform( (int) getX(), (int)effY+d )) {
				//System.out.println(getY());
				return d;
			}
		}
		for(int d = 0; d >= (int) getYVel(); d--) {
			if( Panel.getStage().isAPlatform( (int) getX(), (int)effY+d )) {
				//System.out.println(getY());
				return d;
			}
		}
		return -1;
	}
	public default int collidedWithSurfaceHori() {
		/*float effX = getX(); // effective x
		if(getXVel() >0 )
			effX += width;
		
		for(int d = 0; d < getXVel(); d++) {
			if( Panel.getStage().isAPlatform( (int)effX+1+d, (int) getY() ))
				return d;
		}*/
		//System.out.println(getX());
		if(getX()+getXVel()<0) {
			setX(0);
			return 0;
		}else if(getX()+getXVel()>850) {
			setX(850);
			return 0;
		}else
			return -1;
	}
	public boolean landed();
	public default float getSpeed() {
		return (float) Math.sqrt(getXVel()*getXVel() + getYVel() * getYVel());
	}
	public default void gravityIncrement() {
		setYVel(getYVel ()+gravity);
	}
	public default void incrementMovement(boolean hitstun) {
		//System.out.println("Yvel before physics: "+getYVel());
		if(getOnGround() == false) {
			setXVel((float) (getXVel()*.98));
			gravityIncrement();
 		}
		
		int yDist = collidedWithSurfaceVerti();
		if(yDist != -1)  {// handles bouncing, loss of control handled in character class
			setY(getY()+yDist);
			float floorMod = 0;//when being launched down, harder to bounce/lose
			if(getYVel() >= 0) { // 
				floorMod = 30;
			}
			//System.out.println("yvel: "+getYVel());
			//System.out.println("loss threshold for floor: "+(lossThreshold+floorMod));
			if(Math.abs(getYVel()) > lossThreshold + floorMod) {
				//TODO: end game
				dead();
				//System.out.println("break through floor/ceiling");
			}
			else if(Math.abs(getYVel())>bounceThreshold&&hitstun) {
				setYVel(-(float) (getYVel() * .8));
				//System.out.println("bounce off ground");
				//probably some dumb thing with being in hitstun
			}else {
				setYVel(0);
				//System.out.println("kajsldkjfl");
			}
			//if(hitstun) {
			//	System.out.println("yvel after in physics: "+getYVel());
			//	System.out.println("y: "+getY());
			//}
		}
		
		int xDist = collidedWithSurfaceHori();
		if(xDist != -1) {
			//System.out.println("hitstun? "+hitstun);
			//System.out.println("fast enough for kill? "+(Math.abs(getXVel()) > lossThreshold));
			//System.out.println("hitstun?" +hitstun);
			setX(getX()+xDist);
			if(Math.abs(getXVel()) > lossThreshold) {
				//TODO: end game
				dead();
				//System.out.println("break through walls");
			}
			else if(Math.abs(getXVel())>bounceThreshold&&hitstun) {
				setXVel(-(float) (getXVel() * .6));
				//System.out.println("bounce off wall");
			}else {
				setXVel(0);
			}
		}
		//System.out.println("yvel after physics: "+getYVel());
		setX( getX() + getXVel() );
		setY( getY() + getYVel() );
			
	}
	
}
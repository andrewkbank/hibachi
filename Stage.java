public class Stage {
	boolean[][] platform = new boolean[700][2000];
	public Stage() {
		for(int i = 0; i < platform[699].length; i++) {
			platform[699][i] = true;
			platform[0][i]=true;
		}
		//for(int i=0;i<700;i++) {
		//	platform[i][0]=true;
		//}
		
	}
	
	public boolean isAPlatform(int x, int y) {
		try {
		return platform[y][x];
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("x: "+x+" y: "+y);
			return true;
		}
	}
}
import java.awt.Graphics; 
import java.awt.Color; 
public class Shape  {
	
	public int pos_x; 
	public int pos_y; 
	
	public int width; 
	public int height; 
	
	private Color color = Color.BLUE; 
	
	//public int v_x; 
	//public static final int v_y = 20; 
	
	public int max_x; 
	public int max_y; 
	
	public Shape(int posX, int posY, int courtWidth, int courtHeight) {
		this.pos_x = posX; 
		this.pos_y = posY; 
		this.max_x = courtWidth; 
		this.max_y = courtHeight; 
	}
	
	public int[][] getShape() {
		return new int[1][1]; 
	}
	
	public Color getColor() {
		return null;  
	}
	
	public int[][] getRotatedShape() {
		return new int[1][1]; 
	}
	
	public void rotate() {
	}
	
	public void draw(Graphics g) {
	}
}

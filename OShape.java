import java.awt.*; 

public class OShape extends Shape implements Block{
	public int blockSize; 
	private Color color = Color.YELLOW; 
	public static final int INIT_X = 120;
	public static final int INIT_Y = 0;


	private int[][] shapeGrid; 
	//private int rotation; 

	public OShape(int courtWidth, int courtHeight, int blockSize) {
		super(INIT_X, INIT_Y, courtWidth, courtHeight);
		this.blockSize = blockSize; 
		//this.rotation = 0; 
		width = 2 * blockSize; 
		height = 2 * blockSize; 
		this.shapeGrid = new int [][]{
				{1, 1}, 
				{1, 1}};   
	}

	@Override 
	public int[][] getShape() {
		return shapeGrid;
	}

	@Override
	public int[][] getRotatedShape() {
		return shapeGrid; 
	}

	public void rotate() {
	}
	
	@Override 
	public Color getColor() {
		return this.color; 
	}
	
	@Override 
	public void draw(Graphics g) {
		int w = this.blockSize; 
		int h = this.blockSize;  
		for (int i = 0; i < shapeGrid.length; i++) {
			for (int j = 0; j < shapeGrid[i].length; j++) {
				if (shapeGrid[i][j] == 1) {
					g.setColor(this.color);
					g.fillRect(pos_x + (w*j), pos_y + (h*i), w, h);
					g.setColor(Color.BLACK);
					g.drawRect(pos_x + (w*j), pos_y + (h*i), w, h);
				}
			}
		}
	}
}

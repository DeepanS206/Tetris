import java.awt.*; 

public class IShape extends Shape implements Block{
	public int blockSize; 
	private Color color = Color.CYAN; 
	public static final int INIT_X = 120;
	public static final int INIT_Y = 0;

	
	private int[][] shapeGrid; 
	private int rotation; 
	
	public IShape(int courtWidth, int courtHeight, int blockSize) {
		super(INIT_X, INIT_Y, courtWidth, courtHeight);
		this.blockSize = blockSize; 
		this.rotation = 0; 
		width = 1 * blockSize; 
		height = 4 * blockSize; 
		this.shapeGrid = new int [][]{
				{1}, 
				{1}, 
				{1}, 
				{1}};   
	}
	
	@Override 
	public int[][] getShape() {
		return shapeGrid;
	}
	
	@Override
	public int[][] getRotatedShape() {
		int a =  rotation + 1;
		if (rotation > 1) {
			rotation = 0; 
		}
		if (a == 0) {
			return new int [][]{
				{1}, 
				{1}, 
				{1}, 
				{1}}; 
		}
		
		else {
			return new int[][]{
				{1, 1, 1, 1}}; 
			}
	}
	
	public void rotate() {
		rotation = rotation+=1;
		if (rotation > 1) {
			rotation = 0; 
		}
		if (rotation == 0) {
			this.width = 1 * blockSize; 
			this.height = 4 * blockSize; 
			this.shapeGrid = new int [][]{
				{1}, 
				{1}, 
				{1}, 
				{1}}; 
		}
		
		if (rotation == 1) {
			this.width = 4 * blockSize; 
			this.height = 1 * blockSize; 
			//pos_x = pos_x - ((height - 1) * blockSize); 
			this.shapeGrid = new int[][]{
				{1, 1, 1, 1}}; 
			}
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
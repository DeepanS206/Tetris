import java.awt.*; 
public class TShape extends Shape implements Block {
	public int blockSize; 
	private Color color = new Color(148, 0 ,211); 
	public static final int INIT_X = 120;
	public static final int INIT_Y = 0;


	private int[][] shapeGrid; 
	private int rotation; 

	public TShape(int courtWidth, int courtHeight, int blockSize) {
		super(INIT_X, INIT_Y, courtWidth, courtHeight);
		this.blockSize = blockSize; 
		this.rotation = 0; 
		width = 3 * blockSize; 
		height = 2 * blockSize; 
		this.shapeGrid = new int [][]{
				{1, 1, 1}, 
				{0, 1, 0}};   
	}

	@Override 
	public int[][] getShape() {
		return shapeGrid;
	}

	@Override
	public int[][] getRotatedShape() {
		int a = rotation + 1;
		if (a > 3) {
			a = 0; 
		}
		if (a == 0) {
			return new int [][]{
					{1, 1, 1}, 
					{0, 1, 0}};
		}
		else if (a == 1) {
			return new int [][]{
					{0, 1},
					{1, 1}, 
					{0, 1}}; 
		}
		else if (a == 2) {
			return new int[][]{
					{0, 1, 0}, 
					{1, 1, 1}}; 
		}
		else {
			return new int [][]{
					{1, 0},
					{1, 1},
					{1, 0}}; 
		}
	}

	public void rotate() {
		rotation+=1;
		if (rotation > 3) {
			rotation = 0; 
		}
		if (rotation == 0) {
			width = 3 * blockSize; 
			height = 2 * blockSize; 
			this.shapeGrid = new int [][]{
					{1, 1, 1}, 
					{0, 1, 0}};   
		}
		if (rotation == 1) {
			this.width = 2 * blockSize; 
			this.height = 3 * blockSize; 
			this.shapeGrid = new int [][]{
					{0, 1},
					{1, 1}, 
					{0, 1}}; 
		}

		if (rotation == 2) {
			this.width = 3 * blockSize; 
			this.height = 2 * blockSize; 
			//pos_x = pos_x - ((height - 1) * blockSize); 
			this.shapeGrid = new int[][]{
					{0, 1, 0}, 
					{1, 1, 1}}; 
		}
		if (rotation == 3) {
			this.width = 2 * blockSize; 
			this.height = 3 * blockSize; 
			this.shapeGrid = new int [][]{
					{1, 0},
					{1, 1},
					{1, 0}}; 
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


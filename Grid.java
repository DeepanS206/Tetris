import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList; 


public class Grid {
	private int[][] grid; 
	private Color[][] colorMap; 
	private int blockSize; 
	private int courtWidth; 
	public int score; 
	private int courtHeight; 


	public Grid (int courtWidth, int courtHeight, int blockSize) {
		this.courtWidth = courtWidth; 
		this.courtHeight = courtHeight; 
		this.blockSize = blockSize; 
		int j = courtWidth / blockSize; 
		int i = courtHeight / blockSize; 
		this.grid = new int[i][j]; 
		this.colorMap = new Color[i][j]; 
	}

	public int getI(int posY) {
		//System.out.println(blockSize); 
		return posY / this.blockSize; 
	}

	public int getJ(int posX) {
		return posX / this.blockSize; 
	}

	public boolean checkForBounds(int i, int i2) {
		if (i >= 0 && i < grid.length && i2 >=0 && i2 < grid[0].length) {
			return true; 
		}
		return false; 
	}

	//posX and posY are the coordinates after shifting
	public boolean checkCollision (Shape s, int posX, int posY, Direction.Type direction) {
		int [][] block = s.getShape(); 
		int length = block.length; 
		int width = block[0].length; 
		switch(direction) {
		case DOWN:
			if (s.pos_y + s.height != courtHeight) {
				int indexI3 = getI(posY); 
				int indexJ3 = getJ(posX); 
				int endIndexI7 = indexI3 + length; 
				int endIndexJ = indexJ3 + width; 
				int counterI3 = 0, counterJ3 = 0; 
				if(checkForBounds(indexI3, indexJ3)) {
					//	System.out.println("----------------------"); 
					for (int i = indexI3; i < endIndexI7; i++) {
						for (int j = indexJ3; j < endIndexJ; j++) {
							//	System.out.println(counterI3 + ", " + counterJ3); 
							if(grid[i][j] == 1 && block[counterI3][counterJ3] == 1) {
								return false; 
							}
							counterJ3++; 
						}
						counterJ3 = 0; 
						counterI3++; 
					}
					return true; 
				} else {
					return false; 
				}
			} else {
				return false; 
			}
		case LEFT:
			int indexI = getI(posY); 
			int indexJ = getJ(posX); 
			int endIndexI = indexI + length; 
			int endIndexJ7 = indexJ + width; 
			int counterI = 0, counterJ = 0; 
			if(checkForBounds(indexI, indexJ)) {
				//	System.out.println("----------------------"); 
				for (int i = indexI; i < endIndexI; i++) {
					for (int j = indexJ; j < endIndexJ7; j++) {
						//	System.out.println(counterI3 + ", " + counterJ3); 
						if(grid[i][j] == 1 && block[counterI][counterJ] == 1) {
							return false; 
						}
						counterJ++; 
					}
					counterJ = 0; 
					counterI++; 
				}
				return true; 
			} else {
				return false; 
			}
		case RIGHT:
			if (s.pos_x + s.width < courtWidth) {
				int indexI3 = getI(posY); 
				int indexJ3 = getJ(posX); 
				int endIndexI7 = indexI3 + length; 
				int endIndexJ = indexJ3 + width; 
				int counterI3 = 0, counterJ3 = 0; 
				if(checkForBounds(indexI3, indexJ3)) {
					//	System.out.println("----------------------"); 
					for (int i = indexI3; i < endIndexI7; i++) {
						for (int j = indexJ3; j < endIndexJ; j++) {
							//	System.out.println(counterI3 + ", " + counterJ3); 
							if(grid[i][j] == 1 && block[counterI3][counterJ3] == 1) {
								return false; 
							}
							counterJ3++; 
						}
						counterJ3 = 0; 
						counterI3++; 
					}
					return true; 
				} else {
					return false; 
				}
			} else {
				return false; 
			}
		case UP:
			int[][] rotated = s.getRotatedShape(); 
			int indexI4 = getI(posY); 
			int indexJ4 = getJ(posX);
			int endIndexI4 = indexI4 + rotated.length; 
			int endIndexJ4 = indexJ4 + rotated[0].length;  
			int counterI4 = 0, counterJ4 = 0;  
			if(checkForBounds(endIndexI4, endIndexJ4)) {
				for (int i = indexI4; i < endIndexI4; i++) {
					for (int j = indexJ4; j < endIndexJ4; j++) {
						if(grid[i][j] == 1 && rotated[counterI4][counterJ4] == 1) {
							//							s.rotate();
							//							s.rotate();
							//							s.rotate(); 
							return false;
						}
						counterJ4++; 
					}
					counterJ4 = 0; 
					counterI4++; 
				}
				return true; 
			} else {
				return false; 
			}
		default:
			break;

		}
		return false; 
	}

	//function checks of piece can move one block to the right
	public boolean canMoveRight(int posX, int posY, Shape s) {
		int newPosX = posX + blockSize; 
		if(checkCollision(s, newPosX, posY, Direction.Type.RIGHT) && 
				posX + s.width < courtWidth) {
			return true; 
		}
		return false; 
	}

	public boolean canMoveLeft(int posX, int posY, Shape s) {
		int newPosX = posX - blockSize; 
		if(checkCollision(s, newPosX, posY, Direction.Type.LEFT) &&
				posX > 0) {
			return true; 
		}
		return false; 
	}

	public boolean canMoveDown(int posX, int posY, Shape s) {
		int newPosY = posY + blockSize; 
		if(checkCollision(s, posX, newPosY, Direction.Type.DOWN) &&
				posY + s.height < courtHeight) {
			return true; 
		}
		return false; 
	}

	public boolean canRotation(int posX, int posY, Shape s) {
		return checkCollision(s, posX, posY, Direction.Type.UP); 
	}

	public void place(Shape s, int posX, int posY) {
		//System.out.println(score); 
		int[][] block = s.getShape();
		Color color = s.getColor(); 
		int length = block.length; 
		int width = block[0].length; 
		int indexI = getI(posY); 
		int indexJ = getJ(posX); 
		int counterI = 0, counterJ = 0; 
		for (int i = indexI; i < indexI + length; i++) {
			for(int j = indexJ; j < indexJ + width; j++) {
				if (block[counterI][counterJ] == 1) {
					grid[i][j] = block[counterI][counterJ]; 
					colorMap[i][j] = color;  
				}
				counterJ++; 
			}
			counterJ = 0; 
			counterI++; 
		}

		//System.out.println("---------------------------------------"); 

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				//System.out.print(" " + grid[i][j]); 
			}
			//System.out.println(""); 
		}
	}

	public int getScore() {
		return score; 
	}

	public boolean checkForLose() {
		for (int j = 0; j < grid[0].length; j++) {
			if (grid[0][j] == 1) {
				return true; 
			}
		}
		return false; 
	}

	public void checkForRows() {
		LinkedList<Integer> remove = new LinkedList<Integer>(); 
		for (int i = 0; i < grid.length; i++) {
			Boolean check = true;  
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 0) {
					check = false; 
					break; 
				}
			}
			if (check)
				remove.add(i); 
		}

		if (remove.size() == 1) {
			score+=40; 
		}
		else if (remove.size() == 2) {
			score+=100; 
		}
		else if (remove.size() == 3) {
			score+=300; 
		}
		else if (remove.size() == 4) {
			score+=1200; 
		}

		for (int i : remove) {
			//System.out.println(i); 
			removeRows(i); 
		}
	}

	private void removeRows(int k) {
		//System.out.println("---------------------------");
		for(int i = k - 1; i > -1; i--) {
			for(int j = 0; j < grid[i].length; j++) {
				//System.out.println(grid[i+1][j]); 
				grid[i+1][j] = grid[i][j];
				colorMap[i+1][j] = colorMap[i][j]; 
				//System.out.println(grid[i+1][j]); 
			}
		}
	}

	public void draw(Graphics g) {
		int w = blockSize; 
		int h = blockSize; 
		//System.out.println("---------------------------------------"); 
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				//System.out.print(" " + grid[i][j]); 
				if (grid[i][j] == 1) {
					int posX = j * blockSize; 
					int posY = i * blockSize;
					g.setColor(colorMap[i][j]); 
					g.fillRect(posX,  posY, w, h);
					g.setColor(Color.BLACK);
					g.drawRect(posX,  posY, w, h);
				}
			}
			//System.out.println(""); 
		}
	}

}

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; 
import javax.swing.*; 


@SuppressWarnings("serial")
public class TetrisCourt extends JPanel {
	private Shape shape; 
	private Grid grid;
	private ArrayList<Integer> pieces; 
	
	public int score; 
	
	private Timer timer; 
	
	public boolean playing = false; 
	public boolean paused = false; 
	private JLabel status; 
	
	public static final int COURT_WIDTH = 300;
	public static final int COURT_HEIGHT = 600;
	public static final int blockSize = 30;
	public static final int Interval = 400; 

	public TetrisCourt(JLabel status) {
	
		setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		
		this.timer = new Timer(Interval, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick(); 
			}
		});
		timer.start();
		
		setFocusable(true); 
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					move(Direction.Type.LEFT); 
					repaint(); 
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					move(Direction.Type.RIGHT); 
					repaint(); 
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					move(Direction.Type.DOWN); 
					repaint(); 
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					move(Direction.Type.UP); 
					repaint(); 
				}
			}
		}); 
		
		this.status = status; 
		
	}
	
	public void reset() {

		grid = new Grid(COURT_WIDTH, COURT_HEIGHT, blockSize ); 
		
		pieces = fill(); 
		
		int i = (int) Math.round(Math.random() * pieces.size() - 1); 
		if (i < 0) {
			i = 0; 
		}
		
		int nextPiece = pieces.get(i); 
		
		if (nextPiece == 0) {
			this.shape = new IShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 1) {
			this.shape = new OShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 2) {
			this.shape = new ZShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		} 
		else if (nextPiece == 3) {
			this.shape = new TShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 4) {
			this.shape = new LShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 5) {
			this.shape = new JShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 6) {
			this.shape = new SShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		
		
		//shape = new OShape(COURT_WIDTH, COURT_HEIGHT, blockSize); 
		
		score = grid.getScore();  

		playing = true;
		paused = false; 
		timer.start(); 
		status.setText("Score: " + Integer.toString(score));

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}
	
	public void pause() {
		paused = !paused; 
		if (paused) {
			timer.stop(); 
		}
		else {
			timer.start(); 
		}
		requestFocusInWindow();
	}
	
	public void requestFocus() {
		requestFocusInWindow(); 
	}
	
	public int getScore() {
		return score; 
	}
	
	public ArrayList<Integer> fill() {
		ArrayList<Integer> arr = new ArrayList<Integer>(); 
		for (int i = 0; i < 7; i++) {
			int counter = 0; 
			while (counter < 4) {
				arr.add(i); 
				counter++; 
			}
		}
		return arr; 
	}

	public void move(Direction.Type direction) {
		if (playing && !paused) {
			switch(direction) {
			case DOWN: moveDown(); 
			break;
			case LEFT:
				if (grid.canMoveLeft(shape.pos_x, shape.pos_y, shape) && playing) {
					shape.pos_x -= blockSize; 
					clip(); 
				}
				break;
			case RIGHT:
				if (grid.canMoveRight(shape.pos_x, shape.pos_y, shape) && playing) {
					shape.pos_x += blockSize; 
					clip(); 
				}
				break;
			case UP:
				if (grid.canRotation(shape.pos_x, shape.pos_y, shape) && playing) {
					shape.rotate();
				}
				break;
			default:
				break;
			}
		}
	}

	private void moveDown() {
		if (grid.canMoveDown(shape.pos_x, shape.pos_y, shape)) {
			shape.pos_y += blockSize;
			//clip();
		}
		else {
			grid.place(shape, shape.pos_x, shape.pos_y); 
			//System.out.println(score);
			grid.checkForRows();
			score = grid.getScore(); 
			//System.out.println(score); 
			status.setText("Score: " + score);
			GenerateRandom(); 
		}
	}
	
	public void GenerateRandom() {
		int i = (int) Math.round(Math.random() * pieces.size() - 1); 
		if (i < 0) {
			i = 0; 
		}
		if (pieces.isEmpty()) {
			pieces = fill(); 
		}
		int nextPiece = pieces.remove(i);
		if (nextPiece == 0) {
			this.shape = new OShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 1) {
			this.shape = new TShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 2) {
			this.shape = new SShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		} 
		else if (nextPiece == 3) {
			this.shape = new ZShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 4) {
			this.shape = new LShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 5) {
			this.shape = new JShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		else if (nextPiece == 6) {
			this.shape = new IShape(COURT_WIDTH, COURT_HEIGHT, blockSize);
		}
		
		//System.out.println("ON TO THE NEXT ONE"); 
	}

	public void clip() {
		if (shape.pos_x < 0) 
			shape.pos_x = 0;
		else if (shape.pos_x + shape.width > shape.max_x) 
			shape.pos_x = shape.max_x - shape.width;

		if (shape.pos_y < 0) 
			shape.pos_y = 0;
		else if (shape.pos_y + shape.height > shape.max_y) 
			shape.pos_y = shape.max_y - shape.height;
		
	}
	
	public void tick() {
		if (playing) {
			move(Direction.Type.DOWN);
			
			if (grid.checkForLose()) {
				int s = grid.getScore(); 
				playing = false; 
				status.setText("You Lose!  Score: " + s); 
			}
	
			repaint(); 
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		shape.draw(g);
		grid.draw(g); 
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}

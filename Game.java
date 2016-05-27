import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game implements Runnable {
	public void run() {
		final JFrame frame = new JFrame("Tetris"); 
		frame.setLocation(300, 300);
		
		final JPanel status_panel = new JPanel(); 
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);
		
		final TetrisCourt game = new TetrisCourt(status); 
		frame.add(game, BorderLayout.CENTER);
		
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);
		
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.reset();
			}
		});
		final JButton pause = new JButton("Pause"); 
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.pause();
			}
		}); 
		final JButton instruction = new JButton("Instructions"); 
		instruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.pause();
				JOptionPane.showMessageDialog(frame, "Use the arrow keys "
						+ "to move and rotate pieces (The rotations "
						+ "are based of the upper left point of the block). \n"
						+ "Try to  complete rows "
						+ "of blocks at the bottom (with no gaps). \n"
						+ "Each complete row will disappear, "
						+ "giving you more room to as you continue playing. \n"
						+ "Your game is over if the falling"
						+ " blocks pile up to the top of the playing area. \n"
						+ " \n "
						+ "1 row : 40 points            Up Arrow - Rotate \n"
						+ "2 rows: 100 points           Down Arrow - Faster Descent\n"
						+ "3 rows: 300 points           Left Arrow - Move Left\n"
						+ "4 rows : 1200 points         Right Arrow - Move Right\n");
				game.pause();
			}
		});
		control_panel.add(instruction);  
		control_panel.add(reset);
		control_panel.add(pause);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		game.reset();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}

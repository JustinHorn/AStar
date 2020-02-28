package aStar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.Timer;

/** Simple java a* project
 * press small s to start search
 * press small n to reset everything 
 * dragg on fields to block them for the algorithm
 * click on field to unblock
 *   */
public class Main extends JFrame{

	final int dim = 100;
	Szenario sz;
	AStarPanel[][] aPanel;
	AStar astar;
	private boolean mousePressed = false; // for dragg effect
	private boolean over = false;
	private int time = 0;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		super("A*");
		sz = Szenario.getRandomSzenario(dim);
		astar =  new AStar(sz);
		aPanel = new AStarPanel[sz.getX()][sz.getY()];
		buildFrame();
		reset();
	}
	
	
	private void buildFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setBounds(width/4, height/4, width/2,height/2);
		setLayout(new GridLayout(sz.getX(),sz.getY()));
		for (int i = 0; i < aPanel.length; i++) {
			for (int j = 0; j < aPanel[i].length; j++) {
				aPanel[i][j] = new AStarPanel(sz.getField()[i][j]);
				aPanel[i][j].addMouseListener(getMouseListener(i,j));
				add(aPanel[i][j]);
			}
		}
		setVisible(true);
		setEnabled(true);
		addKeyListener(getWindowKeyListener());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private MouseListener getMouseListener(final int i,final int j) {
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				mousePressed = true;
				setBlocked();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(mousePressed) {
					setBlocked();
				}
			}
			
			private void setBlocked() {
				aPanel[i][j].setType(Cell.BLOCKED);
				sz.getField()[i][j] = Cell.BLOCKED;
				aPanel[i][j].repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				aPanel[i][j].setType(Cell.NORMAL);
				sz.getField()[i][j] = Cell.NORMAL;
				aPanel[i][j].repaint();
			}
		};
	}

	private KeyListener getWindowKeyListener() {
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				switch(e.getKeyChar()) {
					case 's':findPath();break;
					case 'n': reset();break;
					default: break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		};
	}


	private void reset() {	
		sz.reset();
		astar.reset();
		Stream.of(aPanel).parallel().forEach(a -> Arrays.stream(a).forEach(b -> b.setType(Cell.NORMAL)));
		aPanel[sz.getGoal().x][sz.getGoal().y].setType(Cell.GOAL);
		aPanel[sz.getStart().x][sz.getStart().y].setType(Cell.START);
		over = false;
	}

	private void findPath() {
		Timer t = new Timer(time,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				over = astar.oneIteration();
				a_look();
			}
		});
		t.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(over) {
					t.stop();
				}
			}
		});
		t.start();
		t.setRepeats(true);
	}
	
	private void a_look() {
		Iterator i = astar.known_nodes.iterator();
		while(i.hasNext()) {
			Node n = (Node) i.next();
			if (aPanel[n.x][n.y].getType() != Cell.DISCOVRED) {
				aPanel[n.x][n.y].setType(Cell.DISCOVRED);	
				aPanel[n.x][n.y].repaint();
			}
		}
		i = astar.closed_nodes.iterator();
		while(i.hasNext()) {
			Node n = (Node) i.next();
			if (aPanel[n.x][n.y].getType() != Cell.CLOSED) {
				aPanel[n.x][n.y].setType(Cell.CLOSED);	
				aPanel[n.x][n.y].repaint();
			}
		}
	
	}


	


}

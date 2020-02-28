package aStar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class AStarPanel extends JPanel {

	private Cell type;

	
	public AStarPanel(Cell type) {
		setType(type);
	}

	public AStarPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public AStarPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public AStarPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	    int w = this.getWidth();
	    int h =this.getHeight();
		
	    g2.setStroke(new BasicStroke(1));
	    g2.drawRect(0, 0, w, h); 

	}
	
	public Cell getType() {
		return type;
	}

	public void setType(Cell type) {
		this.type = type;
		setBackground(type.getColor());
		
	}

}

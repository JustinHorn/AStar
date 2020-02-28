package aStar;

import java.awt.Color;

enum Cell {
	
	
	NORMAL(Color.WHITE),BLOCKED(Color.BLACK),DISCOVRED(Color.GREEN),CLOSED(Color.RED),START(Color.RED),GOAL(Color.PINK);
	
	private Color color;
	
	private Cell(Color color) {
		this.color= color;
	}
	
	public Color getColor() {
		return color;
	}

}
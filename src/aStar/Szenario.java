package aStar;

import java.util.Arrays;

public class Szenario {

	private Node start;
	private Node goal;
	private Cell[][] field;

	
	public Szenario(Node start, Node goal, Cell[][] field) {
		this.setStart(start);
		this.setGoal(goal);
		this.setField(field);
	}
	
	public static Szenario getRandomSzenario(int dim) {
		Cell[][] field = new Cell[dim][dim];
		Arrays.stream(field).forEach(a -> Arrays.fill(a, Cell.NORMAL));
		Node start = new Node();
		Node goal = new Node();
		start.random(dim,dim);
		goal.random(dim,dim,start);
		
		return new Szenario(start, goal, field);
	}
	
	public void reset() {
		Arrays.stream(field).forEach(a -> Arrays.fill(a, Cell.NORMAL));
		start.random(getX(),getY());
		goal.random(getX(),getY(),start.getPoint());
	}
	

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getGoal() {
		return goal;
	}

	public void setGoal(Node goal) {
		this.goal = goal;
	}

	public Cell[][] getField() {
		return field;
	}

	public void setField(Cell[][] field) {
		this.field = field;
	}

	public int getX() {
		return field.length;
	}

	public int getY() {
		return field[0].length;
	}

}

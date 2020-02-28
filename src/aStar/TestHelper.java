package aStar;

import java.util.HashSet;

public class TestHelper {

	public TestHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public static Szenario getOneLineSzenario(int l) {
		Cell[][] field = new Cell[l][1];
		for (int i = 0; i < field.length; i++) {
			field[i][0] = Cell.NORMAL;
		}
		Node start = new Node(0,0);
		Node goal = new Node(l-1,0);
		return new Szenario(start,goal,field);
	}
}

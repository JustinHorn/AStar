package aStar;

public class Node extends Point {

	Node discoverer;
	
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node(java.awt.Point p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public Node(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Node(int x, int y,Node n) {
		super(x, y);
		this.discoverer = n;
	}
	
	public Point getPoint() {
		return new Point(x,y);
	}
	
	/** Is true when obj is Point or Node && x==y & y ==y*/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point new_point = (Point) obj;
			return new_point.equals(getPoint());
		} else if (obj instanceof Node) {
			Node new_node = (Node) obj;
			return new_node.getPoint().equals(getPoint());
		}
		return false;
	}

}

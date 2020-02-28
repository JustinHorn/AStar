package aStar;

public class Point extends java.awt.Point {

	public Point() {
		// TODO Auto-generated constructor stub
	}

	public Point(java.awt.Point p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public Point(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void random(int dim1, int dim2) {
		x = (int) (Math.random() * dim1);
		y = (int) (Math.random() * dim2);
	}

	public void random(int dim1, int dim2, Point different) {
		x = (int) (Math.random() * dim1);
		y = (int) (Math.random() * dim2);
		if (this == different) {
			if (dim1 > 1 || dim2 > 1) {
				random(dim1, dim2, different);
			} else {
				throw new RuntimeException("Points cant be different!");
			}
		}
	}

	public boolean inbounds(int dimx, int dimy) {
		return x >= 0 && y >= 0 && x < dimx && y < dimy;
	}
	

}

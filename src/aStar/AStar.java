package aStar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

public class AStar {

	HashSet<Node> known_nodes;
	HashSet<Node> closed_nodes;
	private Szenario szenario;
	
	
	public AStar(Szenario sz) {
		setSzenario(sz);
	}
	
	public Szenario getSzenario() {
		return szenario;
	}

	public void setSzenario(Szenario sz) {
		this.szenario = sz;
		reset();
	}
	
	public void reset() {
		known_nodes = new HashSet<Node>();
		closed_nodes = new HashSet<Node>();
		known_nodes.add(szenario.getStart());
	}
	
	public boolean oneIteration() {
		
		if ( known_nodes.isEmpty())
		{
			return false;
		}		
		Node node = getNodeWithHeighestValue(known_nodes);
		if (node.equals(szenario.getGoal())) {
			return true;
		} else {
			known_nodes.addAll(getNewKnownNodes(node));
			closed_nodes.add(node);
			known_nodes.remove(node);
			return false;
		}
	}
	
	private Node getNodeWithHeighestValue(HashSet<Node> known_nodes2) {
		Node n = known_nodes.stream().reduce((a,b) -> getValue(a)<=getValue(b)?b:a).get();
		return n;
	}
	
	private int getValue(Node x) {
		return -(Math.abs(szenario.getGoal().x -x.x) + Math.abs(szenario.getGoal().y-x.y));
	}
	
	
	private ArrayList<Node> getNewKnownNodes(Node n) {
		int x = n.x;
		int y = n.y;
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node(x-1,y,n));
		nodes.add(new Node(x+1,y,n));
		nodes.add(new Node(x,y-1,n));
		nodes.add(new Node(x,y+1,n));
		nodes = nodes.parallelStream().filter(node -> !isNodeOutBoundsOrVorbidden(node)).collect(Collectors.toCollection(ArrayList::new));
		return nodes;
	}
	
	private boolean isNodeOutBoundsOrVorbidden(Node n) {
		return !n.inbounds(szenario.getX(),szenario.getY()) || szenario.getField()[n.x][n.y] == Cell.BLOCKED || closed_nodes.contains(n);
	}

	
}

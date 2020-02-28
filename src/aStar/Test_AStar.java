package aStar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class Test_AStar {

	@Test
	void test_iteration() {
		Szenario sz = TestHelper.getOneLineSzenario(2);
		AStar aStar = new AStar(sz);
		HashSet<Node> k_n = new HashSet<Node>();
		HashSet<Node> c_n = new HashSet<Node>();

		k_n.add(sz.getStart());
		
		assertEquals(k_n, aStar.known_nodes);
		
		assertEquals(false, aStar.oneIteration());
		
		k_n.remove(sz.getStart());
		k_n.add(sz.getGoal());
		c_n.add(sz.getStart());
		assertEquals(k_n, aStar.known_nodes);
		assertEquals(c_n, aStar.closed_nodes);
		
		assertEquals(true, aStar.oneIteration());



	}

}

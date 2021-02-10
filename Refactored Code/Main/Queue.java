package Main;

import java.util.Vector;
 
public class Queue {
	private Vector<Party> v;
	
	/** Queue() creates a new queue. */
	public Queue() {
		v = new Vector<Party>();
	}
	
	public Object next() {
		return v.remove(0);
	}

	public void add(Party o) {
		v.addElement(o);
	}
	
	public boolean hasMoreElements() {
		return !v.isEmpty();
	}

	public Vector<Party> asVector() {
		return v;
	}
}
/* Queue.java Version $Id$ Revisions: $Log$ */

import java.util.Vector;

public class Queue {
    private final Vector<Party> v;

    /** Queue() <p> creates a new queue. */
    public Queue() {
        v = new Vector<Party>();
    }

    public Party next() {
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

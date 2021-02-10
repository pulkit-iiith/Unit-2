package Main;
<<<<<<< HEAD

/* Container that holds bowlers */
=======
>>>>>>> main

import java.util.*;

public class Party {
	/** Vector of bowlers in this party. */	
    private Vector<? extends Bowler> myBowlers;
	
	/**
	 * Constructor for a Party.
	 * 
	 * @param bowlers	Vector of bowlers that are in this party
	 */
    public Party( Vector<? extends Bowler> bowlers ) {
		myBowlers = new Vector <Bowler>(bowlers);
    }

	/**
	 * Accessor for members in this party.
	 * 
	 * @return 	A vector of the bowlers in this party
	 */
    public Vector<? extends Bowler> getMembers() {
		return myBowlers;
    }
}
package Main;

/* Class that is the outer container for the bowling sim. */

public class Alley {
    public ControlDesk controldesk;

    public Alley(int numLanes) {
        controldesk = new ControlDesk(numLanes);
    }

    public ControlDesk getControlDesk() {
        return controldesk;
    }
}
    

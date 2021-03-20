package Main;

public class PinsetterEvent {
    private final boolean[] pinsStillStanding;
    private final boolean foulCommited;
    private final int throwNumber;
    private final int pinsDownThisThrow;

    /**
     * PinsetterEvent()
     * <p>
     * creates a new pinsetter event.
     *
     * @pre none
     * @post the object has been initialized
     */
    public PinsetterEvent(boolean[] ps, boolean foul, int tn, int pinsDownThisThrow) {
        pinsStillStanding = new boolean[10];

        System.arraycopy(ps, 0, pinsStillStanding, 0, 10);

        foulCommited = foul;
        throwNumber = tn;
        this.pinsDownThisThrow = pinsDownThisThrow;
    }

    /**
     * PinKnockedDown()
     * <p>
     * check if a pin has been knocked down.
     *
     * @return true if pin [i] has been knocked down
     */
    public boolean pinKnockedDown(int i) {
        return !pinsStillStanding[i];
    }

    /**
     * PinsDownOnThisThrow().
     *
     * @return the number of pins knocked down associated with this event
     */
    public int pinsDownOnThisThrow() {
        return pinsDownThisThrow;
    }

    /**
     * TotalPinsDown().
     *
     * @return the total number of pins down for pinsetter that generated the event
     */
    public int totalPinsDown() {
        int count = 0;

        for (int i = 0; i <= 9; i++) {
            if (pinKnockedDown(i)) {
                count++;
            }
        }

        return count;
    }

    /**
     * IsFoulCommited().
     *
     * @return true if a foul was commited on the lane, false otherwise
     */
    public boolean isFoulCommited() {
        return foulCommited;
    }

    /**
     * GetThrowNumber().
     *
     * @return current number of throws taken on this lane after last reset
     */
    public int getThrowNumber() {
        return throwNumber;
    }
}

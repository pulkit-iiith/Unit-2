package Main;

/** Interface for classes that observe control desk events. */

public interface ControlDeskObserver {
    void receiveControlDeskEvent(ControlDeskEvent ce);
}

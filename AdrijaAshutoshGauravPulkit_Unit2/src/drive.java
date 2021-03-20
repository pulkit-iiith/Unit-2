import Main.ControlDesk;
import Views.ControlDeskView;
import javax.swing.JOptionPane;

public class drive {

	public static void main(String[] args) {
		String input1,input2;
		
		input1 = JOptionPane.showInputDialog("Number of Lanes");
		
		input2 = JOptionPane.showInputDialog("Maximum Players");

		final int NUM_LANES = Integer.parseInt(input1);
		final int MAX_PATRONS_PER_ALLEY = Integer.parseInt(input2);

		// Create a control desk and run the GUI
		ControlDesk controlDesk = new ControlDesk(NUM_LANES);
		ControlDeskView cdv = new ControlDeskView( controlDesk, MAX_PATRONS_PER_ALLEY);
		controlDesk.addObserver(cdv);
	}
}

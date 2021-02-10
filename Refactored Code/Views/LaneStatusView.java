package Views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Main.Bowler;
import Main.Lane;
import Main.Pinsetter;
import Main.PinsetterEvent;

public class LaneStatusView implements ActionListener, Observer {
	private JPanel jp;
	private JLabel curBowler;
	private JLabel pinsDown;
	private JButton viewLane;
	private JButton viewPinSetter;
	private JButton maintenance;
	private PinSetterView psv;
	private LaneView lv;
	private Lane lane;
	int laneNum;

	boolean laneShowing;
	boolean psShowing;

	public LaneStatusView(Lane lane, int laneNum ) {
		this.lane = lane;
		this.laneNum = laneNum;

		laneShowing=false;
		psShowing=false;
		Pinsetter ps = lane.getPinsetter();
		psv = new PinSetterView( ps, laneNum );
		ps.addObserver(this);
		lv = new LaneView( lane, laneNum );
		lane.addObserver(lv);
		jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JLabel cLabel = new JLabel( "Now Bowling: " );
		curBowler = new JLabel( "(no one)" );
		JLabel fLabel = new JLabel( "Foul: " );
		JLabel pdLabel = new JLabel( "Pins Down: " );
		pinsDown = new JLabel( "0" );

		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		Insets buttonMargin = new Insets(4, 4, 4, 4);

		viewLane = new JButton("View Lane");
		JPanel viewLanePanel = new JPanel();
		viewLanePanel.setLayout(new FlowLayout());
		viewLane.addActionListener(this);
		viewLanePanel.add(viewLane);

		viewPinSetter = new JButton("Pinsetter");
		JPanel viewPinSetterPanel = new JPanel();
		viewPinSetterPanel.setLayout(new FlowLayout());
		viewPinSetter.addActionListener(this);
		viewPinSetterPanel.add(viewPinSetter);

		maintenance = new JButton(" ");
		maintenance.setBackground(Color.GREEN);
		maintenance.setOpaque(true);
		JPanel maintenancePanel = new JPanel();
		maintenancePanel.setLayout(new FlowLayout());
		maintenance.addActionListener(this);
		maintenancePanel.add(maintenance);

		viewLane.setEnabled( false );
		viewPinSetter.setEnabled( false );

		buttonPanel.add(viewLanePanel);
		buttonPanel.add(viewPinSetterPanel);
		buttonPanel.add(maintenancePanel);

		jp.add( cLabel );
		jp.add( curBowler );
		jp.add( pdLabel );
		jp.add( pinsDown );
		jp.add(buttonPanel);
	}

	public JPanel showLane() {
		return jp;
	}

	public void actionPerformed( ActionEvent e ) {
		if ( lane.isPartyAssigned() && e.getSource().equals(viewPinSetter) ) {
			if ( !psShowing ) {
				psv.show();
				psShowing=true;
			} else if ( psShowing ) {
				psv.hide();
				psShowing=false;
			}
		}
		if (e.getSource().equals(viewLane) && lane.isPartyAssigned()) { 
			if ( !laneShowing ) {
				lv.show();
				laneShowing=true;
			} else if ( laneShowing ) {
				lv.hide();
				laneShowing=false;
			}
		}
		if (e.getSource().equals(maintenance)) {
			if ( lane.isGameIsHalted() ) {
				lane.unPauseGame();
				maintenance.setBackground( Color.GREEN );
			}
			else{
				lane.pauseGame();
				maintenance.setBackground( Color.RED );
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Pinsetter){
			PinsetterEvent pe = (PinsetterEvent)arg;
			pinsDown.setText( new Integer(pe.totalPinsDown()).toString() );
		}
		else if(o instanceof Lane){
			Lane le = (Lane)o;
			if(lane.isGameFinished() && lane.isPartyAssigned()){ // Start end of game routine
				EndGamePrompt egp = new EndGamePrompt( ((Bowler) le.getParty().getMembers().get(0)).getNickName() + "'s Party" );
				int result = egp.getResult();
				egp.distroy();
				egp = null;	
				switch (result) {
				case 1:
					lane.resetScores();
					lane.resetBowlerIterator();
					break;
				case 2:
					Vector printVector;
					EndGameReport egr = new EndGameReport( ((Bowler)le.getParty().getMembers().get(0)).getNickName() + "'s Party", le.getParty());
					printVector = egr.getResult();
					Iterator scoreIt = le.getParty().getMembers().iterator();
					lane.clearLane();
					int myIndex = 0;
					while (scoreIt.hasNext()){
						Bowler thisBowler = (Bowler)scoreIt.next();
						ScoreReport sr = new ScoreReport( thisBowler, lane.getFinalScores()[myIndex++], lane.getGameNumber() );
						sr.sendEmail(thisBowler.getEmail());
						Iterator printIt = printVector.iterator();
						while (printIt.hasNext()){
							if (thisBowler.getNick() == (String)printIt.next()){
								System.out.println("Printing " + thisBowler.getNick());
								sr.sendPrintout();
							}
						}
					}
					break;
				}
			}				
			curBowler.setText(le.getCurrentThrower().getNickName());
			if ( le.isGameIsHalted() ) {
				maintenance.setBackground( Color.RED );
			}	
			if ( !lane.isPartyAssigned() ) {
				viewLane.setEnabled( false );
				viewPinSetter.setEnabled( false );
			} else {
				viewLane.setEnabled( true );
				viewPinSetter.setEnabled( true );
			}
		}
	}	
}
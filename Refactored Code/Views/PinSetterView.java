package Views;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Main.Pinsetter;
import Main.PinsetterEvent;
import java.util.*;

/** Constructs a prototype PinSetter GUI. */
public class PinSetterView implements Observer {
    private Vector pinVect = new Vector ( );
    private JPanel firstRoll;
    private JPanel secondRoll;
    private Vector emojiVector = new Vector ();
    private Dictionary imageVector = new Hashtable();
    
	private JFrame frame;
	private Boolean emoji;
	private int totalPlayers=10,bowlerindex;
    /**
     * Constructs a Pin Setter GUI displaying which roll it is with
     * yellow boxes along the top (1 box for first roll, 2 boxes for second)
     * and displays the pins as numbers in this format:
     *
     *                7   8   9   10
     *                  4   5   6
     *                    2   3
     *                      1
     */
    public PinSetterView ( Pinsetter ps, int laneNum ) {
		ps.addObserver(this);
		this.emoji = false;
		frame = new JFrame ( "Lane " + laneNum + ":" );
		
		Container cpanel = frame.getContentPane ( );
		
		JPanel pins = new JPanel ( );
		
		pins.setLayout ( new GridLayout ( 4, 7 ) );
		
		//********************Top of GUI indicates first or second roll
		
		JPanel top = new JPanel ( );
		
		firstRoll = new JPanel ( );
		firstRoll.setBackground( Color.yellow );
		
		secondRoll = new JPanel ( );
		secondRoll.setBackground ( Color.black );
		
		top.add ( firstRoll, BorderLayout.WEST );
		
		top.add ( secondRoll, BorderLayout.EAST );
		
		//******************************************************************
		
		//**********************Grid of the pins**************************
		
				JPanel one = new JPanel ();
		JLabel oneL = new JLabel ( "1" );
		one.add (oneL);
		JPanel two = new JPanel ();
		JLabel twoL = new JLabel ( "2" );
		two.add (twoL);
		JPanel three = new JPanel ();
		JLabel threeL = new JLabel ( "3" );
		three.add (threeL);
		JPanel four = new JPanel ();
		JLabel fourL = new JLabel ( "4" );
		four.add (fourL);
		JPanel five = new JPanel ();
		JLabel fiveL = new JLabel ( "5" );
		five.add (fiveL);
		JPanel six = new JPanel ();
		JLabel sixL = new JLabel ( "6" );
		six.add (sixL);
		JPanel seven = new JPanel ();
		JLabel sevenL = new JLabel ( "7" );
		seven.add (sevenL);
		JPanel eight = new JPanel ();
		JLabel eightL = new JLabel ( "8" );
		eight.add (eightL);
		JPanel nine = new JPanel ();
		JLabel nineL = new JLabel ( "9" );
		nine.add (nineL);
		JPanel ten = new JPanel ();
		JLabel tenL = new JLabel ( "10" );
		ten.add (tenL);
		
		//This Vector will keep references to the pin labels to show
		//which ones have fallen.
		
		pinVect.add ( oneL );
		pinVect.add ( twoL );
		pinVect.add ( threeL );
		pinVect.add ( fourL );
		pinVect.add ( fiveL );
		pinVect.add ( sixL );
		pinVect.add ( sevenL );
		pinVect.add ( eightL );
		pinVect.add ( nineL );
		pinVect.add ( tenL );	
		
				//******************************Fourth Row**************
		
		pins.add ( seven );
		pins.add ( new JPanel ( ) );
		pins.add ( eight );
		pins.add ( new JPanel ( ) );
		pins.add ( nine );
		pins.add ( new JPanel ( ) );
		pins.add ( ten );
		
		//*****************************Third Row***********
			
		pins.add ( new JPanel ( ) );
		pins.add ( four );
		pins.add ( new JPanel ( ) );
		pins.add ( five );
		pins.add ( new JPanel ( ) );
		pins.add ( six );
		
		//*****************************Second Row**************
	 
		pins.add ( new JPanel ( ) );
		pins.add ( new JPanel ( ) );
		pins.add ( new JPanel ( ) );
		pins.add ( two );
		pins.add ( new JPanel ( ) );
		pins.add ( three );
		pins.add ( new JPanel ( ) );
		pins.add ( new JPanel ( ) );
		
		//******************************First Row*****************
		
		pins.add ( new JPanel ( ) );
		pins.add ( new JPanel ( ) );
		pins.add ( new JPanel ( ) );
		pins.add ( one );
		pins.add ( new JPanel ( ) );
		pins.add ( new JPanel ( ) );
		pins.add ( new JPanel ( ) );
		//*********************************************************
		
		
		JPanel bot = new JPanel();
		bot.setLayout(new GridLayout(1,10));

		for(Integer i=0;i<this.totalPlayers;i++){
			JPanel e = new JPanel();
			try {
			BufferedImage image = ImageIO.read(new File("D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\cool.png"));
			Image rimage=image.getScaledInstance(30,30,Image.SCALE_SMOOTH);
			JLabel el = new JLabel(new ImageIcon(rimage));
			e.add(el);
			emojiVector.add(el);
			bot.add(e);
			} catch (IOException ex){
				ex.printStackTrace();
			}

		}
		
		top.setBackground ( Color.black );
		
		cpanel.add ( top, BorderLayout.NORTH );
		
		pins.setBackground ( Color.black );
		pins.setForeground ( Color.yellow );
		
		cpanel.add ( pins, BorderLayout.CENTER );
		bot.setBackground(Color.LIGHT_GRAY);
		cpanel.add(bot,BorderLayout.SOUTH);
		
		frame.pack();
		
		Map<String,String> imgsrc=new HashMap<String,String>();
		imgsrc.put("laugh","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\laugh.jpg");
		imgsrc.put("love","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\love.jpg");
		imgsrc.put("medium_smile","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\medium_smile.jpg");
		imgsrc.put("perfect","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\perfect.jpg");
		imgsrc.put("facepalm","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\facepalm.jpg");
		imgsrc.put("cry","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\cry.jpg");
		imgsrc.put("cry_hard","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\cry_hard.jpg");
		imgsrc.put("cool","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\cool.png");
		imgsrc.put("smile","D:\\Software Engineering\\SWE_Project\\Refactored Code\\emojis\\smile.jpg");

		for(Map.Entry m : imgsrc.entrySet()){
			try {
			String s=(String)m.getValue();
			BufferedImage image = ImageIO.read(new File(s));
			Image rimage=image.getScaledInstance(30,30,Image.SCALE_SMOOTH);
			imageVector.put(m.getKey(),new ImageIcon(rimage));
			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
	}
    
    public void show() {
    	frame.show();
    }

    public void hide() {
    	frame.hide();
    }

	@Override
	public void update(Observable o, Object arg) {
		
		
		
		
		PinsetterEvent pe = (PinsetterEvent)arg;
		
		JLabel emo=new JLabel();
		for(int i=0;i<this.totalPlayers;i++)
		{
			emo=(JLabel)emojiVector.get(i);

			switch(pe.totalPinsDown())
			{
				case 0:
					if(i==this.bowlerindex)
						emo.setIcon((ImageIcon)imageVector.get("cry_hard"));
					else
						emo.setIcon((ImageIcon)imageVector.get("facepalm"));
					break;
				case 10:
					if(i==this.bowlerindex)
						emo.setIcon((ImageIcon)imageVector.get("cool"));
					else
						emo.setIcon((ImageIcon)imageVector.get("perfect"));
					break;
				default:
					emo.setIcon((ImageIcon)imageVector.get("smile"));
					
			}
			if(this.emoji)
				emo.setEnabled(true);
			else
				emo.setEnabled(false);
		}
		
		if ( !pe.isFoulCommited() ) {
	    	JLabel tempPin = new JLabel ( );
	    	boolean pin;
			for ( int c = 0; c < 10; c++ ) {
				pin = pe.pinKnockedDown ( c );
				tempPin = (JLabel)pinVect.get ( c );
				if ( pin ) {
		    		tempPin.setForeground ( Color.lightGray );
				}
	    	}
    	}
		if ( pe.getThrowNumber() == 1 ) {
	   		 secondRoll.setBackground ( Color.yellow );
		}
		if ( pe.pinsDownOnThisThrow() == -1) {
			for ( int i = 0; i != 10; i++){
				((JLabel)pinVect.get(i)).setForeground(Color.black);
			}
			secondRoll.setBackground( Color.black);
		}
	}
	
	public void setemoji(Boolean fl){
		this.emoji=fl;
	}

	public void setbowlerindex(int index){
		this.bowlerindex=index;
	}

	public void setnum(int x){
		this.totalPlayers=x;
		for(int i=x;i<10;i++)
		{
			JLabel emo=(JLabel)emojiVector.get(i);
			emo.setEnabled(false);
		}
	}
}
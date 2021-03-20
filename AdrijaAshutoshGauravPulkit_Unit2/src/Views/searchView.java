package Views;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class searchView {

    searchView() {

        Frame fra = new Frame("Searchable View");
        Label l = new Label("Select query for different players:");
        Choice cho = new Choice();
        TextField t = new TextField("Fill name of the player if needed:");
        Button but = new Button("Search");
        TextField r = new TextField(40);  // Result.

        cho.add("Highest score among all");
        cho.add("Lowest score among all");
        cho.add("Highest score of specific");
        cho.add("Lowest score of specific");
        cho.add("Top player");
        cho.add("Last 5 score specific");

        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String queryparse = cho.getItem(cho.getSelectedIndex());
                String result;


                if (queryparse.equals("Lowest score among all")) {
                    try {
                        result = "Result: " + searchDATABASE.giveLowestScoreAll();
                    }
                    catch (Exception e) {
                        result = "Error retrieving lowest score.";
                    }
                }
                else if (queryparse.equals("Top player")) {
                    try {
                        result = "Result: " + searchDATABASE.giveTopPlayer();
                    }
                    catch (Exception e) {
                        result = "Error retrieving top player.";
                    }
                }
                else if (queryparse.equals("Highest score of specific")) {
                    try {
                        String playerName = t.getText();
                        result = "Result: " + searchDATABASE.giveHighestScoreofSpecific(playerName);
                    }
                    catch (Exception e) {
                        result = "Error retrieving top player.";
                    }
                }
                else if (queryparse.equals("Lowest score of specific")) {
                    try {
                        String playerName = t.getText();
                        result = "Result: " + searchDATABASE.giveLowestScoreofSpecific(playerName);
                    }
                    catch (Exception e) {
                        result = "Error retrieving top player.";
                    }
                }
                else if (queryparse.equals("Highest score among all")) {
                    try {
                        result = "Result: " + searchDATABASE.giveHighestScoreAll();
                    }
                    catch (Exception e) {
                        result = "Error retrieving highest score.";
                    }
                }
                else if (queryparse.equals("Last 5 score specific")) {
                    try {
                        String playerName = t.getText();
                        result = "Result: " + searchDATABASE.giveLast5ScoreSpecific(playerName);
                    }
                    catch (Exception e) {
                        result = "Error retrieving top player.";
                    }
                }
                else {
                    result = "Error in searchview.";
                }

                r.setText(result);
            }
        });

        fra.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                fra.dispose();
            }
        });

        fra.add(l);
        fra.add(cho);
        fra.add(t);
        fra.add(but);
        fra.add(r);
	r.setColumns(20);
        fra.setLayout(new FlowLayout());
        fra.setSize(400, 400);
        fra.setVisible(true);
    }

}


class searchDATABASE {


    public static String giveHighestScoreofSpecific(String playerName) throws FileNotFoundException, IOException {
        File myobj = new File("/home/ady/SEM2/SE/Unit-2/Refactored Code/SCOREHISTORY.DAT");
        Scanner reader = new Scanner(myobj);

        int highScore = -1;
        String returnString = "No high score!";

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] splitData = data.split("\t");
            int presentscore = Integer.parseInt(splitData[2]);
            String presentplayer = splitData[0];

            if (presentplayer.equals(playerName) && highScore < presentscore) {  // checking if player name is same as input and score is highest or not
                highScore = presentscore;
                returnString = splitData[2];
            }
        }

        return returnString;
    }


    public static String giveLowestScoreofSpecific(String playerName) throws FileNotFoundException, IOException {
        File myobj = new File("/home/ady/SEM2/SE/Unit-2/Refactored Code/SCOREHISTORY.DAT");
        Scanner reader = new Scanner(myobj);

        int lowestscore = 301;
        String returnString = "No low score!";

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] splitData = data.split("\t");
            int presentscore = Integer.parseInt(splitData[2]);
            String presentplayer = splitData[0];

            if (presentplayer.equals(playerName) && lowestscore > presentscore) { // checking if player name is same as input and score is lowest or not
                lowestscore = presentscore;
                returnString = splitData[2];
            }
        }
  // returning string 
        return returnString;
    }
    public static String giveLast5ScoreSpecific(String playerName) throws FileNotFoundException, IOException {
		File myObj = new File("/home/ady/SEM2/SE/Unit-2/Refactored Code/SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		String returnString = "";

		java.util.List<String> reverseFile = new ArrayList<String>();

		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			reverseFile.add(data);
		}

		int count = 0;
		for(int i = reverseFile.size()-1; i >= 0; i--) {
			String data = reverseFile.get(i);
			String[] splitData = data.split("\t");
			String curPlayer = splitData[0];

			if (curPlayer.equals(playerName)) {
				returnString += splitData[2] + " on " + splitData[1] + ";     ";
				count += 1;
			}

			if (count == 5) {
				break;
			}
		}
	
		if (count == 0) {
			returnString = "No scores found!";
		}

		return returnString;
	}
    
    public static String giveHighestScoreAll() throws FileNotFoundException, IOException {
		File myObj = new File("/home/ady/SEM2/SE/Unit-2/Refactored Code/SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		int highScore = -1;
		String returnString = "No high score!";
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitData = data.split("\t");
			int curScore = Integer.parseInt(splitData[2]);
			
			if (highScore < curScore) {
				highScore = curScore;
				returnString = splitData[2];
			}
		}

		return returnString;
	}


	public static String giveLowestScoreAll() throws FileNotFoundException, IOException {
		File myObj = new File("D:\\Software Engineering\\SWE_Project\\Refactored Code\\SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		int lowScore = 301;
		String returnString = "No low score!";
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitData = data.split("\t");
			int curScore = Integer.parseInt(splitData[2]);
			
			if (lowScore > curScore) {
				lowScore = curScore;
				returnString = splitData[2];
			}
		}

		return returnString;
	}


	public static String giveTopPlayer() throws FileNotFoundException, IOException {
		File myObj = new File("D:\\Software Engineering\\SWE_Project\\Refactored Code\\SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		int highScore = -1;
		String returnString = "No top player!";
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitData = data.split("\t");
			int curScore = Integer.parseInt(splitData[2]);
			
			if (highScore < curScore) {
				highScore = curScore;
				returnString = splitData[0];
			}
		}

		return returnString;
	}


}

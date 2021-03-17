import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class searchview {

    searchview() {

        Frame fra = new Frame("Searchable View");
        Label l = new Label("Select query for different players:");
        Choice cho = new Choice();
        TextField t = new TextField("Fill name of the player if needed:");
        Button but = new Button("Search");
        Label r = new Label("Result: ");  // Result.

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
                        result = "Error retriving lowest score.";
                    }
                }
                else if (queryparse.equals("Top player")) {
                    try {
                        result = "Result: " + searchDATABASE.giveTopPlayer();
                    }
                    catch (Exception e) {
                        result = "Error retriving top player.";
                    }
                }
                else if (queryparse.equals("Highest score of specific")) {
                    try {
                        String playerName = t.getText();
                        result = "Result: " + searchDATABASE.giveHighestofScoreSpecific(playerName);
                    }
                    catch (Exception e) {
                        result = "Error retriving top player.";
                    }
                }
                else if (queryparse.equals("Lowest score of specific")) {
                    try {
                        String playerName = t.getText();
                        result = "Result: " + searchDATABASE.giveLowestScoreofSpecific(playerName);
                    }
                    catch (Exception e) {
                        result = "Error retriving top player.";
                    }
                }
                else if (queryparse.equals("Highest score among all")) {
                    try {
                        result = "Result: " + searchDATABASE.giveHighestScoreAll();
                    }
                    catch (Exception e) {
                        result = "Error retriving highest score.";
                    }
                }
                else if (queryparse.equals("Last 5 score specific")) {
                    try {
                        String playerName = t.getText();
                        result = "Result: " + searchDATABASE.giveLast5ScoreSpecific(playerName);
                    }
                    catch (Exception e) {
                        result = "Error retriving top player.";
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

        fra.setLayout(new FlowLayout());
        fra.setSize(600, 300);

        fra.setVisible(true);
    }

}


class searchDATABASE {


    public static String giveHighestScoreofSpecific(String playerName) throws FileNotFoundException, IOException {
        File myobj = new File("SCOREHISTORY.DAT");
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
        File myobj = new File("SCOREHISTORY.DAT");
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
}

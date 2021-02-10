package Main;

import java.io.*;
import java.util.Vector;

public class ScoreHistoryFile {
    private static final String SCOREHISTORY_DAT = "../SCOREHISTORY.DAT";

    public static void addScore(String nick, String date, String score)
            throws IOException {
        String data = nick + "\t" + date + "\t" + score + "\n";

        RandomAccessFile out = new RandomAccessFile(SCOREHISTORY_DAT, "rw");
        out.skipBytes((int) out.length());
        out.writeBytes(data);
        out.close();
    }

    public static Vector<Score> getScores(String nick)
            throws IOException {
        Vector<Score> scores = new Vector<>();

        BufferedReader in =
                new BufferedReader(new FileReader(SCOREHISTORY_DAT));
        String data;
        String[] scoredata;
		while ((data = in.readLine()) != null) {
            // File format is nick\tfname\te-mail
            scoredata = data.split("\t");
            //"Nick: scoredata[0] Date: scoredata[1] Score: scoredata[2]
            if (nick.equals(scoredata[0])) {
                scores.add(new Score(scoredata[0], scoredata[1], scoredata[2]));
            }
        }
        return scores;
    }
}

package Main;

import java.io.*;
import java.util.Vector;

public class ScoreHistoryFile {

	private static String SCOREHISTORY_DAT = "/home/ady/SEM2/SE/Unit-2/Refactored Code/SCOREHISTORY.DAT";

	public static void addScore(String nick, String date, String score) throws IOException, FileNotFoundException {
		String data = nick + "\t" + date + "\t" + score + "\n";
		RandomAccessFile out = new RandomAccessFile(SCOREHISTORY_DAT, "rw");
		out.skipBytes((int) out.length());
		out.writeBytes(data);
		out.close();
	}

	public static Vector<Score> getScores(String nick)
		throws IOException, FileNotFoundException {
		Vector<Score> scores = new Vector<>();
		BufferedReader in =
			new BufferedReader(new FileReader(SCOREHISTORY_DAT));
		String data;
		while ((data = in.readLine()) != null) {
			// File format is nick\tfname\te-mail
			String[] scoredata = data.split("\t");
			//"Nick: scoredata[0] Date: scoredata[1] Score: scoredata[2]
			if (nick.equals(scoredata[0])) {
				scores.add(new Score(scoredata[0], scoredata[1], scoredata[2]));
			}
		}
		in.close();
		return scores;
	}
}

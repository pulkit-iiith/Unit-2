package Main;

import java.io.*;
import java.util.Vector;

/** Class for interfacing with Bowler database. */

public class BowlerFile {
	/** The location of the bowelr database. */
	private static String BOWLER_DAT = "/home/ady/SEM2/SE/Unit-2/Refactored Code/BOWLERS.DAT";

	/**
	 * Retrieves bowler information from the database and returns a Bowler objects with populated fields.
	 *
	 * @param nickName	the nickName of the bolwer to retrieve
	 *
	 * @return a Bowler object
	 */

	public static Bowler getBowlerInfo(String nickName)
			throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(BOWLER_DAT));
		String data;
		String[] bowler;
		while ((data = in.readLine()) != null) {
			// File format is nick\tfname\te-mail
			bowler = data.split("\t");
			if (nickName.equals(bowler[0])) {
				System.out.println("Nick: " + bowler[0] + " Full: " + bowler[1] + " email: " + bowler[2]);
				return new Bowler(bowler[0], bowler[1], bowler[2]);
			}
		}
		System.out.println("Nick not found...");
		in.close();
		return null;
	}

	/**
	 * Stores a Bowler in the database.
	 *
	 * @param nickName	the NickName of the Bowler
	 * @param fullName	the FullName of the Bowler
	 * @param email	the E-mail Address of the Bowler
	 */
	public static void putBowlerInfo(String nickName, String fullName, String email) throws IOException, FileNotFoundException {
		String data = nickName + "\t" + fullName + "\t" + email + "\n";
		RandomAccessFile out = new RandomAccessFile(BOWLER_DAT, "rw");
		out.skipBytes((int) out.length());
		out.writeBytes(data);
		out.close();
	}

	/**
	 * Retrieves a list of nicknames in the bowler database.
	 *
	 * @return a Vector of Strings
	 */
	public static Vector<String> getBowlers() throws IOException, FileNotFoundException {
		Vector<String> allBowlers = new Vector<>();
		System.err.println("Hello");
		BufferedReader in = new BufferedReader(new FileReader(BOWLER_DAT));
		String data;
		String[] bowler;
		while ((data = in.readLine()) != null) {
			// File format is nick\tfname\te-mail
			bowler = data.split("\t");
			//"Nick: bowler[0] Full: bowler[1] email: bowler[2]
			allBowlers.add(bowler[0]);
		}
		in.close();
		return allBowlers;
	}
}

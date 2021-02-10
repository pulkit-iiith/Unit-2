package Main;

import java.util.HashMap;

public class LaneEvent {
//    int frame;
    int ball;
    Bowler bowler;
    int[][] cumulScore;
    HashMap score;
    int index;
    int frameNum;
    int[] curScores;
    boolean mechProb;
    private final Party p;

    public LaneEvent(Party pty, int theIndex, Bowler theBowler, int[][] theCumulScore, HashMap theScore, int theFrameNum, int[] theCurScores, int theBall, boolean mechProblem) {
        p = pty;
        index = theIndex;
        bowler = theBowler;
        cumulScore = theCumulScore;
        score = theScore;
        curScores = theCurScores;
        frameNum = theFrameNum;
        ball = theBall;
        mechProb = mechProblem;
    }

    public boolean isMechanicalProblem() {
        return mechProb;
    }

    public int getFrameNum() {
        return frameNum;
    }

    public HashMap getScore() {
        return score;
    }

/** Public int[] getCurScores() { return curScores; }. */

    public int getIndex() {
        return index;
    }

/** Public int getFrame() { return frame; }. */

    public int getBall() {
        return ball;
    }

    public int[][] getCumulScore() {
        return cumulScore;
    }

    public Party getParty() {
        return p;
    }

    public Bowler getBowler() {
        return bowler;
    }
}
 

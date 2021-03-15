package Main;

public class ScoreCalculator {

	private float[][] cumulScores;  //reference to be mutated.

	 /**
	 * Constructor
	 */
	public ScoreCalculator(float[][] cumulScores2){
		this.cumulScores = cumulScores2;  // Operate on Lane's reference
	}

	/*
	* This is the main method of this class. This method contains
	* the algorithm that calculates the scores of the current player.
	* The method first checks if the player has achieved a strike, if they
	* did, then the private helper strike is called, if the current player
	* has achieved a spare, then the spare helper method is called, and so on.
	*
	* @param bowlerScores: the current score record.
	* @param bowlerIndex : the index of the current bowler.
	* @param frameNumber : the current frame the current bowler is on.
	* @param roll        : the current roll or throw that the current bowler is on.
	* @param pinsDown    : the total number of pins that were knocked down by the last throw.
	*/
	
	protected void calculateGame(int[] bowlersScores, int bowlerIndex, int frameNumber, int roll, int pinsDown ){
		int currentRoll = (2 * frameNumber) + roll;
		// Reset the previous record of scores
		for(int i = 0; i < cumulScores[bowlerIndex].length; i++){
			cumulScores[bowlerIndex][i] = 0;
		}

		int frameIndex;
		int prevScore = 0;

		for(int i = 0; i < currentRoll; i++){
			boolean even = i % 2 == 0;

			// Check Strike
			if(even && bowlersScores[i] == 10 && bowlersScores[i + 1] == -1){ // If all ten are down, and the roll we are on is even (starting at zero)
				prevScore += strike(i, bowlersScores);
			}
			// Check Spare
			else if( !even && (bowlersScores[i - 1] + bowlersScores[i] == 10) ){ // If the roll we are on an odd roll, and the past two rolls add to ten
				prevScore += spare(i, bowlersScores);
			}
			// Check if Normal
			else if(bowlersScores[i] >= 0){ // Only display after the second throw of the frame
				if (!even || i == 20){
					prevScore += normal(i, bowlersScores);
				}
			}
			
			// Check for Penalty
			if(i > 1) {
				// check consecutive 2 throws 0 or not
				if(bowlersScores[i] == 0 && bowlersScores[i] == bowlersScores[i - 1]) {
					if(i == 2) {
						prevScore -= bowlersScores[2] / 2;
					}
					else {
						int maximum = bowlersScores[0];
						int idx = 1;
						while(idx < i - 1) {
							maximum = Math.max(maximum, bowlersScores[idx]);
							idx++;
						}
						prevScore -= maximum / 2;
					}
				}
			}

			frameIndex = i / 2 ;
			if(i > 19){ // Last frame could have three throws
				frameIndex = 9;
			}
			this.cumulScores[bowlerIndex][frameIndex] = prevScore;

		}
	}

	/*
	* This is the strike helper method to calculate the strike score of the
	* current bowler.
	*
	* @param index         : the current throw or roll.
	* @param currentScores : the most up to date record of scores.
	*/
	private int strike(int index, int[] currentScores){
		if(index > 17){
			return (10);
		}
		int count = 0;
		int scoreSum = 10; // Start at ten since it was a strike
		for(int i = 1; i <= 4; i++){ // If a strike is thrown, then also add the next two rolls
			if(currentScores[index + i] >= 0 && count < 2){ // Add only the next two valid scores
				scoreSum += currentScores[index + i];
				count++;
			}
		}
		return scoreSum;
	}

	/*
	* This is the spare helper method to calculate the stike score of the
	* current bolwer.
	*
	* @param index         : the current throw or roll.
	* @param currentScores : the most up to date record of scores.
	*/
	private int spare(int index, int[] currentScores){
		if(index > 17){
			return (10);
		}
		int count = 0;
		int scoreSum = 10; // Start at ten since it was a strike
		for(int i = 1; i <= 4; i++){ // If a strike is thrown, then also add the next two rolls
			if(currentScores[index + i] >= 0 && count < 1){ // Add only the next two valid scores
				scoreSum += currentScores[index + i];
				count++;
			}
		}
		return scoreSum;
	}

	/*
	* This is the normal case helper method to calculate the stike score of the
	* current bolwer.
	*
	* @param index         : the current throw or roll.
	* @param currentScores : the most up to date record of scores.
	*/
	private int normal(int index, int[] currentScores){
		if(index % 2 == 1 && currentScores[index - 1] >= 0){
			return(currentScores[index] + currentScores[index - 1]);
		}
		return(currentScores[index]);
	}
}

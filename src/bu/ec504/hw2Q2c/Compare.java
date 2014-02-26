package bu.ec504.hw2Q2c;

import java.util.Random;
import java.util.Vector;

public class Compare {
	final static int NUM_TRIALS = 300000; // the number of trials to run

	public static class ComparisonFailed extends Exception {
		ComparisonFailed(int ii) {
			data = ii;
		}

		public int data;
	}

	public static void main(String[] args) {
		Vector<Boolean> answers = new Vector<Boolean>(); // correct answers to LSDS queries

		// try on the TrivialLSDS - the control for the experiment
		long seed = System.currentTimeMillis();
		Random rn = new Random(seed);

		long beginControl = System.currentTimeMillis();
		LSDS control = new TrivialLSDS();
		for (int ii = 0; ii < NUM_TRIALS; ii++) {
			
			// decide QUERY or INSERT
			if (rn.nextBoolean())// random query between 0 and NUM_TRIALS
				{answers.add(control.QUERY(rn.nextInt(NUM_TRIALS)));}
			else
				// insertion of a random number between 0 and NUM_TRIALS
				control.INSERT(rn.nextInt(NUM_TRIALS));
		}
		long endControl = System.currentTimeMillis();

		// Compare to myLSDS - the experiment
		rn = new Random(seed);
		long beginExp = System.currentTimeMillis();
		LSDS exp = new myLSDS();
		int index = 0; // the index in the answers vector
		try {
			for (int ii = 0; ii < NUM_TRIALS; ii++) {

				// decide QUERY or INSERT
				if (rn.nextBoolean())// random query between 0 and NUM_TRIALS
					{if (answers.get(index++) != exp.QUERY(rn.nextInt(NUM_TRIALS)))
						throw new ComparisonFailed(ii);}
					else
						// insertion of a random number between 0 and NUM_TRIALS
						exp.INSERT(rn.nextInt(NUM_TRIALS));
			}
		} catch (ComparisonFailed E) {
			System.out.println("Comparison failed at index " + E.data
					+ ", seed was " + seed);
		}
		long endExp = System.currentTimeMillis();

		System.out.println("Control time elapsed:   "
				+ (double) (endControl - beginControl) / 1000);
		System.out.println("Experiment time elapsed:"
				+ (double) (endExp - beginExp) / 1000);
		System.out.println("Efficiency ratio: "
				+ (double) (endControl - beginControl) / (endExp - beginExp));
	}

}

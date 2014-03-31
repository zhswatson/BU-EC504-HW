package bu.ec504.hw3Q4;

import java.util.ArrayList;
import java.util.Random;

public class reduce {

	static int chainSize = 10;

	static public class Pair<F, S> {
		Pair(F theF, S theS) {
			first = theF;
			second = theS;
		}

		public F first;
		public S second;
	};

	// LOP : list of pairs
	private static ArrayList<Pair<String, String>> LOP = new ArrayList<Pair<String, String>>();

	// main function which builds the rainbow table
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String arg = "(,|< (<pz$j|4<$4";
		String arg = null;
		if(args[0].equals("rainbow"))
			arg = args[1];
		else
			arg =  "rx6|f.0 l8.hjt<:"; 
		for(int i = 0; i< args.length; i++) {
			System.out.println(args[i]);
		}
		System.out.println("arg is " + arg);
/*
		for (int h = 48; h < 123; h++) {
			for (int i = 48; i < 123; i++) {
				for (int j = 48; j < 123; j++) {
					for (int k = 48; k < 123; k++) {
						String tmp = "" + (char) h + (char) i + (char) j
								+ (char) k;
						getPair(tmp);
						//System.out.println("add chain is " + (getPair(tmp)));
					}
				}
			}
		}
*/
		System.out.println("adding custom pair " + getPair("Axel"));
		System.out.println("adding custom pair " + getPair("cAds"));
		System.out.println("adding custom pair " + getPair("DafT"));
		System.out.println("adding custom pair " + getPair("XRAY"));
		System.out.println("adding custom pair " + getPair("N1FF"));
		System.out.println("adding custom pair " + getPair("MaCs"));
		System.out.println("adding custom pair " + getPair("1234"));
		System.out.println("adding custom pair " + getPair("pass"));
		System.out.println("adding custom pair " + getPair("word"));
		System.out.println("adding custom pair " + getPair("ec50"));
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			String tmp = "" + (char) (32 + r.nextInt(96))
					+ (char) (32 + r.nextInt(96)) + (char) (32 + r.nextInt(96))
					+ (char) (32 + r.nextInt(96));
			System.out.println("add chain is " + (getPair(tmp)));
		}
		
		System.out.println("Final # of chains is " + LOP.size());
		System.out.println("Finished computing LOR");

		// starting with arg to find the pair
		String start = new String();
		String end = reduce(0, arg, 4);
		boolean found = false;

		outloop: for (int i = 0; i < chainSize; i++) {
			for (Pair<String, String> pair : LOP) {
				if (pair.second.equals(end)) {
					start = pair.first;
					// System.out.println("chain's start is " + start +	" and end is" + end);
					if (found = findArg(start, arg)) {
						break outloop;
					}
				} else {
				}
			}
			end = reduce(0, hash.compact(end), 4);
			// System.out.println("right now plaintext is " + end);
		}
		if (found) {
			System.out.println("Hash in chain, operation success");
			//System.out.println("Chain with same ending found");
			//System.out.println("Found chain's start is " + start + " end is " + end);
		} else {
			System.out.println("Hash not in chain, operation failure");
		}

	}

	public static boolean getPair(String start) {
		String result = start;
		for (int i = 0; i < chainSize; i++) {
			result = reduce(0, hash.compact(result), 4);
			// check the rainbow table to see if there is any collision
			for (Pair<String, String> pair : LOP) {
				if (pair.second.equals(result)) {
					return false; // if collision, do nothing but return false
				}
			}
		}

		// if no collision, add the pair
		LOP.add(new Pair<String, String>(start, result));
		//System.out.println("chain create successful, start is " + start	+ " end is " + result);
		return true;
	}

	public static boolean findArg(String start, String arg) {
		// System.out.println("find arg entered" + start + arg);
		boolean argfound = false;
		// scan every hash in the chain for equal
		for (int i = 0; i < chainSize; i++) {
			if (hash.compact(start).equals(arg)) {
				//System.out.println(hash.compact(start));
				//System.out.println("start found to be " + start);
				argfound = true;
				break;
			} else
				start = reduce(0, hash.compact(start), 4);
		}

		if (argfound) {
			System.out.println("Found key complete and key is " + start);
		} else {
			//System.out.println("Key not found");
		}
		return argfound;
	}


	public static String reduceReadable(byte[] bytes) {
		for (int ii = 0; ii < bytes.length; ii++)
			bytes[ii] = (byte) (Math.abs(bytes[ii] % 0x5E) + 33); // Convert to
																	// character
																	// ! through
																	// ~
		return new String(bytes);
	}

	/**
	 * Computes a "reduce" function for a rainbow table
	 * 
	 * @param num
	 *            The index of the reduce function to call
	 * @param str
	 *            The string to reduce
	 * @param len
	 *            The ultimate length of the reduction
	 * @return A string of characters between ! and ~.
	 */
	public static String reduce(int num, String str, int len) {
		byte[] result = new byte[len];
		byte[] strB = str.getBytes();
		Random rand = new Random();
		rand.setSeed(0); // set the seed in order to make this predictable
		for (int ii = 0; ii < len; ii++) { // xor two characters
			result[ii] = (byte) num;
			result[ii] ^= (byte) strB[rand.nextInt(strB.length)]
					^ rand.nextInt(128);
			result[ii] ^= (byte) strB[rand.nextInt(strB.length)]
					^ rand.nextInt(128);
		}
		return reduceReadable(result);
	}

}

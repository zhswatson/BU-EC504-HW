package bu.ec504.hw3Q4;

import java.util.ArrayList;
import java.util.Random;

public class reduce {

	static int chainSize = 500;
	static public class Pair<F, S> {
		Pair(F theF, S theS) {
			first = theF;
			second = theS;
		}

		public F first;
		public S second;
	};
	// LOP : list of pairs
	private static ArrayList<Pair<String,String>> LOP = new ArrayList<Pair<String,String>> ();
	
	public static boolean getPair(String start) {
		String result = start;
		String tmp;
		for(int i = 0; i < chainSize; i++) {
			result = reduce(0, hash.compact(result), 4);
		}
		for(Pair<String, String> pair: LOP) {
			if(pair.second.equals(result)) {
				return false;
			}
		}
		LOP.add(new Pair<String, String>(start, result));
		return true;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String arg = "(,|< (<pz$j|4<$4";
		String arg = ":&x`>``0$lln$:8<";
		
/*		for (int h = 48; h < 58; h++) {
			for (int i = 48; i < 58; i++) {
				for (int j = 48; j < 58; j++) {
					for (int k = 48; k < 58; k++) {
	            		String tmp = "" + (char)h + (char)i + (char)j + (char)k;
	            		LOP.add(getPair(tmp));
					}
				}
			}
		}
*/
		Random r = new Random();
		for(int i = 0; i < 1000; i++) {
			String tmp = "" + (char) r.nextInt(128) + (char) r.nextInt(128)
					 + (char) r.nextInt(128) + (char) r.nextInt(128);
			
    		System.out.println("add chain is " + (getPair(tmp)));
		}
		System.out.println("final number of chains is " + LOP.size());
		

		
		System.out.println("adding custom pair " + getPair("DaFT"));
		
		System.out.println("Finished computing LOR");
		
		// starting with arg to find the pair
		String start = new String();
		String end = reduce(0, arg, 4);
		boolean found = false;
		System.out.println("start is " + start + "end is " + end);

		outerloop:
		for(int i = 0; i < chainSize; i++) {
			for(Pair<String, String> pair: LOP) {
				if(pair.second.equals(end)) {
					start = pair.first;
					found = true;
					break outerloop;
				}
				else {
				}
			}
			end = reduce(0, hash.compact(end), 4);
//			System.out.println("right now plaintext is " + end);
		}
		if(found) {
			System.out.println("Pair with same ending found");
			System.out.println("found pair's start is " + start + " end is " + end);
		}

		// after locating pair, start searching from the beginning
		for(int i = 0; i < chainSize; i++) {
			if(hash.compact(start) == arg) {
				System.out.println("start found to be " + start);
				break;				
			}
			else
				start = reduce(0, hash.compact(start), 4);
		}
		
		if(found)
			System.out.println("find key complete and key is " + start);
		System.out.println(reduce(0, "(,|< (<pz$j|4<$4", 4));

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

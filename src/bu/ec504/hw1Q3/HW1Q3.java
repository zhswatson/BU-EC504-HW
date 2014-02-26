package bu.ec504.hw1Q3;

public class HW1Q3 {

	static long[] numArray = new long[2000000];
	static int count = 0;
	static float median = (float) 0.0;
	
	public static Float runningMedian(long number) {
		count++;
		numArray[count-1] = number;
		for(int i = 0; i < count; i++){
			if (numArray[i] >= number){
				for(int ii = count-1; ii > i; ii--) {
					numArray[ii] = numArray[ii-1];
				}
				numArray[i] = number;
				break;
			}
			if (numArray[i] < number){
				;
			}
		}
		if(count%2 == 1)
			median = numArray[(count-1)/2];
		else if(count%2 == 0)
			median = (float)(numArray[count/2-1]+numArray[count/2])/2;
		
		return median;
			 
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000616; i++) {
			long param = (long)((i%1000)/Math.sin(i));
			System.out.println(param);
			System.out.println(runningMedian(param));
		}
	}

}

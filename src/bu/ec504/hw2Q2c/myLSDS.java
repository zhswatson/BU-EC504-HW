package bu.ec504.hw2Q2c;

import java.util.ArrayList;
import java.util.Collections;

public class myLSDS extends LSDS{

	private ArrayList<Integer> sorted;
	private ArrayList<Integer> unsorted;
	private static int numofinsert;
	
	myLSDS() {
		sorted = new ArrayList<Integer>();
		unsorted = new ArrayList<Integer>();
	}
	
	@Override
	public void INSERT(Integer x) {
		unsorted.add(x);
		if(numofinsert++ == 900) {
			numofinsert = 0;
			this.RESORT();
		}
		
	}

	@Override
	public boolean QUERY(Integer x) {
		if(Collections.binarySearch(sorted, x)>=0 
			&& Collections.binarySearch(sorted, x)<sorted.size()) return true;
		return  unsorted.indexOf(x)!=-1;
	}

	@Override
	protected void RESORT() {
		sorted.addAll(unsorted);
		Collections.sort(sorted);
		unsorted.removeAll(unsorted);
	
	}
		
}

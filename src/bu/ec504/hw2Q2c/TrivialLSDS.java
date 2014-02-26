package bu.ec504.hw2Q2c;

import java.util.ArrayList;

public class TrivialLSDS extends LSDS {

	TrivialLSDS() { data = new ArrayList<Integer>(); }
	
	@Override
	public void INSERT(Integer x) {
		data.add(x);
	}

	@Override
	public boolean QUERY(Integer x) {
		return data.indexOf(x)!=-1;
	}

	@Override
	protected void RESORT() {
		;
	}

	private ArrayList<Integer> data;
}

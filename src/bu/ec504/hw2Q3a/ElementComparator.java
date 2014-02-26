package bu.ec504.hw2Q3a;

import java.util.Comparator;

public class ElementComparator implements Comparator<Element> {
	
	@Override
	public int compare(Element x, Element y) {
		if (x.getKey() < y.getKey())
			return -1;
		if (x.getKey() > y.getKey())
			return 1;
		return 0;
	}
}
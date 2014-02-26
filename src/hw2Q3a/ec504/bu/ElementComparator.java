package hw2Q3a.ec504.bu;

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
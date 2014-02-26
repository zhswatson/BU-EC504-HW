package bu.ec504.hw2Q3a;
 
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample {

	public static void main(String[] args) {
		Comparator<Element> comparator = new ElementComparator();
		PriorityQueue<Element> queue = new PriorityQueue<Element>(10, comparator);
		Element e = new Element(1, 1);
		queue.add(e);
		Element e1 = new Element(2222, 2);
		queue.add(e1);
		Element e2 = new Element(13, 3);
		queue.add(e2);
		
		System.out.println(queue.remove().getData());
		System.out.println(queue.remove().getData());
		System.out.println(queue.remove().getData());
		System.out.println(queue.size());
	}

}


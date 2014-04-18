package bu.ec504.hw3Q2;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a rudimentary Binary Search Tree keyed by Integers
 * @author trachten
 */
public class BST {

    // CLASSES
    
    /**
     * Represents one rotation (ZIG or ZAG) around one key in a Binary Search Tree
     */
    public static class Rotation {
        public static enum RotationType {ZIG, ZAG};
        public Rotation(Integer key, RotationType rot) {
            myKey = key;
            myRot = rot;
        }
        /**
         * @return A human-readable description of the rotation
         */
        public String print() {
            String result="";
            switch (myRot) {
                case ZIG: result="ZIG"; break;
                case ZAG: result="ZAG"; break;
            }
            result+=" " + myKey;
            return result;
        }
        private Integer myKey;         // the key that is at the root of the rotation being performed
        private RotationType myRot;    // the type of rotation being performed
    }

    // CONSTRUCTORS
     
    /**
     * Constructs a one-node Binary Search Tree with the given key.
     * @param num The key of the one node of the tree
     */
    public BST(Integer num) {
        key = num;
        left = null;
        right = null;
    }
    
    /**
     * Constructs a Binary Search Tree by repeatedly inserting Integers in <nums> in the order they appear in the array
     * @param nums The array of Integers to insert
     */
    public BST(Integer[] nums) {
        this(nums[0]); // call the other constructor with the first Integer
        for (int ii=1; ii< nums.length; ii++) // insert all the Integers, one by one
            insert(nums[ii]);
    }

    
    // METHODS
    /**
     * Compute how to transform BST <first> into BST <second> using rotations.
     * @param first The first BST to transform.
     * @param second The desired transformed BST.
     * @return An ArrayList of rotations indicating which rotations around which nodes
     *  must be performed to transform <first> into <second.
     */
    public static ArrayList<Rotation> transform(BST first, BST second) {
        System.out.println("PLEASE OVERSHADOW ME IN A SUBCLASS WITH WORKING CODE");
        return new ArrayList<Rotation>();
    }
    
    /**
     * Inserts the new number as a key into the binary search tree
     * @param num The new key to be inserted
     */
    final public void insert(Integer num) {
		if (num.intValue() > key.intValue()) {
            // insert to the right
            if (right==null)
                right = new BST(num);
            else
                right.insert(num);
        }
        else // num<=key; insert to the left
            if (left==null)
                left = new BST(num);
            else
                left.insert(num);
    }
    
    /**
     * Return the BST rooted at this node as a human-readable string
     */
    final public String print() {
        return print(0);
    }
    
    /**
     * Return the BST rooted at this node as a human-readable string, indented by <depth> characters
     * @param depth How many characters to indent
     */
    final private String print(int depth) {
        String result="";
        
        // output the key
        result+=Integer.toString(key)+"\n";
        
        // recurse
        if (left!=null)
            result+=RepeatChar(depth)+"L"+left.print(depth+1);
                if (right!=null)
            result+=RepeatChar(depth)+"R"+right.print(depth+1);

        return result;
    }
    
    /**
     * Return a string of <depth> spaces
     * @param depth The number of spaces
     */
    private String RepeatChar(int depth) {
      String result="";
      for (int ii=0; ii<depth; ii++)
            result+=THE_CHAR;
      return result;
    }
    
    // FIELDS
    protected Integer key; // the key stored by this node
    protected BST left;    // the left child of this node
    protected BST right;   // the right child of this node    
    
    final char THE_CHAR = '-'; // the character to repeat
}

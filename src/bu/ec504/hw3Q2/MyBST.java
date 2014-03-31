package bu.ec504.hw3Q2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class MyBST extends BST{

    public MyBST(Integer num) {
        super(num);
        // TODO Auto-generated constructor stub
    }
   
    public MyBST(Integer[] nums) {
        super(nums);
    }
       
    public static ArrayList<Rotation> transform(BST first, BST second) {
        ArrayList<Rotation> LOR = new ArrayList<Rotation>();
        //System.out.println("first key is " + first.key + " second key is " + second.key);
        if (first.key.intValue() == second.key.intValue()) {
            if(first.left != null) {
                //System.out.println("go to left of " +  first.key + " -- " + first.left.key);
                LOR.addAll(transform(first.left, second.left));
            }
            else {
            	//System.out.println(first.key + "'s left is null");
            }
            if(first.right != null) {
                //System.out.println("go to right of " + first.key + " -- " + first.right.key);
                LOR.addAll(transform(first.right, second.right));
            }
            else {
                //System.out.println(first.key + "'s right is null");
            }
            
            return LOR;
        }
       
        else {
        	//System.out.println("before upkey, key is " + first.key + " " + second.key );
        	// do an upKey operation if a mismatch is encountered
        	LOR.addAll(upKey(first, second.key));
        	//System.out.println("upkey success, key is " + first.key + " " + second.key );
        	// do transform again at the current location
            LOR.addAll(transform(first,second));
        }
        return LOR;
    }

    public static ArrayList<Rotation> upKey(BST tree, Integer num) {
        ArrayList<Rotation> LOR = new ArrayList<Rotation>();
        if(tree == null)
            return LOR;
        if (tree.key.intValue() == num.intValue()) {
            ;
        }
        else if (tree.key.intValue() > num.intValue()) {
            LOR.addAll(upKey(tree.left, num));
            BST A = tree.left.left; BST B = tree.left.right; BST C = tree.right;
            Integer oldkey = tree.key; Integer newkey = tree.left.key;
            tree.left = A; tree.right = new BST(oldkey);
            tree.right.left = B; tree.right.right = C;
            tree.key = newkey; 
            Rotation ROT = new Rotation(oldkey, BST.Rotation.RotationType.ZIG);
            LOR.add(ROT);
        }
        else {
            LOR.addAll(upKey(tree.right, num));
            BST A = tree.left; BST B = tree.right.left; BST C = tree.right.right;
            Integer oldkey = tree.key; Integer newkey = tree.right.key;
            tree.left = new BST(oldkey); tree.right = C;
            tree.left.left = A; tree.left.right = B; 
            tree.key = newkey;
            Rotation ROT = new Rotation(oldkey, BST.Rotation.RotationType.ZAG);
            LOR.add(ROT);
        }
        return LOR;   
    }
   
    public static void main(String[] args) throws FileNotFoundException {
        Integer[] nums1 = new Integer[7918];
        for(int i = 0; i < 7918; i++) {
            if (i == 0) nums1[i] = (Integer)1;
            else
                nums1[i] = (Integer)((nums1[i-1].intValue()*7)%7919);
        }
        Integer[] nums2 = new Integer[7918];
        for(int i = 0; i < 7918; i++) {
            if (i == 0) nums2[i] = (Integer)1;
            else
                nums2[i] = (Integer)((nums2[i-1].intValue()*13)%7919);
        }
       
//        Integer[] nums1 = {1,2,3,4,5,6};
//        Integer[] nums2 = {6,5,4,3,2,1};
        BST tree1 = new BST(nums1);
        BST tree2 = new BST(nums2);

        ArrayList<Rotation> LOR = transform(tree1, tree2);
        PrintStream outDecode_file = new PrintStream(new FileOutputStream("problem2submission.txt"));
        for( int i = 0; i < LOR.size(); i++) { 
        	outDecode_file.println(LOR.get(i).print()); 
        }
    	
        System.out.println("The length of LOR is ....." + LOR.size());
        System.out.println("Finished");
       
    }
}


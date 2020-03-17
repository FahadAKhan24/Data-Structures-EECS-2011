import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class BinarySearchTree<E> extends LinkedBinaryTree<E> {
	
	LinkedBinaryTree tree = new LinkedBinaryTree<E>();
	Map<String, Integer> wordcount = new HashMap<String, Integer>();
	
	public void readIn(String fileName) throws FileNotFoundException {
		
	    Scanner sc2 = new Scanner(new File(fileName));
	    while (sc2.hasNextLine()) {
	            Scanner s2 = new Scanner(sc2.nextLine());
	        while (s2.hasNext()) {
	            String s = s2.next().toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
	            if (tree.isEmpty()){
	            	tree.addRoot(s);
	            	
	            }
	            else {
	            	addtotree(s);
	            }
	        }
	    }
	}
	
	public void addtotree(String s){
		insert(tree.root, null, s);

		
	}
	
	public BTNode<E> insert(BTNode<E> node, BTNode<E> parent, String s){
		
		if (node == null){
			return new BTNode<E>( s, parent, null, null);
		}
		
		if (s.equalsIgnoreCase(node.getElement())){
			node.increaseWordCounter();
			return node;
		}
		
		else if (s.compareToIgnoreCase(node.getElement()) > 0){
			if (node.right != null){
				insert(node.right, node, s);
			}
			else{
			tree.addRight(node, s);
			}
			 return node;
		}
		else {
			if (node.left != null){
				insert(node.left, node, s);
			}
			else{
				tree.addLeft(node,s);
			}
			 return node;
		 }

	}
	

	public int maxSearchPath() {
		return height(tree.root);
		 
	}
	
	public int height(BTNode<E> n) {
		if (n == null){
			return -1;
		}
		int leftHeight = height(n.left);
		int rightHeight = height(n.right);
		return Math.max(leftHeight,rightHeight) + 1;
	}
	
	public void printWordsSorted() {
		System.out.println("Print Words Sorted");
		printwords(tree.root);
	}
	
	public void printwords(BTNode<E> node) {
		   if (node == null) return;
		   printwords(node.getLeft());
		   //or any other manipulation of the node
		   System.out.println(node.getElement());
		   printwords(node.getRight());
		   
		}
	
	//comparator taken from stack overflow http://stackoverflow.com/questions/11647889/sorting-the-mapkey-value-in-descending-order-based-on-the-value
	static <K,V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {

			List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());
			Collections.sort(sortedEntries, new Comparator<Entry<K,V>>() {
				@Override
				public int compare(Entry<K,V> e1, Entry<K,V> e2) {
					return e2.getValue().compareTo(e1.getValue());
					}
			});
			return sortedEntries;
	}
	
	public void printTenMostCommonWords() {
		int i = 0;
		fillTree(tree.root);
		System.out.println("Print Ten Most Common Words:");
			for (Map.Entry<String, Integer> values : entriesSortedByValues(wordcount)) {
				
				if(i<10){
				System.out.println(values.getKey() + " - the word appears " + values.getValue() + " times");
				i++;
			}}
		
		
	}

	public void fillTree(BTNode v) {
		if (v == null) return;
		fillTree(v.getLeft());
		wordcount.put(v.getElement(), v.getWordCounter());
		fillTree(v.getRight());
	}
}
	


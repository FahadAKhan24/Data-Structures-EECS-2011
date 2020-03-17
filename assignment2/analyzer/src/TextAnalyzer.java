import java.io.FileNotFoundException;

public class TextAnalyzer {
	public static void main(String[] args) {
		BinarySearchTree BST = new BinarySearchTree();
		try {
			BST.readIn("WisdomForRoad.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(BST.maxSearchPath());

		BST.printWordsSorted();
		BST.printTenMostCommonWords();
	}
}
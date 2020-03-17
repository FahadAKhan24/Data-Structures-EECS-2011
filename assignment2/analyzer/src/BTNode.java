public class BTNode<E> implements Position<E> {
String word;
int wordCounter=0;
BTNode<E> left, right, parent;
public BTNode(String s, BTNode<E> u, BTNode<E> v, BTNode<E> w) { 
	word = s;
	left = w;
	 right = v;
	parent = u;
}
public void setElement(String s) { word = s; }
public String getElement() { return word; }
public int getWordCounter() { return wordCounter; }
public void increaseWordCounter() { wordCounter++; }
public BTNode getLeft() { return left; }
public void setLeft(BTNode<E> v) { left = v; }
public BTNode getRight() { return right; }
public void setRight(BTNode<E> v) { right = v; }
public BTNode getParent() { return parent; }
public void setParent(BTNode<E> v) { parent = v; }
@Override
public E element() throws IllegalStateException {
	// TODO Auto-generated method stub
	return (E) word;
}
}
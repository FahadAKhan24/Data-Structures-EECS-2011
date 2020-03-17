import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedBinaryTree<E> {
	
	protected BTNode<E> root = null;
	private int size = 0;  
	
	public Position<E> root() {
		return root;
	}
	
	public LinkedBinaryTree() {
	}  
	
	protected BTNode validate(Position<E> p) throws IllegalArgumentException {
	    if (!(p instanceof BTNode))
	      throw new IllegalArgumentException("Not valid position type");
	    BTNode<E> node = (BTNode<E>) p;       // safe cast
	    if (node.getParent() == node)     // our convention for defunct node
	      throw new IllegalArgumentException("p is no longer in the tree");
	    return node;
	  }
	
	
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		BTNode<E> node = validate(p);
	    return node.getParent();
	}

	
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		List<Position<E>> snapshot = new ArrayList<>(2);    // max capacity of 2
	    if (left(p) != null)
	      snapshot.add(left(p));
	    if (right(p) != null)
	      snapshot.add(right(p));
	    return snapshot;
	}

	public int numChildren(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		int count=0;
	    if (left(p) != null)
	      count++;
	    if (right(p) != null)
	      count++;
	    return count;
	}

	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		 return numChildren(p) > 0; 
	}

	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return numChildren(p) == 0;
	}

	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return p == root();
	}

	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}
	
	
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		BTNode<E> node = validate(p);
	    return node.getLeft();
	}

	
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		BTNode<E> node = validate(p);
	    return node.getRight();
	}

	
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		Position<E> parent = parent(p);
	    if (parent == null) return null;                  // p must be the root
	    if (p == left(parent))                            // p is a left child
	      return right(parent);                           // (right child might be null)
	    else                                              // p is a right child
	      return left(parent);                            // (left child might be null)
	 
	}

	 public Position<E> addRoot(E e) throws IllegalStateException {
		    if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
		    root = new BTNode((String) e, null, null, null);
		    size = 1;
		    return root;
		  }
	 
	 
	 public Position<E> addLeft(Position<E> p, E e)
             throws IllegalArgumentException {
		 		BTNode<E> parent = validate(p);
		 			if (parent.getLeft() != null)
		 				throw new IllegalArgumentException("p already has a left child");
		 			BTNode<E> child = new BTNode((String) e, parent, null, null);
		 			parent.setLeft(child);
		 			size++;
		 			return child;
	 }
	 
	  public Position<E> addRight(Position<E> p, E e)
              throws IllegalArgumentException {
		  		BTNode<E> parent = validate(p);
		  			if (parent.getRight() != null)
		  				throw new IllegalArgumentException("p already has a right child");
		  			BTNode<E> child = new BTNode((String) e, parent, null, null);
		  			parent.setRight(child);
		  			size++;
		  			return child;
	  }
	
	   
	   public void attach(Position<E> p, LinkedBinaryTree<E> t1,
	                     LinkedBinaryTree<E> t2) throws IllegalArgumentException {
	     BTNode<E> node = validate(p);
	     if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
	     size += t1.size() + t2.size();
	     if (!t1.isEmpty()) {                  // attach t1 as left subtree of node
	       t1.root.setParent(node);
	       node.setLeft(t1.root);
	       t1.root = null;
	       t1.size = 0;
	     }
	     if (!t2.isEmpty()) {                  // attach t2 as right subtree of node
	       t2.root.setParent(node);
	       node.setRight(t2.root);
	       t2.root = null;
	       t2.size = 0;
	     }
	   }

	   public E remove(Position<E> p) throws IllegalArgumentException {
		    BTNode<E> node = validate(p);
		    if (numChildren(p) == 2)
		      throw new IllegalArgumentException("p has two children");
		    BTNode<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight() );
		    if (child != null)
		      child.setParent(node.getParent());  // child's grandparent becomes its parent
		    if (node == root)
		      root = child;                       // child becomes root
		    else {
		      BTNode<E> parent = node.getParent();
		      if (node == parent.getLeft())
		        parent.setLeft(child);
		      else
		        parent.setRight(child);
		    }
		    size--;
		    E temp = (E) node.element();
		    node.setElement(null);                // help garbage collection
		    node.setLeft(null);
		    node.setRight(null);
		    node.setParent(node);                 // our convention for defunct node
		    return temp;
		  }
	   
	   
	 
}

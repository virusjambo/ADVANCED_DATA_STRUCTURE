package avl;
/*a) Left Left Case

T1, T2, T3 and T4 are subtrees.
         z                                      y 
        / \                                   /   \
       y   T4      Right Rotate (z)          x      z
      / \          - - - - - - - - ->      /  \    /  \ 
     x   T3                               T1  T2  T3  T4
    / \
  T1   T2
b) Left Right Case

     z                               z                           x
    / \                            /   \                        /  \ 
   y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
  / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
T1   x                          y    T3                    T1  T2 T3  T4
    / \                        / \
  T2   T3                    T1   T2
c) Right Right Case

  z                                y
 /  \                            /   \ 
T1   y     Left Rotate(z)       z      x
    /  \   - - - - - - - ->    / \    / \
   T2   x                     T1  T2 T3  T4
       / \
     T3  T4
d) Right Left Case

   z                            z                            x
  / \                          / \                          /  \ 
T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      x
    / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
   x   T4                      T2   y                  T1  T2  T3  T4
  / \                              /  \
T2   T3                           T3   T4*/


public class AVLInsertion {

	class Node {
		int key;
		int height;
		Node left = null;
		Node right = null;

		public Node(int key) {

			this.key = key;
		}

	}

	int height(Node node) {

		if (node == null)

			return 0;
		else
			return node.height;
	}

	int balanceFactor(Node z) {
		if (z == null)
			return 0;
		return height(z.left) - height(z.right);

	}

	Node rightRotate(Node z) {
		Node x = z.left;
		Node T3 = x.right;

		x.right = z;
		z.left = T3;

		// Update Heights
		z.height = Math.max(height(z.left), height(z.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		return x;

	}

	Node leftRotate(Node z) {
		Node x = z.right;
		Node T3 = x.left;

		x.left = z;
		z.right = T3;

		// Update Heights
		z.height = Math.max(height(z.left), height(z.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		return x;

	}

	Node AVLINSERT(Node rt, int key) {
		if (rt == null)
			rt = new Node(key);
		if (  key <rt.key)
			rt.left = AVLINSERT(rt.left, key);
		else if (key >rt.key)  {
			rt.right = AVLINSERT(rt.right, key);
		}
		// Update heights
		rt.height = Math.max(height(rt.left), height(rt.right)) + 1;
		// get balance factor
		int balance = balanceFactor(rt);
		if (balance > 1 && key < rt.left.key)
			return rightRotate(rt);

		// Right Right Case
		if (balance < -1 && key > rt.right.key)
			return leftRotate(rt);

		// Left Right Case
		if (balance > 1 && key > rt.left.key) {
			rt.left = leftRotate(rt.left);
			return rightRotate(rt);
		}

		// Right Left Case
		if (balance < -1 && key < rt.right.key) {
			rt.right = rightRotate(rt.right);
			return leftRotate(rt);
		}

		/* return the (unchanged) node pointer */

		return rt;

	}
	void preOrder(Node root)
	{
	    if(root != null)
	    {
	    	System.out.print(root.key);
	        preOrder(root.left);
	        preOrder(root.right);
	    }
	}
	public static  void main(String[] a) {
		Node root = null;
		AVLInsertion avl=new AVLInsertion();
		  /* Constructing tree given in the above figure */
		    root = avl.AVLINSERT(root, 9);
		    root = avl.AVLINSERT(root, 5);
		    root = avl.AVLINSERT(root, 10);
		    root = avl.AVLINSERT(root, 0);
		    root = avl.AVLINSERT(root, 6);
		    root = avl.AVLINSERT(root, 11);
		    root = avl.AVLINSERT(root, -1);
		    root = avl.AVLINSERT(root, 1);
		    root = avl.AVLINSERT(root, 2);
		    root = avl.AVLINSERT(root, 9);
		 
		    /* The constructed AVL Tree would be
		            9
		           /  \
		          1    10
		        /  \     \
		       0    5     11
		      /    /  \
		     -1   2    6
		    */
		 
		   System.out.println("Pre order traversal of the constructed AVL tree is \n");
		  avl.  preOrder(root);
		 

	}
	

}

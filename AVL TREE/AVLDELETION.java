package avl;

public class AVLDELETION {
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
		if (key < rt.key)
			rt.left = AVLINSERT(rt.left, key);
		else if (key > rt.key) {
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

	void preOrder(Node root) {
		if (root != null) {
			System.out.print(root.key + "-");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	Node minValueNode(Node root) {
		Node curret = root;
		while (curret.left != null) {
			curret = curret.left;

		}
		return curret;

	}

	Node AVLDELETE(Node root, int key) {

		if (root == null)
			return root;
		if (key < root.key) {
			root.left = AVLDELETE(root.left, key);

		} else if (key > root.key) {
			root.right = AVLDELETE(root.right, key);

		} else {// We found our node
				// Check if node has only one child
			if (root.left == null || root.right == null) {
				Node temp = root.left == null ? root.right : root.left;
				if (temp == null) {

					root = null;
				} else {
					root = temp;
				}

			} else {
				Node temp = minValueNode(root.right);

				root.key = temp.key;
				root.right = AVLDELETE(root.right, temp.key);

			}

		}
		
		  if (root == null)
		      return root;
		
		  root.height = Math.max(height(root.left), height(root.right)) + 1;
		// get balance factor
		int balance = balanceFactor(root);
		if (balance > 1 && key < root.left.key)
			return rightRotate(root);

		// Right Right Case
		if (balance < -1 && key > root.right.key)
			return leftRotate(root);

		// Left Right Case
		if (balance > 1 && key > root.left.key) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Left Case
		if (balance < -1 && key < root.right.key) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		/* return the (unchanged) node pointer */

		

		return root;

	}

	public static void main(String[] a) {
		Node root = null;
		AVLDELETION avl = new AVLDELETION();
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
		

		/*
		 * The constructed AVL Tree would be 9 / \ 1 10 / \ \ 0 5 11 / / \ -1 2
		 * 6
		 */

		System.out
				.println("Pre order traversal of the constructed AVL tree is \n");
		avl.preOrder(root);
		
		  root =avl. AVLDELETE(root, 10);
		  
		    /* The AVL Tree after deletion of 10
		            1
		           /  \
		          0    9
		        /     /  \
		       -1    5     11
		           /  \
		          2    6
		    */
		 
		  System.out
			.println("\nPre order traversal after deletion of 10 \n");
		   avl. preOrder(root);
		 
		    

	}

}

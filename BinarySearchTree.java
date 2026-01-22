
// Victor Strachan, Jehong Kim, Weihua Huang, Mason Fowble
// CS202 Winter 2025
// Feb. 7 Team Assignment 


// Note, this is not the original file which had much higher quality comments applied to it, but a trimmed down version we put in this project.
// I don't know *why* all the comments were removed, I just found it in this state.
class TreeNode {
    // Package access members
    TreeNode left;
    int data;
    TreeNode right;
    
    public TreeNode(int d){
        data = d;
        left = right = null; //Node has no children
    }
    
    // Insert function that determines whether a data value should be a leftmost or rightmost child node of the current node,
    // if a node already exists there the check is repeated at that child node, if no data exists there then a new child node is made.
    public synchronized void insert(int d){
        if(d < data){
            if(left == null)
                left = new TreeNode(d);
            else
                left.insert(d);
        } else if(d >= data){
            if(right == null)
                right = new TreeNode(d);
            else
                right.insert(d);
        }
    }
}


class Tree {
    private TreeNode root;

    public Tree(){
        root = null;
    }

    // Calls the insert function if nodes already exists, or creates a new node if no nodes exist yet.
    public void insertNode(int d){
        if(root == null)
            root = new TreeNode(d);
        else
            root.insert(d);
    }
    
    public void remove(int d){
        root = remove(d, root);
    }

    // Function for removing a node from the tree and reorganizing the tree accordingly.
    private TreeNode remove(int d, TreeNode node){
        if(node == null)
            return node;
        if(d < node.data)
            node.left = remove(d, node.left);
        else if(d > node.data)
            node.right = remove(d, node.right);
        else if(node.left != null && node.right != null){
            node.data = findMax(node.left).data;
            node.left = remove(node.data, node.left);
        } else
            node = (node.left != null) ? node.left : node.right;
        return node;
    }

    //Function for finding the max value of the tree.
    private TreeNode findMax(TreeNode node){
        if(node != null)
            while(node.right != null)
                node = node.right;
        return node;
    }

    // toString override that prints the entire tree.
    @Override
    public String toString(){
        if(isEmpty())
            return("");
        else
            return(printTree(root));
    } 

    // Function to print the tree, used by toString override.
    private String printTree(TreeNode node){
        StringBuilder buf = new StringBuilder("");
        if(node != null){
            buf.append(printTree(node.left));
            buf.append(node.data + " ");
            buf.append(printTree(node.right));
        }
        return buf.toString();
    }

    // Checks whether the tree is empty.
    public boolean isEmpty(){
        return root == null;
    }

    // Checks how many nodes exist.
    public int size(){
        return sizeHelper(root);
    }

    // Helper function for size().
    public int sizeHelper(TreeNode node){
        if(node == null)
            return 0;
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    // Checks the height of the tree, aka how deep the deepest child node is.
    public int height(){
        return heightHelper(root);
    }

    // Helper function for height().
    public int heightHelper(TreeNode node){
        if(node == null)
            return 0;
        else
            return 1 + Math.max(heightHelper(node.left),
                    heightHelper(node.right));
    }
}

public class BinarySearchTree {
    public static void main(String[] args) {
        Tree tree = new Tree();
    
        tree.insertNode(20);
        tree.insertNode(12);
        tree.insertNode(9);
        tree.insertNode(18);
        tree.insertNode(15);
        tree.insertNode(31);
        tree.insertNode(25);
        tree.insertNode(41);
        tree.insertNode(35);
        tree.insertNode(60);

        System.out.println("The BST contains the following integers:");
        System.out.println(tree);
        System.out.println("The BST contains " + tree.size() + " nodes, and has a height of " +
            tree.height() + '.');

        System.out.println("\nRemoving leaf node 9");
        tree.remove(9);

        System.out.println("The BST now contains the following integers:");
        System.out.println(tree);
        
        System.out.println("\nRemoving node with one child: 18");
        tree.remove(18);
        
        System.out.println("The BST now contains the following integers:");
        System.out.println(tree);

        System.out.println("The BST contains " + tree.size() + " nodes, and has a height of " +
            tree.height() + '.');

        System.out.println("\nAdding back node 9");
        tree.insertNode(9);

        System.out.println("Adding back node 18");
        tree.insertNode(18);

        System.out.println("The BST now contains the following integers:");
        System.out.println(tree);
    
    }
}

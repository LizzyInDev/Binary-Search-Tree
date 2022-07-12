import java.util.Objects;
import java.util.Scanner;

public class BinarySearchTree{
    private BinarySearchTree tree;
    private String[] inorder;
    private String fileInput;
    private int inorderCounter = 0;

    class Node {
        String key;
        Node left;
        Node right;

        public Node (String value) {
            key = value;
            left = null;
            right = null;
        }
    }

    Node root;

    BinarySearchTree() {
        root = null;
    }

    /*
     * produces and empty BST
     * */
    public void create() {
        tree = new BinarySearchTree();
    }

    public String fileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        fileInput = scanner.next();
        return fileInput = fileInput + ".txt";
    }

    /*
     * gets all valid words from readFile() (temp[])
     * passes each word to Node search
     * if the search key does not exist, the program calls insert()
     * */
    public void search(String fileInput) {
        Files files = new Files();
        int error = files.readFile(fileInput);
        if (error == 1)
            System.exit(0);
        String[] temp = files.getInput();

        for (String s : temp) {
            Node exists;
            exists = search(root, s);
            if (!Objects.equals(s, exists))
                tree.insert(s);
        }
        tree.inorder(fileInput);
    }

    /*
     * traverses through the BST to find the search key
     * */
    public Node search(Node root, String key) {
        if (Objects.equals(root, null) || !Objects.equals(key, root.key))
            return root;
        // root.key < key
        if (root.key.compareTo(key) < 0)
            return search(root.right, key);
        else
            return search(root.left, key);
    }

    /*
     * method that calls insertKey()
     * */
    public void insert(String key) {
        root = insertKey(root, key);
    }

    /*
     * traverses through the BST and determines where the key value is to be added
     * */
    public Node insertKey(Node root, String key) {
        if (Objects.equals(root, null)) {
            root = new Node(key);
            return root;
        }

        if (key.compareTo(root.key) < 0)
            root.left = insertKey(root.left, key);
        else if (key.compareTo(root.key) > 0)
            root.right = insertKey(root.right, key);

        return root;
    }

    /*
     * method that calls inorderTraversal()
     * */
    public void inorder(String fileInput) {
        Files files = new Files();
        int length = files.countWords(fileInput);
        inorder = new String[length];

        inorderTraversal(root, inorder);
        files.writeToFile(inorder, fileInput);
    }

    /*
     * performs inorder traversal to get all the nodes in the BST in ascending value
     * calls writeToFile in order to input the values in ascending order
     * */
    public void inorderTraversal(Node root, String[] inorder) {
        if (!Objects.equals(root, null)) {
            inorderTraversal(root.left, inorder);
            saveInorder(root, inorder);
            inorderTraversal(root.right, inorder);
        }
    }

    /*
    * saves inorder format into an array
    * */
    public void saveInorder(Node root, String[] inorder) {
        inorder[inorderCounter++] = root.key;
    }

    /*
     * works as the clean-up operation before the memory terminates
     * */
    public void destroy() {
        System.gc();
    }

}
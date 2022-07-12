public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.create();

        String fileName = bst.fileName();

        bst.search(fileName);

        bst.destroy();
    }
}

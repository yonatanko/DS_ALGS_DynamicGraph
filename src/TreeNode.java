public class TreeNode {
    private TreeNode parent;
    private TreeNode leftChild;
    private TreeNode rightSibling;
    private TreeNode rightChild;
    private final int key;
    private DoublyLinkedList.Node<TreeNode> nodeInList;

    public TreeNode(int key){
        this.parent = null;
        this.leftChild = null;
        this.rightSibling = null;
        this.key = key;
        this.rightChild = null;
        this.nodeInList = null;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(TreeNode rightSibling) {
        this.rightSibling = rightSibling;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getKey() {
        return key;
    }

    public DoublyLinkedList.Node<TreeNode> getNodeInList() {
        return nodeInList;
    }

    public void setNodeInList(DoublyLinkedList.Node<TreeNode> nodeInList) {
        this.nodeInList = nodeInList;
    }

    public void addLeftChild (TreeNode leftChild, TreeNode parent){
        if (this.leftChild == null) {
            this.leftChild = leftChild;
            this.leftChild.parent = parent;
            this.rightChild = leftChild;
        }
        else{
            leftChild.rightSibling = this.leftChild;
            this.leftChild = leftChild;
            this.leftChild.parent = parent;
        }
    }

    public void addRightSibling (TreeNode rightSibling) {
        rightSibling.parent = this.parent;
        this.parent.rightChild.rightSibling = rightSibling;
        this.parent.rightChild = rightSibling;
    }
}

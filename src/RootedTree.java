import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    private TreeNode root;

    public RootedTree(){
        this.root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void printByLayer(DataOutputStream out) throws IOException{
        if (this.root == null)
            return;
        else {
            DoublyLinkedList<TreeNode> nodes = new DoublyLinkedList<>();
            out.writeBytes(String.valueOf(this.root.getKey()));
            if (this.root.getLeftChild() != null)
                out.writeBytes(System.lineSeparator());
            int numNextLayer = 0;
            int numCurrentLayer = insertAllChildren(this.root, nodes);
            try {
                while (nodes.getTail() != null) {
                    while (numCurrentLayer > 0) {
                        if (nodes.getTail().getValue().getLeftChild() != null) {
                            numNextLayer += insertAllChildren(nodes.getTail().getValue(), nodes);
                        }
                        if (numCurrentLayer == 1)
                            out.writeBytes(String.valueOf(nodes.getTail().getValue().getKey()));
                        else
                            out.writeBytes(nodes.getTail().getValue().getKey() + ",");
                        nodes.setTail(nodes.getTail().getPrev());
                        numCurrentLayer -= 1;
                    }
                    if (nodes.getTail() != null)
                        out.writeBytes(System.lineSeparator());
                    numCurrentLayer = numNextLayer;
                    numNextLayer = 0;
                }
            }catch (IOException e){};

        }
    }

    public int insertAllChildren(TreeNode parent, DoublyLinkedList<TreeNode> nodes){
        TreeNode temp = parent.getLeftChild();
        int counter = 0;
        while (temp != null){
            temp.setNodeInList(nodes.insert(temp));
            temp = temp.getRightSibling();
            counter+=1;
        }
        return counter;
    }

    public void preorderPrint(DataOutputStream out) throws IOException {
        TreeNode lastNode = this.root;
        TreeNode temp = this.root;
        while (temp != null){
            temp = temp.getRightChild();
            if (temp != null)
                lastNode = lastNode.getRightChild();
        }
        preorderPrint(this.root, lastNode, out);
    }

    private void preorderPrint(TreeNode node, TreeNode lastNode, DataOutputStream out) throws IOException {
        if (node == null)
            return;
        else{
            try {
                if(node == lastNode)
                    out.writeBytes(String.valueOf(node.getKey()));
                else
                    out.writeBytes((node.getKey()) + ",");
                preorderPrint(node.getLeftChild(), lastNode, out);
                preorderPrint(node.getRightSibling(), lastNode, out);
            } catch (IOException e){};
        }
    }
}

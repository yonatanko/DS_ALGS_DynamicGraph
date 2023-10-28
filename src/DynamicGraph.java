public class DynamicGraph {
    private DoublyLinkedList<GraphNode> graphNodesList;
    private DoublyLinkedList<GraphNode> BFS_queue;

    public DoublyLinkedList<GraphNode> getGraphNodesList() {
        return graphNodesList;
    }

    public DoublyLinkedList<GraphNode> getBFS_queue() {
        return BFS_queue;
    }

    public DoublyLinkedList<GraphNode> getDFS_queue() {
        return DFS_queue;
    }

    private DoublyLinkedList<GraphNode> DFS_queue;

    public DynamicGraph(){
        this.graphNodesList = new DoublyLinkedList<>();
        this.BFS_queue = new DoublyLinkedList<>();
        this.DFS_queue = new DoublyLinkedList<>();
    }

    public GraphNode insertNode(int nodeKey){
        GraphNode node = new GraphNode(nodeKey);
        node.setListNode(graphNodesList.insert(node));
        return node;
    }

    public void deleteNode(GraphNode node){
        if (node.getOutEdges().getLength() == 0 && node.getInEdges().getLength() == 0)
            graphNodesList.delete(node.getListNode());
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        GraphEdge edge = new GraphEdge(from,to);
        edge.setOutEdgeInList(from.getOutEdges().insert(edge));
        edge.setInEdgeInList(to.getInEdges().insert(edge));
        return edge;
    }

    public void deleteEdge (GraphEdge edge){
        edge.getFrom().getOutEdges().delete(edge.getOutEdgeInList());
        edge.getTo().getInEdges().delete(edge.getInEdgeInList());
    }

    public void bfsInitialization(GraphNode source){
        if (source != null) {
            DoublyLinkedList.Node<GraphNode> temp = graphNodesList.getHead();
            GraphNode vertex;
            while (temp != null) {
                vertex = temp.getValue();
                if (vertex.getKey() != source.getKey()) {
                    vertex.setColor("white");
                    vertex.setDistance(Integer.MAX_VALUE);
                    vertex.setPi(null);
                }
                temp = temp.getNext();
            }
            source.setColor("gray");
            source.setDistance(0);
            source.setPi(null);
            BFS_queue.insert(source);
        }
    }

    public RootedTree bfs(GraphNode source){
        RootedTree shortestPathsTree = new RootedTree();
        bfsInitialization(source);
        boolean firstNeighbour;
        DoublyLinkedList<TreeNode> currentInTree = new DoublyLinkedList<>();
        currentInTree.insert(new TreeNode(BFS_queue.getHead().getValue().getKey()));
        TreeNode newNode;
        while (BFS_queue.getLength() > 0){
            GraphNode u = BFS_queue.extract();
            newNode = currentInTree.extract();
            if (u.equals(source))
                shortestPathsTree.setRoot(newNode);
            DoublyLinkedList.Node<GraphEdge> position = u.getOutEdges().getHead();
            GraphNode neighbour;
            firstNeighbour = true;
            while (position != null){
                neighbour = position.getValue().getTo();
                if (neighbour.getColor().equals("white")){
                    neighbour.setColor("gray");
                    neighbour.setDistance(u.getDistance()+1);
                    neighbour.setPi(u);
                    BFS_queue.insert(neighbour);
                    if (firstNeighbour) {
                        firstNeighbour = false;
                        TreeNode node = new TreeNode(neighbour.getKey());
                        newNode.addLeftChild(node,newNode);
                        currentInTree.insert(node);
                        newNode = newNode.getLeftChild();
                    }
                    else{
                        TreeNode node = new TreeNode(neighbour.getKey());
                        newNode.addRightSibling(node);
                        currentInTree.insert(node);
                        newNode = newNode.getRightSibling();
                    }
                }
                position = position.getNext();
            }
            u.setColor("black");
        }
        return shortestPathsTree;
    }

    public void dfsInitialization(boolean isTransposed){
        DoublyLinkedList.Node<GraphNode> position = this.graphNodesList.getHead();
        if (!isTransposed)
            this.DFS_queue = new DoublyLinkedList<>();
        while (position != null){
            position.getValue().setColor("white");
            position.getValue().setPi(null);
            position = position.getNext();
        }
    }

    public DoublyLinkedList<RootedTree> dfs(boolean isTransposed){
        dfsInitialization(isTransposed);
        int time = 0;
        DoublyLinkedList.Node<GraphNode> position;
        if (!isTransposed) {
            position = this.graphNodesList.getHead();
        }
        else{
            position = this.DFS_queue.getHead();
        }
        DoublyLinkedList<RootedTree> trees = new DoublyLinkedList<>();

        while (position != null){
            if (position.getValue().getColor().equals("white")){
                RootedTree newTree = new RootedTree();
                TreeNode root = new TreeNode(position.getValue().getKey());
                newTree.setRoot(root);
                time = dfs_Visit(position.getValue(),time,root, isTransposed);
                trees.insert(newTree);
            }
            position = position.getNext();
        }
        return trees;
    }

    public int dfs_Visit(GraphNode vertex, int time, TreeNode root, boolean isTransposed){
        time = time + 1;
        vertex.setDiscovery(time);
        vertex.setColor("gray");
        DoublyLinkedList.Node<GraphEdge> position;
        GraphNode neighbour;
        if (!isTransposed)
            position = vertex.getOutEdges().getHead();
        else
            position = vertex.getInEdges().getHead();
        boolean firstNeighbour = true;
        while (position != null){
            if (isTransposed)
                neighbour = position.getValue().getFrom();
            else
                neighbour = position.getValue().getTo();
            if (neighbour.getColor().equals("white")){
                if (firstNeighbour){
                    firstNeighbour = false;
                    neighbour.setPi(vertex);
                    TreeNode neighbourAsTreeNode = new TreeNode(neighbour.getKey());
                    root.addLeftChild(neighbourAsTreeNode,root);
                    time = dfs_Visit(neighbour,time,neighbourAsTreeNode,isTransposed);
                }

                else{
                    neighbour.setPi(vertex);
                    TreeNode neighbourAsTreeNode = new TreeNode(neighbour.getKey());
                    root.getLeftChild().addRightSibling(neighbourAsTreeNode);
                    time = dfs_Visit(neighbour,time,neighbourAsTreeNode,isTransposed);
                }
            }
            position = position.getNext();
        }
        vertex.setColor("black");
        time = time + 1;
        vertex.setRetraction(time);
        if (!isTransposed)
            this.DFS_queue.insert(vertex);
        return time;
    }

   public RootedTree scc(){
        dfs(false);
        DoublyLinkedList<RootedTree> trees = dfs(true);
        RootedTree newTree = new RootedTree();
        TreeNode root = new TreeNode(0);
        newTree.setRoot(root);
        boolean firstTree = true;
        DoublyLinkedList.Node<RootedTree> position = trees.getTail();
        TreeNode current = root;
        while (position != null){
            if (firstTree){
                firstTree = false;
                root.addLeftChild(position.getValue().getRoot(),root);
                current = root.getLeftChild();
            }
            else{
                current.addRightSibling(position.getValue().getRoot());
                current = current.getRightSibling();
            }
            position = position.getPrev();
        }
        return newTree;
   }
}

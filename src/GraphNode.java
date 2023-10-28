public class GraphNode{
    private DoublyLinkedList<GraphEdge> inEdges;
    private DoublyLinkedList<GraphEdge> outEdges;
    private final int key;
    private DoublyLinkedList.Node<GraphNode> nodeInList;
    private String color;
    private int distance;
    private GraphNode pi;
    private int discovery;
    private int retraction;

    public GraphNode(int nodeKey){
        this.inEdges = new DoublyLinkedList<>();
        this.outEdges = new DoublyLinkedList<>();
        this.key = nodeKey;
        this.nodeInList = null;
    }

    public DoublyLinkedList<GraphEdge> getInEdges() {
        return inEdges;
    }


    public DoublyLinkedList<GraphEdge> getOutEdges() {
        return outEdges;
    }


    public int getKey() {
        return key;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public GraphNode getPi() {
        return pi;
    }

    public void setPi(GraphNode pi) {
        this.pi = pi;
    }

    public int getDiscovery() {
        return discovery;
    }

    public void setDiscovery(int discovery) {
        this.discovery = discovery;
    }

    public int getRetraction() {
        return retraction;
    }

    public void setRetraction(int retraction) {
        this.retraction = retraction;
    }

    public int getOutDegree(){
        return outEdges.getLength();
    }

    public int getInDegree(){
        return inEdges.getLength();
    }

    public DoublyLinkedList.Node<GraphNode> getListNode (){
        return this.nodeInList;
    }

    public void setListNode (DoublyLinkedList.Node<GraphNode> node){
        this.nodeInList = node;
    }

}

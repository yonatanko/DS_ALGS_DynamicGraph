public class GraphEdge {
    private GraphNode from;
    private GraphNode to;
    private DoublyLinkedList.Node<GraphEdge> outEdgeInList;
    private DoublyLinkedList.Node<GraphEdge> inEdgeInList;

    public GraphNode getFrom() {
        return from;
    }

    public void setFrom(GraphNode from) {
        this.from = from;
    }

    public GraphNode getTo() {
        return to;
    }

    public void setTo(GraphNode to) {
        this.to = to;
    }

    public DoublyLinkedList.Node<GraphEdge> getOutEdgeInList() {
        return outEdgeInList;
    }

    public void setOutEdgeInList(DoublyLinkedList.Node<GraphEdge> outEdgeInList) {
        this.outEdgeInList = outEdgeInList;
    }

    public DoublyLinkedList.Node<GraphEdge> getInEdgeInList() {
        return inEdgeInList;
    }

    public void setInEdgeInList(DoublyLinkedList.Node<GraphEdge> inEdgeInList) {
        this.inEdgeInList = inEdgeInList;
    }

    public boolean isTransposed() {
        return isTransposed;
    }

    public void setTransposed(boolean transposed) {
        isTransposed = transposed;
    }

    private boolean isTransposed;

    public GraphEdge (GraphNode from, GraphNode to){
        this.from = from;
        this.to = to;
        this.outEdgeInList = null;
        this.inEdgeInList = null;
        this.isTransposed = false;
    }


}

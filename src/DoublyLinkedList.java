public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        length = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public Node<T> insert (T object){
        Node<T> node = new Node<>(object);
        node.next = this.head;
        if (this.head != null)
            this.head.prev = node;
        if (this.head == null)
            this.tail = node;
        this.head = node;
        node.prev = null;
        this.length+=1;
        return node;
    }


    public void delete (Node<T> node){
        if (node.prev != null)
            node.prev.next = node.next;
        else
            this.head = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        this.length-=1;
        node.next = null;
        node.prev = null;
    }

    public T extract (){
        T node = this.tail.getValue();
        this.tail = this.tail.prev;
        if (this.tail != null)
            this.tail.next = null;
        else{
            this.tail = null;
            this.head = null;
        }
        this.length -=1;
        return node;

    }

    @Override
    public String toString() {
        Node<T> temp = this.head;
        String t = "";
        while (temp!= null){
            t += temp.getValue()+ " ";
            temp = temp.next;
        }
        return t;
    }

    public static class Node<T> {
        private Node<T> next = null;
        private Node<T> prev = null;
        private final T value;

        public Node(T value){
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrev() {
            return prev;
        }
    }
}
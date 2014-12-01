/**
 * Created by z_wu on 11/30/2014.
 */
public class RunList {
    Node head;
    Node tail;
    int size = 0;
    public RunList(){}
    public  void add(Run element){
        final Node t = tail;
        final Node n = new Node(t, element, null);
        tail = n;
        if(n == null)
            head = n;
        else
            t.next = n;
        size ++;
    }
    public int size() {
        return size;
    }
    public static class Node{
        Run item;
        Node next;
        Node prev;
        Node(Node prev, Run element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public static class Run{
        private PixImage.Color c;
        private int length;

        public Run(PixImage.Color c,int length){
            this.c = c;
            this.length = length;
        }
    }
}

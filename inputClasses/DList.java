package org.example;

public class DList extends MyList {

    private Node tail;

    public DList(){
        this.head = new Node("HEAD");
        this.tail = new Node("TAIL");
        this.head.setNext(tail);
        this.tail.setPrev(head);
    }


    @Override
    public boolean isEmpty() {
        return this.head.getNext().equals(this.tail) && this.tail.getPrev().equals(this.head); // Wenn Doppelt verkettete liste leer ist, gibt es nur head und tail und diese zeigen aufeinander
    }



    @Override
    public void addFirst(Object value) {
        if (this.isEmpty()){
            Node first = new Node(value);
            this.head.setNext(first);
            first.setPrev(this.head);
            first.setNext(this.tail);
            this.tail.setPrev(first);
        }
        else {
            Node first = new Node(value);
            Node temp = this.head.getNext();
            this.head.setNext(first);
            first.setNext(temp);
            temp.setPrev(first);
            first.setPrev(this.head);
        }
    }

    @Override
    public Object removeFirst() throws ListEmptyException {
        if (this.isEmpty()) { throw new ListEmptyException(); }
        else {
            Object temp = this.head.getNext().getValue();
            this.head.setNext(this.head.getNext().getNext());
            this.head.getNext().setPrev(this.head);
            return temp;
        }
    }


    @Override
    public Object getLast() throws ListEmptyException {
        if (this.isEmpty()){ throw new ListEmptyException();}
        else { return this.tail.getPrev().getValue(); }

    }


    @Override
    public void addLast(Object value) {
        Node newLast = new Node(value);
        Node last = this.tail.getPrev();
        last.setNext(newLast);
        newLast.setPrev(last);
        newLast.setNext(this.tail);
        this.tail.setPrev(newLast);

    }


    @Override
    public Object removeLast() throws ListEmptyException {
        if (this.isEmpty()){ throw new ListEmptyException(); }
        else{
            Object temp = this.getLast();
            Node newLast = this.tail.getPrev().getPrev();
            newLast.getPrev().setNext(newLast);
            newLast.setNext(this.tail);
            this.tail.setPrev(newLast);
            return temp;

        }
    }


    @Override
    public String toString(){
        if (this.isEmpty()){ return "[" + "HEAD" + "]" + "<->" + "[" + "TAIL" + "]"; }
        else {return this.head.toString(); }

    }
}

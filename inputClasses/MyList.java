package org.example;

public class MyList implements List {

    protected Node head;


    public MyList(){
        this.head = new Node("HEAD");
    }

    
    public MyList(Node node){
        this.head = new Node("HEAD");
        this.head.setNext(node);
    }

   
    public MyList(Object value) {
        this.head = new Node("HEAD");
        this.head.setNext(new Node(value));
    }

    public MyList(Object ...values){
        this.head = new Node("HEAD");
        Node cur = this.head;
        for (int i = 0; i < values.length -1 ; i++) {
            cur.setNext(new Node(values[i]));
            cur = cur.getNext();

        }
    }

    public boolean isEmpty() {
        return this.head.getNext() == null;
    }


    public void addFirst(Object value) {
        if(isEmpty()){
            this.head.setNext(new Node(value));
        }
        else{
            Node temp = this.head.getNext();
            this.head.setNext(new Node(value, temp));  // new Node (value, next)
        }
    }

    public Object getFirst() throws ListEmptyException{
        if (this.isEmpty()){ throw new ListEmptyException(); }
        else{  return this.head.getNext().getValue(); }

    }


    public Object removeFirst() throws ListEmptyException {
        if (this.isEmpty()){ throw new ListEmptyException(); }
        else{
            Object temp = this.getFirst();
            this.head.setNext(this.head.getNext().getNext());
            return temp;
        }

    }

    public void addLast(Object value) {
        if(this.isEmpty()){ this.head.setNext(new Node(value)); }
        else { this.head.addLast(value); }
    }


    public Object getLast() throws ListEmptyException{
        if(this.isEmpty()){ throw new ListEmptyException(); }
        else{ return this.head.getLast();}
    }

    public Object removeLast() throws ListEmptyException{
        if(this.isEmpty()){ throw new ListEmptyException();}
        else{ return this.head.removeLast();}
    }


    public String toString() {
        if(this.isEmpty()){ return "[" + "HEAD" + "]"; }
        else{ return this.head.toString(); }

    }
}

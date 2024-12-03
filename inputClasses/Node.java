package org.example;

public class Node {

    private Object value;
    private Node next;
    private Node prev;

   
    public Node(Object value, Node next) {
        this.value = value;
        this.next = next;

    }

  
    public Node(Object value){

        this.value = value;
    }

   
    public Node(){ }


    public Node(Object value, Node next, Node prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
        next.setPrev(this);
        prev.setNext(this);
    }


    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Object getValue() {
        return value;
    }


    public void setValue(Object value) {
        this.value = value;
    }


    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }


    public void addLast(Object value) {
        if(this.next == null){
            this.next = new Node(value);
        }
        else{ this.next.addLast(value); }
    }


    public Node getLastElement() {
        if(this.next == null){ return this; }
        else{ return this.next.getLastElement(); }
    }

    public Object getLast() {
        if(this.next == null){ return this.value; }
        else{ return this.next.getLast(); }
    }


    public Object removeLast() {
        if(this.next.next == null){
            Object temp = this.next.value;
            this.next = null;
            return temp;
        }
        else{ return this.next.removeLast(); }
    }

    public String toString(){
        if(this.next == null ){
            return "[" + this.value + "]";
        }
        else{
            if ((this.next.prev != null && this.value == "HEAD") || this.prev != null){ return "[" + this.value +"]" + "<->" + this.next.toString(); }    // Wenn ein Node Referenzen auf Vorgänger hat, muss es sich um eine Doppelt verkettete Liste handeln
            else { return "[" + this.value +"]" + "->" + this.next.toString(); }
        }
    }
}

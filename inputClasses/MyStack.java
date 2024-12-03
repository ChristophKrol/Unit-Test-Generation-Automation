package org.example;

public class MyStack implements Stack {
    private MyList top;

    public MyStack(){
        this.top = new MyList();
    }


    public boolean isEmpty(){ return this.top.isEmpty(); }


    public void push(Object o){
        Node n = new Node(o, this.top.head.getNext());
        this.top = new MyList(n);
    }


    public Object pop() throws ListEmptyException {
        if (this.isEmpty()){ throw new ListEmptyException(); }
        else{
            Object temp = this.top.head.getNext().getValue();
            this.top = new MyList(this.top.head.getNext().getNext());
            return temp;
        }

    }


    public String toString() {
        return top.toString();
    }



}

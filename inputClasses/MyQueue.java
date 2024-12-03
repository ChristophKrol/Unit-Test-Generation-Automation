package org.example;

public class MyQueue implements Queue {
    private DList queue;

    public MyQueue(){
        this.queue = new DList();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }


    @Override
    public void enter(Object enter) {
        this.queue.addLast(enter);

    }

    @Override
    public Object leave() throws ListEmptyException {
        if (this.isEmpty()) { throw new ListEmptyException(); }
        else {
            Object temp = this.queue.getFirst();
            this.queue.removeFirst();
            return temp;
        }

    }

    
    public String toString() {
        return this.queue.toString();
    }
}

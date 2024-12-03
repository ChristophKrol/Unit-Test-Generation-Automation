package org.example;

public interface Stack {

    public boolean isEmpty();

   
    public void push(Object o );

    public Object pop() throws ListEmptyException;
}

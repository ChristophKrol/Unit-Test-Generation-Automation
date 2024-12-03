package org.example;

public interface Queue {

    public boolean isEmpty();

    public void enter(Object enter);


    public Object leave() throws ListEmptyException;
}

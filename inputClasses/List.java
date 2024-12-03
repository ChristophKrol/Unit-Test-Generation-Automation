package org.example;

public interface List {
  
    public boolean isEmpty();

   
    public void addFirst(Object value);

   
    public Object getFirst() throws ListEmptyException;

    
    public Object removeFirst() throws ListEmptyException;

    public void addLast(Object value);

   
    public Object getLast() throws ListEmptyException;

    public Object removeLast() throws ListEmptyException;
}

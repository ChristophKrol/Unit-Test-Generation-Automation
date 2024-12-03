package org.example;


public class BinaryTree <T extends Comparable<T>> {


    private TreeNode<T> root;

    public BinaryTree() {
    }


    public BinaryTree(T value) {
        this.root = new TreeNode<T>(value);
    }


    public BinaryTree( AVLNode<T> root ) {
        this.root = root;
    }


    public boolean isEmpty() {
        return this.root == null;
    }


    public TreeNode<T> getRoot() {
        return this.root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }



    public boolean insert(T value) {
        if (this.isEmpty()) {
            this.root = new TreeNode<T>(value);
            return true;
        } else {
            return this.root.insert(value);
        }
    }


    public boolean find(T value) {
        if (this.isEmpty()) {
            return false;
        } else {
            return this.root.find(value);
        }
    }

    public T findMinRekursiv() throws EmptyTreeException {
        if (this.isEmpty()) {
            throw new EmptyTreeException();
        } else {
            return this.root.findMinRekursiv();
        }
    }


    public T findMinIterativ() throws EmptyTreeException {
        if (this.isEmpty()) {
            throw new EmptyTreeException();
        } else {
            return this.root.findMinIterativ();
        }
    }


    public T findMaxElement() throws EmptyTreeException {
        if (this.isEmpty()) {
            throw new EmptyTreeException();
        } else {
            return this.root.findMaxElement();
        }
    }


    public boolean remove(T value) throws EmptyTreeException {
        if (this.isEmpty()) { throw new EmptyTreeException(); }
        // Mit find() gucken, ob der Knoten überhaupt erstmal existiert
        else if (!this.find(value)){ return false; }
        else if (this.root.hasNoChildren() && this.root.getKey().compareTo(value) == 0){
            this.root = null;
            return true;
        }
        else {
            return this.root.delete(value);
        }
    }


    public String traverse(Traversal traversal) {
        return this.root.traverse(traversal);
    }


    public String toString() {
        if (this.isEmpty()) { return null; }
        else { return this.root.toString(); }
    }
}





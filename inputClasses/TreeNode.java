package org.example;


public class TreeNode <T extends Comparable> {

   
    private T key;

  
    private TreeNode <T> left;

    private TreeNode <T> right;

    public TreeNode(T value) {
        this.key = value;
    }

    public T getKey() {
        return this.key;
    }

    
    public void setKey(T key) {
        this.key = key;
    }

    
    public TreeNode<T> getLeft() {
        return left;
    }

    
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

   
    public TreeNode<T> getRight() {
        return right;
    }

    
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

   
    public boolean insert(T value) {
        // Value kleiner als Knoten
        if (value.compareTo(this.getKey()) < 0){
            if (this.getLeft() == null){
                this.setLeft(new TreeNode<T>(value));
                return true;
            }
            else{
                return this.getLeft().insert(value);
            }
        }
        else {
            if (this.getRight() == null){
                this.setRight(new TreeNode<T>(value));
                return true;
            }
            else {
                return this.getRight().insert(value);
            }
        }
    }

   
    public boolean find(T value) {
        // Ist Knotenwert gleich value
        if (this.key.compareTo(value) == 0){
            return true;
        }
        // this.key größer als value -> value muss links stehen
        else if (this.key.compareTo(value) == 1){
            if (this.getLeft() != null) {
                return this.getLeft().find(value);
            }
            else{ return false; }
        }
        else {
            if (this.getRight() != null){
                return this.getRight().find(value);
            }
            else { return false; }

        }
    }

   
    private TreeNode<T> findNode(T value) {
        // Ist Knotenwert gleich value
        if (this.key.compareTo(value) == 0){
            return this;
        }
        // this.key größer als value -> value muss links stehen
        else if (this.key.compareTo(value) == 1){
            if (this.getLeft() != null) {
                return this.getLeft().findNode(value);
            }
            else{ return null; }
        }
        else {
            if (this.getRight() != null){
                return this.getRight().findNode(value);
            }
            else { return null; }

        }
    }

    
    private TreeNode<T> findParentOf(T value){
        if (this.getLeft().getKey().compareTo(value) == 0 || this.getRight().getKey().compareTo(value) == 0){
            return this;
        }
        else if (this.getKey().compareTo(value) == 1){
            return this.getLeft().findParentOf(value);
        }
        else{
            return this.getRight().findParentOf(value);
        }
    }


    
    public T findMinRekursiv(){
        if (this.getLeft() == null){ return this.key; }
        else { return this.getLeft().findMinRekursiv(); }
    }

    
    public T findMinIterativ() {
        T min = this.key;
        TreeNode<T> cur = this;
        while (cur.getLeft() != null){
            cur = cur.left;
        }
        min = cur.key;
        return min;
    }

    
    public T findMaxElement() {
        if (this.getRight() == null){ return this.getKey(); }
        else { return this.getRight().findMaxElement(); }
    }
    private TreeNode<T> findMaxNode() {
        if (this.getRight() == null){ return this; }
        else { return this.getRight().findMaxNode(); }
    }

    public boolean delete(T value) {
        TreeNode<T> toDelete = this.findNode(value);
        TreeNode<T> toDeleteParent = this.findParentOf(value);
        if (toDelete.hasNoChildren()){
            if (toDeleteParent.getRight().equals(toDelete)){
                toDeleteParent.setRight(null);
            }
            else{
                toDeleteParent.setLeft(null);
            }
            return true;
        }
        else if (toDelete.getLeft() != null && toDelete.getRight() == null ){
            if (toDeleteParent.getRight().equals(toDelete)){
                toDeleteParent.setRight(toDelete.getLeft());
            }
            else{
                toDeleteParent.setLeft(toDelete.getLeft());
            }
            return true;
        }
        else if (toDelete.getRight() != null && toDelete.getLeft() == null){
            if (toDeleteParent.getRight().equals(toDelete)){
                toDeleteParent.setRight(toDelete.getRight());
            }
            else{
                toDeleteParent.setLeft(toDelete.getRight());
            }
            return true;
        }
        // Wenn toDelete 2 Kinder hat
        else {
            TreeNode<T> maxNode = toDelete.left.findMaxNode();
            toDelete.delete(maxNode.key);
            if (toDeleteParent.getRight().equals(toDelete)){
                toDeleteParent.setRight(maxNode);
            }
            else{
                toDeleteParent.setLeft(maxNode);
            }
            maxNode.setRight(toDelete.getRight());
            maxNode.setLeft(toDelete.getLeft());
            return true;
        }
    }


    public boolean hasNoChildren() { return this.getLeft() == null && this.getRight() == null; }



    public String toString(){
        if (this.getLeft() == null && this.getRight() == null){
            return  " [" + this.key + "] " ;
        }
        else if (this.getLeft() == null){
            return "( " + "[" + this.key + "] " + this.getRight().toString() + " )";
        }
        else if (this.getRight() == null){
            return "( " + this.getLeft().toString()  + "[" + this.key + "]" +  " )";
        }
        else {
            return "( " + this.getLeft().toString() + " " + "[" + this.key + "]" + " " +  this.getRight().toString() + " )";
        }
    }


    public String traverse(Traversal traverse){
        String traversal ="";
        switch (traverse){
            case INORDER -> {
                traversal = this.inorder();
            }
            case PREORDER -> {
                traversal = this.preorder();
            }
            case POSTORDER -> {
                traversal = this.postorder();
            }

        }
        return traversal;
    }

   private String inorder(){
       if (this.getLeft() == null && this.getRight() == null) {
           return " [" + this.getKey() + "] ";
       }
       else if (this.getLeft() == null) {
           return " [" + this.getKey() + "] " + this.getRight().inorder();
       }
       else if (this.getRight() == null) {
           return this.getLeft().inorder() + " [" + this.getKey() + "] ";
       }
       else {
           return this.getLeft().inorder() +  " [" + this.getKey() + "] " + this.getRight().inorder() ;
       }
   }


   private String preorder(){
       if (this.getLeft() == null && this.getRight() == null) {
           return " [" + this.getKey() + "] ";
       }
       else if (this.getLeft() == null) {
           return " [" + this.getKey() + "] " + this.getRight().preorder();
       }
       else if (this.getRight() == null) {
           return  " [" + this.getKey() + "] " + this.getLeft().preorder();
       }
       else {
           return " [" + this.getKey() + "] " +  this.getLeft().preorder() + this.getRight().preorder() ;
       }


   }


   private String postorder() {
       if (this.getLeft() == null && this.getRight() == null) {
           return " [" + this.getKey() + "] ";
       }
       else if (this.getLeft() == null) {
           return this.getRight().postorder() + " [" + this.getKey() + "] ";
       }
       else if (this.getRight() == null) {
           return this.getLeft().postorder() + " [" + this.getKey() + "] ";
       }
       else {
           return this.getLeft().postorder() + this.getRight().postorder() + " [" + this.getKey() + "] ";
       }

   }



}

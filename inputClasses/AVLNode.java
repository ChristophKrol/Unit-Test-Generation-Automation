package org.example;

public class AVLNode<T extends Comparable<T>> extends TreeNode<T> {


    private AVLNode<T> right;


    private AVLNode<T> left;

    private int balance;

    private int height;


    public AVLNode(T value) {
        super(value);
    }

    @Override
    public AVLNode<T> getLeft() {
        return left;
    }


    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }


    @Override
    public AVLNode<T> getRight() {
        return right;
    }


    public void setRight(AVLNode<T> right) {
        this.right = right;
    }


    public int getBalance() {
        return this.balance;
    }


    public void setBalance(int balance) {
        this.balance = balance;
    }


    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }


    public int getTotalNumberOfChildren(){
        if (this.getLeft() == null && this.getRight() == null){
            return 1;
        }
        else if (this.getLeft() == null && this.getRight() != null){
            return 1 + this.getRight().getTotalNumberOfChildren();
        }
        else if (this.getLeft() != null && this.getRight() == null){
            return  1 + this.getLeft().getTotalNumberOfChildren();
        }
        else {
            return 1 + this.getLeft().getTotalNumberOfChildren() + this.getRight().getTotalNumberOfChildren();
        }
    }
}

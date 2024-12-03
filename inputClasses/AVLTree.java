package org.example;

public class AVLTree <T extends Comparable<T>>{


    private AVLNode<T> root;

   
    public AVLTree (T value){
        this.root = new AVLNode<>(value);
    }

   
    public AVLNode<T> getRoot() {
        return root;
    }

    
    public void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    
    public int height() {
        if (root == null) return 0;
        return root.getHeight();
    }

    
    public boolean isEmpty() {
        return this.root == null;
    }

    
    public boolean find(T value) {
        return contains(root, value);
    }

    
    private boolean contains(AVLNode<T> node, T value) {
        if (node == null) return false;

        // Vergleiche Wert mit Wert im Knoten
        int cmp = value.compareTo(node.getKey());

        // Wenn kleiner, gehe in linken Teilbaum
        if (cmp < 0) return contains(node.getLeft(), value);

        // Gehe in rechten Teilbaum, wenn groeßer
        if (cmp > 0) return contains(node.getRight(), value);

        // Wert gefunden.
        return true;
    }

    
    public boolean insert(T value) {
        if (value == null) return false;
        if (!contains(root, value)) {
            root = insert(root, value);

            return true;
        }
        return false;
    }

    
    private AVLNode<T> insert(AVLNode<T> node, T value) {

        if (node == null) return new AVLNode<>(value);

        // Vergleiche value mit dem Key vom node
        int cmp = value.compareTo(node.getKey());

        // Wenn kleiner, dann weiter im linken Teilbaum fortfahren
        if (cmp < 0) {
            node.setLeft(insert(node.getLeft(), value));

            // Wenn größer, dann im rechten Teilbaum fortfahren
        } else {
            node.setRight(insert(node.getRight(), value));
        }

        // Update balance und Höhe.
        update(node);

        // Rebalanzierung des Baumes
        return balance(node);
    }

    
    private void update(AVLNode<T> node) {
        int leftNodeHeight = (node.getLeft() == null) ? -1 : node.getLeft().getHeight();
        int rightNodeHeight = (node.getRight() == null) ? -1 : node.getRight().getHeight();

        // Aktualisiere die Höhe des Knotens
        node.setHeight( 1 + Math.max(leftNodeHeight, rightNodeHeight));

        // Aktualisiere Balance des Knotens
        node.setBalance(rightNodeHeight - leftNodeHeight);
    }

    
    private AVLNode<T> balance(AVLNode<T> node) {
        // Links-geneigter Unterbaum.
        if (node.getBalance() == -2) {

            // Left-Left case.
            if (node.getLeft().getBalance() <= 0) {
                return leftLeftCase(node);

                // Left-Right case.
            } else {
                return leftRightCase(node);
            }

            //Rechts-geneigter Teilbaum.
        } else if (node.getBalance() == +2) {

            // Right-Right case.
            if (node.getRight().getBalance() >= 0) {
                return rightRightCase(node);

                // Right-Left case.
            } else {
                return rightLeftCase(node);
            }
        }
        return node;
    }

    
    private AVLNode<T> leftLeftCase(AVLNode<T> node) {
        return rightRotation(node);
    }

   
    private AVLNode<T> leftRightCase(AVLNode<T> node) {
        node.setLeft(leftRotation(node.getLeft()));
        return leftLeftCase(node);
    }

    
    private AVLNode<T> rightRightCase(AVLNode<T> node) {
        return leftRotation(node);
    }

    
    private AVLNode<T> rightLeftCase(AVLNode<T> node) {
        node.setRight(rightRotation(node.getRight()));
        return rightRightCase(node);
    }

    
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> newParent = node.getRight();
        node.setRight(newParent.getLeft());
        newParent.setLeft(node);
        update(node);
        update(newParent);
        return newParent;
    }

    
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> newParent = node.getLeft();
        node.setLeft(newParent.getRight());
        newParent.setRight(node);
        update(node);
        update(newParent);
        return newParent;
    }

    
    public String traverse(Traversal traversal) {
        return this.root.traverse(traversal);
    }


    public String toString() {
        if (this.isEmpty()) { return null; }
        else { return this.root.toString(); }
    }
}

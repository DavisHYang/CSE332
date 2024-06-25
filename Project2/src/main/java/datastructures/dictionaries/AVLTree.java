package datastructures.dictionaries;

import cse332.datastructures.trees.BinarySearchTree;

/**
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and calls to superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 * <p>
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override the insert method such that it creates AVLNode instances
 * instead of BSTNode instances.
 * 3. Do NOT "replace" the children array in BSTNode with a new
 * children array or left and right fields in AVLNode.  This will
 * instead mask the super-class fields (i.e., the resulting node
 * would actually have multiple copies of the node fields, with
 * code accessing one pair or the other depending on the type of
 * the references used to access the instance).  Such masking will
 * lead to highly perplexing and erroneous behavior. Instead,
 * continue using the existing BSTNode children array.
 * 4. Ensure that the class does not have redundant methods
 * 5. Cast a BSTNode to an AVLNode whenever necessary in your AVLTree.
 * This will result a lot of casts, so we recommend you make private methods
 * that encapsulate those casts.
 * 6. Do NOT override the toString method. It is used for grading.
 * 7. The internal structure of your AVLTree (from this.root to the leaves) must be correct
 */

public class AVLTree<K extends Comparable<? super K>, V> extends BinarySearchTree<K, V> {
    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        V prev = find(key);
        root = insert(cast(root), key, value);
        if(prev == null) {
            size++;
        }
        return prev;
    }

    private AVLNode insert(AVLNode root, K key, V value) {
        // Insert via normal BST properties
        if(root == null) {
            return new AVLNode(key, value, 0);
        } else {
            int compare = key.compareTo(root.key);
            if(compare < 0) {
                root.children[0] = insert(cast(root.children[0]), key, value);
            } else if(compare > 0) {
                root.children[1] = insert(cast(root.children[1]), key, value);
            } else {
                root.value = value;
                return root;
            }
        }
        // On the way up update heights
        root.height = 1 + Math.max(getHeight(cast(root.children[0])), getHeight(cast(root.children[1])));

        // rebalance
        int balance = balance(root);

        // imbalance in right subtree
        if(balance < -1) {
            // imbalance in right subtree
            if(key.compareTo(root.children[1].key) > 0) {
                return rotateLeft(root);
            }
            root.children[1] = rotateRight(cast(root.children[1]));
            return rotateLeft(root);
        }

        // imbalance in left subtree
        if(balance > 1) {
            // imbalance in left subtree
            if(key.compareTo(root.children[0].key) < 0) {
                return rotateRight(root);
            }
            root.children[0] = rotateLeft(cast(root.children[0]));
            return rotateRight(root);
        }
        return root;
    }

    private AVLNode cast(BSTNode b) {
        return (AVLNode)b;
    }

    private AVLNode rotateLeft(AVLNode root) {
        AVLNode tempR = cast(root.children[1]);
        AVLNode tempRL = cast(tempR.children[0]);
        tempR.children[0] = root;
        root.children[1] = tempRL;
        updateHeights(root);
        updateHeights(tempR);
        return tempR;
    }

    private AVLNode rotateRight(AVLNode root) {
        AVLNode tempL = cast(root.children[0]);
        AVLNode tempLR = cast(tempL.children[1]);
        tempL.children[1] = root;
        root.children[0] = tempLR;
        updateHeights(root);
        updateHeights(tempL);
        return tempL;
    }

    /** Gets the height of an AVLNode */
    private int getHeight(AVLNode root) {
        return root == null ? -1 : root.height;
    }

    /** Updates the heights of the AVL tree */
    private void updateHeights(AVLNode root) {
        root.height = 1 + Math.max(getHeight(cast(root.children[0])), getHeight(cast(root.children[1])));
    }

    /** Returns the balance at a certain node */
    private int balance(AVLNode root) {
        return root == null ? 0 : getHeight(cast(root.children[0])) - getHeight(cast(root.children[1]));
    }

    public class AVLNode extends BSTNode {
        int height;
        public AVLNode(K key, V value, int height) {
            super(key, value);
            this.height = height;
        }
    }
}

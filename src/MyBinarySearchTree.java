import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyBinarySearchTree<K extends Comparable<K>, V> implements Iterable<MyBinarySearchTree<K, V>.Node> {
    public class Node {
        K key;
        V value;
        Node left, right, parent;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = parent = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private Node root;
    private int size;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
    }

    public void insert(K key, V value) {
        root = insert(root, key, value, null);
    }

    private Node insert(Node current, K key, V value, Node parent) {
        if (current == null) {
            Node newNode = new Node(key, value);
            newNode.parent = parent;
            size++;
            return newNode;
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            current.left = insert(current.left, key, value, current);
        } else if (cmp > 0) {
            current.right = insert(current.right, key, value, current);
        } else {
            current.value = value;
        }
        return current;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print("key is " + node.getKey() + " and value is " + node.getValue() + " ");
            inOrder(node.right);
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Node> iterator() {
        return new InOrderIterator(root);
    }

    private class InOrderIterator implements Iterator<Node> {
        private Node next;

        public InOrderIterator(Node root) {
            next = root;
            if (next == null) {
                return;
            }
            while (next.left != null) {
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node r = next;
            if (next.right != null) {
                next = next.right;
                while (next.left != null) {
                    next = next.left;
                }
            } else {
                while (true) {
                    if (next.parent == null) {
                        next = null;
                        return r;
                    }
                    if (next.parent.left == next) {
                        next = next.parent;
                        return r;
                    }
                    next = next.parent;
                }
            }
            return r;
        }
    }
}
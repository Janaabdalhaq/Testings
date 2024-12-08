package org.example;

public class Stack {
    private class Node {
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public void push(String data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}

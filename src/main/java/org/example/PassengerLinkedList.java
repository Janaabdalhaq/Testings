package org.example;
public class PassengerLinkedList {
    private PassengerNode head;
    private PassengerNode tail;
    private int size;

    public PassengerLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addAtHead(Passenger passenger) {
        PassengerNode newNode = new PassengerNode(passenger);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    public void addAtTail(Passenger passenger) {
        PassengerNode newNode = new PassengerNode(passenger);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    public void remove(Passenger passenger) {
        if (isEmpty()) {
            return;
        }

        PassengerNode current = head;
        while (current != null) {
            if (current.getPassenger().getPassengerId() == passenger.getPassengerId()) {
                if (current == head) {
                    head = current.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    }
                } else if (current == tail) {
                    tail = current.getPrev();
                    tail.setNext(null);
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                size--;
                return;
            }
            current = current.getNext();
        }
    }

    public Passenger search(int passengerId) {
        PassengerNode current = head;
        while (current != null) {
            if (current.getPassenger().getPassengerId() == passengerId) {
                return current.getPassenger();
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void display() {
        PassengerNode current = head;
        while (current != null) {
            System.out.println("Passenger ID: " + current.getPassenger().getPassengerId() +
                    ", Name: " + current.getPassenger().getName() +
                    ", Flight ID: " + current.getPassenger().getFlightId() +
                    ", Status: " + current.getPassenger().getStatus());
            current = current.getNext();
        }
    }
}
package org.example;

public class Queue {
    private PassengerNode front;
    private PassengerNode rear;
    private int size;

    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(Passenger passenger) {
        PassengerNode newNode = new PassengerNode(passenger);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            newNode.setPrev(rear);
            rear = newNode;
        }
        size++;
    }

    public Passenger dequeue() {
        if (isEmpty()) {
            return null;
        }
        Passenger passenger = front.getPassenger();
        front = front.getNext();
        if (front == null) {
            rear = null;
        } else {
            front.setPrev(null);
        }
        size--;
        return passenger;
    }

    public Passenger search(int passengerId) {
        PassengerNode current = front;
        while (current != null) {
            if (current.getPassenger().getPassengerId() == passengerId) {
                return current.getPassenger();
            }
            current = current.getNext();
        }
        return null;
    }
    public boolean remove(Passenger passenger) {
        PassengerNode current = front;
        while (current != null) {
            if (current.getPassenger().getPassengerId() == passenger.getPassengerId()) {
                current.getPrev().setNext(current.getNext());
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                }
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void display() {
        PassengerNode current = front;
        while (current != null) {
            Passenger passenger = current.getPassenger();
            System.out.println("Passenger ID: " + passenger.getPassengerId());
            System.out.println("Name: " + passenger.getName());
            System.out.println("Flight ID: " + passenger.getFlightId());
            System.out.println("Status: " + passenger.getStatus());
            System.out.println("------------------------");
            current = current.getNext();
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void update(int id) {
    }
}

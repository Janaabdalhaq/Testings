package org.example;

public class PassengerNode {
    private Passenger passenger;
    private PassengerNode next;
    private PassengerNode prev;
    
    public PassengerNode(Passenger passenger) {
        this.passenger = passenger;
        this.next = null;
        this.prev = null;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public PassengerNode getNext() {
        return next;
    }

    public void setNext(PassengerNode next) {
        this.next = next;
    }

    public PassengerNode getPrev() {
        return prev;
    }

    public void setPrev(PassengerNode prev) {
        this.prev = prev;
    }
}

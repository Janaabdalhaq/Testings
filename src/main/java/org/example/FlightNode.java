package org.example;

public class FlightNode {
    private Flight flight;
    private FlightNode next;
    private FlightNode prev;

    public FlightNode(Flight flight) {
        this.flight = flight;
        this.next = null;
        this.prev = null;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public FlightNode getNext() {
        return next;
    }

    public void setNext(FlightNode next) {
        this.next = next;
    }

    public FlightNode getPrev() {
        return prev;
    }

    public void setPrev(FlightNode prev) {
        this.prev = prev;
    }
}

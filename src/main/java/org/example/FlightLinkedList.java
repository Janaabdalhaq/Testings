package org.example;

public class FlightLinkedList {
    private FlightNode head;
    private FlightNode tail;
    private int size;

    public FlightLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFlight(Flight flight) {
        FlightNode newNode = new FlightNode(flight);
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
    public void removeFlight(int flightId) {
        FlightNode current = head;
        while (current != null) {
            if (current.getFlight().getFlightId() == flightId) {
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

    public Flight searchFlight(int flightId) {
        FlightNode current = head;
        while (current != null) {
            if (current.getFlight().getFlightId() == flightId) {
                return current.getFlight();
            }
            current = current.getNext();
        }
        return null;
    }

    public void updateFlight(int flightId, String destination, String status) {
        FlightNode current = head;
        while (current != null) {
            if (current.getFlight().getFlightId() == flightId) {
                current.getFlight().setDestination(destination);
                current.getFlight().setStatus(status);
                return;
            }
            current = current.getNext();
        }
    }

    public void displayFlights() {
        FlightNode current = head;
        while (current != null) {
            Flight flight = current.getFlight();
            System.out.println("Flight ID: " + flight.getFlightId());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Status: " + flight.getStatus());
            System.out.println("------------------------");
            current = current.getNext();
        }
    }

    public void addPassenger(Passenger passenger) {
        Flight flight = searchFlight(passenger.getFlightId());
        if (flight != null) {
            flight.addPassenger(passenger);
        }
    }
    public void BoardPassenger(int flightID,int PassID){
        Flight flight = searchFlight(flightID);
        Passenger passenger;
        passenger = flight.getPassenger(PassID);
        if (passenger!=null){
            if (flight!=null){
                flight.BoardPassenger(passenger);
                if (passenger.getStatus().equals("Regular")){
                    flight.getRegularQueue().remove(passenger);
                } else if (passenger.getStatus().equals("VIP")){
                    flight.getVipQueue().remove(passenger);
                }
            }
        }
    }
    public void CancelPassenger(int flightID,int PassID){
        Flight flight = searchFlight(flightID);
        Passenger passenger = flight.getPassenger(PassID);
        if (passenger!=null){
            flight.CancelPassenger(passenger);
            if (passenger.getStatus().equals("Regular")){
                flight.getRegularQueue().remove(passenger);
            } else if (passenger.getStatus().equals("VIP")){
                flight.getVipQueue().remove(passenger);
            }
            flight.getBoardedList().remove(passenger);
        }
    }
    public int getCanceledRegularCount(){
        FlightNode current = head;
        int count = 0;
        while (current != null){
            count += current.getFlight().getCanceledRegularCount();
            current = current.getNext();
        }
        return count;
    }
    public int getCanceledVIPCount(){
        FlightNode current = head;
        int count = 0;
        while (current != null){
            count += current.getFlight().getCanceledVIPCount();
            current = current.getNext();
        }
        return count;
    }
    public int getTotalCanceledCount(){
        return getCanceledRegularCount() + getCanceledVIPCount();
    }
    public int getBoardedRegularCount(){
        FlightNode current = head;
        int count = 0;
        while (current != null){
            count += current.getFlight().getBoardedRegularCount();
            current = current.getNext();
        }
        return count;
    }
    public int getBoardedVIPCount(){
        FlightNode current = head;
        int count = 0;
        while (current != null){
            count += current.getFlight().getBoardedVIPCount();
            current = current.getNext();
        }
        return count;
    }
    public int getTotalBoardedCount(){
        return getBoardedRegularCount() + getBoardedVIPCount();
    }
    public int vipInQueue(){
        FlightNode current = head;
        int count = 0;
        while (current != null){
            count += current.getFlight().getVipQueue().getSize();
            current = current.getNext();
        }
        return count;
    }
    public int RegularInQueue(){
        FlightNode current = head;
        int count = 0;
        while (current != null){
            count += current.getFlight().getRegularQueue().getSize();
            current = current.getNext();
        }
        return count;
    }
    public int getTotalinQueue(){
        return RegularInQueue()+vipInQueue();
    }
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


}

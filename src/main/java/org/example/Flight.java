package org.example;

public class Flight {
    private int flightId;
    private String destination;
    private String status;
    private Queue regularQueue;
    private Queue vipQueue;
    private Stack undoStack;
    private Stack redoStack;
    private PassengerLinkedList boardedList;
    private PassengerLinkedList cancelledList;
    private int totalCount;
    private int canceledVIPCount = 0;
    private int canceledRegularCount = 0;
    private int boardedVIPCount = 0;
    private int boardedRegularCount = 0;


    public Flight(int flightId, String destination, String status) {
        this.flightId = flightId;
        this.destination = destination;
        this.status = status;
        regularQueue = new Queue();
        vipQueue = new Queue();
        undoStack = new Stack();
        redoStack = new Stack();
        boardedList = new PassengerLinkedList();
        cancelledList = new PassengerLinkedList();
    }
    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Passenger getPassenger(int passengerId) { if (regularQueue.search(passengerId) != null) return regularQueue.search(passengerId); else if (vipQueue.search(passengerId) != null) return vipQueue.search(passengerId); else if(boardedList.search(passengerId)!= null)return boardedList.search(passengerId) ;else if (cancelledList.search(passengerId)!= null)return  cancelledList.search(passengerId); else return null ; }
    public Queue getRegularQueue() { return regularQueue; }
    public Queue getVipQueue() { return vipQueue; }
    public Stack getUndoStack() { return undoStack; }
    public Stack getRedoStack() { return redoStack; }
    public PassengerLinkedList getBoardedList() { return boardedList; }
    public PassengerLinkedList getCancelledList() { return cancelledList; }
    public int getTotalCount() { return totalCount; }
    public int getCanceledVIPCount() { return canceledVIPCount; }
    public int getCanceledRegularCount() { return canceledRegularCount; }
    public int getBoardedVIPCount() { return boardedVIPCount; }
    public int getBoardedRegularCount() { return boardedRegularCount; }
    public void addPassenger(Passenger passenger) {
        if (passenger.getStatus().equals("Regular")) {
            regularQueue.enqueue(passenger);
        } else if (passenger.getStatus().equals("VIP")) {
            vipQueue.enqueue(passenger);
        }
        undoStack.push("Check-in " + passenger.getName() + "(" + flightId + ")");
    }
    public void BoardPassenger(Passenger passenger) {
        if (passenger.getStatus().equals("Regular")) {
            regularQueue.remove(passenger);
            boardedList.addAtTail(passenger);
            boardedRegularCount++;
        } else if (passenger.getStatus().equals("VIP")) {
            vipQueue.remove(passenger);
            boardedList.addAtHead(passenger);
            boardedVIPCount++;
        }
        undoStack.push("Board " + passenger.getName() + "(" + flightId + ")");
    }
    public boolean CancelPassenger(Passenger passenger) {
        boolean removed=false;
        if (passenger.getStatus().equals("Regular")) {
            regularQueue.remove(passenger);
            canceledRegularCount++;
            removed = true;
        } else if (passenger.getStatus().equals("VIP")) {
            vipQueue.remove(passenger);
            removed = true;
            canceledVIPCount++;
        }
        if (boardedList.search(passenger.getPassengerId()) != null){
            boardedList.remove(passenger);
            removed = true;
        }
        if (removed == true){
            cancelledList.addAtTail(passenger);
        }
        undoStack.push("Cancelled passenger " + passenger.getPassengerId() + " from the flight " + flightId);
        return removed;
    }

    public void displayPassengers() {
        System.out.println("Passengers on flight " + flightId + ":");
        vipQueue.display();
        regularQueue.display();
        System.out.println("--------------------------------");
    }

}

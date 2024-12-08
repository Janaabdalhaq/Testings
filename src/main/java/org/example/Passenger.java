package org.example;

public class Passenger {
    private int passengerId;
    private String name;
    private int flightId;
    private String status;

    public Passenger(int passengerId, String name, int flightId, String status) {
        this.passengerId = passengerId;
        this.name = name;
        this.flightId = flightId;
        this.status = status;
    }

    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
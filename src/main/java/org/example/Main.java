package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static File passengersFile;
    private static File flightsFile;
    private static StringBuilder logBuilder = new StringBuilder();

    static boolean addPassenger(int passengerId, String name, int flightId, String status) throws IOException {
        if (!passengersFile.exists()) {
            passengersFile.createNewFile();
        }
        if (checkIfIdExists(passengerId) != -1) {
            System.out.println("Passenger ID already exists");
            return false;
        }
        FileWriter fileWriter = new FileWriter(passengersFile, true);
        fileWriter.write(passengerId + "," + name + "," + flightId + "," + status + "\n");
        fileWriter.close();
        System.out.println("Passenger added successfully!");
        logOperation("Added passenger: ID=" + passengerId + ", Name=" + name + ", Flight=" + flightId + ", Status=" + status);
        return true;
    }

    static void removePassenger(int passengerId) throws IOException {
        int index = checkIfIdExists(passengerId);
        if (index == -1) {
            System.out.println("Passenger ID does not exist");
            return;
        }

        StringBuilder content = new StringBuilder();
        int currentLine = 0;

        Scanner scanner = new Scanner(passengersFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (currentLine != index) {
                content.append(line).append("\n");
            }
            currentLine++;
        }
        scanner.close();

        FileWriter fileWriter = new FileWriter(passengersFile);
        fileWriter.write(content.toString());
        fileWriter.close();

        System.out.println("Passenger removed successfully!");
        logOperation("Removed passenger: ID=" + passengerId);
    }

    public static boolean updatePassenger(int passengerid) throws IOException {
        int index = checkIfIdExists(passengerid);
        if(index==-1){
            return false;
        }
        Scanner inputScanner = new Scanner(System.in);
        removePassenger(passengerid);
        System.out.println("Enter new passenger name: ");
        String name = inputScanner.next();
        System.out.println("Enter new flight ID: ");
        int flightId = inputScanner.nextInt();
        System.out.println("Enter new passenger status: ");
        String status = inputScanner.next();
        addPassenger(passengerid, name, flightId, status);
        logOperation("Updated passenger: ID=" + passengerid + ", New Name=" + name +
                ", New Flight=" + flightId + ", New Status=" + status);
        return true;
    }

    public static int checkIfIdExists(int passengerId) {
        try {
            Scanner scanner = new Scanner(passengersFile);
            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.split(",")[0].equals(String.valueOf(passengerId))) {
                    scanner.close();
                    return index;
                }
                index++;
            }
            scanner.close();
            return -1;
        } catch (Exception e) {
            System.out.println("Error checking passenger ID: " + e.getMessage());
            return -1;
        }
    }
    public static int checkIfFlightIdExists(int flightId) {
        try {
            Scanner scanner = new Scanner(flightsFile);
            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.split(",")[0].equals(String.valueOf(flightId))) {
                    scanner.close();
                    return index;
                }
                index++;
            }
            scanner.close();
            return -1;
        } catch (Exception e) {
            System.out.println("Error checking Flight ID: " + e.getMessage());
            return -1;
        }
    }


    public static Passenger SearchPassenger(int passengerid) throws FileNotFoundException, IOException {
        int index = checkIfIdExists(passengerid);
        if (index == -1){
            System.out.println("Cant Find Passenger");
            return null;
        }

        Scanner scanner = new Scanner(passengersFile);
        String line = "";
        for (int i = 0; i <= index; i++) {
            line = scanner.nextLine();
        }
        scanner.close();

        int pid = Integer.parseInt(line.split(",")[0]);
        String name = line.split(",")[1];
        int flightId = Integer.parseInt(line.split(",")[2]);
        String status = line.split(",")[3];
        Passenger found = new Passenger(pid, name, flightId, status);
        if (found != null) {
            logOperation("Searched for passenger: ID=" + passengerid + " - Found");
        } else {
            logOperation("Searched for passenger: ID=" + passengerid + " - Not Found");
        }
        return found;
    }

    public static void printAllPassengers() throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(passengersFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        scanner.close();
        logOperation("Displayed all passengers");
    }

    public static void PrintPassenger(int id) throws FileNotFoundException, IOException {
        Passenger x = SearchPassenger(id);
        System.out.println("Passenger ID: " + x.getPassengerId());
        System.out.println("Passenger Name: " + x.getName());
        System.out.println("Flight ID: " + x.getFlightId());
        System.out.println("Passenger Status: " + x.getStatus());
        System.out.println("-----------------------------------");
    }
    public static void AddFlight(int flightId, String destination, String status) throws IOException {
        if (!flightsFile.exists()) {
            flightsFile.createNewFile();
        }
        if (checkIfFlightIdExists(flightId) != -1) {
            System.out.println("Flight ID already exists");
            return;
        }
        FileWriter fileWriter = new FileWriter(flightsFile, true);
        fileWriter.write(flightId + "," + destination + "," + status + "\n");
        fileWriter.close();
        System.out.println("Flight added successfully.");
        logOperation("Added flight: ID=" + flightId + ", Destination=" + destination + ", Status=" + status);
    }
    public static void RemoveFlight(int flightId) throws IOException {
        int index = checkIfFlightIdExists(flightId);
        if (index == -1) {
            System.out.println("Flight ID does not exist");
            return;
        }
        StringBuilder content = new StringBuilder();
        int currentLine = 0;
        Scanner scanner = new Scanner(flightsFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (currentLine != index) {
                content.append(line).append("\n");
            }
            currentLine++;
        }
        scanner.close();
        FileWriter fileWriter = new FileWriter(flightsFile);
        fileWriter.write(content.toString());
        fileWriter.close();
        System.out.println("Flight removed successfully!");
        logOperation("Removed flight: ID=" + flightId);
    }
    public static void UpdateFlight(int flightId, String destination, String status) throws IOException {
        int index = checkIfFlightIdExists(flightId);
        if (index == -1) {
            System.out.println("Flight ID does not exist");
            return;
        }
        RemoveFlight(flightId);
        AddFlight(flightId, destination, status);
        System.out.println("Flight updated successfully!");
        logOperation("Updated flight: ID=" + flightId + ", New Destination=" + destination +
                ", New Status=" + status);
        return;
    }
    public static Flight SearchFlight(int flightId) throws FileNotFoundException, IOException {
        int index = checkIfFlightIdExists(flightId);
        if (index == -1) {
            System.out.println("Flight ID does not exist");
            return null;
        }
        Scanner scanner = new Scanner(flightsFile);
        String line = "";
        for (int i = 0; i <= index; i++) {
            line = scanner.nextLine();
        }
        scanner.close();
        int fid = Integer.parseInt(line.split(",")[0]);
        String destination = line.split(",")[1];
        String status = line.split(",")[2];
        Flight found = new Flight(fid, destination, status);
        if (found != null) {
            logOperation("Searched for flight: ID=" + flightId + " - Found");
        } else {
            logOperation("Searched for flight: ID=" + flightId + " - Not Found");
        }
        return found;
    }
    public static void PrintFlight(int id) throws FileNotFoundException, IOException {
        Flight x = SearchFlight(id);
        System.out.println("Flight ID: " + x.getFlightId());
        System.out.println("Flight Destination: " + x.getDestination());
        System.out.println("Flight Status: " + x.getStatus());
    }
    public static void PrintAllFlights() throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(flightsFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        scanner.close();
        logOperation("Displayed all flights");
    }
    public static void DisplayAllActiveFlights() throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(flightsFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.split(",")[2].equals("Active")) {
                String[] flightDetails = line.split(",");
                System.out.println("Flight ID: " + flightDetails[0]);
                System.out.println("Flight Destination: " + flightDetails[1]);
                System.out.println("Flight Status: " + flightDetails[2]);
                System.out.println("-------------------------------");

            }
        }
        scanner.close();
        logOperation("Displayed all active flights");
    }
    public static void DisplayAllInactiveFlights() throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(flightsFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.split(",")[2].equals("Inactive")) {
                String[] flightDetails = line.split(",");
                System.out.println("Flight ID: " + flightDetails[0]);
                System.out.println("Flight Destination: " + flightDetails[1]);
                System.out.println("Flight Status: " + flightDetails[2]);
                System.out.println("-------------------------------");
            }
        }
        scanner.close();
        logOperation("Displayed all inactive flights");
    }
    public static void ReadFlightsFromFile(FlightLinkedList flightList) throws FileNotFoundException {
        Scanner scanner = new Scanner(flightsFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] flightDetails = line.split(",");
            int flightId = Integer.parseInt(flightDetails[0]);
            String destination = flightDetails[1];
            String status = flightDetails[2];
            flightList.addFlight(new Flight(flightId, destination, status));
        }
        scanner.close();
    };
    public static void ReadPassengersFromFile(FlightLinkedList flightList) throws FileNotFoundException {
        Scanner scanner = new Scanner(passengersFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] passengerDetails = line.split(",");
            int passengerId = Integer.parseInt(passengerDetails[0]);
            String name = passengerDetails[1];
            int flightId = Integer.parseInt(passengerDetails[2]);
            String status = passengerDetails[3];

            Flight flight = flightList.searchFlight(flightId);
            if (flight != null) {
                Passenger passenger = new Passenger(passengerId, name, flightId, status);
                if (status.equals("VIP")) {
                    flight.getVipQueue().enqueue(passenger);
                } else if (status.equals("Regular")) {
                    flight.getRegularQueue().enqueue(passenger);
                }
            }
        }
        scanner.close();
    }
    public static void  PassengerOpMenu(FlightLinkedList flightList) throws IOException {
        System.out.println("\n=== Passenger Management Menu ===");
        System.out.println("1. Add Passenger");
        System.out.println("2. Update Passenger");
        System.out.println("3. Remove Passenger");
        System.out.println("4. Search Passenger");
        System.out.println("5. Print All Passengers");
        System.out.println("6. Print Specific Passenger Info");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        Scanner inputScanner = new Scanner(System.in);
        int choice = inputScanner.nextInt();
        Scanner scanner = new Scanner(System.in);
        int pid;
        int fid;
        switch (choice) {
            case 1:
                System.out.println("Enter Passenger ID: ");
                pid = scanner.nextInt();
                System.out.println("Enter Passenger Name: ");
                scanner.nextLine(); // Clear buffer
                String name = scanner.nextLine();
                System.out.println("Enter Flight ID: ");
                fid = scanner.nextInt();
                System.out.println("Enter Status (Regular/VIP): ");
                String status = scanner.next();
                addPassenger(pid, name, fid, status);
                break;
            case 2:
                System.out.println("Enter Passenger ID: ");
                pid = scanner.nextInt();
                updatePassenger(pid);
                break;
            case 3:
                System.out.println("Enter Passenger ID: ");
                pid = scanner.nextInt();
                removePassenger(pid);
                break;
            case 4:
                System.out.println("Enter Passenger ID to search: ");
                Passenger found = SearchPassenger(scanner.nextInt());
                if (found != null) {
                    PrintPassenger(found.getPassengerId());
                }
                break;
            case 5:
                printAllPassengers();
                break;
            case 6:
                System.out.println("Enter Passenger ID  : ");
                PrintPassenger(scanner.nextInt());
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
    public static void FlightOpMenu(FlightLinkedList flightList) throws IOException {
        System.out.println("\n=== Flight Management Menu ===");
        System.out.println("1. Add Flight");
        System.out.println("2. Update Flight");
        System.out.println("3. Remove Flight");
        System.out.println("4. Search Flight");
        System.out.println("5. Print All Flights");
        System.out.println("6. Print Specific Flight Info");
        System.out.println("7. Display All Active Flights");
        System.out.println("8. Display All Inactive Flights");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        Scanner inputScanner = new Scanner(System.in);
        int choice = inputScanner.nextInt();
        Scanner scanner = new Scanner(System.in);
        int fid;
        String status,destination;
        switch (choice) {
            case 1:
                System.out.println("Enter Flight ID: ");
                fid = scanner.nextInt();
                System.out.println("Enter Flight Destination: ");
                destination = scanner.next();
                System.out.println("Enter Flight Status: ");
                status = scanner.next();
                AddFlight(fid, destination, status);
                flightList.addFlight(new Flight(fid, destination, status));
                break;
            case 2:
                System.out.println("Enter Flight ID: ");
                fid = scanner.nextInt();
                System.out.println("Enter Flight Destination: ");
                destination = scanner.next();
                System.out.println("Enter Flight Status: ");
                status = scanner.next();
                UpdateFlight(fid, destination, status);
                flightList.updateFlight(fid, destination, status);
                break;
            case 3:
                System.out.println("Enter Flight ID: ");
                fid = scanner.nextInt();
                RemoveFlight(fid);
                flightList.removeFlight(fid);
                break;
            case 4:
                System.out.println("Enter Flight ID: ");
                fid = scanner.nextInt();
                Flight found = SearchFlight(fid);
                if (found != null) {
                    PrintFlight(found.getFlightId());
                }
                break;
            case 5:
                PrintAllFlights();
                break;
            case 6:
                System.out.println("Enter Flight ID: ");
                fid = scanner.nextInt();
                PrintFlight(fid);
                break;
            case 7:
                DisplayAllActiveFlights();
                break;
            case 8:
                DisplayAllInactiveFlights();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public static void OperationsMenu(FlightLinkedList flightList) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Operations Menu ===");
            System.out.println("1. Check-in Passenger");
            System.out.println("2. Board Passenger");
            System.out.println("3. Cancel Passenger");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Passenger ID to check-in: ");
                    int pid = scanner.nextInt();
                    Passenger passenger = SearchPassenger(pid);
                    if (passenger != null) {
                        Flight flight = flightList.searchFlight(passenger.getFlightId());
                        if (passenger.getStatus().equalsIgnoreCase("VIP")) {
                            flight.getVipQueue().enqueue(passenger);
                            System.out.println("VIP Passenger checked-in.");
                        } else {
                            flight.getRegularQueue().enqueue(passenger);
                            System.out.println("Regular Passenger checked-in.");
                        }
                        logOperation("Checked in passenger: ID=" + pid + ", Status=" + passenger.getStatus() +
                                " for flight " + passenger.getFlightId());
                    } else {
                        System.out.println("Passenger not found.");
                    }
                    break;

                case 2:
                    System.out.println("Enter Flight ID for boarding: ");
                    int fid = scanner.nextInt();
                    Flight boardingFlight = flightList.searchFlight(fid);
                    if (boardingFlight != null) {
                        Passenger boardingPassenger = boardingFlight.getVipQueue().dequeue();
                        if (boardingPassenger == null) {
                            boardingPassenger = boardingFlight.getRegularQueue().dequeue();
                        }
                        if (boardingPassenger != null) {
                            boardingFlight.BoardPassenger(boardingPassenger);
                            System.out.println("Passenger boarded.");
                            logOperation("Boarded passenger: ID=" + boardingPassenger.getPassengerId() +
                                    " on flight " + fid);
                        } else {
                            System.out.println("No passengers in queue.");
                        }
                    } else {
                        System.out.println("Flight not found.");
                    }
                    break;

                case 3:
                    System.out.println("Enter Passenger ID to cancel: ");
                    int cancelPid = scanner.nextInt();
                    Passenger cancelPassenger = SearchPassenger(cancelPid);
                    if (cancelPassenger != null) {
                        Flight cancelFlight = flightList.searchFlight(cancelPassenger.getFlightId());
                        boolean removed = false;
                        if (cancelPassenger.getStatus().equalsIgnoreCase("VIP")) {
                            removed = cancelFlight.getVipQueue().remove(cancelPassenger);
                        } else {
                            removed = cancelFlight.getRegularQueue().remove(cancelPassenger);
                        }
                        if (removed) {
                            cancelFlight.CancelPassenger(cancelPassenger);
                            System.out.println("Passenger booking cancelled.");
                            logOperation("Cancelled passenger: ID=" + cancelPid + " from flight " +
                                    cancelPassenger.getFlightId());
                        } else {
                            System.out.println("Passenger not found in queue.");
                        }
                    } else {
                        System.out.println("Passenger not found.");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void logOperation(String operation) {
        String timestamp = java.time.LocalDateTime.now().toString();
        String logEntry = timestamp + " - " + operation + "\n";
        logBuilder.append(logEntry);
    }

    public static void LogMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Log Management Menu ===");
            System.out.println("1. View Current Session Log");
            System.out.println("2. Export Session Log to File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n=== Current Session Log ===");
                    if (logBuilder.length() > 0) {
                        System.out.println(logBuilder.toString());
                    } else {
                        System.out.println("No operations logged in current session.");
                    }
                    break;

                case 2:
                    System.out.println("Enter export file name (e.g., flight_log_20240120.txt): ");
                    scanner.nextLine(); // Clear buffer
                    String fileName = scanner.nextLine();
                    File exportFile = new File(fileName);
                    FileWriter writer = new FileWriter(exportFile);
                    writer.write(logBuilder.toString());
                    writer.close();
                    System.out.println("Log exported successfully to " + fileName);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void StatisticalMenu(FlightLinkedList flightList) {
        System.out.println("\n=== Statistical Menu ===");
        logOperation("Viewed Statistics.");
        int canceledVIP = 0;
                canceledVIP=flightList.getCanceledVIPCount();
                System.out.println("Total canceled VIP passengers: " + canceledVIP);
                int canceledRegular = 0;
                canceledRegular = flightList.getBoardedRegularCount();
                System.out.println("Total canceled regular passengers: " + canceledRegular);
                int vipInQueue = 0;
                vipInQueue = flightList.vipInQueue();
                System.out.println("Total VIP passengers in queue: " + vipInQueue);
                int regularInQueue = 0;
                regularInQueue = flightList.RegularInQueue();
                System.out.println("Total regular passengers in queue: " + regularInQueue);
                int boardedVIP = 0;
                boardedVIP = flightList.getBoardedVIPCount();
                System.out.println("Total boarded VIP passengers: " + boardedVIP);
                int boardedRegular = 0;
                boardedRegular = flightList.getBoardedRegularCount();
                System.out.println("Total boarded regular passengers: " + boardedRegular);

    }

    public static void main(String[] args) throws IOException {
        flightsFile = new File("Flights.txt");
        passengersFile = new File("Passengers.txt");
        FlightLinkedList flightList = new FlightLinkedList();
        ReadFlightsFromFile(flightList);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Flight Management System ===");
            System.out.println("1. Passenger Management");
            System.out.println("2. Flight Management");
            System.out.println("3. Operations");
            System.out.println("4. Log Management");
            System.out.println("5. Statistical Menu");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    PassengerOpMenu(flightList);
                    break;
                case 2:
                    FlightOpMenu(flightList);
                    break;
                case 3:
                    OperationsMenu(flightList);
                    break;
                case 4:
                    LogMenu();
                    break;
                case 5:
                    StatisticalMenu(flightList);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Reservation {
    private String name;
    private int trainNumber;
    private String classType;
    private String date;
    private String source;
    private String destination;
    private String pnr;

    public Reservation(String name, int trainNumber, String classType, String date, String source, String destination, String pnr) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.date = date;
        this.source = source;
        this.destination = destination;
        this.pnr = pnr;
    }

    public String getName() {
        return name;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getClassType() {
        return classType;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getPnr() {
        return pnr;
    }
}

class ReservationSystem {
    private List<Reservation> reservations;
    private int nextPnr;

    public ReservationSystem() {
        this.reservations = new ArrayList<>();
        this.nextPnr = 1;
    }

    public void reserve(String name, int trainNumber, String classType, String date, String source, String destination) {
        String pnr = "PNR" + nextPnr++;
        Reservation reservation = new Reservation(name, trainNumber, classType, date, source, destination, pnr);
        reservations.add(reservation);
        System.out.println("Reservation successful. Your PNR is: " + pnr);
    }

    public void cancel(String pnr) {
        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                reservations.remove(reservation);
                System.out.println("Cancellation successful for PNR: " + pnr);
                return;
            }
        }
        System.out.println("Reservation with PNR " + pnr + " not found.");
    }

    public void displayReservations() {
        for (Reservation reservation : reservations) {
            System.out.println("PNR: " + reservation.getPnr());
            System.out.println("Name: " + reservation.getName());
            System.out.println("Train Number: " + reservation.getTrainNumber());
            System.out.println("Class: " + reservation.getClassType());
            System.out.println("Date: " + reservation.getDate());
            System.out.println("Source: " + reservation.getSource());
            System.out.println("Destination: " + reservation.getDestination());
            System.out.println("--------------------------");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Online Reservation System Menu:");
            System.out.println("1. Reserve Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Display Reservations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter train number: ");
                    int trainNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter class type: ");
                    String classType = scanner.nextLine();
                    System.out.print("Enter date of journey: ");
                    String date = scanner.nextLine();
                    System.out.print("Enter source: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine();
                    reservationSystem.reserve(name, trainNumber, classType, date, source, destination);
                    break;
                case 2:
                    System.out.print("Enter PNR to cancel: ");
                    String pnr = scanner.nextLine();
                    reservationSystem.cancel(pnr);
                    break;
                case 3:
                    reservationSystem.displayReservations();
                    break;
                case 4:
                    System.out.println("Exiting program. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


package models.reservation;

import models.customer.Customer;
import models.room.IRoom;

import java.util.Date;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(final Customer customer, final IRoom room,
                       final Date checkInDate, final Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Customer: " + this.customer.toString()
                + "\nRoom: " + this.room.toString()
                + "\nCheckIn Date: " + this.checkInDate
                + "\nCheckOut Date: " + this.checkOutDate;
    }
}



package models.room;

import models.room.enums.RoomType;

public class FreeRoom extends Room {

    public FreeRoom(final String roomNumber, final RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }

    @Override
    public String toString() {
        return "FreeRoom => " + super.toString();
    }

}



package models.room;

import models.room.enums.RoomType;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    public Room(final String roomNumber, final Double price, final RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public Double getRoomPrice() {
        return this.price;
    }

    public RoomType getRoomType() {
        return this.enumeration;
    }

    public boolean isFree() {
        return true;
    }

    @Override
    public String toString() {
        return "Room Number:" + this.roomNumber
                + " Price:" + this.price
                + " Enumeration:" + this.enumeration;
    }
}




package models.customer;

import java.util.regex.Pattern;

public class Customer {

    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";

    private String firstName;
    private String lastName;
    private String email;

    public Customer(final String firstName, final String lastName, final String email) {
        this.isValidEmail(email);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private void isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstName
                + " Last Name: " + this.lastName
                + " Email: " + this.email;
    }
}


package models.room.enums;

public enum RoomType {
    SINGLE,
    DOUBLE
}



package tests;

import models.customer.Customer;
import models.reservation.Reservation;
import models.room.Room;
import models.room.enums.RoomType;

import java.util.Date;

public class Tester {

    public static void main(String[] args) {
        Customer customer = new Customer("tharunis", "tk", "tk_tharunis@email.com");
        System.out.println(customer);

        // Create room and reservation
        Room room = new Room("101", 500.0, RoomType.DOUBLE);
        Date checkIn = new Date(); // current date
        Date checkOut = new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000); // +2 days
        Reservation reservation = new Reservation(customer, room, checkIn, checkOut);

        System.out.println( room);
        System.out.println( reservation);

        // Simple availability check0
        Date today = new Date();
        if (today.after(checkIn) && today.before(checkOut)) {
            System.out.println(room.getRoomNumber() + " sis currently OCCUPIED.");
            System.out.println("It will be available after: " + checkOut);
        } else {
            System.out.println(room.getRoomNumber() + " is AVAILABLE.");
        }
    }
}

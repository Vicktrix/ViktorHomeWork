package com.viktorshomework.HomeWork2.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking{
    
    private Map<Integer, Book> booking = new HashMap<>();
    private String bookingName;
    public Booking(String name) {
        this.bookingName = name;
    }
    public Booking(Guest guest) {
        this.bookingName = guest.getName();
    }
    public void addToBook(Order order, Guest guest) {
        booking.put(order.getNumOrder(),new Book(order,guest));
    }
    public void addToBookWithMultipleGuestsInRoom(Order order, Guest guest) {
        Order newOrder = order.numOrderAddedSameRoom();
        booking.put(newOrder.getNumOrder(),new Book(newOrder,guest));
    }
    
    public Map<Integer,Book> getBooking(){
        return booking;
    }
    public Order getOrdersByNumOfOrder(Integer i) {
        return booking.get(i).order();
    }
    public List<Order> getOrdersByGuest(Guest guest) {
        return booking.values().stream().filter(b -> b.guest().equals(guest))
                .map(b -> b.order()).toList();
    }
    public List<Guest> getGuestByOrder(Order o) {
        return booking.values().stream().filter(b -> b.order().equals(o))
                .map(b -> b.guest()).toList();
    }
    public String getBookingName(){
        return bookingName;
    }
    
//    public boolean checkOrder(Room room, LocalDate from, LocalDate to) {
//        return true;
//    }

    @Override
    public String toString(){
        return "Booking{"+"bookingName="+bookingName;
    }
    
}

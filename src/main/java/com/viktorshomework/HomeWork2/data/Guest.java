package com.viktorshomework.HomeWork2.data;

import java.time.LocalDate;
import java.util.List;

public class Guest {
    private final String name;
    private final String surname;
    private final LocalDate birthDay;
    private final Booking booking;
    public Guest(String name,String surname,LocalDate birthDay){
        this.name=name;
        this.surname=surname;
        this.birthDay=birthDay;
        this.booking=new Booking(this);
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public LocalDate getBirthDay(){
        return birthDay;
    }

    public Booking getBooking(){
        return booking;
    }
    
    public List<Order> getOrders(){
        return booking.getOrdersByGuest(this);
    }

    public void addToBook(Order order) {
        booking.addToBook(order,this);
    }
    
    public void clearBooking() {
        booking.getBooking().clear();
    }
    @Override
    public String toString(){
        return "\n\tGuest{"+"name="+name+", surname="+surname+", birthDay="+birthDay+", \nbooking="+booking.getOrdersByGuest(this)+"}\n";
    }
}

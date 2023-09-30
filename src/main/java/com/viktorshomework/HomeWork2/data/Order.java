package com.viktorshomework.HomeWork2.data;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Order{
    private static int numberOfOrders = 1;
    private final Room room;
    private final LocalDate from;
    private final LocalDate to;
    private final Period period;
    private int numOrder;
    public Order(Room room,LocalDate from,LocalDate to){
        this.room=room;
        this.from=from.isBefore(to)? from : to;
        this.to=to.isAfter(from)? to : from;
        this.numOrder = numberOfOrders++;
        this.period = this.from.until(this.to);
    }

    public static int getNumberOfOrders(){
        return numberOfOrders;
    }

    public Room getRoom(){
        return room;
    }

    public LocalDate getFrom(){
        return from;
    }

    public LocalDate getTo(){
        return to;
    }

    public int getNumOrder(){
        return numOrder;
    }

    public Period getPeriod(){
        return period;
    }
    
    @Override
    public int hashCode(){
        // WRONG, 
        int hash=3;
        hash=71*hash+Objects.hashCode(this.room);
        return hash;
//        return 1;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(getClass()!=obj.getClass()){
            return false;
        }
        final Order other=(Order)obj;
        if(!Objects.equals(this.room,other.room)){
            return false;
        }
        return !(other.from.minusDays(1).isBefore(from) && other.to.minusDays(1).isBefore(from) ||
                other.from.plusDays(1).isAfter(to) && other.to.plusDays(1).isAfter(to));
    }

    @Override
    public String toString(){
        return "Order{room="+room+", from="+from+", to="+to+", period(Days) ="+period.getDays()+", numOrder="+numOrder+'}';
    }
}

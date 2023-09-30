package com.viktorshomework.HomeWork2;

import com.viktorshomework.HomeWork2.data.Room;
import java.util.List;
import java.util.stream.IntStream;
import static com.viktorshomework.HomeWork2.Filter.*;
import com.viktorshomework.HomeWork2.data.Book;
import com.viktorshomework.HomeWork2.data.Booking;
import com.viktorshomework.HomeWork2.data.Guest;
import com.viktorshomework.HomeWork2.data.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

public class Reception{
    private static List<Room> rooms;
    private Booking booking;
    // not uses
    private Map<Integer,List<Order>> roomToOrders = new HashMap<>();
    public Reception(int max){
        generateRooms(max);
        this.booking = new Booking("Reception's Booking");
    }
    
    private static void generateRooms(int max) {
        IntUnaryOperator beds = i -> i%4+1;
        IntPredicate balcony = i -> i>30&&i%5!=0;
        IntPredicate viewOfSee = i -> i%3!=2;
        IntUnaryOperator vipRooms = i -> i>=80? 80 : 0;
        Function<Integer, Integer> cost = i -> beds.applyAsInt(i)*200 + (balcony.test(i)? 100 : 0) 
                + (viewOfSee.test(i)? 50 : 0) + vipRooms.applyAsInt(i);
        rooms = IntStream.rangeClosed(1,max).mapToObj(i -> 
                new Room(i,beds.applyAsInt(i),balcony.test(i),viewOfSee.test(i), cost.apply(i))).toList();
    }
    public StreamOfRooms<Room> rooms() {
        return new StreamOfRooms<Room>(rooms);
    }
    public void registerGuest(Guest guest, Order order) {
        if(isOrderAlredyPresentInSystem(order)){
            System.out.println("\u001B[31m"+ "I'm sorry, but this order already parallel prepared for another customers"+"\u001B[0m");
            System.out.println("\u001B[36m"+ guest.getName()+"  --  "+ order+"\u001B[0m");
            return;
        } 
        BiFunction<Integer, List<Order>, List<Order>> rm = (i,m) -> {
            m = (m==null)? new ArrayList<Order>() : m;
            m.add(order);
            return m;
        };
        // not uses
        roomToOrders.compute(order.getRoom().number(),rm);
        booking.addToBook(order,guest);
        guest.addToBook(order);
    }
    // not uses
    public List<Order> getOrdersByRoom(int i) {
        return roomToOrders.get(i);
    }
    public BiFunction<LocalDate, LocalDate, Predicate<Room>> isOrderedRoomInDate = 
            (from,to) -> r -> isOrderAlredyPresentInSystem(new Order(r,from,to));
//            (from,to) -> r -> true;
//    public boolean isAvailableRoomInDates(Room room, LocalDate from, LocalDate to) {
//        return isOrderAlredyPresentInSystem(new Order(room,from,to));
//    }
    // not uses
//    private boolean isGuestInSystem(Guest guest) {
//        return booking.getOrdersByGuest(guest).size()>0;
//    }
    public boolean isOrderAlredyPresentInSystem(Order order) {
        return booking.getBooking().values().stream().map(b -> b.order()).anyMatch(o -> o.equals(order));
    }
    public Collection<Book> getBooking() {
        return booking.getBooking().values();
    }
    public void clearBooking() {
        roomToOrders.clear();
        booking.getBooking().clear();
    }
    public List<Room> getRoomWithThreeBedsBalconyAndViewOfSee() {
        return rooms.stream()
                .filter(BedsThree.filter)
                .filter(Balcony.filter)
                .filter(ViewOfSee.filter)
                .toList();
    }
}

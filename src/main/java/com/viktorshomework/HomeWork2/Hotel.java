package com.viktorshomework.HomeWork2;

import com.viktorshomework.HomeWork2.data.Guest;
import com.viktorshomework.HomeWork2.data.Order;
import com.viktorshomework.HomeWork2.data.Room;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Hotel{
    // LocalDate, Random and List of Guests for tests
    private static final LocalDate now=LocalDate.now();
    private static final Supplier<Integer> randGuest = () -> ThreadLocalRandom.current().nextInt(0,14);
    private static final Supplier<Integer> randRooms = () -> ThreadLocalRandom.current().nextInt(0,120);
    private static final List<Guest> guests = new ListOfGuests().list;
    public static void main(String[] args){
        Reception reception = new Reception(120);
        
        //  STEP 1 --------- ROOM FILTER TEST ---------------
        // --- Old longTerm decision ---
//        System.out.println("\n__________getRoomWithThreeBedsBalconyAndViewOfSee()___________________\n");
//        reception.getRoomWithThreeBedsBalconyAndViewOfSee().forEach(System.out::println);
        // --- use new combine decision ---
//        System.out.println("\n__________byTwoBeds().byCostsLessThan(600).limit(30).sortByDecreaseCost()___________________\n");
//        reception.rooms()
//                .byTwoBeds()
//                .byCostsLessThan(600)
//                .limit(30)
//                .sortByDecreaseCost()
//                .forEach(System.out::println);
//        System.out.println("\n__________.byCostsLessThan(600).byOneBed().byCostsBetweenBed(240,400)___________________\n");
//        reception.rooms()
//                .byCostsLessThan(600)
//                .byOneBed()
//                .byCostsBetweenBed(240,400)
//                .forEach(System.out::println);
//        System.out.println("\n__________filter(r -> r.number()>=100).withBalcony(true)..sortByInreaseCost()___________________\n");
//        reception.rooms()
//                .filter(r -> r.number()>=100)
//                .withBalcony(true)
//                .sortByInreaseCost()
//                .forEach(System.out::println);
//        System.out.println("\n__________.byBedsLessThan(4).byBedsMoreThan(1).peek(System.out::println).sortByInreaseCost()\n");
//        List<Room> toList=reception.rooms()
//                .byBedsLessThan(4)
//                .byBedsMoreThan(1)
//                .peek(System.out::println)
//                .sortByInreaseCost()
//                .toList();
//        System.out.println("_____________________________\n");
//        toList.forEach(System.out::println);
        // STEP 2  --------  START TEST WITH ORDERED ROOMS  -----------
//        printReceptionOrdersAndGuestsOrders(reception);
//        testOneGuestManyRoomsDifferendDays(reception);
//        testOneGuestManyRoomsSameDays(reception);
//        testOneGuestOneRoomsDifferentDays(reception);
//        testOneGuestOneRoomsSameDays(reception);
//        testManyGuestManyRoomsDifferendDays(reception);
//        testManyGuestManyRoomsSameDays(reception);
//        testManyGuestOneRoomsDifferentDays(reception);
//        testManyGuestOneRoomsSameDays(reception);
//        
//        printReceptionOrdersAndGuestsOrders(reception);

        // STEP 3  ---------  COMBINE STEP 1 AND 2, ---- ADD FILTER ORDERED/EMPTY ROOMS
        
        Room room;
        Order order;
        for(int i=1; i<=20; i++) {
            room = reception.rooms().getRoomByNum(i).get();
//            order = new Order(room,now.plusDays(i), now.plusDays(i*3+5));
            order = new Order(room,now.plusDays(i), now.plusDays(i*2+3));
            reception.registerGuest(guests.get(randGuest.get()),order);
        }
//        reception.rooms().getOrderedRoomInDate(now,now.plusWeeks(3),reception.isOrderedRoomInDate)
        reception.rooms().getOrderedRoomInDate(now.plusDays(5),now.plusDays(17),reception.isOrderedRoomInDate)
//        reception.rooms().getFreeRoomInDate(now.plusWeeks(2),now.plusWeeks(4),reception.isOrderedRoomInDate)
//        reception.rooms().getFreeRoomInDate(now.plusDays(7),now.plusDays(22),reception.isOrderedRoomInDate)
                .forEach(System.out::println);
        
        System.out.println("\n\t------- CHECK ---------\n");
        
        printReceptionOrdersAndGuestsOrders(reception);
        
        System.out.println("\n\t--COMBINE--\n");
        
        reception
                .rooms()
                .byBedsLessThan(4)
                .byBedsMoreThan(0)
                .byCostsMoreThan(300)
                .withViewOfSee(true)
                .sortByInreaseCost()
                .getFreeRoomInDate(now,now.plusMonths(2),reception.isOrderedRoomInDate)
                .forEach(System.out::println);
        
    }
    public static void printReceptionOrdersAndGuestsOrders(Reception reception) {
        System.out.println("\n\t\tReception Orders : ");
        reception.getBooking().stream()
                .peek(b -> System.out.print(b.guest().getName()+", "))
                .map(b -> b.order())
                .forEach(System.out::println);
        System.out.println("\n\t\tGuests Orders : ");
        guests.stream()
                .peek(b -> System.out.println(b.getName()))
                .forEach(g -> g.getOrders().forEach(System.out::println));
        
    }
    public static void cleanReceptionAndGuestsBooking(Reception reception) {
        reception.clearBooking();
        guests.stream().forEach(g -> g.clearBooking());
    }
    public static void testOneGuestManyRoomsDifferendDays(Reception reception){
        final Guest guest=guests.get(randGuest.get());
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room;
        for(int i=0,j=0; i<10; i++, j+=4) {
            room = rooms.get(randRooms.get());
            order = new Order(room,now.plusDays(j),now.plusDays(j+3));
            reception.registerGuest(guest,order);
        }
    }
    public static void testOneGuestManyRoomsSameDays(Reception reception){
        final Guest guest=guests.get(randGuest.get());
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room;
        for(int i=0; i<10; i++) {
            room = rooms.get(randRooms.get());
            order = new Order(room,now.plusDays(i+1),now.plusDays(i+3));
            reception.registerGuest(guest,order);
        }
    }
    public static void testOneGuestOneRoomsDifferentDays(Reception reception){
        final Guest guest=guests.get(randGuest.get());
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room = rooms.get(randRooms.get());
        for(int i=0,j=0; i<10; i++, j+=4) {
            order = new Order(room,now.plusDays(j),now.plusDays(j+3));
            reception.registerGuest(guest,order);
        }
    }
    public static void testOneGuestOneRoomsSameDays(Reception reception){
        final Guest guest=guests.get(randGuest.get());
        final List<Room> rooms=reception.rooms().toList();
        Room room = rooms.get(randRooms.get());
        Order order;
        for(int i=0; i<10; i++) {
            order = new Order(room,now.plusDays(i+1),now.plusDays(i+3));
            reception.registerGuest(guest,order);
        }
    }
    public static void testManyGuestManyRoomsDifferendDays(Reception reception){
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room;
        for(int i=0,j=0; i<10; i++, j+=4) {
            room = rooms.get(randRooms.get());
            order = new Order(room,now.plusDays(j),now.plusDays(j+3));
            reception.registerGuest(guests.get(i),order);
        }
    }
    public static void testManyGuestManyRoomsSameDays(Reception reception){
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room;
        for(int i=0; i<10; i++) {
            room = rooms.get(randRooms.get());
            order = new Order(room,now.plusDays(i),now.plusDays(i+3));
            reception.registerGuest(guests.get(i),order);
        }
    }
    public static void testManyGuestOneRoomsDifferentDays(Reception reception){
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room = rooms.get(randRooms.get());
        for(int i=0,j=0; i<10; i++, j+=4) {
            order = new Order(room,now.plusDays(j),now.plusDays(j+3));
            reception.registerGuest(guests.get(i),order);
        }
    }
    public static void testManyGuestOneRoomsSameDays(Reception reception){
        final List<Room> rooms=reception.rooms().toList();
        Room room = rooms.get(randRooms.get());
        Order order;
        for(int i=0; i<10; i++) {
            order = new Order(room,now.plusDays(i),now.plusDays(i+3));
            reception.registerGuest(guests.get(i),order);
        }
    }
}

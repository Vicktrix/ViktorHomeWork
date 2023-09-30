package com.viktorshomework.HomeWork2;

import com.viktorshomework.HomeWork2.data.Room;
import java.util.function.Predicate;

public enum Filter {
        BedsOne(r -> r.beds()==1),
        BedsTwo(r -> r.beds()==2),
        BedsThree(r -> r.beds()==3),
        BedsFour(r -> r.beds()==4),
        Balcony(r -> r.balcony()), 
        ViewOfSee(r -> r.viewOfSee());
        Predicate<Room> filter;
        Filter(Predicate<Room> p){ filter = p; }
}

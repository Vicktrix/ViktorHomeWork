package com.viktorshomework.HomeWork1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Supplier;
import static java.math.MathContext.*;
import static java.time.Month.*;

public class HomeWork_1_2{

public static void main(String[] args){
    final LocalDate birthDay=LocalDate.of(1970,JANUARY,1);
    Prodejce goodProdejce=new Prodejce.Builder()
            .name("Karel")
            .birthDay(birthDay)
            .numberOfDeal(17)
            .carrot(BigDecimal.valueOf(42))
            .town("Prague")
            .spz("AB-12-34-CD")
            .consume(BigDecimal.TEN)
            .inetAddress("www.someAddress.someWhere")
            .build();
    System.out.println("Good Prodejce - "+goodProdejce);
    Prodejce badProdejce=new Prodejce.Builder().build();
    System.out.println("Bad Prodejce  - "+badProdejce);
    badProdejce.setCarrot(BigDecimal.valueOf(7));
    Supplier<BigDecimal> exception = () -> {
            System.out.println("\tNumber of deals is ZERO\n\t you can`t divide by ZERO");
            return BigDecimal.valueOf(-1);
    };
    printCarrotByDeal(goodProdejce,exception);
    printCarrotByDeal(badProdejce,exception);
}
    static void printCarrotByDeal(Prodejce prodejce, Supplier<BigDecimal> or) {
        System.out.println("průměrné množství(ton) prodané mrkve na jednu smlouvu ");
        BigDecimal carrotByDeals = prodejce.getCarrot().divide(prodejce.getNumberOfDeal()
            .orElseGet(or), DECIMAL32);
        System.out.println("\t "+prodejce.getName()+"\t"+carrotByDeals+"\n");
    }
}
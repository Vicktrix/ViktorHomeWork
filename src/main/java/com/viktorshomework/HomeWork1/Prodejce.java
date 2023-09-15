package com.viktorshomework.HomeWork1;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Optional;

public class Prodejce{

    private String name="Default Name";
    private LocalDate birthDay=LocalDate.now();
    private int numberOfDeal=0;
    private BigDecimal carrot=BigDecimal.ZERO;
    private String town="Unknown";
    private String spz="ENPTY";
    private BigDecimal consume=BigDecimal.valueOf(-1);
    private InetAddress inetAddress=getAddress("www.google.cz");
    
private Prodejce(){}

private static InetAddress getAddress(String address){
    InetAddress inet;
    try{
        inet=InetAddress.getByName(address);
    }catch(UnknownHostException ex){
        System.out.println("Wrong address, we changes to default value \n "+ex);
        inet=InetAddress.getLoopbackAddress();
    }
    return inet;
}

public LocalDate getBirthDay(){
    return birthDay;
}

public String getName(){
    return name;
}

public void setName(String name){
    this.name=name;
}
// rozhodnul jsem si ze by v metode main clovek sam rozhodoval co ma delat kdyz deli na nulu
public Optional<BigDecimal> getNumberOfDeal(){
    return numberOfDeal == 0? 
            Optional.empty() : Optional.of(BigDecimal.valueOf(numberOfDeal));
}

public void setNumberOfDeal(Optional<BigDecimal> numberOfDeal){
    this.numberOfDeal=numberOfDeal.orElseGet(() -> BigDecimal.ZERO).intValue();
}

public BigDecimal getCarrot(){
    return carrot;
}

public void setCarrot(BigDecimal carrot){
    this.carrot=carrot;
}

public String getTown(){
    return town;
}

public void setTown(String town){
    this.town=town;
}

public String getSpz(){
    return spz;
}

public void setSpz(String spz){
    this.spz=spz;
}

public BigDecimal getConsume(){
    return consume;
}

public void setConsume(BigDecimal consume){
    this.consume=consume;
}

public InetAddress getInetAddress(){
    return inetAddress;
}

public void setInetAddress(InetAddress inetAddress){
    this.inetAddress=inetAddress;
}

@Override
public String toString(){
    return "Prodejce{"+"name="+name+", birthDay="+birthDay+", numberOfDeal="+numberOfDeal
            +", carrot="+carrot+", town="+town+", spz="+spz+",\n\t consume liters per 100 km ="+consume
            +", inetAddress="+inetAddress+"}\n";
}
    // muj prvni Builder, vetsi pocet parametru jsem nikdy nemel
    static class Builder{

    public Builder(){
        that=new Prodejce();
    }
    private Prodejce that;

    Builder name(String name){
        that.name=name;
        return this;
    }

    Builder birthDay(LocalDate birthDay){
        that.birthDay=birthDay;
        return this;
    }

    Builder numberOfDeal(int numberOfDeal){
        that.numberOfDeal=numberOfDeal;
        return this;
    }

    Builder carrot(BigDecimal carrot){
        that.carrot=carrot;
        return this;
    }

    Builder town(String town){
        that.town=town;
        return this;
    }

    Builder spz(String spz){
        that.spz=spz;
        return this;
    }

    Builder consume(BigDecimal eatTank){
        that.consume=eatTank;
        return this;
    }

    Builder inetAddress(String address){
        that.inetAddress=getAddress(address);
        return this;
    }

    Prodejce build(){
        return that;
    }
    }
}

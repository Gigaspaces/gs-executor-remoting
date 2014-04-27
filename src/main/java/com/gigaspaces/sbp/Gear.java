package com.gigaspaces.sbp;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 4/27/14
 * Time: 5:14 AM
 */
public class Gear implements WatchPart {

    private Integer number;
    private Float weight;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight){
        this.weight = weight;
    }
}

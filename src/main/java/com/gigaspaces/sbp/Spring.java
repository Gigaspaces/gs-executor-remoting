package com.gigaspaces.sbp;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 4/27/14
 * Time: 5:16 AM
 */
public class Spring implements WatchPart {

    private Float weight;

    @Override
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}

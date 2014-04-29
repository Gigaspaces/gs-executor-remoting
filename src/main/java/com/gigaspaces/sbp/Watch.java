package com.gigaspaces.sbp;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 4/27/14
 * Time: 5:12 AM
 * Provides toy model
 */
@SpaceClass
public class Watch {

    // canonical GigaSpaces constructs
    private String spaceId;
    private Integer partitionId;

    // some properties
    private String name;
    private Float weight;
    private List<Gear> gears;
    private List<Spring> springs;

    private boolean dirty;

    @SpaceId(autoGenerate = true)
    public String getSpaceId(){
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    @SpaceRouting
    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Gear> getGears() {
        return gears;
    }

    public void setGears(List<Gear> gears) {
        this.gears = gears;
        dirty = true;
    }

    public Float getWeight() {
        if( weight != null && !dirty ) return weight;
        weight = 0F;
        for(Spring spring : springs ) weight += spring.getWeight();
        for(Gear gear : gears ) weight += gear.getWeight();
        return weight;
    }

    public List<Spring> getSprings() {
        return springs;
    }

    public void setSprings(List<Spring> springs) {
        this.springs = springs;
        dirty = true;
    }

}

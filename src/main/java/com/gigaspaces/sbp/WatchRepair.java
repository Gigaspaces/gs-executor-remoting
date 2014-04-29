package com.gigaspaces.sbp;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 4/28/14
 * Time: 1:20 PM
 */
public interface WatchRepair {

    /**
     * Executes a set on the watch to be repaired, in a mechanism that is collocated with
     * the data.
     * @param toRepair on which to perform the gear update
     * @param newGears gears to use
     */
    void switchGears(Watch toRepair, List<Gear> newGears);

}

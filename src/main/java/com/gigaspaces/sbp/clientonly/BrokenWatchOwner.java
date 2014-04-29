package com.gigaspaces.sbp.clientonly;

import com.gigaspaces.sbp.Gear;
import com.gigaspaces.sbp.Watch;
import com.gigaspaces.sbp.WatchRepair;
import org.openspaces.remoting.ExecutorProxy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 4/28/14
 * Time: 10:48 PM
 */
@Component
public class BrokenWatchOwner {

    @ExecutorProxy
    private WatchRepair watchRepair;

    public BrokenWatchOwner(WatchRepair watchRepair) {
        this.watchRepair = watchRepair;
    }

    public void sendRequestWithParts(Watch fixMe, List<Gear> withThese){
        watchRepair.switchGears(fixMe, withThese);
    }

}

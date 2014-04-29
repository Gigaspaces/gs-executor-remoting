package com.gigaspaces.sbp.serveronly;

import com.gigaspaces.sbp.Gear;
import com.gigaspaces.sbp.Watch;
import com.gigaspaces.sbp.WatchRepair;
import org.openspaces.core.GigaSpace;
import org.openspaces.remoting.RemotingService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 4/28/14
 * Time: 1:22 PM
 */
@RemotingService
public class RetailShop implements WatchRepair {

    @Resource
    private GigaSpace gigaSpace;

    @Override
    public void switchGears(Watch toRepair, List<Gear> newGears) {

        Watch original = gigaSpace.readById(Watch.class, toRepair.getSpaceId());
        original.setGears(newGears);
//        gigaSpace.write(original);

    }

}
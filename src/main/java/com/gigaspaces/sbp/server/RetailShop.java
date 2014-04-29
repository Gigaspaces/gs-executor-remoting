package com.gigaspaces.sbp.server;

import com.gigaspaces.sbp.Gear;
import com.gigaspaces.sbp.Watch;
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
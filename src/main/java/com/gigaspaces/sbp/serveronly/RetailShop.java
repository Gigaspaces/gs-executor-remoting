package com.gigaspaces.sbp.serveronly;

import com.gigaspaces.sbp.Gear;
import com.gigaspaces.sbp.Watch;
import com.gigaspaces.sbp.services.WatchRepair;
import org.openspaces.core.GigaSpace;
import org.openspaces.remoting.RemotingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private GigaSpace gigaSpace;

    @Override
    public Watch switchGears(Watch toRepair, List<Gear> newGears) {

        logger.debug(String.format("Watch: %s .", toRepair));
        for( Gear gear : newGears) logger.debug(String.format("Gears: %s", gear));
        logger.debug(String.format("GigaSpace %s .", gigaSpace));

        Watch original = gigaSpace.readById(Watch.class, toRepair.getSpaceId());
        original.setGears(newGears);
        gigaSpace.write(original);

        return original;

    }

}
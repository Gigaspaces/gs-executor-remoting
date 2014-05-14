gs-executor-remoting
====================

Example of how to do executor remoting, with emphasis on how to use the [Projection API]
(http://docs.gigaspaces.com/xap97/query-partial-results.html) to avoid network, memory and CPU overhead.

1. Once you've checked this codebase out, put a valid gslicense.xml in the src/test/resources directory. 
(This demo uses 2 partitions, which is incompatible with the freeware licensing terms.)

1. To build a Processing Unit, do:

	$ gradle -C rebuild clean ear -x test

This will produce a Processing Unit, named "build/libs/executor-remoting-pu.jar".

1. Start a grid:

    $ gs-agent.(sh|bat)  gsa.global.lus 1 gsa.global.gsm 1 gsa.gsc 2
    
1. Deploy the Processing Unit with settings: cluster_schema=partitioned-sync2backup total_members=2,1 .

1. Run the WatchRepairSuite of tests from the command line: 

    $ gradle clean test -i
    
That's it.
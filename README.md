gs-executor-remoting
====================

This codebase serves as an example of how to do [Executor Based Remoting](http://docs.gigaspaces.com/xap97/executor-based-remoting.html) 
and the [Projection API] (http://docs.gigaspaces.com/xap97/query-partial-results.html) . In combination, these techniques
help us to avoid network, memory and CPU overhead.

+ Once you've checked this repository out, put a valid [License Key](http://docs.gigaspaces.com/xap97/license-key.html) 
in the [src/test/resources](https://github.com/jasonnerothin/gs-executor-remoting/tree/master/src/test/resources) 
directory. 
(This demo uses 2 partitions, which is incompatible with the freeware licensing terms.)

+ Open up the WatchRepairSuite. Two tests are provided: "switchGears changes gears" and "switchGears changes gears 
(with projection)". 
 
Either can be run or debugged in a properly configured IDE: IntelliJ IDEA requires the [scala plugin]
(http://confluence.jetbrains.com/display/SCA/Scala+Plugin+for+IntelliJ+IDEA) be installed. The gradle plugin now ships 
with it installed, for versions >= 12. There is a good [scala flavor for Eclipse](http://scala-ide.org/), as well.
  
If you wish to run this code from the command line, you may do so by checking out the "cli" branch of this repository
and look at the readme.
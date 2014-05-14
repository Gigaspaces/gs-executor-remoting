gs-executor-remoting
====================

Example of how to do executor remoting in an efficient manner by using the [Projection API](http://docs.gigaspaces.com/xap97/query-partial-results.html) to avoid network, memory and CPU overhead.

To build a processing unit, do:

	$ gradle clean assemble -x test

which will produce a Processing Unit, named "./build/libs/gs-executor-remoting.ear".

Then deploy to a grid started, thusly:

    $ gs-agent.(sh|bat) gsa.gsc 2 gsa.global.lus 1 gsa.global.gsm 1

Then, run [WatchRepairSuite](https://github.com/jasonnerothin/gs-executor-remoting/blob/master/src/test/scala/com/gigaspaces/sbp/WatchRepairSuite.scala#L17) from an IDE or command line, thusly:

    $ gradle clean test -i

That's it.

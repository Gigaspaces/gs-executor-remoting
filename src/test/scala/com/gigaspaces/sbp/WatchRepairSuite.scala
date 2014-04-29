package com.gigaspaces.sbp

import com.gigaspaces.GsI10nSuite
import scala.util.Random
import org.scalatest.ConfigMap
import com.j_spaces.core.client.SQLQuery
import com.gigaspaces.sbp.server.WatchRepair

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 4/27/14
 * Time: 5:30 AM
 */
class WatchRepairSuite extends GsI10nSuite {

  // SETUP STUFF

  val rand = new Random(System.currentTimeMillis())
  val maxNumPartsPerWatch = 3
  val numPartitions = 2
  val numTestWatches = 4

  val spaceName = classOf[WatchRepairSuite].getSimpleName

  defaults = Map[String, Any](
    schemaProperty -> "partitioned-sync2backup"
    , numInstancesProperty -> int2Integer(numPartitions)
    , numBackupsProperty -> int2Integer(0)
    , instanceIdProperty -> int2Integer(1)
    , spaceUrlProperty -> s"/./$spaceName"
    , spaceModeProperty -> SpaceMode.Embedded
    , configLocationProperty -> "classpath*:/com/gigaspaces/sbp/WatchRepairPu.xml"
    , localViewQueryListProperty -> List[SQLQuery[_]]()
  )

  val defaultConfigMap = new ConfigMap( defaults )

  var watchRepair: WatchRepair = null

  override def beforeAll(cm: ConfigMap): Unit = {
    setupWith(defaultConfigMap)
  }

  var testWatches: Seq[Watch] = null

  override def beforeEach(): Unit = {
    testWatches = writeTestWatches()
  }

  def writeTestWatches(): List[Watch] = {
    val testWatches = {
      var list = List[Watch]()
      for (i <- 1 to numTestWatches + 1) list = list :+ testWatch(i)
      list
    }
    testWatches.foreach {
      w => w.setSpaceId(gigaSpace.write(w).getUID)
    }
    testWatches
  }

  // TESTS

  test("switchGears changes gears") {

    val b4Update = readFromGigaSpace
    val oldGears = b4Update.getGears
    val newGears = testGears()
    assume(oldGears != newGears)

    watchRepair.switchGears(b4Update, newGears)

    val afterGears = readFromGigaSpace.getGears
    assert(afterGears === newGears, "we've changed gears")

  }

  test("switchGears changes gears (with projection)") {

    val b4Update = readFromGigaSpace
    val oldGears = b4Update.getGears
    val newGears = testGears()
    assume(oldGears != newGears)

    watchRepair.switchGears(b4Update, newGears)

    val afterGears = readFromGigaSpace.getGears
    assert(afterGears === newGears, "we've changed gears (with projection)")

  }

  def readFromGigaSpaceWithProjection: Watch = {

    val query = new SQLQuery[Watch](classOf[Watch], "spaceId = ?", firstTestWatch.getSpaceId)
      .setProjections("spaceId","partitionId","name","weight")
    val watch = gigaSpace.read[Watch](query)

    assume(watch != null, "Test watch not returned from GigaSpace")

    watch

  }

  def firstTestWatch: Watch = {
    val watch = testWatches.filter(w => w.getName == "Watch 1").head
    assume(watch != null, "Test watch was not written as expected.")
    watch
  }

  def readFromGigaSpace: Watch = {

    val watch = gigaSpace.readById(classOf[Watch], firstTestWatch.getSpaceId)
    assume(watch != null, "Test watch not returned from GigaSpace")
    watch

  }

  // TEST DATA GEN STUFF
  def randUpper = math.abs(rand.nextInt(maxNumPartsPerWatch)) + 1

  def testWatch(num: Int): Watch = {
    val w = new Watch
    w.setName(s"Watch $num")
    w.setPartitionId(num % numPartitions)
    w.setGears(testGears())
    w.setSprings(testSprings())
    w
  }

  def testGears(): java.util.List[Gear] = {
    val list = new java.util.ArrayList[Gear]()
    for (i <- 0 to randUpper) list.add(testGear())
    list
  }

  def testGear(): Gear = {
    setMass(new Gear).asInstanceOf[Gear]
  }

  def testSprings(): java.util.List[Spring] = {
    val list = new java.util.ArrayList[Spring]()
    for (i <- 0 to randUpper) list.add(testSpring())
    list
  }

  def testSpring(): Spring = {
    setMass(new Spring).asInstanceOf[Spring]
  }

  def setMass(part: WatchPart): WatchPart = {
    part.setWeight(Math.abs(rand.nextFloat()))
    part
  }

}
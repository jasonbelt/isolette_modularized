package isolette__JVM

import org.sireum._
import art.scheduling.static.Schedule.DScheduleSpec

object ScheduleProvider {

  def getRoundRobinOrder(): ISZ[art.Bridge] = {
    return Schedulers.roundRobinSchedule
  }

  def getStaticSchedule(): DScheduleSpec = {
    return Schedulers.staticSchedule
  }
}

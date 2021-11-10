// #Sireum
package isolette__JVM

import org.sireum._
import art.Art
import art.scheduling.legacy.Legacy
import art.scheduling.roundrobin.RoundRobin
import art.scheduling.static.Schedule.{DSchedule, DScheduleSpec, Slot}
import art.scheduling.static.StaticScheduler

// This file was auto-generated.  Do not edit

@datatype class ProcessorTimingProperties(val clockPeriod: Option[Z],
                                          val framePeriod: Option[Z],
                                          val maxDomain: Option[Z],
                                          val slotTime: Option[Z])

@datatype class ThreadTimingProperties(val domain: Option[Z],
                                       val computeExecutionTime: Option[(Z, Z)])

object Schedulers {

  val isolette_single_sensor_Instance_isolette_processor_timingProperties: ProcessorTimingProperties = ProcessorTimingProperties(
    clockPeriod = Some(2),
    framePeriod = Some(1000),
    maxDomain = Some(10),
    slotTime = None())

  val isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface_mrit_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(5))

  val isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_heat_source_mhst_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(6))

  val isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_mode_mrmt_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(4))

  val isolette_single_sensor_Instance_thermostat_monitor_temperature_manage_alarm_mat_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(9))

  val isolette_single_sensor_Instance_thermostat_monitor_temperature_manage_monitor_interface_mmit_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(8))

  val isolette_single_sensor_Instance_thermostat_monitor_temperature_manage_monitor_mode_mmmt_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(7))

  val isolette_single_sensor_Instance_operator_interface_oip_oit_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(2))

  val isolette_single_sensor_Instance_temperature_sensor_cpi_thermostat_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(3))

  val isolette_single_sensor_Instance_heat_source_cpi_heat_controller_timingProperties: ThreadTimingProperties = ThreadTimingProperties(
    computeExecutionTime = Some((6, 6)),
    domain = Some(10))

  // roundRobinSchedule represents the component dispatch order
  val roundRobinSchedule: ISZ[art.Bridge] = Arch.ad.components

  val framePeriod: Z = 1000
  val numComponents: Z = Arch.ad.components.size
  val maxExecutionTime: Z = numComponents / framePeriod

  // staticSchedule represents the component dispatch order
  val staticSchedule: DScheduleSpec = DScheduleSpec(0, 0, DSchedule(ISZ(
    Slot(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface_mrit.id, maxExecutionTime),
    Slot(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_heat_source_mhst.id, maxExecutionTime),
    Slot(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_mode_mrmt.id, maxExecutionTime),
    Slot(Arch.isolette_single_sensor_Instance_thermostat_monitor_temperature_manage_alarm_mat.id, maxExecutionTime),
    Slot(Arch.isolette_single_sensor_Instance_thermostat_monitor_temperature_manage_monitor_interface_mmit.id, maxExecutionTime),
    Slot(Arch.isolette_single_sensor_Instance_thermostat_monitor_temperature_manage_monitor_mode_mmmt.id, maxExecutionTime),
    Slot(Arch.isolette_single_sensor_Instance_operator_interface_oip_oit.id, maxExecutionTime),
    Slot(Arch.isolette_single_sensor_Instance_temperature_sensor_cpi_thermostat.id, maxExecutionTime),
    Slot(Arch.isolette_single_sensor_Instance_heat_source_cpi_heat_controller.id, maxExecutionTime)
  )))


  def getRoundRobinScheduler(schedule: Option[ISZ[art.Bridge]]): RoundRobin = {
    if(roundRobinSchedule.isEmpty) {} // line needed for transpiler; do not remove
    schedule match {
      case Some(s) => return RoundRobin(s)
      case _ => return RoundRobin(ScheduleProviderI.getRoundRobinOrder())
    }
  }

  def getStaticScheduler(schedule: Option[DScheduleSpec]): StaticScheduler = {
    if(staticSchedule.schedule.slots.isEmpty) {} // line needed for transpiler; do not remove
    schedule match {
      case Some(s) => return StaticScheduler(Arch.ad.components, s)
      case _ => return StaticScheduler(Arch.ad.components, ScheduleProviderI.getStaticSchedule())
    }
  }

  def getLegacyScheduler(): Legacy = {
    return Legacy(Arch.ad.components)
  }
}

@ext(name = "ScheduleProvider") object ScheduleProviderI {
  def getRoundRobinOrder(): ISZ[art.Bridge] = $
  def getStaticSchedule(): DScheduleSpec = $
}
// #Sireum

package isolette__JVM.Monitor

import org.sireum._
import isolette__JVM._

// This file will not be overwritten so is safe to edit
object Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_mat {

  def initialise(api: Manage_Alarm_impl_Initialization_Api): Unit = {
    // example api usage

    api.logInfo("Example info logging")
    api.logDebug("Example debug logging")
    api.logError("Example error logging")

    api.put_alarm_control(Isolette_Data_Model.On_Off.byOrdinal(0).get)
  }

  def timeTriggered(api: Manage_Alarm_impl_Operational_Api): Unit = {
    // example api usage

    val apiUsage_current_tempWstatus: Option[Isolette_Data_Model.TempWstatus_impl] = api.get_current_tempWstatus()
    api.logInfo(s"Received on current_tempWstatus: ${apiUsage_current_tempWstatus}")
    val apiUsage_lower_alarm_temp: Option[Isolette_Data_Model.Temp_impl] = api.get_lower_alarm_temp()
    api.logInfo(s"Received on lower_alarm_temp: ${apiUsage_lower_alarm_temp}")
    val apiUsage_upper_alarm_temp: Option[Isolette_Data_Model.Temp_impl] = api.get_upper_alarm_temp()
    api.logInfo(s"Received on upper_alarm_temp: ${apiUsage_upper_alarm_temp}")
    val apiUsage_monitor_mode: Option[Isolette_Data_Model.Monitor_Mode.Type] = api.get_monitor_mode()
    api.logInfo(s"Received on monitor_mode: ${apiUsage_monitor_mode}")
  }

  def activate(api: Manage_Alarm_impl_Operational_Api): Unit = { }

  def deactivate(api: Manage_Alarm_impl_Operational_Api): Unit = { }

  def finalise(api: Manage_Alarm_impl_Operational_Api): Unit = { }

  def recover(api: Manage_Alarm_impl_Operational_Api): Unit = { }
}

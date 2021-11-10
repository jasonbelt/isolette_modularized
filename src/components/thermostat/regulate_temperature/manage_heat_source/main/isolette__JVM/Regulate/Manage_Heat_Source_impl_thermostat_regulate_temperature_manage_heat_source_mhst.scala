// #Sireum

package isolette__JVM.Regulate

import org.sireum._
import isolette__JVM._

// This file will not be overwritten so is safe to edit
object Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_mhst {

  def initialise(api: Manage_Heat_Source_impl_Initialization_Api): Unit = {
    // example api usage

    api.logInfo("Example info logging")
    api.logDebug("Example debug logging")
    api.logError("Example error logging")

    api.put_heat_control(Isolette_Data_Model.On_Off.byOrdinal(0).get)
  }

  def timeTriggered(api: Manage_Heat_Source_impl_Operational_Api): Unit = {
    // example api usage
    val apiUsage_current_tempWstatus: Option[Isolette_Data_Model.TempWstatus_impl] = api.get_current_tempWstatus()
    api.logInfo(s"Received on current_tempWstatus: ${apiUsage_current_tempWstatus}")
    val apiUsage_lower_desired_temp: Option[Isolette_Data_Model.Temp_impl] = api.get_lower_desired_temp()
    api.logInfo(s"Received on lower_desired_temp: ${apiUsage_lower_desired_temp}")
    val apiUsage_upper_desired_temp: Option[Isolette_Data_Model.Temp_impl] = api.get_upper_desired_temp()
    api.logInfo(s"Received on upper_desired_temp: ${apiUsage_upper_desired_temp}")
    val apiUsage_regulator_mode: Option[Isolette_Data_Model.Regulator_Mode.Type] = api.get_regulator_mode()
    api.logInfo(s"Received on regulator_mode: ${apiUsage_regulator_mode}")
  }

  def activate(api: Manage_Heat_Source_impl_Operational_Api): Unit = { }

  def deactivate(api: Manage_Heat_Source_impl_Operational_Api): Unit = { }

  def finalise(api: Manage_Heat_Source_impl_Operational_Api): Unit = { }

  def recover(api: Manage_Heat_Source_impl_Operational_Api): Unit = { }
}

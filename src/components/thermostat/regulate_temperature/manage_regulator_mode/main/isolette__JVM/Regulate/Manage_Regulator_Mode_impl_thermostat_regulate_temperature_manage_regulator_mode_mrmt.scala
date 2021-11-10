// #Sireum

package isolette__JVM.Regulate

import org.sireum._
import isolette__JVM._

// This file will not be overwritten so is safe to edit
object Manage_Regulator_Mode_impl_thermostat_regulate_temperature_manage_regulator_mode_mrmt {

  def initialise(api: Manage_Regulator_Mode_impl_Initialization_Api): Unit = {
    // example api usage

    api.logInfo("Example info logging")
    api.logDebug("Example debug logging")
    api.logError("Example error logging")

    api.put_regulator_mode(Isolette_Data_Model.Regulator_Mode.byOrdinal(0).get)
  }

  def timeTriggered(api: Manage_Regulator_Mode_impl_Operational_Api): Unit = {
    // example api usage

    val apiUsage_current_tempWstatus: Option[Isolette_Data_Model.TempWstatus_impl] = api.get_current_tempWstatus()
    api.logInfo(s"Received on current_tempWstatus: ${apiUsage_current_tempWstatus}")
    val apiUsage_interface_failure: Option[Isolette_Data_Model.Failure_Flag_impl] = api.get_interface_failure()
    api.logInfo(s"Received on interface_failure: ${apiUsage_interface_failure}")
    val apiUsage_internal_failure: Option[Isolette_Data_Model.Failure_Flag_impl] = api.get_internal_failure()
    api.logInfo(s"Received on internal_failure: ${apiUsage_internal_failure}")
  }

  def activate(api: Manage_Regulator_Mode_impl_Operational_Api): Unit = { }

  def deactivate(api: Manage_Regulator_Mode_impl_Operational_Api): Unit = { }

  def finalise(api: Manage_Regulator_Mode_impl_Operational_Api): Unit = { }

  def recover(api: Manage_Regulator_Mode_impl_Operational_Api): Unit = { }
}

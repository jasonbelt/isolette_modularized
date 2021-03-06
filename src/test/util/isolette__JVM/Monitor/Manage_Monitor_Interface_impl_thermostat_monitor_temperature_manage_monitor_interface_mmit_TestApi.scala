package isolette__JVM.Monitor

import org.sireum._
import art.{ArtNative_Ext, Empty}
import isolette__JVM._

// This file was auto-generated.  Do not edit
abstract class Manage_Monitor_Interface_impl_thermostat_monitor_temperature_manage_monitor_interface_mmit_TestApi extends BridgeTestSuite[Manage_Monitor_Interface_impl_thermostat_monitor_temperature_manage_monitor_interface_mmit_Bridge](Arch.isolette_single_sensor_Instance_thermostat_monitor_temperature_manage_monitor_interface_mmit) {

  /** helper function to set the values of all input ports.
   * @param upper_alarm_tempWstatus payload for data port upper_alarm_tempWstatus
   * @param lower_alarm_tempWstatus payload for data port lower_alarm_tempWstatus
   * @param current_tempWstatus payload for data port current_tempWstatus
   * @param monitor_mode payload for data port monitor_mode
   */
  def put_concrete_inputs(upper_alarm_tempWstatus : Isolette_Data_Model.TempWstatus_impl,
                          lower_alarm_tempWstatus : Isolette_Data_Model.TempWstatus_impl,
                          current_tempWstatus : Isolette_Data_Model.TempWstatus_impl,
                          monitor_mode : Isolette_Data_Model.Monitor_Mode.Type): Unit = {
    put_upper_alarm_tempWstatus(upper_alarm_tempWstatus)
    put_lower_alarm_tempWstatus(lower_alarm_tempWstatus)
    put_current_tempWstatus(current_tempWstatus)
    put_monitor_mode(monitor_mode)
  }


  /** helper function to check Manage_Monitor_Interface_impl_thermostat_monitor_temperature_manage_monitor_interface_mmit's
   * output ports.  Use named arguments to check subsets of the output ports.
   * @param upper_alarm_temp method that will be called with the value of the outgoing data
   *        port 'upper_alarm_temp'.
   * @param lower_alarm_temp method that will be called with the value of the outgoing data
   *        port 'lower_alarm_temp'.
   * @param monitor_status method that will be called with the value of the outgoing data
   *        port 'monitor_status'.
   * @param interface_failure method that will be called with the value of the outgoing data
   *        port 'interface_failure'.
   */
  def check_concrete_output(upper_alarm_temp: Isolette_Data_Model.Temp_impl => B = upper_alarm_tempParam => {T},
                            lower_alarm_temp: Isolette_Data_Model.Temp_impl => B = lower_alarm_tempParam => {T},
                            monitor_status: Isolette_Data_Model.Status.Type => B = monitor_statusParam => {T},
                            interface_failure: Isolette_Data_Model.Failure_Flag_impl => B = interface_failureParam => {T}): Unit = {
    var testFailures: ISZ[ST] = ISZ()

    val upper_alarm_tempValue: Isolette_Data_Model.Temp_impl = get_upper_alarm_temp().get
    if(!upper_alarm_temp(upper_alarm_tempValue)) {
      testFailures = testFailures :+ st"'upper_alarm_temp' did not match expected: value of the outgoing data port is ${upper_alarm_tempValue}"
    }
    val lower_alarm_tempValue: Isolette_Data_Model.Temp_impl = get_lower_alarm_temp().get
    if(!lower_alarm_temp(lower_alarm_tempValue)) {
      testFailures = testFailures :+ st"'lower_alarm_temp' did not match expected: value of the outgoing data port is ${lower_alarm_tempValue}"
    }
    val monitor_statusValue: Isolette_Data_Model.Status.Type = get_monitor_status().get
    if(!monitor_status(monitor_statusValue)) {
      testFailures = testFailures :+ st"'monitor_status' did not match expected: value of the outgoing data port is ${monitor_statusValue}"
    }
    val interface_failureValue: Isolette_Data_Model.Failure_Flag_impl = get_interface_failure().get
    if(!interface_failure(interface_failureValue)) {
      testFailures = testFailures :+ st"'interface_failure' did not match expected: value of the outgoing data port is ${interface_failureValue}"
    }

    assert(testFailures.isEmpty, st"${(testFailures, "\n")}".render)
  }


  // setter for in DataPort
  def put_upper_alarm_tempWstatus(value : Isolette_Data_Model.TempWstatus_impl): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.upper_alarm_tempWstatus_Id, Isolette_Data_Model.TempWstatus_impl_Payload(value))
  }

  // setter for in DataPort
  def put_lower_alarm_tempWstatus(value : Isolette_Data_Model.TempWstatus_impl): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.lower_alarm_tempWstatus_Id, Isolette_Data_Model.TempWstatus_impl_Payload(value))
  }

  // setter for in DataPort
  def put_current_tempWstatus(value : Isolette_Data_Model.TempWstatus_impl): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.current_tempWstatus_Id, Isolette_Data_Model.TempWstatus_impl_Payload(value))
  }

  // setter for in DataPort
  def put_monitor_mode(value : Isolette_Data_Model.Monitor_Mode.Type): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.monitor_mode_Id, Isolette_Data_Model.Monitor_Mode_Payload(value))
  }

  // getter for out DataPort
  def get_upper_alarm_temp(): Option[Isolette_Data_Model.Temp_impl] = {
    val value: Option[Isolette_Data_Model.Temp_impl] = get_upper_alarm_temp_payload() match {
      case Some(Isolette_Data_Model.Temp_impl_Payload(v)) => Some(v)
      case Some(v) => fail(s"Unexpected payload on port upper_alarm_temp.  Expecting 'Isolette_Data_Model.Temp_impl_Payload' but received ${v}")
      case _ => None[Isolette_Data_Model.Temp_impl]()
    }
    return value
  }

  // payload getter for out DataPort
  def get_upper_alarm_temp_payload(): Option[Isolette_Data_Model.Temp_impl_Payload] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.upper_alarm_temp_Id).asInstanceOf[Option[Isolette_Data_Model.Temp_impl_Payload]]
  }

  // getter for out DataPort
  def get_lower_alarm_temp(): Option[Isolette_Data_Model.Temp_impl] = {
    val value: Option[Isolette_Data_Model.Temp_impl] = get_lower_alarm_temp_payload() match {
      case Some(Isolette_Data_Model.Temp_impl_Payload(v)) => Some(v)
      case Some(v) => fail(s"Unexpected payload on port lower_alarm_temp.  Expecting 'Isolette_Data_Model.Temp_impl_Payload' but received ${v}")
      case _ => None[Isolette_Data_Model.Temp_impl]()
    }
    return value
  }

  // payload getter for out DataPort
  def get_lower_alarm_temp_payload(): Option[Isolette_Data_Model.Temp_impl_Payload] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.lower_alarm_temp_Id).asInstanceOf[Option[Isolette_Data_Model.Temp_impl_Payload]]
  }

  // getter for out DataPort
  def get_monitor_status(): Option[Isolette_Data_Model.Status.Type] = {
    val value: Option[Isolette_Data_Model.Status.Type] = get_monitor_status_payload() match {
      case Some(Isolette_Data_Model.Status_Payload(v)) => Some(v)
      case Some(v) => fail(s"Unexpected payload on port monitor_status.  Expecting 'Isolette_Data_Model.Status_Payload' but received ${v}")
      case _ => None[Isolette_Data_Model.Status.Type]()
    }
    return value
  }

  // payload getter for out DataPort
  def get_monitor_status_payload(): Option[Isolette_Data_Model.Status_Payload] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.monitor_status_Id).asInstanceOf[Option[Isolette_Data_Model.Status_Payload]]
  }

  // getter for out DataPort
  def get_interface_failure(): Option[Isolette_Data_Model.Failure_Flag_impl] = {
    val value: Option[Isolette_Data_Model.Failure_Flag_impl] = get_interface_failure_payload() match {
      case Some(Isolette_Data_Model.Failure_Flag_impl_Payload(v)) => Some(v)
      case Some(v) => fail(s"Unexpected payload on port interface_failure.  Expecting 'Isolette_Data_Model.Failure_Flag_impl_Payload' but received ${v}")
      case _ => None[Isolette_Data_Model.Failure_Flag_impl]()
    }
    return value
  }

  // payload getter for out DataPort
  def get_interface_failure_payload(): Option[Isolette_Data_Model.Failure_Flag_impl_Payload] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.interface_failure_Id).asInstanceOf[Option[Isolette_Data_Model.Failure_Flag_impl_Payload]]
  }

}

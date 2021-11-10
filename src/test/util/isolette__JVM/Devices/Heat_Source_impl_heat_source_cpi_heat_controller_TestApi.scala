package isolette__JVM.Devices

import org.sireum._
import art.{ArtNative_Ext, Empty}
import isolette__JVM._

// This file was auto-generated.  Do not edit
abstract class Heat_Source_impl_heat_source_cpi_heat_controller_TestApi extends BridgeTestSuite[Heat_Source_impl_heat_source_cpi_heat_controller_Bridge](Arch.isolette_single_sensor_Instance_heat_source_cpi_heat_controller) {

  /** helper function to set the values of all input ports.
   * @param heat_control payload for data port heat_control
   */
  def put_concrete_inputs(heat_control : Isolette_Data_Model.On_Off.Type): Unit = {
    put_heat_control(heat_control)
  }


  /** helper function to check Heat_Source_impl_heat_source_cpi_heat_controller's
   * output ports.  Use named arguments to check subsets of the output ports.
   * @param heat_out method that will be called with the value of the outgoing data
   *        port 'heat_out'.
   */
  def check_concrete_output(heat_out: Isolette_Environment.Heat.Type => B = heat_outParam => {T}): Unit = {
    var testFailures: ISZ[ST] = ISZ()

    val heat_outValue: Isolette_Environment.Heat.Type = get_heat_out().get
    if(!heat_out(heat_outValue)) {
      testFailures = testFailures :+ st"'heat_out' did not match expected: value of the outgoing data port is ${heat_outValue}"
    }

    assert(testFailures.isEmpty, st"${(testFailures, "\n")}".render)
  }


  // setter for in DataPort
  def put_heat_control(value : Isolette_Data_Model.On_Off.Type): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.heat_control_Id, Isolette_Data_Model.On_Off_Payload(value))
  }

  // getter for out DataPort
  def get_heat_out(): Option[Isolette_Environment.Heat.Type] = {
    val value: Option[Isolette_Environment.Heat.Type] = get_heat_out_payload() match {
      case Some(Isolette_Environment.Heat_Payload(v)) => Some(v)
      case Some(v) => fail(s"Unexpected payload on port heat_out.  Expecting 'Isolette_Environment.Heat_Payload' but received ${v}")
      case _ => None[Isolette_Environment.Heat.Type]()
    }
    return value
  }

  // payload getter for out DataPort
  def get_heat_out_payload(): Option[Isolette_Environment.Heat_Payload] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.heat_out_Id).asInstanceOf[Option[Isolette_Environment.Heat_Payload]]
  }

}

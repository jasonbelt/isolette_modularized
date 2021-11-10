// #Sireum

package isolette__JVM.Regulate

import org.sireum._
import art._
import isolette__JVM._

@sig trait Manage_Regulator_Interface_impl_Api {
  def id: Art.BridgeId
  def current_tempWstatus_Id : Art.PortId
  def lower_desired_tempWstatus_Id : Art.PortId
  def upper_desired_tempWstatus_Id : Art.PortId
  def regulator_mode_Id : Art.PortId
  def upper_desired_temp_Id : Art.PortId
  def lower_desired_temp_Id : Art.PortId
  def displayed_temp_Id : Art.PortId
  def regulator_status_Id : Art.PortId
  def interface_failure_Id : Art.PortId

  def put_upper_desired_temp(value : Isolette_Data_Model.Temp_impl) : Unit = {
    Art.putValue(upper_desired_temp_Id, Isolette_Data_Model.Temp_impl_Payload(value))
  }

  def put_lower_desired_temp(value : Isolette_Data_Model.Temp_impl) : Unit = {
    Art.putValue(lower_desired_temp_Id, Isolette_Data_Model.Temp_impl_Payload(value))
  }

  def put_displayed_temp(value : Isolette_Data_Model.Temp_impl) : Unit = {
    Art.putValue(displayed_temp_Id, Isolette_Data_Model.Temp_impl_Payload(value))
  }

  def put_regulator_status(value : Isolette_Data_Model.Status.Type) : Unit = {
    Art.putValue(regulator_status_Id, Isolette_Data_Model.Status_Payload(value))
  }

  def put_interface_failure(value : Isolette_Data_Model.Failure_Flag_impl) : Unit = {
    Art.putValue(interface_failure_Id, Isolette_Data_Model.Failure_Flag_impl_Payload(value))
  }

  def logInfo(msg: String): Unit = {
    Art.logInfo(id, msg)
  }

  def logDebug(msg: String): Unit = {
    Art.logDebug(id, msg)
  }

  def logError(msg: String): Unit = {
    Art.logError(id, msg)
  }
}

@datatype class Manage_Regulator_Interface_impl_Initialization_Api (
  val id: Art.BridgeId,
  val current_tempWstatus_Id : Art.PortId,
  val lower_desired_tempWstatus_Id : Art.PortId,
  val upper_desired_tempWstatus_Id : Art.PortId,
  val regulator_mode_Id : Art.PortId,
  val upper_desired_temp_Id : Art.PortId,
  val lower_desired_temp_Id : Art.PortId,
  val displayed_temp_Id : Art.PortId,
  val regulator_status_Id : Art.PortId,
  val interface_failure_Id : Art.PortId) extends Manage_Regulator_Interface_impl_Api

@datatype class Manage_Regulator_Interface_impl_Operational_Api (
  val id: Art.BridgeId,
  val current_tempWstatus_Id : Art.PortId,
  val lower_desired_tempWstatus_Id : Art.PortId,
  val upper_desired_tempWstatus_Id : Art.PortId,
  val regulator_mode_Id : Art.PortId,
  val upper_desired_temp_Id : Art.PortId,
  val lower_desired_temp_Id : Art.PortId,
  val displayed_temp_Id : Art.PortId,
  val regulator_status_Id : Art.PortId,
  val interface_failure_Id : Art.PortId) extends Manage_Regulator_Interface_impl_Api {

  def get_current_tempWstatus() : Option[Isolette_Data_Model.TempWstatus_impl] = {
    val value : Option[Isolette_Data_Model.TempWstatus_impl] = Art.getValue(current_tempWstatus_Id) match {
      case Some(Isolette_Data_Model.TempWstatus_impl_Payload(v)) => Some(v)
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port current_tempWstatus.  Expecting 'Isolette_Data_Model.TempWstatus_impl_Payload' but received ${v}")
        None[Isolette_Data_Model.TempWstatus_impl]()
      case _ => None[Isolette_Data_Model.TempWstatus_impl]()
    }
    return value
  }

  def get_lower_desired_tempWstatus() : Option[Isolette_Data_Model.TempWstatus_impl] = {
    val value : Option[Isolette_Data_Model.TempWstatus_impl] = Art.getValue(lower_desired_tempWstatus_Id) match {
      case Some(Isolette_Data_Model.TempWstatus_impl_Payload(v)) => Some(v)
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port lower_desired_tempWstatus.  Expecting 'Isolette_Data_Model.TempWstatus_impl_Payload' but received ${v}")
        None[Isolette_Data_Model.TempWstatus_impl]()
      case _ => None[Isolette_Data_Model.TempWstatus_impl]()
    }
    return value
  }

  def get_upper_desired_tempWstatus() : Option[Isolette_Data_Model.TempWstatus_impl] = {
    val value : Option[Isolette_Data_Model.TempWstatus_impl] = Art.getValue(upper_desired_tempWstatus_Id) match {
      case Some(Isolette_Data_Model.TempWstatus_impl_Payload(v)) => Some(v)
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port upper_desired_tempWstatus.  Expecting 'Isolette_Data_Model.TempWstatus_impl_Payload' but received ${v}")
        None[Isolette_Data_Model.TempWstatus_impl]()
      case _ => None[Isolette_Data_Model.TempWstatus_impl]()
    }
    return value
  }

  def get_regulator_mode() : Option[Isolette_Data_Model.Regulator_Mode.Type] = {
    val value : Option[Isolette_Data_Model.Regulator_Mode.Type] = Art.getValue(regulator_mode_Id) match {
      case Some(Isolette_Data_Model.Regulator_Mode_Payload(v)) => Some(v)
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port regulator_mode.  Expecting 'Isolette_Data_Model.Regulator_Mode_Payload' but received ${v}")
        None[Isolette_Data_Model.Regulator_Mode.Type]()
      case _ => None[Isolette_Data_Model.Regulator_Mode.Type]()
    }
    return value
  }
}

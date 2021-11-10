// #Sireum

package isolette__JVM.Isolette_Data_Model

import org.sireum._
import isolette__JVM._

// This file was auto-generated.  Do not edit

@enum object ValueStatus {
  "Valid"
  "Invalid"
}

object ValueStatus_Payload {
  def example(): ValueStatus_Payload = {
    return ValueStatus_Payload(Isolette_Data_Model.ValueStatus.byOrdinal(0).get)
  }
}

@datatype class ValueStatus_Payload(value: Isolette_Data_Model.ValueStatus.Type) extends art.DataContent

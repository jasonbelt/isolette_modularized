::#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF         #
if [ -z ${SIREUM_HOME} ]; then                      #
  echo "Please set SIREUM_HOME env var"             #
  exit -1                                           #
fi                                                  #
exec ${SIREUM_HOME}/bin/sireum slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
%SIREUM_HOME%\\bin\\sireum.bat slang run "%0" %*
exit /B %errorlevel%
::!#
// #Sireum

// Example Sireum Proyek build definitions -- the contents of this file will not be overwritten
//
// To install Sireum (Proyek and IVE) see https://github.com/sireum/kekinian#installing
//
// The following commands should be executed in the parent of the 'bin' directory.
//
// Command Line:
//   To run the demo from the command line using the default scheduler:
//     sireum proyek run . isolette__JVM.Demo
//
//   To see the available CLI options:
//     sireum proyek run . isolette__JVM.Demo -h
//
//   To run the example unit tests from the command line:
//     sireum proyek test .
//
//   To build an executable jar:
//     sireum proyek assemble --uber --main isolette__JVM.Demo .
//
// Sireum IVE:
//
//   If you did not have HAMR run Proyek IVE then first generate the IVE project:
//     sireum proyek ive .
//
//   Then in IVE select 'File > Open ...' and navigate to the parent of the
//   'bin' directory and click 'OK'.
//
//   To run the demo from within Sireum IVE:
//     Right click src/main/architecture/isolette__JVM/Demo.scala and choose "Run 'Demo'"
//
//   To run the unit test cases from within Sireum IVE:
//     Right click the src/test/bridge and choose "Run ScalaTests in bridge"
//
//   NOTE: A ClassNotFoundException may be raised the first time you try to
//         run the demo or unit tests.  If this occurs simply delete the directory
//         named 'target' and retry

import org.sireum._
import org.sireum.project.{JSON, Module, Project, ProjectUtil, Target}

def usage(): Unit = {
  println("Usage: [ json ]")
}

var isDot = T

Os.cliArgs match {
  case ISZ(string"json") => isDot = F
  case ISZ(string"-h") =>
    usage()
    Os.exit(0)
  case ISZ() =>
  case _ =>
    usage()
    Os.exit(-1)
}

val homeDir: Os.Path = Os.slashDir.up.canon

def slangJvmModule(id: String,
                   baseDir: Os.Path,
                   deps: ISZ[String],
                   jvmIvyDeps: ISZ[String],
                   jvmSources: ISZ[Os.Path],
                   jvmTestSources: ISZ[String]): Module = {
  return Module(
    id = id,
    basePath = baseDir.string,
    subPathOpt = None(),
    deps = deps,
    targets = ISZ(Target.Jvm),
    ivyDeps = jvmIvyDeps,
    resources = ISZ(),
    sources = jvmSources.map((m: Os.Path) => m.string),
    testSources = jvmTestSources,
    testResources = ISZ(),
    publishInfoOpt = None()
  )
}

// infrastructure
val art: Module = slangJvmModule(
  id = "art",
  baseDir = homeDir / "src" / "infrastructure" / "art",
  deps = ISZ(),
  jvmIvyDeps = ISZ("org.sireum.kekinian::library:"),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

/**************************************************
 * user - expect users to make changes to files
 *        in subdirectories
 **************************************************/

// user_common 
//   will contain data and potential 
//   read-only (ie. val/immutable) data structures


val data: Module = slangJvmModule(
  id = "data",
  baseDir = homeDir / "src" / "common" / "data",
  deps = ISZ(art.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)


val apis_heat_source_cpi: Module = slangJvmModule(
  id = "apis_heat_source_cpi",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "heat_source" / "cpi",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val apis_operator_interface_oip: Module = slangJvmModule(
  id = "apis_operator_interface_oip",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "operator_interface" / "oip",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val apis_temperature_sensor_cpi: Module = slangJvmModule(
  id = "apis_temperature_sensor_cpi",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "temperature_sensor" / "cpi",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val apis_thermostat_monitor_temperature_manage_alarm: Module = slangJvmModule(
  id = "apis_thermostat_monitor_temperature_manage_alarm",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "thermostat" / "monitor_temperature" / "manage_alarm",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val apis_thermostat_monitor_temperature_manage_monitor_interface: Module = slangJvmModule(
  id = "apis_thermostat_monitor_temperature_manage_monitor_interface",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "thermostat" / "monitor_temperature" / "manage_monitor_interface",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val apis_thermostat_monitor_temperature_manage_monitor_mode: Module = slangJvmModule(
  id = "apis_thermostat_monitor_temperature_manage_monitor_mode",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "thermostat" / "monitor_temperature" / "manage_monitor_mode",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val apis_thermostat_regulate_temperature_manage_heat_source: Module = slangJvmModule(
  id = "apis_thermostat_regulate_temperature_manage_heat_source",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "thermostat" / "regulate_temperature" / "manage_heat_source",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val apis_thermostat_regulate_temperature_manage_regulator_interface: Module = slangJvmModule(
  id = "apis_thermostat_regulate_temperature_manage_regulator_interface",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "thermostat" / "regulate_temperature" / "manage_regulator_interface",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val apis_thermostat_regulate_temperature_manage_regulator_mode: Module = slangJvmModule(
  id = "apis_thermostat_regulate_temperature_manage_regulator_mode",
  baseDir = homeDir / "src" / "infrastructure" / "apis" / "thermostat" / "regulate_temperature" / "manage_regulator_mode",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)






val library: Module = slangJvmModule(
  id = "library",
  baseDir = homeDir / "src" / "common" / "library",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

// processes 
//   group threads based on the process they belong to
//   as such threads could employ communicate via subprogram
//   and shared data

val components_heat_source_cpi: Module = slangJvmModule(
  id = "components_heat_source_cpi",
  baseDir = homeDir / "src" / "components" / "heat_source" / "cpi",
  deps = ISZ(apis_heat_source_cpi.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val components_operator_interface_oip: Module = slangJvmModule(
  id = "components_operator_interface_oip",
  baseDir = homeDir / "src" / "components" / "operator_interface" / "oip",
  deps = ISZ(apis_operator_interface_oip.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val components_temperature_sensor_cpi: Module = slangJvmModule(
  id = "components_temperature_sensor_cpi",
  baseDir = homeDir / "src" / "components" / "temperature_sensor" / "cpi",
  deps = ISZ(apis_temperature_sensor_cpi.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val components_thermostat_monitor_temperature_manage_alarm: Module = slangJvmModule(
  id = "components_thermostat_monitor_temperature_manage_alarm",
  baseDir = homeDir / "src" / "components" / "thermostat" / "monitor_temperature" / "manage_alarm",
  deps = ISZ(apis_thermostat_monitor_temperature_manage_alarm.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val components_thermostat_monitor_temperature_manage_monitor_interface: Module = slangJvmModule(
  id = "components_thermostat_monitor_temperature_manage_monitor_interface",
  baseDir = homeDir / "src" / "components" / "thermostat" / "monitor_temperature" / "manage_monitor_interface",
  deps = ISZ(apis_thermostat_monitor_temperature_manage_monitor_interface.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val components_thermostat_monitor_temperature_manage_monitor_mode: Module = slangJvmModule(
  id = "components_thermostat_monitor_temperature_manage_monitor_mode",
  baseDir = homeDir / "src" / "components" / "thermostat" / "monitor_temperature" / "manage_monitor_mode",
  deps = ISZ(apis_thermostat_monitor_temperature_manage_monitor_mode.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val components_thermostat_regulate_temperature_manage_heat_source: Module = slangJvmModule(
  id = "components_thermostat_regulate_temperature_manage_heat_source",
  baseDir = homeDir / "src" / "components" / "thermostat" / "regulate_temperature" / "manage_heat_source",
  deps = ISZ(apis_thermostat_regulate_temperature_manage_heat_source.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val components_thermostat_regulate_temperature_manage_regulator_interface: Module = slangJvmModule(
  id = "components_thermostat_regulate_temperature_manage_regulator_interface",
  baseDir = homeDir / "src" / "components" / "thermostat" / "regulate_temperature" / "manage_regulator_interface",
  deps = ISZ(apis_thermostat_regulate_temperature_manage_regulator_interface.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val components_thermostat_regulate_temperature_manage_regulator_mode: Module = slangJvmModule(
  id = "components_thermostat_regulate_temperature_manage_regulator_mode",
  baseDir = homeDir / "src" / "components" / "thermostat" / "regulate_temperature" / "manage_regulator_mode",
  deps = ISZ(apis_thermostat_regulate_temperature_manage_regulator_mode.id, library.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)







val bridges_heat_source_cpi: Module = slangJvmModule(
  id = "bridges_heat_source_cpi",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "heat_source" / "cpi",
  deps = ISZ(components_heat_source_cpi.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val bridges_operator_interface_oip: Module = slangJvmModule(
  id = "bridges_operator_interface_oip",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "operator_interface" / "oip",
  deps = ISZ(components_operator_interface_oip.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val bridges_temperature_sensor_cpi: Module = slangJvmModule(
  id = "bridges_temperature_sensor_cpi",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "temperature_sensor" / "cpi",
  deps = ISZ(components_temperature_sensor_cpi.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val bridges_thermostat_monitor_temperature_manage_alarm: Module = slangJvmModule(
  id = "bridges_thermostat_monitor_temperature_manage_alarm",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "thermostat" / "monitor_temperature" / "manage_alarm",
  deps = ISZ(components_thermostat_monitor_temperature_manage_alarm.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val bridges_thermostat_monitor_temperature_manage_monitor_interface: Module = slangJvmModule(
  id = "bridges_thermostat_monitor_temperature_manage_monitor_interface",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "thermostat" / "monitor_temperature" / "manage_monitor_interface",
  deps = ISZ(components_thermostat_monitor_temperature_manage_monitor_interface.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val bridges_thermostat_monitor_temperature_manage_monitor_mode: Module = slangJvmModule(
  id = "bridges_thermostat_monitor_temperature_manage_monitor_mode",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "thermostat" / "monitor_temperature" / "manage_monitor_mode",
  deps = ISZ(components_thermostat_monitor_temperature_manage_monitor_mode.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val bridges_thermostat_regulate_temperature_manage_heat_source: Module = slangJvmModule(
  id = "bridges_thermostat_regulate_temperature_manage_heat_source",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "thermostat" / "regulate_temperature" / "manage_heat_source",
  deps = ISZ(components_thermostat_regulate_temperature_manage_heat_source.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val bridges_thermostat_regulate_temperature_manage_regulator_interface: Module = slangJvmModule(
  id = "bridges_thermostat_regulate_temperature_manage_regulator_interface",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "thermostat" / "regulate_temperature" / "manage_regulator_interface",
  deps = ISZ(components_thermostat_regulate_temperature_manage_regulator_interface.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val bridges_thermostat_regulate_temperature_manage_regulator_mode: Module = slangJvmModule(
  id = "bridges_thermostat_regulate_temperature_manage_regulator_mode",
  baseDir = homeDir / "src" / "infrastructure" / "bridges" / "thermostat" / "regulate_temperature" / "manage_regulator_mode",
  deps = ISZ(components_thermostat_regulate_temperature_manage_regulator_mode.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val architecture: Module = slangJvmModule(
  id = "architecture",
  baseDir = homeDir / "src" / "infrastructure" / "architecture",
  deps = ISZ(
    bridges_heat_source_cpi.id,
    bridges_operator_interface_oip.id,
    bridges_temperature_sensor_cpi.id,

    bridges_thermostat_monitor_temperature_manage_alarm.id,
    bridges_thermostat_monitor_temperature_manage_monitor_interface.id,
    bridges_thermostat_monitor_temperature_manage_monitor_mode.id,

    bridges_thermostat_regulate_temperature_manage_heat_source.id,
    bridges_thermostat_regulate_temperature_manage_regulator_interface.id,
    bridges_thermostat_regulate_temperature_manage_regulator_mode.id
  ),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)

val test: Module = slangJvmModule(
  id = "test",
  baseDir = homeDir / "src" / "test",
  deps = ISZ(architecture.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(),
  jvmTestSources = ISZ("bridge", "util")

)
val demo: Module = slangJvmModule(
  id = "demo",
  baseDir = homeDir / "src" / "demo",
  deps = ISZ(architecture.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(Os.path("main")),
  jvmTestSources = ISZ()
)


val infrastructure_modules = ISZ(art,
  architecture,

  apis_heat_source_cpi,

  apis_operator_interface_oip,

  apis_temperature_sensor_cpi,

  apis_thermostat_monitor_temperature_manage_alarm,
  apis_thermostat_monitor_temperature_manage_monitor_interface,
  apis_thermostat_monitor_temperature_manage_monitor_mode,

  apis_thermostat_regulate_temperature_manage_heat_source,
  apis_thermostat_regulate_temperature_manage_regulator_interface,
  apis_thermostat_regulate_temperature_manage_regulator_mode,

  bridges_heat_source_cpi,

  bridges_operator_interface_oip,

  bridges_temperature_sensor_cpi,

  bridges_thermostat_monitor_temperature_manage_alarm,
  bridges_thermostat_monitor_temperature_manage_monitor_interface,
  bridges_thermostat_monitor_temperature_manage_monitor_mode,

  bridges_thermostat_regulate_temperature_manage_heat_source,
  bridges_thermostat_regulate_temperature_manage_regulator_interface,
  bridges_thermostat_regulate_temperature_manage_regulator_mode)

val user_modules = ISZ(data, library, demo, test,
  components_heat_source_cpi,

  components_operator_interface_oip,

  components_temperature_sensor_cpi,

  components_thermostat_monitor_temperature_manage_alarm,
  components_thermostat_monitor_temperature_manage_monitor_interface,
  components_thermostat_monitor_temperature_manage_monitor_mode,

  components_thermostat_regulate_temperature_manage_heat_source,
  components_thermostat_regulate_temperature_manage_regulator_interface,
  components_thermostat_regulate_temperature_manage_regulator_mode
)

var slangProject: Project = Project.empty

for(m <- infrastructure_modules ++ user_modules) {
  slangProject = slangProject + m
}

val project: Project = slangProject
//val prj: Project = inspectorProject()

if (isDot) {
  val projectDot = homeDir / "project.dot"
  projectDot.writeOver(ProjectUtil.toDot(project))
  println(s"Wrote $projectDot")
} else {
  println(JSON.fromProject(project, T))
}


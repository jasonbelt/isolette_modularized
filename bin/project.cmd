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
                   jvmSources: ISZ[String],
                   jvmTestSources: ISZ[String]): Module = {
  return Module(
    id = id,
    basePath = baseDir.string,
    subPathOpt = None(),
    deps = deps,
    targets = ISZ(Target.Jvm),
    ivyDeps = jvmIvyDeps,
    resources = ISZ(),
    sources = jvmSources,
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
  jvmSources = ISZ("main"),
  jvmTestSources = ISZ()
)

// user
val data: Module = slangJvmModule(
  id = "data",
  baseDir = homeDir / "src" / "common" / "data",
  deps = ISZ(art.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ("main"),
  jvmTestSources = ISZ()
)

// user
val library: Module = slangJvmModule(
  id = "library",
  baseDir = homeDir / "src" / "common" / "library",
  deps = ISZ(data.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ("main"),
  jvmTestSources = ISZ()
)

val processes: ISZ[ISZ[String]] = ISZ(
  ISZ("heat_source", "cpi"),
  ISZ("operator_interface", "oip"),
  ISZ("temperature_sensor", "cpi"),
  ISZ("thermostat", "monitor_temperature", "manage_alarm"),
  ISZ("thermostat", "monitor_temperature", "manage_monitor_interface"),
  ISZ("thermostat", "monitor_temperature", "manage_monitor_mode"),
  ISZ("thermostat", "regulate_temperature", "manage_heat_source"),
  ISZ("thermostat", "regulate_temperature", "manage_regulator_interface"),
  ISZ("thermostat", "regulate_temperature", "manage_regulator_mode"),
)

var modules: ISZ[Module] = ISZ()
var bridges: ISZ[Module] = ISZ()

for(p <- processes) {
  val id = ops.ISZOps(p).foldLeft((a: String, b: String) => s"${a}_${b}", "")

  // infrastructure
  val api = slangJvmModule(
    id = s"apis${id}",
    baseDir = (homeDir / "src" / "infrastructure" / "apis") /+ p,
    deps = ISZ(data.id),
    jvmIvyDeps = ISZ(),
    jvmSources = ISZ("main"),
    jvmTestSources = ISZ())

  // user
  val component: Module = slangJvmModule(
    id = s"components${id}",
    baseDir = (homeDir / "src" / "components") /+ p,
    deps = ISZ(api.id, library.id),
    jvmIvyDeps = ISZ(),
    jvmSources = ISZ("main"),
    jvmTestSources = ISZ())

  // infrastructure
  val bridge: Module = slangJvmModule(
    id = s"bridges${id}",
    baseDir = (homeDir / "src" / "infrastructure" / "bridges") /+ p,
    deps = ISZ(component.id),
    jvmIvyDeps = ISZ(),
    jvmSources = ISZ("main"),
    jvmTestSources = ISZ())

  bridges = bridges :+ bridge
  modules = modules :+ api :+ component :+ bridge
}

// infrastructure
val architecture: Module = slangJvmModule(
  id = "architecture",
  baseDir = homeDir / "src" / "infrastructure" / "architecture",
  deps = bridges.map((m: Module) => m.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ("main"),
  jvmTestSources = ISZ())

// user
val test: Module = slangJvmModule(
  id = "test",
  baseDir = homeDir / "src" / "test",
  deps = ISZ(architecture.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ(),
  jvmTestSources = ISZ("bridge", "util"))

//user
val demo: Module = slangJvmModule(
  id = "demo",
  baseDir = homeDir / "src" / "demo",
  deps = ISZ(architecture.id),
  jvmIvyDeps = ISZ(),
  jvmSources = ISZ("main"),
  jvmTestSources = ISZ())


var slangProject: Project = Project.empty
for(m <- ISZ(art, architecture, data, library, demo, test) ++ modules) {
  slangProject = slangProject + m
}

val project: Project = slangProject

if (isDot) {
  val projectDot = homeDir / "project.dot"
  projectDot.writeOver(ProjectUtil.toDot(project))
  println(s"Wrote $projectDot")
} else {
  println(JSON.fromProject(project, T))
}


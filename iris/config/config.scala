package iris.config
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.FileWriter
import scala.io.Source
import scala.util.matching.Regex
import iris.tui._


def listOfConfigs(): Array[String] = //I changed to array to remove the list conversion overhead
  val irisConfLoc = getHome()+"/.config/Iris/"
  val irisConfList = File(irisConfLoc).list()

  if irisConfList != null then
    irisConfList
  else Array()

def getConfig(filename: String): Iterator[String] =
  Source.fromFile(getHome()+"/.config/Iris/"+filename).getLines()

def getConfig_vector(filename: String): Vector[String] = getConfig(filename).toVector

def getConfig_list(filename: String): List[String] = getConfig(filename).toList

def getConfig_array(filename: String): Array[String] = getConfig(filename).toArray

def getConfig_string(filename: String): String =
  getConfig(filename)
    .map(x => x + '\n')
    .mkString

def getAllConfigs(): Vector[String] = //1 whole string per config to avoid matrices
  def groupConfigs(files: Array[String], cfgList: Vector[String] = Vector(), i: Int = 0): Vector[String] =
    if i >= files.length then
      cfgList
    else
      groupConfigs(files, cfgList :+ getConfig_string(files(i)), i+1)

  val files = listOfConfigs()
  groupConfigs(files)
  
def readConfig(filename: String, line: String): String = 
  val value = Regex (line + "[a-zA-Z0-9\\-\\_]+")
  
  val config = getConfig_string(filename)
  val search = value.findFirstIn(config).mkString
  search

def createConfig(confname: String) =
  val settings = String(s"themename=$confname\ndesktop_environment=\ngtktheme=\nlibadwaita=\nicontheme=\ncursortheme=\n" +
  "desktoptheme=\nkvantumtheme=\nqt5ct=\nflatpakgtk=")
  val configLocation = getHome()+"/.config/Iris/"
  

  if File(configLocation).exists() == false then //creates config location
    File(configLocation).mkdirs()
    val makeconf = FileWriter(File(configLocation+confname))
    makeconf.write(settings)
    makeconf.close()
  else if File(configLocation+confname).exists() == true then
    println("TODO, TUI error message config already exists, reasks question")
  else 
    val makeconf = FileWriter(File(configLocation+confname))
    makeconf.write(settings)
    makeconf.close()

def replaceLine(confname: String, line: String, newvalue: String) = //newvalue is the line like "gtktheme=" + the value you want
  val configLocation = getHome()+"/.config/Iris/"
  
  val currentconf = getConfig_string(confname)
  val currentconfList = getConfig_list(confname)
  
  val emptyreplace = currentconf.replaceAll(line, newvalue)
  val replace = currentconf.replaceAll(line+"[a-zA-Z0-9\\-\\_]+", newvalue)
  
  if currentconfList.contains(line) then 
    val emptywriter = FileWriter( File(configLocation+confname))
    emptywriter.write(emptyreplace)
    emptywriter.close()
  else
    val writer = FileWriter( File(configLocation+confname))
    writer.write(replace)
    writer.close()

def getHome() = System.getProperty("user.home")
//outdated, will have to rewrite some tui functions first
// def selectConfiguration(config: String): String =
//   if config != "" then
//     config
//   else 
//     readLoop_getListString(listOfConfigs())

  

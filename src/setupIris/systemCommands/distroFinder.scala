package setupIris.systemCommands

import scala.sys.process._
import scala.io.Source
import java.io.File
import lib.bananatui.*
import setupIris.systemCommands.sysUpdate
// import setupIris.systemCommands.*

def getHome() = System.getProperty("user.home")

def getPackageManager(): String =
  def findDistro(distro: String, l: Seq[String], i: Int = 0): Int = // l = list. i = iterator
    if i >= l.length then
      -1 //convenient way to tell its an "unknown" distro
    else if distro.contains(l(i)) then
      i
    else findDistro(distro, l, i+1)

  if !File("/etc/os-release").isFile() then
    ""
  else
    val distros = Vector("Debian GNU/Linux", "Linux Mint", "Pop!_OS", "Ubuntu", "Arch Linux", "EndeavourOS", "Fedora Linux", "openSUSE Tumbleweed")
    val pacmans = Vector("apt", "apt", "apt", "apt", "pacman", "pacman", "dnf", "zypper")
    val getos = 
      Source.fromFile("/etc/os-release")
      .getLines().toVector
      .filter(x => x.contains("NAME="))(0) //get the line that has your distro info as a string
      .replaceAll("NAME=", "")

    val i = findDistro(getos, distros)
    //distros and pacmans have the same size, and the positions are equivalent and related (if distros(3) is ubuntu then pacmans(3) is apt
    //with 2 vectors and 1 recursive function, we can know what package manager to use without using a huge if else or match statement, all we need to do is to add a new distro to distros and its equivalent package manager to pacmans, both in the same position
    if i != -1 then pacmans(i) //returns the package manager of the user's distro finally
    else "" //error handling, this means "unknown" distro
  
def aptWho() =
  val ubCheck = askPrompt("Are you on Ubuntu or a derivative (like Pop!_OS)?")
  if ubCheck then
    val ubVersion = askPrompt("Are you on Ubuntu LTS? Also known as 22.04, or in the Ubuntu derivatives based on 22.04?")
    if ubVersion then 
      kvantumUbuntuOld()
    else 
      pressToContinue("We will install the Papirus PPA to install a working version of Kvantum, a key component of Iris.\nYou can find the reason of this choice at IRIS-DOCS-LINK")
      kvantumUbuntu()
  else
    pressToContinue("We will install The Papirus Repository to install a working version of Kvantum, a key component of Iris.\nYou can find the reason of this choice at IRIS-DOCS-LINK")
    kvantumDebian()

def desktopList() = List("Budgie", "Cinnamon", "GNOME", "XFCE", "Other")

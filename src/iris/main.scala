package iris
import iris.interface._
import iris.theming._
import iris.interface._
import iris.config._
import lib.bananatui.*



@main def main(theme: String*) =
  if theme.isEmpty then
    mainMenu()
  else 
    loadConfig(theme.head)
  //if you read this you are a certified scalamancer

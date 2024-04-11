import mill._, scalalib._

object Iris extends RootModule with ScalaModule {
  def scalaVersion = "3.3.1"

  // Here you add the external libraries (dependencies) that you want to use. They are of the form "author::name:version"
  def ivyDeps = Agg(
    ivy"com.lihaoyi::os-lib:0.9.1",
  )

  // Choose a main class to use for `mill run` if there are multiple present
  def mainClass = Some("iris.main")
  val src = os.pwd / "src"

  // os.pwd refers to where this file is located
  // Add (or replace) source folders for the module to use
  def sources = T.sources(src)

  object IrisSetup extends ScalaModule {
    def scalaVersion = "3.3.1"

    // Choose a main class to use for `mill run` if there are multiple present
    def mainClass = Some("setupIris.main")
    // os.pwd refers to where this file is located

    // Add (or replace) source folders for the module to use
    def sources = T.sources(
       src / "setupIris",
    )
  }
}


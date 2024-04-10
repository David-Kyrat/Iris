import mill._, scalalib._

object foo extends RootModule with ScalaModule {
  def scalaVersion = "3.3.1"

  // You can have arbitrary numbers of dependencies
  def ivyDeps = Agg(
    ivy"com.lihaoyi::os-lib:0.9.1",
  )

  // Choose a main class to use for `.run` if there are multiple present
  def mainClass = Some("foo.Foo2")

  // Add (or replace) source folders for the module to use
  def sources = T.sources{
    Seq(os.pwd / "src")
  }

}

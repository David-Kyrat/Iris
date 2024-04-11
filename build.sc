import mill._, scalalib._

object Iris extends RootModule with ScalaModule {
    def scalaVersion = "3.3.1"

    // Choose a main class to use for `mill run` if there are multiple present
    def mainClass = Some("iris.main")

    // NOTE: os.pwd refers to where this file is located (Project Working Directory)
    val src = os.pwd / "src"

    // Add (or replace) source folders for the module to use
    // HINT: We tell mill that the IrisSetup module is composed of files in /src
    def sources = T.sources(src / "iris", src / "lib")

    object IrisSetup extends ScalaModule {
        def scalaVersion = Iris.scalaVersion

        // Choose a main class to use for `mill run` if there are multiple present
        def mainClass = Some("setupIris.main")

        // HINT: We tell mill that the IrisSetup module is composed of files in /src/setupIris and /src/lib
        def sources = T.sources(src / "setupIris", src / "lib")
    }
}

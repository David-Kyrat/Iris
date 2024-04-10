# TODO

Further steps to improve the project & the code quality.

## Must Have

- [ ] `Dependency` Section in the `README.md` with a list of dependencies.  
If not using a build tool that will install them automatically, add instructions on how to install them.

- [ ] Basic refactoring
    - Switch all the statements like `if <boolean_value> == true` to `if <boolean_value>`

## Should Have

- [ ] Tests ? (Although since the project is based on graphical stuff it might be hard to implement some relevant tests)

- [ ] Code formatting: Use the vscode extension, IntelliJ or you editor of choice's code formatter to format your code. (e.g `List("a","b"+getHome()+"c","d")` => `List("a", "b" + getHome() + "c", "d")`)
 
- [ ] Further refactoring:
    - [ ] Replacing bits of code that reimplement existing scala3 features or constructs
    - [ ] DRY (Don't Repeat Yourself principle) - refactor repeated code into functions
    - [ ] Use `Try` instead of catching exceptions. E.g. replace
    ```scala
    try
        <something>
    catch
        case e: Exception => -1
    ```
    by 
    ```scala 
    import scala.util.Try

    Try(<something>).getOrElse(-1)
    ``` 

    or

    ```scala
    import scala.util.Try

    Try { 
        <a block of code> 
    }.getOrElse(-1)
    ```

    - [ ] A `Constants.scala` file with an object `Constant` that will hold values like  
    `System.getProperty("user.home")` or `flatpakGtkOverride()` so that they are define once (unless overridden) and stay consistent everywhere.


## Nice to Have

- [ ] A build tool, to automatically install dependencies, compile, run and package the project. I wholeheartedly recommend using [mill](https://mill-build.com/mill/Intro_to_Mill.html) for this, [download mill](https://mill-build.com/mill/Installation_IDE_Support.html#_bootstrap_scripts).

- [ ] Putting all code in a `src` or `src/main/scala` directory, the latter is the standard directory structure for Scala projects.

- [ ] Using string interpolation, when it increase readability.

- [ ] Take advantage of scala's functional features to write cleaner code.  
    In scala everything is a function even values, this (and some syntactic sugar) allows us to write cleaner code. Specifically it allows to remove the `()` when calling getter functions which allows us to write code like this:
    - **Base code**: 
    ```scala
    def getHome() = System.getProperty("user.home")

    def flatpakGtkOverride() = List("sudo", "flatpak", "override", "--filesystem="+getHome()+"/.themes")
    ```
    - **Using getter as if they were actual fields**

    ```scala
    def home = System.getProperty("user.home")

    def flatpakGtkOverride = 
        List("sudo", "flatpak", "override", "--filesystem=" + home + "/.themes")
    ```

    - **Or with String interpolation**
    ```scala
    def home = System.getProperty("user.home")

    def flatpakGtkOverride = 
        List("sudo", "flatpak", "override", f"--filesystem=${home}/.themes")
    ```

    This can lead to ambiguity in some cases, especially with Unit functions that are not getter / accessors, i.e. that return nothing. To solve this problem we usually either add back the `()` for Unit functions, or add a type hint `: Unit` or do both. Example:

    ```scala
    // this can be called like a variable but it does not return a value
    def printHello = println("Hello")

    val x = printHello // seems normal but it's not
    ```
    Instead we do 

    ```scala
    def printHello() = println("Hello")
    
    // or better but not always strictly necessary
    def printHello(): Unit = println("Hello")

    val x = printHello() // everybody sees that writing this makes no sense
    ```



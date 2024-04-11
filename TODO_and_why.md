# TODO

Further steps to improve the project & the code quality.

## Must Have

<div style="color: blue" >

- [ ] **Package must match the directory structure.**  
    This is **extremely important** as it will tell the jvm / compiler  **where to look** for files, when you try to **import functions** (and it is also the whole point of packages). 
    ### Why?
    <details> 
    <summary>Click to expand</summary>

    - Putting `package iris.theming` on top of a file *whatever.scala*, tells scala:  
    *"Hey `whatever.scala` is in the directory `iris/theming`"*.  
    (e.g. putting `package iris.iris` on top of `iris/main.scala` tells scala that your main.scala is in `iris/iris` directory).  

    - If you have the file  `iris/systemCommands/theming.scala` then you **must** put  
    `package iris.systemCommands` at the top of `theming.scala`. If you want to change the package. Create a new directory, put theming.scala in it or rename the existing one.  
    
    - **Imports**:  
    Now this is probably the "why the points above are importants".  
    When you have (e.g. in `theming.scala`) `import iris.themeSelector._` at the top of the file. This tells scala.  
    *"I want to use everything that is in the package `iris.themeSelector`"*
    i.e. everything that is in the directory `iris/themeSelector` (which does not exist).  
    To import the functions from the file `iris/interface/themeSelector.scala` instead you should do import `iris.interface._`.  
    <br/> This will import everything `iris.interface`.  If you only want to import the things from the `themesSelector.scala` file you'll have to create an `object` and put the functions in it. e.g. 
    ```scala
    package iris.interface
    // your imports here

    object ThemeSelector {
        def askDesktop(): String = {...}
        def setDesktopTheme(filename: String, theme String) = {...}
    }
    ```
    you'll then be able to import the `askDesktop()` function like `import iris.interface.ThemeSelector.askDesktop` or `import iris.interface.ThemeSelector._` to import everything.

    However creating object like this, is *not mandatory* and you could just import everything from the `interface` package without any major problem.

     </details>

    

</div>

- [ ] Basic refactoring  
    Replace all the statements of the form `if <boolean_value> == true` to `if <boolean_value>`


## Should Have

- [ ] Putting all code in a `src` or `src/main/scala` directory, the latter is the standard directory structure for Scala projects.

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


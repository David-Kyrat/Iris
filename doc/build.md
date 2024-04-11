# Building Iris from source

Iris requires Scala 3 to build from source. You can use the scala compiler directly, or use scala-cli.
Most of these commands are in the project's Makefile, so if you have `Make`, you can run them.

# Compiling the project

To compile just iris (the root module)

```bash
mill compile
```

To compile iris-setup (module `SetupIris`)

```bash
mill SetupIris.compile
```

# Running the project

To run just iris (the root module)

```bash
mill run
```

To run iris-setup (module `SetupIris`)

```bash
mill SetupIris.run
```


# Building JARs

With mill, you can build it like this:

```bash
mill assembly && mill SetupIris.assembly
```

Having Scala in your system, you can then run it as if it was an executable:

```bash
./out/assembly.dest/out.jar
./out/SetupIris/assembly.dest/out.jar
```

or with java 

```bash
java -jar out/assembly.dest/out.jar
java -jar out/SetupIris/assembly.dest/out.jar
```
By default mill builds a "bootstrap assembly" jar or "bootstrap fat jar".  
This means that the jar will contain the Scala runtime and the dependencies and will work like an executable.  
A bootstrap works like an executable, in the sense that you can run it as if it was one. Other than that its a normal jar.

## Building a lightweight JAR

You can build a lightweight JAR with scala-cli:

```bash
make light_jar
```

These can only be run with Scala-CLI, as the makefile will call

```bash
scala-cli --power package -f src/iris src/lib --library -o iris.jar
scala-cli --power package -f src/setupIris src/lib --library -o setup-iris.jar
scala-cli --power package -f src/misc src/lib --library -o misc_flatpak.jar
```

This will create 3 jars, `iris.jar`, `setup-iris.jar` and `misc_flatpak.jar`.  

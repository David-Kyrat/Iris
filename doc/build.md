# Building Iris from source

Iris requires Scala 3 to build from source. You can use the scala compiler directly, or use scala-cli.
Most of these commands are in the project's Makefile, so if you have `Make`, you can run them.

# Compiling the project

To compile everything
```bash
make build
```
> This will execute `mill compile && mill SetupIris.compile`, (see below)

- To compile just iris (the root module)

```bash
mill compile
```

- To compile iris-setup (module `SetupIris`)

```bash
mill SetupIris.compile
```

# Running the project

- To run just iris (the root module)

```bash
make run
```

> This will call `mill run`.

- To run iris-setup (module `SetupIris`)

```bash
make run_setup
```

> This will call `mill SetupIris.run`

# Building JARs

You can build it like this:

```bash
make package
```
> This will run `mill assembly && mill SetupIris.assembly`

This will create 2 jars `out/assembly.dest/out.jar` and `out/SetupIris/assembly.dest/out.jar`. 

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

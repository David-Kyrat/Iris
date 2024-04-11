# Building Iris from source

Iris requires Scala 3 and [mill](https://github.com/com-lihaoyi/mill) (installation instruction [here](https://mill-build.com/mill/Installation_IDE_Support.html#_bootstrap_scripts), you can also install it with coursier `cs intall mill`) to build from source. 
Most of these commands are Makefile target that call mill under the hood for simplicity. 
Installing Make: `sudo <your_package_manager> install make` (unless you're on Arch, in that case I suppose you already have it.)

# Compiling the project

To compile everything
```bash
make build
```
> This will run `mill compile && mill SetupIris.compile`, (see below)

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

> This will run `mill run`.

- To run iris-setup (module `SetupIris`)

```bash
make run_setup
```

> This will run `mill SetupIris.run`

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

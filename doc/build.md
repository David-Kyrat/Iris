# Building Iris from source

Iris requires Scala 3 to build from source. You can use the scala compiler directly, or use scala-cli.
Most of these commands are in the project's Makefile, so if you have `Make`, you can run them.


# Building a lightweight JAR

You can build a lightweight JAR with this command:

```bash
make build
```

This builds Iris and the setup utility using the scala compiler. You can run them with scala: (watch out, these will rebuilt the JARs. So you don't need the step above)

```bash
make run_iris run_setup
```

If you prefer to use Scala-CLI instead, here's the command:

```bash
scala-cli --power package iris lib --library -o iris.jar
scala-cli --power package setup-iris lib --library -o setup-iris.jar
```

These can only be run with Scala-CLI.

# Building an assembly JAR

With Scala-CLI, you can build an assembly JAR, which contains all the runtime and dependencies and so it can be run directly with Java:

```bash
make iris_fatjar setup_fatjar
```

You can run Iris with Java:

```bash
java -jar iris.jar
java -jar setup-iris.jar
```

# Building bootstrap JARs

A bootstrap works like an executable, in the sense that you can run it as if it was one.

With Scala-CLI, you can build a bootstrap, like this:

```bash
make bootstrap_jar_nodep
```

Having Scala in your system, you can then run it as if it was an executable:

```bash
./iris.jar
./setup-iris.jar
```

If you want to include all dependencies in your bootstrap and only rely on Java, then you can build the following way:

```bash
make bootstrap_jar
```

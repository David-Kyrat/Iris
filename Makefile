DIR_IRIS=iris/*.scala iris/*/*.scala
DIR_SETUP=setup-iris/*.scala setup-iris/*/*.scala
DIR_LIB=lib/*/*.scala
DIR_MISC=misc/*.scala

SETUP_JAR=setup-iris.jar
IRIS_JAR=iris.jar
FLATPAK_JAR=misc-flatpak-override.jar



# run build jar. has `build_iris` as dependency => will run `build_iris` first
run_iris: build_iris
	scala $(IRIS_JAR)

# run misc_flatpak jar. has `build_misc_flatpak` as dependency => will run `build_misc_flatpak` first
run_misc_flatpak: build_misc_flatpak
	scala $(FLATPAK_JAR)

# run setup jar. has `build_setup` as dependency => will run `build_setup` first
run_setup: build_setup
	scala $(SETUP_JAR)


# build all
build: build_iris build_setup build_misc_flatpak

# build iris
build_iris:
	scalac $(DIR_IRIS) $(DIR_LIB) -d $(IRIS_JAR)

# build setup jar
build_setup:
	scalac $(DIR_SETUP) $(DIR_LIB) -d $(SETUP_JAR)

# (optional for flatpaks)
build_misc_flatpak:
	scalac $(DIR_MISC) -d $(FLATPAK_JAR)



# Building the light jar is just building each jar individually. i.e. calling the `build` target
light_jar: build


fatjar: iris_fatjar setup_iris_fatjar misc_flatpak_fatjar

iris_fatjar:
	scala-cli --power package iris lib --assembly --preamble=false --jvm 11 -f -o iris.jar

setup_fatjar:
	scala-cli --power package setup-iris lib --assembly --preamble=false --jvm 11 -f -o setup-iris-java.jar

misc_flatpak_fatjar:
	scala-cli --power package misc lib --assembly --preamble=false --jvm 11 -f -o misc-flatpak-override-java.jar


bootstrap_jar:
	scala-cli --power package iris lib --assembly -o iris.jar && scala-cli --power package setup-iris lib --assembly -o setup-iris.jar && scala-cli --power package misc lib --assembly -o setup-iris.jar

bootstrap_jar_nodep:
	scala-cli --power package iris lib -o iris.jar && scala-cli --power package setup-iris lib -o setup-iris.jar




clean:
	rm *.jar
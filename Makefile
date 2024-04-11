DIR_IRIS=src/iris/*.scala src/iris/*/*.scala
DIR_SETUP=src/setupIris/*.scala src/setupIris/*/*.scala
DIR_LIB=src/lib/*/*.scala
DIR_MISC=src/misc/*.scala

SETUP_JAR=.jar
IRIS_JAR=iris.jar
FLATPAK_JAR=misc-flatpak-override.jar

run: 
	mill run

# run setup jar. has `build_setup` as dependency => will run `build_setup` first
run_setup:
	mill SetupIris.run

# run misc_flatpak jar. has `build_misc_flatpak` as dependency => will run `build_misc_flatpak` first
run_misc_flatpak: build_misc_flatpak
	scala $(FLATPAK_JAR)


build:
	mill compile && mill SetupIris.compile

# (optional for flatpaks)
build_misc_flatpak:
	scalac $(DIR_MISC) $(DIR_LIB) -d $(FLATPAK_JAR)



package:
	mill assembly && mill SetupIris.assembly



clean:
	mill clean; rm *.jar

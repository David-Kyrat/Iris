SETUP_JAR=out/SetupIris/assembly.dest/out.jar
IRIS_JAR=out/assembly.dest/out.jar
FLATPAK_JAR=out/MiscFlatpak/assembly.dest/out.jar


run: 
	mill run

# run setup jar. has `build_setup` as dependency => will run `build_setup` first
run_setup:
	mill SetupIris.run

# run misc_flatpak jar. has `build_misc_flatpak` as dependency => will run `build_misc_flatpak` first
run_misc_flatpak: 
	mill MiscFlatpak.run


build:
	mill compile && mill SetupIris.compile

# (optional for flatpaks)
build_misc_flatpak:
	mill MiscFlatpak.compile


package:
	mill assembly && mill SetupIris.assembly && mill MiscFlatpak.assembly


# move jar in root directory to access them more easily
move_jar:
	-mv $(IRIS_JAR) iris.jar; mv $(SETUP_JAR) setupIris.jar; mv $(FLATPAK_JAR) miscFlatpak.jar


clean:
	mill clean; rm *.jar

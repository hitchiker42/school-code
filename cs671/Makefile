SOURCES := $(wildcard src/cs671/*.java src/cs671/*/*.java)
#SOURCES1=something
SOURCES2 := $(wildcard src/cs671/Test*.java)
JAVAC ?= javac
CLASSPATH ?=$PWD:$PWD/src/grader.jar
ifeq ($(JAVAC),gcj)
	override JAVAC_FLAGS += --main=cs671.GuesserTextUI -o guesser 
	DEBUG := -Wall
else
	override JAVAC_FLAGS += -sourcepath src  -d .
	DEBUG := -Xlint
endif
.PHONY: clean all 1 2 3 4
all:cs671
check:cs671 tests
debug: JAVAC_FLAGS += $(DEBUG)
debug:cs671
cs671: $(SOURCES)
	$(JAVAC) $(JAVAC_FLAGS) $^
tests: cs671 src/tests/*.java
	$(JAVAC) $(JAVAC_FLAGS) src/tests/*.java
html: $(SOURCES)
	javadoc @javadoc-options $^
clean:
	/bin/rm -rf cs671 html tests
doc: html
# SOURCES1 := src/cs671/Guesser.java \
#             src/cs671/HiLo.java \
#             src/cs671/Liar.java \
#             src/cs671/TextUI.java # + other files, if needed
# 1: $(SOURCES1)
#	$(JAVAC) $?
1: cs671
2: $(SOURCES2)
	$(JAVAC) $(JAVAC_FLAGS) $^

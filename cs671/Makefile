SOURCES := $(wildcard src/cs671/*.java src/cs671/*/*.java)
SOURCES1= src/cs671/Guesser.java src/cs671/GuesserTextUI.java\
	src/cs671/HiLo.java src/cs671/Liar.java
SOURCES2 := $(wildcard src/cs671/Test*.java)
SOURCES3 :=
SOURCES4 :=
JAVAC ?= javac
CLASSPATH ?=$(PWD):$(PWD)/grader.jar
ifeq ($(JAVAC),gcj)
	override JAVAC_FLAGS += --main=cs671.GuesserTextUI -o guesser 
	DEBUG := -Wall
else
	override JAVAC_FLAGS += -sourcepath src  -d . -cp $(CLASSPATH)
	DEBUG := -Xlint
endif
.PHONY: clean all 1 2 3 4
cs671: $(SOURCES)
	$(JAVAC) $(JAVAC_FLAGS) $^
all:cs671 tests html
check:cs671 tests
debug: JAVAC_FLAGS += $(DEBUG)
debug:cs671
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
1: JAVAC_FLAGS += $(DEBUG)
1: $(SOURCES1)
	$(JAVAC) $(JAVAC_FLAGS) $^
2: JAVAC_FLAGS += $(DEBUG)
2: $(SOURCES2)

3: JAVAC_FLAGS += $(DEBUG)
3: $(SOURCES3)
	$(JAVAC) $(JAVAC_FLAGS) $^
4: JAVAC_FLAGS += $(DEBUG)
4: $(SOURCES4)
	$(JAVAC) $(JAVAC_FLAGS) $^
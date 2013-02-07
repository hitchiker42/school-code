SOURCES := $(wildcard src/cs671/*.java src/cs671/*/*.java)
JAVAC ?= javac
ifeq ($(JAVAC),gcj)
	override JAVAC_FLAGS += --main=cs671.GuesserTextUI -o guesser 
	DEBUG := -Wall
else
	override JAVAC_FLAGS += -sourcepath src  -d .
	DEBUG := -Xlint
endif
.PHONY: clean all 1 2 3 4
all: cs671
# html tests
debug: JAVAC_FLAGS += $(DEBUG)
debug:cs671
cs671: $(SOURCES)
	$(JAVAC) $(JAVAC_FLAGS) $^
#@touch cs671
tests: cs671 src/tests/*.java
	$(JAVAC) $(JAVAC_FLAGS) src/tests/*.java
	@touch tests
html: $(SOURCES)
	javadoc @javadoc-options $^
#	@touch html
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

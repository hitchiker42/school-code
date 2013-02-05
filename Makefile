JAVAC := javac -sourcepath src -Xlint -d .
SOURCES := $(wildcard src/cs671/*.java src/cs671/*/*.java)
.PHONY: clean all 1 2 3 4

all: cs671 html tests

cs671: $(SOURCES)
	$(JAVAC) $?
	@touch cs671

tests: cs671 src/tests/*.java
	$(JAVAC) src/tests/*.java
	@touch tests

html: $(SOURCES)
	javadoc @javadoc-options $(SOURCES)
	@touch html

clean:
	/bin/rm -rf cs671 html tests

# SOURCES1 := src/cs671/Guesser.java \
#             src/cs671/HiLo.java \
#             src/cs671/Liar.java \
#             src/cs671/TextUI.java # + other files, if needed

# 1: $(SOURCES1)
# 	$(JAVAC) $?

1: cs671
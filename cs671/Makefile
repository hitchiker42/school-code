SOURCES := $(wildcard j*/*.java)
ONE := $(wildcard j1/*.java)
TWO := $(wildcard j2/*.java)
THREE := $(wildcard j3/*.java)
FOUR := $(wildcard j4/*.java)
JAVAC := javac
JAVAC_FLAGS := -sourcepath $(wildcard j*) -Xlint -d $(PWD)
.PHONY: clean all 1 2 3 4
all: cs671 html tests
doc: html

cs671: $(SOURCES)
	$(JAVAC) $(JAVAC_FLAGS) $?
	@touch cs671
tests: cs671 src/tests/*.java
	$(JAVAC) $(JAVAC_FLAGS) src/tests/*.java
	@touch tests
html: $(SOURCES)
	javadoc @javadoc-options $(SOURCES)
	@touch html
clean:
	/bin/rm -rf cs671 html tests
1:     $(ONE)
	$(JAVAC) $(JAVAC_FLAGS) $?
	@touch 1
2:     $(TWO)
	$(JAVAC) $(JAVAC_FLAGS) $?
	@touch 2
3:     $(THREE)
	$(JAVAC) $(JAVAC_FLAGS) $?
	@touch 3
4:     $(FOUR)
	$(JAVAC) $(JAVAC_FLAGS) $?
	@touch 4

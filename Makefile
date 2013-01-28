JAVAC := javac -sourcepath src -Xlint -d .
SOURCES := $(wildcard src/cs671/java/{1,2,3,4}/*.java)
ONE := $(wildcard src/cs671/java/1/*.java}
TWO := $(wildcard src/cs671/java/2/*.java}
THREE := $(wildcard src/cs671/java/3/*.java}
FOUR := $(wildcard src/cs671/java/4/*.java}
.PHONY: clean all 1 2 3 4
all: cs671 html tests
doc: html

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
1:     $(ONE)
	$(JAVAC) $? 
	@touch 1
2:     $(TWO)
	$(JAVAC) $? 
	@touch 2
3:     $(THREE)
	$(JAVAC) $? 
	@touch 3
4:     $(FOUR)
	$(JAVAC) $? 
	@touch 4

JFLAGS = -d .
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        src/com/node.java \
        src/com/fheap.java \
        src/hashtagcounter.java

default: classes
	@echo Finished compiling
	@echo Run using - "java hashtagcounter <input_file> [output_file]"

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

# Another makefile with macros
JAVASRC = Search.java
SOURCES = README makefile ${JAVASRC}
MAINCLASS = Search
CLASSES = Search.class
JARFILE	= Search
JARCLASSES = ${CLASSES}
SUBMIT	= submit cmps012b-pt.u12 pa2

all: ${JARFILE}

${JARFILE}: ${CLASSES}
	echo Main-class: ${MAINCLASS} > Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	rm Manifest
	chmod +x ${JARFILE}

${CLASSES}: ${JAVASRC}
	javac -Xlint ${JAVASRC}

clean:
	rm ${CLASSES} ${JARFILE}

submit: ${SOURCES}
	${SUBMIT} ${SOURCES}
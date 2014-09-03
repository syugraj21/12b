# Another makefile with macros
JAVASRC	= Extrema.java
SOURCES	= README makefile ${JAVASRC}
MAINCLASS= Extrema
CLASSES	= Extrema.class
JARFILE	= myExtrema
JARCLASSES= ${CLASSES}
SUBMIT	= submit cmps012b-pt.u12 lab1

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

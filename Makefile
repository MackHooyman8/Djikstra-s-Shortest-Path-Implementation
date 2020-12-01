run: compile
	java FrontEndInterface

compile: FrontEndInterface.class Project3Test.class

FrontEndInterface.class: FrontEndInterface.java BackEndMethods.class
	javac FrontEndInterface.java

BackEndMethods.class: BackEndMethods.java LoadDataObject.class
	javac BackEndMethods.java

LoadDataObject.class: LoadDataObject.java LocationObject.class Park.txt
	javac LoadDataObject.java

LocationObject.class: LocationObject.java
	javac LocationObject.java

CS400Graph.class: CS400Graph.java GraphADT.class
	javac CS400Graph.java

GraphADT.class: GraphADT.java
	javac GraphADT.java

test: Project3Test.class
	java -jar junit5.jar --class-path . --scan-classpath --details tree

Project3Test.class: junit5.jar Project3Test.java BackEndMethods.class
	javac -cp .:junit5.jar Project3Test.java 

junit5.jar:
	wget http://pages.cs.wisc.edu/~cs400/junit5.jar

clean:
	$(RM) *.class

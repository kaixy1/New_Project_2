all: Main.class Lexical_Analysis.class readfile.class  semantic_Analysis_1.class

Main.class: Main.java
	javac Main.java
Lexical_Analysis.class: Lexical_Analysis.java
	javac Lexical_Analysis.java
readfile.class: readfile.java
	javac readfile.java
semantic_Analysis_1.class: semantic_Analysis_1.java
	javac semantic_Analysis_1.java

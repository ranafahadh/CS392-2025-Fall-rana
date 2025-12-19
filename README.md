
MyFinalLib

MyFinalLib contains all supporting data structures and utility code required by the final project including lazy streams (LnStrm and LnStcn) functional lists (FnList) linear lists (LnList) tuples (FnTupl2) sorting utilities priority queues hash maps and tree-based data structures. These components were implemented or reused strictly according to course specifications and were used unchanged in the final solutions except where explicitly required by an assignment. it provides the foundational abstractions needed for stream processing, sorting, merging, and frequency counting, and all Final_00 - Final_05 programs were compiled and executed by including MyFinalLib on the classpath without modifying library interfaces or adding external dependencies.

Final_01
In Final_01 I built a word stream on top of the character stream from Final_00. Characters are grouped into words where a word is defined as a sequence of letters and apostrophes. All uppercase letters are converted to lowercase and each word is represented as an FnList<Character>. which produces a lazy stream of words without using Java string tokenization or built in parsers.
I ran the files in cmd whith these commands:
javac -cp ..\MyFinalLib Final_01.java
java  -cp .;..\MyFinalLib Final_01



Final_02
In Final_02 I computed word frequencies by first converting the word stream into an array then sorting the array lexicographically using the quicksort implementation from Assign06_03. After sorting consecutive equal words were grouped to form (word, count) pairs. These pairs were then sorted using mergesort from Assign05_01 ordering first by descending frequency and then lexicographically for ties. which demonstrates a sorting-based approach to frequency analysis.

I ran the files in cmd whith these commands:
javac -cp ..\MyFinalLib Assign05_01.java Assign06_03.java Final_02.java
java  -cp .;..\MyFinalLib Final_02



Final_03
In Final_03 I implemented word frequency counting using an open addressing hash map from Assign08_02. Each word from the word stream is inserted into the hash map, and the associated count is incremented for repeated words. The hash map contents are then converted into a list of (word, count) pairs, which is sorted using mergesort from Assign05_01 with the same ordering as in Final_02. which demonstrates a hash-based alternative to sorting-first frequency computation.

I ran the files in cmd whith these commands:
javac -cp ..\MyFinalLib Assign05_01.java Assign08_02.java Final_03.java
java  -cp .;..\MyFinalLib Final_03



Final_04
In Final_04 I computed word frequencies using a Randomized Binary Search Tree (RBST) based on my implementation from Quiz02_06, which I adapted into a generic associative map. Each word is inserted into the RBST updating its frequency count. The RBST is then traversed to produce a list of (word, count) pairs which is sorted using mergesort from Assign05_01. which highlights a tree-based approach to frequency analysis and contrasts with the hash-map approach in Final_03.
I ran the files in cmd whith these commands:
javac -cp ..\MyFinalLib Assign05_01.java Quiz02_06.java Final_04.java
java  -cp .;..\MyFinalLib Final_04





Final_05

In Final_05, I implemented an n-way merge for linear lists (LnList) using a priority queue (MyPQueueArray) from Assignment 9. This merge function was then used to implement a stable 5-way mergesort on linear lists without creating new list nodes. The main method includes performance testing by parity-sorting the list [0, 1, 2, ..., 999999] and reporting the runtime demonstrating both correctness and scalability.
I ran the files in cmd whith these commands:
javac -cp ..\MyFinalLib Final_05.java
java  -cp .;..\MyFinalLib Final_05





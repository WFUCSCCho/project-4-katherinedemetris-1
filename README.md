# Project 4 @ CSC 201 Fall 2024: Hash Tables

## Pledged Work Policy

This is a ___Pledged Work___ assignment.  This means that the work you submit for grading ___must___ be your work product.  
You may not submit the work of others outside of your team, or the modification of work of others outside of your team.
You are encouraged to talk with each other about general problems.  For example, you may talk to someone about "What does
it mean when the compiler says there is a semicolon missing on line 20", or "I can not get my assignment template to
download from GitHub, what did you do?".  However, you may not engage in "Could you send me a copy of your work so I can
see how to get started?".  You may get full and detailed assistance from me, the Teaching Assistant (TA), and the TAs in
the Computer Science Center.  If you have any question about the appropriateness of assistance, do not hesitate to
consult with me.

If I believe you have violated our ___Pledge Work___ agreement, I will pursue this matter through the college Honor Council.

## Overview

A hash table is a fundamental data structure that stores key-value pairs. It uses a hash function to compute an index
(hash) into an array of buckets or slots, from which the desired value can be found. The hash function is used to 
transform the key into the index of the array that will be used to store the value. Hash tables offer average case 
O(1) time complexity for lookups, insertions, and deletions, assuming that the hash function is well-designed and the
load factor is kept low.

In this project, you will 

1. Implement a hash table that uses separate chaining to handle collisions.
2. Perform the hash table insert, search, and delete operations using already-sorted, shuffled, and reversed datasets 
lists as input.
3. Time the insert, search, and delete operation performances for the different number of inputs.
4. Graph and analyze the performance of the hash table operations.
5. Document your dataset and results in a `README.md` file.

## Invocation and I/O Files:

The name of the program is `Proj4` ( provided with a `main` method in`Proj4.java` ).

You are encouraged to run and debug code in __IntelliJ IDEA__. Also, the program can be invoked from the command-line as:

```shell
java Proj4 {dataset-file} {number-of-lines}
```
## 1. **Implement Separate Chaining Hash Table**

I have enclosed one starter code:
1. `SeparateChainingHashTable.java`
2. `TestSeparateChainingHashTable.java`
3. `Proj4.java`

The `SeparateChainingHashTable.java` file contains an overall structure of that Separate Chaining Hash Table class, and 
you are expected to complete the code where it is indicated (Search for // FINISH ME). The 
`TestSeparateChainingHashTable.java` file must not be modified, and it will be used for testing your Separate Chaining 
Hash Table class. It does not check for every error, but I hope that it will be helpful for you. The Proj4.java file 
contains a starter code for you to implement the main driver of the program.

The program takes in two command line arguments: 1) the filename if your dataset and 2) the number of lines of your 
dataset to read.

## 2. **Perform Hash Table insert, search, and delete operations on Already-Sorted, Shuffled, and Reversed Lists**

You will read your dataset and store the data in an ArrayList. To sort and randomize your ArrayList, you will use the
`Collections.sort()`, `Collections.shuffle()`, and `Collections.sort(al, Collections.reverseOrder())` commands,
respectively.

## 3. **Time Sorting Algorithm Performance**

For the hash table insert, search, and delete operations, you will use `System.nanoTime()` to calculate
the time it takes to run them on already-sorted, shuffled, and reversed lists.

Your program will print out the number of lines evaluated and the times insert, search, and delete each of the dataset 
elements into the already-sorted, shuffled, and reversed lists to the screen in a human-readable format (i.e., nice to 
look at) and also separately to a file named `analysis.txt` in CSV format. Each time the program runs, it will append 
the timing results to `analysis.txt`. At the end of your run, your hash table should be completely empty.

## 4. **Graph and Analyze the Performance of the Sorting Algorithms**

Run your program several times for different number of lines of your dataset, N, by choosing different values of the
second command line argument. After several runs, your `analysis.txt` file will be filled with timing and comparison data.

Using your favorite graphing software (e.g., MS Excel or Google Sheets), plot the running time (in seconds) vs. N for 
each case. Take a screenshot of your graph and put them here by modifying this file, committing, and pushing
it to this repository.

Insertion, search, and deletion running time (already sorted):
<img width="602" alt="Screenshot 2024-12-03 at 8 36 13 AM" src="https://github.com/user-attachments/assets/e08f5e27-c64b-40d9-ad09-2733d90c7589">


Insertion, search, and deletion running time (shuffled): 
<img width="603" alt="Screenshot 2024-12-03 at 8 37 51 AM" src="https://github.com/user-attachments/assets/c2326699-00c0-4ed5-974f-4af139fe25a8">


Insertion, search, and deletion running time (reversed):
<img width="604" alt="Screenshot 2024-12-03 at 8 38 26 AM" src="https://github.com/user-attachments/assets/7537a625-37a9-4d3e-8054-f11622ecf59b">


## 5. **Document your Dataset and Results**
Document the source of your dataset and any modifications you made to it. Describe the results of your analysis and 
how it compares to the theoretical performance of the hash table operations.

Dataset Source: Melbourne Housing Market Dataset
* Contains comprehensive property data including suburbs, addresses, room numbers, prices, and other housing attributes
* Each line provides detailed real estate transaction information
* Perfect for hash table operation testing due to varied data types

Dataset Modifications ("None" if unchanged): None

Result Analysis: 
* After running my hash table implementation with different-sized chunks of data (from 100 up to 1500 lines), I found some really interesting patterns. When I tested it with sorted data, it was definitely slower - insert times jumped from 0.29ms with 100 lines all the way up to 4.89ms with 1300 lines. This makes sense because when data is all sorted, it's more likely to bunch up in the same spots in the hash table.
  
* The cool part was seeing how well it handled shuffled and reversed data. With shuffled data, everything stayed pretty quick - inserts only took between 0.06ms and 1.23ms no matter how many lines I threw at it. Reversed data was similar, with inserts taking between 0.04ms and 0.93ms. This shows that my hash table is doing its job well - when data is spread out randomly (like in real-world situations), it keeps things fast and efficient.

* What really stood out was how the implementation handled searching and deleting. Even though timings bounced around a bit (like searches taking anywhere from 0.02ms to 1.78ms), they never got too slow, even with larger amounts of data. This matches up with what we learned about hash tables in class - they should be O(1) on average when the data is distributed well, and that's exactly what I saw.
  
* The separate chaining I used for handling collisions seems to work great too. Even when I fed it sorted data (which could have been a worst-case scenario), it kept things from falling apart completely. This was cool to see in practice after learning about it theoretically.
  
* These results really show why hash tables are so useful in real-world applications - they stay fast even when you throw a lot of data at them, as long as you implement them properly. The only time you might need to worry is if your data is coming in in a very specific pattern, but even then, it's still manageable.

## Submission:

Your project will be developed and graded via GitHub. Your final "push" is your final submission, and it must occur
before it is due. On Canvas, enter the url to your Github repository. Your project will not be graded without it.

## Recommendations:

I ___strongly suggest___ that you carefully think through your strategy before just jumping into the code.  Once that
is working, start adding in new features individually.  A good place to start is building your class.

*In order to get full points of Commenting and Code Style, you need to add comments to every method and head comments
for each file (providing file description, author, date, and acknowledgement).

```
/∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*
∗ @file: filename.java
∗ @description: This program implements . . .
∗ @author: Your Name
∗ @date: December 5, 2024
∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗/
```

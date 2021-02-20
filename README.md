# Journal Submission System

This system is designed to simulate a journal submission system between 3 roles: 
	Author, Reviewer, and Editor
All new users are required to register, before using the system.

The general process is as follows:
*	An author upload's a journal PDF of their choosing to be reviewed. The author can also select or nominate up to 3 reviewers they would like to reviewer their journal.
*	An editor can assign 3 reviewers to an author, also being able to view and author's preference for reviewers. 
*	A reivewer, having been assigned an author can then dowload, and view their journal and provide feedback.
*	An editor can view reviewer feedback and assign a status to the author. Then an author can  upload revisions until their journal is rejected or accepted. 

The functionality requirnemnets implemented were referenced in lecture of SENG 300, Winter 2020
at the University of Calgary under the guidence of Professor Sohaib. This system was created
for the purpose of the final project submission.

## Installation

Open your favourite IDE that allows you to run programs on the console. Using Eclsipse or another of the like to run the program suffices.  Download the zip of this repository and extract it to a known folder. Import the folder into your IDE. Navigate through folder src, then in package main open up class MainGUI.java. Run this class on your IDE.

### Prerequisites

Have a working Java enviornment setup on the local machine. Java JDK 8 is recommeneded to run the
this project on. Type in java -version into the command line to view version 
(should start with "1.8. ..." )
Refer to https://java.com/en/download/manual.jsp to view instructions and be sure to install
the correct one for the operating system on the local machine.


## Running the tests

Through src, open the testing foler. There you will find 3 testing classes that test Author, Reviewer and Editor. To run, open the class in your IDE and hit run. On IDE such as eclispe, the results of the test will be visable through its interface.

## Authors

* **Vikram Bawa**
* **Josh Diwa**
* **Haris Muhammad**
* **Ayman Shahriar**
* **Vi Tsang**

## Year

Project started in Februrary 2020. 
Completed on April 13th, 2020.

## Acknowledgments

* Used basic coding practicies covered in courses CPSC 233 and SENG 300, taught at the U of C 
* Gained insighed from question and answer sites such as Stackoverflow.com, Mkyong.com, etc...
* Referenced https://docs.oracle.com/javase/tutorial/uiswing/ for Swing help

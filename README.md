# Insight Data Engineering Coding Challenge 2019

## Summary and Instructions

This challenge is to do the following: 

Imagine you are a data engineer working for an online pharmacy. You are asked to generate a list of all drugs, the total number of UNIQUE individuals who prescribed the medication, and the total drug cost, which must be listed in descending order based on the total drug cost and if there is a tie, drug name in ascending order.

## Inputs

itcont.txt - text file with pharmacy prescriber information.

## Code
* Programming Language: Java 1.8
* Drug.java - The class that saves drug name, number of prescribers and total drug cost
* Process_Line.java - This class behaves like data ingestor which mainly fetches the field of interest in each record and stores the data.
* Pharmacy_Counting - Main class, initializes objects, defines input/output directories and loads contents into "Process Line".

## Approach

My solution uses the following approach:

* Process the input file one line at a time. 
* Line are tokenized by comma. If the field is inside of " "- double quotes, it is considered as a token.
* Stores tokens into "Drug" object as drug name, drug prescribers and drug cost respectively.
* Each drug Object is stored in HashMap.
* Records are listed in descending order of total drug cost by java Comparator. If same total cost appears, records are listed in ascending order.


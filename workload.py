#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Nov 26 20:15:08 2024

@software{Baccianella_JSON_Repair_-_2024,
    author = {Baccianella, Stefano},
    month = aug,
    title = {{JSON Repair - A python module to repair invalid JSON, commonly used to parse the output of LLMs}},
    url = {https://github.com/mangiucugna/json_repair},
    version = {0.28.3},
    year = {2024}
}

@author: Christoph Krol
"""
# Automized Workload for creating TestClasses of JavaClasses using open source LLMs through API
import os
import generateTests as genTest
import generateJavaFiles as genFile

finishedClasses = 0


# Iterating through all Java Input Classes to Create TestClasses (Copy your Test Classes into the inputClasses folder)
for fileName in os.listdir("inputClasses"):
    print(f"Progress: {finishedClasses}/{len(os.listdir('inputClasses'))} classes" )
    print (f"Process for class {fileName} started")
    filePath = os.path.join("inputClasses", fileName) # Path = inputClasses/ClassName.java
    if os.path.isfile(filePath) and fileName.endswith(".java"):
        
        # create prompt for LLM
        prompt = genTest.createPrompt(fileName)
        
        try:
            
            # send request to LLM
            llm_response = genTest.makeRequest("codellama:7b", prompt)

            # retrieve the JSON-Testsuite
            json_testSuite = genTest.retrieveJsonTestSuite(llm_response, fileName)
            
            # generate JSON-File for the Test Suite
            genTest.generateJSONFile(json_testSuite)
        
            # waiting till JSON-File is created
            fileName = os.path.splitext(fileName)[0] + "Test" # retrieve file name without .java
            print(fileName)
            
            # generate Java-TestClass from that JSON  
            genFile.generateJavaTestClass(fileName)
            finishedClasses = finishedClasses + 1 
            print(f"Process for {fileName} succesfully finished")
        except Exception as e:
            if not os.path.exists("errors.txt"):
                with open("errors.txt", "a") as error_log:
                    error_log.write(f"File: {fileName} " + f"{type(e).__name__}-  {str(e)}\n")
                    print(f"ERROR while creating Tests for {fileName}")
            continue
                    
        
print("Workload finished")       


#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Nov 28 11:44:53 2024

@author: christoph
"""

import os
import json
import generateTests as genTest
import generateJavaFiles as genFile
import traceback



finishedProjects = 0

# create prompt by giving all relevant project classes 
def createPrompt(project_folder):
    source_code = ""
    path = f"inputProjects/{project_folder}"
    for java_class in os.listdir(path):
        file_path = os.path.join(path, java_class)
        if os.path.isfile(file_path) and java_class.endswith(".java"):
            
            with open(file_path, "r") as cf:
                source_code = source_code + cf.read() + "\n"
    
    prompt = json.dumps(source_code)
    #print(source_code) 
    return prompt

def generateJSONFile(jsonTestSuite, project_folder):

    print("Generating JSON-File")
    os.makedirs("jsonTests", exist_ok=True)
    os.makedirs(f"jsonTests/{project_folder}", exist_ok=True)
    try:
        fileName = jsonTestSuite["className"] + "Test"  
    
        
        with open(f"jsonTests/{project_folder}/" + fileName + ".json", "w") as file:
            json.dump(jsonTestSuite, file, indent=4)
            print("JSON-File created")
    except Exception as e:
        traceback.print_exc()
        if not os.path.exists("errors.txt"):
            with open("errors.txt", "a") as error_log:
                error_log.write(traceback + "/n")
                

def generateJavaTestClass(filename, project_folder):
    
    with open(f"jsonTests/{project_folder}/" + filename + ".json", "r") as inputFile:
        print(f"Reading {filename}.json ")
        jsonTestSuite = json.load(inputFile)
        testMethods = jsonTestSuite["testMethods"]
        javaSourceCode = "";
        for testMethod in testMethods:
            code = testMethod["code"]
            code = code.replace("\\n", "\n") # Convert \\n back to real newlines
            javaSourceCode += f"\n// Test Method: {testMethod['methodName']}\n"
            javaSourceCode += f"{code}\n\n"
        #print(javaSourceCode)
        print("Reading successful")
            
        # generate .java Files
        os.makedirs("javaClasses", exist_ok=True)
        os.makedirs(f"javaClasses/{project_folder}", exist_ok=True)
        try:
            with open(f"javaClasses/{project_folder}/" + filename + ".java", "w") as javaFile:
                print("Writing JavaClass")
                javaFile.write("import org.junit.jupiter.api.Test;\n\n")
                javaFile.write("import static org.junit.jupiter.api.Assertions.*;\n\n")
                javaFile.write("class " + filename + "{\n\n")
                javaFile.write(javaSourceCode)
                javaFile.write("\n}")
                print("JavaClass successfully created")
        except Exception as e:
            traceback.print_exc()
            


# iterating through all projects 
for project_folder in os.listdir("inputProjects"):
    print(f"Generating Tests for {project_folder}")
    project_prompt = createPrompt(project_folder)
    
    try:
        # send request to LLM
        llm_response = genTest.makeRequest("yi-coder:latest", project_prompt)

        # retrieve the JSON-Testsuite
        json_testSuite = genTest.retrieveJsonTestSuite(llm_response, project_folder)
            
        # generate JSON-File for the Test Suite
        generateJSONFile(json_testSuite, project_folder)
        
        # waiting till JSON-File is created
        file_name = project_folder + "Test" # create Name for Test Class based on folder name 
        print(file_name[2:])
            
        # generate Java-TestClass from that JSON  
        generateJavaTestClass(file_name[2:], project_folder)
        finishedProjects = finishedProjects + 1 
        print(f"Process for {file_name} succesfully finished")
    except Exception as e:
        if not os.path.exists("errors.txt"):
            with open("errors.txt", "a") as error_log:
                error_log.write(f"File: {file_name} " + f"{type(e).__name__}-  {str(e)}\n")
                print(f"ERROR while creating Tests for {file_name}")
                print(e.__name__)
        continue
                    
        
print("Workload finished")       

    
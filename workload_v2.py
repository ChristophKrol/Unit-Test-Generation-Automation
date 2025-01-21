#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Nov 30 10:49:23 2024

@author: christoph
"""
import json
import os


from typing import List
from pydantic import BaseModel, Field, Json
from openai import OpenAI

# Helpful sources
#
#
#


client = OpenAI(api_key=API_KEY)
class TestMethod(BaseModel):
    methodName: str
    code: str


class TestSuite(BaseModel):
    className: str
    testMethods: List[TestMethod]



system_prompt = """\
    Create Java JUnit 5 TestMethods for the provided Java Class {java_class}. Try to get a high code coverage and make robust tests against mutations. All conditional expressions must assume true and false values. Tests with Boundary Values are also mandatory. For numeric data, always use positive and negative values. Before you make use of a variable, make sure it is instantiated to not get any compiler errors. Also add a throws-Statement where a test method might throw an unhandled exception. Provide the results in JSON format, matching the TestSuite model:
- className: the name of {java_class} + Test
- testMethods: A list of all test methods for the class under testing
Make sure to annotate every TestMethod.code with @Test and include the signature of every method in the code 
    """

# read the Class Under Testing and return  it as a string for the prompt
def createPrompt(filename):
    with open("inputClasses/" + filename, "r") as cf:
        sourceCode = cf.read()
        sourceCodeEscaped = json.dumps(sourceCode)
        #print(sourceCodeEscaped)
        print("Prompt Created")
        return sourceCodeEscaped


def get_response(query: str, model: str):
    completion = client.beta.chat.completions.parse(
        model=model,
        messages=[
            {"role": "system", "content": system_prompt},
            {"role": "user", "content": query}
        ],
        response_format=TestSuite,
    )
    return completion.choices[0].message.parsed


def generateJSONFile(jsonTestSuite):
    import os
    print("Generating JSON-File")
    os.makedirs("jsonTests", exist_ok=True)
    test_suite = jsonTestSuite
    fileName = test_suite.className
    #fileName = jsonTestSuite["className"] + "Test"

    with open("jsonTests/" + fileName + ".json", "w") as file:
        json.dump(jsonTestSuite.model_dump_json(), file, indent=4)
        print("JSON-File created")


def generateJavaTestClass(response):

    javaSourceCode = ""
    for test_method in response.testMethods:
        code = test_method.code
        method_name = test_method.methodName
        javaSourceCode += f"\n// Test Method: {method_name}\n"
        javaSourceCode += f"{code}\n\n"
    print("Reading successful")
    # generate .java Files
    os.makedirs("javaClasses", exist_ok=True)
    with open("javaClasses/" + response.className + ".java", "w") as javaFile:
        print("Writing JavaClass")
        javaFile.write("import org.junit.jupiter.api.Test;\n\n")
        javaFile.write("import static org.junit.jupiter.api.Assertions.*;\n\n")
        javaFile.write("class " + response.className + "{\n\n")
        javaFile.write(javaSourceCode)
        javaFile.write("\n}")
        print("JavaClass successfully created")


finishedClasses = 0

# Iterating through all Java Input Classes to Create TestClasses (Copy your Test Classes into the inputClasses folder)
for fileName in os.listdir("inputClasses"):
    print(f"Progress: {finishedClasses}/{len(os.listdir('inputClasses'))} classes")
    print(f"Process for class {fileName} started")
    filePath = os.path.join("inputClasses", fileName)  # Path = inputClasses/ClassName.java
    if os.path.isfile(filePath) and fileName.endswith(".java"):

        # create prompt for LLM
        prompt = createPrompt(fileName)

        try:

            # send request to LLM
            llm_response = get_response(prompt, "gpt-4o-mini")

            # generate JSON-File for the Test Suite
            generateJSONFile(llm_response)

            # waiting till JSON-File is created
            fileName = os.path.splitext(fileName)[0] + "Test"  # retrieve file name without .java
            print(fileName)

            # generate Java-TestClass from that JSON
            generateJavaTestClass(llm_response
                                  )
            finishedClasses = finishedClasses + 1
            print(f"Process for {fileName} succesfully finished")
        except Exception as e:
            if not os.path.exists("errors.txt"):
                with open("errors.txt", "a") as error_log:
                    error_log.write(f"File: {fileName} " + f"{type(e).__name__}-  {str(e)}\n")
                    print(f"ERROR while creating Tests for {fileName}")
            continue

print("Workload finished")


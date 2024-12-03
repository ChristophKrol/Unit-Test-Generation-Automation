import json
import os
 


def generateJavaTestClass(filename):
    with open("jsonTests/" + filename + ".json", "r") as inputFile:
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
        with open("javaClasses/" + filename + ".java", "w") as javaFile:
            print("Writing JavaClass")
            javaFile.write("import org.junit.jupiter.api.Test;\n\n")
            javaFile.write("import static org.junit.jupiter.api.Assertions.*;\n\n")
            javaFile.write("class " + filename + "{\n\n")
            javaFile.write(javaSourceCode)
            javaFile.write("\n}")
            print("JavaClass successfully created")


#generateJavaTestClass("AVLTreeTest")



    
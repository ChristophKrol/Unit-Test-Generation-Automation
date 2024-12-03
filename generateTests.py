import requests
import json
import re
import os
import json_repair

URL = "http://localhost:11434/api/generate"
ERROR = "\033[31m"

failureCounter = 3
systemPrompt = "Generate Testmethods in JUnit 5 for me. I will provide you a JSON-template for your output. CLASSNAME is my placeholder for the class name under test. CLASSNAME must only include the name of the class, not the package of it. METHODNAME is the name of the test method that you generate. CODE ist the the whole source code of the test method, including the @Test-Annotation and the method signature. Make sure CODE is written JSON-friendly like in the example to avoid errors in further processing.\r\n{\r\n    \"className\": \"CLASSNAME\",\r\n    \"testMethods\": [\r\n        {\r\n             \"methodName\": \"METHODNAME\",\r\n             \"code\": \"CODE\"\r\n        }\r\n    ]\r\n}\r\nHere is one example for the following Code:\r\n\r\npublic class Max { \r\n  public static Item max (Item v[], int n) { \r\n    Item max = v[0];\r\n    for (int i = 1; i < n; i++) if (max.compara (v[i]) < 0) max = v[i];\r\n    return max;\r\n  }\r\n}\r\n\r\nThis would be an Output of you:\r\n\r\n{\r\n    \"className\": \"Max\",\r\n    \"testMethods\": [\r\n        {\r\n            \"methodName\": \"testMaxWithEmptyArray\",\r\n            \"code\": \"@Test \\\\nvoid testMaxWithEmptyArray(){\\\\n assertNull(Max.max(new Item[0], 0));\\\\n}\"\r\n        },\r\n        {\r\n            \"methodName\": \"testMaxWithOneElementArray\",\r\n            \"code\": \"@Test \\\\nvoid testMaxWithOneElementArray(){\\\\n assertEquals(item, Max.max(new Item[]{item}, 1));\\\\n}\"\r\n        },\r\n        {\r\n            \"methodName\": \"testMaxWithMultipleElementsArray\",\r\n            \"code\": \"@Test \\\\nvoid testMaxWithMultipleElementsArray(){\\\\n assertEquals(item3, Max.max(new Item[]{item1, item2, item3}, 3));\\\\n}\"\r\n        }\r\n    ]\r\n}\r\n\r\nYour Responses will be processed further, so it is crucial, that you create the class in that JSON-Format to extract the source code from the code attribute. Try to get a high code coverage and make robust tests against mutations. All conditional expressions must assume true and false values. Tests with Boundary Values are also mandatory. For numeric data, always use positive and negative values. Before you make use of a variable, make sure it is instantiated to not get any compiler errors. Also add a throws-Statement where a test method might throw an unhandled exception. Do not create TestClasses for interfaces and do not instantiate Objects of an interface type. Include all necessary imports. Ensure the response is strictly valid JSON, without any additional explanation, comments, or extra text.\r\nBefore sending it, double check the JSON format to ensure it is correct and complete."



# read the Class Under Testing and return  it as a string for the prompt
def createPrompt(filename):
    with open("inputClasses/" + filename, "r") as cf:
        sourceCode = cf.read()
        sourceCodeEscaped = json.dumps(sourceCode)
        #print(sourceCodeEscaped)
        print("Prompt Created")
        return sourceCodeEscaped
        

def makeRequest(modelName, sourceCode):

    # generate JSON Post-Request
    json_request = {
        "model": modelName,
        "prompt": sourceCode,
        "system": systemPrompt,
        "options": {
            "temperature": 0.2
        },
        "stream": False
    }
    #print(json_request)
    #print()
    print("Sending request")
    response = requests.post(URL, data=json.dumps(json_request)).json()
    #print(response)
    print("Response received")
    return response

def retrieveJsonTestSuite(response, test_name):

        # retrieve LLM-generated JSON
        raw_llm_response = response.get("response")
        print("Retrieving LLMs response")
        #print(raw_llm_response)
        
        # if LLM generates some unwanted text, look for substring where the jsojn begins
        jsonTestSuite = re.search(r'\{.*\}', raw_llm_response, re.DOTALL).group(0)
        
        # Fix escape issues in the JSON String for proper parsing
        #jsonTestSuite = fix_json_escape(jsonTestSuite)
        #print("JSON: \n" + jsonTestSuite)
        try:
            jsonTestSuite = json_repair.loads(jsonTestSuite)  # Parse into a dictionary
            print("JSON retrieved")
            print(jsonTestSuite)
        except json.JSONDecodeError as e :
            print(f"Error decoding the JSON response")
            print(jsonTestSuite + "\n")
            print(e)
            if not os.path.exists("errors.txt"):
                with open("errors.txt", "a") as error_log:
                    error_log.writelines(f"JSON-ERROR in {test_name}")
            return None
        #print(jsonTestSuite)
        
        return jsonTestSuite

def generateJSONFile(jsonTestSuite):
    import os
    print("Generating JSON-File")
    os.makedirs("jsonTests", exist_ok=True)
    fileName = jsonTestSuite["className"] + "Test"  
    
    with open("jsonTests/" + fileName + ".json", "w") as file:
        json.dump(jsonTestSuite, file, indent=4)
        print("JSON-File created")

#req = makeRequest("llama3:latest", createPrompt("AVLTree.java"))
#testSuite = retrieveJsonTestSuite(req)
#generateJSONFile(testSuite)
#createPrompt("AVLTree.java")

#print(systemPrompt)
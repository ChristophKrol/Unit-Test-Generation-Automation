#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Nov 30 10:49:23 2024

@author: christoph
"""
import os
import generateTests as genTest
from typing import List
from pydantic import BaseModel
from llama_index.llms.ollama import Ollama
from llama_index.core.program import LLMTextCompletionProgram

# Helpful sources
#
# https://docs.llamaindex.ai/en/stable/examples/customization/prompts/chat_prompts/
# https://medium.com/@haldankar.deven/cracking-the-code-getting-structured-output-from-ollama-15652c4613e1
#


class TestMethod(BaseModel):
    methodName: str
    code: str
    
class TestSuite(BaseModel):
    className: str
    testMethods: List[TestMethod]


llm = Ollama(model = "llama3:latest", request_timeout = 120.0)

system_prompt = """\
    Create Java JUnit 5 TestMethods for the provided Java Class {java_class}. Try to get a high code coverage and make robust tests against mutations. All conditional expressions must assume true and false values. Tests with Boundary Values are also mandatory. For numeric data, always use positive and negative values. Before you make use of a variable, make sure it is instantiated to not get any compiler errors. Also add a throws-Statement where a test method might throw an unhandled exception. Provide the results in JSON format, matching the TestSuite model:
- className: the name of {java_class}
- testMethods: A list of all test methods for the class under testing
    """
    
class_input_prompt = genTest.createPrompt("MyList.java")



program = LLMTextCompletionProgram.from_defaults(
    output_cls=TestSuite,
    prompt_template_str=system_prompt,
    verbose=True,
    llm=llm
)

output = program(java_class = class_input_prompt)

print(output.className)
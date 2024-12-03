#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Nov 28 17:22:43 2024

@author: christoph
"""

import generateJavaFiles as genFiles
import os 


for json_file in os.listdir("jsonTests"):
    fileName = os.path.splitext(json_file)[0]
    genFiles.generateJavaTestClass(fileName)
    
    
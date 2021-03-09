# SWE 262P Project: Milestone 5
Group Members: Marilyn Nguyen, Ashhad Shah

## What we did
Added an AsyncJSON class to the XML class. This class handles asynchronous calls to read JSON Objects from XML files using ExecutorService. It contains a method with the signature \
```public Future<JSONObject> submitTask(Reader reader) ``` (line 1157) \
This method creates a new ```ThreadTask``` object which implements ```Callable<JSONObject>```. This overrides a callable method that actually converts the XML file into a JSON Object.

## Test cases
The test cases are located in XMLTestMilestone5.java

The following cases were tested:

1. Tests if the asynchronous method works correctly on just one file
2. Tests if the asynchronous method works correctly on two files, one being a large file (740MB). Writes the result JSON objects to .json files located in the xmls folder.


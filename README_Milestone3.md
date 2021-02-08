# SWE 262P Project: Milestone 3 
Group Members: Marilyn Nguyen, Ashhad Shah

## What we did
**(Task 4)** Added an overloaded static method to the XML class with the signature \
```public static JSONObject toJSONObject(Reader reader, Function<String, String> keyTransformer) ``` (lines 1030-1046) \
This method returns a JSONObject with keys prefixed with the specified keyTransformer This method uses an overloaded parse method to parse through the XML file.
	
The overloaded parse method is a recursive method. \
	```private static boolean parse(XMLTokener x, JSONObject context, String name, XMLParserConfiguration config, Function<String, String> keyTransformer)``` (lines 685-862) \
Doing this in the library allows us to modify the JSONObject in place during parsing/reading the XML whereas if keys were transformed outside of the library, the original XML file would be transformed into a JSONObject, then that JSONObject would be parsed a second time to modify the keys. In other words, doing the performance speed is increased two-fold.

## Test cases
The test cases are located in XMLTestMilestone3.java

The following cases were tested:

1. **Task 4:** Tests too see if the correct JSONObject with prefixed keys is returned. The JSONObject is nested and contains JSONArray.
2. **Task 4:** Tests too see if the correct JSONObject with keys replaced with uppercase keys (i.e. ```book``` to ```BOOK``` is returned. The JSONObject is nested and contains JSONArray.
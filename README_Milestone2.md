# SWE 262P Project: Milestone 2 
Group Members: Marilyn Nguyen, Ashhad Shah

## What we did
1. **(Task 2)** Added an overloaded static method to the XML class with the signature \
```static JSONObject toJSONObject(Reader reader, JSONPointer path) ``` (lines 1030-1078) \
This method returns a JSONObject containing the sub-object pointed to by the JSONPointer. It throws a ```JSONPointerException``` if the path was not found. It throws a ```ClassCastException``` if the sub-object returned is not a JSONObject. This method uses an overloaded parse method to parse through the XML file.
	
	The overloaded parse method is a recursive method. \
	```private static boolean parse(XMLTokener x, JSONObject context, String name, XMLParserConfiguration config, JSONPointer ptr, Boolean found)``` (lines 452-671)\
	The JSONPointer passed goes deeper with each call. For example, for the path ```/catalog/books``` after catalog is found, the path passed through the next call is ```/books```. Once the path is empty, ```Boolean found``` is set to true so that the object returned is the sub-object.

	

2. **(Task 5)** Add an overloaded static method to the XML class with the signature \
```static JSONObject toJSONObject(Reader reader, JSONPointer path, JSONObject replacement) ``` (lines 1080-1128) \
This method returns a JSONObject with the sub-object pointed to by ```path``` with a new JSONObject ```replacement```.  It returns null if the key path does not exist.

## Test cases
The test cases are located in XMLTestMilestone2.java

The following cases were tested:

1. **Task 2:** Tests if the correct sub-object is returned given a valid XML and JSONPointer. This does not go through any JSONArrays.
2. **Task 2:** Tests if the correct sub-object is returned given a valid XML and JSONPointer. This goes through a JSONArray. 
3.  **Task 2:** Tests if a JSONPointerException is thrown given a valid XML and a JSONPointer whose key path does not exist.
4. **Task 2:** Tests if a ClassCastException is thrown given a valid XML and a JSONPointer whose key path points to a non-JSONObject.
5. **Task 5:** Tests if the correct JSONObject with replacements is returned. 
	* Replacing a JSONObject within a JSONObject. 
	* Given a valid XML, JSONPointer, and replacement JSONObject.
6. **Task 5:** Tests if the null is returned given a JSONPointer whose key path does not exist. 
	* Replacing a JSONObject within a JSONObject. 
	* Given a valid XML and replacement JSONObject.
7. **Task 5:** Tests if the correct JSONObject with replacements is returned. 
	* Replacing a JSONObject within a JSONArray. 
	* Given a valid XML, JSONPointer, and replacement JSONObject.
8. **Task 5:** Tests if the null is returned given a JSONPointer whose key path does not exist. 
	* Replacing a JSONObject within a JSONArray. 
	* Given a valid XML and replacement JSONObject.

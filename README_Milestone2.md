#SWE 262P Project: Milestone 2
Group Members: Marilyn Nguyen, Ashhad Shah

## What we did
1. **(Task 2)** Added an overloaded static method to the XML class with the signature
```static JSONObject toJSONObject(Reader reader, JSONPointer path) ```
This method returns a JSONObject containing the sub-object pointed to by the JSONPointer. It returns an empty JSONObject ```{}``` if the path was not found. This method uses an overloaded parse method to parse through the XML file.

	We were not able to handle parsing JSONArrays in the overloaded parse method as shown in the second test case. However, there is a section of commented out code in lines 830-849 of XML.java that coverts the entire XML file to a JSONObject that returns the correct output.
	
	The overloaded parse method is a recursive method. 
	```private static boolean parse(XMLTokener x, JSONObject context, String name, XMLParserConfiguration config, JSONPointer ptr, String parsedPath)```
	It keeps track of path currently being parsed, and sees if it matches the path of the JSONPointer provided. If so, it modifies a global variable ```subObj``` to store the JSONObject.

	

2. **(Task 5)** Add an overloaded static method to the XML class with the signature
```static JSONObject toJSONObject(Reader reader, JSONPointer path, JSONObject replacement) ```
This method returns a JSONObject with the sub-object pointed to by ```path``` with a new JSONObject ```replacement```.  It returns null if the key path does not exist.

##Test cases
The test cases are located in XMLTestMilestone2.java

The following cases were tested:

1. **Task 2:** Tests if the correct sub-object is returned given a valid XML and JSONPointer. This does not go through any JSONArrays.
2. **Task 2:** Tests if the correct sub-object is returned given a valid XML and JSONPointer. This goes through a JSONArray. (**Note:** this test failed.)
3. **Task 2:** Tests if an empty sub-object is returned given a valid XML and a JSONPointer whose key path does not exist.
4. **Task 5:** Tests if the correct JSONObject with replacements is returned. 
	* Replacing a JSONObject within a JSONObject. 
	* Given a valid XML, JSONPointer, and replacement JSONObject.
5. **Task 5:** Tests if the null is returned given a JSONPointer whose key path does not exist. 
	* Replacing a JSONObject within a JSONObject. 
	* Given a valid XML and replacement JSONObject.
6. **Task 5:** Tests if the correct JSONObject with replacements is returned. 
	* Replacing a JSONObject within a JSONArray. 
	* Given a valid XML, JSONPointer, and replacement JSONObject.
7. **Task 5:** Tests if the null is returned given a JSONPointer whose key path does not exist. 
	* Replacing a JSONObject within a JSONArray. 
	* Given a valid XML and replacement JSONObject.

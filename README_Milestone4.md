# SWE 262P Project: Milestone 4
Group Members: Marilyn Nguyen, Ashhad Shah

## What we did
Added an toStream() method to the JSONObject class with the signature \
```public Stream<JSONObject> toStream() ``` (lines 101-110) \
This method returns a stream of JSON objects within a given JSONObject. It utilizes a helper method to perform a depth first search on the JSONObject.

The helper method is a recursive method. \
```private void helper(String key, Object obj)``` (lines 112-151) \
Given that JSON is a hierarchical structure, this method goes down into nested elements before going across to other objects on the same level. If a JSONObject or JSONArray is encountered, the method is recursively called to go deeper into the object. This method adds the bottom-most JSONObjects to the stream.

## Test cases
The test cases are located in JSONObjectTestMilestone4.java

The following cases were tested:

1. Tests chaining the ```toStream()``` method with a ```.forEach()``` function.
2. Tests chaining the ```toStream()``` method with a ```.map()``` function.
3. Tests chaining the ```toStream()``` method with a ```.filter()``` function.


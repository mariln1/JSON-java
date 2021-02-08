package org.json.junit;

/*
Copyright (c) 2020 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.json.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


/**
 * Tests for JSON-Java XML.java
 * Tests Milestone 2 methods
 * The following tests use the absolute path for books.xml which is located in the 'xmls' folder within the 'test' folder
 */
public class XMLTestMilestone2 {

    /**
     * Tests if toJSONObject returns the correct sub-object given a valid XML and JSONPointer
     * Not going through any JSONArrays
     */
    @Test
    public void testTask2Valid() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONPointer ptr = new JSONPointer("/catalog");
        String expectedStr ="{\"book\": [\n" +
                "  {\n" +
                "    \"author\": \"Gambardella, Matthew\",\n" +
                "    \"price\": 44.95,\n" +
                "    \"genre\": \"Computer\",\n" +
                "    \"description\": \"An in-depth look at creating applications \\n      with XML.\",\n" +
                "    \"id\": \"bk101\",\n" +
                "    \"title\": \"XML Developer's Guide\",\n" +
                "    \"publish_date\": \"2000-10-01\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Ralls, Kim\",\n" +
                "    \"price\": 5.95,\n" +
                "    \"genre\": \"Fantasy\",\n" +
                "    \"description\": \"A former architect battles corporate zombies, \\n      an evil sorceress, and her own childhood to become queen \\n      of the world.\",\n" +
                "    \"id\": \"bk102\",\n" +
                "    \"title\": \"Midnight Rain\",\n" +
                "    \"publish_date\": \"2000-12-16\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Corets, Eva\",\n" +
                "    \"price\": 5.95,\n" +
                "    \"genre\": \"Fantasy\",\n" +
                "    \"description\": \"After the collapse of a nanotechnology \\n      society in England, the young survivors lay the \\n      foundation for a new society.\",\n" +
                "    \"id\": \"bk103\",\n" +
                "    \"title\": \"Maeve Ascendant\",\n" +
                "    \"publish_date\": \"2000-11-17\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Corets, Eva\",\n" +
                "    \"price\": 5.95,\n" +
                "    \"genre\": \"Fantasy\",\n" +
                "    \"description\": \"In post-apocalypse England, the mysterious \\n      agent known only as Oberon helps to create a new life \\n      for the inhabitants of London. Sequel to Maeve \\n      Ascendant.\",\n" +
                "    \"id\": \"bk104\",\n" +
                "    \"title\": \"Oberon's Legacy\",\n" +
                "    \"publish_date\": \"2001-03-10\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Corets, Eva\",\n" +
                "    \"price\": 5.95,\n" +
                "    \"genre\": \"Fantasy\",\n" +
                "    \"description\": \"The two daughters of Maeve, half-sisters, \\n      battle one another for control of England. Sequel to \\n      Oberon's Legacy.\",\n" +
                "    \"id\": \"bk105\",\n" +
                "    \"title\": \"The Sundered Grail\",\n" +
                "    \"publish_date\": \"2001-09-10\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Randall, Cynthia\",\n" +
                "    \"price\": 4.95,\n" +
                "    \"genre\": \"Romance\",\n" +
                "    \"description\": \"When Carla meets Paul at an ornithology \\n      conference, tempers fly as feathers get ruffled.\",\n" +
                "    \"id\": \"bk106\",\n" +
                "    \"title\": \"Lover Birds\",\n" +
                "    \"publish_date\": \"2000-09-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Thurman, Paula\",\n" +
                "    \"price\": 4.95,\n" +
                "    \"genre\": \"Romance\",\n" +
                "    \"description\": \"A deep sea diver finds true love twenty \\n      thousand leagues beneath the sea.\",\n" +
                "    \"id\": \"bk107\",\n" +
                "    \"title\": \"Splish Splash\",\n" +
                "    \"publish_date\": \"2000-11-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Knorr, Stefan\",\n" +
                "    \"price\": 4.95,\n" +
                "    \"genre\": \"Horror\",\n" +
                "    \"description\": \"An anthology of horror stories about roaches,\\n      centipedes, scorpions  and other insects.\",\n" +
                "    \"id\": \"bk108\",\n" +
                "    \"title\": \"Creepy Crawlies\",\n" +
                "    \"publish_date\": \"2000-12-06\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Kress, Peter\",\n" +
                "    \"price\": 6.95,\n" +
                "    \"genre\": \"Science Fiction\",\n" +
                "    \"description\": \"After an inadvertant trip through a Heisenberg\\n      Uncertainty Device, James Salway discovers the problems \\n      of being quantum.\",\n" +
                "    \"id\": \"bk109\",\n" +
                "    \"title\": \"Paradox Lost\",\n" +
                "    \"publish_date\": \"2000-11-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"O'Brien, Tim\",\n" +
                "    \"price\": 36.95,\n" +
                "    \"genre\": \"Computer\",\n" +
                "    \"description\": \"Microsoft's .NET initiative is explored in \\n      detail in this deep programmer's reference.\",\n" +
                "    \"id\": \"bk110\",\n" +
                "    \"title\": \"Microsoft .NET: The Programming Bible\",\n" +
                "    \"publish_date\": \"2000-12-09\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"O'Brien, Tim\",\n" +
                "    \"price\": 36.95,\n" +
                "    \"genre\": \"Computer\",\n" +
                "    \"description\": \"The Microsoft MSXML3 parser is covered in \\n      detail, with attention to XML DOM interfaces, XSLT processing, \\n      SAX and more.\",\n" +
                "    \"id\": \"bk111\",\n" +
                "    \"title\": \"MSXML3: A Comprehensive Guide\",\n" +
                "    \"publish_date\": \"2000-12-01\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Galos, Mike\",\n" +
                "    \"price\": 49.95,\n" +
                "    \"genre\": \"Computer\",\n" +
                "    \"description\": \"Microsoft Visual Studio 7 is explored in depth,\\n      looking at how Visual Basic, Visual C++, C#, and ASP+ are \\n      integrated into a comprehensive development \\n      environment.\",\n" +
                "    \"id\": \"bk112\",\n" +
                "    \"title\": \"Visual Studio 7: A Comprehensive Guide\",\n" +
                "    \"publish_date\": \"2001-04-16\"\n" +
                "  }\n" +
                "]}";

        // WHEN
        JSONObject jsonObject = XML.toJSONObject(reader, ptr);

        //THEN
        Assert.assertEquals(expectedStr, jsonObject.toString(2));
        //Util.compareActualVsExpectedJsonObjects(jsonObject,expectedJsonObject);
    }

    /**
     * Tests if toJSONObject returns the correct sub-object given a valid XML and JSONPointer
     * Going through a JSONArray
     */
    @Test
    public void testTask2ValidArr() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONPointer ptr = new JSONPointer("/catalog/book/0");
        String expectedStr =
                "{\n" +
                        "  \"author\": \"Gambardella, Matthew\",\n" +
                        "  \"price\": 44.95,\n" +
                        "  \"genre\": \"Computer\",\n" +
                        "  \"description\": \"An in-depth look at creating applications \\n      with XML.\",\n" +
                        "  \"id\": \"bk101\",\n" +
                        "  \"title\": \"XML Developer's Guide\",\n" +
                        "  \"publish_date\": \"2000-10-01\"\n" +
                        "}";

        // WHEN
        JSONObject jsonObject = XML.toJSONObject(reader, ptr);

        //THEN
        Assert.assertEquals(expectedStr, jsonObject.toString(2));
        //Util.compareActualVsExpectedJsonObjects(jsonObject,expectedJsonObject);
    }

    /**
     * Tests if toJSONObject throws JSONPointerError given a valid XML and valid JSONPointer whose key path does not exist
     */
    @Test
    public void testTask2InvalidPath() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONPointer ptr = new JSONPointer("/catalog/book/poo");

        try {
            // WHEN
            JSONObject jsonObject = XML.toJSONObject(reader, ptr);

            // THEN
            fail( "My method didn't throw when I expected it to" );
        } catch (JSONPointerException expectedException) {
        }
    }

    /**
     * Tests if toJSONObject throws a ClassCastException when the sub object is not a JSONObject
     * given a valid XML and valid JSONPointer
     */
    @Test
    public void testTask2InvalidObj() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONPointer ptr = new JSONPointer("/catalog/book/0/author");

        try {
            // WHEN
            JSONObject jsonObject = XML.toJSONObject(reader, ptr);

            // THEN
            fail( "My method didn't throw when I expected it to" );
        } catch (ClassCastException expectedException) {
        }
    }

    /**
     * Tests if toJSONObject returns the correct JSONObject with replacements.
     * Replacing a JSONObject within a JSONObject
     * Given a valid XML, JSONPointer, and replacement JSONObject
     */
    @Test
    public void testTask5ValidJSONObj() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONObject replacement = new JSONObject("{\"Replacement key\":\"Replacement value\"}");
        String expectedStr = "{\"catalog\":{\"Replacement key\":\"Replacement value\"}}";
        JSONPointer ptr = new JSONPointer("/catalog/book");

        // WHEN
        JSONObject jsonObject = XML.toJSONObject(reader, ptr, replacement);

        // THEN
        assertEquals(expectedStr, jsonObject.toString());
        //Util.compareActualVsExpectedJsonObjects(jsonObject,expectedJsonObject);
    }
    /**
     * Tests if toJSONObject returns null given a JSONPointer whose key path does not exist.
     * Replacing a JSONObject within a JSONObject
     * Given a valid XML and replacement JSONObject
     */
    @Test
    public void testTask5InvalidJSONObj() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONObject replacement = new JSONObject("{\"Replacement key\":\"Replacement value\"}");
        String expectedStr = null;
        JSONPointer ptr = new JSONPointer("/poop");

        // WHEN
        JSONObject jsonObject = XML.toJSONObject(reader, ptr, replacement);

        // then
        assertEquals(expectedStr, jsonObject);
    }
    /**
     * Tests if toJSONObject returns the correct JSONObject with replacements.
     * Replacing a JSONObject within a JSONArray
     * Given a valid XML, JSONPointer, and replacement JSONObject
     */
    @Test
    public void testTask5ValidJSONArr() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONObject replacement = new JSONObject("{\"Replacement key\":\"Replacement value\"}");
        String expectedStr = "{\"catalog\": {\"book\": [\n" +
                "  {\"Replacement key\": \"Replacement value\"},\n" +
                "  {\n" +
                "    \"author\": \"Corets, Eva\",\n" +
                "    \"price\": 5.95,\n" +
                "    \"genre\": \"Fantasy\",\n" +
                "    \"description\": \"After the collapse of a nanotechnology \\n      society in England, the young survivors lay the \\n      foundation for a new society.\",\n" +
                "    \"id\": \"bk103\",\n" +
                "    \"title\": \"Maeve Ascendant\",\n" +
                "    \"publish_date\": \"2000-11-17\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Corets, Eva\",\n" +
                "    \"price\": 5.95,\n" +
                "    \"genre\": \"Fantasy\",\n" +
                "    \"description\": \"In post-apocalypse England, the mysterious \\n      agent known only as Oberon helps to create a new life \\n      for the inhabitants of London. Sequel to Maeve \\n      Ascendant.\",\n" +
                "    \"id\": \"bk104\",\n" +
                "    \"title\": \"Oberon's Legacy\",\n" +
                "    \"publish_date\": \"2001-03-10\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Corets, Eva\",\n" +
                "    \"price\": 5.95,\n" +
                "    \"genre\": \"Fantasy\",\n" +
                "    \"description\": \"The two daughters of Maeve, half-sisters, \\n      battle one another for control of England. Sequel to \\n      Oberon's Legacy.\",\n" +
                "    \"id\": \"bk105\",\n" +
                "    \"title\": \"The Sundered Grail\",\n" +
                "    \"publish_date\": \"2001-09-10\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Randall, Cynthia\",\n" +
                "    \"price\": 4.95,\n" +
                "    \"genre\": \"Romance\",\n" +
                "    \"description\": \"When Carla meets Paul at an ornithology \\n      conference, tempers fly as feathers get ruffled.\",\n" +
                "    \"id\": \"bk106\",\n" +
                "    \"title\": \"Lover Birds\",\n" +
                "    \"publish_date\": \"2000-09-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Thurman, Paula\",\n" +
                "    \"price\": 4.95,\n" +
                "    \"genre\": \"Romance\",\n" +
                "    \"description\": \"A deep sea diver finds true love twenty \\n      thousand leagues beneath the sea.\",\n" +
                "    \"id\": \"bk107\",\n" +
                "    \"title\": \"Splish Splash\",\n" +
                "    \"publish_date\": \"2000-11-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Knorr, Stefan\",\n" +
                "    \"price\": 4.95,\n" +
                "    \"genre\": \"Horror\",\n" +
                "    \"description\": \"An anthology of horror stories about roaches,\\n      centipedes, scorpions  and other insects.\",\n" +
                "    \"id\": \"bk108\",\n" +
                "    \"title\": \"Creepy Crawlies\",\n" +
                "    \"publish_date\": \"2000-12-06\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Kress, Peter\",\n" +
                "    \"price\": 6.95,\n" +
                "    \"genre\": \"Science Fiction\",\n" +
                "    \"description\": \"After an inadvertant trip through a Heisenberg\\n      Uncertainty Device, James Salway discovers the problems \\n      of being quantum.\",\n" +
                "    \"id\": \"bk109\",\n" +
                "    \"title\": \"Paradox Lost\",\n" +
                "    \"publish_date\": \"2000-11-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"O'Brien, Tim\",\n" +
                "    \"price\": 36.95,\n" +
                "    \"genre\": \"Computer\",\n" +
                "    \"description\": \"Microsoft's .NET initiative is explored in \\n      detail in this deep programmer's reference.\",\n" +
                "    \"id\": \"bk110\",\n" +
                "    \"title\": \"Microsoft .NET: The Programming Bible\",\n" +
                "    \"publish_date\": \"2000-12-09\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"O'Brien, Tim\",\n" +
                "    \"price\": 36.95,\n" +
                "    \"genre\": \"Computer\",\n" +
                "    \"description\": \"The Microsoft MSXML3 parser is covered in \\n      detail, with attention to XML DOM interfaces, XSLT processing, \\n      SAX and more.\",\n" +
                "    \"id\": \"bk111\",\n" +
                "    \"title\": \"MSXML3: A Comprehensive Guide\",\n" +
                "    \"publish_date\": \"2000-12-01\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"Galos, Mike\",\n" +
                "    \"price\": 49.95,\n" +
                "    \"genre\": \"Computer\",\n" +
                "    \"description\": \"Microsoft Visual Studio 7 is explored in depth,\\n      looking at how Visual Basic, Visual C++, C#, and ASP+ are \\n      integrated into a comprehensive development \\n      environment.\",\n" +
                "    \"id\": \"bk112\",\n" +
                "    \"title\": \"Visual Studio 7: A Comprehensive Guide\",\n" +
                "    \"publish_date\": \"2001-04-16\"\n" +
                "  }\n" +
                "]}}";
        JSONPointer ptr = new JSONPointer("/catalog/book/0");

        // WHEN
        JSONObject jsonObject = XML.toJSONObject(reader, ptr, replacement);

        // THEN
        assertEquals(expectedStr, jsonObject.toString(2));
    }

    /**
     * Tests if toJSONObject returns null given a JSONPointer whose key path does not exist.
     * Replacing a JSONObject within a Array
     * Given a valid XML and replacement JSONObject
     */
    @Test
    public void testTask5InvalidJSONArr() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONObject replacement = new JSONObject("{\"Replacement key\":\"Replacement value\"}");
        String expectedStr = null;
        JSONPointer ptr = new JSONPointer("/catalog/book/100");

        // WHEN
        JSONObject jsonObject = XML.toJSONObject(reader, ptr, replacement);

        // then
        assertEquals(expectedStr, jsonObject);
    }
}

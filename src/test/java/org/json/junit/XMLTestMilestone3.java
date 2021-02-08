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
public class XMLTestMilestone3 {

    /**
     * Tests if key prefix is added to each key in a nested JSONObject, containing JSONArray
     * Not going through any JSONArrays
     */
    @Test
    public void testTask4Valid() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        String keyPrefix = "swe262_";
        String expectedStr ="{\"swe262_catalog\": {\"swe262_book\": [\n" +
                "  {\n" +
                "    \"swe262_price\": 44.95,\n" +
                "    \"swe262_genre\": \"Computer\",\n" +
                "    \"swe262_author\": \"Gambardella, Matthew\",\n" +
                "    \"swe262_publish_date\": \"2000-10-01\",\n" +
                "    \"swe262_title\": \"XML Developer's Guide\",\n" +
                "    \"swe262_description\": \"An in-depth look at creating applications \\n      with XML.\",\n" +
                "    \"swe262_id\": \"bk101\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 5.95,\n" +
                "    \"swe262_genre\": \"Fantasy\",\n" +
                "    \"swe262_author\": \"Ralls, Kim\",\n" +
                "    \"swe262_publish_date\": \"2000-12-16\",\n" +
                "    \"swe262_title\": \"Midnight Rain\",\n" +
                "    \"swe262_description\": \"A former architect battles corporate zombies, \\n      an evil sorceress, and her own childhood to become queen \\n      of the world.\",\n" +
                "    \"swe262_id\": \"bk102\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 5.95,\n" +
                "    \"swe262_genre\": \"Fantasy\",\n" +
                "    \"swe262_author\": \"Corets, Eva\",\n" +
                "    \"swe262_publish_date\": \"2000-11-17\",\n" +
                "    \"swe262_title\": \"Maeve Ascendant\",\n" +
                "    \"swe262_description\": \"After the collapse of a nanotechnology \\n      society in England, the young survivors lay the \\n      foundation for a new society.\",\n" +
                "    \"swe262_id\": \"bk103\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 5.95,\n" +
                "    \"swe262_genre\": \"Fantasy\",\n" +
                "    \"swe262_author\": \"Corets, Eva\",\n" +
                "    \"swe262_publish_date\": \"2001-03-10\",\n" +
                "    \"swe262_title\": \"Oberon's Legacy\",\n" +
                "    \"swe262_description\": \"In post-apocalypse England, the mysterious \\n      agent known only as Oberon helps to create a new life \\n      for the inhabitants of London. Sequel to Maeve \\n      Ascendant.\",\n" +
                "    \"swe262_id\": \"bk104\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 5.95,\n" +
                "    \"swe262_genre\": \"Fantasy\",\n" +
                "    \"swe262_author\": \"Corets, Eva\",\n" +
                "    \"swe262_publish_date\": \"2001-09-10\",\n" +
                "    \"swe262_title\": \"The Sundered Grail\",\n" +
                "    \"swe262_description\": \"The two daughters of Maeve, half-sisters, \\n      battle one another for control of England. Sequel to \\n      Oberon's Legacy.\",\n" +
                "    \"swe262_id\": \"bk105\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 4.95,\n" +
                "    \"swe262_genre\": \"Romance\",\n" +
                "    \"swe262_author\": \"Randall, Cynthia\",\n" +
                "    \"swe262_publish_date\": \"2000-09-02\",\n" +
                "    \"swe262_title\": \"Lover Birds\",\n" +
                "    \"swe262_description\": \"When Carla meets Paul at an ornithology \\n      conference, tempers fly as feathers get ruffled.\",\n" +
                "    \"swe262_id\": \"bk106\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 4.95,\n" +
                "    \"swe262_genre\": \"Romance\",\n" +
                "    \"swe262_author\": \"Thurman, Paula\",\n" +
                "    \"swe262_publish_date\": \"2000-11-02\",\n" +
                "    \"swe262_title\": \"Splish Splash\",\n" +
                "    \"swe262_description\": \"A deep sea diver finds true love twenty \\n      thousand leagues beneath the sea.\",\n" +
                "    \"swe262_id\": \"bk107\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 4.95,\n" +
                "    \"swe262_genre\": \"Horror\",\n" +
                "    \"swe262_author\": \"Knorr, Stefan\",\n" +
                "    \"swe262_publish_date\": \"2000-12-06\",\n" +
                "    \"swe262_title\": \"Creepy Crawlies\",\n" +
                "    \"swe262_description\": \"An anthology of horror stories about roaches,\\n      centipedes, scorpions  and other insects.\",\n" +
                "    \"swe262_id\": \"bk108\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 6.95,\n" +
                "    \"swe262_genre\": \"Science Fiction\",\n" +
                "    \"swe262_author\": \"Kress, Peter\",\n" +
                "    \"swe262_publish_date\": \"2000-11-02\",\n" +
                "    \"swe262_title\": \"Paradox Lost\",\n" +
                "    \"swe262_description\": \"After an inadvertant trip through a Heisenberg\\n      Uncertainty Device, James Salway discovers the problems \\n      of being quantum.\",\n" +
                "    \"swe262_id\": \"bk109\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 36.95,\n" +
                "    \"swe262_genre\": \"Computer\",\n" +
                "    \"swe262_author\": \"O'Brien, Tim\",\n" +
                "    \"swe262_publish_date\": \"2000-12-09\",\n" +
                "    \"swe262_title\": \"Microsoft .NET: The Programming Bible\",\n" +
                "    \"swe262_description\": \"Microsoft's .NET initiative is explored in \\n      detail in this deep programmer's reference.\",\n" +
                "    \"swe262_id\": \"bk110\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 36.95,\n" +
                "    \"swe262_genre\": \"Computer\",\n" +
                "    \"swe262_author\": \"O'Brien, Tim\",\n" +
                "    \"swe262_publish_date\": \"2000-12-01\",\n" +
                "    \"swe262_title\": \"MSXML3: A Comprehensive Guide\",\n" +
                "    \"swe262_description\": \"The Microsoft MSXML3 parser is covered in \\n      detail, with attention to XML DOM interfaces, XSLT processing, \\n      SAX and more.\",\n" +
                "    \"swe262_id\": \"bk111\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"swe262_price\": 49.95,\n" +
                "    \"swe262_genre\": \"Computer\",\n" +
                "    \"swe262_author\": \"Galos, Mike\",\n" +
                "    \"swe262_publish_date\": \"2001-04-16\",\n" +
                "    \"swe262_title\": \"Visual Studio 7: A Comprehensive Guide\",\n" +
                "    \"swe262_description\": \"Microsoft Visual Studio 7 is explored in depth,\\n      looking at how Visual Basic, Visual C++, C#, and ASP+ are \\n      integrated into a comprehensive development \\n      environment.\",\n" +
                "    \"swe262_id\": \"bk112\"\n" +
                "  }\n" +
                "]}}";

        // WHEN
        JSONObject jsonObject = XML.toJSONObject(reader, (String key) -> keyPrefix + key);

        //THEN
        Assert.assertEquals(expectedStr, jsonObject.toString(2));
        //Util.compareActualVsExpectedJsonObjects(jsonObject,expectedJsonObject);
    }

    /**
     * Tests if key prefix is added to each key in a nested JSONObject, containing JSONArray
     * Not going through any JSONArrays
     */
    @Test
    public void testTask4ValidUppercase() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        String expectedStr ="{\"CATALOG\": {\"BOOK\": [\n" +
                "  {\n" +
                "    \"PRICE\": 44.95,\n" +
                "    \"DESCRIPTION\": \"An in-depth look at creating applications \\n      with XML.\",\n" +
                "    \"TITLE\": \"XML Developer's Guide\",\n" +
                "    \"GENRE\": \"Computer\",\n" +
                "    \"ID\": \"bk101\",\n" +
                "    \"AUTHOR\": \"Gambardella, Matthew\",\n" +
                "    \"PUBLISH_DATE\": \"2000-10-01\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 5.95,\n" +
                "    \"DESCRIPTION\": \"A former architect battles corporate zombies, \\n      an evil sorceress, and her own childhood to become queen \\n      of the world.\",\n" +
                "    \"TITLE\": \"Midnight Rain\",\n" +
                "    \"GENRE\": \"Fantasy\",\n" +
                "    \"ID\": \"bk102\",\n" +
                "    \"AUTHOR\": \"Ralls, Kim\",\n" +
                "    \"PUBLISH_DATE\": \"2000-12-16\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 5.95,\n" +
                "    \"DESCRIPTION\": \"After the collapse of a nanotechnology \\n      society in England, the young survivors lay the \\n      foundation for a new society.\",\n" +
                "    \"TITLE\": \"Maeve Ascendant\",\n" +
                "    \"GENRE\": \"Fantasy\",\n" +
                "    \"ID\": \"bk103\",\n" +
                "    \"AUTHOR\": \"Corets, Eva\",\n" +
                "    \"PUBLISH_DATE\": \"2000-11-17\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 5.95,\n" +
                "    \"DESCRIPTION\": \"In post-apocalypse England, the mysterious \\n      agent known only as Oberon helps to create a new life \\n      for the inhabitants of London. Sequel to Maeve \\n      Ascendant.\",\n" +
                "    \"TITLE\": \"Oberon's Legacy\",\n" +
                "    \"GENRE\": \"Fantasy\",\n" +
                "    \"ID\": \"bk104\",\n" +
                "    \"AUTHOR\": \"Corets, Eva\",\n" +
                "    \"PUBLISH_DATE\": \"2001-03-10\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 5.95,\n" +
                "    \"DESCRIPTION\": \"The two daughters of Maeve, half-sisters, \\n      battle one another for control of England. Sequel to \\n      Oberon's Legacy.\",\n" +
                "    \"TITLE\": \"The Sundered Grail\",\n" +
                "    \"GENRE\": \"Fantasy\",\n" +
                "    \"ID\": \"bk105\",\n" +
                "    \"AUTHOR\": \"Corets, Eva\",\n" +
                "    \"PUBLISH_DATE\": \"2001-09-10\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 4.95,\n" +
                "    \"DESCRIPTION\": \"When Carla meets Paul at an ornithology \\n      conference, tempers fly as feathers get ruffled.\",\n" +
                "    \"TITLE\": \"Lover Birds\",\n" +
                "    \"GENRE\": \"Romance\",\n" +
                "    \"ID\": \"bk106\",\n" +
                "    \"AUTHOR\": \"Randall, Cynthia\",\n" +
                "    \"PUBLISH_DATE\": \"2000-09-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 4.95,\n" +
                "    \"DESCRIPTION\": \"A deep sea diver finds true love twenty \\n      thousand leagues beneath the sea.\",\n" +
                "    \"TITLE\": \"Splish Splash\",\n" +
                "    \"GENRE\": \"Romance\",\n" +
                "    \"ID\": \"bk107\",\n" +
                "    \"AUTHOR\": \"Thurman, Paula\",\n" +
                "    \"PUBLISH_DATE\": \"2000-11-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 4.95,\n" +
                "    \"DESCRIPTION\": \"An anthology of horror stories about roaches,\\n      centipedes, scorpions  and other insects.\",\n" +
                "    \"TITLE\": \"Creepy Crawlies\",\n" +
                "    \"GENRE\": \"Horror\",\n" +
                "    \"ID\": \"bk108\",\n" +
                "    \"AUTHOR\": \"Knorr, Stefan\",\n" +
                "    \"PUBLISH_DATE\": \"2000-12-06\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 6.95,\n" +
                "    \"DESCRIPTION\": \"After an inadvertant trip through a Heisenberg\\n      Uncertainty Device, James Salway discovers the problems \\n      of being quantum.\",\n" +
                "    \"TITLE\": \"Paradox Lost\",\n" +
                "    \"GENRE\": \"Science Fiction\",\n" +
                "    \"ID\": \"bk109\",\n" +
                "    \"AUTHOR\": \"Kress, Peter\",\n" +
                "    \"PUBLISH_DATE\": \"2000-11-02\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 36.95,\n" +
                "    \"DESCRIPTION\": \"Microsoft's .NET initiative is explored in \\n      detail in this deep programmer's reference.\",\n" +
                "    \"TITLE\": \"Microsoft .NET: The Programming Bible\",\n" +
                "    \"GENRE\": \"Computer\",\n" +
                "    \"ID\": \"bk110\",\n" +
                "    \"AUTHOR\": \"O'Brien, Tim\",\n" +
                "    \"PUBLISH_DATE\": \"2000-12-09\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 36.95,\n" +
                "    \"DESCRIPTION\": \"The Microsoft MSXML3 parser is covered in \\n      detail, with attention to XML DOM interfaces, XSLT processing, \\n      SAX and more.\",\n" +
                "    \"TITLE\": \"MSXML3: A Comprehensive Guide\",\n" +
                "    \"GENRE\": \"Computer\",\n" +
                "    \"ID\": \"bk111\",\n" +
                "    \"AUTHOR\": \"O'Brien, Tim\",\n" +
                "    \"PUBLISH_DATE\": \"2000-12-01\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"PRICE\": 49.95,\n" +
                "    \"DESCRIPTION\": \"Microsoft Visual Studio 7 is explored in depth,\\n      looking at how Visual Basic, Visual C++, C#, and ASP+ are \\n      integrated into a comprehensive development \\n      environment.\",\n" +
                "    \"TITLE\": \"Visual Studio 7: A Comprehensive Guide\",\n" +
                "    \"GENRE\": \"Computer\",\n" +
                "    \"ID\": \"bk112\",\n" +
                "    \"AUTHOR\": \"Galos, Mike\",\n" +
                "    \"PUBLISH_DATE\": \"2001-04-16\"\n" +
                "  }\n" +
                "]}}";

        // WHEN
        JSONObject jsonObject = XML.toJSONObject(reader, (String key) -> key.toUpperCase());

        //THEN
        Assert.assertEquals(expectedStr, jsonObject.toString(2));
        //Util.compareActualVsExpectedJsonObjects(jsonObject,expectedJsonObject);
    }

}

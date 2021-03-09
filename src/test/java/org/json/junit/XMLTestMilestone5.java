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

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


/**
 * Tests for JSON-Java XML.java
 * Tests Milestone 5 methods
 * The following tests use the absolute path for books.xml which is located in the 'xmls' folder within the 'test' folder
 */
public class XMLTestMilestone5 {

    /**
     * Tests if the asynchronous method works correctly on just one file
     */
    @Test
    public void testOneFile() throws IOException, ExecutionException, InterruptedException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        String expectedStr = "{\"catalog\": {\"book\": [\n" +
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
                "]}}";

        // WHEN
        XML.AsyncJSON asyncJSON = new XML.AsyncJSON();
        JSONObject jsonObject = asyncJSON.submitTask(reader).get();

        //THEN
        Assert.assertEquals(expectedStr, jsonObject.toString(2));
    }

    /**
     * Tests if the asynchronous method works correctly on two files, one being a large file (740MB)
     * NOTE: Could not upload medium.xml to GitHub due to large file size!
     */
    @Test
    public void testTwoFiles() throws IOException, ExecutionException, InterruptedException {
        // GIVEN

        FileReader reader1 = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        FileReader reader2 = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/medium.xml");

        BlockingQueue<Future<JSONObject>> q = new LinkedBlockingQueue<>();

        // expected for books.xml
        String expectedStr1 = "{\"catalog\": {\"book\": [\n" +
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
                "]}}";

        // WHEN
        XML.AsyncJSON a = new XML.AsyncJSON();
        q.put(a.submitTask(reader1));
        q.put(a.submitTask(reader2));
        a.shutdownAndClose();

        BufferedWriter output1 = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books_testresult.json"));
        JSONObject obj1 = q.take().get();
        obj1.write(output1, 2, 2);
        output1.close();

        BufferedWriter output2 = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/medium_testresult.json"));
        JSONObject obj2 = q.take().get();
        obj2.write(output2, 2, 2);
        output2.close();

        //THEN
        Assert.assertEquals(expectedStr1, obj1.toString(2));
        assertTrue(!obj2.isEmpty());
    }

}

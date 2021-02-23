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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointerException;
import org.json.JSONTokener;
import org.json.XML;
import org.json.junit.data.BrokenToString;
import org.json.junit.data.ExceptionalBean;
import org.json.junit.data.Fraction;
import org.json.junit.data.GenericBean;
import org.json.junit.data.GenericBeanInt;
import org.json.junit.data.MyBean;
import org.json.junit.data.MyBeanCustomName;
import org.json.junit.data.MyBeanCustomNameSubClass;
import org.json.junit.data.MyBigNumberBean;
import org.json.junit.data.MyEnum;
import org.json.junit.data.MyEnumField;
import org.json.junit.data.MyJsonString;
import org.json.junit.data.MyNumber;
import org.json.junit.data.MyNumberContainer;
import org.json.junit.data.MyPublicClass;
import org.json.junit.data.Singleton;
import org.json.junit.data.SingletonEnum;
import org.json.junit.data.WeirdList;
import org.junit.Test;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class JSONObjectTestMilestone4 {

    /**
     * Tests chaining the toStream() method with a .forEach() function.
     * @throws IOException
     */
    @Test
    public void toStreamTestForEach() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONObject obj = XML.toJSONObject(reader);
        String expectedStr = "[{\"author\":\"Gambardella, Matthew\",\"price\":44.95,\"genre\":\"Computer\",\"description\":\"An in-depth look at creating applications \\n      with XML.\",\"id\":\"bk101\",\"title\":\"XML Developer's Guide\",\"publish_date\":\"2000-10-01\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Ralls, Kim\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"A former architect battles corporate zombies, \\n      an evil sorceress, and her own childhood to become queen \\n      of the world.\",\"id\":\"bk102\",\"title\":\"Midnight Rain\",\"publish_date\":\"2000-12-16\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Corets, Eva\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"After the collapse of a nanotechnology \\n      society in England, the young survivors lay the \\n      foundation for a new society.\",\"id\":\"bk103\",\"title\":\"Maeve Ascendant\",\"publish_date\":\"2000-11-17\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Corets, Eva\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"In post-apocalypse England, the mysterious \\n      agent known only as Oberon helps to create a new life \\n      for the inhabitants of London. Sequel to Maeve \\n      Ascendant.\",\"id\":\"bk104\",\"title\":\"Oberon's Legacy\",\"publish_date\":\"2001-03-10\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Corets, Eva\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"The two daughters of Maeve, half-sisters, \\n      battle one another for control of England. Sequel to \\n      Oberon's Legacy.\",\"id\":\"bk105\",\"title\":\"The Sundered Grail\",\"publish_date\":\"2001-09-10\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Randall, Cynthia\",\"price\":4.95,\"genre\":\"Romance\",\"description\":\"When Carla meets Paul at an ornithology \\n      conference, tempers fly as feathers get ruffled.\",\"id\":\"bk106\",\"title\":\"Lover Birds\",\"publish_date\":\"2000-09-02\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Thurman, Paula\",\"price\":4.95,\"genre\":\"Romance\",\"description\":\"A deep sea diver finds true love twenty \\n      thousand leagues beneath the sea.\",\"id\":\"bk107\",\"title\":\"Splish Splash\",\"publish_date\":\"2000-11-02\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Knorr, Stefan\",\"price\":4.95,\"genre\":\"Horror\",\"description\":\"An anthology of horror stories about roaches,\\n      centipedes, scorpions  and other insects.\",\"id\":\"bk108\",\"title\":\"Creepy Crawlies\",\"publish_date\":\"2000-12-06\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Kress, Peter\",\"price\":6.95,\"genre\":\"Science Fiction\",\"description\":\"After an inadvertant trip through a Heisenberg\\n      Uncertainty Device, James Salway discovers the problems \\n      of being quantum.\",\"id\":\"bk109\",\"title\":\"Paradox Lost\",\"publish_date\":\"2000-11-02\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"O'Brien, Tim\",\"price\":36.95,\"genre\":\"Computer\",\"description\":\"Microsoft's .NET initiative is explored in \\n      detail in this deep programmer's reference.\",\"id\":\"bk110\",\"title\":\"Microsoft .NET: The Programming Bible\",\"publish_date\":\"2000-12-09\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"O'Brien, Tim\",\"price\":36.95,\"genre\":\"Computer\",\"description\":\"The Microsoft MSXML3 parser is covered in \\n      detail, with attention to XML DOM interfaces, XSLT processing, \\n      SAX and more.\",\"id\":\"bk111\",\"title\":\"MSXML3: A Comprehensive Guide\",\"publish_date\":\"2000-12-01\",\"tmpKey\":\"tmpVal\"}, {\"author\":\"Galos, Mike\",\"price\":49.95,\"genre\":\"Computer\",\"description\":\"Microsoft Visual Studio 7 is explored in depth,\\n      looking at how Visual Basic, Visual C++, C#, and ASP+ are \\n      integrated into a comprehensive development \\n      environment.\",\"id\":\"bk112\",\"title\":\"Visual Studio 7: A Comprehensive Guide\",\"publish_date\":\"2001-04-16\",\"tmpKey\":\"tmpVal\"}]";

        // WHEN
        List<JSONObject> list = new ArrayList<>();
        obj.toStream().forEach(node -> list.add(node.put("tmpKey", "tmpVal")));

        // THEN
        assertEquals(expectedStr, list.toString());

    }

    /**
     * Tests chaining the toStream() method with a .map() function.
     * @throws IOException
     */
    @Test
    public void toStreamMap() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONObject obj = XML.toJSONObject(reader);
        String expectedStr = "[XML Developer's Guide, Midnight Rain, Maeve Ascendant, Oberon's Legacy, The Sundered Grail, Lover Birds, Splish Splash, Creepy Crawlies, Paradox Lost, Microsoft .NET: The Programming Bible, MSXML3: A Comprehensive Guide, Visual Studio 7: A Comprehensive Guide]";
        // WHEN
        List<JSONObject> list = new ArrayList<>();
        List<Object> titles = obj.toStream().map(node -> node.get("title")).collect(Collectors.toList());

        // THEN
        assertEquals(expectedStr, titles.toString());
    }

    /**
     * Tests chaining the toStream() method with a .filter() function.
     * @throws IOException
     */
    @Test
    public void toStreamFilter() throws IOException {
        // GIVEN
        FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/java/org/json/junit/xmls/books.xml");
        JSONObject obj = XML.toJSONObject(reader);
        String expectedStr = "[{\"author\":\"Gambardella, Matthew\",\"price\":44.95,\"genre\":\"Computer\",\"description\":\"An in-depth look at creating applications \\n      with XML.\",\"id\":\"bk101\",\"title\":\"XML Developer's Guide\",\"publish_date\":\"2000-10-01\"}, {\"author\":\"Ralls, Kim\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"A former architect battles corporate zombies, \\n      an evil sorceress, and her own childhood to become queen \\n      of the world.\",\"id\":\"bk102\",\"title\":\"Midnight Rain\",\"publish_date\":\"2000-12-16\"}, {\"author\":\"Corets, Eva\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"After the collapse of a nanotechnology \\n      society in England, the young survivors lay the \\n      foundation for a new society.\",\"id\":\"bk103\",\"title\":\"Maeve Ascendant\",\"publish_date\":\"2000-11-17\"}, {\"author\":\"Corets, Eva\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"In post-apocalypse England, the mysterious \\n      agent known only as Oberon helps to create a new life \\n      for the inhabitants of London. Sequel to Maeve \\n      Ascendant.\",\"id\":\"bk104\",\"title\":\"Oberon's Legacy\",\"publish_date\":\"2001-03-10\"}, {\"author\":\"Corets, Eva\",\"price\":5.95,\"genre\":\"Fantasy\",\"description\":\"The two daughters of Maeve, half-sisters, \\n      battle one another for control of England. Sequel to \\n      Oberon's Legacy.\",\"id\":\"bk105\",\"title\":\"The Sundered Grail\",\"publish_date\":\"2001-09-10\"}, {\"author\":\"Randall, Cynthia\",\"price\":4.95,\"genre\":\"Romance\",\"description\":\"When Carla meets Paul at an ornithology \\n      conference, tempers fly as feathers get ruffled.\",\"id\":\"bk106\",\"title\":\"Lover Birds\",\"publish_date\":\"2000-09-02\"}, {\"author\":\"Thurman, Paula\",\"price\":4.95,\"genre\":\"Romance\",\"description\":\"A deep sea diver finds true love twenty \\n      thousand leagues beneath the sea.\",\"id\":\"bk107\",\"title\":\"Splish Splash\",\"publish_date\":\"2000-11-02\"}, {\"author\":\"Knorr, Stefan\",\"price\":4.95,\"genre\":\"Horror\",\"description\":\"An anthology of horror stories about roaches,\\n      centipedes, scorpions  and other insects.\",\"id\":\"bk108\",\"title\":\"Creepy Crawlies\",\"publish_date\":\"2000-12-06\"}, {\"author\":\"Kress, Peter\",\"price\":6.95,\"genre\":\"Science Fiction\",\"description\":\"After an inadvertant trip through a Heisenberg\\n      Uncertainty Device, James Salway discovers the problems \\n      of being quantum.\",\"id\":\"bk109\",\"title\":\"Paradox Lost\",\"publish_date\":\"2000-11-02\"}, {\"author\":\"O'Brien, Tim\",\"price\":36.95,\"genre\":\"Computer\",\"description\":\"Microsoft's .NET initiative is explored in \\n      detail in this deep programmer's reference.\",\"id\":\"bk110\",\"title\":\"Microsoft .NET: The Programming Bible\",\"publish_date\":\"2000-12-09\"}, {\"author\":\"O'Brien, Tim\",\"price\":36.95,\"genre\":\"Computer\",\"description\":\"The Microsoft MSXML3 parser is covered in \\n      detail, with attention to XML DOM interfaces, XSLT processing, \\n      SAX and more.\",\"id\":\"bk111\",\"title\":\"MSXML3: A Comprehensive Guide\",\"publish_date\":\"2000-12-01\"}, {\"author\":\"Galos, Mike\",\"price\":49.95,\"genre\":\"Computer\",\"description\":\"Microsoft Visual Studio 7 is explored in depth,\\n      looking at how Visual Basic, Visual C++, C#, and ASP+ are \\n      integrated into a comprehensive development \\n      environment.\",\"id\":\"bk112\",\"title\":\"Visual Studio 7: A Comprehensive Guide\",\"publish_date\":\"2001-04-16\"}]";

        // WHEN
        List<JSONObject> list = new ArrayList<>();
        List<Object> titles = obj.toStream().filter(node -> node.has("title")).collect(Collectors.toList());

        // THEN
        assertEquals(expectedStr, titles.toString());
    }

}
